package alldemdimensions.entity.render;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class RenderWind extends Render
{

    public RenderWind(int i)
    {
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        GL11.glScalef(0.5F, 0.5F, 0.5F);
        bindTexture(texture);
        Tessellator tessellator = Tessellator.instance;
        
        int pixelOffset = 0;
        float f2 = (float)(pixelOffset % 16 * 16 + 0) / 256.0F;
        float f3 = (float)(pixelOffset % 16 * 16 + 16) / 256.0F;
        float f4 = (float)(pixelOffset / 16 * 16 + 0) / 256.0F;
        float f5 = (float)(pixelOffset / 16 * 16 + 16) / 256.0F;
        float f6 = 1.0F;
        float f7 = 0.5F;
        float f8 = 0.25F;
        GL11.glRotatef(180.0F - this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
        GL11.glRotatef(-this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
		//GL11.glRotatef(entity.rotationYaw, (float)entity.posX + 1, (float)entity.posY + 1, (float)entity.posZ + 1);
        tessellator.startDrawingQuads();
        tessellator.setNormal(0.0F, 1.0F, 0.0F);
        tessellator.addVertexWithUV((double)(0.0F - f7), (double)(0.0F - f8), 0.0D, (double)f2, (double)f5);
        tessellator.addVertexWithUV((double)(f6 - f7), (double)(0.0F - f8), 0.0D, (double)f3, (double)f5);
        tessellator.addVertexWithUV((double)(f6 - f7), (double)(f6 - f8), 0.0D, (double)f3, (double)f4);
        tessellator.addVertexWithUV((double)(0.0F - f7), (double)(f6 - f8), 0.0D, (double)f2, (double)f4);
        tessellator.draw();
        
        GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/wind.png");
}
