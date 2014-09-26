package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.GETFIELD;
import static org.objectweb.asm.Opcodes.GETSTATIC;
import static org.objectweb.asm.Opcodes.INVOKEDYNAMIC;
import static org.objectweb.asm.Opcodes.INVOKEINTERFACE;
import static org.objectweb.asm.Opcodes.INVOKESPECIAL;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import static org.objectweb.asm.Opcodes.PUTFIELD;
import static org.objectweb.asm.Opcodes.PUTSTATIC;

import java.util.Iterator;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class Transformer implements IClassTransformer
{
    
    public byte[] transform(String className, String string1, byte[] bytes)
    {
        //System.out.println("[ADD][ASM] Currently transforming: " + className);
        if(className.toLowerCase().contains("edit"))
        {
            return bytes;
        }
        if(!EditBase.initialized)
        {
            print("----------Transformation initializing.----------");
            EditBase.addAllEdits();
        }
        for(EditBase edit : EditBase.edits)
        {
            boolean obfuscated;
            if(className.equals(edit.targetClassName))
            {
                obfuscated = false;
            } else
            if(className.equals(edit.targetClassNameObfuscated))
            {
                obfuscated = true;
            } else
            {
                continue;
            }
            print("Found class to edit: " + edit.targetClassName);
            ClassNode classNode = new ClassNode();
            ClassReader classReader = new ClassReader(bytes);
            classReader.accept(classNode, 0);
            for(EditBase.Insert insert : edit.methodsToInsert.keySet())
            {
                String targetName = obfuscated ? insert.memberNameObf() : insert.memberName();
                /**METHOD SPECIFIC**/
                for(MethodNode m : classNode.methods)
                {
                    if((m.name.equals(targetName) && m.desc.equals(insert.memberDesc())))
                    {
                        print("Found member to edit: " + insert.memberName());
                        AbstractInsnNode currentNode = null;
                        Iterator<AbstractInsnNode> iter = m.instructions.iterator();
                        int opcode, validInstrCount = 0;
                        AbstractInsnNode[] instructionsToReplace = new AbstractInsnNode[insert.instrOpcodes().length];
                        int matchesToSkip = 0;// insert.skipMatches();
                        while(iter.hasNext())
                        {
                            currentNode = iter.next();
                            boolean matches = true;
                            AbstractInsnNode currentNode1 = currentNode;
                            for(int index = 0; index < instructionsToReplace.length; index++)
                            {
                                opcode = insert.instrOpcodes()[index];
                                if(currentNode1.getOpcode() == insert.instrOpcodes()[index] && ((insert.instrNames().length < instructionsToReplace.length
                                    || insert.instrNames()[index].equals("")) || (((isMethod(opcode) && ((obfuscated && ((MethodInsnNode)currentNode).name.equals(insert.instrNamesObf()[index])) 
                                    || !obfuscated && ((MethodInsnNode)currentNode).name.equals(insert.instrNames()[index]))) || (isField(opcode) && 
                                    ((obfuscated && ((FieldInsnNode)currentNode).name.equals(insert.instrNamesObf()[index])) 
                                    || !obfuscated && ((FieldInsnNode)currentNode).name.equals(insert.instrNames()[index])))))))
                                {
                                    instructionsToReplace[index] = currentNode1;
                                    currentNode1 = currentNode1.getNext();
                                } else
                                {
                                    matches = false;
                                    break;
                                }
                            }
                            if(matches)
                            {
                            	if(matchesToSkip > 0)
                            	{
                            		matchesToSkip--;
                            	} else
                                if(validInstrCount == insert.instrOffset())
                                {
                                    print("Found matching instruction sequence: ");
                                    for(AbstractInsnNode instruction : instructionsToReplace)
                                    {
                                        print("Opcode: " + instruction.getOpcode() + (currentNode instanceof MethodInsnNode ? ", Name: " + ((MethodInsnNode)currentNode).name : ""));
                                    }
                                    break;
                                }
                                validInstrCount++;
                            }
                            currentNode = null;
                        }
                        if(currentNode == null)
                        {
                            print("Unable to find target node; aborting.");
                            return bytes;
                        }
                        boolean insertBefore = insert.insertOffset() < 0;
                        if(insert.replaceInstr().length >= instructionsToReplace.length)
                        {
                            currentNode = instructionsToReplace[0].getPrevious();
                            //print(FOLLOWING NODE OPCODE: " + instructionsToReplace[instructionsToReplace.length - 1].getNext().getOpcode());
                            AbstractInsnNode node;
                            for(int index = 0; index < instructionsToReplace.length; index++)
                            {
                                node = instructionsToReplace[index];
                                if(insert.replaceInstr()[index])
                                {
                                    print("Removing instruction; opcode: " + node.getOpcode() + (node instanceof MethodInsnNode ? ", Name: " + ((MethodInsnNode)node).name : ""));
                                    m.instructions.remove(node);
                                } else
                                if(!insertBefore && insert.insertOffset() == index + 1)
                                {
                                    currentNode = node;
                                }
                            }
                        }
                        Iterator<AbstractInsnNode> iterator = edit.methodsToInsert.get(insert).iterator();
                        while(iterator.hasNext())
                        {
                            print("New instruction opcode: " + iterator.next().getOpcode());
                        }
                        if(insertBefore)
                        {
                            m.instructions.insertBefore(currentNode, edit.methodsToInsert.get(insert));
                        } else
                        {
                            m.instructions.insert(currentNode, edit.methodsToInsert.get(insert));
                        }
                        print("Successfully applied patch.");
                        break;
                    }
                }
            }
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            classNode.accept(writer);
            return writer.toByteArray();
        }
        return bytes;
    }
    
    public static boolean isMethod(int opcode)
    {
        return opcode == INVOKEVIRTUAL || opcode == INVOKESTATIC || opcode == INVOKESPECIAL || opcode == INVOKEINTERFACE || opcode == INVOKEDYNAMIC;
    }
    
    public static boolean isField(int opcode)
    {
        return opcode == GETFIELD || opcode == PUTFIELD || opcode == GETSTATIC || opcode == PUTSTATIC;
    }
    
    public static void print(String message)
    {
        System.out.println("[AllDemDimensions][ASM] " + message);
    }

}
