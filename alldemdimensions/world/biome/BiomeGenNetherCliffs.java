package alldemdimensions.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenNetherCliffs extends BiomeGenNetherBase 
{
    
    public BiomeGenNetherCliffs(int i)
    {
        super(i);
        topId = Blocks.netherrack;
        fillerId = Blocks.netherrack;
        stoneId = Blocks.netherrack;
        minHeight = -1.5F;
        maxHeight = 2F;//1.8F;
        //minCeilingHeight = -0.1F;
        //maxCeilingHeight = -1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 64D;
        terrainNoise3 = 2737.648D;
        terrainNoise4 = 684.412D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D;
        terrainNoiseCeiling4 = 684.412D;
        biomeName = "Red Cliffs";
    }
    
}
