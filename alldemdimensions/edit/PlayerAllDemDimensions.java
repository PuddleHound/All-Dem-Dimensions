package alldemdimensions.edit;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S05PacketSpawnPosition;
import net.minecraft.potion.Potion;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.Teleporter;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.entity.player.PlayerSleepInBedEvent;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.item.EnumToolMaterialADD;
import alldemdimensions.item.ItemADDTool;
import alldemdimensions.world.Dimension;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class PlayerAllDemDimensions
{
	
	/**
	 * RESPAWNING:
	 * The respawning system is very simple. Each main dimension saves two
	 * spawn points (default and bed). The default spawn point is whatever
	 * the player's very first location in the dimension is (i.e. the
	 * location of the portal through which he enters the dimension for the
	 * first time, or just his original location in the dimension in which
	 * he chose to spawn). This cannot be changed. The bed spawn point is 
	 * set when the player sleeps in a bed, just like in vanilla. Obviously
	 * this can be changed, when the bed is destroyed or when the player
	 * sleeps in an another bed. The bed spawn point overrides the default
	 * assuming the player has slept in a (non-destroyed) bed.
	 * 
	 * Later, upon dying in a boss dimension, the player will respawn at
	 * his spawn point in the corresponding main dimension.
	 * 
	 * The default spawn point is also sent to the client every server tick
	 * for the purpose of compass rendering.
	 */
    
	@SubscribeEvent
    public void onLivingDeath(LivingDeathEvent event)
	{
        if(!(event.entityLiving instanceof EntityPlayerMP))
        {
            return;
        }
        
        EntityPlayerMP player = (EntityPlayerMP)event.entityLiving;
		Dimension dimension = Dimension.getDimensionForId_MC(player.dimension);
		System.out.println("ON DEATH:");
                
        NBTTagCompound nbttagcompound = player.getEntityData();
		int[] spawnLocations = loadSpawnData(nbttagcompound, nbt_defaultSpawnPoints);
		int[] bedSpawnLocations = loadSpawnData(nbttagcompound, nbt_bedSpawnPoints);
        if(spawnLocations == null || spawnLocations.length != Dimension.allDimensions.size() * 3)
        {
        	System.out.println("	Resetting default spawn point.");
            spawnLocations = new int[Dimension.allDimensions.size() * 3];//15
            Arrays.fill(spawnLocations, -1);
        }
        if(bedSpawnLocations == null || bedSpawnLocations.length != Dimension.allDimensions.size() * 3)
        {
        	System.out.println("	Resetting bed spawn point.");
            bedSpawnLocations = new int[Dimension.allDimensions.size() * 3];//15
            Arrays.fill(bedSpawnLocations, -1);
        }
        
		if(bedSpawnLocations[(dimension.id * 3) + 1] != -1)
		{
			System.out.println("	Bed spawn point has been set for this dimension.");
			int x = bedSpawnLocations[(dimension.id * 3) + 0];
			int y = bedSpawnLocations[(dimension.id * 3) + 1];
			int z = bedSpawnLocations[(dimension.id * 3) + 2];
			Block block = player.worldObj.getBlock(x, y, z);
			if(block == null || !block.isBed(player.worldObj, x, y, z, player))
			{
				System.out.println("	Bed is no longer available; defaulting to original spawn point.");
				ChunkCoordinates coords = new ChunkCoordinates(spawnLocations[(dimension.id * 3) + 0], spawnLocations[(dimension.id * 3) + 1], spawnLocations[(dimension.id * 3) + 2]);
				player.setSpawnChunk(coords, true);
				bedSpawnLocations[(dimension.id * 3) + 0] = -1;
				bedSpawnLocations[(dimension.id * 3) + 1] = -1;
				bedSpawnLocations[(dimension.id * 3) + 2] = -1;
				System.out.println("	Normal spawn coordinates for " + dimension.name + " on death are " + spawnLocations[(dimension.id * 3) + 0] + "," + spawnLocations[(dimension.id * 3) + 1] + "," + spawnLocations[(dimension.id * 3) + 2]);
			} else
			{
				ChunkCoordinates coords = new ChunkCoordinates(bedSpawnLocations[(dimension.id * 3) + 0], bedSpawnLocations[(dimension.id * 3) + 1], bedSpawnLocations[(dimension.id * 3) + 2]);
				player.setSpawnChunk(coords, true);
				System.out.println("	Bed is still present at respawn coordinates.");
				System.out.println("	Bed spawn coordinates for " + dimension.name + " on death are " + bedSpawnLocations[(dimension.id * 3) + 0] + "," + bedSpawnLocations[(dimension.id * 3) + 1] + "," + bedSpawnLocations[(dimension.id * 3) + 2]);
			}
		} else
		{
			System.out.println("	Bed spawn point has not been set for this dimension.");
			System.out.println("	Normal spawn coordinates for " + dimension.name + " on death are " + spawnLocations[(dimension.id * 3) + 0] + "," + spawnLocations[(dimension.id * 3) + 1] + "," + spawnLocations[(dimension.id * 3) + 2]);
                        ChunkCoordinates coords = new ChunkCoordinates(spawnLocations[(dimension.id * 3) + 0], spawnLocations[(dimension.id * 3) + 1], spawnLocations[(dimension.id * 3) + 2]);
			player.setSpawnChunk(coords, true);
                }
                
		saveSpawnData(nbttagcompound, spawnLocations, bedSpawnLocations);
		
                
		if(player.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
        {
			return;
		}
		ItemStack[] dropsToAdd = new ItemStack[player.inventory.mainInventory.length];
		boolean hasChest = false;
		ItemStack itemstack;
		for(int i = 0; i < player.inventory.mainInventory.length; i++)
		{
			itemstack = player.inventory.mainInventory[i];
			if(!hasChest && itemstack != null && itemstack.getItem() == Item.getItemFromBlock(AllDemDimensions.amberChest))
			{
				hasChest = true;
				itemstack.stackSize--;
				if(itemstack.stackSize <= 0)
				{
					itemstack = null;
				}
			}
			if(itemstack != null)
			{
				dropsToAdd[i] = itemstack.copy();
				player.inventory.setInventorySlotContents(i, null);
			}
		}
		ItemStack itemstack1;
		if(hasChest)
		{
			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);
			if(y < 0)
			{
				y = 0;
			}
			player.worldObj.setBlock(x, y, z, AllDemDimensions.amberChest, 0, 3);
			TileEntityChest chest = (TileEntityChest)player.worldObj.getTileEntity(x, y, z);
			for(byte b = 0; b < dropsToAdd.length; b++)
			{
				itemstack1 = dropsToAdd[b];
				if(itemstack1 != null)
				{
					chest.setInventorySlotContents(b, itemstack1);
				}
			}
		}
	}
	
	@SubscribeEvent
	public void onPlayerSleepInBed(PlayerSleepInBedEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		NBTTagCompound nbttagcompound = player.getEntityData();
		int[] bedSpawnLocations = loadSpawnData(nbttagcompound, nbt_bedSpawnPoints);
		if(bedSpawnLocations == null || bedSpawnLocations.length != Dimension.allDimensions.size() * 3)//15
        {
            bedSpawnLocations = new int[Dimension.allDimensions.size() * 3];
            Arrays.fill(bedSpawnLocations, -1);
        }
		Dimension dimension = Dimension.getDimensionForId_MC(player.dimension);
		bedSpawnLocations[(dimension.id * 3) + 0] = event.x;
		bedSpawnLocations[(dimension.id * 3) + 1] = event.y;
		bedSpawnLocations[(dimension.id * 3) + 2] = event.z;
		System.out.println("[ADD] Setting bed point for " + dimension.name + " at " + 
		bedSpawnLocations[(dimension.id * 3) + 0] + "," + bedSpawnLocations[(dimension.id * 3) + 1] + "," + bedSpawnLocations[(dimension.id * 3) + 2]);
		saveSpawnData(nbttagcompound, loadSpawnData(nbttagcompound, nbt_defaultSpawnPoints), bedSpawnLocations);
	}
	
	
    public void onServerTick(EntityPlayerMP player)
	{
        NBTTagCompound nbttagcompound = player.getEntityData();
		Block currentPortal = Block.getBlockById(nbttagcompound.getShort("ADDCurrentPortal"));
        short portalTime = nbttagcompound.getShort("ADDPortalTime");
        boolean canTeleport = nbttagcompound.getBoolean("ADDCanTeleport");
        boolean isTeleporting = nbttagcompound.getBoolean("ADDIsTeleporting");
        int[] spawnLocations = loadSpawnData(nbttagcompound, nbt_defaultSpawnPoints);
        Dimension playerDimension = Dimension.getDimensionForId_MC(player.dimension);
        if(playerDimension == null)
        {
            return;
        }
        
        if(spawnLocations == null || spawnLocations.length != Dimension.allDimensions.size() * 3)
        {
            System.out.println("[ADD] Spawn points have not been set.");
            spawnLocations = new int[Dimension.allDimensions.size() * 3];
            Arrays.fill(spawnLocations, -1);
        }
        if(spawnLocations[(playerDimension.id * 3) + 1] == -1)
        {
            spawnLocations[(playerDimension.id * 3) + 0] = (int)player.posX;
            spawnLocations[(playerDimension.id * 3) + 1] = (int)player.posY;
            spawnLocations[(playerDimension.id * 3) + 2] = (int)player.posZ;
            System.out.println("[ADD] Set player spawn point for " + playerDimension.name + ": " + (int)player.posX + ", " + (int)player.posY + ", " + (int)player.posZ);
        }                
        player.playerNetServerHandler.sendPacket(new S05PacketSpawnPosition(spawnLocations[(playerDimension.id * 3) + 0], spawnLocations[(playerDimension.id * 3) + 1], spawnLocations[(playerDimension.id * 3) + 2]));
        
        
		boolean isInPortal = false;
		Block portalBlockId;
		for(Dimension realm : Dimension.allDimensions)
		{
			portalBlockId = realm.portalBlockId;
			if(AllDemDimensions.isInPortal(player, portalBlockId))
			{
				isInPortal = true;
				if(currentPortal != portalBlockId)
				{
					portalTime = 0;
					isTeleporting = false;
				}
				if(canTeleport)
				{
					currentPortal = portalBlockId;
					portalTime++;
				}
			}
		}
		
		if(!isInPortal)
		{
			if(!isTeleporting)
			{
				canTeleport = true;
			}
			currentPortal = null;
			portalTime = 0;
		}
		
		if(portalTime > 100)
		{
			portalTime = 0;
			Dimension dimension = Dimension.getDimensionForPortal(currentPortal);
			int dimId = dimension.dimensionId;
			Teleporter teleporter = dimension.getTeleporterForId(player.dimension);
			MinecraftServer minecraftserver = MinecraftServer.getServer();
			minecraftserver.getConfigurationManager().transferPlayerToDimension(player, dimId, teleporter);
			canTeleport = false;
			isTeleporting = true;
		}
                
        nbttagcompound.setShort("ADDCurrentPortal", (short)Block.getIdFromBlock(currentPortal));
        nbttagcompound.setShort("ADDPortalTime", portalTime);
        nbttagcompound.setBoolean("ADDCanTeleport", canTeleport);
        nbttagcompound.setBoolean("ADDIsTeleporting", isTeleporting);
        saveSpawnData(nbttagcompound, spawnLocations, loadSpawnData(nbttagcompound, nbt_bedSpawnPoints));		
	}
        
    private int[] loadSpawnData(NBTTagCompound nbttagcompound, String nbtId)
    {
    	NBTTagCompound nbttagcompound2 = nbttagcompound.getCompoundTag(EntityPlayer.PERSISTED_NBT_TAG);
    	return nbttagcompound2.getIntArray(nbtId);
    }
    
    private void saveSpawnData(NBTTagCompound nbttagcompound, int[] spawnLocations, int[] bedSpawnLocations)
    {
        NBTTagCompound nbttagcompound2 = new NBTTagCompound();
        nbttagcompound2.setIntArray(nbt_defaultSpawnPoints, spawnLocations);
        nbttagcompound2.setIntArray(nbt_bedSpawnPoints, bedSpawnLocations);
        nbttagcompound.setTag(EntityPlayer.PERSISTED_NBT_TAG, nbttagcompound2);
    }
    
    @SideOnly(Side.CLIENT)
    public static void onClientTick(EntityPlayerSP player, World world)
    {
    	NBTTagCompound nbttagcompound = player.getEntityData();
    	int swingCooldown = nbttagcompound.getInteger("ADDSwingCooldown");
    	if(player.swingProgress == 0)
    	{
    		if(swingCooldown > 0)
    		{
    			swingCooldown--;
    		}
    	} else
    	{
    		swingCooldown = 11;
    	}
    	nbttagcompound.setInteger("ADDSwingCooldown", swingCooldown);
    	
        player.timeInPortal = 0;
        player.prevTimeInPortal = 0;
        boolean isInPortal = false;
        Block portalBlockId;
        for(Dimension realm : Dimension.allDimensions)
        {
                portalBlockId = realm.portalBlockId;
                if(AllDemDimensions.isInPortal(player, portalBlockId))
                {
                        isInPortal = true;
                        if(currentPortal_client != portalBlockId)
                        {
                                portalTime_client = 0;
                                isTeleporting_client = false;
                        }
                        if(canTeleport_client)
                        {
                                currentPortal_client = portalBlockId;
                                portalTime_client++;
                        }
                }
        }
        if(!isInPortal)
        {
                if(!isTeleporting_client)
                {
                        canTeleport_client = true;
                }
                currentPortal_client = null;
                portalTime_client = 0;
        }

        if(portalTime_client > 100)
        {
                portalTime_client = 0;
                canTeleport_client = false;
                isTeleporting_client = true;
        }
    }
	
    @SubscribeEvent
    public void onBreakBlock(PlayerEvent.BreakSpeed event)
    {
        EntityPlayer player = event.entityPlayer;
		ItemStack itemstack = player.getCurrentEquippedItem();
		if(itemstack != null && itemstack.getItem() instanceof ItemADDTool)
		{
			float strength = itemstack.getItem().getDigSpeed(itemstack, event.block, event.metadata);
			if(((ItemADDTool)itemstack.getItem()).material == EnumToolMaterialADD.EMERALD)
			{
				if(player.isPotionActive(Potion.digSpeed))
				{
					strength *= 1.0F + (float)(player.getActivePotionEffect(Potion.digSpeed).getAmplifier() + 1) * 0.2F;
				}
				if(player.isPotionActive(Potion.digSlowdown))
				{
					strength *= 1.0F - (float)(player.getActivePotionEffect(Potion.digSlowdown).getAmplifier() + 1) * 0.2F;
				}
				if(player.isInsideOfMaterial(Material.water) && !EnchantmentHelper.getAquaAffinityModifier(player))
				{
					strength /= 5.0F;
				}
				if(strength < 0)
				{
					strength = 0;
				}
			}
		}
	}///FINISH

	public static short portalTime_client = 0;
	public static boolean canTeleport_client = true;
	public static Block currentPortal_client = null;
	public static boolean isTeleporting_client = false;
	public static final String nbt_defaultSpawnPoints = "ADDDefaultSpawnPoints";
	public static final String nbt_bedSpawnPoints = "ADDBedSpawnPoints";
}
