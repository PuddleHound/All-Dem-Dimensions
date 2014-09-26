package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyHoneysuckle extends WorldGenerator
{

    public WorldGenSkyHoneysuckle(boolean flag)
    {
        cave = flag;
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		for(int count = 0; count < 8; count++)
        {
            int x = i + random.nextInt(8) - random.nextInt(8);
            int y = j + random.nextInt(8) - random.nextInt(8);
            int z = k + random.nextInt(8) - random.nextInt(8);
            if(cave)
            {
                if(world.canBlockSeeTheSky(x, y, z))
                {
                    continue;
                }
                int yLevel = y;
                while(yLevel >= 0)
                {
                    if(world.getBlock(x, yLevel, z).getMaterial().isSolid())
                    {
                        break;
                    }
                    yLevel--;
                }
                if(yLevel == 0)
                {
                    continue;
                }
            }
            
			if(world.getBlock(x, y, z).getMaterial().isSolid()) 
			{
				if(world.isAirBlock(x - 1, y, z) && random.nextInt(2) == 0)
				{
					addVine(world, random, x - 1, y, z, 8);
				}
				if(world.isAirBlock(x + 1, y, z) && random.nextInt(2) == 0)
				{
					addVine(world, random, x + 1, y, z, 2);
				}
				if(world.isAirBlock(x, y, z - 1) && random.nextInt(2) == 0)
				{
					addVine(world, random, x, y, z - 1, 1);
				}
				if(world.isAirBlock(x, y, z + 1) && random.nextInt(2) == 0)
				{
					addVine(world, random, x, y, z + 1, 4);
				}
			}
			
		}
		return true;
    }
	
	private void addVine(World world, Random random, int i, int j, int k, int metadata)
	{
		int counter = 0;
		while(world.isAirBlock(i, j - counter, k) && counter < random.nextInt(8) + 4)
		{
			setBlockAndNotifyAdequately(world, i, j - counter, k, AllDemDimensions.honeysuckle, metadata);
			counter++;
		}
	}
        
        private boolean cave;
}
