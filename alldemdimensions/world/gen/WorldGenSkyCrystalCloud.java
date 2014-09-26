package alldemdimensions.world.gen; 

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSkyCrystalCloud extends WorldGenerator
{   
    
    public WorldGenSkyCrystalCloud(Block i, int j)
    {
        blockID = i;
        metadata = j;
    }
    
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
        if(!world.isAirBlock(i, j, k))
        {
            return true;
        }
        //BiomeGenSkyBase biome = (BiomeGenSkyBase)world.getWorldChunkManager().getBiomeGenAt(i, k);
        //if((biome.canCloudGenerate(j, metadata) || blockID != AllDemDimensions.cloud) && world.isAirBlock(i, j, k))
        {
            int radius = random.nextInt(12) + 6;
            int x = 0, z = 0;
            if(random.nextBoolean())
            {
                x = radius;
            } else
            {
                z = radius;
            }
            if(random.nextBoolean())
            {
                x *= -1;
                z *= -1;
            }
            addCircle(world, random, i, j, k, radius, x, z, random.nextInt(4) == 0 ? 1 : 0);
        }
        return true;
    }
	
	private void addCircle(World world, Random random, int i, int j, int k, int l, int originX, int originZ, int type)
	{
		if(l < 2)
		{
			return;
		}
                int y = 0;
                for(int layer = -1; layer < l / 2; layer++)
                {
                    if(layer == -1)
                    {
                        y = 1;
                    } else
                    {
                        y = layer;
                    }
                    for(int width = -l + y; width < l - y; width++)
                    {
                            //for(int height = -l / 4; height < l / 4; height++)
                            {
                                    for(int depth = -l + y; depth < l - y; depth++)
                                    {
                                        int i1 = (int)Math.round(Math.sqrt(Math.pow(width,2) + Math.pow(width/*height*/,2) + Math.pow(depth,2)));
                                        //if(i1 <= (l * 0.75F) && world.isAirBlock(i+width, j+height, k+depth))
                                        if(i1 <= l - y && world.isAirBlock(i+width, j-layer/*+height*/, k+depth))
                                        {
                                            setBlockAndNotifyAdequately(world, i+width, j-layer/*+height*/, k+depth, blockID, metadata);
                                        }
                                    }
                            }
                    }
                }
                /*
                if(originX == 100)
                {
                    return;
                }
                addCircle(world, random, i, j + 1, k, (l * 3) / 4, 100, 100);
                addCircle(world, random, i, j - 1, k, (l * 3) / 4, 100, 100);
                 */
                if(type == 0)
                {
                    addCircle(world, random, i + (originX != 0 ? (originX * 2 / 3) : (originZ / 3)), j - (l / 2), k + (originZ != 0 ? (originZ * 2 / 3) : (originX / 3)), l * 3 / 4, originX, originZ, type);
                    return;
                }
                if(originX == 0 && originZ == 0)
                {
                    addCircle(world, random, i + l, j, k, l / 2, -1, 0, type);
                    addCircle(world, random, i - l, j, k, l / 2, 1, 0, type);
                    addCircle(world, random, i, j, k + l, l / 2, 0, -1, type);
                    addCircle(world, random, i, j, k - l, l / 2, 0, 1, type);
                } else
                {
                    if(originX == 0)
                    {
                        addCircle(world, random, i - l, j, k + (l * -originZ), l / 2, 1, originZ, type);
                        addCircle(world, random, i + l, j, k + (l * -originZ), l / 2, -1, originZ, type);
                    } else
                    if(originZ == 0)
                    {
                        addCircle(world, random, i + (l * -originX), j, k - l, l / 2, originX, 1, type);
                        addCircle(world, random, i + (l * -originX), j, k + l, l / 2, originX, -1, type);
                    } else
                    {
                        addCircle(world, random, i + (l * -originX), j, k, l / 2, originX, 0, type);
                        addCircle(world, random, i, j, k + (l * -originZ), l / 2, 0, originZ, type);
                    }
                }
	}
        
        
        public Block blockID;
        public int metadata;
}