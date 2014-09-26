package alldemdimensions.world.biome;

import alldemdimensions.AllDemDimensions;

public class BiomeGenSkyHoney extends BiomeGenSkyBase
{
    public BiomeGenSkyHoney(int i)
    {
        super(i);
		topBlock = AllDemDimensions.waxGrass;
		temperature = 1.0F;
		rainfall = 0.8F;
		minSnowHeight = 256;
		worldGen[GEN_TREE].put(hugeTree, 8);
		worldGen[GEN_PLANT].put(hibiscus, 2);
		worldGen[GEN_PLANT].put(delphinium, 2);
		worldGen[GEN_PLANT].put(anthurium, 2);
		worldGen[GEN_PLANT].put(orchid, 2);
		worldGen[GEN_PLANT].put(honeysuckle, 8);
		worldGen[GEN_STRUCTURE].put(beeHive, 16);
		worldGen[GEN_ORE].put(honeyfall, 500);
		worldGen[GEN_MISC].put(honeyPond, -4);
		worldGen[GEN_MISC].put(pond, 0);
    }
	
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0x61ba3b;
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
		return 0x89de8c;
	}
}
