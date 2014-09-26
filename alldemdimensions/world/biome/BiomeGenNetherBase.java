package alldemdimensions.world.biome;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityMagmaCube;
import net.minecraft.entity.monster.EntityPigZombie;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenFire;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenGlowStone1;
import net.minecraft.world.gen.feature.WorldGenGlowStone2;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.TerrainLayer;
import alldemdimensions.world.gen.WorldGenADDLiquids;
import alldemdimensions.world.gen.WorldGenNetherCrevice;
import alldemdimensions.world.gen.WorldGenNetherCrystal;
import alldemdimensions.world.gen.WorldGenNetherLavaLake;
import alldemdimensions.world.gen.WorldGenNetherMushroom;

public class BiomeGenNetherBase extends BiomeGenMainDimension
{
    
    public BiomeGenNetherBase(int i)
    {
        super(i, Dimension.nether);
        topId = Blocks.netherrack;
        fillerId = Blocks.netherrack;
        stoneId = Blocks.netherrack;
        spawnableMonsterList.add(new SpawnListEntry(EntityGhast.class, 50, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityPigZombie.class, 100, 4, 4));
        spawnableMonsterList.add(new SpawnListEntry(EntityMagmaCube.class, 1, 4, 4));
        minHeight = -1.8F;
        maxHeight = 1.8F;
        minCeilingHeight = 1.8F;//-0.1F;
        maxCeilingHeight = 1.0F;//-1.0F;
        terrainNoise1 = 512D;
        terrainNoise2 = 512D;
        terrainNoise3 = 684.412D;
        terrainNoise4 = 2053.236D;
        terrainNoiseCeiling1 = 512D;
        terrainNoiseCeiling2 = 512D;
        terrainNoiseCeiling3 = 684.412D;
        terrainNoiseCeiling4 = 2053.236D;
        temperature = 2.0F;
        rainfall = 0.0F;
        setDisableRain();
        biomeName = "Barrens";
        worldGen[GEN_ORE].put(lavaFall, 8);
        worldGen[GEN_ORE].put(crystals, 8);
        worldGen[GEN_LAST].put(fire, 6);
        worldGen[GEN_LAST].put(glowstone1, 5);
        worldGen[GEN_LAST].put(glowstone2, 10);
        worldGen[GEN_PLANT].put(mushroomBrown, 1);
        worldGen[GEN_PLANT].put(mushroomRed, 1);
    }
    
    public BiomeGenNetherBase setMinMaxFloorHeight(float f, float f1)
    {
        minHeight = f;
        maxHeight = f1;
        return this;
    }
    
    public BiomeGenNetherBase setMinMaxCeilingHeight(float f, float f1)
    {
        minCeilingHeight = f;
        maxCeilingHeight = f1;
        return this;
    }
    
    public BiomeGenNetherBase setFloorNoise(double d, double d1, double d2, double d3)
    {
        terrainNoise1 = d;
        terrainNoise2 = d1;
        terrainNoise3 = d2;
        terrainNoise4 = d3;
        return this;
    }
    
    public BiomeGenNetherBase setCeilingNoise(double d, double d1, double d2, double d3)
    {
        terrainNoiseCeiling1 = d;
        terrainNoiseCeiling2 = d1;
        terrainNoiseCeiling3 = d2;
        terrainNoiseCeiling4 = d3;
        return this;
    }
    
    @Override
    public double getTerrainNoise(TerrainLayer layer, int i)
    {
    	if(layer == Dimension.nether.floorLayer)
    	{
    		switch(i)
    		{
    			case 0: return terrainNoise3;
    			case 1: return terrainNoise4;
    			case 2: return terrainNoise1;
    			case 3: return terrainNoise2;
    		}
    	} else
    	{
    		switch(i)
    		{
    			case 0: return terrainNoiseCeiling3;
    			case 1: return terrainNoiseCeiling4;
    			case 2: return terrainNoiseCeiling1;
    			case 3: return terrainNoiseCeiling2;
    		}
    	}
    	return super.getTerrainNoise(layer, i);
    }
    
    @Override
    public float getMinHeight(TerrainLayer layer)
    {
    	if(layer == Dimension.nether.floorLayer)
    	{
    		return minHeight;
    	} else
    	{
    		return minCeilingHeight;
    	}
    }
    
    @Override
    public float getMaxHeight(TerrainLayer layer)
    {
    	if(layer == Dimension.nether.floorLayer)
    	{
    		return maxHeight;
    	} else
    	{
    		return maxCeilingHeight;
    	}
    }
    
    @Override
    public float getLowerTerrainOrientation(TerrainLayer layer)
    {
    	if(layer == Dimension.nether.ceilingLayer)
    	{
    		return -1F;
    	}
    	return super.getLowerTerrainOrientation(layer);
    }
    
    @Override
    public float getUpperTerrainOrientation(TerrainLayer layer)
    {
    	if(layer == Dimension.nether.ceilingLayer)
    	{
    		return -1F;
    	}
    	return super.getUpperTerrainOrientation(layer);
    }    
    
    @Override
    public Block getTopBlock(TerrainLayer layer)
    {
    	return layer == Dimension.nether.ceilingLayer ? getStoneBlock(layer) : super.getTopBlock(layer);
    }
    
    @Override
    public byte getTopMetadata(TerrainLayer layer)
    {
    	return layer == Dimension.nether.ceilingLayer ? getStoneMetadata(layer) : super.getTopMetadata(layer);
    }
    
    @Override
    public Block getFillerBlock(TerrainLayer layer)
    {
    	return layer == Dimension.nether.ceilingLayer ? getStoneBlock(layer) : super.getFillerBlock(layer);
    }
    
    @Override
    public byte getFillerMetadata(TerrainLayer layer)
    {
    	return layer == Dimension.nether.ceilingLayer ? getStoneMetadata(layer) : super.getFillerMetadata(layer);
    }
    
    /*@Override
    public Block getStoneBlock(TerrainLayer layer)
    {
    	return Blocks.netherrack;
    }
    
    @Override
    public byte getStoneMetadata(TerrainLayer layer)
    {
    	return 0;
    }*/
    
    public float minCeilingHeight;
    public float maxCeilingHeight;
    public double terrainNoise1;
    public double terrainNoise2;
    public double terrainNoise3;
    public double terrainNoise4;
    public double terrainNoiseCeiling1;
    public double terrainNoiseCeiling2;
    public double terrainNoiseCeiling3;
    public double terrainNoiseCeiling4;
    
    //original
    public static final WorldGenerator lavaFall = new WorldGenADDLiquids(Blocks.flowing_lava, Blocks.netherrack);
    public static final WorldGenerator lavaFallSoulstoneBasalt = new WorldGenADDLiquids(Blocks.flowing_lava, AllDemDimensions.netherStone);
    public static final WorldGenerator fire = new WorldGenFire();
    public static final WorldGenerator glowstone1 = new WorldGenGlowStone1();
    public static final WorldGenerator glowstone2 = new WorldGenGlowStone2();
    public static final WorldGenerator mushroomBrown = new WorldGenFlowers(Blocks.brown_mushroom);
    public static final WorldGenerator mushroomRed = new WorldGenFlowers(Blocks.red_mushroom);
    
    public static final WorldGenerator lavaLake = new WorldGenNetherLavaLake(Blocks.lava, 0, Blocks.air, -1);//formerly -1, -1
    public static final WorldGenerator giantMushroomBrown = new WorldGenNetherMushroom(Blocks.brown_mushroom_block);
    public static final WorldGenerator giantMushroomRed = new WorldGenNetherMushroom(Blocks.red_mushroom_block);
    public static final WorldGenerator crystals = new WorldGenNetherCrystal();
    public static final WorldGenerator cracks = new WorldGenNetherCrevice();
    
    public static final BiomeGenMainDimension netherBarrens = new BiomeGenNetherBase(185);
    public static final BiomeGenMainDimension netherCliffs = new BiomeGenNetherCliffs(186).setMajorBiome();
    public static final BiomeGenMainDimension netherOcean = new BiomeGenNetherOcean(187);
    public static final BiomeGenMainDimension netherAsh = new BiomeGenNetherAsh(188);//.setMajorBiome();
    public static final BiomeGenMainDimension netherGlowstoneCave = new BiomeGenNetherGlowstoneCave(189).setMajorBiome();
    public static final BiomeGenMainDimension netherFungi = new BiomeGenNetherFungi(190).setMajorBiome();
    public static final BiomeGenMainDimension netherForest = new BiomeGenNetherForest(191);
    public static final BiomeGenMainDimension netherJungle = new BiomeGenNetherJungle(192);
    public static final BiomeGenMainDimension netherCrackedPlains = new BiomeGenNetherCrackedPlains(193).setMajorBiome();
    public static final BiomeGenMainDimension netherBadlands = new BiomeGenNetherBadlands(194).setMajorBiome();
    public static final BiomeGenMainDimension netherRiver = new BiomeGenNetherRiver(195).setDefaultRiverBiome();
        
}
