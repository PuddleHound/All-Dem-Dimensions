package alldemdimensions.network;

import java.util.HashMap;

import alldemdimensions.AllDemDimensions;
import cpw.mods.fml.common.network.FMLEventChannel;
import cpw.mods.fml.common.network.FMLNetworkEvent.CustomPacketEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

public abstract class ADDPacket
{

	public ADDPacket(String s)
	{
		if(s.length() > 16)
		{
			AllDemDimensions.print("Channel name " + s + " contains too many characters; channel will not be registered.");
			return;
		}
		channelName = s;
		channelToPacket.put(channelName, this);
	}
	
	public static void registerChannels()
	{
		for(ADDPacket packet : channelToPacket.values())
		{
			packet.channel = NetworkRegistry.INSTANCE.newEventDrivenChannel(packet.channelName);
			packet.channel.register(packet);
		}
	}
	
	public abstract void send();
	
	public abstract void receive(CustomPacketEvent event);
	
	public FMLEventChannel channel;
	public String channelName;
	public static final HashMap<String, ADDPacket> channelToPacket = new HashMap<String, ADDPacket>();
	public static final ADDPacket server_planetInfo = new ServerPacketPlanetInfo("ADD|PlanetPos");
	
}
