package alldemdimensions.world.biome;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiver;
import alldemdimensions.world.Dimension;

public class GenLayerBasicRiver extends GenLayerRiver
{
	
    public GenLayerBasicRiver(long seed, GenLayer genlayer, Dimension dim)
    {
        super(seed, genlayer);
        dimension = dim;
    }

    @Override
    public int[] getInts(int i, int j, int k, int l)
    {
    	int[] aint1 = super.getInts(i, j, k, l);
    	for(int index = 0; index < aint1.length; index++)
    	{
    		if(aint1[index] == BiomeGenBase.river.biomeID)
    		{
    			aint1[index] = dimension.biomeGenerator.getBaseRiverBiome();
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