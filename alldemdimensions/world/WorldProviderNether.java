package alldemdimensions.world;

import net.minecraft.util.Vec3;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class WorldProviderNether extends WorldProviderMainDimension
{
    
    @Override
    public void registerWorldChunkManager()
    {
        super.registerWorldChunkManager();
        isHellWorld = true;
        hasNoSky = true;
        dimensionId = -1;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public Vec3 getFogColor(float f, float f1)
    {
        return this.worldObj.getWorldVec3Pool().getVecFromPool(0.20000000298023224D, 0.029999999329447746D, 0.029999999329447746D);
    }

    @Override
    protected void generateLightBrightnessTable()
    {
        float f = 0.1F;

        for (int lightLevels = 0; lightLevels <= 15; ++lightLevels)
        {
            float f1 = 1.0F - (float)lightLevels / 15.0F;
            this.lightBrightnessTable[lightLevels] = (1.0F - f1) / (f1 * 3.0F + 1.0F) * (1.0F - f) + f;
        }
    }

    @Override
    public boolean isSurfaceWorld()
    {
        return false;
    }

    @Override
    public boolean canCoordinateBeSpawn(int x, int z)
    {
		for(int y = 32; y < 96; y++)
		{
			if(worldObj.getBlock(x, y, z).getMaterial().isSolid() && !worldObj.getBlock(x, y + 1, z).getMaterial().isSolid())
			{
				return true;
			}
		}
		return false;
	}

    @Override
    public float calculateCelestialAngle(long l, float f)
    {
        return 0.5F;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public boolean doesXZShowFog(int x, int z)
    {
        return false;
    }
    
    @Override
    public String getDimensionName()
    {
        return "Nether";
    }
    
    @Override
    public Dimension getDimension()
    {
        return Dimension.nether;
    }
    
    @Override
    public int getActualHeight()
    {
        return 128;
    }
        
    @Override
    public int getAverageGroundLevel()
    {
        return 48;
    }
    
}
