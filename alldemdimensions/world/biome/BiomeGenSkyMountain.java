package alldemdimensions.world.biome;

import alldemdimensions.world.environment.EnumZenithWeather;

public class BiomeGenSkyMountain extends BiomeGenSkyBase
{
    public BiomeGenSkyMountain(int i, boolean flag)
    {
        super(i);
		if(flag)
		{
			minHeight = -24.0F;
			maxHeight = 1.0F;
			minMtnHeight = -0.5F;///-1.5F
			maxMtnHeight = 1.0F;
		} else
		{
			minHeight = -24.0F;
			maxHeight = 0.2F;
			minMtnHeight = -1.0F;
			maxMtnHeight = 1.8F;
		}
		temperature = 0.1F;
		rainfall = 0.1F;
		worldGen[GEN_STRUCTURE].put(dungeon, -64);
		worldGen[GEN_MISC].put(pond, 0);
		worldGen[GEN_MISC].put(frozenPond, -50);
		worldGen[GEN_TREE].put(chestnutTree, 2);
		worldGen[GEN_TREE].put(iceTree, 5);
		worldGen[GEN_LAST].put(thermal, -100);
    }
	
    @Override
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.SLEET;
	}
}
