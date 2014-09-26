package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.world.Dimension;

public class WorldGenSkyWillow extends WorldGenTreeBase
{

	public WorldGenSkyWillow()
    {
    	super();
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(!Dimension.zenith.isValidForWorldGen(world, i, j, k))
		{
			return false;
		}
		int height = random.nextInt(15) + 15;
		for(int l = 0; l < height; l++)
		{
			for(int j1 = -1; j1 < 1; j1++)
			{
				for(int k1 = -1; k1 < 1; k1++)
				{
					setBlockAndNotifyAdequately(world, i + j1, j + l, k + k1, logBlock, logMeta);
				}
			}
		}
		for(int j1 = -2; j1 < 2; j1++)
		{
			for(int k1 = -2; k1 < 2; k1++)
			{
				if(world.isAirBlock(i + j1, j, k + k1))
				{
					setBlockAndNotifyAdequately(world, i + j1, j, k + k1, logBlock, logMeta);
					int l1 = 0;
					int i2 = random.nextInt(3);
					while(l1 < i2)
					{
						setBlockAndNotifyAdequately(world, i + j1, j + l1, k + k1, logBlock, logMeta);
						l1++;
					}
				}
			}
		}
		int i1 = 0;
		int thirdHeight = height / 3;
		while(thirdHeight + i1 < (height * 3) / 4)
		{
			//addCircle(world, random, i + random.nextInt(5) - random.nextInt(5), j + 12 + random.nextInt(15), k + random.nextInt(5) - random.nextInt(5), random.nextInt(6) + 4);
			if(random.nextInt(2) == 0)
			{
				addBranch(world, random, i, j + thirdHeight + i1, k);
			}
			i1++;
		}
		for(int i3 = -2; i3 < 3; i3++)
		{
			for(int j3 = -2; j3 < 3; j3++)
			{
				if(!(i3 == 2 && j3 == 2) && !(i3 == 2 && j3 == -2) && !(i3 == -2 && j3 == 2) && !(i3 == -2 && j3 == -2))
				addLeaves(world, random, i + i3, j + height, k + j3, random.nextInt(4) + 6);
			}
		}
		for(int i3 = -1; i3 < 2; i3++)
		{
			for(int j3 = -1; j3 < 2; j3++)
			{
				setBlockAndNotifyAdequately(world, i + i3, j + height + 1, k + j3, leavesBlock, leavesMeta);
			}
		}
		//addLeaves(world, random, i, j + height, k, 8);
		//addLeaves(world, random, i, j + height + 1, k, 6);
		return true;
	}
	
	private void addBranch(World world, Random random, int i, int j, int k)
	{
		int[] ai = new int[] {0, 1, 0, -1, 1, 1, -1, -1};
		int[] aj = new int[] {1, 0, -1, 0, -1, 1, 1, -1};
		int i1 = random.nextInt(8);
		int j1 = 1;
		int l1 = j1;
		int k1 = random.nextInt(3) + 5;
		int i2 = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		while(j1 < k1)
		{
			j2 = i + (ai[i1] * j1);
			k2 = j + l1;
			l2 = k + (aj[i1] * j1);
			setBlockAndNotifyAdequately(world, j2, k2, l2, logBlock, logMeta);
			if(j1 <= k1 / 3)
			{
				if(i2 % 2 == 0)
				{
					l1++;
				}
				j1++;
			} else
			if(j1 >= (k1 * 2) / 3)
			{
				if(i2 % 2 == 0)
				{
					j1++;
				}
				l1++;
			} else
			{
				j1++;
				l1++;
			}
			i2++;
		}
		setBlockAndNotifyAdequately(world, j2, k2 + 1, l2, leavesBlock, leavesMeta);
		/*for(int i3 = -1; i3 < 2; i3++)
		{
			for(int j3 = -1; j3 < 2; j3++)
			{
				addLeaves(world, random, j2 + i3, k2, l2 + j3, random.nextInt(3) + 5);
			}
		}*/
		addLeaves(world, random, j2 + 1, k2, l2, random.nextInt(3) + 5);
		addLeaves(world, random, j2 - 1, k2, l2, random.nextInt(3) + 5);
		addLeaves(world, random, j2, k2, l2 + 1, random.nextInt(3) + 5);
		addLeaves(world, random, j2, k2, l2 - 1, random.nextInt(3) + 5);
	}
	
	/*private void addLeaves(World world, Random random, int i, int j, int k, int i1)
	{
	
		for(int width = -i1; width < i1; width++)
		{
			for(int height = 0; height < 1; height++)
			{
				for(int depth = -i1; depth < i1; depth++)
				{
					int l1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width,2) + Math.pow(depth,2)));
					int i2 = i1 / 2;
					if(l1 <= i2)
					{
						if(world.isAirBlock(i + width, j + height, k + depth))
						{
							func_150515_a(world, i + width, j + height, k + depth, Blocks.leaves);
						}
					}
				}
			}
		}
	}*/
	
	private void addLeaves(World world, Random random, int i, int j, int k, int l)
	{
		for(int width = 0; width < 1; width++)
		{
			for(int height = -l; height < 1; height++)
			{
				for(int depth = 0; depth < 1; depth++)
				{
					if(!world.getBlock(i + width, j + height, k + depth).getMaterial().isSolid())
					{
						setBlockAndNotifyAdequately(world, i + width, j + height, k + depth, leavesBlock, leavesMeta);
					}
				}
			}
		}
	}
}
