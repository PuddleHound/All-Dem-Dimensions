package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyIcicles extends WorldGenerator
{

    public WorldGenSkyIcicles()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(world.getBlock(i + 3, j + 1, k + 3) == AllDemDimensions.limestone && world.isAirBlock(i + 3, j, k + 3) &&
			world.getBlock(i - 3, j + 1, k - 3) == AllDemDimensions.limestone)
		
		{
			for(int l = -4; l < 4; l++)
			{
				for(int i1 = -4; i1 < 4; i1++)
				{
					int k1 = l * i1;
					int l1 = 12 - (k1 / 2);
					for(int j1 = 0; j1 < random.nextInt(6) + k1; j1++)
					{
						func_150515_a(world, i + l, j - j1, k + i1, Blocks.ice);
					}
				}
			}
		}
        return true;
    }
}
