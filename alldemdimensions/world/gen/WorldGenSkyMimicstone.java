package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyMimicstone extends WorldGenerator
{

    public WorldGenSkyMimicstone()
    {
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
		if(world.getBlock(x, y, z) == AllDemDimensions.limestone)
		{
			int l = random.nextInt(4) + 2;
			for(int width = -l; width < l; width++)
			{
				for(int height = 0; height < 1; height++)
				{
					for(int depth = -l; depth < l; depth++)
					{
						int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width,2) + Math.pow(depth,2)));
						if(i1 <= 4)
						{
							if(world.getBlock(x + width, y + height, z + depth) == AllDemDimensions.limestone)
							{
								func_150515_a(world, x + width, y + height, z + depth, AllDemDimensions.mimicstone);
							}
						}
					}
				}
			}
		}
        return true;
    }
	
}
