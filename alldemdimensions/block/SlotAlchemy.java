package alldemdimensions.block;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SlotAlchemy extends Slot
{

    public SlotAlchemy(IInventory iinventory, World world, EntityPlayer entityplayer, int i, int j, int k)
    {
		super(iinventory, i, j, k);
		worldObj = world;
		player = entityplayer;
    }

    @Override
    public void onSlotChange(ItemStack itemstack, ItemStack itemstack1)
    {
        super.onSlotChange(itemstack, itemstack1);
    }

    @Override
	public boolean isItemValid(ItemStack itemstack)
    {
		if(getSlotIndex() >= InventoryAlchemy.SLOT_RESULT_START)
		{
			return false;
		}
        return true;
    }
	
    @Override
    protected void onCrafting(ItemStack itemstack, int i) {}

    @Override
    protected void onCrafting(ItemStack itemstack) {}

    @Override
    public void onPickupFromSlot(EntityPlayer entityplayer, ItemStack itemstack)
    {
		if(getSlotIndex() >= InventoryAlchemy.SLOT_RESULT_START)
		{
			System.out.println("Taking result");
			((InventoryAlchemy)inventory).onResultTaken(itemstack);
		} else
		if(getSlotIndex() == InventoryAlchemy.SLOT_CATALYST || getSlotIndex() == InventoryAlchemy.SLOT_MODIFIER)
		{
			((InventoryAlchemy)inventory).onCatalystOrModifierTaken(itemstack, this);
		}
		//super.onPickupFromSlot(entityplayer, itemstack);
    }
	
	
    @Override
    public void onSlotChanged()
    {
        super.onSlotChanged();
		if(worldObj.isRemote)
		{
			return;
		}
		if(getSlotIndex() < InventoryAlchemy.SLOT_RESULT_START)
		{
			System.out.println("Checking for result");
			((InventoryAlchemy)inventory).checkForResult(worldObj.rand);
			sendResultToClient();
		}
    }
	
	private void sendResultToClient()
	{
		cpw.mods.fml.relauncher.Side side = cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide();
		if(side != cpw.mods.fml.relauncher.Side.SERVER)
		{
			return;
		}
		///ByteArrayOutputStream stream = new ByteArrayOutputStream(180);
		///DataOutputStream stream1 = new DataOutputStream(stream);
		try
		{
			for(ItemStack itemstack : ((InventoryAlchemy)inventory).items)
			{
				///Packet.writeItemStack(itemstack, stream1);
			}
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		///Packet250CustomPayload packet = new Packet250CustomPayload("ADD|Alchemy", stream.toByteArray());///1.7.2
		/*packet.channel = "ADD|Painting";
		packet.data = stream.toByteArray();*/
		///packet.length = stream.size();
		///((EntityPlayerMP)player).playerNetServerHandler.sendPacketToPlayer(packet);
		//PacketDispatcher.sendPacketToPlayer(packet, (EntityPlayerMP)player);
		//((EntityClientPlayerMP)player).sendQueue.addToSendQueue(packet);
	}

    @Override
    public int getSlotStackLimit()
    {
        return 64;
    }
	
	public World worldObj;
	public EntityPlayer player;
}
