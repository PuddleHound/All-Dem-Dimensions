package alldemdimensions.world.biome;

import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.environment.EnumZenithWeather;

public class BiomeGenSkyJungle extends BiomeGenSkyBase
{
    public BiomeGenSkyJungle(int i)
    {
        super(i);
		temperature = 2.0F;
		rainfall = 2.0F;
		minSnowHeight = 256;
		minHeight = -24.0F;
		maxHeight = 0.2F;
		minMtnHeight = 1.0F;
		maxMtnHeight = -1.0F;
		worldGen[GEN_TREE].put(corkscrewTree, 64);
		worldGen[GEN_TREE].put(jungleTree, 32);
		worldGen[GEN_PLANT].put(fern, 8);
		worldGen[GEN_PLANT].put(hibiscus, 3);
		worldGen[GEN_PLANT].put(orchid, 3);
		worldGen[GEN_PLANT].put(anthurium, 3);
		worldGen[GEN_PLANT].put(honeysuckle, 128);
		worldGen[GEN_PLANT].put(nectarfall, 25);
		worldGen[GEN_PLANT].put(waterfall, 100);
    }
	
    @Override
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.RAIN;
	}
}
