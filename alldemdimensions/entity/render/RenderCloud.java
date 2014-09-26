package alldemdimensions.entity.render;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import alldemdimensions.AllDemDimensions;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderCloud extends Render
{

    public RenderCloud()
	{
    }

    @Override
    public void doRender(Entity entity, double d, double d1, double d2, float f, float f1)
    {
		GL11.glPushMatrix();
        GL11.glTranslatef((float)d, (float)d1, (float)d2);
        bindTexture(texture);
        Block block = AllDemDimensions.cloud;
        World world = entity.worldObj;
        GL11.glDisable(GL11.GL_LIGHTING);
        (new RenderBlocks()).renderBlockSandFalling(block, world, MathHelper.floor_double(entity.posX), MathHelper.floor_double(entity.posY), MathHelper.floor_double(entity.posZ), 0);
        GL11.glEnable(GL11.GL_LIGHTING);
        GL11.glPopMatrix();
    }
    
    @Override
    protected ResourceLocation getEntityTexture(Entity entity)
    {
        return texture;
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/entities/cloud.png");
}
