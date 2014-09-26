package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import alldemdimensions.entity.EntitySycopter;

public class RenderSycopter extends RenderLiving
{
    public RenderSycopter(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderSycopter(EntitySycopter entitysycopter, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entitysycopter, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderSycopter((EntitySycopter)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderSycopter((EntitySycopter)entity, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/sycopter.png");
}
