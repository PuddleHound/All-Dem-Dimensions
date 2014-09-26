package alldemdimensions.entity.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import alldemdimensions.entity.EntityBluekite;

public class RenderBluekite extends RenderLiving
{
    public RenderBluekite(ModelBase modelbase)
    {
        super(modelbase, 0.0F);
    }

    public void renderBluekite(EntityBluekite entitybluekite, double d, double d1, double d2, float f, float f1)
    {
        super.doRender(entitybluekite, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(EntityLiving entityliving, double d, double d1, double d2, float f, float f1)
    {
        this.renderBluekite((EntityBluekite)entityliving, d, d1, d2, f, f1);
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
        this.renderBluekite((EntityBluekite)entity, d, d1, d2, f, f1);
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/bluekite.png");
}
