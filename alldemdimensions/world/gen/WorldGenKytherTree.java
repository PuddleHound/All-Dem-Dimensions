package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenKytherTree extends WorldGenerator
{

    public WorldGenKytherTree()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j - 1, k) != Blocks.grass)
		{
			return false;
		}
		for(int l = 0; l < 20; l++)
		{
			for(int j1 = -1; j1 < 1; j1++)
			{
				for(int k1 = -1; k1 < 1; k1++)
				{
					func_150515_a(world, i + j1, j + l, k + k1, Blocks.log);
				}
			}
		}
		for(int j1 = -2; j1 < 2; j1++)
		{
			for(int k1 = -2; k1 < 2; k1++)
			{
				if(world.isAirBlock(i + j1, j, k + k1))
				{
					func_150515_a(world, i + j1, j, k + k1, Blocks.log);
					int l1 = 0;
					int i2 = random.nextInt(3);
					while(l1 < i2)
					{
						func_150515_a(world, i + j1, j + l1, k + k1, Blocks.log);
						l1++;
					}
				}
			}
		}
		int i1 = 0;
		while(i1 < random.nextInt(6) + 10)
		{
			addCircle(world, random, i + random.nextInt(5) - random.nextInt(5), j + 12 + random.nextInt(15), k + random.nextInt(5) - random.nextInt(5), random.nextInt(6) + 4);
			i1++;
		}
		return true;
	}
	
	public void addCircle(World world, Random random, int i, int j, int k, int i1)
	{
		func_150515_a(world, i, j, k, Blocks.log);
		for(int width = -i1; width < i1; width++)
		{
			for(int height = -i1; height < i1; height++)
			{
				for(int depth = -i1; depth < i1; depth++)
				{
					int l1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(height,2) + Math.pow(depth,2)));
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
	}
}
