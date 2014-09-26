package alldemdimensions.block; 

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockBubble extends Block
{
    public BlockBubble()
    {
        super(Material.gourd);
    }

    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return null;
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}
	
    @Override
	public int getRenderBlockPass()
	{
		return 1;
	}
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int side)
    {
        Block block = iblockaccess.getBlock(i, j, k);

        if (block == this)
        {
            return false;
        }
        else
        {
            return super.shouldSideBeRendered(iblockaccess, i, j, k, side);
        }
    }
	
    @Override
	public boolean isBlockSolid(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		return false;
	}
}
