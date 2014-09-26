package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.biome.BiomeGenSkyBase;

public class WorldGenSkyHugeTree extends WorldGenTreeBase
{

	public WorldGenSkyHugeTree()
    {
    	super();
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j - 1, k) != Blocks.grass &&
			world.getBlock(i, j - 1, k) != AllDemDimensions.waxGrass)
		{
			return true;
		}
        int height = random.nextInt(10) + 20;
		int counter = 0;
		int quarterHeight = 0;
		int thirdHeight = 0;
		int trunkRadius = 6;
		while(counter < height)
		{
			for(int l = 1 - trunkRadius; l < trunkRadius; l++)
			{
				for(int i1 = counter; i1 < counter + 1; i1++)
				{
					for(int j1 = 1 - trunkRadius; j1 < trunkRadius; j1++)
					{
						int k1 = (int)Math.round(Math.sqrt(Math.pow(l,2) + Math.pow(l,2) + Math.pow(j1,2)));
						if(k1 < trunkRadius)// && !world.getBlock(i + l, j + i1, k + j1).getMaterial().isSolid())
						{
							setBlockAndNotifyAdequately(world, i + l, j + i1, k + j1, logBlock, logMeta);
						}
					}
				}
			}
			if(counter % 3 == 0)
			{
				trunkRadius--;
			}
			
			quarterHeight = height / 4;
			thirdHeight = height / 3;
			int branches;
			/*if(counter > quarterHeight * 3)
			{
				for(branches = 0; branches < random.nextInt(2) + 2; branches++)
				{
					generateBranch(world, random, i, j + counter, k, VERTICAL, quarterHeight, false);
				}
			} else*/
			if(counter > thirdHeight * 2)
			{
				for(branches = 0; branches < random.nextInt(2) + 2; branches++)
				{
					generateBranch(world, random, i, j + counter, k, DIAGONAL, height / 3, false);
				}
			} else
			if(counter > thirdHeight)
			{
				for(branches = 0; branches < random.nextInt(2) + 2; branches++)
				{
					generateBranch(world, random, i, j + counter, k, HORIZONTAL, height / 3, false);
				}
			}
			counter++;
		}
        return true;
    }

	private void generateBranch(World world, Random random, int i, int j, int k, byte type, int distance, boolean secondary)
	{
		int dirX = random.nextInt(3) - 1;
		int dirZ = random.nextInt(3) - 1;
		boolean offset = false;
		int counter = 0;
		while(counter < distance)
		{
			if(type == HORIZONTAL)
			{
				i += dirX;
				k += dirZ;
				if(counter > (distance / 2) && !offset)
				{
					j++;
					offset = true;
				}
			} else
			if(type == VERTICAL)
			{
				j++;
				if(counter > (distance / 2) && !offset)
				{
					i += dirX;
					k += dirZ;
					offset = true;
				}
			} else
			if(type == DIAGONAL)
			{
				if(counter > (distance / 2))
				{
					j++;
				} else
				{
					i += dirX;
					k += dirZ;
				}
			}
			setBlockAndNotifyAdequately(world, i, j, k, logBlock, logMeta);
			counter++;
		}
		generateLeaves(world, random, i, j, k);
		if(!secondary)
		{
			for(int branches = 0; branches < random.nextInt(2) + 2; branches++)
			{
				generateBranch(world, random, i, j, k, HORIZONTAL, distance / 2, true);
			}
		}
		if(random.nextInt(1000) == 0 || (random.nextInt(200) == 0 &&
			world.getWorldChunkManager().getBiomeGenAt(i, k) == BiomeGenSkyBase.skyFlowerBiome))
		{
			setBlockAndNotifyAdequately(world, i, j - 1, k, AllDemDimensions.beehive, AllDemDimensions.beehive.WAX);
			(new WorldGenSkyBeehive(true)).generate(world, random, i, j - 7, k);
		}
	}
	
	private void generateLeaves(World world, Random random, int i, int j, int k)
	{
		for(int l = -2; l < 3; l++)
		{
			for(int i1 = -2; i1 < 3; i1++)
			{
				for(int j1 = -2; j1 < 3; j1++)
				{
					int k1 = (int)Math.round(Math.sqrt(Math.pow(l,2) + Math.pow(i1,2) + Math.pow(j1,2)));
					if(k1 < 3 && !world.getBlock(i + l, j + i1, k + j1).getMaterial().isSolid())
					{
						setBlockAndNotifyAdequately(world, i + l, j + i1, k + j1, leavesBlock, leavesMeta);
					}
				}
			}
		}
	}
	
	private static final byte HORIZONTAL = 0;
	private static final byte DIAGONAL = 1;
	private static final byte VERTICAL = 2;
	
}
