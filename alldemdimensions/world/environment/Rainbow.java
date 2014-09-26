package alldemdimensions.world.environment;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import alldemdimensions.world.DimensionZenith;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
//import cpw.mods.fml.common.network.PacketDispatcher;
import org.lwjgl.opengl.GL11;

public class Rainbow
{

	public Rainbow()
	{
		System.out.println("Spawned rainbow");
	}
	
	public void onUpdate(World world)
	{
		age++;
		if(age >= maxAge)
		{
			DimensionZenith.currentRainbow = null;
			maxAge = 0;
		}
		sendInfoToClient(world);
	}
	
	public void sendInfoToClient(World world)
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream(24);
		DataOutputStream stream1 = new DataOutputStream(stream);
		try 
		{
			stream1.writeInt(age);
			stream1.writeInt(maxAge);
			stream1.writeInt(posX);
			stream1.writeInt(posZ);
			stream1.writeInt(sizeX);
			stream1.writeInt(sizeZ);
		} 
		catch(Exception e) 
		{
			e.printStackTrace();
		}
		/*Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "ADD|Rainbow";
		packet.data = stream.toByteArray();
		packet.length = stream.size();
		PacketDispatcher.sendPacketToAllInDimension(packet, world.provider.dimensionId);*///1.7.2 n
	}
	
	@SideOnly(Side.CLIENT)
	public void render(Minecraft mc)
	{
		int alpha = 5;
		if(maxAge - age < 200)
		{
			alpha = (maxAge - age) / 40;
		}
		if(age < 200)
		{
			alpha = age / 40;
		}
		GL11.glPushMatrix();
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(texture);
		Tessellator tessellator = Tessellator.instance;
		GL11.glRotatef(posX, 0F, 1F, 0F);
		GL11.glRotatef(posZ, 0F, 0F, 1F);
		tessellator.startDrawing(5);
		for(int counter = 0; counter < 360; counter++)
		{
			if(Math.sin(counter) > 0)
			{
				tessellator.setColorRGBA(255, 255, 255, 0);
			} else
			{
				tessellator.setColorRGBA(32, 0, 0, alpha);
			}
			tessellator.addVertexWithUV((double)(Math.cos(counter) * sizeX), 100F, (double)(Math.sin(counter) * sizeZ), 0.0D, 0.0D);//64
		}
		for(int counter = 0; counter < 360; counter++)
		{
			if(Math.sin(counter) > 0)
			{
				tessellator.setColorRGBA(255, 255, 255, 0);
			} else
			{
				tessellator.setColorRGBA(0, 32, 0, alpha);
			}
			tessellator.addVertexWithUV((double)(Math.cos(counter) * (sizeX * 0.85D)), 100F, (double)(Math.sin(counter) * (sizeZ * 0.85D)), 0.0D, 0.0D);//54
		}
		for(int counter = 0; counter < 360; counter++)
		{
			if(Math.sin(counter) > 0)
			{
				tessellator.setColorRGBA(255, 255, 255, 0);
			} else
			{
				tessellator.setColorRGBA(0, 0, 32, alpha);
			}
			tessellator.addVertexWithUV((double)(Math.cos(counter) * (sizeX * 0.68D)), 100F, (double)(Math.sin(counter) * (sizeZ * 0.68D)), 0.0D, 0.0D);//44
		}
		tessellator.draw();
		GL11.glPopMatrix();
	}
	
	public int age;
	public int maxAge;
	public int posX;
	public int posZ;
	public int sizeX;
	public int sizeZ;
    public ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/sky/blank.png");
}
