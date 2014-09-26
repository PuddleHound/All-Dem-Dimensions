package alldemdimensions.world;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.WorldChunkManagerHell;
import alldemdimensions.world.biome.BiomeGenEnder;

public class WorldProviderEnder extends WorldProviderMainDimension
{
	
    @Override
	public Dimension getDimension()
	{
		return Dimension.ender;
	}
	
    /*@Override
	public boolean renderEndSky()
	{
		return true;
	}
	
    @Override
	public boolean renderVoidFog()
    {
        return false;
    }
	
    @Override
	public boolean renderClouds()
	{
		return false;
	}*/

    @Override
    public void registerWorldChunkManager()
    {
        worldChunkMgr = new WorldChunkManagerHell(BiomeGenEnder.ender, 0.5F);
        hasNoSky = true;
    }

    @Override
    public float calculateCelestialAngle(long l, float f)
    {
        return 0.0F;
    }

    @Override
    public float[] calcSunriseSunsetColors(float f, float f1)
    {
        return null;
    }

    @Override
    public Vec3 getFogColor(float f, float f1)
    {
        int i = 0x8080a0;
        float f2 = MathHelper.cos(f * 3.141593F * 2.0F) * 2.0F + 0.5F;
        if (f2 < 0.0F)
        {
            f2 = 0.0F;
        }
        if (f2 > 1.0F)
        {
            f2 = 1.0F;
        }
        float f3 = (float)(i >> 16 & 0xff) / 255F;
        float f4 = (float)(i >> 8 & 0xff) / 255F;
        float f5 = (float)(i & 0xff) / 255F;
        f3 *= f2 * 0.0F + 0.15F;
        f4 *= f2 * 0.0F + 0.15F;
        f5 *= f2 * 0.0F + 0.15F;
        return Vec3.createVectorHelper(f3, f4, f5);
    }

    @Override
	public boolean isSkyColored()
    {
        return false;
    }
	
    /*@Override
	public int respawnInDimension()
	{
		return Dimension.ender.dimensionId;
	}*/

    @Override
    public float getCloudHeight()
    {
        return 8F;
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

    /*public int func_46066_g()
    {
        return 50;
    }*/
	
    @Override
	protected void generateLightBrightnessTable()
    {
        float f = 0.1F;
        for (int i = 0; i <= 15; i++)
        {
            float f1 = 1.0F - (float)i / 15F;
            lightBrightnessTable[i] = ((1.0F - f1) / (f1 * 3F + 1.0F)) * (1.0F - f) + f;
        }
    }   

}
