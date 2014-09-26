package alldemdimensions.world;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import alldemdimensions.world.biome.BiomeGenKytherBase;

public class WorldProviderKyther extends WorldProviderMainDimension
{
    public WorldProviderKyther()
    {
    }
    
    @Override
    protected void generateLightBrightnessTable()//temporary
    {
        Arrays.fill(lightBrightnessTable, 1F);
    }
	
    @Override
	public Dimension getDimension()
	{
		return Dimension.kyther;
	}
	
    /*
    @Override
	public boolean renderVoidFog()
	{
		return false;
	}*/
	
    @Override
	public float getCloudHeight()
    {
        return 224F;
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
                
}
