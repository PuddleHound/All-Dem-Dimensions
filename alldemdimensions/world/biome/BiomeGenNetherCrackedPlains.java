package alldemdimensions.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenNetherCrackedPlains extends BiomeGenNetherBase
{
    
    public BiomeGenNetherCrackedPlains(int i)
    {
        super(i);
        topId = fillerId = Blocks.soul_sand;
        minHeight = -1.5F;
        maxHeight = 0.1F;
        //minCeilingHeight = -0.1F;
        //maxCeilingHeight = -1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D * 0.25D;
        terrainNoise4 = 684.412D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D * 0.125D;
        terrainNoiseCeiling4 = 684.412D;
        biomeName = "Cracked Plains";
        worldGen[GEN_FIRST].put(cracks, 32);
    }
    
}
