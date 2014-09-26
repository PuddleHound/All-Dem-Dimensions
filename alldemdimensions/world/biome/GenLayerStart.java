package alldemdimensions.world.biome;

import net.minecraft.world.gen.layer.GenLayerIsland;
import alldemdimensions.world.Dimension;

public class GenLayerStart extends GenLayerIsland
{
	
    public GenLayerStart(long seed, Dimension dim)
    {
        super(seed);
        dimension = dim;
    }

    public int[] getInts(int i, int j, int k, int l)
    {
        int[] aint = super.getInts(i, j, k, l);
        java.util.Arrays.fill(aint, 1);
        return aint;
    }
    
    @Override
    public int nextInt(int i)
    {
    	return super.nextInt(i);
    }
    
    private Dimension dimension;
}