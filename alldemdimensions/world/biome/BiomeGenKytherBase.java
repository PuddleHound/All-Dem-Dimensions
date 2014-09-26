package alldemdimensions.world.biome;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenVines;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.TerrainLayer;
import alldemdimensions.world.gen.WorldGenKytherCanopyTree;
import alldemdimensions.world.gen.WorldGenKytherCecropia;
import alldemdimensions.world.gen.WorldGenKytherMangrove;
import alldemdimensions.world.gen.WorldGenKytherTree;

public class BiomeGenKytherBase extends BiomeGenMainDimension
{
    
    public BiomeGenKytherBase(int i)
    {
    	super(i, Dimension.kyther);
    	temperature = 1.7F;
        rainfall = 1.3F;
        spawnableMonsterList.clear();
        spawnableCreatureList.clear();
        spawnableWaterCreatureList.clear();
        spawnableCaveCreatureList.clear(); 
    }
    
    @Override
    public int getSkyColorByTemp(float f)
    {
        return 0x00683b;
    }
    
    public BiomeGenKytherBase setMinMaxCrustHeight(float f, float f1)
    {
        minCrustHeight = f;
        maxCrustHeight = f1;
        return this;
    }
       
    @Override
    public double getTerrainNoise(TerrainLayer layer, int i)
    {
    	if(layer == Dimension.kyther.crustLayer)
    	{
    		switch(i)
    		{
    			case 0: return crustNoise3;
    			case 1: return crustNoise4;
    			case 2: return crustNoise1;
    			case 3: return crustNoise2;
    		}
    	} else
    	{
    		switch(i)
    		{
    			case 0: return seaFloorNoise3;
    			case 1: return seaFloorNoise4;
    			case 2: return seaFloorNoise1;
    			case 3: return seaFloorNoise2;
    		}
    	}
    	return super.getTerrainNoise(layer, i);
    }
    
    @Override
    public float getMinHeight(TerrainLayer layer)
    {
    	if(layer == Dimension.kyther.crustLayer)
    	{
    		return minCrustHeight;
    	} else
    	{
    		return minSeaFloorHeight;
    	}
    }
    
    @Override
    public float getMaxHeight(TerrainLayer layer)
    {
    	if(layer == Dimension.kyther.crustLayer)
    	{
    		return maxCrustHeight;
    	} else
    	{
    		return maxSeaFloorHeight;
    	}
    }
    
    @Override
    public float getLowerTerrainOrientation(TerrainLayer layer)
    {
    	if(layer == Dimension.kyther.crustLayer)
    	{
    		return -1F;
    	}
    	return super.getLowerTerrainOrientation(layer);
    }
    
    @Override
    public float getUpperTerrainOrientation(TerrainLayer layer)
    {
    	if(layer == Dimension.kyther.crustLayer)
    	{
    		return -1F;//with this value for floor = possibility for Kyther crust?
    	}
    	return super.getUpperTerrainOrientation(layer);
    }
    
    public double crustNoise1 = 512D;
	public double crustNoise2 = 512D * 6D;
	public double crustNoise3 = 684.412D;//* 8D with maxHeight = 1: marsh with floating islands with stalactites
	public double crustNoise4 = 684.412D * 6D;// * 6D;//2053.236D;
	public double seaFloorNoise1;
	public double seaFloorNoise2;
	public double seaFloorNoise3;
	public double seaFloorNoise4;
	public float minCrustHeight = 4.0F;//lower = flatter surface, fewer holes
	public float maxCrustHeight = 1.5F;
	public float minSeaFloorHeight;
	public float maxSeaFloorHeight;
	
	//trees
    public WorldGenerator canopyTree = new WorldGenKytherCanopyTree();
    public WorldGenerator cecropiaTree = new WorldGenKytherCecropia();
    public WorldGenerator mangroveTree = new WorldGenKytherMangrove();
    public WorldGenerator defaultTree = new WorldGenKytherTree();
    
    //plants
    public WorldGenerator vines = new WorldGenVines();
    public WorldGenerator tallGrass = new WorldGenTallGrass(Blocks.tallgrass, 1);
    public WorldGenerator ferns = new WorldGenTallGrass(Blocks.tallgrass, 2);
	
    public static final BiomeGenKytherBase default_biome = (BiomeGenKytherBase)(new BiomeGenKytherBase(170).setBiomeName("DefaultKytherBiome").setMajorBiome().setDefaultRiverBiome());
    public static final BiomeGenKytherBase default_biome_2 = (BiomeGenKytherBase)(new BiomeGenKytherBase(171).setBiomeName("DefaultKytherBiome2").setMajorBiome());
    
}