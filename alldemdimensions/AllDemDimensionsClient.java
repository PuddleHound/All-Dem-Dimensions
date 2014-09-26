package alldemdimensions;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import alldemdimensions.block.BlockRenderer;
import alldemdimensions.block.IBlockRenderer;
import alldemdimensions.block.Plant;
import alldemdimensions.block.TileEntityAlchemyTable;
import alldemdimensions.block.TileEntityAlchemyTableRenderer;
import alldemdimensions.block.TileEntityAmberChest;
import alldemdimensions.block.TileEntityAmberChestRenderer;
import alldemdimensions.block.TileEntityEasel;
import alldemdimensions.block.TileEntityEaselRenderer;
import alldemdimensions.block.Tree;
import alldemdimensions.entity.EntityADDPortalFX;
import alldemdimensions.entity.EntityCustomPainting;
import alldemdimensions.entity.EntityHeliumBubbleFX;
import alldemdimensions.entity.EntityTornadoFX;
import alldemdimensions.entity.model.ModelAmpfly;
import alldemdimensions.entity.model.ModelBadger;
import alldemdimensions.entity.model.ModelBee;
import alldemdimensions.entity.model.ModelBluekite;
import alldemdimensions.entity.model.ModelButterfly;
import alldemdimensions.entity.model.ModelCloudGolem;
import alldemdimensions.entity.model.ModelEnderwraith;
import alldemdimensions.entity.model.ModelHeliumSlime;
import alldemdimensions.entity.model.ModelHotAirBalloon;
import alldemdimensions.entity.model.ModelSkyknight;
import alldemdimensions.entity.model.ModelSycopter;
import alldemdimensions.entity.render.RenderAmpfly;
import alldemdimensions.entity.render.RenderAurora;
import alldemdimensions.entity.render.RenderBadger;
import alldemdimensions.entity.render.RenderBee;
import alldemdimensions.entity.render.RenderBluekite;
import alldemdimensions.entity.render.RenderButterfly;
import alldemdimensions.entity.render.RenderCloud;
import alldemdimensions.entity.render.RenderCloudGolem;
import alldemdimensions.entity.render.RenderCustomPainting;
import alldemdimensions.entity.render.RenderEnderwraith;
import alldemdimensions.entity.render.RenderGust;
import alldemdimensions.entity.render.RenderHeliumSlime;
import alldemdimensions.entity.render.RenderHotAirBalloon;
import alldemdimensions.entity.render.RenderSycopter;
import alldemdimensions.entity.render.RenderWind;
import alldemdimensions.network.PacketHandlerClient;
import alldemdimensions.util.TextureLibrary;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.environment.Constellation;
import alldemdimensions.world.environment.DimensionSkyRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent;

public class AllDemDimensionsClient extends AllDemDimensionsProxy
	implements ISimpleBlockRenderingHandler
{

	public AllDemDimensionsClient()
	{
		if(!initialized)
		{
			mc = Minecraft.getMinecraft();
			RenderingRegistry.registerBlockHandler(this);
			registerEntityRenderers();
			//need to make new key bindings
			MinecraftForge.EVENT_BUS.register(this);
            MinecraftForge.EVENT_BUS.register(TextureLibrary.instance);
            FMLCommonHandler.instance().bus().register(this);
            MinecraftForgeClient.registerItemRenderer(AllDemDimensions.crossbow, AllDemDimensions.crossbow);
		}
		initialized = true;
	}
	
    @Override
	public void init()
	{
        AllDemDimensions.channel.register(new PacketHandlerClient());
		if(mc == null)
		{
			mc = Minecraft.getMinecraft();
		}
	}
        
	@SubscribeEvent
	public void onRenderWorld(RenderWorldLastEvent event)
	{
		if(mc.theWorld != null)
		{
			if(mc.theWorld.provider.dimensionId == Dimension.zenith.dimensionId)
			{
				skyRenderer.renderPrecipitation(Minecraft.getMinecraft(), event.partialTicks);
			}
		}
		updateCount++;
	}    

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event)
    {
    	if(event.phase == TickEvent.Phase.END)
    	{
    		Plant.tickCounter++;
    		if(Minecraft.getMinecraft().theWorld != null)
    		{
    			Minecraft.getMinecraft().theWorld.theProfiler.startSection("ADD client tick");
    		}
    		checkForGraphicsChange();
    		/*if(mc.theWorld != null)
            {
                for(EntityCustomPainting entity : paintings)
                {
                    mc.theWorld.addEntityToWorld(entity.id, entity);
                }
            }*/
            if(mc.theWorld != null && mc.thePlayer != null)
            {
                AllDemDimensions.customPlayer.onClientTick(mc.thePlayer, mc.theWorld);
            }
            if(mc.theWorld != null && mc.theWorld.provider.dimensionId == Dimension.zenith.dimensionId)
    		{
    			skyRenderer.onWorldTick(mc);
    			for(Constellation constellation : Constellation.allConstellations)
    			{
    				if(constellation != null)
    				{
    					constellation.onClientTick(mc);
    				}
    			}
    			if(Minecraft.getMinecraft().theWorld != null)
    			{
    				Minecraft.getMinecraft().theWorld.theProfiler.endSection();
    			}
    			return;
    		}
            if(Minecraft.getMinecraft().theWorld != null)
            {
            	Minecraft.getMinecraft().theWorld.theProfiler.endSection();
            }
            DimensionSkyRenderer.clientTick();
    	}
    }
        
    @SubscribeEvent
	public void onRenderTick(TickEvent.RenderTickEvent event)
	{
    	if(Minecraft.getMinecraft().theWorld != null)
    	{
    		Minecraft.getMinecraft().theWorld.theProfiler.startSection("ADD render tick");
    	}
		ScaledResolution scaledresolution = new ScaledResolution(this.mc.gameSettings, this.mc.displayWidth, this.mc.displayHeight);
        int scaledWidth = scaledresolution.getScaledWidth();
        int scaledHeight = scaledresolution.getScaledHeight();
		if(mc.thePlayer != null && mc.currentScreen == null && AllDemDimensions.customPlayer.currentPortal_client != null)
		{
			renderBlockOverlay(AllDemDimensions.customPlayer.portalTime_client * 0.01F, scaledWidth, scaledHeight, AllDemDimensions.customPlayer.currentPortal_client);
		}
		if(mc.thePlayer != null && mc.currentScreen == null && mc.thePlayer.getCurrentEquippedItem() != null && mc.thePlayer.getCurrentEquippedItem().getItem() == AllDemDimensions.telescopeActive)
		{
			//mc.gameSettings.hideGUI = true;
			renderTelescopeOverlay(scaledWidth, scaledHeight);
			if(zoomFactor >= 8.0D)
			{
				zoomFactor = 8.0D;
				zoomOut = true;
			} else
			if(zoomFactor <= 1.1D)
			{
				zoomFactor = 1.1D;
				zoomOut = false;
			}
			if(zoomKeyDown)
			{
				if(zoomOut)
				{
					zoomFactor -= 0.1D;
				} else
				{
					zoomFactor += 0.1D;
				}
			}
		} else
		{
			//mc.gameSettings.hideGUI = false;
			zoomFactor = 1.0D;
		}
		//mc.entityRenderer.cameraZoom = zoomFactor;
		if(Minecraft.getMinecraft().theWorld != null)
		{
			Minecraft.getMinecraft().theWorld.theProfiler.endSection();
		}
	}
	
	private void checkForGraphicsChange()
	{
		for(Tree tree : Tree.treeList)
		{
			tree.leavesBlock.setGraphicsLevel(Minecraft.getMinecraft().gameSettings.fancyGraphics);
		}
		/*
		if(fancyGraphics && !mc.gameSettings.fancyGraphics)
		{
			fancyGraphics = false;
			AllDemDimensions.skyLeaves0.graphicsLevel = fancyGraphics;
			AllDemDimensions.skyLeaves1.graphicsLevel = fancyGraphics;
			AllDemDimensions.skyLeaves2.graphicsLevel = fancyGraphics;
		} else
		if(!fancyGraphics && mc.gameSettings.fancyGraphics)
		{
			fancyGraphics = true;
			AllDemDimensions.skyLeaves0.graphicsLevel = fancyGraphics;
			AllDemDimensions.skyLeaves1.graphicsLevel = fancyGraphics;
			AllDemDimensions.skyLeaves2.graphicsLevel = fancyGraphics;
		}*/
	}
	
	@SubscribeEvent
	public void onLoadSound(SoundLoadEvent event)
	{
		try
		{
            //FIX SOUNDS
			//event.manager.soundPoolSounds.addSound("alldemdimensions/sound/zenith/ambient1.wav", AllDemDimensions.class.getResource("/alldemdimensions/sound/zenith/ambient1.wav"));
			//event.manager.soundPoolSounds.addSound("alldemdimensions/sound/zenith/wind1.wav", AllDemDimensions.class.getResource("/alldemdimensions/sound/zenith/wind1.wav"));
			//event.manager.soundPoolSounds.addSound("alldemdimensions/sound/zenith/wind2.wav", AllDemDimensions.class.getResource("/alldemdimensions/sound/zenith/wind2.wav"));
			System.out.println("Successfully loaded sounds for All Dem Dimensions.");
		}
		catch(Exception e)
		{
			System.out.println("Failed to load sounds for All Dem Dimensions.");
			e.printStackTrace();
		}
	}
	
	@Deprecated
	private void renderBlockOverlay(float f, int i, int j, Block id)
    {
		//This needs to be fixed.
    }
	
	private void renderTelescopeOverlay(int u, int v)
    {
        GL11.glDisable(GL11.GL_DEPTH_TEST);
        GL11.glDepthMask(false);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_ALPHA_TEST);
        //GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.mc.renderEngine.getTexture(new ResourceLocation("%blur%/alldemdimensions/gui/telescope.png")));
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(0.0D, (double)v, -90.0D, 0.0D, 1.0D);
        tessellator.addVertexWithUV((double)u, (double)v, -90.0D, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)u, 0.0D, -90.0D, 1.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, -90.0D, 0.0D, 0.0D);
        tessellator.draw();
        GL11.glDepthMask(true);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glEnable(GL11.GL_ALPHA_TEST);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    }
	
	private void registerEntityRenderers()
	{
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntitySycopter.class, new RenderSycopter(new ModelSycopter()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityBadger.class, new RenderBadger(new ModelBadger()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityEnderwraith.class, new RenderEnderwraith(new ModelEnderwraith()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityBluekite.class, new RenderBluekite(new ModelBluekite()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntitySkyknight.class, new RenderBiped(new ModelSkyknight(), 0.5F));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityCloudGolem.class, new RenderCloudGolem(new ModelCloudGolem()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityButterfly.class, new RenderButterfly(new ModelButterfly()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityHeliumSlime.class, new RenderHeliumSlime(new ModelHeliumSlime()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityCustomPainting.class, new RenderCustomPainting());
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityWind.class, new RenderWind(224));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityHotAirBalloon.class, new RenderHotAirBalloon(new ModelHotAirBalloon()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityCloud.class, new RenderCloud());
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityAmpfly.class, new RenderAmpfly(new ModelAmpfly()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityBee.class, new RenderBee(new ModelBee()));
		//RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityTornado.class, new RenderTornado(new ModelTornado()));
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityAurora.class, new RenderAurora());
		RenderingRegistry.registerEntityRenderingHandler(alldemdimensions.entity.EntityGust.class, new RenderGust());
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAmberChest.class, new TileEntityAmberChestRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityEasel.class, new TileEntityEaselRenderer());
        ClientRegistry.bindTileEntitySpecialRenderer(TileEntityAlchemyTable.class, new TileEntityAlchemyTableRenderer());
	}
	
	public static void spawnPortalParticle(World world, float red, float green, float blue, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityADDPortalFX(world, red, green, blue, posX, posY, posZ, motionZ, motionY, motionZ));
	}
	
	public static void spawnParticle(EntityFX entityfx)//NEEDS FIXED
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(entityfx);
	}
	
    @Override
	public void spawnParticle(String s, World world, double d, double d1, double d2, double d3, double d4, double d5)
	{
		if(!(world instanceof WorldClient))
		{
			return;
		}
		if(s == "heliumBubble")
		{
			spawnParticle(new EntityHeliumBubbleFX(world, d, d1, d2, d3, d4, d5));
		} else
		if(s == "tornado")
		{
			spawnParticle(new EntityTornadoFX(world, d, d1, d2, d3, d4, d5));
		}
	}
	
    @Override
	public void spawnPortalParticle(String s, World world, float red, float green, float blue, double posX, double posY, double posZ, double motionX, double motionY, double motionZ)
	{
		Minecraft.getMinecraft().effectRenderer.addEffect(new EntityADDPortalFX(world, red, green, blue, posX, posY, posZ, motionZ, motionY, motionZ));
	}
        
    public ArrayList<EntityCustomPainting> paintings = new ArrayList<EntityCustomPainting>();//make per dimension
	
    @Override
    public void renderInventoryBlock(Block block, int metadata, int modelId, RenderBlocks renderblocks)
    {
        if(block instanceof IBlockRenderer)
        {
            BlockRenderer blockrenderer = new BlockRenderer(renderblocks, block, (byte)metadata);
            ((IBlockRenderer)block).render(blockrenderer);
            blockrenderer = null;
        }
    }

    @Override
    public boolean renderWorldBlock(IBlockAccess iblockaccess, int i, int j, int k, Block block, int modelId, RenderBlocks renderblocks)
    {
        if(block instanceof IBlockRenderer)
        {
            mc.theWorld.theProfiler.startSection("ADD Blocks");
            BlockRenderer blockrenderer = new BlockRenderer(renderblocks, iblockaccess, block, (byte)iblockaccess.getBlockMetadata(i, j, k), i, j, k);
            ((IBlockRenderer)block).render(blockrenderer);
            blockrenderer = null;
            mc.theWorld.theProfiler.endSection();
            return true;
        }
        return false;
    }

    @Override
    public boolean shouldRender3DInInventory(int i)
    {
        return true;
    }

    @Override
    public int getRenderId()
    {
        return renderId;
    }
	        
	public static final int renderId = AllDemDimensions.blockRenderId;
	public static Minecraft mc;
	public static boolean initialized;
	private static boolean fancyGraphics;
	public static final DimensionSkyRenderer skyRenderer = new DimensionSkyRenderer();
	public static boolean zoomKeyDown = false;
	public static double zoomFactor = 1.0D;
	private static boolean zoomOut = false;
	public static int selectedSpawnDimension = 0;
	public static int updateCount = 0;
        
}
