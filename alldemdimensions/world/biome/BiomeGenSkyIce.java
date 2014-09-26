package alldemdimensions.world.biome;

import alldemdimensions.world.environment.EnumZenithWeather;

public class BiomeGenSkyIce extends BiomeGenSkyBase
{
    public BiomeGenSkyIce(int i)
    {
        super(i);
		temperature = 0.1F;
		rainfall = 0.1F;
		worldGen[GEN_TREE].put(iceTree, 128);
		worldGen[GEN_TREE].put(chestnutTree, 0);
		worldGen[GEN_PLANT].put(orchid, -12);
		worldGen[GEN_LAST].put(thermal, -100);
		worldGen[GEN_FIRST].put(icicle, 100);
		worldGen[GEN_MISC].put(pond, 0);
		worldGen[GEN_MISC].put(frozenPond, -16);
    }
		
    @Override
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.SLEET;
	}
    
}
