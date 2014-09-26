package alldemdimensions.world.biome;

import java.util.HashMap;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.TerrainLayer;

import com.google.common.collect.Sets;

public abstract class BiomeGenMainDimension extends BiomeGenBase
{
    
    public BiomeGenMainDimension(int i, Dimension dim)
    {
        super(i);
        dimension = dim;
        for(byte b = 0; b < worldGen.length; b++)
        {
                worldGen[b] = new HashMap<WorldGenerator, Integer>();
        }
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear();
        allADDBiomes.add(this);
    }
    
    public static void initBiomes()
    {
    	BiomeGenBase biome = BiomeGenBase.ocean;
    	biome = BiomeGenSkyBase.skylands;
        biome = BiomeGenNetherBase.netherBarrens;
        biome = BiomeGenKytherBase.default_biome;
        biome = BiomeGenEnder.ender;
        explorationBiomesList.removeAll(allADDBiomes);
    }
    
    public BiomeGenBase setSkyColor(float red, float green, float blue)
    {
        skyColorRed = red;
        skyColorGreen = green;
        skyColorBlue = blue;
        return this;
    }
    
    @Override
    public BiomeGenMainDimension setBiomeName(String s)
    {
        biomeName = s;
        return this;
    }
    
    private BiomeEntry createBiomeEntry(int type, int rarity, BiomeGenBase baseBiome, BiomeGenBase baseBiome1)
    {
        BiomeEntry entry = new BiomeEntry(this);
        entry.baseBiomeId = baseBiome != null ? baseBiome.biomeID : 0;
        entry.baseBiomeId1 = baseBiome1 != null ? baseBiome1.biomeID : 0;
        entry.rarity = rarity;
        System.out.println("[ADD] Adding biome of type " + type + ": " + biomeName);
        return entry;
    }
    
    public BiomeGenMainDimension setMajorBiome()
    {
    	return setMajorBiome(1);
    }
    
    public BiomeGenMainDimension setMajorBiome(int tries)
    {
    	for(int count = 0; count < tries; count++)
    	{
    		dimension.biomeGenerator.mainBiomes.add(createBiomeEntry(TYPE_MAJOR, 0, null, null));
    	}
    	return this;
    }
    
    public BiomeGenMainDimension setMinorBiome(BiomeGenBase baseBiome, int rarity)
    {
    	dimension.biomeGenerator.subBiomes.add(createBiomeEntry(TYPE_MINOR, rarity, baseBiome, null));
        return this;
    }
    
    public BiomeGenBase setMiscBiome(BiomeGenBase baseBiome, int rarity)
    {
    	dimension.biomeGenerator.miscBiomes.add(createBiomeEntry(TYPE_MISC, rarity, baseBiome, null));
        return this;
    }
    
    public BiomeGenMainDimension setRiverBiome(BiomeGenBase baseBiome, int rarity)
    {
        dimension.biomeGenerator.riverBiomes.add(createBiomeEntry(TYPE_RIVER, rarity, baseBiome, null));
        return this;
    }
    
    public BiomeGenMainDimension setOceanBiome(BiomeGenBase baseBiome, int rarity)
    {
    	dimension.biomeGenerator.oceanBiomes.add(createBiomeEntry(TYPE_OCEAN, rarity, baseBiome, null));
        return this;
    }
    
    public BiomeGenMainDimension setBorderBiome(BiomeGenBase baseBiome, BiomeGenBase baseBiome1, int rarity)
    {
    	dimension.biomeGenerator.borderBiomes.add(createBiomeEntry(TYPE_BORDER, rarity, baseBiome, baseBiome1));
    	return this;
    }
    
    public BiomeGenMainDimension setDefaultRiverBiome()
    {
    	dimension.biomeGenerator.defaultRiverBiome = this;
    	return this;
    }
    
    public BiomeGenMainDimension setDefaultOceanBiome()
    {
    	dimension.biomeGenerator.defaultOceanBiome = this;
    	return this;
    }
    
    public double getTerrainNoise(TerrainLayer layer, int type)
    {
    	return 512D;
    }
    
    public float getMinHeight(TerrainLayer layer)
    {
    	return minHeight;
    }
    
    public float getMaxHeight(TerrainLayer layer)
    {
    	return maxHeight;
    }
    
    public Block getTopBlock(TerrainLayer layer)
    {
    	return topId;
    }
    
    public byte getTopMetadata(TerrainLayer layer)
    {
    	return topMetadata;
    }
    
    public Block getFillerBlock(TerrainLayer layer)
    {
    	return fillerId;
    }
    
    public byte getFillerMetadata(TerrainLayer layer)
    {
    	return fillerMetadata;
    }
    
    public Block getStoneBlock(TerrainLayer layer)
    {
    	return stoneId;
    }
    
    public byte getStoneMetadata(TerrainLayer layer)
    {
    	return stoneMetadata;
    }
    
    public float getLowerTerrainOrientation(TerrainLayer layer)
    {
    	return 4F;
    }
    
    public float getUpperTerrainOrientation(TerrainLayer layer)
    {
    	return 1F;
    }
        
    public float minHeight;
    public float maxHeight;
    public byte topMetadata = 0;
    public byte fillerMetadata = 0;
    public byte stoneMetadata = 0;
    public Block topId = Blocks.grass;
    public Block fillerId = Blocks.dirt;
    public Block stoneId = Blocks.stone;
    public float skyColorRed;
    public float skyColorGreen;
    public float skyColorBlue;
    public float sunSize = 30F;
    public float moonSize = 20F;
    public boolean overrideFoliageColor = false;
    public HashMap<WorldGenerator, Integer>[] worldGen = new HashMap[7];
    public Dimension dimension;
    public static final Set allADDBiomes = Sets.newHashSet();
    public static final byte GEN_FIRST = 0;
    public static final byte GEN_ORE = 1;
    public static final byte GEN_STRUCTURE = 2;
    public static final byte GEN_MISC = 3;
    public static final byte GEN_TREE = 4;
    public static final byte GEN_PLANT = 5;
    public static final byte GEN_LAST = 6;
    public static final byte TYPE_MAJOR = 0;
    public static final byte TYPE_MINOR = 1;
    public static final byte TYPE_MISC = 2;
    public static final byte TYPE_OCEAN = 3;
    public static final byte TYPE_RIVER = 4;
    public static final byte TYPE_BORDER = 5;
}
