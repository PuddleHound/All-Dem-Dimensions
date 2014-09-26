package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyRingIsland extends WorldGenerator
{

    public WorldGenSkyRingIsland()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		int j1 = random.nextInt(9) + 9;
		for(int l = -j1; l < j1; l++)
		{
			for(int i1 = -j1; i1 < j1; i1++)
			{
				int k1 = l * i1;
				int l1 = sqr(l) + sqr(i1);
				int i2 = sqr(j1);
				if(l1 > i2 - 3 && l1 < i2 + 3)
				{
					addCircle(world, random, i + l, j, k + i1, j1 / 3, false);
				}
			}
		}
        return true;
    }
	
	private void addCircle(World world, Random random, int i, int j, int k, int l, boolean flag)
	{
		for(int width = -l; width < l; width++)
		{
			for(int height = -l; height < l; height++)
			{
				for(int depth = -l; depth < l; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
					if(i1 <= l && (flag || world.isAirBlock(i + width, j + height, k + depth)))
					{
						if(!flag)
						{
							func_150515_a(world, i + width, j + height, k + depth, AllDemDimensions.limestone);
						} else
						{
							Block j1 = world.getBlock(i + width, j + height + 1, k + depth);
							Block k1 = world.getBlock(i + width, j + height + 2, k + depth);
							Block l1 = world.getBlock(i + width, j + height + 3, k + depth);
							if(j1 == Blocks.air)//(height == l - 1)
							{
								func_150515_a(world, i + width, j + height, k + depth, Blocks.grass);
							} else
							if(j1 != Blocks.air && (k1 == Blocks.air || (l1 == Blocks.air && random.nextInt(2) == 0)))//(height == l - 2 || (height == l - 3 && random.nextInt(3) != 0) || (height == l - 4 && random.nextInt(3) == 0))
							{
								func_150515_a(world, i + width, j + height, k + depth, Blocks.dirt);
							}
						}
					}
				}
			}
		}
		if(!flag)
		{
			addCircle(world, random, i, j, k, l, true);
		}
	}
	
	private int sqr(int i)
	{
		return i * i;
	}
}
