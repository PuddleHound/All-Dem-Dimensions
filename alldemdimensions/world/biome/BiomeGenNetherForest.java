package alldemdimensions.world.biome;


public class BiomeGenNetherForest extends BiomeGenNetherBase
{
    
    public BiomeGenNetherForest(int i)
    {
        super(i);
        //topId = fillerId = stoneId = (short)AllDemDimensions.netherStone.blockID;
        //topMetadata = fillerMetadata = stoneMetadata = AllDemDimensions.netherStone.SOULSTONE;
        minHeight = -1.0F;
        maxHeight = 1.5F;
        minCeilingHeight = -0.1F;
        maxCeilingHeight = -1.5F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D * 4.0D;
        terrainNoise3 = 684.412D * 4.0D;
        terrainNoise4 = 684.412D * 0.25D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D * 4.0D;
        terrainNoiseCeiling3 = 684.412D * 4.0D;
        terrainNoiseCeiling4 = 684.412D * 0.25D;
        biomeName = "Root Forest";
    }
    
}
