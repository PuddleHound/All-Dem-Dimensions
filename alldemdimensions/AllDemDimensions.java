package alldemdimensions;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.command.ICommandSender;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.storage.SaveHandler;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.BonemealEvent;
import alldemdimensions.block.AstronomyManager;
import alldemdimensions.block.BlockADDFlowingLiquid;
import alldemdimensions.block.BlockADDOre;
import alldemdimensions.block.BlockADDSlab;
import alldemdimensions.block.BlockADDStairs;
import alldemdimensions.block.BlockADDStaticLiquid;
import alldemdimensions.block.BlockADDStorage;
import alldemdimensions.block.BlockAlchemyTable;
import alldemdimensions.block.BlockAmberChest;
import alldemdimensions.block.BlockBasket;
import alldemdimensions.block.BlockBeehive;
import alldemdimensions.block.BlockBubble;
import alldemdimensions.block.BlockCandle;
import alldemdimensions.block.BlockCandlestick;
import alldemdimensions.block.BlockColumn;
import alldemdimensions.block.BlockCrystal;
import alldemdimensions.block.BlockEasel;
import alldemdimensions.block.BlockEnderCrystal;
import alldemdimensions.block.BlockFloatingWire;
import alldemdimensions.block.BlockHoneysuckle;
import alldemdimensions.block.BlockKytherWater;
import alldemdimensions.block.BlockLightSensor;
import alldemdimensions.block.BlockMimicstone;
import alldemdimensions.block.BlockModified;
import alldemdimensions.block.BlockNetherCrystal;
import alldemdimensions.block.BlockNetherFungus;
import alldemdimensions.block.BlockNetherStone;
import alldemdimensions.block.BlockPortalBase;
import alldemdimensions.block.BlockRedstoneValve;
import alldemdimensions.block.BlockSkyCloud;
import alldemdimensions.block.BlockSkyFruit;
import alldemdimensions.block.BlockSkyLimestone;
import alldemdimensions.block.BlockSkySapling;
import alldemdimensions.block.BlockStellarAltar;
import alldemdimensions.block.BlockThermal;
import alldemdimensions.block.BlockWaxGrass;
import alldemdimensions.block.BlockWindTube;
import alldemdimensions.block.ContainerAlchemy;
import alldemdimensions.block.ContainerEasel;
import alldemdimensions.block.GuiAlchemy;
import alldemdimensions.block.GuiEasel;
import alldemdimensions.block.GuiStellarAltar;
import alldemdimensions.block.Plant;
import alldemdimensions.block.TileEntityAlchemyTable;
import alldemdimensions.block.TileEntityAmberChest;
import alldemdimensions.block.TileEntityEasel;
import alldemdimensions.block.TileEntityLightSensor;
import alldemdimensions.block.TileEntityModified;
import alldemdimensions.block.TileEntityRedstoneValve;
import alldemdimensions.block.TileEntitySoundRecorder;
import alldemdimensions.block.TileEntityWindTube;
import alldemdimensions.block.Tree;
import alldemdimensions.edit.PlayerAllDemDimensions;
import alldemdimensions.edit.ReflectionManager;
import alldemdimensions.entity.EntityAmpfly;
import alldemdimensions.entity.EntityAurora;
import alldemdimensions.entity.EntityBadger;
import alldemdimensions.entity.EntityBee;
import alldemdimensions.entity.EntityBluekite;
import alldemdimensions.entity.EntityButterfly;
import alldemdimensions.entity.EntityCloud;
import alldemdimensions.entity.EntityCloudGolem;
import alldemdimensions.entity.EntityCustomPainting;
import alldemdimensions.entity.EntityEnderwraith;
import alldemdimensions.entity.EntityGust;
import alldemdimensions.entity.EntityHeliumSlime;
import alldemdimensions.entity.EntityHotAirBalloon;
import alldemdimensions.entity.EntitySkyknight;
import alldemdimensions.entity.EntitySycopter;
import alldemdimensions.entity.EntityTornado;
import alldemdimensions.entity.EntityWind;
import alldemdimensions.item.EnumToolMaterialADD;
import alldemdimensions.item.ItemADDAxe;
import alldemdimensions.item.ItemADDPickaxe;
import alldemdimensions.item.ItemADDShovel;
import alldemdimensions.item.ItemADDSword;
import alldemdimensions.item.ItemADDTool;
import alldemdimensions.item.ItemBlockADDSlab;
import alldemdimensions.item.ItemBlockBeehive;
import alldemdimensions.item.ItemBlockCrystal;
import alldemdimensions.item.ItemBlockNetherCrystal;
import alldemdimensions.item.ItemBlockNetherStone;
import alldemdimensions.item.ItemBlockSkyCloud;
import alldemdimensions.item.ItemBlockSkyLimestone;
import alldemdimensions.item.ItemBlockSkyPlant;
import alldemdimensions.item.ItemCanvas;
import alldemdimensions.item.ItemCrossbow;
import alldemdimensions.item.ItemFloatingRedstone;
import alldemdimensions.item.ItemHammerAndChisel;
import alldemdimensions.item.ItemHotAirBalloon;
import alldemdimensions.item.ItemMatch;
import alldemdimensions.item.ItemPaintBottle;
import alldemdimensions.item.ItemTelescope;
import alldemdimensions.item.ItemWindBlower;
import alldemdimensions.util.IDataHandler;
import alldemdimensions.util.NBTDataHandler;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.WorldProviderKyther;
import alldemdimensions.world.WorldProviderNether;
import alldemdimensions.world.WorldProviderSkylands;
import alldemdimensions.world.biome.BiomeGenMainDimension;
import alldemdimensions.world.environment.Planet;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

@Mod(modid = "alldemdimensions", name = "All Dem Dimensions", version = "Alpha 0.4.0")
public class AllDemDimensions implements IGuiHandler, IDataHandler
{
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent fmlpreinitializationevent)
    {
        registerBlocksAndItems();
        System.out.println("[ADD] Registered blocks and items.");
        registerTileEntities();
        registerEntities();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent fmlinitializationevent)
    {
		instance = this;
		channel = NetworkRegistry.INSTANCE.newEventDrivenChannel("ADDChannel");//network code isn't working currently
		alldemdimensions.network.ADDPacket.registerChannels();
		proxyInstance.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(this, this);
		MinecraftForge.EVENT_BUS.register(new NBTDataHandler());
		MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new PlayerAllDemDimensions());
        FMLCommonHandler.instance().bus().register(this);
        BiomeGenMainDimension.initBiomes();
		registerCrafting();
		registerWorldGeneration();
        DimensionManager.unregisterProviderType(Dimension.nether.dimensionId);
        DimensionManager.unregisterDimension(Dimension.nether.dimensionId);
        DimensionManager.registerProviderType(Dimension.nether.dimensionId, WorldProviderNether.class, false);
		DimensionManager.registerProviderType(Dimension.zenith.dimensionId, WorldProviderSkylands.class, false);
		//DimensionManager.registerProviderType(Dimension.ender.dimensionId, WorldProviderEnder.class, false);//Ender disabled
		DimensionManager.registerProviderType(Dimension.kyther.dimensionId, WorldProviderKyther.class, false);
        DimensionManager.registerDimension(Dimension.nether.dimensionId, Dimension.nether.dimensionId);
		DimensionManager.registerDimension(Dimension.zenith.dimensionId, Dimension.zenith.dimensionId);
		//DimensionManager.registerDimension(Dimension.ender.dimensionId, Dimension.ender.dimensionId);
		DimensionManager.registerDimension(Dimension.kyther.dimensionId, Dimension.kyther.dimensionId);
		//AlchemyManager.init();//alchemy is in the process of getting an overhaul
		AstronomyManager.init();
		Planet.initPlanets();
		NBTDataHandler.register(this);
    }
    
    //Some debug code
    @Mod.EventHandler
	public void onServerStarting(cpw.mods.fml.common.event.FMLServerStartingEvent event)
	{
    	((net.minecraft.command.ServerCommandManager)event.getServer().getCommandManager()).registerCommand(new CommandTerrainNoise());
	}
    public static class CommandTerrainNoise extends net.minecraft.command.CommandBase
    {
    	public String getCommandName()
        {
            return "noise";
        }
    	
        public String getCommandUsage(ICommandSender icommandsender)
        {
            return "commands.noise.usage";
        }

        public void processCommand(ICommandSender commandSender, String[] stringArray)
        {
        	System.out.println("processing command");
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise1 = 512D * Integer.parseInt(stringArray[0]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise2 = 512D * Integer.parseInt(stringArray[1]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise3 = 684.412D * Integer.parseInt(stringArray[2]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise4 = 684.412D * Integer.parseInt(stringArray[3]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.minCrustHeight = Float.parseFloat(stringArray[4]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.maxCrustHeight = Float.parseFloat(stringArray[5]);
        	
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome_2.crustNoise1 = 512D * Integer.parseInt(stringArray[0]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome_2.crustNoise2 = 512D * Integer.parseInt(stringArray[1]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome_2.crustNoise3 = 684.412D * Integer.parseInt(stringArray[2]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome_2.crustNoise4 = 684.412D * Integer.parseInt(stringArray[3]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome_2.minCrustHeight = Float.parseFloat(stringArray[4]);
        	alldemdimensions.world.biome.BiomeGenKytherBase.default_biome_2.maxCrustHeight = Float.parseFloat(stringArray[5]);
        	
        	System.out.println("[ADD] New terrain noise:");
        	System.out.println(alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise1);
        	System.out.println(alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise2);
        	System.out.println(alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise3);
        	System.out.println(alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise4);
        	System.out.println(alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.minCrustHeight);
        	System.out.println(alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.maxCrustHeight);
        	
        	notifyAdmins(commandSender, "New terrain noise values: " +
        			alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise1 + ", " +
        			alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise2 + ", " +
        			alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise3 + ", " +
        			alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.crustNoise4 + ", " +
        			alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.minCrustHeight + ", " +
        			alldemdimensions.world.biome.BiomeGenKytherBase.default_biome.maxCrustHeight);
        }

		@Override
		public int compareTo(Object object)
		{
			return 0;
		}

    }
    
    @Mod.EventHandler
	public void onServerStarted(cpw.mods.fml.common.event.FMLServerStartedEvent fmlserverstartedevent)
	{
    	Dimension.setWorldsAndCreateTeleporters(false);
	}
    
	/**Was thinking of implementing colored lighting in ADD. Not yet though.**/
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent fmlpostinitializationevent)
    {
    	try
    	{
    		Class.forName("coloredlightscore.src.api.CLApi");
    		coloredLightsLoaded = true;
    	}
    	catch(ClassNotFoundException e)
    	{
    		System.err.println("[ADD] Colored lights mod is not installed. Any colored light sources in All Dem Dimensions will not work.");
    	}
    	
    }
	
	@SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
		World world = event.world;
		world.theProfiler.startSection("ADD world tick");
		if(world instanceof WorldServer)
		{
	        WorldServer worldserver = (WorldServer)world;
			Dimension dim = Dimension.getDimensionForId_MC(world.provider.dimensionId);
			if(dim != null)
			{
				dim.onWorldTick(worldserver);
			}
		}
		world.theProfiler.endSection();
    }
	
	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		event.player.worldObj.theProfiler.startSection("ADD player tick");//root.tick.level.entities.regular.tick
		if(event.player instanceof EntityPlayerMP)
		{
			customPlayer.onServerTick((EntityPlayerMP)event.player);
		}
		event.player.worldObj.theProfiler.endSection();
	}
      
	@SubscribeEvent
	public void onPlayerUseBonemeal(BonemealEvent event)
	{
		Block block = event.block;
		if(block instanceof BlockSkySapling)
		{
            if(!event.world.isRemote)
            {
                ((BlockSkySapling)block).func_149878_d(event.world, event.x, event.y, event.z, event.world.rand);//growTree
            }
		}
	}
	
	@SubscribeEvent
	public void onEntityDropItem(LivingDropsEvent event)
	{
		DamageSource source = event.source;
		if(source != null && source instanceof EntityDamageSource && ((EntityDamageSource)source).getEntity() instanceof EntityPlayerMP)
		{
			EntityPlayerMP player = (EntityPlayerMP)((EntityDamageSource)source).getEntity();
			ItemStack itemstack = player.getCurrentEquippedItem();
			if(itemstack != null && ((itemstack.getItem() instanceof ItemADDTool && ((ItemADDTool)itemstack.getItem()).material == EnumToolMaterialADD.AMBER) ||
				(itemstack.getItem() instanceof ItemADDSword && ((ItemADDSword)itemstack.getItem()).material == EnumToolMaterialADD.AMBER)))
			{
				for(EntityItem entityitem : event.drops)
				{
					if(entityitem != null)
					{
						ItemStack itemstack1 = entityitem.getDataWatcher().getWatchableObjectItemStack(10);
						if(itemstack1 != null)
						{
							player.inventory.addItemStackToInventory(itemstack1);
						}
					}
				}
				event.setCanceled(true);
			}
		}
	}        
        
    @SubscribeEvent
    public void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
    }
	
    @Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if(id == GUI_EASEL && tileentity instanceof TileEntityEasel)
		{
			return new ContainerEasel(player.inventory, (TileEntityEasel)tileentity);
		}
		if(id == GUI_ALCHEMY)
		{
			return new ContainerAlchemy(player.inventory, world, x, y, z);
		}
		if(id == GUI_STELLAR_ALTAR)
		{
			return null;
		}
		return null;
	}

    @Override
    public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z)
	{
		TileEntity tileentity = world.getTileEntity(x, y, z);
		if(id == GUI_EASEL && tileentity instanceof TileEntityEasel)
		{
			return new GuiEasel(player.inventory, (TileEntityEasel)tileentity);
		}
		if(id == GUI_ALCHEMY)
		{
			return new GuiAlchemy(player.inventory, world, x, y, z);
		}
		if(id == GUI_STELLAR_ALTAR)
		{
			return new GuiStellarAltar(player, world, x, y, z);
		}
		return null;
	}
	
    public static Item getItemFromName(String itemName)
    {
        if (Item.itemRegistry.containsKey(itemName))
        {
            return (Item)Item.itemRegistry.getObject(itemName);
        }
        else
        {
            try
            {
                return (Item)Item.itemRegistry.getObjectById(Integer.parseInt(itemName));
            }
            catch (NumberFormatException numberformatexception)
            {
                return null;
            }
        }
    }
        
    public static void print(String message)
    {
        System.out.println("[All Dem Dimensions] " + message);
    }   
            	
	public static AllDemDimensions getInstance()
	{
		return instance;
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.FIELD)
	public @interface RegisterBlock
	{
		public abstract Class<? extends ItemBlock> itemClass() default ItemBlock.class;
		public abstract String displayName() default "Unnamed Block";
	}
	
	private void registerBlocksAndItems()
	{
		Field[] fields = this.getClass().getFields();
		for(Field field : fields)
		{
			RegisterBlock annotation = field.getAnnotation(RegisterBlock.class);
			if(annotation != null)
			{
				Object object = ReflectionManager.getFieldValue(null, field);
				if(object instanceof Block)
				{
					Block block = (Block)object;
					allBlocks.add(block);
					print("REGISTERING BLOCK: " + block.getUnlocalizedName());
					GameRegistry.registerBlock(block, annotation.itemClass(), block.getUnlocalizedName());
				}
			}
		}
		for(String name : Plant.nameToBlock.keySet())
		{
			GameRegistry.registerBlock(Plant.nameToBlock.get(name), ItemBlockSkyPlant.class, name);
		}
		for(Tree tree : Tree.treeList)
		{
			System.out.println("Adding tree: " + tree.name);
			System.out.println("	Log: " + tree.logBlock.getUnlocalizedName());
			System.out.println("	Leaves: " + tree.leavesBlock.getUnlocalizedName());
			System.out.println("	Sapling: " + tree.saplingBlock.getUnlocalizedName());
			GameRegistry.registerBlock(tree.logBlock, tree.name + "Log");
			GameRegistry.registerBlock(tree.leavesBlock, tree.name + "Leaves");
			GameRegistry.registerBlock(tree.saplingBlock, tree.name + "Sapling");
		}
		for(Field field : fields)
		{
			if(field.getType().isAssignableFrom(Item.class))
			{
				Object object = ReflectionManager.getFieldValue(null, field);
				if(object instanceof Item)
				{
					Item item = (Item)object;
					allItems.add(item);
				}
			}
		}
		
		for(Item item : allItems)
		{
			GameRegistry.registerItem(item, item.getUnlocalizedName());
		}
	}
	
	private void registerTileEntities()
	{
		GameRegistry.registerTileEntity(TileEntityWindTube.class, "WindTube");
		GameRegistry.registerTileEntity(TileEntityLightSensor.class, "LightSensor");
		GameRegistry.registerTileEntity(TileEntitySoundRecorder.class, "SoundRecorder");
		GameRegistry.registerTileEntity(TileEntityAmberChest.class, "AmberChest");
		GameRegistry.registerTileEntity(TileEntityEasel.class, "Easel");
		GameRegistry.registerTileEntity(TileEntityRedstoneValve.class, "RedstoneValve");
        GameRegistry.registerTileEntity(TileEntityAlchemyTable.class, "AlchemyTable");
        GameRegistry.registerTileEntity(TileEntityModified.class, "ModifiedBlockEntity");
		//GameRegistry.registerTileEntity(TileEntityStellarAltar.class, "StellarAltar");
	}
	
	private void registerCrafting()
	{
		Block[] stoneBlocks = new Block[]{limestone};
		for(Block block : stoneBlocks)
		{
			GameRegistry.addRecipe(new ItemStack(Blocks.furnace), new Object[] {"###", "# #", "###", Character.valueOf('#'), block});
			GameRegistry.addRecipe(new ItemStack(Items.stone_pickaxe, 1), new Object[] { "ccc", " s ", " s ", Character.valueOf('c'), block, Character.valueOf('s'), Items.stick });
			GameRegistry.addRecipe(new ItemStack(Items.stone_shovel, 1), new Object[] { "c", "s", "s", Character.valueOf('c'), block, Character.valueOf('s'), Items.stick });
			GameRegistry.addRecipe(new ItemStack(Items.stone_axe, 1), new Object[] { "cc", "cs", " s", Character.valueOf('c'), block, Character.valueOf('s'), Items.stick });
			GameRegistry.addRecipe(new ItemStack(Items.stone_sword, 1), new Object[] { "c", "c", "s", Character.valueOf('c'), block, Character.valueOf('s'), Items.stick });
			GameRegistry.addRecipe(new ItemStack(Items.repeater, 1), new Object[] {"#X#", "III", '#', Blocks.redstone_torch, 'X', Items.redstone, 'I', block});
			GameRegistry.addRecipe(new ItemStack(Blocks.stone_button, 1), new Object[] {"#", '#', block});
			GameRegistry.addRecipe(new ItemStack(Blocks.stone_pressure_plate, 1), new Object[] {"##", '#', block});
			GameRegistry.addRecipe(new ItemStack(Blocks.lever, 1), new Object[] {"X", "#", '#', block, 'X', Items.stick});
		}
		Item[] metals = new Item[]{Items.iron_ingot, silver};
		for(Item item : metals)
		{
			GameRegistry.addRecipe(new ItemStack(hammerAndChisel, 1), new Object[] { " mm", "m s", "s s", Character.valueOf('m'), item, Character.valueOf('s'), Items.stick });
			GameRegistry.addRecipe(new ItemStack(Items.shears, 1), new Object[] { " m", "m ", Character.valueOf('m'), item });
		}
		/**ZENITH**/
		GameRegistry.addRecipe(new ItemStack(alchemyTable, 1), new Object[] { "eee", "mmm", "mmm", Character.valueOf('e'), emerald, Character.valueOf('m'), (new ItemStack(limestone, 1, limestone.MARBLE)) });
		GameRegistry.addRecipe(new ItemStack(amberPickaxe, 1), new Object[] { "ccc", " s ", " s ", Character.valueOf('c'), (new ItemStack(amber, 1, 0)), Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(amberShovel, 1), new Object[] { "c", "s", "s", Character.valueOf('c'), (new ItemStack(amber, 1, 0)), Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(amberAxe, 1), new Object[] { "cc", "cs", " s", Character.valueOf('c'), (new ItemStack(amber, 1, 0)), Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(amberSword, 1), new Object[] { "c", "c", "s", Character.valueOf('c'), (new ItemStack(amber, 1, 0)), Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(silverPickaxe, 1), new Object[] { "ppp", " s ", " s ", Character.valueOf('p'), silver, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(silverShovel, 1), new Object[] { "p", "s", "s", Character.valueOf('p'), silver, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(silverAxe, 1), new Object[] { "pp", "ps", " s", Character.valueOf('p'), silver, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(silverSword, 1), new Object[] { "p", "p", "s", Character.valueOf('p'), silver, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(emeraldPickaxe, 1), new Object[] { "eee", " s ", " s ", Character.valueOf('e'), emerald, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(emeraldShovel, 1), new Object[] { "e", "s", "s", Character.valueOf('e'), emerald, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(emeraldAxe, 1), new Object[] { "ee", "es", " s", Character.valueOf('e'), emerald, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(emeraldSword, 1), new Object[] { "e", "e", "s", Character.valueOf('e'), emerald, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(amberBlock, 1), new Object[] { "aaa", "aaa", "aaa", Character.valueOf('a'), amber });
		GameRegistry.addRecipe(new ItemStack(amber, 9), new Object[] { "a", Character.valueOf('a'), amberBlock });
		GameRegistry.addRecipe(new ItemStack(amberChest, 1), new Object[] { "aaa", "aca", "aaa", Character.valueOf('a'), amberBlock, Character.valueOf('c'), Blocks.chest });
		for(byte b1 = beehive.HONEYCOMB_SIDE_0; b1 <= beehive.HONEYCOMB_SIDE_5; b1++)
		{
			GameRegistry.addRecipe(new ItemStack(wax, 4), new Object[] { "b", Character.valueOf('b'), (new ItemStack(beehive, 1, b1)) });
		}
		GameRegistry.addRecipe(new ItemStack(beehive, 1, beehive.WAX), new Object[] { "www", "www", "www", Character.valueOf('w'), wax });
		GameRegistry.addRecipe(new ItemStack(wax, 9), new Object[] { "w", Character.valueOf('w'), (new ItemStack(beehive, 1, beehive.WAX)) });
		GameRegistry.addRecipe(new ItemStack(candle, 8), new Object[] { "s", "w", "w", Character.valueOf('w'), wax, Character.valueOf('s'), Items.string });
		GameRegistry.addRecipe(new ItemStack(match, 16), new Object[] { "p", "s", Character.valueOf('p'), phosphorus, Character.valueOf('s'), Items.stick });
		GameRegistry.addRecipe(new ItemStack(candlestick, 12), new Object[] { "s", "s", "s", Character.valueOf('s'), silver });
		GameRegistry.addRecipe(new ItemStack(Blocks.glass, 8), new Object[] { "c", Character.valueOf('c'), crystal });
		GameRegistry.addRecipe(new ItemStack(emeraldBlock, 1), new Object[] { "eee", "eee", "eee", Character.valueOf('e'), emerald });
		GameRegistry.addRecipe(new ItemStack(emerald, 9), new Object[] { "e", Character.valueOf('e'), emeraldBlock });
		GameRegistry.addRecipe(new ItemStack(silverBlock, 1), new Object[] { "sss", "sss", "sss", Character.valueOf('s'), silver });
		GameRegistry.addRecipe(new ItemStack(silver, 9), new Object[] { "s", Character.valueOf('s'), silverBlock });
		GameRegistry.addRecipe(new ItemStack(easel, 1), new Object[] { " s ", "ppp", "s s", Character.valueOf('s'), Items.stick, Character.valueOf('p'), Blocks.planks });
		GameRegistry.addRecipe(new ItemStack(lightSensor, 1), new Object[] { "msm", "pgp", "mrm", Character.valueOf('m'), (new ItemStack(limestone, 1, limestone.MARBLE)), Character.valueOf('s'), silver,  Character.valueOf('g'), Blocks.glass, Character.valueOf('r'), Items.redstone, Character.valueOf('p'), phosphorus });
		GameRegistry.addRecipe(new ItemStack(windTube, 16), new Object[] { "mmm", "mtm", "mmm", Character.valueOf('m'), (new ItemStack(limestone, 1, limestone.MARBLE)), Character.valueOf('t'), bucketThermal });
		GameRegistry.addRecipe(new ItemStack(canvas, 1), new Object[] { "c c", " c ", "c c", Character.valueOf('c'), cotton });
		GameRegistry.addRecipe(new ItemStack(windBlower, 1), new Object[] { "s  ", " s ", "  a", Character.valueOf('s'), silver, Character.valueOf('a'), airSac });
		GameRegistry.addRecipe(new ItemStack(balloonItem, 1), new Object[] { "aaa", "rsr", "bbb", Character.valueOf('a'), airSac, Character.valueOf('r'), Items.string/*rope?*/, Character.valueOf('s'), silver, Character.valueOf('b'), basket });
		GameRegistry.addRecipe(new ItemStack(limestone, 4, limestone.LIMESTONE_BRICK), new Object[] { "ll", "ll", Character.valueOf('l'), (new ItemStack(limestone, 1, limestone.LIMESTONE)) });
		GameRegistry.addRecipe(new ItemStack(limestone, 4, limestone.MARBLE_BRICK), new Object[] { "mm", "mm", Character.valueOf('m'), (new ItemStack(limestone, 1, limestone.MARBLE)) });
		/*GameRegistry.addRecipe(new ItemStack(Blocks.planks, 4), new Object[] { "w", Character.valueOf('w'), skyLog0 });
		GameRegistry.addRecipe(new ItemStack(Blocks.planks, 4), new Object[] { "w", Character.valueOf('w'), skyLog1 });
		GameRegistry.addRecipe(new ItemStack(Blocks.planks, 4), new Object[] { "w", Character.valueOf('w'), skyLog2 });*/
		for(Tree tree : Tree.treeList)
		{
			GameRegistry.addRecipe(new ItemStack(Blocks.planks, 4), new Object[] { "w", Character.valueOf('w'), tree.logBlock });
		}
		GameRegistry.addRecipe(new ItemStack(telescope, 1), new Object[] { "sgs", "s s", "sgs", Character.valueOf('s'), silver, Character.valueOf('g'), Blocks.glass });
		GameRegistry.addRecipe(new ItemStack(redstoneValve, 1), new Object[] { " w ", "wrw", " w ", Character.valueOf('w'), windTube, Character.valueOf('r'), Items.redstone });
		GameRegistry.addShapelessRecipe(new ItemStack(floatingRedstone, 1), new Object[] {Items.redstone, cloud});

		FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(silverOre), (new ItemStack(silver)), 0F);
		FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(emeraldOre), (new ItemStack(emerald)), 0F);
		FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(phosphorusOre), (new ItemStack(phosphorus)), 0F);
		FurnaceRecipes.smelting().func_151396_a(sugarplum, (new ItemStack(Items.sugar)), 0F);
		FurnaceRecipes.smelting().func_151396_a(phosphorus, (new ItemStack(Items.redstone)), 0F);
		/*FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(skyLog0), (new ItemStack(Items.coal, 1, 1)), 0F);
		FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(skyLog1), (new ItemStack(Items.coal, 1, 1)), 0F);
		FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(skyLog2), (new ItemStack(Items.coal, 1, 1)), 0F);*/
		for(Tree tree : Tree.treeList)
		{
			FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(tree.logBlock), (new ItemStack(Items.coal, 1, 1)), 0F);
		}
		FurnaceRecipes.smelting().func_151396_a(Item.getItemFromBlock(limestone), (new ItemStack(limestone, 1, limestone.MARBLE)), 0F);
		//flowers -> dyes
	}
	
	public static final Material honeyMaterial = (new MaterialLiquid(MapColor.sandColor));
	public static final Material nectarMaterial = (new MaterialLiquid(MapColor.foliageColor));
	
	/**UNIVERSAL**/
	@RegisterBlock public static final BlockColumn column = (BlockColumn)(new BlockColumn()).setHardness(1.0F).setResistance(7F).setStepSound(Block.soundTypeStone).setBlockName("column").setBlockTextureName("alldemdimensions:nether/columnBlazeSide");
	
	/**OVERWORLD**/
	@RegisterBlock public static final BlockPortalBase earthPortal = (BlockPortalBase)(new BlockPortalBase(0.2F, 0.6F, 0.3F)).setHardness(-1F).setLightLevel(0.75F).setStepSound(Block.soundTypeGlass).setBlockName("earthPortal").setBlockTextureName("alldemdimensions:overworld/overworldPortal");
	/**Bronze was the temporary material for the overworld's portal. Slated for removal. Some blocks are just my own
	 * 		tinkering and won't be in the final mod.**/
	public static final Block bronzeBlock = Blocks.mossy_cobblestone;// = (new BlockBronze(3251, 128)).setHardness(3.0F).setResistance(15F).setBlockName("bronzeBlock").setStepSound(Block.soundTypeMetal).setCreativeTab(CreativeTabs.tabBlock).setTextureFile("/alldemdimensions/blocks_misc.png");
	
	/**ZENITH**/
	@RegisterBlock(itemClass = ItemBlockSkyLimestone.class) public static final BlockSkyLimestone limestone = (BlockSkyLimestone)(new BlockSkyLimestone()).setHardness(1.0F).setResistance(7.5F).setStepSound(Block.soundTypeStone).setBlockName("skyLimestone").setBlockTextureName("alldemdimensions:zenith/limestone").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock(itemClass = ItemBlockSkyCloud.class) public static final BlockSkyCloud cloud = (BlockSkyCloud)(new BlockSkyCloud()).setHardness(0.4F).setLightOpacity(0).setStepSound(Block.soundTypeCloth).setBlockName("skyCloud").setBlockTextureName("alldemdimensions:zenith/cloud").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock(itemClass = ItemBlockCrystal.class) public static final BlockCrystal crystal = (BlockCrystal)(new BlockCrystal()).setHardness(2.0F).setResistance(15F).setStepSound(Block.soundTypeGlass).setBlockName("crystal").setBlockTextureName("alldemdimensions:zenith/crystal").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final BlockPortalBase skyPortal = (BlockPortalBase)(new BlockPortalBase(0.8F, 0.8F, 1F)).setHardness(-1F).setLightLevel(0.75F).setStepSound(Block.soundTypeGlass).setBlockName("skyPortal").setBlockTextureName("alldemdimensions:zenith/zenithPortal");
	@RegisterBlock public static final Block honeysuckle = (new BlockHoneysuckle()).setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("honeysuckle").setBlockTextureName("alldemdimensions:zenith/honeysuckle").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock(itemClass = ItemBlockBeehive.class) public static final BlockBeehive beehive = (BlockBeehive)(new BlockBeehive()).setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("beehive").setBlockTextureName("alldemdimensions:zenith/beehiveExterior")/*.setTickRandomly(true)*/.setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final Block alchemyTable = (new BlockAlchemyTable()).setHardness(2.0F).setResistance(20F).setStepSound(Block.soundTypeStone).setBlockName("alchemyTable").setBlockTextureName("alldemdimensions:zenith/alchemyTableSide").setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final Block thermal = (new BlockThermal()).setHardness(-1F).setStepSound(Block.soundTypeCloth).setBlockName("thermalBlock").setBlockTextureName("alldemdimensions:transparent");
	@RegisterBlock public static final Block phosphorusOre = (new BlockADDOre()).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("phosphorusOre").setBlockTextureName("alldemdimensions:zenith/orePhosphorus").setCreativeTab(CreativeTabs.tabBlock);
	//@RegisterBlock(itemClass = ItemBlockSkyPlant.class) public static final BlockSkyPlant skyPlant = (BlockSkyPlant)(new BlockSkyPlant(3310, 15)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("alldemdimensions:transparent").setBlockName("skyPlant");
	@RegisterBlock public static final BlockSkyFruit skyFruit = (BlockSkyFruit)(new BlockSkyFruit()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("alldemdimensions:transparent").setBlockName("skyFruit");
	@RegisterBlock public static final Block emeraldOre = (new BlockADDOre()).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("emeraldOre").setBlockTextureName("alldemdimensions:zenith/oreEmerald").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block silverOre = (new BlockADDOre()).setHardness(2.0F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("silverOre").setBlockTextureName("alldemdimensions:zenith/oreSilver").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block waxGrass = (new BlockWaxGrass()).setHardness(0.6F).setStepSound(Block.soundTypeGrass).setBlockName("waxGrass").setBlockTextureName("alldemdimensions:zenith/waxGrassSide").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block honeyMoving = (new BlockADDFlowingLiquid(honeyMaterial)).setHardness(100.0F).setLightOpacity(3).setBlockName("honeyMoving").setBlockTextureName("alldemdimensions:zenith/honey")./*disablestats().*/setTickRandomly(true);
	@RegisterBlock public static final Block honeyStill = (new BlockADDStaticLiquid(honeyMaterial)).setHardness(100.0F).setLightOpacity(3).setBlockName("honeyStill").setBlockTextureName("alldemdimensions:zenith/honey")./*disablestats().*/setTickRandomly(true);
	/*public static final BlockSkyLeaves skyLeaves0 = (BlockSkyLeaves)(new BlockSkyLeaves(3317, 87)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("skyLeaves0").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	public static final BlockSkyLeaves skyLeaves1 = (BlockSkyLeaves)(new BlockSkyLeaves(3318, 87)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("skyLeaves1").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	public static final BlockSkyLeaves skyLeaves2 = (BlockSkyLeaves)(new BlockSkyLeaves(3319, 87)).setHardness(0.2F).setLightOpacity(1).setStepSound(Block.soundTypeGrass).setBlockName("skyLeaves2").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	public static final BlockSkyLog skyLog0 = (BlockSkyLog)(new BlockSkyLog(3320)).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("skyLog0").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabBlock);
	public static final BlockSkyLog skyLog1 = (BlockSkyLog)(new BlockSkyLog(3321)).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("skyLog1").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabBlock);
	public static final BlockSkyLog skyLog2 = (BlockSkyLog)(new BlockSkyLog(3322)).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("skyLog2").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabBlock);
	public static final BlockSkySapling skySapling0 = (BlockSkySapling)(new BlockSkySapling(3323, 79)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("skySapling0").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	public static final BlockSkySapling skySapling1 = (BlockSkySapling)(new BlockSkySapling(3324, 79)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("skySapling1").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	public static final BlockSkySapling skySapling2 = (BlockSkySapling)(new BlockSkySapling(3325, 79)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("skySapling2").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	*/
	static
	{
		Tree tree = Tree.zenith_ancient;
	}
	@RegisterBlock public static final Block lightSensor = (new BlockLightSensor()).setHardness(5.0F).setStepSound(Block.soundTypeMetal).setBlockName("lightSensor").setBlockTextureName("alldemdimensions:zenith/lightSensorSide").setCreativeTab(CreativeTabs.tabRedstone);
	@RegisterBlock public static final Block emeraldBlock = (new BlockADDStorage(Material.rock)).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundTypeStone).setBlockName("skyEmeraldBlock").setBlockTextureName("alldemdimensions:zenith/emeraldBlock").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block silverBlock = (new BlockADDStorage(Material.iron)).setHardness(3.0F).setResistance(15.0F).setStepSound(Block.soundTypeMetal).setBlockName("skySilverBlock").setBlockTextureName("alldemdimensions:zenith/silverBlock").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block amberBlock = (new BlockADDStorage(Material.rock)).setHardness(1.5F).setResistance(2.0F).setStepSound(Block.soundTypeGlass).setBlockName("skyAmberBlock").setBlockTextureName("alldemdimensions:zenith/amberBlock").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block amberChest = (new BlockAmberChest()).setHardness(2.5F).setStepSound(Block.soundTypeWood).setBlockName("amberChest").setBlockTextureName("alldemdimensions:transparent").setTickRandomly(true).setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final Block floatingWire = (new BlockFloatingWire()).setHardness(0.0F).setStepSound(Block.soundTypeSnow).setBlockName("floatingWire").setBlockTextureName("alldemdimensions:transparent")./*disablestats().*/setTickRandomly(true);
	@RegisterBlock public static final Block nectarMoving = (new BlockADDFlowingLiquid(nectarMaterial)).setHardness(100.0F).setLightOpacity(3).setBlockName("nectarMoving").setBlockTextureName("alldemdimensions:zenith/nectar")./*disablestats().*/setTickRandomly(true);
	@RegisterBlock public static final Block nectarStill = (new BlockADDStaticLiquid(nectarMaterial)).setHardness(100.0F).setLightOpacity(3).setBlockName("nectarStill").setBlockTextureName("alldemdimensions:zenith/nectar")./*disablestats().*/setTickRandomly(true);	
	@RegisterBlock public static final Block easel = (new BlockEasel()).setHardness(1.5F).setStepSound(Block.soundTypeWood).setBlockName("easel").setBlockTextureName("wood").setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final BlockCandle candle = (BlockCandle)(new BlockCandle()).setHardness(0.0F).setStepSound(Block.soundTypeWood).setBlockName("candle").setBlockTextureName("alldemdimensions:zenith/flower5").setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final Block candlestick = (new BlockCandlestick()).setHardness(0.0F).setStepSound(Block.soundTypeMetal).setBlockName("candlestick").setBlockTextureName("alldemdimensions:zenith/silverBlock").setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final BlockWindTube windTube = (BlockWindTube)(new BlockWindTube()).setHardness(5.0F).setStepSound(Block.soundTypeStone).setBlockName("windTube").setBlockTextureName("alldemdimensions:zenith/marble").setCreativeTab(CreativeTabs.tabRedstone);	
	@RegisterBlock public static final Block basket = (new BlockBasket()).setHardness(0.5F).setStepSound(Block.soundTypeWood).setBlockName("basketBlock").setBlockTextureName("alldemdimensions:zenith/basket").setCreativeTab(CreativeTabs.tabDecorations);
	@RegisterBlock public static final Block stellarAltar = (new BlockStellarAltar()).setHardness(2.5F).setStepSound(Block.soundTypeStone).setBlockName("stellarAltar").setBlockTextureName("alldemdimensions:zenith/stellarAltarTop").setCreativeTab(CreativeTabs.tabDecorations);
	//public static final BlockSkyPlant1 skyPlant1 = (BlockSkyPlant1)(new BlockSkyPlant1(3341, 168)).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockName("skyPlant1").setBlockTextureName("alldemdimensions:transparent");
	static
	{
		Plant plant = Plant.zenith_flowerCover;
	}
	@RegisterBlock public static final Block redstoneValve = (new BlockRedstoneValve()).setHardness(5.0F).setStepSound(Block.soundTypeStone).setBlockName("redstoneValve").setBlockTextureName("alldemdimensions:zenith/marble").setCreativeTab(CreativeTabs.tabRedstone);	
    @RegisterBlock public static final Block stairsLimestoneBrick = (new BlockADDStairs(limestone, limestone.LIMESTONE_BRICK)).setBlockName("stairsLimestoneBrick").setBlockTextureName("alldemdimensions:zenith/limestoneBrick").setTickRandomly(true).setCreativeTab(CreativeTabs.tabBlock);
    @RegisterBlock public static final Block stairsMarbleBrick = (new BlockADDStairs(limestone, limestone.MARBLE_BRICK)).setBlockName("stairsMarbleBrick").setBlockTextureName("alldemdimensions:zenith/marbleBrick").setTickRandomly(true).setCreativeTab(CreativeTabs.tabBlock);
    @RegisterBlock(itemClass = ItemBlockADDSlab.class) public static final BlockADDSlab stoneSlab = (BlockADDSlab)(new BlockADDSlab(false)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("stoneSlabADD").setBlockTextureName("alldemdimensions:zenith/limestoneBrick").setCreativeTab(CreativeTabs.tabBlock);
    @RegisterBlock(itemClass = ItemBlockADDSlab.class) public static final BlockADDSlab stoneSlabDouble = (BlockADDSlab)(new BlockADDSlab(true)).setHardness(2.0F).setResistance(10.0F).setStepSound(Block.soundTypeStone).setBlockName("stoneSlabDoubleADD").setBlockTextureName("alldemdimensions:zenith/limestoneBrick");
	@RegisterBlock public static final Block modifiedBlock = (new BlockModified(Material.rock)).setStepSound(Block.soundTypeStone).setBlockName("modifiedBlock").setBlockTextureName("alldemdimensions:transparent");
	//public static final Block ghoststone;
	
	/**KYTHER**/
	@RegisterBlock public static final BlockPortalBase waterPortal = (BlockPortalBase)(new BlockPortalBase(0F, 0.2F, 0.8F)).setHardness(-1F).setLightLevel(0.75F).setStepSound(Block.soundTypeGlass).setBlockName("waterPortal").setBlockTextureName("alldemdimensions:kyther/kytherPortal").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock public static final Block bubble = (new BlockBubble()).setHardness(0.2F).setStepSound(Block.soundTypeWood).setBlockName("bubble").setBlockTextureName("alldemdimensions:transparent");
	@RegisterBlock public static final Block kytherWater = (new BlockKytherWater()).setHardness(100F).setLightOpacity(3).setBlockName("kytherWater").setBlockTextureName("alldemdimensions:transparent")/*.disableStats()*/;
	//public static final Block palmWood = (new BlockPalmWood()).setHardness(2.0F).setStepSound(Block.soundTypeWood).setBlockName("palmWood");
	//public static final Block palmLeaves = (new BlockPalmLeaves()).setHardness(0.2F).setStepSound(Block.soundTypeGrass).setBlockName("palmLeaves");
	
	/**NETHER**/
    @RegisterBlock(itemClass = ItemBlockNetherStone.class) public static final BlockNetherStone netherStone = (BlockNetherStone)(new BlockNetherStone()).setHardness(1.5F).setResistance(20F).setStepSound(Block.soundTypeStone).setBlockName("netherStone").setBlockTextureName("alldemdimensions:nether/soulstone").setCreativeTab(CreativeTabs.tabBlock);
	//public static final BlockBlazestone blazestone = (BlockBlazestone)(new BlockBlazestone()).setHardness(0.8F).setResistance(5F).setLightLevel(0.75F).setStepSound(Block.soundTypeStone).setBlockName("blazestone");
	@RegisterBlock public static final Block mimicstone = (new BlockMimicstone()).setHardness(1.5F).setResistance(10F).setStepSound(Block.soundTypeStone).setBlockName("mimicStone").setBlockTextureName("alldemdimensions:nether/mimicstone").setCreativeTab(CreativeTabs.tabBlock);
	@RegisterBlock(itemClass = ItemBlockNetherCrystal.class) public static final BlockNetherCrystal netherCrystal = (BlockNetherCrystal)(new BlockNetherCrystal()).setHardness(1.0F).setStepSound(Block.soundTypeGlass).setBlockName("netherCrystal").setBlockTextureName("alldemdimensions:nether/crystalRedstone").setCreativeTab(CreativeTabs.tabDecorations);
    @RegisterBlock public static final BlockNetherFungus netherFungus = (BlockNetherFungus)(new BlockNetherFungus()).setBlockName("netherFungus").setBlockTextureName("alldemdimensions:transparent").setCreativeTab(CreativeTabs.tabDecorations);
    //public static final Block soundRecorder = (new BlockSoundRecorder()).setHardness(1.0F).setStepSound(Block.soundTypeWood).setBlockName("soundRecorder");
	
	/**ENDER**/
	@RegisterBlock public static final BlockPortalBase enderPortal = (BlockPortalBase)(new BlockPortalBase(0.3F, 0.1F, 0.2F)).setHardness(-1F).setLightLevel(0.75F).setStepSound(Block.soundTypeGlass).setBlockTextureName("alldemdimensions:ender/enderPortal").setBlockName("enderPortal");
	@RegisterBlock public static final Block enderCrystal = (new BlockEnderCrystal()).setHardness(2.0F).setResistance(15F).setStepSound(Block.soundTypeGlass).setBlockName("enderCrystal").setBlockTextureName("alldemdimensions:ender/enderCrystal").setCreativeTab(CreativeTabs.tabBlock);
	
	
	public static final Item mercury = new Item().setUnlocalizedName("mercury");
	public static final Item salt = new Item().setUnlocalizedName("salt");
	public static final Item magnesium = new Item().setUnlocalizedName("magnesium");
	public static final Item sulfur = new Item().setUnlocalizedName("sulfur");
	public static final Item emerald = new Item().setUnlocalizedName("zenith/emerald").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item phosphorus = new Item().setUnlocalizedName("zenith/phosphorus").setCreativeTab(CreativeTabs.tabMaterials).setMaxDamage(64);
	public static final Item silver = new Item().setUnlocalizedName("zenith/ingotSilver").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item emeraldPickaxe = new ItemADDPickaxe(EnumToolMaterialADD.EMERALD).setUnlocalizedName("zenith/pickaxeEmerald").setCreativeTab(CreativeTabs.tabTools);
	public static final Item emeraldShovel = new ItemADDShovel(EnumToolMaterialADD.EMERALD).setUnlocalizedName("zenith/shovelEmerald").setCreativeTab(CreativeTabs.tabTools);
	public static final Item emeraldAxe = new ItemADDAxe(EnumToolMaterialADD.EMERALD).setUnlocalizedName("zenith/axeEmerald").setCreativeTab(CreativeTabs.tabTools);
	public static final Item emeraldSword = new ItemADDSword(EnumToolMaterialADD.EMERALD).setUnlocalizedName("zenith/swordEmerald").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item amberPickaxe = new ItemADDPickaxe(EnumToolMaterialADD.AMBER).setUnlocalizedName("zenith/pickaxeAmber").setCreativeTab(CreativeTabs.tabTools);
	public static final Item amberShovel = new ItemADDShovel(EnumToolMaterialADD.AMBER).setUnlocalizedName("zenith/shovelAmber").setCreativeTab(CreativeTabs.tabTools);
	public static final Item amberAxe = new ItemADDAxe(EnumToolMaterialADD.AMBER).setUnlocalizedName("zenith/axeAmber").setCreativeTab(CreativeTabs.tabTools);
	public static final Item amberSword = new ItemADDSword(EnumToolMaterialADD.AMBER).setUnlocalizedName("zenith/swordAmber").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item silverPickaxe = new ItemADDPickaxe(EnumToolMaterialADD.SILVER).setUnlocalizedName("zenith/pickaxeSilver").setCreativeTab(CreativeTabs.tabTools);
	public static final Item silverShovel = new ItemADDShovel(EnumToolMaterialADD.SILVER).setUnlocalizedName("zenith/shovelSilver").setCreativeTab(CreativeTabs.tabTools);
	public static final Item silverAxe = new ItemADDAxe(EnumToolMaterialADD.SILVER).setUnlocalizedName("zenith/axeSilver").setCreativeTab(CreativeTabs.tabTools);
	public static final Item silverSword = new ItemADDSword(EnumToolMaterialADD.SILVER).setUnlocalizedName("zenith/swordSilver").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item sugarplum = new ItemFood(3, 0.5F, false).setUnlocalizedName("zenith/plum").setCreativeTab(CreativeTabs.tabFood);
	public static final Item amber = new Item().setUnlocalizedName("zenith/amber").setCreativeTab(CreativeTabs.tabMaterials).setMaxDamage(64);
	public static final Item cherry = new ItemFood(1, 0.5F, false).setUnlocalizedName("zenith/cherry").setCreativeTab(CreativeTabs.tabFood);
	public static final Item wax = new Item().setUnlocalizedName("zenith/wax").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item match = new ItemMatch().setUnlocalizedName("zenith/matchstick").setCreativeTab(CreativeTabs.tabMisc);
	public static final Item royalJelly = new Item().setUnlocalizedName("zenith/royalJelly").setCreativeTab(CreativeTabs.tabMaterials).setMaxDamage(64);
	public static final Item floatingRedstone = new ItemFloatingRedstone().setUnlocalizedName("zenith/floatingRedstone").setCreativeTab(CreativeTabs.tabRedstone);
	public static final Item cotton = new Item().setUnlocalizedName("zenith/cotton").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item canvas = new ItemCanvas().setUnlocalizedName("zenith/canvas").setCreativeTab(CreativeTabs.tabDecorations);
	public static final Item paintBottle = new ItemPaintBottle().setUnlocalizedName("zenith/paintBottle")/*.setCreativeTab(CreativeTabs.tabMisc)*/;	
	public static final Item ghostPowder = new Item().setUnlocalizedName("zenith/ectoplasm").setCreativeTab(CreativeTabs.tabMaterials).setMaxDamage(64);
	public static final Item bucketNectar = new /*ItemBucketNectar*/ItemBucket(nectarMoving).setUnlocalizedName("zenith/bucketNectar").setContainerItem(Items.bucket).setCreativeTab(CreativeTabs.tabMisc).setMaxDamage(64);
	public static final Item bucketHoney = new ItemBucket(honeyMoving).setUnlocalizedName("zenith/bucketHoney").setContainerItem(Items.bucket).setCreativeTab(CreativeTabs.tabMisc);
	public static final Item bucketThermal = new ItemBucket(thermal).setUnlocalizedName("zenith/bucketThermal").setContainerItem(Items.bucket).setCreativeTab(CreativeTabs.tabMisc);
	public static final Item airSac = new Item().setUnlocalizedName("zenith/airSac").setCreativeTab(CreativeTabs.tabMaterials);
	public static final Item windBlower = new ItemWindBlower().setUnlocalizedName("zenith/windBlower").setCreativeTab(CreativeTabs.tabCombat);
	public static final Item podFruit = new ItemFood(5, 0.5F, false).setUnlocalizedName("zenith/podFruit").setCreativeTab(CreativeTabs.tabFood);
	public static final Item hammerAndChisel = new ItemHammerAndChisel().setUnlocalizedName("hammerAndChisel").setCreativeTab(CreativeTabs.tabTools);
	public static final Item balloonItem = new ItemHotAirBalloon().setUnlocalizedName("zenith/hotAirBalloon").setCreativeTab(CreativeTabs.tabTransport);
	public static final Item telescope = new ItemTelescope(false).setUnlocalizedName("zenith/telescope").setCreativeTab(CreativeTabs.tabTools).setMaxStackSize(1);
	public static final Item telescopeActive = new ItemTelescope(true).setUnlocalizedName("zenith/telescope").setMaxStackSize(1);
    public static final Item chestnut = new ItemFood(2, 0.5F, false).setUnlocalizedName("alldemdimensions:zenith/chestnut").setCreativeTab(CreativeTabs.tabFood);
    public static final ItemCrossbow crossbow = (ItemCrossbow)new ItemCrossbow().setUnlocalizedName("zenith/crossbow").setTextureName("alldemdimensions:zenith/crossbow").setCreativeTab(CreativeTabs.tabCombat);
    
	//public static final Item sapphireHelmet = (new ItemWaterArmor(18722, EnumArmorMaterialDim.SAPPHIRE, 0/*ModLoader.addArmor("/alldemdimensions/armor/sapphire.png")*/, 0)).setBlockName("sapphireHelmet");
    //public static final Item sapphireChestplate = (new ItemWaterArmor(18723, EnumArmorMaterialDim.SAPPHIRE, 0/*ModLoader.addArmor("/alldemdimensions/armor/sapphire.png")*/, 1)).setBlockName("sapphireChestplate");
    //public static final Item sapphireLeggings = (new ItemWaterArmor(18724, EnumArmorMaterialDim.SAPPHIRE, 0/*ModLoader.addArmor("/alldemdimensions/armor/sapphire.png")*/, 2)).setBlockName("sapphireLeggings");
    //public static final Item sapphireFlippers = (new ItemWaterArmor(18725, EnumArmorMaterialDim.SAPPHIRE, 0/*ModLoader.addArmor("/alldemdimensions/armor/sapphire.png")*/, 3)).setBlockName("sapphireFlippers");
	//public static final Item aquamarineHelmet = (new ItemWaterArmor(18726, EnumArmorMaterialDim.AQUAMARINE, 0/*ModLoader.addArmor("/alldemdimensions/armor/aquamarine.png")*/, 0)).setBlockName("aquamarineHelmet");
    //public static final Item aquamarineChestplate = (new ItemWaterArmor(18727, EnumArmorMaterialDim.AQUAMARINE, 0/*ModLoader.addArmor("/alldemdimensions/armor/aquamarine.png")*/, 1)).setBlockName("aquamarineChestplate");
    //public static final Item aquamarineLeggings = (new ItemWaterArmor(18728, EnumArmorMaterialDim.AQUAMARINE, 0/*ModLoader.addArmor("/alldemdimensions/armor/aquamarine.png")*/, 2)).setBlockName("aquamarineLeggings");
    //public static final Item aquamarineFlippers = (new ItemWaterArmor(18729, EnumArmorMaterialDim.AQUAMARINE, 0/*ModLoader.addArmor("/alldemdimensions/armor/aquamarine.png")*/, 3)).setBlockName("aquamarineFlippers");
	//public static final Item copperHelmet = (new ItemWaterArmor(18730, EnumArmorMaterialDim.COPPER, 0/*ModLoader.addArmor("/alldemdimensions/armor/copper.png")*/, 0)).setBlockName("copperHelmet");
    //public static final Item copperChestplate = (new ItemWaterArmor(18731, EnumArmorMaterialDim.COPPER, 0/*ModLoader.addArmor("/alldemdimensions/armor/copper.png")*/, 1)).setBlockName("copperChestplate");
    //public static final Item copperLeggings = (new ItemWaterArmor(18732, EnumArmorMaterialDim.COPPER, 0/*ModLoader.addArmor("/alldemdimensions/armor/copper.png")*/, 2)).setBlockName("copperLeggings");
    //public static final Item copperFlippers = (new ItemWaterArmor(18733, EnumArmorMaterialDim.COPPER, 0/*ModLoader.addArmor("/alldemdimensions/armor/copper.png")*/, 3)).setBlockName("copperFlippers");
	//public static final Item rubberHelmet = (new ItemWaterArmor(18734, EnumArmorMaterialDim.RUBBER, 0/*ModLoader.addArmor("/alldemdimensions/armor/rubber.png")*/, 0)).setBlockName("rubberHelmet");
    //public static final Item rubberChestplate = (new ItemWaterArmor(18735, EnumArmorMaterialDim.RUBBER, 0/*ModLoader.addArmor("/alldemdimensions/armor/rubber.png")*/, 1)).setBlockName("rubberChestplate");
    //public static final Item rubberLeggings = (new ItemWaterArmor(18736, EnumArmorMaterialDim.RUBBER, 0/*ModLoader.addArmor("/alldemdimensions/armor/rubber.png")*/, 2)).setBlockName("rubberLeggings");
    //public static final Item rubberFlippers = (new ItemWaterArmor(18737, EnumArmorMaterialDim.RUBBER, 0/*ModLoader.addArmor("/alldemdimensions/armor/rubber.png")*/, 3)).setBlockName("rubberFlippers");
	
	private void registerEntities()
	{
		EntityRegistry.registerModEntity(EntitySycopter.class, "sycopter", 2, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityBadger.class, "honeyBadger", 3, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityEnderwraith.class, "enderwraith", 4, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityBluekite.class, "bluekite", 5, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntitySkyknight.class, "skyknight", 6, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityCloudGolem.class, "cloudGolem", 7, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityButterfly.class, "butterfly", 8, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityHeliumSlime.class, "heliumSlime", 9, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityCustomPainting.class, "customPainting", 10, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityWind.class, "skyWindEntity", 11, this, 40, 1, true);	
		EntityRegistry.registerModEntity(EntityHotAirBalloon.class, "hotAirBalloon", 12, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityCloud.class, "skyCloudEntity", 13, this, 40, 1, true);	
		EntityRegistry.registerModEntity(EntityAmpfly.class, "ampfly", 15, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityBee.class, "bee", 17, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityTornado.class, "tornado", 18, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityAurora.class, "aurora", 19, this, 40, 1, true);
		EntityRegistry.registerModEntity(EntityGust.class, "gust", 20, this, 40, 1, true);
	}
	
	private void registerWorldGeneration()
	{
		//GameRegistry.registerWorldGenerator(new WorldGenEarthCrystals(), 1);
	}

    public static boolean isInPortal(EntityPlayer entityplayer, Block i)
    {
        int j = (int)Math.floor(entityplayer.posX);
        int k = (int)Math.floor(entityplayer.posY);
        int l = (int)Math.floor(entityplayer.posZ);
        if(entityplayer.worldObj.getBlock(j, k, l) == i || entityplayer.worldObj.getBlock(j, k - 1, l) == i)
		{
			return true;
		}
		return false;
    }

    public static Object getKeyFromValue(Map map, Object obj)
    {
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext();)
        {
            Object obj1 = iterator.next();
            if (map.get(obj1).equals(obj))
            {
                return obj1;
            }
        }

        return null;
    }
	
	public static File getSaveDirectory(SaveHandler savehandler)
	{
		return savehandler.getWorldDirectory();
	}
	
	public static int makeColorLightValue(float r, float g, float b)
	{
		int brightness = (int)(15.0F * Math.max(Math.max(r, g), b));
		return brightness | ((((int) (15.0F * b)) << 15) + (((int) (15.0F * g)) << 10) + (((int) (15.0F * r)) << 5));
	}
	
	@Override
	public void saveData(NBTTagCompound nbttagcompound)
	{
	}

	@Override
	public void loadData(NBTTagCompound nbttagcompound)
	{
	}
		
	public static final String version = "_Alpha_0.4.0";
	private static AllDemDimensions instance;
    public static final PlayerAllDemDimensions customPlayer = new PlayerAllDemDimensions();
    public static boolean coloredLightsLoaded;
    public static final int blockRenderId = 2781;
	public static final byte GUI_EASEL = 0;
	public static final byte GUI_ALCHEMY = 1;
	public static final byte GUI_STELLAR_ALTAR = 2;
	public static Item[] invalidItems = (new Item[]{silverPickaxe, silverShovel,
		silverAxe, silverSword});
	@SidedProxy(clientSide="alldemdimensions.AllDemDimensionsClient", serverSide="alldemdimensions.AllDemDimensionsServer")
	public static AllDemDimensionsProxy proxyInstance = new AllDemDimensionsProxy();
	public static FMLEventChannel channel;
	public static final ArrayList<Block> allBlocks = new ArrayList<Block>();
	public static final ArrayList<Item> allItems = new ArrayList<Item>();
	
}