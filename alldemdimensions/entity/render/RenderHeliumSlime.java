package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import alldemdimensions.entity.EntityHeliumSlime;

public class RenderHeliumSlime extends RenderLiving
{
    public RenderHeliumSlime(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderHeliumSlime(EntityHeliumSlime entityheliumslime, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entityheliumslime, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderHeliumSlime((EntityHeliumSlime)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderHeliumSlime((EntityHeliumSlime)entity, d, d1, d2, f, f1);
    }
	
    @Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
		scaleHeliumSlime((EntityHeliumSlime)entityliving, f);
		GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
	protected void scaleHeliumSlime(EntityHeliumSlime slime, float f)
    {
        float size = (float)slime.getSlimeSize();
        float f1 = (slime.prevSquishFactor + (slime.squishFactor - slime.prevSquishFactor) * f) / (size * 0.5F + 1.0F);
        float f2 = 1.0F / (f1 + 1.0F);
        GL11.glScalef(f2 * size, 1.0F / f2 * size, f2 * size);
		
		if(slime.getJumpTick() > 0)
		{
			GL11.glScalef(1.0F, 1.0F + (float)(Math.cos((slime.getJumpTick() * 0.1F) % 180)) * 0.25F, 1.0F);
		}
    }
        
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/heliumSlime.png");
	
}
