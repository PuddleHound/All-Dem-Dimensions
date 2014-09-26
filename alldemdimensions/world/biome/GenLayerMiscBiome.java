package alldemdimensions.world.biome;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.layer.IntCache;
import alldemdimensions.world.Dimension;

public class GenLayerMiscBiome extends GenLayerAddMushroomIsland
{

    public GenLayerMiscBiome(long seed, GenLayer genlayer, Dimension dim)
    {
        super(seed, genlayer);
        dimension = dim;
    }

    public int[] getInts(int i, int j, int k, int l)
    {
    	int i1 = i - 1;
        int j1 = j - 1;
        int k1 = k + 2;
        int l1 = l + 2;
        int[] aint = this.parent.getInts(i1, j1, k1, l1);
        int[] aint1 = IntCache.getIntCache(k * l);

        for (int i2 = 0; i2 < l; ++i2)
        {
            for (int j2 = 0; j2 < k; ++j2)
            {
                /*int k2 = aint[j2 + 0 + (i2 + 0) * k1];
                int l2 = aint[j2 + 2 + (i2 + 0) * k1];
                int i3 = aint[j2 + 0 + (i2 + 2) * k1];
                int j3 = aint[j2 + 2 + (i2 + 2) * k1];*/
                int k3 = aint[j2 + 1 + (i2 + 1) * k1];
                this.initChunkSeed((long)(j2 + i), (long)(i2 + j));

                aint1[j2 + i2 * k] = dimension.biomeGenerator.getRareBiome(this, k3);
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