package alldemdimensions.world.biome;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiome;
import net.minecraft.world.gen.layer.IntCache;
import alldemdimensions.world.Dimension;

public class GenLayerMajorBiome extends GenLayerBiome
{
	
    public GenLayerMajorBiome(long seed, GenLayer genlayer, WorldType worldtype, Dimension dim)
    {
        super(seed, genlayer, worldtype);
        dimension = dim;
    }

    public int[] getInts(int i, int j, int k, int l)
    {
        int[] aint = this.parent.getInts(i, j, k, l);
        int[] aint1 = IntCache.getIntCache(k * l);

        for (int i1 = 0; i1 < l; ++i1)
        {
            for (int j1 = 0; j1 < k; ++j1)
            {
                this.initChunkSeed((long)(j1 + i), (long)(i1 + j));
                int k1 = aint[j1 + i1 * k];
                int l1 = (k1 & 3840) >> 8;
                k1 &= -3841;
                aint1[j1 + i1 * k] = dimension.biomeGenerator.getMainBiome(this);
            }
        }

        return aint1;
    }
    
    @Override
    public int nextInt(int i)
    {
    	return super.nextInt(i);
    }
    
    private Dimension dimension;
}