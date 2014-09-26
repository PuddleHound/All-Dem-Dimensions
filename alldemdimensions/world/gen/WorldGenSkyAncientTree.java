package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.Plant;
import alldemdimensions.world.Dimension;

public class WorldGenSkyAncientTree extends WorldGenTreeBase
{

    public WorldGenSkyAncientTree()
    {
    	super();
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		//if(world.getBlock(i, j - 1, k) != Blocks.grass)
		
    	if(!Dimension.zenith.isValidForWorldGen(world, i, j, k))
                {
			return true;
		}
        int height = random.nextInt(14) + 14;
		int counter = 0;
		int k1;
		int i2 = random.nextInt(2);
		for(int i1 = -3 - i2; i1 < 4 + i2; i1++)
		{
			for(int j1 = -3 - i2; j1 < 4 + i2; j1++)
			{
				k1 = 4 - (Math.abs(i1) + Math.abs(j1));
				if(Math.abs(i1) + Math.abs(j1) < 4 + i2)
				{
					//for(int l1 = -(height / 4) - random.nextInt(3); l1 < 1; l1++)
					for(int l1 = -4; l1 < 1; l1++)
					{
						/*if(random.nextInt(12) == 0)
						{
							setBlockAndNotifyAdequately(world, i + i1, j + k1 + l1, k + j1, AllDemDimensions.skyLog2, Tree.ANCIENT_AMBER);
						} else*/
						{
							setBlockAndNotifyAdequately(world, i + i1, j + k1 + l1, k + j1, logBlock, logMeta);
						}
					}
				}
			}
		}
		while(counter < height)
		{
			for(int trunkX = -1; trunkX < 2; trunkX++)
			{
				for(int trunkZ = -1; trunkZ < 2; trunkZ++)
				{
					if(Math.abs(trunkX) + Math.abs(trunkZ) < 2)
					{
						/*if(random.nextInt(12) == 0)
						{
							setBlockAndNotifyAdequately(world, i + trunkX, j + counter, k + trunkZ, AllDemDimensions.skyLog2, Tree.ANCIENT_AMBER);
						} else*/
						{
							setBlockAndNotifyAdequately(world, i + trunkX, j + counter, k + trunkZ, logBlock, logMeta);
						}
					}
				}
			}
			if(counter > height / 4)
			{
				for(int branches = 0; branches < random.nextInt(3) + 2; branches++)
				{
					generateBranch(world, random, i, j + counter, k, (height - counter) / 3);
				}
			}
			counter++;
		}
        return true;
    }
	
	private void generateBranch(World world, Random random, int i, int j, int k, int distance)
	{
		int dirX = random.nextInt(3) - 1;
		int dirZ = random.nextInt(3) - 1;
		int counter = 0;
		while(counter < distance)
		{
			i += dirX;
			k += dirZ;
			setBlockAndNotifyAdequately(world, i, j, k, logBlock, logMeta);
			counter++;
		}
		counter = 0;
		i -= (distance / 3) * dirX;
		j += 1;
		k -= (distance / 3) * dirZ;
		while(counter < distance / 2)
		{
			i += dirX;
			k += dirZ;
			setBlockAndNotifyAdequately(world, i, j, k, logBlock, logMeta);
			counter++;
		}
		generateLeaves(world, random, i, j, k);
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
						if(random.nextInt(5) == 0)
						{
							int counter = 0;
							int metadata = Plant.zenith_hangingMoss.plantMeta;
							int yOffset = -1;
							while(counter < random.nextInt(6) + 3 && !world.getBlock(i + l, j + i1 + yOffset, k + j1).getMaterial().isSolid())
							{
								setBlockAndNotifyAdequately(world, i + l, j + i1 + yOffset, k + j1, Plant.zenith_hangingMoss.plantBlock, metadata);
								yOffset--;
								counter++;
							}
						}
					}
				}
			}
		}
	}
	
}
