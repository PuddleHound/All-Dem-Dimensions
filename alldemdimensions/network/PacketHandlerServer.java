package alldemdimensions.network;

import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.block.ContainerEasel;
import alldemdimensions.block.TileEntityEasel;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public class PacketHandlerServer extends PacketHandler
{

	@SubscribeEvent
	public void receivePacket(ServerCustomPacketEvent event)
	{
		EntityPlayerMP player = ((NetHandlerPlayServer)event.handler).playerEntity;
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
		
		C17PacketCustomPayload packet = (C17PacketCustomPayload)event.packet.toC17Packet();
		
		if(event.packet.channel().equals("ADD|Painting"))
		{
			System.out.println("Receiving packet data");
            try
            {
            	PacketBuffer packetbuffer = new PacketBuffer(Unpooled.wrappedBuffer(packet.func_149558_e()));
                ItemStack itemstack = packetbuffer.readItemStackFromBuffer();
				ItemStack itemstack1 = ((ContainerEasel)player.openContainer).tileEntityEasel.getStackInSlot(TileEntityEasel.PAINTING_SLOT);
                if(itemstack != null && itemstack.getItem() == AllDemDimensions.canvas)
                {
					if(itemstack1 == null)//prevents NPE
					{
						itemstack1 = new ItemStack(AllDemDimensions.canvas, 1);
						((ContainerEasel)player.openContainer).putStackInSlot(TileEntityEasel.PAINTING_SLOT, itemstack1);
					}
					System.out.println("Found sent ItemStack instance");
					NBTTagCompound data = itemstack1.getTagCompound();
					if(data == null)
					{
						itemstack1.setTagCompound(new NBTTagCompound());
						data = itemstack1.getTagCompound();
						System.out.println("Data for new ItemStack is null");
					}
                    data.setIntArray("pixels", itemstack.getTagCompound().getIntArray("pixels"));
					
					if(itemstack.getTagCompound().getIntArray("pixels") == null)
					{
						System.out.println("Pixel data from sent ItemStack is null");
					}
					
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
	}
	
	
	
}
