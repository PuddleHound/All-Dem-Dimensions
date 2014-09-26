package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import alldemdimensions.entity.EntityAmpfly;

public class RenderAmpfly extends RenderLiving
{
    public RenderAmpfly(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }
	
	public void renderAmpfly(EntityAmpfly ampfly, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(ampfly, d, d1, d2, f, f1);
		int[] attackLocation = ampfly.getEnergyAttackLocation();
		if(attackLocation == null || ampfly.getEnergyAttackDuration() <= 0)
		{
			return;
		}
		GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glColor4f(0.5F, 0.5F, 0.5F, ampfly.getEnergyAttackDuration() * 0.03125F);
		bindTexture(energyTexture);
		Tessellator tessellator = Tessellator.instance;
		
		float distance = (float)Math.sqrt(Math.pow(attackLocation[0] - ampfly.posX, 2) + Math.pow(attackLocation[1] - ampfly.posY, 2) + Math.pow(attackLocation[2] - ampfly.posZ, 2));
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(16777215);
		tessellator.addVertexWithUV(-0.25F, -0.25F, -0.25F, 0F, 0F);
		tessellator.addVertexWithUV(0.25F, 0.25F, 0.25F, 1F, 0F);
		tessellator.addVertexWithUV(attackLocation[0] - ampfly.posX + 0.25F, attackLocation[1] - ampfly.posY + 0.25F, attackLocation[2] - ampfly.posZ + 0.25F, 1F, distance);
		tessellator.addVertexWithUV(attackLocation[0] - ampfly.posX - 0.25F, attackLocation[1] - ampfly.posY - 0.25F, attackLocation[2] - ampfly.posZ - 0.25F, 0F, distance);
        tessellator.draw();
		
		GL11.glPopMatrix();
    }
	
    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderAmpfly((EntityAmpfly)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderAmpfly((EntityAmpfly)entity, d, d1, d2, f, f1);
    }
	
    @Override
	protected void preRenderCallback(EntityLivingBase entitylivingbase, float f)
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
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/ampfly.png");
    public static final ResourceLocation energyTexture = new ResourceLocation("alldemdimensions", "textures/entities/ampflyEnergy.png");
    
}
