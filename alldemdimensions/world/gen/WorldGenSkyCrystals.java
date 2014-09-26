package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;

public class WorldGenSkyCrystals extends WorldGenerator
{

    public WorldGenSkyCrystals(boolean flag)
    {
        cave = flag;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(cave && (world.getBlock(i, j, k) != AllDemDimensions.limestone || world.canBlockSeeTheSky(i, j, k)))
                {
                    return true;
                } else
		if(!cave && !Dimension.zenith.isValidForWorldGen(world, i, j, k))
		{
                    return true;
                }
		for(int width = -4; width < 4; width++)
		{
			for(int height = 0; height < 1; height++)
			{
				for(int depth = -4; depth < 4; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width,2) + Math.pow(depth,2)));
					if(i1 <= 4)
					{
						if(random.nextInt(2) == 0)
						{
							addColumn(world, random, i + width, j + height, k + depth);
						}
					}
				}
			}
		}
        return true;
    }
	
	private void addColumn(World world, Random random, int i, int j, int k)
	{
		for(int l = 0; l < 1/*random.nextInt(12) + 3*/; l++)
		{
			func_150515_a(world, i, j + l, k, AllDemDimensions.crystal);
		}
	}
        
        private boolean cave;
}
