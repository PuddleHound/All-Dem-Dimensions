package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.FADD;
import static org.objectweb.asm.Opcodes.FLOAD;
import static org.objectweb.asm.Opcodes.FSTORE;
import static org.objectweb.asm.Opcodes.INVOKESTATIC;

import java.lang.reflect.Field;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;

public class EditEntityRenderer extends EditBase
{
    
    public EditEntityRenderer()
    {
        super("net.minecraft.client.renderer.EntityRenderer", "bfb");
    }
    
    @Override
    public void init()
    {
        EntityRenderer_fogColorRed = ReflectionManager.accessField(net.minecraft.client.renderer.EntityRenderer.class, "fogColorRed", "fogColorRed");
        EntityRenderer_fogColorGreen = ReflectionManager.accessField(net.minecraft.client.renderer.EntityRenderer.class, "fogColorGreen", "fogColorGreen");
        EntityRenderer_fogColorBlue = ReflectionManager.accessField(net.minecraft.client.renderer.EntityRenderer.class, "fogColorBlue", "fogColorBlue");
        EntityRenderer_torchFlickerX = ReflectionManager.accessField(net.minecraft.client.renderer.EntityRenderer.class, "torchFlickerX", "torchFlickerX");
    }
    
    @Insert(memberName = "updateFogColor", memberNameObf = "i", memberDesc = "(F)V", instrNames = "getBlockIdAtEntityViewpoint", instrNamesObf = "", instrOpcodes = INVOKESTATIC)
    public static void updateFogColor()
    {
        ReflectionManager.setFieldValue(Minecraft.getMinecraft().entityRenderer, EntityRenderer_fogColorRed, 1F);
        ReflectionManager.setFieldValue(Minecraft.getMinecraft().entityRenderer, EntityRenderer_fogColorGreen, 1F);
        ReflectionManager.setFieldValue(Minecraft.getMinecraft().entityRenderer, EntityRenderer_fogColorBlue, 0.1F);
    }
    
    @Insert(memberName = "updateLightmap", memberNameObf = "", memberDesc = "(F)V", instrOpcodes = {-1, -1, FLOAD, FLOAD, FADD, FSTORE, -1, -1}, replaceInstr = {false, false, true, true, true, false, false, false}, insertOffset = 3, paramIndices = 3)
    public static float updateLightmap_Red(int i)
    {
        System.out.println("red");
        WorldClient worldclient = Minecraft.getMinecraft().theWorld;
        float f1 = worldclient.getSunBrightness(1.0F) * 0.95F + 0.05F;
        float f2 = worldclient.provider.lightBrightnessTable[i / 16] * f1;
        float f3 = Minecraft.getMinecraft().theWorld.provider.lightBrightnessTable[i % 16] * ((Float)(ReflectionManager.getFieldValue(Minecraft.getMinecraft().entityRenderer, EntityRenderer_torchFlickerX)) * 0.1F + 1.5F);
        float f4 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
        float f8 = f3 + f4;
        //f8 = 0.22F + f3 * 0.75F;
        return f8;
    }    
    
    @Insert(memberName = "updateLightmap", memberNameObf = "pl", memberDesc = "(F)V", instrOpcodes = {FLOAD, FLOAD, FADD}, replaceInstr = {true, true, true}, paramIndices = 3)
    public static float updateLightmap_Green(int i)
    {
        System.out.println("green");
        WorldClient worldclient = Minecraft.getMinecraft().theWorld;
        float f1 = worldclient.getSunBrightness(1.0F) * 0.95F + 0.05F;
        float f2 = worldclient.provider.lightBrightnessTable[i / 16] * f1;
        float f3 = Minecraft.getMinecraft().theWorld.provider.lightBrightnessTable[i % 16] * ((Float)(ReflectionManager.getFieldValue(Minecraft.getMinecraft().entityRenderer, EntityRenderer_torchFlickerX)) * 0.1F + 1.5F);
        float f5 = f2 * (worldclient.getSunBrightness(1.0F) * 0.65F + 0.35F);
        float f6 = f3 * ((f3 * 0.6F + 0.4F) * 0.6F + 0.4F);
        float f9 = f5 + f6;
        //f9 = 0.28F + f6 * 0.75F;
        return f9;
    }
    
    @Insert(memberName = "updateLightmap", memberNameObf = "gh", memberDesc = "(F)V", instrOpcodes = {FLOAD, FLOAD, FADD}, replaceInstr = {true, true, true}, paramIndices = 3)
    public static float updateLightmap_Blue(int i)
    {
        System.out.println("blue");
        WorldClient worldclient = Minecraft.getMinecraft().theWorld;
        float f1 = worldclient.getSunBrightness(1.0F) * 0.95F + 0.05F;
        float f2 = worldclient.provider.lightBrightnessTable[i / 16] * f1;
        float f3 = Minecraft.getMinecraft().theWorld.provider.lightBrightnessTable[i % 16] * ((Float)(ReflectionManager.getFieldValue(Minecraft.getMinecraft().entityRenderer, EntityRenderer_torchFlickerX)) * 0.1F + 1.5F);
        float f7 = f3 * (f3 * f3 * 0.6F + 0.4F);
        float f10 = f2 + f7;
        //f10 = 0.25F + f7 * 0.75F;
        return f10;
    }
        
    private static Field EntityRenderer_fogColorRed;
    private static Field EntityRenderer_fogColorGreen;
    private static Field EntityRenderer_fogColorBlue;
    private static Field EntityRenderer_torchFlickerX;
    
}
