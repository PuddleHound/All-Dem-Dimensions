package alldemdimensions.world;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.client.IRenderHandler;//SkyProvider;
import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.world.biome.BiomeGenMainDimension;
import alldemdimensions.world.biome.BiomeGenSkyBase;
import alldemdimensions.world.environment.EnumZenithWeather;

public class WorldProviderSkylands extends WorldProviderMainDimension
{
	
    @Override
	public Dimension getDimension()
	{
		return Dimension.zenith;
	}	
	
    @Override
	public boolean isSkyColored()
    {
        return false;
    }
	
    @Override
	public float getStarBrightness(float f)
    {
        return 1.0F;
    }
	
    /*@Override
	public boolean renderVoidFog()
	{
		return false;
	}*/
	
    @Override
	public float getCloudHeight()
    {
        return 2F;
    }
	
    @Override
	public boolean canCoordinateBeSpawn(int i, int j)
    {
        Block k = worldObj.getTopBlock(i, j);
        if (k == Blocks.air)
        {
            return false;
        }
        else
        {
            return k.getMaterial().isSolid();
        }
    }
	
    /*@Override
	public boolean darkenSkyDuringRain()
    {
        return false;
    }*/
	
    @Override
	public int getAverageGroundLevel()
    {
		return 0;
	}
	
    @Override
	public double getHorizon()
    {
		return -64.0D;
	}
	
    @Override
	public double getVoidFogYFactor()
    {
		return 1.0D;
	}
	
    @Override
	public boolean getWorldHasVoidParticles()
    {
		return false;
	}
	
    @Override
    public Vec3 getSkyColor(Entity cameraEntity, float partialTicks)
    {
		float f = worldObj.getCelestialAngle(partialTicks);
        float f1 = MathHelper.cos(f * (float)Math.PI * 2.0F) * 2.0F + 0.5F;

        if (f1 < 0.0F)//0.0F
        {
            f1 = 0.0F;
        }

        if (f1 > 1.0F)
        {
            f1 = 1.0F;
        }

        int f2 = MathHelper.floor_double(cameraEntity.posX);
        int f3 = MathHelper.floor_double(cameraEntity.posZ);
        BiomeGenBase biome = worldObj.getBiomeGenForCoords(f2, f3);
        float f4 = biome.getFloatTemperature(f2, MathHelper.floor_double(cameraEntity.posY), f3);
        int color = 0xffffff;
        float red = 0.7F;
        float green = 0.8F;
        float blue = 1.0F;
        if(biome instanceof BiomeGenMainDimension)
        {
            BiomeGenMainDimension addBiome = (BiomeGenMainDimension)biome;
            red = addBiome.skyColorRed;
            green = addBiome.skyColorGreen;
            blue = addBiome.skyColorBlue;
        }
		if(worldObj.getRainStrength(partialTicks) > 0)//and current biome is appropriate
		{
			if(biome instanceof BiomeGenSkyBase)
			{
				EnumZenithWeather weatherType = ((BiomeGenSkyBase)biome).getWeatherType();
				if(weatherType == EnumZenithWeather.FLOWERS)
				{
					red += worldObj.getRainStrength(partialTicks) * 0.1F;
				}
				if(weatherType == EnumZenithWeather.RAIN)
				{
					green += worldObj.getRainStrength(partialTicks) * 0.1F;
				}
			}
		}
		
		float worldTime = (float)(getWorldTime() % 48000);
		if(worldTime > 6000 && worldTime < 18000)
		{
			if(worldTime < 12000)
			{
				worldTime -= 6000;
				red = 0.7F + (worldTime / 20000F);
				green = 0.8F + (worldTime / 30000F);
			} else
			{
				worldTime -= 12000;
				red = 1.0F - (worldTime / 20000F);
				green = 1.0F - (worldTime / 30000F);
			}
		}
		
        red *= (f1 + ((1.0F - f1) * 0.5F));
        green *= (f1 + ((1.0F - f1) * 0.5F));
        blue *= (f1 + ((1.0F - f1) * 2F));
		if(blue > 1.0F){blue = 1.0F;}
        float f5 = 0F;//worldObj.getRainStrength(partialTicks);
        float f6;
        float f7;

        if (f5 > 0.0F)
        {
            f6 = (red * 0.3F + green * 0.59F + blue * 0.11F) * 0.6F;
            f7 = 1.0F - f5 * 0.75F;
            red = red * f7 + f6 * (1.0F - f7);
            green = green * f7 + f6 * (1.0F - f7);
            blue = blue * f7 + f6 * (1.0F - f7);
        }

        f6 = 0F;//worldObj.getWeightedThunderStrength(partialTicks);

        if (f6 > 0.0F)
        {
            f7 = (red * 0.3F + green * 0.59F + blue * 0.11F) * 0.2F;
            float f8 = 1.0F - f6 * 0.75F;
            red = red * f8 + f7 * (1.0F - f8);
            green = green * f8 + f7 * (1.0F - f8);
            blue = blue * f8 + f7 * (1.0F - f8);
        }

        if (worldObj.lastLightningBolt > 0)
        {
            f7 = (float)worldObj.lastLightningBolt - partialTicks;

            if (f7 > 1.0F)
            {
                f7 = 1.0F;
            }

            f7 *= 0.45F;
            red = red * (1.0F - f7) + 0.8F * f7;
            green = green * (1.0F - f7) + 0.8F * f7;
            blue = blue * (1.0F - f7) + 1.0F * f7;
        }

        return worldObj.getWorldVec3Pool().getVecFromPool((double)red, (double)green, (double)blue);
	}
	
    @Override
	public boolean doesXZShowFog(int x, int z)
    {
		return false;
    }
	
    @Override
	protected void generateLightBrightnessTable()//use for brightness variation
    {
        float f = 0.0F;
		int worldTime = (int)(getWorldTime() % 48000);
		if(worldTime > 6000 && worldTime < 18000)
		{
			if(worldTime < 12000)
			{
				f = ((float)Math.pow(worldTime * 0.001F, 4) * 0.0000025F);
			} else
			{
				f = (80 / (float)Math.pow(worldTime * 0.001F, 3));
			}
		}

        for (int lightLevel = 0; lightLevel <= 15; ++lightLevel)
        {
            float f1 = 1.0F - (float)lightLevel / 15.0F;
            this.lightBrightnessTable[lightLevel] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }
    
	//high day -- fog, brightness, sky color
	//24000 ticks in a full normal day; 48000 in a full Zenith day
    @Override
	public float calculateCelestialAngle(long worldTime, float partialTicks)//0 = dawn; 24000 = dusk; 6000-18000 = high day
    {
		generateLightBrightnessTable();
        int timeOfDay = (int)(worldTime % 48000L);//determining the time of day from worldTime
        float angle = ((float)timeOfDay + partialTicks) / 48000.0F - 0.25F;//adding in elapsed render ticks and dividing by 48000 for the celestial angle

        if (angle < 0.0F)
        {
            angle++;// = 0.0F;//+= 0.5F;
        }
		//0.25 - 0.75 = night?
        if (angle > 1.0F)
        {
            angle--;// = 1.0F;//-= 0.5F;
        }

        float angle1 = angle;
        angle = 1.0F - (float)((Math.cos((double)angle * Math.PI) + 1.0D) / 2.0D);
        angle = angle1 + (angle - angle1) / 3.0F;

		if(angle < /*0.375F*/0.5F)
		{
			angle *= 0.625F;
		} else
		{
			angle = (angle * 0.625F) + 0.375F;
		}
		if(angle > 0.275F && angle < 0.725F)
		{
			angle = 0.275F;
		}
        return angle;
    }
	
    @Override
	public float[] calcSunriseSunsetColors(float f, float f1)
    {
		return null;
	}
	
    @Override
	public Vec3 getFogColor(float f, float f1)
    {
        float f2 = MathHelper.cos(f * (float)Math.PI * 2.0F) * 2.0F + 0.5F;
		
        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }

        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }

        float red = 0.7F;//529412F;
        float green = 0.8F;//4705883F;
        float blue = 1.0F;
        EntityLivingBase player = Minecraft.getMinecraft().renderViewEntity;
        BiomeGenBase biomegenbase = worldObj.getBiomeGenForCoords((int)Math.floor(player.posX), (int)Math.floor(player.posZ));
        if(biomegenbase instanceof BiomeGenMainDimension)
        {
            BiomeGenMainDimension biome = (BiomeGenMainDimension)biomegenbase;
            red = biome.skyColorRed;
            green = biome.skyColorGreen;
            blue = biome.skyColorBlue;
        }
		if(worldObj.getRainStrength(f1) > 0)
		{
			//EntityLivingBase player = Minecraft.getMinecraft().renderViewEntity;
			BiomeGenBase biome = worldObj.getBiomeGenForCoords(MathHelper.floor_double(player.posX), MathHelper.floor_double(player.posZ));
			EnumZenithWeather weatherType = null;
			if(biome instanceof BiomeGenSkyBase)
			{
				weatherType = ((BiomeGenSkyBase)biome).getWeatherType();
				if(weatherType == EnumZenithWeather.FLOWERS)
				{
					red += worldObj.getRainStrength(f1) * 0.1F;
				}
				if(weatherType == EnumZenithWeather.RAIN)
				{
					green += worldObj.getRainStrength(f1) * 0.1F;
				}
			}
		}
        red *= (f2 + ((1.0F - f2) * 0.075F)/* * 0.5F*/) * 0.94F + 0.06F;
        green *= (f2 + ((1.0F - f2) * 0.075F)/* * 0.5F*/) * 0.94F + 0.06F;
        blue *= (f2 + ((1.0F - f2) * 0.3F)/* * 2.0F*/) * 0.91F + 0.09F;
        return this.worldObj.getWorldVec3Pool().getVecFromPool((double)red, (double)green, (double)blue);
    }
    
}