package alldemdimensions.world.biome;

public class BiomeGenSkyTunnel extends BiomeGenSkylands
{

    public BiomeGenSkyTunnel(int i)
    {
        super(i);
        minMtnHeight = -2.0F;//-0.2F;
        maxMtnHeight = 1.8F;
        terrainNoiseMtn3 /= 2F;
        terrainNoiseMtn4 /= 2F;
    }
    
}
