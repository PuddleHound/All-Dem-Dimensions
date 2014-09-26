package alldemdimensions.block;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class TileEntityEaselRenderer extends TileEntitySpecialRenderer
{

    public void renderTileEntityEaselAt(TileEntityEasel easel, double x, double y, double z, float f)
    {
		ItemStack painting = easel.getStackInSlot(easel.PAINTING_SLOT);
		if(painting == null || !painting.hasTagCompound())
		{
			return;
		}
		int metadata = easel.getBlockMetadata();
		float rotation = 0F;
		float offsetX = 0F;
		float offsetZ = 0F;
		if(metadata == 2)
		{
			offsetZ = -0.125F;
			rotation = 0F;
		}
		if(metadata == 5)
		{
			offsetX = 0.125F;
			rotation = 270F;
		}
		if(metadata == 3)
		{
			offsetZ = 0.125F;
			rotation = 180F;
		}
		if(metadata == 4)
		{
			offsetX = -0.125F;
			rotation = 90F;
		}
		
		int[] pixelData = painting.getTagCompound().getIntArray("pixels");
		GL11.glPushMatrix();
        GL11.glTranslatef((float)x + 0.5F + offsetX, (float)y + 1.125F, (float)z + 0.5F + offsetZ);
        GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        float scale = 0.0625F;
        GL11.glScalef(scale, scale, scale);
		GL11.glRotatef(rotation, 0, 1, 0);
        this.renderPainting(16, 16, 0, 0);
		GL11.glDisable(GL12.GL_RESCALE_NORMAL);
        GL11.glPopMatrix();
    }
	
	private void renderPainting(int i, int j, int k, int l)
    {
        float f = (float)(-i) / 2.0F;
        float f1 = (float)(-j) / 2.0F;
        float f2 = 0.5F;
        float f3 = 0.75F;
        float f4 = 0.8125F;
        float f5 = 0.0F;
        float f6 = 0.0625F;
        float f7 = 0.75F;
        float f8 = 0.8125F;
        float f9 = 0.001953125F;
        float f10 = 0.001953125F;
        float f11 = 0.7519531F;
        float f12 = 0.7519531F;
        float f13 = 0.0F;
        float f14 = 0.0625F;

        for (int i1 = 0; i1 < i / 16; ++i1)
        {
            for (int j1 = 0; j1 < j / 16; ++j1)
            {
                float f15 = f + (float)((i1 + 1) * 16);
                float f16 = f + (float)(i1 * 16);
                float f17 = f1 + (float)((j1 + 1) * 16);
                float f18 = f1 + (float)(j1 * 16);
                float f19 = (float)(k + i - i1 * 16) / 16.0F;
                float f20 = (float)(k + i - (i1 + 1) * 16) / 16.0F;
                float f21 = (float)(l + j - j1 * 16) / 16.0F;
                float f22 = (float)(l + j - (j1 + 1) * 16) / 16.0F;
                Tessellator tessellator = Tessellator.instance;
                tessellator.startDrawingQuads();
                tessellator.setNormal(0.0F, 0.0F, -1.0F);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)(-f2), (double)f20, (double)f21);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)(-f2), (double)f19, (double)f21);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)(-f2), (double)f19, (double)f22);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)(-f2), (double)f20, (double)f22);
                tessellator.setNormal(0.0F, 0.0F, 1.0F);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)f2, (double)f3, (double)f5);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)f2, (double)f4, (double)f5);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)f2, (double)f4, (double)f6);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)f2, (double)f3, (double)f6);
                tessellator.setNormal(0.0F, 1.0F, 0.0F);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)(-f2), (double)f7, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)(-f2), (double)f8, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)f2, (double)f8, (double)f10);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)f2, (double)f7, (double)f10);
                tessellator.setNormal(0.0F, -1.0F, 0.0F);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)f2, (double)f7, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)f2, (double)f8, (double)f9);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)(-f2), (double)f8, (double)f10);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)(-f2), (double)f7, (double)f10);
                tessellator.setNormal(-1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)f2, (double)f12, (double)f13);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)f2, (double)f12, (double)f14);
                tessellator.addVertexWithUV((double)f15, (double)f18, (double)(-f2), (double)f11, (double)f14);
                tessellator.addVertexWithUV((double)f15, (double)f17, (double)(-f2), (double)f11, (double)f13);
                tessellator.setNormal(1.0F, 0.0F, 0.0F);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)(-f2), (double)f12, (double)f13);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)(-f2), (double)f12, (double)f14);
                tessellator.addVertexWithUV((double)f16, (double)f18, (double)f2, (double)f11, (double)f14);
                tessellator.addVertexWithUV((double)f16, (double)f17, (double)f2, (double)f11, (double)f13);
                tessellator.draw();
            }
        }
    }

    @Override
    public void renderTileEntityAt(TileEntity tileentity, double x, double y, double z, float tick)
    {
        this.renderTileEntityEaselAt((TileEntityEasel)tileentity, x, y, z, tick);
    }
}
