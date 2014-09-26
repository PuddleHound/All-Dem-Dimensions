package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.DLOAD;
import static org.objectweb.asm.Opcodes.FLOAD;
import static org.objectweb.asm.Opcodes.ILOAD;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;
import static org.objectweb.asm.Opcodes.LLOAD;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import org.objectweb.asm.tree.InsnList;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.VarInsnNode;

public class EditBase
{
    
    public EditBase(String s, String s1)
    {
        targetClassName = s;
        targetClassNameObfuscated = s1;
        Method[] methods = getClass().getDeclaredMethods();
        Insert annotation;
        for(Method method : methods)
        {
            annotation = method.getAnnotation(Insert.class);
            if(annotation != null)
            {
                System.out.println("[ADD][ASM] Found new method to insert: " + method.getName());
                InsnList methodData = new InsnList();                
                Class[] parameters = method.getParameterTypes();
                if(parameters.length > annotation.paramIndices().length)
                {
                    System.err.println("[All Dem Dimensions] Method " + getClass().getName() + "." + method.getName() + " is missing parameter definitions; aborting insertion.");
                    return;
                }
                int index = 0;
                String methodSignature = "(";
                for(Class parameter : parameters)
                {
                    int parameterOpcode = ALOAD;
                    boolean primitiveParameter = false;
                    for(Class parameterType : typeToName.keySet())
                    {
                        if(parameter.isAssignableFrom(parameterType))
                        {
                            parameterOpcode = typeToOpcode.get(parameterType) != null ? typeToOpcode.get(parameterType) : parameterOpcode;
                            methodSignature = methodSignature + typeToName.get(parameterType);
                            primitiveParameter = true;
                            break;
                        }
                    }
                    if(!primitiveParameter)
                    {
                        methodSignature = methodSignature + "L" + parameter.getName().replaceAll("\\.", "/") + ";";
                    }
                    methodData.add(new VarInsnNode(parameterOpcode, annotation.paramIndices()[index]));
                    index++;
                }
                Class returnType = method.getReturnType();
                String returnString = typeToName.get(returnType) != null ? typeToName.get(returnType) : "L" + method.getReturnType().getName().replaceAll("\\.", "/") + ";";
                methodSignature = methodSignature + ")" + returnString;
                String className = getClass().getName().replaceAll("\\.", "/"); 
                String methodName = method.getName();
                System.out.println("[ADD][ASM] Inserting method; opcode: " + annotation.opcode() + ", class: " + className + ", name: " + methodName + ", signature: " + methodSignature);
                methodData.add(new MethodInsnNode(annotation.opcode(), className, methodName, methodSignature));
                methodsToInsert.put(annotation, methodData);
            }
        }
    }
    
    public static void addAllEdits()
    {
        initialized = true;
        //addEdit(new EditedEntityRenderer());
        addEdit(new EditGuiCreateWorld());
        addEdit(new EditBlock());
        addEdit(new EditBlockPortal());
        addEdit(new EditServerConfigurationManager());
        addEdit(new EditTeleporter());
        addEdit(new EditEntityThrowable());
        addEdit(new EditRenderBlocks());
        addEdit(new EditTileEntityChestRenderer());
        for(EditBase edit : edits)
        {
            edit.init();
        }
    }
    
    public static void addEdit(EditBase editbase)
    {
        edits.add(editbase);
    }
    
    public void init()
    {
    }
    
    public EditBase setType(int i)
    {
        type = i;
        return this;
    }
    
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.METHOD)
    public static @interface Insert
    {
        int opcode() default INVOKESTATIC;
        String memberName();
        String memberNameObf();
        String memberDesc();
        String[] instrNames() default {};
        String[] instrNamesObf() default {};
        int[] instrOpcodes();
        boolean[] replaceInstr() default {};
        int instrOffset() default 0;
        int[] paramIndices() default {};
        int insertOffset() default 0;
        int skipMatches() default 0;
    }
    
    public static final ArrayList<EditBase> edits = new ArrayList<EditBase>();
    public static boolean initialized;
    public int type;
    public static final byte TYPE_METHOD = 0;
    public static final HashMap<Class, String> typeToName = new HashMap<Class, String>();
    public static final HashMap<Class, Integer> typeToOpcode = new HashMap<Class, Integer>();
    static
    {
        typeToName.put(int.class, "I");
        typeToName.put(float.class, "F");
        typeToName.put(double.class, "D");
        typeToName.put(byte.class, "B");
        typeToName.put(boolean.class, "Z");
        typeToName.put(long.class, "L");
        typeToName.put(short.class, "S");
        typeToName.put(char.class, "C");
        typeToName.put(void.class, "V");
        typeToOpcode.put(int.class, ILOAD);
        typeToOpcode.put(float.class, FLOAD);
        typeToOpcode.put(double.class, DLOAD);
        typeToOpcode.put(long.class, LLOAD);
        typeToOpcode.put(Object.class, ALOAD);
    }
    public String targetClassName;
    public String targetClassNameObfuscated;
    public HashMap<Insert, InsnList> methodsToInsert = new HashMap<Insert, InsnList>();
}
