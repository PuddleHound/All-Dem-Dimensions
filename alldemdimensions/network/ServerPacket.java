package alldemdimensions.network;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ServerCustomPacketEvent;

public abstract class ServerPacket extends ADDPacket
{
	
	public ServerPacket(String s)
	{
		super(s);
	}

	@SubscribeEvent
	public abstract void receive(ServerCustomPacketEvent event);

}
