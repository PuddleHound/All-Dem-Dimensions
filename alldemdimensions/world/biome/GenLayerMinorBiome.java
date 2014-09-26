package alldemdimensions.world.biome;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerHills;
import net.minecraft.world.gen.layer.IntCache;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import alldemdimensions.world.Dimension;

public class GenLayerMinorBiome extends GenLayerHills
{

    public GenLayerMinorBiome(long seed, GenLayer genlayer, GenLayer genlayer1, Dimension dim)
    {
        super(seed, genlayer, genlayer1);
        dimension = dim;
    }

    public int[] getInts(int f, int f1, int f2, int f3)
    {
        int[] aint = this.parent.getInts(f - 1, f1 - 1, f2 + 2, f3 + 2);
        int[] aint2 = IntCache.getIntCache(f2 * f3);

        for (int i1 = 0; i1 < f3; ++i1)
        {
            for (int j1 = 0; j1 < f2; ++j1)
            {
                this.initChunkSeed((long)(j1 + f), (long)(i1 + f1));
                int k1 = aint[j1 + 1 + (i1 + 1) * (f2 + 2)];                
                aint2[j1 + i1 * f2] = dimension.biomeGenerator.getSubBiome(this, k1);
            }
        }

        return aint2;
    }
    
    @Override
    public int nextInt(int i)
    {
    	return super.nextInt(i);
    }
    
    private Dimension dimension;
}