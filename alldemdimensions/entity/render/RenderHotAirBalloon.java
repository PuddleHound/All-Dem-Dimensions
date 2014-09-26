package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import alldemdimensions.entity.EntityHotAirBalloon;

public class RenderHotAirBalloon extends Render
{
    public RenderHotAirBalloon(ModelBase modelbase)
    {
        super();
		shadowSize = 1.0F;
		model = modelbase;
    }
	
    public void renderHotAirBalloon(EntityHotAirBalloon balloon, double d, double d1, double d2, float f, float f1)
    {
        GL11.glPushMatrix();
		float offsetX = 0F;
		float offsetY = 0F;
		float offsetZ = 0F;
        GL11.glTranslatef((float)d + offsetX, (float)d1 + offsetY, (float)d2 + offsetZ);
        GL11.glRotatef(180.0F - f, 0.0F, 1.0F, 0.0F);
        bindTexture(texture);
		GL11.glScalef(-1.0F, -1.0F, 1.0F);
        model.render(balloon, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        renderHotAirBalloon((EntityHotAirBalloon)entity, d, d1, d2, f, f1);
    }
	
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/hotAirBalloon.png");
	public ModelBase model;
}
