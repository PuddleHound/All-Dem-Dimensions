package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenNetherRoots extends WorldGenerator
{

    public WorldGenNetherRoots()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j - 1, k) == Blocks.netherrack && world.isAirBlock(i, j, k))
		{
			while(random.nextInt(3) != 0)
			{
				addWood(world, random, i, j, k);
			}
		}
		return true;
	}
	
	private void addWood(World world, Random random, int i, int j, int k)
	{
		int l = 0;
		int i1 = 0;
		int j1 = 0;
		while(random.nextInt(10) != 0)
		{
			int k1 = l + random.nextInt(3) - 1;
			int l1 = i1 + random.nextInt(3) - 1;
			int i2 = j1 + random.nextInt(3) - 1;
			func_150515_a(world, i + k1, j + l1, k + i2, Blocks.log);
			if(random.nextInt(10) == 0)
			{
				addWood(world, random, i + k1, j + l1, k + i2);
			}
			l = k1;
			i1 = l1;
			j1 = i2;
		}
			
	}
}
