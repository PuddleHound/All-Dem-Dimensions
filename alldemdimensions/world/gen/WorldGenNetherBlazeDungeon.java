package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenNetherBlazeDungeon extends WorldGenerator
{

    public WorldGenNetherBlazeDungeon()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j, k) != Blocks.netherrack)
		{
			return false;
		}
		int l = random.nextInt(3) + 5;
		int i1 = random.nextInt(6) + 10;
		int j1 = random.nextInt(3) + 5;
                addBox(world, random, i, j, k, l, 1, j1, AllDemDimensions.netherStone, AllDemDimensions.netherStone.BLAZESTONE_BRICK, false);
		addBox(world, random, i, j + 1, k, l, i1, j1, Blocks.air, 0, false);
		addBox(world, random, i, j + i1 + 1, k, l, 1, j1, AllDemDimensions.netherStone, AllDemDimensions.netherStone.BLAZESTONE_BRICK, false);
		int k1 = 0;
		while(k1 <= (l * 2))
		{
			if(k1 % 2 == 0)
			{
				addColumn(world, random, i + k1 - l, j + 1, k + j1 - 1, i1);
				addColumn(world, random, i + k1 - l, j + 1, k - j1, i1);
			}
			k1++;
		}
		int l1 = 0;
		while(l1 <= (j1 * 2))
		{
			if(l1 % 2 == 0)
			{
				addColumn(world, random, i + l - 1, j + 1, k + l1 - j1, i1);
				addColumn(world, random, i - l, j + 1, k + l1 - j1, i1);
			}
			l1++;
		}
		addLavaPool(world, random, i, j + 1, k);
        return true;
    }
	
	private void addBox(World world, Random random, int i, int j, int k, int l, int i1, int j1, Block blockID, int metadata, boolean flag)
	{
		for(int x = -l; x < l; x++)
		{
			for(int y = 0; y < i1; y++)
			{
				for(int z = -j1; z < j1; z++)
				{
					if(flag || (!world.getBlock(i + x, j + y, k + z).getMaterial().isLiquid() && !world.isAirBlock(i + x, j + y, k + z)))
					{
						setBlockAndNotifyAdequately(world, i + x, j + y, k + z, blockID, metadata);
					}
				}
			}
		}
	}
	
	private void addColumn(World world, Random random, int i, int j, int k, int l)
	{
		if(!world.isAirBlock(i, j - 1, k))//if(world.getBlock(i, j - 1, k) == AllDemDimensions.netherStone)
		{
			for(int y = 0; y < l; y++)
			{
				Block blockID = AllDemDimensions.column;
				int metadata = 0;
				if(y == 0 || y == l - 1)
				{
					blockID = AllDemDimensions.netherStone;
					metadata = AllDemDimensions.netherStone.BLAZESTONE_BRICK;
				}
				setBlockAndNotifyAdequately(world, i, j + y, k, blockID, metadata);
			}
		}
	}
	
	private void addLavaPool(World world, Random random, int i, int j, int k)
	{
		addBox(world, random, i, j, k, 2, 1, 2, AllDemDimensions.netherStone, AllDemDimensions.netherStone.BLAZESTONE_BRICK, true);
		addBox(world, random, i, j, k, 1, 1, 1, Blocks.lava, 0, false);
	}
}
