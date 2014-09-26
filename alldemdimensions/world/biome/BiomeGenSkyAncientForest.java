package alldemdimensions.world.biome;

import alldemdimensions.world.environment.EnumZenithWeather;

public class BiomeGenSkyAncientForest extends BiomeGenSkyBase
{
    public BiomeGenSkyAncientForest(int i)
    {
        super(i);
		temperature = 1.5F;
		rainfall = 1.2F;
		waterColorMultiplier = 0x00ffcc;
		minHeight = -24.0F;
		maxHeight = 0.2F;
		minMtnHeight = -1.8F;
		maxMtnHeight = 0.8F;
		minSnowHeight = 256;
		worldGen[GEN_TREE].put(ancientTree, 128);
		worldGen[GEN_TREE].put(willowTree, 2);
		worldGen[GEN_TREE].put(chestnutTree, 64);
		worldGen[GEN_STRUCTURE].put(ruins, 16);
		worldGen[GEN_STRUCTURE].put(dungeon, -8);
		worldGen[GEN_PLANT].put(mushroom, 2);
		worldGen[GEN_PLANT].put(fern, 4);
		worldGen[GEN_PLANT].put(honeysuckle, 4);
    }
	
    @Override
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.RAIN;
	}
}
