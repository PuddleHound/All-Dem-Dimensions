package alldemdimensions.item;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.entity.EntityCustomPainting;

public class ItemCanvas extends Item
{
    public ItemCanvas()
    {
        super();
		setMaxStackSize(1);
    }
	
    @Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ)
    {
        if (side == 0)
        {
            return false;
        }
        else if (side == 1)
        {
            return false;
        }
        else
        {
            int direction = Direction.facingToDirection[side];
			int[] pixelData = null;
			NBTTagCompound data = itemstack.getTagCompound();
			if(data != null)
			{
				pixelData = data.getIntArray("pixels"); 
			} else
			{
				pixelData = new int[512 * 512];
				int i = -16777216 | (255 << 16) | (255 << 8) | 255;
				for(int j = 0; j < pixelData.length; j++)
				{
					pixelData[j] = i;
				}
			}
            EntityCustomPainting painting = new EntityCustomPainting(world, x, y, z, direction, pixelData);
			painting.pixels = pixelData;
			
            if (!entityplayer.canPlayerEdit(x, y, z, side, itemstack))
            {
                return false;
            }
            else
            {
                if (painting != null && painting.onValidSurface())
                {
                    if (!world.isRemote)
                    {
                        world.spawnEntityInWorld(painting);
                        //sendPaintingSpawnPacket(painting);
                    }

                    itemstack.stackSize--;
                }

                return true;
            }
        }
    }
	
	private void sendPaintingSpawnPacket(EntityCustomPainting painting)
	{
		System.out.println("Attempting to send packet");
		ByteArrayOutputStream stream = new ByteArrayOutputStream(1044);
		DataOutputStream stream1 = new DataOutputStream(stream);
		try 
		{
			stream1.writeInt(painting.getEntityId());
			stream1.writeInt(painting.hangingDirection);
			stream1.writeInt(painting.field_146063_b);
			stream1.writeInt(painting.field_146064_c);
			stream1.writeInt(painting.field_146062_d);
			for(int i : painting.pixels)
			{
				stream1.writeInt(i);
			}
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		/*Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "ADD|PaintSpawn";
		packet.data = stream.toByteArray();
		packet.length = stream.size();
		PacketDispatcher.sendPacketToAllInDimension(packet, painting.worldObj.provider.dimensionId);*/
	}
	
    @Override
	public void onCreated(ItemStack itemstack, World world, EntityPlayer entityplayer)
	{
		int[] pixelData = new int[16 * 16];
		int i = -16777216 | (255 << 16) | (255 << 8) | 255;
		for(int j = 0; j < pixelData.length; j++)
		{
			pixelData[j] = i;
		}
		itemstack.setTagCompound(new NBTTagCompound());
		itemstack.getTagCompound().setIntArray("pixels", pixelData);
	}
}
