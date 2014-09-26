package alldemdimensions.world.biome;

import alldemdimensions.AllDemDimensions;

public class BiomeGenNetherAsh extends BiomeGenNetherBase 
{
    
    public BiomeGenNetherAsh(int i)
    {
        super(i);
        topId = fillerId = stoneId = AllDemDimensions.netherStone;
        topMetadata = fillerMetadata = stoneMetadata = AllDemDimensions.netherStone.BASALT;
        minHeight = -1.0F;
        maxHeight = 2.8F;
        //minCeilingHeight = -0.1F;
        //maxCeilingHeight = -1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D * 0.25D;
        terrainNoise4 = 684.412D * 0.25D;
        /*terrainNoise1 = 512D;
        terrainNoise2 = 512D * 0.125D;
        terrainNoise3 = 684.412D * 0.5D;//low - makes flat
        terrainNoise4 = 684.412D * 0.25D;*///lots of walls and cliffs
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D;
        terrainNoiseCeiling4 = 684.412D;
        biomeName = "Ashen";
        worldGen[GEN_ORE].remove(lavaFall);
        worldGen[GEN_ORE].put(lavaFallSoulstoneBasalt, 25);
        worldGen[GEN_ORE].put(lavaLake, 150);
    }
    
}