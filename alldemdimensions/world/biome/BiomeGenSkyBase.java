package alldemdimensions.world.biome; import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.Plant;
import alldemdimensions.block.Tree;
import alldemdimensions.entity.EntityAmpfly;
import alldemdimensions.entity.EntityBadger;
import alldemdimensions.entity.EntityBee;
import alldemdimensions.entity.EntityButterfly;
import alldemdimensions.entity.EntityEnderwraith;
import alldemdimensions.entity.EntitySycopter;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.TerrainLayer;
import alldemdimensions.world.environment.EnumZenithWeather;
import alldemdimensions.world.gen.WorldGenADDLiquids;
import alldemdimensions.world.gen.WorldGenADDOres;
import alldemdimensions.world.gen.WorldGenNetherLavaLake;
import alldemdimensions.world.gen.WorldGenSkyBeam;
import alldemdimensions.world.gen.WorldGenSkyBeehive;
import alldemdimensions.world.gen.WorldGenSkyCastle;
import alldemdimensions.world.gen.WorldGenSkyCrystalCloud;
import alldemdimensions.world.gen.WorldGenSkyCrystals;
import alldemdimensions.world.gen.WorldGenSkyDungeon;
import alldemdimensions.world.gen.WorldGenSkyHoneysuckle;
import alldemdimensions.world.gen.WorldGenSkyIcicles;
import alldemdimensions.world.gen.WorldGenSkyLotus;
import alldemdimensions.world.gen.WorldGenSkyPlants;
import alldemdimensions.world.gen.WorldGenSkyPond;
import alldemdimensions.world.gen.WorldGenSkyRingIsland;
import alldemdimensions.world.gen.WorldGenSkyRuins;
import alldemdimensions.world.gen.WorldGenSkyThermal;
import alldemdimensions.world.gen.WorldGenSkyVillage;

public class BiomeGenSkyBase extends BiomeGenMainDimension
{
    public BiomeGenSkyBase(int i)
    {
        super(i, Dimension.zenith);
		topBlock = Blocks.grass;
		fillerBlock = Blocks.dirt;
		topId = Blocks.grass;
        fillerId = Blocks.dirt;
        stoneId = AllDemDimensions.limestone;
		minHeight = -12.0F;
		maxHeight = 1.5F;
		minMtnHeight = -1.8F;
		maxMtnHeight = -0.1F;
		terrainNoise1 = 512D;
		terrainNoise2 = 512D;
		terrainNoise3 = 1368.824D;
        terrainNoise4 = 5475.296D * 3D;
		terrainNoiseMtn1 = 512D;
		terrainNoiseMtn2 = 512D;
		terrainNoiseMtn3 = 684.412D / 4D;
		terrainNoiseMtn4 = 684.412D / 4D;
		temperature = 0.3F;
		rainfall = 1.0F;
		waterColorMultiplier = 0x00ffcc;
		minSnowHeight = 96;
        moonSize = 30F;
		setDisableRain();
        skyColorRed = 0.7F;
        skyColorGreen = 0.8F;
        skyColorBlue = 1.0F;
        spawnableCreatureList.clear();
		spawnableMonsterList.clear();
		spawnableWaterCreatureList.clear();
		spawnableCaveCreatureList.clear();
		spawnableMonsterList.add(new SpawnListEntry(EntityButterfly.class, 1, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityBee.class, 1, 1, 1));
		//spawnableMonsterList.add(new SpawnListEntry(EntityHeliumSlime.class, 1, 1, 1));//1.7 problem with sounds?
		spawnableMonsterList.add(new SpawnListEntry(EntitySycopter.class, 1, 4, 4));
		spawnableMonsterList.add(new SpawnListEntry(EntityAmpfly.class, 1, 1, 1));
		spawnableMonsterList.add(new SpawnListEntry(EntityEnderwraith.class, 1, 1, 1));
		spawnableCreatureList.add(new SpawnListEntry(EntityBadger.class, 4, 4, 4));

		worldGen[GEN_TREE].put(chestnutTree, 8);
		worldGen[GEN_ORE].put(emerald, 4);
		worldGen[GEN_ORE].put(silver, 8);
		worldGen[GEN_ORE].put(phosphorus, 16);
		worldGen[GEN_STRUCTURE].put(ruins, -4);
		worldGen[GEN_STRUCTURE].put(castle, -2000);
		worldGen[GEN_MISC].put(crystal, 4);
		worldGen[GEN_MISC].put(pond, -25);
		worldGen[GEN_ORE].put(waterfall, 50);
		worldGen[GEN_ORE].put(honeyfall, 10);
		worldGen[GEN_PLANT].put(lapisFlower, -128);
		worldGen[GEN_LAST].put(thermal, -24);
                
        //hollow islands
        /*
        worldGen[GEN_PLANT].put(mushroomCyan, 64);
        //worldGen[GEN_FIRST].put(caveNectarPond, 8);
        worldGen[GEN_PLANT].put(caveVines, 64);
        worldGen[GEN_FIRST].put(caveCrystal, 4);*/
	}
	
    @Override
	public int getSkyColorByTemp(float f)
    {
		return 0xabcaff;
	}
	
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.FLOWERS;
	}
        
    public BiomeGenSkyBase setMinMaxTerrainHeight(float f, float f1)
    {
        minMtnHeight = f;
        maxMtnHeight = f1;
        return this;
    }
    
    public BiomeGenSkyBase setTerrainNoise(double d, double d1, double d2, double d3)
    {
        terrainNoiseMtn1 = d;
        terrainNoiseMtn2 = d1;
        terrainNoiseMtn3 = d2;
        terrainNoiseMtn4 = d3;
        return this;
    }
    
    @Override
    public double getTerrainNoise(TerrainLayer layer, int i)
    {
    	if(layer == Dimension.zenith.cloudLayer)
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
    			case 0: return terrainNoiseMtn3;
    			case 1: return terrainNoiseMtn4;
    			case 2: return terrainNoiseMtn1;
    			case 3: return terrainNoiseMtn2;
    		}
    	}
    	return super.getTerrainNoise(layer, i);
    }
    
    @Override
    public float getMinHeight(TerrainLayer layer)
    {
    	if(layer == Dimension.zenith.cloudLayer)
    	{
    		return minHeight;
    	} else
    	{
    		return minMtnHeight;
    	}
    }
    
    @Override
    public float getMaxHeight(TerrainLayer layer)
    {
    	if(layer == Dimension.zenith.cloudLayer)
    	{
    		return maxHeight;
    	} else
    	{
    		return maxMtnHeight;
    	}
    }
    
    @Override
    public float getLowerTerrainOrientation(TerrainLayer layer)
    {
    	return -1F;
    }
    
    @Override
    public Block getTopBlock(TerrainLayer layer)
    {
    	if(layer == Dimension.zenith.cloudLayer)
    	{
    		return AllDemDimensions.cloud;
    	}
    	return super.getTopBlock(layer);
    }
    
    @Override
    public byte getTopMetadata(TerrainLayer layer)
    {
    	return topMetadata;
    }
    
    @Override
    public Block getFillerBlock(TerrainLayer layer)
    {
    	if(layer == Dimension.zenith.cloudLayer)
    	{
    		return AllDemDimensions.cloud;
    	}
    	return super.getFillerBlock(layer);
    }
    
    @Override
    public byte getFillerMetadata(TerrainLayer layer)
    {
    	return fillerMetadata;
    }
    
    @Override
    public Block getStoneBlock(TerrainLayer layer)
    {
    	if(layer == Dimension.zenith.cloudLayer)
    	{
    		return AllDemDimensions.cloud;
    	}
    	return super.getStoneBlock(layer);
    }
    
    @Override
    public byte getStoneMetadata(TerrainLayer layer)
    {
    	return stoneMetadata;
    }

   	public double terrainNoise1;
	public double terrainNoise2;
	public double terrainNoise3;
	public double terrainNoise4;
	public double terrainNoiseMtn1;
	public double terrainNoiseMtn2;
	public double terrainNoiseMtn3;
	public double terrainNoiseMtn4;
	public float minMtnHeight;
	public float maxMtnHeight;
	public int minSnowHeight;
	
	//trees
	public WorldGenerator arrowTree = Tree.zenith_arrow.generator;//new WorldGenSkyTree();
	public WorldGenerator willowTree = Tree.zenith_willow.generator;//new WorldGenSkyWillow();
	public WorldGenerator hugeTree = Tree.zenith_huge.generator;//new WorldGenSkyHugeTree();
	public WorldGenerator plumTree = Tree.zenith_plum.generator;//new WorldGenSkyPlumTree();
	public WorldGenerator cherryTree = Tree.zenith_cherry.generator;//new WorldGenSkyCherryTree();
	public WorldGenerator iceTree = Tree.zenith_ice.generator;//new WorldGenSkyIceTree();
	public WorldGenerator ancientTree = Tree.zenith_ancient.generator;//new WorldGenSkyAncientTree();
	public WorldGenerator corkscrewTree = Tree.zenith_corkscrew.generator;//new WorldGenSkyCorkscrewTree();
	public WorldGenerator redwoodTree = null;
	public WorldGenerator bonsaiTree = Tree.zenith_bonsai.generator;//new WorldGenSkyBonsaiTree();
	public WorldGenerator jungleTree = Tree.zenith_jungle.generator;//new WorldGenSkyJungleTree();
	public WorldGenerator chestnutTree = Tree.zenith_chestnut.generator;//new WorldGenSkyChestnutTree();
	public WorldGenerator bamboo = new WorldGenSkyPlants(Plant.zenith_bamboo.plantBlock, Plant.zenith_bamboo.plantMeta, 24);
    public WorldGenerator crystalTree = Tree.zenith_crystal.generator;//new WorldGenSkyCrystalTree();

	//rocks
	public WorldGenerator emerald = new WorldGenADDOres(AllDemDimensions.emeraldOre, 0, 3, AllDemDimensions.limestone, 0);
	public WorldGenerator silver = new WorldGenADDOres(AllDemDimensions.silverOre, 0, 12, AllDemDimensions.limestone, 0);
	public WorldGenerator phosphorus = new WorldGenADDOres(AllDemDimensions.phosphorusOre, 0, 10, AllDemDimensions.limestone, 0);
	
	//plants
	public WorldGenerator flowerGrass = Plant.zenith_flowerGrass.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.FLOWER_GRASS);
	public WorldGenerator cotton = Plant.zenith_cotton.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.COTTON);
	public WorldGenerator hibiscus = Plant.zenith_hibiscus.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.HIBISCUS);
	public WorldGenerator delphinium = Plant.zenith_delphinium.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.DELPHINIUM);
	public WorldGenerator orchid  = Plant.zenith_orchid.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.ORCHID);
	public WorldGenerator anthurium  = Plant.zenith_anthurium.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.ANTHURIUM);
	public WorldGenerator mushroom = null;//Plant.zenith_mushroom.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.MUSHROOM);
	public WorldGenerator fern = new WorldGenTallGrass(Blocks.tallgrass, 2);
	public WorldGenerator honeysuckle = new WorldGenSkyHoneysuckle(false);
	public WorldGenerator lapisFlower = Plant.zenith_lapisFlower.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.LAPIS_FLOWER);
    public WorldGenerator mushroomCyan = null;//Plant.zenith_mushroom.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.MUSHROOM, AllDemDimensions.limestone, 0, 1, true);
    public WorldGenerator caveVines = new WorldGenSkyHoneysuckle(true);
    public WorldGenerator redstoneFlower = Plant.zenith_redstoneFlower.generator;//new WorldGenSkyPlants(AllDemDimensions.skyPlant, AllDemDimensions.skyPlant.REDSTONE_FLOWER);
        
	//dungeons
	public WorldGenerator ruins = new WorldGenSkyRuins();
	public WorldGenerator dungeon = new WorldGenSkyDungeon();
	public WorldGenerator castle = new WorldGenSkyCastle();
	public WorldGenerator beeHive = new WorldGenSkyBeehive();
	public WorldGenerator village = new WorldGenSkyVillage();
	
	//other
	public WorldGenerator crystal = new WorldGenSkyCrystals(false);
	public WorldGenerator pond = new WorldGenSkyPond(Blocks.grass, Blocks.flowing_water, Blocks.flowing_water);
	public WorldGenerator frozenPond = new WorldGenSkyPond(Blocks.grass, Blocks.ice, Blocks.ice);
	public WorldGenerator honeyPond = new WorldGenSkyPond(AllDemDimensions.waxGrass, AllDemDimensions.honeyStill, AllDemDimensions.honeyMoving);
	public WorldGenerator lavaPond = new WorldGenSkyPond(Blocks.grass, Blocks.lava, Blocks.flowing_lava);
	public WorldGenerator ringIsland = new WorldGenSkyRingIsland();
	public WorldGenerator waterfall = new WorldGenADDLiquids(Blocks.flowing_water, AllDemDimensions.limestone, AllDemDimensions.cloud);
	public WorldGenerator honeyfall = new WorldGenADDLiquids(AllDemDimensions.honeyMoving, AllDemDimensions.limestone, AllDemDimensions.cloud);
	public WorldGenerator nectarfall = new WorldGenADDLiquids(AllDemDimensions.nectarMoving, AllDemDimensions.limestone, AllDemDimensions.cloud);
    public WorldGenerator lavafall = new WorldGenADDLiquids(Blocks.flowing_lava, AllDemDimensions.limestone, AllDemDimensions.cloud);
	public WorldGenerator amber = null;
	public WorldGenerator thermal = new WorldGenSkyThermal();
	public WorldGenerator icicle = new WorldGenSkyIcicles();
    public WorldGenerator beam = new WorldGenSkyBeam();
    public WorldGenerator crystalCloud = new WorldGenSkyCrystalCloud(AllDemDimensions.cloud, AllDemDimensions.cloud.ICE);
    public WorldGenerator lotus = new WorldGenSkyLotus();
    public WorldGenerator caveNectarPond = new WorldGenNetherLavaLake(AllDemDimensions.nectarStill, 0, AllDemDimensions.limestone, 0);
    public WorldGenerator caveCrystal = new WorldGenSkyCrystals(true);
    
    public static final BiomeGenMainDimension skylands = (new BiomeGenSkylands(210)).setBiomeName("Skylands").setMajorBiome();
	public static final BiomeGenMainDimension skyVillage = (new BiomeGenSkyVillage(212)).setBiomeName("SkyVillage");
	public static final BiomeGenMainDimension skyBlossom = (new BiomeGenSkyBlossom(215)).setBiomeName("SkyBlossom");
	
	public static final BiomeGenMainDimension skyIce = (new BiomeGenSkyIce(218)).setBiomeName("SkyIce");
	public static final BiomeGenMainDimension skyMountain = (new BiomeGenSkyMountain(219, false)).setBiomeName("SkyMountain");
	public static final BiomeGenMainDimension skyMountainBorder = (new BiomeGenSkyMountain(221, true)).setBiomeName("SkyMountainBorder");
	public static final BiomeGenMainDimension skyFlowerBiome = (new BiomeGenSkyHoney(222)).setBiomeName("SkyFlowers");
	public static final BiomeGenMainDimension skyRingIsland = (new BiomeGenSkyRingIsland(223)).setBiomeName("SkyRingIsland");
	public static final BiomeGenMainDimension skyJungle = (new BiomeGenSkyJungle(224)).setBiomeName("SkyJungle");
	public static final BiomeGenMainDimension skyLake = (new BiomeGenSkyLake(225)).setBiomeName("SkyLake");

	public static final BiomeGenMainDimension skyAncientForest = (new BiomeGenSkyAncientForest(227)).setBiomeName("SkyAncientForest");//.setMajorBiome();
	public static final BiomeGenMainDimension skyBambooForest = (new BiomeGenSkyBambooForest(228)).setBiomeName("SkyBambooForest");//.setMajorBiome();
    public static final BiomeGenMainDimension skylandsMountain = (new BiomeGenSkylands(229)).setMinMaxTerrainHeight(-1.8F, 3.6F)/*(0.2F, 5F)(-0.2F, 4F)*/.setBiomeName("SkylandsMountain").setMinorBiome(skylands, 8);
	public static final BiomeGenMainDimension skyGilded = (new BiomeGenSkyGilded(230)).setBiomeName("SkyGilded").setMajorBiome();
    public static final BiomeGenMainDimension skyGildedMountain = (new BiomeGenSkyGilded(231)).setMinMaxTerrainHeight(-1.8F, 3.6F)/*(0.2F, 5F)(-0.2F, 4F)*/.setBiomeName("SkyGildedMountain").setMinorBiome(skyGilded, 8);
    public static final BiomeGenMainDimension skyCrystal = (new BiomeGenSkyCrystal(233)).setBiomeName("SkyCrystal");//.setMajorBiome();
    public static final BiomeGenMainDimension skyCrystalMountain = (new BiomeGenSkyCrystal(234)).setMinMaxTerrainHeight(-0.2F, 1F).setBiomeName("SkyCrystalMountain");
    public static final BiomeGenMainDimension skyTunnel = (new BiomeGenSkyTunnel(235)).setBiomeName("SkyTunnel").setDefaultRiverBiome();
    public static final BiomeGenMainDimension skySunSwamp = (new BiomeGenSkySunSwamp(236)).setBiomeName("SkySunSwamp").setMajorBiome();
    public static final BiomeGenMainDimension skySunSwampLake = (new BiomeGenSkySunSwamp(237)).setMinMaxTerrainHeight(-0.4F, 0.8F).setBiomeName("SkySunSwampLake").setMinorBiome(skySunSwamp, 2);
    public static final BiomeGenMainDimension skyBambooForestMountain = ((BiomeGenSkyBase)(new BiomeGenSkyBambooForest(238))).setMinMaxTerrainHeight(-0.4F, 1.2F).setTerrainNoise(512D, 512D, 5475.296D, 5475.296D).setBiomeName("SkyBambooForestMountain");

}
