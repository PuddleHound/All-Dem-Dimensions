package alldemdimensions.block;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import alldemdimensions.AllDemDimensions;

public class TileEntityEasel extends TileEntity implements IInventory
{

    @Override
    public int getSizeInventory()
    {
        return 22;
    }

    @Override
    public ItemStack getStackInSlot(int index)
    {
        return this.paintBottles[index];
    }
	
	public void checkForColorMix()
	{
		ItemStack itemstack;
		boolean water = false;
		boolean dye = false;
		boolean wax = false;
		boolean empty = false;
		int dyeMeta = 0;
		ArrayList<ItemStack> paints = new ArrayList<ItemStack>();
		Item id;
		for(byte b = 17; b < 21; b++)
		{
			itemstack = getStackInSlot(b);
			if(itemstack != null)
			{
				id = itemstack.getItem();
				if(id == AllDemDimensions.paintBottle)
				{
					paints.add(itemstack);
				} else
				{
					paints = null;
				}
				if(id == AllDemDimensions.wax)
				{
					wax = true;
				}
				if(id == Items.glass_bottle && itemstack.getItemDamage() == 0)//1.7.2
				{
					water = true;
				}
				if(id == Items.dye)
				{
					dye = true;
					dyeMeta = itemstack.getItemDamage();
				}
			} else
			{
				empty = true;
			}
		}
		boolean bottlesSufficientlyFull = true;
		if(water && dye && wax && empty)
		{
			System.out.println("Slots contain the proper items");
			ItemStack result = new ItemStack(AllDemDimensions.paintBottle, 1, 0);
			result.setTagCompound(new NBTTagCompound());
			result.getTagCompound().setInteger("paintColor", dyeToColorMap.get(dyeMeta));
			setInventorySlotContents(21, result);
		} else
		if(paints != null && paints.size() > 1)
		{
			NBTTagCompound data;
			int color = 0;
			int red = 0;
			int green = 0;
			int blue = 0;
			int counter = 0;
			for(ItemStack paint : paints)
			{
				if(paint.hasTagCompound())
				{
					data = paint.getTagCompound();
					color = data.getInteger("paintColor");
					if(color != 0)
					{
						red += color >> 16 & 255;
						green += color >> 8 & 255;
						blue += color & 255;
						counter++;
					}
				}
			}
			for(ItemStack paint : paints)
			{
				if(paint.getItemDamage() + (paint.getMaxDamage() / counter) > paint.getMaxDamage())
				{
					bottlesSufficientlyFull = false;
				}
			}
			if(bottlesSufficientlyFull)
			{
				red /= counter;
				green /= counter;
				blue /= counter;
				ItemStack result = new ItemStack(AllDemDimensions.paintBottle, 1, 0);
				result.setTagCompound(new NBTTagCompound());
				result.getTagCompound().setInteger("paintColor", 255 << 24 | red << 16 | green << 8 | blue);
				setInventorySlotContents(21, result);
				
				for(ItemStack paint : paints)
				{
					if(paint.getItemDamage() >= paint.getMaxDamage())
					{
						paint = new ItemStack(Items.glass_bottle);
					}
				}
			}
		} else
		if(getStackInSlot(21) != null || !bottlesSufficientlyFull)
		{
			decrStackSize(21, 1);
		}
	}

    @Override
    public ItemStack decrStackSize(int slotIndex, int amount)
    {
        if (this.paintBottles[slotIndex] != null)
        {
            ItemStack itemstack;

            if (this.paintBottles[slotIndex].stackSize <= amount)
            {
                itemstack = this.paintBottles[slotIndex];
                this.paintBottles[slotIndex] = null;
                this.markDirty();//this.onInventoryChanged();//1.7.2
                return itemstack;
            }
            else
            {
                itemstack = this.paintBottles[slotIndex].splitStack(amount);

                if (this.paintBottles[slotIndex].stackSize == 0)
                {
                    this.paintBottles[slotIndex] = null;
                }

                this.markDirty();//this.onInventoryChanged();//1.7.2
                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

    @Override
    public ItemStack getStackInSlotOnClosing(int index)
    {
        if (this.paintBottles[index] != null)
        {
            ItemStack itemstack = this.paintBottles[index];
            this.paintBottles[index] = null;
            return itemstack;
        }
        else
        {
            return null;
        }
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemstack)
    {
        this.paintBottles[index] = itemstack;

        if (itemstack != null && itemstack.stackSize > this.getInventoryStackLimit())
        {
            itemstack.stackSize = this.getInventoryStackLimit();
        }
        
        this.markDirty();//this.onInventoryChanged();//1.7.2
    }

    @Override
    public String getInventoryName()
    {
        return "Easel";
    }

    @Override
    public void readFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Items", 10);//1.7.2
        this.paintBottles = new ItemStack[this.getSizeInventory()];

        for (int tagIndex = 0; tagIndex < nbttaglist.tagCount(); ++tagIndex)
        {
            NBTTagCompound slotData = (NBTTagCompound)nbttaglist.getCompoundTagAt(tagIndex);
            int slotIndex = slotData.getByte("Slot") & 255;

            if (slotIndex >= 0 && slotIndex < this.paintBottles.length)
            {
                this.paintBottles[slotIndex] = ItemStack.loadItemStackFromNBT(slotData);
            }
        }
    }

    @Override
    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeToNBT(nbttagcompound);
        NBTTagList nbttaglist = new NBTTagList();

        for (int slotIndex = 0; slotIndex < this.paintBottles.length; ++slotIndex)
        {
            if (this.paintBottles[slotIndex] != null)
            {
                NBTTagCompound slotData = new NBTTagCompound();
                slotData.setByte("Slot", (byte)slotIndex);
                this.paintBottles[slotIndex].writeToNBT(slotData);
                nbttaglist.appendTag(slotData);
            }
        }

        nbttagcompound.setTag("Items", nbttaglist);
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isUseableByPlayer(EntityPlayer entityplayer)
    {
        return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : entityplayer.getDistanceSq((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D) <= 64.0D;
    }

    @Override
    public void openInventory() {}

    @Override
    public void closeInventory() {}
	
    //@Override
	//public void onDataPacket(INetworkManager net, Packet132TileEntityData packet)
    {/*
		NBTTagCompound data = packet.customParam1;
		cpw.mods.fml.relauncher.Side side = cpw.mods.fml.common.FMLCommonHandler.instance().getEffectiveSide();
		if(side == cpw.mods.fml.relauncher.Side.CLIENT && getStackInSlot(PAINTING_SLOT) != null)
		{
			if(getStackInSlot(PAINTING_SLOT).hasTagCompound()
			getStackInSlot(PAINTING_SLOT).setTagCompound("pixels", data.getIntArray("pixels"));
		}*/
		//readFromNBT(packet.data);//customParam1);
    }//1.7.2
	
    @Override
	public void updateEntity()
	{
		NBTTagCompound data = new NBTTagCompound();
        this.writeToNBT(data);
		/*Packet packet = new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 1, data);
		cpw.mods.fml.common.network.PacketDispatcher.sendPacketToAllPlayers(packet);*///1.7.2
	}
	
    @Override
    public boolean isItemValidForSlot(int i, ItemStack itemstack)
    {
        return true;//needs changed
    }

	@Override
	public boolean hasCustomInventoryName()
	{
		return false;
	}
	
	public static final byte PAINTING_SLOT = 16;
	public ItemStack[] paintBottles = new ItemStack[22];
	public int painting_sizeX = 16;
	public int painting_sizeY = 16;
	public int[] painting_pixels = new int[painting_sizeX * painting_sizeY];
	public String painting_title;
	public String painting_author;
	private static HashMap<Integer, Integer> dyeToColorMap = new HashMap<Integer, Integer>();
	static
	{
		dyeToColorMap.put(0, 255 << 24 | 0 << 16 | 0 << 8 | 0);
		dyeToColorMap.put(1, 255 << 24 | 255 << 16 | 0 << 8 | 0);
		dyeToColorMap.put(2, 255 << 24 | 0 << 16 | 255 << 8 | 0);
		dyeToColorMap.put(3, 255 << 24 | 110 << 16 | 60 << 8 | 20);
		dyeToColorMap.put(4, 255 << 24 | 0 << 16 | 0 << 8 | 255);
		dyeToColorMap.put(5, 255 << 24 | 200 << 16 | 0 << 8 | 255);
		dyeToColorMap.put(6, 255 << 24 | 0 << 16 | 255 << 8 | 255);
		dyeToColorMap.put(7, 255 << 24 | 175 << 16 | 175 << 8 | 175);
		dyeToColorMap.put(8, 255 << 24 | 100 << 16 | 100 << 8 | 100);
		dyeToColorMap.put(9, 255 << 24 | 255 << 16 | 180 << 8 | 240);
		dyeToColorMap.put(10, 255 << 24 | 120 << 16 | 255 << 8 | 0);
		dyeToColorMap.put(11, 255 << 24 | 255 << 16 | 255 << 8 | 0);
		dyeToColorMap.put(12, 255 << 24 | 130 << 16 | 200 << 8 | 255);
		dyeToColorMap.put(13, 255 << 24 | 255 << 16 | 0 << 8 | 220);
		dyeToColorMap.put(14, 255 << 24 | 255 << 16 | 150 << 8 | 0);
		dyeToColorMap.put(15, 255 << 24 | 255 << 16 | 255 << 8 | 255);
	}
}
