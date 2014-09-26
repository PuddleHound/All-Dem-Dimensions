package alldemdimensions.world.biome;

import alldemdimensions.AllDemDimensions;

public class BiomeGenNetherJungle extends BiomeGenNetherBase 
{
    
    public BiomeGenNetherJungle(int i)
    {
        super(i);
        topId = fillerId = stoneId = AllDemDimensions.netherStone;
        topMetadata = fillerMetadata = stoneMetadata = AllDemDimensions.netherStone.SOULSTONE;
        minHeight = -1.5F;
        maxHeight = 1.8F;
        minCeilingHeight = -0.1F;
        maxCeilingHeight = -1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D * 4D;//= 2737.648D
        terrainNoise4 = 13688.24D;//high = multiple layers of terrain
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D;
        terrainNoiseCeiling4 = 684.412D;//2053.236D;
        biomeName = "Nether Jungle";
    }
    
}
