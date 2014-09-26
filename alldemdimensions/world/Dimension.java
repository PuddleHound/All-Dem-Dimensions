package alldemdimensions.world;

import java.util.ArrayList;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.gen.MapGenBase;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.IDataHandler;
import alldemdimensions.util.NBTDataHandler;
import alldemdimensions.world.biome.BiomeGenMainDimension;
import alldemdimensions.world.biome.BiomeGenerator;

public class Dimension
{

	public Dimension(int i, Block j, Block k, int l, int i1, String s, byte b)
	{
		dimensionId = i;
		portalBlockId = j;
		portalFrameId = k;
		portalFrameMeta = l;
		heightLimit = i1;
		name = s;
		displayName = name;
		id = b;
		if(i != -81)//ender disabled
		{
			allDimensions.add(this);
		}
	}
	
	public static void setWorldsAndCreateTeleporters(boolean unused)
	{
		MinecraftServer server = MinecraftServer.getServer();
		for(Dimension dim : allDimensions)
		{
			dim.world = server.worldServerForDimension(dim.dimensionId);
			int index = 0;
			Teleporter teleporter;
			for(Dimension dim1 : allDimensions)
			{
				teleporter = new TeleporterMainDimensions(dim.world, dim1.dimensionId, dim.dimensionId);
				dim.teleporters[index] = teleporter;
				dim.world.customTeleporters.add(teleporter);
				index++;
			}
		}
	}
	
	public static Dimension getDimensionForPortal(Block id)
	{
		for(Dimension dim : allDimensions)
		{
			if(dim.portalBlockId == id)
			{
				return dim;
			}
		}
		return null;
	}
	
	public static Dimension getDimensionForId_MC(int id)
	{
		for(Dimension dim : allDimensions)
		{
			if(dim.dimensionId == id)
			{
				return dim;
			}
		}
		return null;
	}
	
	public static Dimension getDimensionForId_ADD(int id)
	{
		for(Dimension dim : allDimensions)
		{
			if(dim.id == id)
			{
				return dim;
			}
		}
		return null;
	}
	
	public Teleporter getTeleporterForId(int id)
	{
		for(Teleporter teleporter : teleporters)
		{
			if(((TeleporterMainDimensions)teleporter).oldDimension == id)
			{
				return teleporter;
			}
		}
		return null;
	}
		
	public void onWorldTick(WorldServer world)
	{
	}
        
    public boolean isValidForWorldGen(World world, int i, int j, int k)
    {
        return true;
    }
        
	public int dimensionId;
	public Block portalBlockId;
	public Block portalFrameId;
	public int portalFrameMeta;
	public int heightLimit;
	public String name;
	public byte id;
	public Teleporter[] teleporters = new Teleporter[6];
	public WorldServer world;
	public int[] spawnPoint;
	public static ArrayList<Dimension> allDimensions = new ArrayList<Dimension>();
    public BiomeGenerator biomeGenerator = new BiomeGenerator();
    public BiomeGenMainDimension defaultBiome;
    public ArrayList<TerrainLayer> terrainLayers = new ArrayList<TerrainLayer>();
    public ArrayList<MapGenBase> mapGenFeatures = new ArrayList<MapGenBase>();
    public String displayName;
	
	public static final Dimension overworld = new Dimension(0, AllDemDimensions.earthPortal, AllDemDimensions.bronzeBlock, 0, 128, "Overworld", (byte)0);
	public static final DimensionZenith zenith = (DimensionZenith)(new DimensionZenith(-80, AllDemDimensions.skyPortal, AllDemDimensions.crystal, 0, 128, "Zenith", (byte)1));
	public static final DimensionNether nether = new DimensionNether(-1, Blocks.portal, Blocks.obsidian, 0, 128, "Hell", (byte)2);
	public static final DimensionKyther kyther = new DimensionKyther(-82, AllDemDimensions.waterPortal, Blocks.lapis_block, 0, 128, "Kyther", (byte)3);
	public static final Dimension ender = new Dimension(-81, AllDemDimensions.enderPortal, AllDemDimensions.crystal, 0, 128, "Ender", (byte)4);
        
}
