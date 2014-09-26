package alldemdimensions.world.biome; import alldemdimensions.world.environment.EnumZenithWeather;

public class BiomeGenSkyLake extends BiomeGenSkyBase
{
    public BiomeGenSkyLake(int i)
    {
        super(i);
    }
	
    @Override
	public EnumZenithWeather getWeatherType()
	{
		return EnumZenithWeather.RAIN;
	}
}
