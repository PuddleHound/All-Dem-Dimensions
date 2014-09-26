package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockCandle extends Block implements IBlockRenderer
{
    public BlockCandle()
    {
        super(Material.circuits);
        setBlockBounds(0.4375F, 0F, 0.4375F, 0.5625F, 0.75F, 0.5625F);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/candle");
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
	public int getLightValue(IBlockAccess iblockaccess, int i, int j, int k) 
    {
		if(iblockaccess.getBlockMetadata(i, j, k) == LIGHTED)
		{
			return 12;
		}
        return super.getLightValue(iblockaccess, i, j, k);
    }

    @Override
	public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        if(world.getBlockMetadata(i, j, k) == LIGHTED)
		{
			double d = (double)((float)i + 0.5F + (random.nextFloat() * 0.05F) - (random.nextFloat() * 0.05F));
			double d1 = (double)((float)j + 0.85F + (random.nextFloat() * 0.05F) - (random.nextFloat() * 0.05F));
			double d2 = (double)((float)k + 0.5F + (random.nextFloat() * 0.05F) - (random.nextFloat() * 0.05F));
			world.spawnParticle("flame", d, d1, d2, 0.0D, 0.0D, 0.0D);
		}
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
    		return;//1.7
    	}
        br.setUseRenderBlocks(true);
        br.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.75F, 0.5625F);
        br.cuboid();
        br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leaf3"));
        br.setBlockBounds(0.48F, 0.75F, 0.48F, 0.52F, 0.85F, 0.52F);
        br.cuboid();
        br.setBlockTexture(null);
    }
	
	public static final byte UNLIGHTED = 0;
	public static final byte LIGHTED = 1;
	
}
