package alldemdimensions.world.environment;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.Vec3;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Constellation
{

	public Constellation(byte b, int starCount)
	{
		starLocations = new Vec3[starCount];
		starSizes = new float[starCount];
		starColors = new byte[starCount];
		starAnimationStates = new byte[starCount];
		for(int i = 0; i < starAnimationStates.length; i++)
		{
			starAnimationStates[i] = (byte)random.nextInt(5);
		}
		id = b;
		allConstellations[id] = this;
	}
	
	@SideOnly(Side.CLIENT)
	public void render(Minecraft mc, float partialTicks)//make single texture file for stars (and planets?)
	{
		Tessellator tessellator = Tessellator.instance;
		float size;
		Vec3 vec3;
		float x;
		float y;
		float z;
		byte color;
		byte anim;
		//GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/alldemdimensions/misc/star.png"));
		for(int i = 0; i < starLocations.length; i++)
		{
			GL11.glPushMatrix();
			size = starSizes[i];
			size *= 1.5F;
			vec3 = starLocations[i];
			anim = starAnimationStates[i];
			x = (float)vec3.xCoord;
			y = (float)vec3.yCoord;
			z = (float)vec3.zCoord;
			color = starColors[i];
			GL11.glRotatef(x, 0F, 1F, 0F);
			GL11.glRotatef(z, 0F, 0F, 1F);
			if(color == COLOR_RED)
			{
				GL11.glColor3f(1.0F, 0.0F, 0.0F);
			} else
			if(color == COLOR_ORANGE)
			{
				GL11.glColor3f(1.0F, 0.5F, 0.0F);
			} else
			if(color == COLOR_YELLOW)
			{
				GL11.glColor3f(1.0F, 1.0F, 0.0F);
			} else
			if(color == COLOR_BLUE)
			{
				GL11.glColor3f(0.0F, 0.0F, 1.0F);
			}
			//GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("/alldemdimensions/misc/star.png"));
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)(-size), 100F, (double)(-size), 0.0D, anim * 0.1667F);
            tessellator.addVertexWithUV((double)size, 100F, (double)(-size), 1.0D, anim * 0.1667F);
            tessellator.addVertexWithUV((double)size, 100F, (double)size, 1.0D, (anim * 0.1667F) + 0.1667F);
            tessellator.addVertexWithUV((double)(-size), 100F, (double)size, 0.0D, (anim * 0.1667F) + 0.1667F);
            tessellator.draw();
			GL11.glPopMatrix();
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void onClientTick(Minecraft mc)
	{
		if(mc.theWorld.getWorldTime() % 3 == 0)
		{
			for(int i = 0; i < starAnimationStates.length; i++)
			{
				if(starAnimationStates[i] >= 5)
				{
					starAnimationStates[i] = 0;
				} else
				{
					starAnimationStates[i]++;
				}
			}
		}
	}
	
	public void addStar(double x, double z)
	{
		addStar(x, 0F, z, random.nextFloat() + 0.5F, (byte)random.nextInt(5));
	}
	
	public void addStar(double x, double y, double z, float size, byte color)
	{
		if(index >= starLocations.length)
		{
			return;
		}
		Vec3 vec3 = Vec3.createVectorHelper(x, y, z);
		starLocations[index] = vec3;
		starSizes[index] = size;
		starColors[index] = color;
		index++;
	}
	
	public void onPlanetCrossed(Planet planet)
	{
	}

	public byte id;
	public Vec3[] starLocations;
	public float[] starSizes;
	public byte[] starColors;
	public byte[] starAnimationStates;
	public byte index = 0;
	public float minX;
	public float minZ;
	public float maxX;
	public float maxZ;
	public String name = "";
	public static Constellation[] allConstellations = new Constellation[16];
	public static final byte COLOR_RED = 1;
	public static final byte COLOR_ORANGE = 2;
	public static final byte COLOR_YELLOW = 3;
	public static final byte COLOR_WHITE = 0;
	public static final byte COLOR_BLUE = 4;
	public static final Random random = new Random();
	static
	{
		random.setSeed(7462097L);
	}
	public static final Constellation kite = new ConstellationKite((byte)1);
	public static final Constellation pickaxe = new ConstellationPickaxe((byte)2);
	public static final Constellation flower = new ConstellationFlower((byte)3);
	public static final Constellation alchemyTable = new ConstellationAlchemyTable((byte)4);
	public static final Constellation enderwraith = new ConstellationEnderwraith((byte)5);
	public static final Constellation knight = new ConstellationKnight((byte)6);
	//public static final Constellation bee = new ConstellationBee(7);
}
