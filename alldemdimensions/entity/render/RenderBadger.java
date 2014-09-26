package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import alldemdimensions.entity.EntityBadger;

public class RenderBadger extends RenderLiving
{
    public RenderBadger(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderBadger(EntityBadger entitybadger, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entitybadger, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderBadger((EntityBadger)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderBadger((EntityBadger)entity, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/badgerWild.png");
}
