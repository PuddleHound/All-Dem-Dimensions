package alldemdimensions.world.biome;


public class BiomeGenNetherBadlands extends BiomeGenNetherBase
{
    
    public BiomeGenNetherBadlands(int i)
    {
        super(i);
        minHeight = -1.8F;//-2.5F;
        maxHeight = 1.8F;//1.0F;
        //minCeilingHeight = -0.1F;
        //maxCeilingHeight = -1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D * 4.0D;
        terrainNoise4 = 684.412D * 0.25D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D;
        terrainNoiseCeiling4 = 684.412D;
        biomeName = "Badlands";
    }
    
}
