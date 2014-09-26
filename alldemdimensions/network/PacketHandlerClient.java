package alldemdimensions.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientCustomPacketEvent;

public class PacketHandlerClient extends PacketHandler
{
	
	@SubscribeEvent
	public void receivePacket(ClientCustomPacketEvent event)
	{
		/*EntityPlayer player = Minecraft.getMinecraft().thePlayer;
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
		
		if(event.packet.channel().equals("ADD|PlanetPos"))
		{

		}*/
		
		ADDPacket addpacket = ADDPacket.channelToPacket.get(event.packet.channel());
		if(addpacket != null)
		{
			addpacket.receive(event);
		}
	}

}
