package alldemdimensions.world.gen;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;

public class WorldGenNetherCrystal extends WorldGenerator
{
    
    public WorldGenNetherCrystal()
    {
    }
    
    public WorldGenNetherCrystal(int i)
    {
        blockMetadata = i;
    }
    
    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {      
        if(!world.getBlock(i, j - 1, k).getMaterial().isSolid() || !world.isAirBlock(i, j, k))
        {
            return true;
        }
        int meta = blockMetadata;
        if(random.nextInt(3) != 0)
        {
            if(blockMetadata == -1)
            {
                meta = random.nextInt(7);
            }
            if(AllDemDimensions.netherCrystal.canGenerateAtHeight(meta, j))
            {
                setBlockAndNotifyAdequately(world, i, j, k, AllDemDimensions.netherCrystal, meta);
            }
            return true;
        }
        byte counter = 0;
        int i1;
        int j1;
        int k1;
        while(counter < 64)
        {
            i1 = random.nextInt(8) - 4;
            j1 = random.nextInt(4) - 2;
            k1 = random.nextInt(8) - 4;
            if(blockMetadata == -1)
            {
                meta = random.nextInt(7);
            }
            if(AllDemDimensions.netherCrystal.canGenerateAtHeight(meta, j + j1) && world.getBlock(i + i1, j + j1 - 1, k + k1).getMaterial().isSolid() && world.isAirBlock(i + i1, j + j1, k + k1))
            {
                setBlockAndNotifyAdequately(world, i + i1, j + j1, k + k1, AllDemDimensions.netherCrystal, meta);
            }
            counter++;
        }
        
        return true;
    }
    
    public int blockMetadata = -1;
    
}
