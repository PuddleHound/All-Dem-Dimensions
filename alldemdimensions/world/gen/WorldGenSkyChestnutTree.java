package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;

public class WorldGenSkyChestnutTree extends WorldGenTreeBase
{

    public WorldGenSkyChestnutTree()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(!Dimension.zenith.isValidForWorldGen(world, i, j, k))
		{
			return true;
		}
		int height = random.nextInt(4) + 5;
		int counter = 0;
		while(counter < height - 1)
		{
			setBlockAndNotifyAdequately(world, i, j + counter, k, logBlock, logMeta);
			counter++;
		}
		setBlockAndNotifyAdequately(world, i, j + height, k, leavesBlock, leavesMeta);
		setBlockAndNotifyAdequately(world, i, j + height - 1, k, leavesBlock, leavesMeta);
		setBlockAndNotifyAdequately(world, i + 1, j + height - 4, k, leavesBlock, leavesMeta);
		setBlockAndNotifyAdequately(world, i - 1, j + height - 4, k, leavesBlock, leavesMeta);
		setBlockAndNotifyAdequately(world, i, j + height - 4, k + 1, leavesBlock, leavesMeta);
		setBlockAndNotifyAdequately(world, i, j + height - 4, k - 1, leavesBlock, leavesMeta);
		for(int x = -2; x <= 2; x++)
		{
			for(int z = -2; z <= 2; z++)
			{
				for(int y = height - 3; y < height; y++)
				{
					if(3 - (Math.abs(x) + Math.abs(z)) >= y - height + 3 && !world.getBlock(i + x, j + y, k + z).getMaterial().isSolid())
					{
						setBlockAndNotifyAdequately(world, i + x, j + y, k + z, leavesBlock, leavesMeta);
                                                if(y == height - 3 && random.nextInt(5) == 0)
                                                {
                                                    setBlockAndNotifyAdequately(world, i + x, j + y - 1, k + z, AllDemDimensions.skyFruit, AllDemDimensions.skyFruit.CHESTNUT);
                                                }
					}
				}
			}
		}
		return true;
	}
}
