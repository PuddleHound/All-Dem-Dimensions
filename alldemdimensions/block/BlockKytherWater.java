package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;

public class BlockKytherWater extends Block
{
    public BlockKytherWater()
    {
        super(Material.water);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = Blocks.water.getBlockTextureFromSide(1);
    }
	
    @Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block l)
    {
		spawnWaterBlocks(world, i, j, k);
	/*
		if(world.isAirBlock(i, j - 1, k))
		{
			world.setBlockWithNotify(i, j - 1, k, blockID);
			return;
		}
		boolean flag = false;
		for(int i1 = -1; i1 > 1; i1++)
		{
			for(int j1 = -1; j1 > 1; j1++)
			{
				if(world.isAirBlock(i + i1, j, k + j1))
				{
					world.setBlockWithNotify(i + i1, j, k + j1, blockID);
					flag = true;
				}
			}
		}
		if(!flag && world.isAirBlock(i, j + 1, k))
		{
			world.setBlockWithNotify(i, j + 1, k, blockID);
		}*/
	}
	
    @Override
	public void onBlockAdded(World world, int i, int j, int k)
	{
		spawnWaterBlocks(world, i, j, k);
	}
	
	public void spawnWaterBlocks(World world, int i, int j, int k)
	{
		if(world.provider.dimensionId != Dimension.kyther.dimensionId || j >= 191)
		{
			//world.setBlockWithNotify(i, j, k, Block.waterStill.blockID);
			return;
		}
		if(world.isAirBlock(i, j - 1, k))// && !world.getChunkFromBlockCoords(i, k).isEmpty())
		{
			world.setBlock(i, j - 1, k, this, 0, 2);
		}
		if(world.isAirBlock(i + 1, j, k))// && !world.getChunkFromBlockCoords(i + 1, k).isEmpty())
		{
			world.setBlock(i + 1, j, k, this, 0, 2);
		}
		if(world.isAirBlock(i, j, k + 1))// && !world.getChunkFromBlockCoords(i, k + 1).isEmpty())
		{
			world.setBlock(i, j, k + 1, this, 0, 2);
		}
		if(world.isAirBlock(i - 1, j, k))// && !world.getChunkFromBlockCoords(i - 1, k).isEmpty())
		{
			world.setBlock(i - 1, j, k, this, 0, 2);
		}
		if(world.isAirBlock(i, j, k - 1))// && !world.getChunkFromBlockCoords(i, k - 1).isEmpty())
		{
			world.setBlock(i, j, k - 1, this, 0, 2);
		}
	}
	
	public boolean canReplaceBlock(World world, int i, int j, int k)
	{
		if(world.isAirBlock(i, j, k) || (!world.getBlock(i, j, k).getMaterial().isSolid() && world.getBlock(i, j, k) != AllDemDimensions.bubble))
		{
			return true;
		}
		return false;
	}
	
    @Override
	public boolean getBlocksMovement(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return true;
    }
	
    @Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
    @Override
	public boolean isBlockSolid(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
		return false;
	}
        
    @Override
        public boolean canCollideCheck(int i, boolean flag)
    {
        return flag && i == 0;
    }
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
        Block i = iblockaccess.getBlock(x, y, z);

        if (i == this)
        {
            return false;
        }
        else
        {
            return super.shouldSideBeRendered(iblockaccess, x, y, z, side);
        }
    }
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return null;
    }
	
    @Override
	public Item getItemDropped(int i, Random random, int j)
    {
        return null;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }
	
    @Override
	public int getMixedBrightnessForBlock(IBlockAccess iblockaccess, int x, int y, int z)
    {
    	return Blocks.water.getMixedBrightnessForBlock(iblockaccess, x, y, z);
    }
    
	/*
    @Override
	public float getBlockBrightness(IBlockAccess iblockaccess, int x, int y, int z)
    {
        float f = iblockaccess.getLightBrightness(x, y, z);
        float f1 = iblockaccess.getLightBrightness(x, y + 1, z);
        return f <= f1 ? f1 : f;
    }*///1.7.2
	
    @Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	
    @Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	Blocks.water.randomDisplayTick(world, x, y, z, random);
    }
	
    @Override
	public boolean isReplaceable(IBlockAccess world, int i, int j, int k)
	{
		return true;
	}
}
