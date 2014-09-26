package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import alldemdimensions.entity.EntityEnderwraith;

public class RenderEnderwraith extends RenderLiving
{
    public RenderEnderwraith(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderEnderwraith(EntityEnderwraith entityenderwraith, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entityenderwraith, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderEnderwraith((EntityEnderwraith)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderEnderwraith((EntityEnderwraith)entity, d, d1, d2, f, f1);
    }
	
    @Override
	protected void preRenderCallback(EntityLivingBase entityliving, float f)
    {
		GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/enderwraith.png");
	
}
