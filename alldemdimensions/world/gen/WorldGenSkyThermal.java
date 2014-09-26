package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenSkyThermal extends WorldGenerator
{

    public WorldGenSkyThermal()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.isAirBlock(i, j, k))
		{
			int l = random.nextInt(2) + 1;
			int i1 = 0;
			while(random.nextInt(16) != 0)
			{
				i1++;
				if(j + i1 > 127)
				{
					break;
				}
				if(random.nextInt(4) == 0)
				{
					l++;
				}
				addCircle(world, random, i, j + i1, k, l);
			}
		}
		return true;
	}
	
	private void addCircle(World world, Random random, int i, int j, int k, int l)
	{
		if(l == 0)
		{
			return;
		}
		for(int width = -l; width < l; width++)
		{
			for(int height = 0; height < 1; height++)
			{
				for(int depth = -l; depth < l; depth++)
				{
					int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width,2) + Math.pow(depth,2)));
					if(i1 <= 4 && world.isAirBlock(i+width, j+height, k+depth))
					{
						func_150515_a(world, i+width, j+height, k+depth, AllDemDimensions.thermal);
					}
				}
			}
		}
	}
}
