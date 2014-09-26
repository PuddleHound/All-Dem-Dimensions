package alldemdimensions.world.biome;

import alldemdimensions.AllDemDimensions;

public class BiomeGenNetherFungi extends BiomeGenNetherBase
{
    
    public BiomeGenNetherFungi(int i)
    {
        super(i);
        topId = fillerId = stoneId = AllDemDimensions.netherStone;
        topMetadata = fillerMetadata = stoneMetadata = AllDemDimensions.netherStone.SOULSTONE;
        minHeight = -1.0F;
        maxHeight = 3F;
        //minCeilingHeight = -0.1F;
        //maxCeilingHeight = -1.8F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D * 0.25D;
        terrainNoise4 = 684.412D * 0.25D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D * 0.5D;
        terrainNoiseCeiling4 = 684.412D * 0.5D;
        biomeName = "Fungal";
        worldGen[GEN_TREE].put(giantMushroomBrown, 16);
        worldGen[GEN_TREE].put(giantMushroomRed, 16);
        worldGen[GEN_PLANT].put(mushroomBrown, 6);
        worldGen[GEN_PLANT].put(mushroomRed, 6);
    }
    
}
