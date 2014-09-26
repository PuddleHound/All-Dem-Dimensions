package alldemdimensions.world.environment;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import alldemdimensions.block.AstronomyManager;
import alldemdimensions.util.IDataHandler;
import alldemdimensions.util.NBTDataHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Planet implements IDataHandler
{

	public Planet(int i, float startX, float startZ, float f, String s, String s1, int j, int k)
	{
		id = i;
		//posX = startX;
		//posZ = startZ;
		//posX = -1F;
		//posZ = -1F;
		size = f;
		name = s;
		theme = s1;
		guiOffsetX = j;
		guiOffsetY = k;
		//destX = posX;
		//destZ = posZ;
		//allPlanets.add(this);
		allPlanets[id] = this;
		NBTDataHandler.register(this);
        texture = new ResourceLocation("alldemdimensions", "textures/sky/" + name + ".png");
	}
	
    @Override
	public void saveData(NBTTagCompound nbttagcompound)
	{
		nbttagcompound.setFloat(name + "PosX", posX);
		nbttagcompound.setFloat(name + "PosZ", posZ);
		nbttagcompound.setFloat(name + "DestX", destX);
		nbttagcompound.setFloat(name + "DestZ", destZ);
		nbttagcompound.setInteger(name + "FreezeTime", freezeTime);
	}
	
    @Override
	public void loadData(NBTTagCompound nbttagcompound)
	{
		posX = nbttagcompound.getFloat(name + "PosX");
		posZ = nbttagcompound.getFloat(name + "PosZ");
		destX = nbttagcompound.getFloat(name + "DestX");
		destZ = nbttagcompound.getFloat(name + "DestZ");
		freezeTime = nbttagcompound.getInteger(name + "FreezeTime");
	}
	
	@SideOnly(Side.CLIENT)
	public void render(Minecraft mc)
	{
		Tessellator tessellator = Tessellator.instance;
		mc.renderEngine.bindTexture(texture);
		GL11.glPushMatrix();
		GL11.glRotatef(posX, 0F, 1F, 0F);
		GL11.glRotatef(posZ, 0F, 0F, 1F);
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV((double)(-size), 100F, (double)(-size), 0.0D, 0.0D);
        tessellator.addVertexWithUV((double)size, 100F, (double)(-size), 1.0D, 0.0D);
        tessellator.addVertexWithUV((double)size, 100F, (double)size, 1.0D, 1.0D);
        tessellator.addVertexWithUV((double)(-size), 100F, (double)size, 0.0D, 1.0D);
        tessellator.draw();
		GL11.glPopMatrix();
	}
	
	public void onUpdate(World world, Random random)
	{
		if(Math.floor(posX) == Math.floor(destX) || Math.floor(posZ) == Math.floor(destZ))//used to be in freezeTime <= 0 block
		{
			destX = (float)random.nextInt(360);
			destZ = (float)random.nextInt(360);
		}
		if(posX == 0F)
		{
			posX = (float)random.nextInt(360);
		}
		if(posZ == 0F)
		{
			posZ = (float)random.nextInt(360);
		}
		if(freezeTime <= 0)
		{
			float diffX = destX - posX;
			float diffZ = destZ - posZ;
			float diffTotal = Math.abs(diffX) + Math.abs(diffZ);
			posX += 0.005F * (diffX / diffTotal);
			posZ += 0.005F * (diffZ / diffTotal);
		} else
		{
			freezeTime--;
		}
		float offset;
		currentConstellation = null;
		AstronomyManager.clearAllEffects();
		for(Constellation constellation : Constellation.allConstellations)
		{
			if(constellation != null)
			{
				offset = size * 2.0F;
				if((posX + offset) % 360F >= constellation.minX && (posX - offset) % 360F <= constellation.maxX &&
					(posZ + offset) % 360F >= constellation.minZ && (posZ - offset) % 360F <= constellation.maxZ)
				{
					currentConstellation = constellation;
					constellation.onPlanetCrossed(this);
				}
			}
		}
	}
	
	public static void sendInfoToClient_old(World world)
	{
		try
		{
			ByteArrayOutputStream stream = new ByteArrayOutputStream(allPlanets.length * 13);
			DataOutputStream stream1 = new DataOutputStream(stream);
			byte constellationId;
			for(Planet planet : allPlanets)
			{
				stream1.writeFloat(planet.posX);
				stream1.writeFloat(planet.posZ);
				stream1.writeInt(planet.freezeTime);
				constellationId = 0;
				if(planet.currentConstellation != null)
				{
					constellationId = planet.currentConstellation.id;
				}
				stream1.writeByte(constellationId);
			}
			/*Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "ADD|PlanetPos";
			packet.data = stream.toByteArray();
			packet.length = stream.size();
			PacketDispatcher.sendPacketToAllInDimension(packet, world.provider.dimensionId);*///1.7.2 n
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/*public static void sendInfoToClient(World world)
	{
		
		ByteBuf bytebuf = Unpooled.buffer();
		ByteBufOutputStream packetbuffer = new ByteBufOutputStream(bytebuf);
		try
        {
            //PacketBuffer packetbuffer = new PacketBuffer(bytebuf);
            byte constellationId;
			for(Planet planet : allPlanets)
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
            AllDemDimensions.channel.sendToDimension(new FMLProxyPacket(bytebuf.copy(), "ADD|PlanetPos"), Dimension.zenith.dimensionId);
        }
        catch (Exception exception)
        {
            LogManager.getLogger().error("Couldn\'t send planet info", exception);
        }
        finally
        {
            bytebuf.release();
        }
	}*/
	
	public static void initPlanets()
	{
	}
	
	public void freezePlanet(int i)
	{
		freezeTime = i;
	}
	
	public void unfreezePlanet()
	{
		freezeTime = 0;
	}
	
	public static byte[] getTimeInMinutes(int i)
	{
		int j = (int)Math.floor(i / 20D);
		byte minutes = (byte)Math.floor(j / 60D);
		byte seconds = (byte)(j % 60);
		return new byte[]{minutes, seconds};
	}
	
	public static int getTimeInTicks(int minutes, int seconds)
	{
		return (minutes * 1200) + (seconds * 20);
	}
	
	public static int calculateFreezeCost(int oldTime, int newTime)
	{
		int difference = Math.abs(newTime - oldTime);
		int cost = (int)Math.ceil(difference / 300);
		return cost;
	}
	
	public float posX;
	public float posZ;
	public float destX;
	public float destZ;
	public float size;
	public String name;
	public String theme;
	public int guiOffsetX;
	public int guiOffsetY;
	public int id;
	public int freezeTime;
	public Constellation currentConstellation;
	public static Planet[] allPlanets = new Planet[8];
	public int tempFreezeTime = -1;
    public ResourceLocation texture;
	
	public static final Planet ares = new Planet(0, 248, 137, 3F, "Ares", "War", 5, 18);
	public static final Planet atlas = new Planet(1, 123, 22, 4F, "Atlas", "Power", 87, 18);
	public static final Planet hermes = new Planet(2, 49, 241, 2F, "Hermes", "Swiftness", 169, 90);
	public static final Planet tyche = new Planet(3, 39, 54, 3.5F, "Tyche", "Fortune", 5, 90);
	public static final Planet persephone = new Planet(4, 210, 278, 2.5F, "Persephone", "Growth", 5, 162);
	public static final Planet limos = new Planet(5, 167, 341, 1.5F, "Limos", "Famine", 169, 162);
	public static final Planet penia = new Planet(6, 106, 183, 3.25F, "Penia", "Poverty", 87, 162);
	public static final Planet eirene = new Planet(7, 76, 325, 2.75F, "Eirene", "Peace", 169, 18);
}
