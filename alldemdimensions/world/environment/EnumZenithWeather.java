package alldemdimensions.world.environment;

import net.minecraft.util.ResourceLocation;

public enum EnumZenithWeather
{
	RAIN("teardropRain"),
	SLEET("sleet"), //or whatever the precipitation is in cold biomes, if any
	FLOWERS("flowers");

    private EnumZenithWeather(String s)
	{
		texture = new ResourceLocation("alldemdimensions", "textures/sky/" + s + ".png");
    }
	
	public ResourceLocation getTexture()
	{
		return texture;
	}
	
	private ResourceLocation texture;
}
