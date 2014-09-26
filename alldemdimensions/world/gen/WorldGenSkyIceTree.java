package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.world.Dimension;

public class WorldGenSkyIceTree extends WorldGenTreeBase
{

	public WorldGenSkyIceTree()
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
		int height = random.nextInt(10) + 10;
		for(int l = 0; l < height; l++)
		{
			setBlockAndNotifyAdequately(world, i, j + l, k, logBlock, logMeta);
		}
		int i1 = (height / 2) + random.nextInt(height / 6) - random.nextInt(height / 3);
		boolean flag = false;
		//flag = smaller diameter
		while(i1 <= height + 1)
		{
			if(i1 % 2 != 0)
			{
				flag = true;
			} else
			{
				flag = false;
			}
			addCircle(world, random, i, j + i1, k, flag);
			i1++;
		}
		if(!flag)
		{
			addCircle(world, random, i, j + height + 2, k, true);
			setBlockAndNotifyAdequately(world, i, j + height + 3, k, leavesBlock, leavesMeta);
			setBlockAndNotifyAdequately(world, i, j + height + 3, k - 1, leavesBlock, leavesMeta);
		} else
		{
			setBlockAndNotifyAdequately(world, i, j + height + 2, k, leavesBlock, leavesMeta);
			setBlockAndNotifyAdequately(world, i, j + height + 2, k - 1, leavesBlock, leavesMeta);
		}
		return true;
	}
	
	private void addCircle(World world, Random random, int i, int j, int k, boolean flag)
	{
		int l = 3;
		if(flag)
		{
			l = 2;
		}
		for(int width = -l; width < l; width++)
		{
			for(int height = 0; height < 1; height++)
			{
				for(int depth = -l; depth < l; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width,2) + Math.pow(depth,2)));
					if(i1 <= l && world.isAirBlock(i+width, j+height, k+depth))
					{
						setBlockAndNotifyAdequately(world, i+width, j+height, k+depth, leavesBlock, leavesMeta);
					}
				}
			}
		}
	}
}
