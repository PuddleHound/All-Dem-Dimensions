package alldemdimensions.world.biome;

import net.minecraft.init.Blocks;

public class BiomeGenNetherGlowstoneCave extends BiomeGenNetherBase 
{
    
    public BiomeGenNetherGlowstoneCave(int i)
    {
        super(i);
        topId = Blocks.netherrack;
        fillerId = Blocks.netherrack;
        stoneId = Blocks.netherrack;
        minHeight = -1.5F;//0.8F;
        maxHeight = 2.5F;//1.8F;//columns
        minCeilingHeight = 1.8F;//-1.0F;
        maxCeilingHeight = 0.5F;//-1.8F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D * 3D;//684.412D * 2D;
        terrainNoise4 = 684.412D * 3D;//684.412D * 2D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        //terrainNoiseCeiling3 = 684.412D * 2D;
        //terrainNoiseCeiling4 = 684.412D * 2D;
        biomeName = "Glowstone Cavern";
        worldGen[GEN_LAST].put(glowstone1, 20);
        worldGen[GEN_LAST].put(glowstone2, 50);
    }
    
}
