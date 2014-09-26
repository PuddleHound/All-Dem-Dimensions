package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyJungleTree extends WorldGenTreeBase
{

	public WorldGenSkyJungleTree()
    {
    	super();
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j - 1, k) != Blocks.grass)
		{
			return false;
		}
		int height = random.nextInt(16) + 16;
		int counter = 0;
		while(counter < height)
		{
			for(int i1 = -((height - counter) / 8) - 1; i1 <= ((height - counter) / 8) + 1; i1++)
			{
				for(int j1 = -((height - counter) / 8) - 1; j1 <= ((height - counter) / 8) + 1; j1++)
				{
					if(Math.abs(i1) + Math.abs(j1) > ((height - counter) / 8) + 1)
					{
					} else
					{
						setBlockAndNotifyAdequately(world, i + i1, j + counter, k + j1, logBlock, logMeta);
					}
				}
			}
			if(counter > (height / 3) * 2)//4 3
			{
				for(int l = 0; l < random.nextInt(12); l++)
				{
					addBranch(world, random, i, j + counter, k, counter / 8, 0, random.nextInt(4));
				}
			}
			counter++;
		}
		return true;
	}
	
	private void addBranch(World world, Random random, int i, int j, int k, int branchChance, int branchCount, int direction)
	{
		int offsetX = 0;
		int offsetZ = 0;
		if(direction == 0)
		{
			offsetX = 1;
			offsetZ = 0;
		} else
		if(direction == 1)
		{
			offsetX = 0;
			offsetZ = 1;
		} else
		if(direction == 2)
		{
			offsetX = -1;
			offsetZ = 0;
		} else
		if(direction == 3)
		{
			offsetX = 0;
			offsetZ = -1;
		}
		setBlockAndNotifyAdequately(world, i + offsetX, j, k + offsetZ, logBlock, logMeta);
		setBlockAndNotifyAdequately(world, i + (offsetX * 2), j, k + (offsetZ * 2), logBlock, logMeta);
		setBlockAndNotifyAdequately(world, i + (offsetX * 3), j + 1, k + (offsetZ * 3), logBlock, logMeta);
		setBlockAndNotifyAdequately(world, i + (offsetX * 3), j + 2, k + (offsetZ * 3), logBlock, logMeta);
		branchCount++;
		boolean branchAdded = false;
		if(branchCount < 6)
		{
			for(int l = 0; l < 8; l++)
			{
				if(random.nextInt(branchChance) == 0)
				{
					addBranch(world, random, i + (offsetX * 4), j + 3, k + (offsetZ * 4), branchChance * 2, branchCount, random.nextInt(4));
					branchAdded = true;
				}
			}
		}
		//if(!branchAdded)
		{
			for(int i1 = -1; i1 <= 1; i1++)
			{
				for(int j1 = -1; j1 <= 1; j1++)
				{
					if(Math.abs(i1) == 1 && Math.abs(j1) == 1)
					{
					} else
					{
						int limit =  -random.nextInt(4) - 1;
						for(int k1 = 0; k1 > limit; k1--)
						{
							if(!world.getBlock(i + (offsetX * 3) + i1, j + 3 + k1, k + (offsetZ * 3) + j1).getMaterial().isSolid())
							{
								setBlockAndNotifyAdequately(world, i + (offsetX * 3) + i1, j + 3 + k1, k + (offsetZ * 3) + j1, leavesBlock, leavesMeta);
								if(k1 == limit + 1 && random.nextInt(8) == 0)
								{
									setBlockAndNotifyAdequately(world, i + (offsetX * 3) + i1, j + 3 + k1 - 1, k + (offsetZ * 3) + j1, AllDemDimensions.skyFruit, AllDemDimensions.skyFruit.POD_FRUIT);
								}
							}
						}
					}
				}
			}
			setBlockAndNotifyAdequately(world, i + (offsetX * 3), j + 3, k + (offsetZ * 3), leavesBlock, leavesMeta);
			/*setBlockAndNotifyAdequately(world, i + (offsetX * 3) + 1, j + 3, k + (offsetZ * 3), AllDemDimensions.skyLeaves1, TreeList.JUNGLE);
			setBlockAndNotifyAdequately(world, i + (offsetX * 3) - 1, j + 3, k + (offsetZ * 3), AllDemDimensions.skyLeaves1, TreeList.JUNGLE);
			setBlockAndNotifyAdequately(world, i + (offsetX * 3), j + 3, k + (offsetZ * 3) + 1, AllDemDimensions.skyLeaves1, TreeList.JUNGLE);
			setBlockAndNotifyAdequately(world, i + (offsetX * 3), j + 3, k + (offsetZ * 3) - 1, AllDemDimensions.skyLeaves1, TreeList.JUNGLE);*/
			setBlockAndNotifyAdequately(world, i + (offsetX * 3), j + 4, k + (offsetZ * 3), leavesBlock, leavesMeta);
		}
	}
	
}