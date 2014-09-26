package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockCandlestick extends Block implements IBlockRenderer
{
    public BlockCandlestick()
    {
        super(Material.circuits);
        setBlockBounds(0.3125F, 0F, 0.3125F, 0.6875F, 1F, 0.6875F);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/silverBlock");
    }
	
    @Override
	public int getRenderType()
	{
		return AllDemDimensions.blockRenderId;
	}

    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

    @Override
    public void render(BlockRenderer br)
    {
    	if(br.isItem())
    	{
    		return;//1.7TEMP
    	}
        br.setUseRenderBlocks(true);
        br.setBlockBounds(0.3125F, 0.0F, 0.3125F, 0.6875F, 0.0625F, 0.6875F);
        br.cuboid();
        br.setBlockBounds(0.375F, 0.0625F, 0.375F, 0.625F, 0.125F, 0.625F);
        br.cuboid();
        br.setBlockBounds(0.4375F, 0.125F, 0.4375F, 0.5625F, 0.9375F, 0.5625F);
        br.cuboid();
        br.setBlockBounds(0.375F, 0.5F, 0.375F, 0.625F, 0.625F, 0.625F);
        br.cuboid();
        br.setBlockBounds(0.375F, 0.9375F, 0.375F, 0.625F, 1.0F, 0.625F);
        br.cuboid();
    }
	
}
