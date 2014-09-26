package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import alldemdimensions.entity.EntityCloudGolem;

public class RenderCloudGolem extends RenderLiving
{
    public RenderCloudGolem(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderCloudGolem(EntityCloudGolem entitycloudgolem, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entitycloudgolem, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderCloudGolem((EntityCloudGolem)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderCloudGolem((EntityCloudGolem)entity, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/cloudGolem.png");
}
