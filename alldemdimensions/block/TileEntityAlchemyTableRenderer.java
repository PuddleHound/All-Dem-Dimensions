package alldemdimensions.block;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class TileEntityAlchemyTableRenderer extends TileEntitySpecialRenderer
{

    public TileEntityAlchemyTableRenderer()
    {
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double d, double d1, double d2, float f)
    {
        GL11.glPushMatrix();
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        float x = (float)d + 0.5F, y = (float)d1 + 0.51F, z = (float)d2 + 0.5F;
        GL11.glTranslatef(x, y, z);
        bindTexture(bottleTexture);
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glTranslatef(0F, 0.75F, 0F);
        GL11.glRotatef(180F, 1F, 0F, 0F);
        GL11.glTranslatef(0F, -0.75F, 0F);
        GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        model.render(tileentity, x, y, z, 0F, 0F, 0F, 0F, 0F, 0.0625F);
        GL11.glPopMatrix();
    }
    
    private ModelAlchemyBottles model = new ModelAlchemyBottles();
    private ResourceLocation bottleTexture = new ResourceLocation("alldemdimensions", "textures/tileentity/alchemyBottles.png");
    
}
