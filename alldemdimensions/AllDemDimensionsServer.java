package alldemdimensions;

import alldemdimensions.network.PacketHandlerServer;

public class AllDemDimensionsServer extends AllDemDimensionsProxy
{
	
	@Override
	public void init()
	{
		AllDemDimensions.channel.register(new PacketHandlerServer());
	}

}
