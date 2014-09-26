package alldemdimensions.world.biome;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerBiomeEdge;
import net.minecraft.world.gen.layer.IntCache;
import alldemdimensions.world.Dimension;

public class GenLayerBorderBiome extends GenLayerBiomeEdge
{
	
    public GenLayerBorderBiome(long seed, GenLayer genlayer, Dimension dim)
    {
        super(seed, genlayer);
        dimension = dim;
    }

    @Override
    public int[] getInts(int i, int j, int k, int l)
    {
        int[] aint = this.parent.getInts(i - 1, j - 1, k + 2, l + 2);
        int[] aint1 = IntCache.getIntCache(k * l);

        for (int i1 = 0; i1 < l; ++i1)
        {
            for (int j1 = 0; j1 < k; ++j1)
            {
                this.initChunkSeed((long)(j1 + i), (long)(i1 + j));
                int k1 = aint[j1 + 1 + (i1 + 1) * (k + 2)];
                
                int l1 = aint[j1 + 1 + (i1 + 1 - 1) * (k + 2)];
                int i2 = aint[j1 + 1 + 1 + (i1 + 1) * (k + 2)];
                int j2 = aint[j1 + 1 - 1 + (i1 + 1) * (k + 2)];
                int k2 = aint[j1 + 1 + (i1 + 1 + 1) * (k + 2)];
                aint1[j1 + i1 * k] = dimension.biomeGenerator.getEdgeBiome(this, k1, l1, i2, j2, k2);
               
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