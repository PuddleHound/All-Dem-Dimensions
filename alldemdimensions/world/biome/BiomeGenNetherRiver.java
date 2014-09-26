package alldemdimensions.world.biome;


public class BiomeGenNetherRiver extends BiomeGenNetherBase
{
    
    public BiomeGenNetherRiver(int i)
    {
        super(i);
        minHeight = -2.0F;
        maxHeight = 0.0F;
        //minCeilingHeight = -0.1F;
        //maxCeilingHeight = -1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D;
        terrainNoise4 = 684.412D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D;
        terrainNoiseCeiling4 = 684.412D;
        biomeName = "Lava River";
    }
    
}
