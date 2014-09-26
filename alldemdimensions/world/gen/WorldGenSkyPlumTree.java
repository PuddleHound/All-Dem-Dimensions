package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;

public class WorldGenSkyPlumTree extends WorldGenTreeBase
{

	public WorldGenSkyPlumTree()
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
        int height = random.nextInt(5) + 5;
		for(int l = 0; l < height; l++)
		{
			setBlockAndNotifyAdequately(world, i, j + l, k, logBlock, logMeta);
		}
		int i1 = height / 2;
		while(i1 <= height)
		{
			addBranch(world, random, i, j + i1, k);
			addBranch(world, random, i, j + i1, k);
			i1++;
		}
        return true;
    }
	
	private void addBranch(World world, Random random, int i, int j, int k)
	{
		int[] ai = new int[] {0, 1, 0, -1, 1, 1, -1, -1};
		int[] aj = new int[] {1, 0, -1, 0, -1, 1, 1, -1};
		int l = random.nextInt(8);
		int length = random.nextInt(3) + 3;
		int i1 = 0;
		int count = 0;
		int j2 = 0;
		int k2 = 0;
		int l2 = 0;
		while(count < length)
		{
			j2 = i + (ai[l] * i1);
			k2 = j + i1;
			l2 = k + (aj[l] * i1);
			setBlockAndNotifyAdequately(world, j2, k2, l2, logBlock, logMeta);
			//world.setBlockWithNotify(i + ai[l], j + i1, k + aj[l], Blocks.wood);
			for(int j1 = -1; j1 < 2; j1++)
			{
				for(int k1 = -1; k1 < 3; k1++)
				{
					for(int l1 = -1; l1 < 2; l1++)
					{
						if(random.nextInt(2) == 0 && !world.getBlock(j2 + j1, k2 + k1, l2 + l1).getMaterial().isSolid())
						{
							int metadata = 0;
							if(random.nextInt(2) == 0)
							{
								metadata = 1;
							}
							setBlockAndNotifyAdequately(world, j2 + j1, k2 + k1, l2 + l1, leavesBlock, leavesMeta);
							if(k1 == -1 && random.nextInt(4) == 0 && !world.getBlock(j2 + j1, k2 + k1 - 1, l2 + l1).getMaterial().isSolid())
							{
								setBlockAndNotifyAdequately(world, j2 + j1, k2 + k1 - 1, l2 + l1, AllDemDimensions.skyFruit, AllDemDimensions.skyFruit.SUGARPLUM);
							}
						}
					}
				}
			}
			if(random.nextInt(3) != 0)
			{
				i1++;
			}
			count++;
		}
	}
}
