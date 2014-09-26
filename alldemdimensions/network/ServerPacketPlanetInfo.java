package alldemdimensions.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;

import org.apache.logging.log4j.LogManager;

import alldemdimensions.world.Dimension;
import alldemdimensions.world.environment.Constellation;
import alldemdimensions.world.environment.Planet;
import cpw.mods.fml.common.network.FMLNetworkEvent.CustomPacketEvent;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;

public class ServerPacketPlanetInfo extends ADDPacket
{

	public ServerPacketPlanetInfo(String s)
	{
		super(s);
	}
	
	public void send()
	{
		ByteBuf bytebuf = Unpooled.buffer();
		ByteBufOutputStream packetbuffer = new ByteBufOutputStream(bytebuf);
		try
        {
            //PacketBuffer packetbuffer = new PacketBuffer(bytebuf);
            byte constellationId;
			for(Planet planet : Planet.allPlanets)
			{
				packetbuffer.writeFloat(planet.posX);
				packetbuffer.writeFloat(planet.posZ);
				packetbuffer.writeInt(planet.freezeTime);
				constellationId = 0;
				if(planet.currentConstellation != null)
				{
					constellationId = planet.currentConstellation.id;
				}
				packetbuffer.writeByte(constellationId);
			}
            channel.sendToDimension(new FMLProxyPacket(bytebuf.copy(), channelName), Dimension.zenith.dimensionId);
        }
        catch (Exception exception)
        {
            LogManager.getLogger().error("Couldn\'t send planet info", exception);
        }
        finally
        {
            bytebuf.release();
        }
	}
	
	public void receive(CustomPacketEvent event)
	{
		//S3FPacketCustomPayload packet = (S3FPacketCustomPayload)event.packet.toS3FPacket();
		//ByteBuf bytebuf = Unpooled.wrappedBuffer(packet.func_149168_d());
		ByteBufInputStream bbis = new ByteBufInputStream(event.packet.payload());
		try
        {
            //PacketBuffer packetbuffer = new PacketBuffer(bytebuf);
			for(Planet planet: Planet.allPlanets)
			{
				planet.posX = bbis.readFloat();
				planet.posZ = bbis.readFloat();
				planet.freezeTime = bbis.readInt();
				planet.currentConstellation = Constellation.allConstellations[bbis.readByte()];
			}
			System.out.println("Received server planet position packet successfully.");
		}
        catch (Exception e)
        {
            e.printStackTrace();
        }
		finally
		{
			//bytebuf.release();
		}
	}
	
}
