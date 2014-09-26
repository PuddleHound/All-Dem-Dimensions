package alldemdimensions.block;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import alldemdimensions.world.environment.Planet;

public class GuiStellarAltar extends GuiScreen
{
    public GuiStellarAltar(EntityPlayer entityplayer, World world, int i, int j, int k)
    {
		player = entityplayer;
		xSize = 256;
		ySize = 238;
    }

    @Override
    public void drawScreen(int i, int j, float f)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(guiTexture);
        int k = (width - xSize) / 2;
        int l = (height - ySize) / 2;
        drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		fontRendererObj.drawString("Stellar Altar", k + 6, l + 6, 4210752);
		
		byte[] ab;
		int offsetX;//14
		int offsetY;//38
		String s;
		for(Planet planet : Planet.allPlanets)
		{
			offsetX = planet.guiOffsetX;
			offsetY = planet.guiOffsetY;
			fontRendererObj.drawString(planet.name, k + offsetX + 5, l + offsetY + 22, 6316128);
			fontRendererObj.drawString(planet.theme, k + offsetX + 5, l + offsetY + 34, 8421504);
			if(planet.currentConstellation != null)
			{
				fontRendererObj.drawString(planet.currentConstellation.name, k + offsetX + 5, l + offsetY + 46, 8421504);
			}
			if(planet.tempFreezeTime != -1)
			{
				ab = Planet.getTimeInMinutes(planet.tempFreezeTime);
			} else
			{
				ab = Planet.getTimeInMinutes(planet.freezeTime);
			}
			if(ab[1] < 10)
			{
				s = "0";
			} else
			{
				s = "";
			}
			fontRendererObj.drawString(ab[0] + ":" + s + ab[1], k + offsetX + 5, l + offsetY + 58, 8421504);
		}
		fontRendererObj.drawString("Cost: " + expCost, k + 112, l + 96, 6316128);
		
        mc.renderEngine.bindTexture(iconTexture);
		if(selectedPlanet != null)
		{
			drawTexturedModalRect(k + selectedPlanet.guiOffsetX, l + selectedPlanet.guiOffsetY, 0, 0, 82, 72);
			drawTexturedModalRect(k + selectedPlanet.guiOffsetX + 3, l + selectedPlanet.guiOffsetY + 56, 82, 0, 118, 12);
			if(selectedPlanet.tempFreezeTime != -1)
			{
				ab = Planet.getTimeInMinutes(selectedPlanet.tempFreezeTime);
			} else
			{
				ab = Planet.getTimeInMinutes(selectedPlanet.freezeTime);
			}
			if(ab[1] < 10)
			{
				s = "0";
			} else
			{
				s = "";
			}
			fontRendererObj.drawString(ab[0] + ":" + s + ab[1], k + selectedPlanet.guiOffsetX + 5, l + selectedPlanet.guiOffsetY + 58, 16777215);
		}
		super.drawScreen(i, j, f);
    }
	
    @Override
	protected void mouseClicked(int i, int j, int k)
    {
		super.mouseClicked(i, j, k);
		int offsetX = (width - xSize) / 2;
        int offsetY = (height - ySize) / 2;
		int x;
		int y;
		boolean withinBounds = false;
		expCost = 0;
		for(Planet planet : Planet.allPlanets)
		{
			x = offsetX + planet.guiOffsetX;
			y = offsetY + planet.guiOffsetY;
			if(i >= x && i <= x + 82 && j >= y && j <= y + 72)
			{
				selectedPlanet = planet;
				withinBounds = true;
				if(i >= x + 27 && i <= x + 38 && j >= y + 57 && j <= y + 61)
				{
					if(planet.tempFreezeTime == -1)
					{
						planet.tempFreezeTime = planet.freezeTime;
					}
					if(planet.tempFreezeTime < 153300)
					{
						planet.tempFreezeTime += 300;
					}
				} else
				if(i >= x + 29 && i <= x + 40 && j >= y + 64 && j <= y + 68)
				{
					if(planet.tempFreezeTime == -1)
					{
						planet.tempFreezeTime = planet.freezeTime;
					}
					if(planet.tempFreezeTime >= 300)
					{
						planet.tempFreezeTime -= 300;
					}
				}
			}
			expCost += Planet.calculateFreezeCost(planet.freezeTime, planet.tempFreezeTime);
		}
		int l = -1;
		byte counter = 0;
		Planet planet;
		boolean flag = true;
		while(counter < Planet.allPlanets.length)
		{
			planet = Planet.allPlanets[counter];
			if((l != -1 && l != planet.tempFreezeTime) || planet.tempFreezeTime == -1)
			{
				flag = false;
				break;
			}
			l = planet.tempFreezeTime;
			counter++;
		}
		if(flag)
		{
			expCost /= 8;
		}
		if(expCost > player.experienceLevel)
		{
			buttonFreezePlanets.enabled = false;
		} else
		{
			buttonFreezePlanets.enabled = true;
		}
		if(!withinBounds)
		{
			selectedPlanet = null;
		}
	}
	
    @Override
	public void updateScreen()
	{
		super.updateScreen();
	}
	
    @Override
	public void initGui()
    {
        buttonList.clear();
		int x = (width - xSize) / 2;
        int y = (height - ySize) / 2;
        buttonList.add(buttonFreezePlanets = new GuiButton(BUTTON_FREEZE_PLANETS, x + 88, y + 112, 80, 20, "Freeze planets"));
		buttonList.add(buttonDone = new GuiButton(BUTTON_DONE, x + 88, y + 136, 80, 20, "Done"));
	}
	
    @Override
	protected void actionPerformed(GuiButton button)
    {
        if(!button.enabled)
        {
			return;
		}
		if(button.id == BUTTON_DONE)
		{
			mc.displayGuiScreen(null);
		}
		if(button.id == BUTTON_FREEZE_PLANETS)
		{
			sendInfoToServer();
			for(Planet planet : Planet.allPlanets)
			{
				planet.tempFreezeTime = -1;
			}
		}
	}
	
	private void sendInfoToServer()
	{
		try
		{
			System.out.println("Sending planet freeze info to server");
			ByteArrayOutputStream stream = new ByteArrayOutputStream((Planet.allPlanets.length * 4) + 4);
			DataOutputStream stream1 = new DataOutputStream(stream);
			for(Planet planet : Planet.allPlanets)
			{
				stream1.writeInt(planet.tempFreezeTime);
				System.out.println(planet.name + ": " + planet.tempFreezeTime);
			}
			stream1.writeInt(expCost);
			/*Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.channel = "ADD|PlanetFreeze";
			packet.data = stream.toByteArray();
			packet.length = stream.size();
			PacketDispatcher.sendPacketToServer(packet);*///1.7.2
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
    @Override
	public void onGuiClosed()
	{
		for(Planet planet : Planet.allPlanets)
		{
			planet.tempFreezeTime = -1;
		}
		expCost = 0;
		super.onGuiClosed();
	}
	
    @Override
	public boolean doesGuiPauseGame()
    {
        return false;
    }
	
	private EntityPlayer player;
	private int xSize;
	private int ySize;
	private Planet selectedPlanet;
	private int expCost;
	private GuiButton buttonFreezePlanets;
	private GuiButton buttonDone;
	private static final byte BUTTON_FREEZE_PLANETS = 0;
	private static final byte BUTTON_DONE = 1;
    private static final ResourceLocation guiTexture = new ResourceLocation("alldemdimensions:gui/stellarAltar.png");
    private static final ResourceLocation iconTexture = new ResourceLocation("alldemdimensions:gui/stellarAltarIcon.png");

}
