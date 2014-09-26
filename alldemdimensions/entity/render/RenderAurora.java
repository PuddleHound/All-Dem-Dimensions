package alldemdimensions.entity.render;

import java.util.Random;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

public class RenderAurora extends Render
{

    public RenderAurora()
	{
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
		GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
		GL11.glEnable(GL11.GL_NORMALIZE);
        GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        bindTexture(texture);

		Tessellator tessellator = Tessellator.instance;
		tessellator.startDrawingQuads();
		tessellator.setColorOpaque_I(16777215);
		for(int j = 1; j < 3; j++)
		{
			double minX = 0D;
			double minY = 0D;
			double minZ = 0D;
			double maxX = 0D;
			double maxY = 0D;
			double maxZ = 0D;
			for(int i = 0; i < 64; i++)
			{
				random.setSeed((entity.ticksExisted * j) + i);
				minX = maxX + (random.nextDouble() * 1D) - (random.nextDouble() * 1D);
				minY = (random.nextDouble() * 1D) - (random.nextDouble() * 1D);
				minZ = maxZ + (random.nextDouble() * 2D);
				maxX = minX + random.nextDouble() - random.nextDouble();
				maxY = minY + (random.nextDouble() * 3D) + 1D;
				maxZ = minZ + random.nextDouble() - random.nextDouble();
				tessellator.addVertexWithUV(minX, minY, minZ, 0D, 0D);
				tessellator.addVertexWithUV(minX, maxY, minZ, 0D, 1D);
				tessellator.addVertexWithUV(maxX, maxY, maxZ, 1D, 1D);
				tessellator.addVertexWithUV(maxX, minY, maxZ, 1D, 0D);
				
				tessellator.addVertexWithUV(maxX, minY, maxZ, 1D, 0D);
				tessellator.addVertexWithUV(maxX, maxY, maxZ, 1D, 1D);
				tessellator.addVertexWithUV(minX, maxY, minZ, 0D, 1D);
				tessellator.addVertexWithUV(minX, minY, minZ, 0D, 0D);
			}
		}
		tessellator.draw();
		GL11.glPopMatrix();
    }
	@Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/aurora.png");
	private Random random = new Random();
}
