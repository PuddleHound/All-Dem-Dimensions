package alldemdimensions.world.biome;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeEntry
{
    public BiomeEntry(BiomeGenMainDimension biomegendimbase)
    {
        biome = biomegendimbase;
        biomeId = biomegendimbase.biomeID;
    }
    
    public boolean canGenerate(BiomeGenBase biome)
    {
        return biome != null ? baseBiomeId == biome.biomeID : false;
    }
    
    public boolean canGenerate(BiomeGenBase biome, BiomeGenBase biome1)
    {
        return biome != null && biome1 != null ? baseBiomeId == biome.biomeID && baseBiomeId1 == biome1.biomeID : false;
    }
    
    public BiomeGenMainDimension biome;
    public int biomeId;
    public int rarity;
    public int baseBiomeId;
    public int baseBiomeId1;
    
}
