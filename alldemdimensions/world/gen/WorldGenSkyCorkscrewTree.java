package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class WorldGenSkyCorkscrewTree extends WorldGenTreeBase
{

    public WorldGenSkyCorkscrewTree()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j - 1, k) != Blocks.grass)
		{
			return true;
		}
        int height = random.nextInt(12) + 8;
		for(int l = 0; l < height; l++)
		{
			setBlockAndNotifyAdequately(world, i, j + l, k, logBlock, logMeta);
		}
		int counter = 0;
		int offsetX = -1;
		int offsetZ = 0;
		while(counter < height)
		{
			setBlockAndNotifyAdequately(world, i + offsetX, j + counter, k + offsetZ, logBlock, logMeta);
			setBlockAndNotifyAdequately(world, i + offsetX, j + counter - 1, k + offsetZ, logBlock, logMeta);
			if(offsetX == -1)
			{
				offsetX = 0;
				offsetZ = -1;
			} else
			if(offsetX == 1)
			{
				offsetX = 0;
				offsetZ = 1;
			} else
			if(offsetZ == -1)
			{
				offsetX = 1;
				offsetZ = 0;
			} else
			if(offsetZ == 1)
			{
				offsetX = -1;
				offsetZ = 0;
			}
			counter++;
		}
		int k1 = 0;
		for(int i1 = -2; i1 < 3; i1++)
		{
			for(int j1 = -2; j1 < 3; j1++)
			{
				k1 = height - (Math.abs(i1) + Math.abs(j1)) + 1;
				if(Math.abs(i1) + Math.abs(j1) < 3)
				{
				for(int l1 = -(height / 4) - random.nextInt(3); l1 < 1; l1++)
				{
					if(!world.getBlock(i + i1, j + k1 + l1, k + j1).getMaterial().isSolid())
					{
						setBlockAndNotifyAdequately(world, i + i1, j + k1 + l1, k + j1, leavesBlock, leavesMeta);
					}
				}
				}
			}
		}
        return true;
    }
	
}
