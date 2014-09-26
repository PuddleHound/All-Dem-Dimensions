package alldemdimensions.world.biome;

import alldemdimensions.world.environment.EnumZenithWeather;

public class BiomeGenSkyBambooForest extends BiomeGenSkyBase
{
    public BiomeGenSkyBambooForest(int i)
    {
        super(i);
		temperature = 1.5F;
		rainfall = 1.5F;
		waterColorMultiplier = 0x00ffcc;
		/*minHeight = -24.0F;
		maxHeight = 0.2F;
		minMtnHeight = -0.4F;
		maxMtnHeight = 1.2F;
		terrainNoiseMtn3 = 5475.296;//2737.648D;//684.412D;
		terrainNoiseMtn4 = 5475.296;//2737.648D;//684.412D;
                */
		minSnowHeight = 256;
		worldGen[GEN_TREE].put(arrowTree, 48);
		//worldGen[GEN_TREE].put(bonsaiTree, 16);
		worldGen[GEN_TREE].put(bamboo, 16);
		worldGen[GEN_PLANT].put(honeysuckle, 16);
        worldGen[GEN_PLANT].put(redstoneFlower, -2);
        setSkyColor(0.69F, 0.95F, 0.88F);
    }
	
    @Override
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.RAIN;
	}
	
    @Override
	public int getBiomeFoliageColor(int i, int j, int k)
    {
		return 0x00a835;
    }
	
    @Override
	public int getBiomeGrassColor(int i, int j, int k)
    {
		return 0xa4d98c;
	}
	
}
