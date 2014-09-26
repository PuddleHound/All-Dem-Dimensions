package alldemdimensions.world;

import net.minecraft.world.WorldProviderSurface;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraftforge.client.IRenderHandler;
import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.world.biome.WorldChunkManagerBase;
import alldemdimensions.world.gen.ChunkProviderBase;

public abstract class WorldProviderMainDimension extends WorldProviderSurface
{
    
    public abstract Dimension getDimension();
    	
	public void registerWorldChunkManager()
    {
		worldChunkMgr = new WorldChunkManagerBase(worldObj);
	}
	
	public boolean canRespawnHere()
	{
		return true;
	}
	   
    @Override
    public IRenderHandler getSkyRenderer()
    {
        return AllDemDimensionsClient.skyRenderer;
    }
        
    @Override
    public IRenderHandler getCloudRenderer()
    {
    	return AllDemDimensionsClient.skyRenderer.cloudRenderer;
    }
        
    @Override
    public IChunkProvider createChunkGenerator()
    {
    	return new ChunkProviderBase(worldObj, worldObj.getSeed(), getDimension());
    }

    @Override
    public String getSaveFolder()
    {
    	return getDimension().name;
    }
    
    @Override
    public String getDimensionName()
    {
    	return getDimension().name;
    }
}
