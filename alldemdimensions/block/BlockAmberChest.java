package alldemdimensions.block;

import net.minecraft.block.BlockChest;
import net.minecraft.client.renderer.tileentity.TileEntityRendererChestHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import alldemdimensions.AllDemDimensionsClient;

public class BlockAmberChest extends BlockChest implements IBlockRenderer
{

    public BlockAmberChest()
    {
        super(0);
    }
	
    @Override
	public int getRenderType()
	{
		return AllDemDimensionsClient.renderId;
	}
	
    @Override
    public TileEntity createNewTileEntity(World world, int i)
    {
        return new TileEntityAmberChest();
    }
    
    @Override
    public void render(BlockRenderer br)
    {
    	//uses TileEntityRenderer for block
        if(br.isItem())
        {
            GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            TileEntityAmberChest chest = new TileEntityAmberChest();
            TileEntityRendererChestHelper.instance.renderChest(this, 0, 0F);//(chest, 0.0D, 0.0D, 0.0D, 0.0F);
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
        }
    }
}
