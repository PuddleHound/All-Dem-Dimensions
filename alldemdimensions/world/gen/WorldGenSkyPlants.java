package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.Plant;

public class WorldGenSkyPlants extends WorldGenerator
{
    
    public WorldGenSkyPlants(Block i, int j)
    {
		this(i, j, 1);
        //plantId = i;
        //plantMeta = j;
    }
	
	public WorldGenSkyPlants(Block i, int j, int k)
	{
            this(i, j, null, -1, k, false);
	}
        
        public WorldGenSkyPlants(Block i, int j, Block k, int l, int i1, boolean flag)
        {
            plantId = i;
            plantMeta = j;
            idBelow = k;
            metaBelow = l;
            maxHeight = i1;
            underground = flag;
        }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(!underground)
        {
            Block block = null;
            do 
            {
                block = world.getBlock(i, j, k);
                if (block != null && !block.isLeaves(world, i, j, k))
                {
                    break;
                }
                j--;
            } while (j > 0);
        }

		int count = 16;
		if((plantId == Plant.zenith_flowerGrass.plantBlock && plantMeta == Plant.zenith_flowerGrass.plantMeta) || (plantId == Plant.zenith_bamboo.plantBlock && plantMeta == Plant.zenith_bamboo.plantMeta))
		{
			count = 128;
		}
        for (int count1 = 0; count1 < count; count1++)
        {
            int x = i + random.nextInt(8) - random.nextInt(8);
            int y = j + random.nextInt(4) - random.nextInt(4);
            int z = k + random.nextInt(8) - random.nextInt(8);

            Block block = plantId;
            boolean canStay = block != null && block.canBlockStay(world, x, y, z);
            if (world.isAirBlock(x, y, z) && canStay && (idBelow == null || world.getBlock(x, y - 1, z) == idBelow) && (metaBelow == -1 || world.getBlockMetadata(x, y - 1, z) == metaBelow))
            {
				if(maxHeight <= 1)
				{
					setBlockAndNotifyAdequately(world, x, y, z, plantId, plantMeta);
				} else
				{
					int count2 = 0;
                                        int height = random.nextInt(maxHeight);
					while(count2 < height)
					{
						if(!world.isAirBlock(x, y + count2, z))
						{
							break;
						}
						setBlockAndNotifyAdequately(world, x, y + count2, z, plantId, plantMeta);
						count2++;
					}
				}
			}
        }
        return true;
    }
    
    private Block plantId;
    private int plantMeta;
	private int maxHeight;
    private Block idBelow = null;
    private int metaBelow = -1;
    private boolean underground;

    
}
