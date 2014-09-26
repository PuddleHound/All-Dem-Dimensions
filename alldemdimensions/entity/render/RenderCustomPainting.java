package alldemdimensions.entity.render;

import java.lang.reflect.Method;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPainting;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityPainting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import alldemdimensions.edit.ReflectionManager;
import alldemdimensions.entity.EntityCustomPainting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCustomPainting extends RenderPainting
{
    
    public RenderCustomPainting()
    {
        dynamicTexture = new DynamicTexture(512, 512);
        texture = null;//1.7.2 Minecraft.getMinecraft().getTextureManager().getDynamicTextureLocation("customPainting", dynamicTexture);
    }
    
    public void renderThePainting(EntityCustomPainting painting, double d, double d1, double d2, float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glRotatef(f, 0.0F, 1.0F, 0.0F);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        
		int[] pixelData = painting.pixels;
        int[] targetPixelData = dynamicTexture.getTextureData();
        for(int i = 0; i < targetPixelData.length; i++)
        {
            targetPixelData[i] = pixelData[i];
        }
        dynamicTexture.updateDynamicTexture();
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
		
        float scale = 0.0625F;
        GL11.glScalef(scale, scale, scale);
        //this.func_77010_a(painting, painting.sizeX, painting.sizeY, 0, 0);
        ReflectionManager.invokeMethod(this, method_RenderPainting_func_77010_a, painting, painting.sizeX, painting.sizeY, 0, 0);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderThePainting((EntityCustomPainting)entity, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public ResourceLocation texture;
    public DynamicTexture dynamicTexture;
    public static final Method method_RenderPainting_func_77010_a = ReflectionManager.accessMethod(RenderPainting.class, "func_77010_a", "", EntityPainting.class, int.class, int.class, int.class, int.class);
}
