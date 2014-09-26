package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import alldemdimensions.entity.EntityButterfly;

public class RenderButterfly extends RenderLiving
{
    public RenderButterfly(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderButterfly(EntityButterfly entitybutterfly, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entitybutterfly, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderButterfly((EntityButterfly)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderButterfly((EntityButterfly)entity, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/butterflyCyan.png");
}
