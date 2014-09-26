package alldemdimensions.world.biome;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;
import net.minecraft.world.gen.layer.IntCache;
import alldemdimensions.world.Dimension;

public class GenLayerRiverBiome extends GenLayerRiverMix
{

    public GenLayerRiverBiome(long seed, GenLayer genlayer, GenLayer genlayer1, Dimension dim)
    {
        super(seed, genlayer, genlayer1);
        this.biomePatternGeneratorChain = genlayer;
        this.riverPatternGeneratorChain = genlayer1;
        dimension = dim;
    }

    public void initWorldGenSeed(long seed)
    {
        this.biomePatternGeneratorChain.initWorldGenSeed(seed);
        this.riverPatternGeneratorChain.initWorldGenSeed(seed);
        super.initWorldGenSeed(seed);
    }

    public int[] getInts(int i, int j, int k, int l)
    {
        int[] aint = this.biomePatternGeneratorChain.getInts(i, j, k, l);
        int[] aint1 = this.riverPatternGeneratorChain.getInts(i, j, k, l);
        int[] aint2 = IntCache.getIntCache(k * l);

        for (int i1 = 0; i1 < k * l; ++i1)
        {
            if (aint1[i1] == dimension.biomeGenerator.getBaseRiverBiome())
            {
                aint2[i1] = dimension.biomeGenerator.getSpecializedRiverBiome(this, aint[i1]);
            }
            else
            {
                aint2[i1] = aint[i1];
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
    private GenLayer biomePatternGeneratorChain;
    private GenLayer riverPatternGeneratorChain;
}