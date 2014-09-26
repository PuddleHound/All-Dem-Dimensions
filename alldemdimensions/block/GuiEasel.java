package alldemdimensions.block;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Arrays;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.client.C17PacketCustomPayload;
import net.minecraft.util.ResourceLocation;

import org.apache.logging.log4j.LogManager;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import alldemdimensions.AllDemDimensions;

public class GuiEasel extends GuiContainer
{
    public GuiEasel(InventoryPlayer inventoryplayer, TileEntityEasel tileentityeasel)
    {
        super(new ContainerEasel(inventoryplayer, tileentityeasel));
		xSize = 400;
		ySize = 243;
		player = inventoryplayer.player;
		ItemStack itemstack = tileentityeasel.getStackInSlot(TileEntityEasel.PAINTING_SLOT);
		if(itemstack != null)
		{
			if(itemstack.hasTagCompound())
			{
				NBTTagCompound data = itemstack.getTagCompound();
				pixelData = data.getIntArray("pixels");
				if(pixelData == null || pixelData.length == 0)
				{
					pixelData = new int[16 * 16];
					Arrays.fill(pixelData, (255 << 24) | (255 << 16) | (255 << 8) | 255);
					data.setIntArray("pixels", pixelData);
				}
			} else
			{
				itemstack.setTagCompound(new NBTTagCompound());
				pixelData = new int[16 * 16];
			}
		} else
		{
			pixelData = new int[16 * 16];
		}
                
        bufferedImage = new DynamicTexture(64, 64);
        texture = Minecraft.getMinecraft().renderEngine.getDynamicTextureLocation("painting", bufferedImage);
        pixelData = bufferedImage.getTextureData();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
		fontRendererObj.drawString("Color Palette", 8, 8, 4210752);
		fontRendererObj.drawString("Mix Colors", 8, 175, 4210752);
        fontRendererObj.drawString("Painting", 167, 10, 4210752);
		fontRendererObj.drawString("Inventory", 330, 8, 4210752);
		//this.fontRenderer.drawString("Easel", 60, 6, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
		TileEntityEasel tileentity = ((ContainerEasel)inventorySlots).tileEntityEasel;
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(paletteTexture);
		int k = (width / 2) - 200;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, 90, 243);
		drawTexturedModalRect(k + 155, l, 90, 0, 180, 28);
		drawTexturedModalRect(k, l, 0, 0, 90, 243);
        this.mc.renderEngine.bindTexture(inventoryTexture);
		k = (width / 2) + 110;
		drawTexturedModalRect(k, l, 0, 0, 90, 243);
		if(tileentity.getStackInSlot(TileEntityEasel.PAINTING_SLOT) != null)
		{
			k = (this.width - 64) / 2;
			l = (this.height - 64) / 2;
			bufferedImage.updateDynamicTexture();
			mc.renderEngine.bindTexture(texture);
			drawTexturedModalRect(k, l, 0, 0, 64, 64);
		}
		
		//color beside bottles
		ItemStack itemstack;
		int offsetX = 118;
		int offsetY = -39;
		NBTTagCompound data;
		int color;
		for(byte b = 0; b < 2; b++)
		{
			for(byte b1 = 0; b1 < 8; b1++)
			{
				itemstack = ((ContainerEasel)inventorySlots).tileEntityEasel.getStackInSlot(15 - (b1 + b * 8));
				if(itemstack != null && itemstack.getItem() == AllDemDimensions.paintBottle)
				{
					data = itemstack.getTagCompound();
					color = data.getInteger("paintColor");
					if(data != null && color != 0)
					{
						if(b > 0)
						{
							offsetX = 140;
						} else
						{
							offsetX = 118;
						}
						drawRect((width / 2) - ((b * 18) + offsetX), (height / 2) - ((b1 * 18) + offsetY), (width / 2) - ((b * 18) + offsetX + 16), (height / 2) - ((b1 * 18) + offsetY + 16), color);
					}
				}
			}
		}
    }
	
    @Override
	public void updateScreen()
	{
		super.updateScreen();
		int i = Mouse.getX() * this.width / this.mc.displayWidth;
		int j = this.height - Mouse.getY() * this.height / this.mc.displayHeight - 1;
		if(!Mouse.isButtonDown(0))
		{
			return;
		}
		TileEntityEasel tileentity = ((ContainerEasel)inventorySlots).tileEntityEasel;
		if(tileentity.getStackInSlot(TileEntityEasel.PAINTING_SLOT) == null)
		{
			return;
		}
		
		int offsetX = 118;
		int offsetY = -39;
		int x = (width - (paintingSizeX * 4)) / 2;
		int y = (height - (paintingSizeY * 4)) / 2;
		if(selectedColorIndex != -1 && i > x && j > y && i < x + (paintingSizeX * 4) && j < y + (paintingSizeY * 4))
		{
			x = (i - x) / 4;
			y = (j - y) / 4;
			ItemStack itemstack = ((ContainerEasel)inventorySlots).tileEntityEasel.getStackInSlot(selectedColorIndex);
			if(itemstack != null && itemstack.getItem() == AllDemDimensions.paintBottle)
			{
				NBTTagCompound data = itemstack.getTagCompound();
				if(data != null && data.getInteger("paintColor") != 0)
				{
					pixelData[x + (y * 16)] = data.getInteger("paintColor");
				}
			}
		}
		//DEBUG
		if(i > x && j > y && i < x + (paintingSizeX * 4) && j < y + (paintingSizeY * 4))
		{
			pixelData[x + (y * 16)] = Integer.MAX_VALUE;
		}
		//END DEBUG

		ItemStack itemstack = tileentity.getStackInSlot(TileEntityEasel.PAINTING_SLOT);
		if(itemstack != null)
		{
			System.out.println("Item is not null");
			if(itemstack.hasTagCompound())
			{
				NBTTagCompound data = itemstack.getTagCompound();
				data.setIntArray("pixels", pixelData);
				System.out.println("Setting item pixel data");
			} else
			{
				itemstack.setTagCompound(new NBTTagCompound());
			}
		}
		sendPixelDataToServer(itemstack);
	}
	
    @Override
	protected void mouseClicked(int i, int j, int k)
	{
		super.mouseClicked(i, j, k);
		TileEntityEasel tileentity = ((ContainerEasel)inventorySlots).tileEntityEasel;
		
		int offsetX = 118;
		int offsetY = -39;
		System.out.println("Mouse coords " + i + "," + j + "," + k);
		for(byte b = 0; b < 2; b++)
		{
			for(byte b1 = 0; b1 < 8; b1++)
			{
				if(b > 0)
				{
					offsetX = 140;
				} else
				{
					offsetX = 118;
				}
				System.out.println("Bounds : " + ((width / 2) - ((b * 18) + offsetX))
				+ "," + ((height / 2) - ((b1 * 18) + offsetY))
				+ "," + ((width / 2) - ((b * 18) + offsetX + 16))
				+ "," + ((height / 2) - ((b1 * 18) + offsetY + 16)));
				if(i < (width / 2) - ((b * 18) + offsetX) && 
					j < (height / 2) - ((b1 * 18) + offsetY) &&
					i > (width / 2) - ((b * 18) + offsetX + 16) &&
					j > (height / 2) - ((b1 * 18) + offsetY + 16))
				{
					selectedColorIndex = (15 - (b1 + b * 8));
					System.out.println("Selected color = " + selectedColorIndex);
				}
			}
		}
	}
	
	private void sendPixelDataToServer_old(ItemStack itemstack)
	{
		ByteArrayOutputStream stream = new ByteArrayOutputStream(4);
		DataOutputStream stream1 = new DataOutputStream(stream);
		try
		{
			//Packet.writeItemStack(itemstack, stream1);
		} 
		catch(Exception e)
		{
			e.printStackTrace();
		}
		//Packet250CustomPayload packet = new Packet250CustomPayload("ADD|Painting", stream.toByteArray());
		/*packet.channel = "ADD|Painting";
		packet.data = stream.toByteArray();*/
		//packet.length = stream.size();
		//((EntityClientPlayerMP)player).sendQueue.addToSendQueue(packet);
	}
	
	private void sendPixelDataToServer(ItemStack itemstack)
	{
		ByteBuf bytebuf = Unpooled.buffer();
		try
        {
            PacketBuffer packetbuffer = new PacketBuffer(bytebuf);
            /*packetbuffer.writeInt(((ContainerEasel)this.inventorySlots).tileEntityEasel.xCoord);
            packetbuffer.writeInt(((ContainerEasel)this.inventorySlots).tileEntityEasel.yCoord);
            packetbuffer.writeInt(((ContainerEasel)this.inventorySlots).tileEntityEasel.zCoord);*/
            packetbuffer.writeItemStackToBuffer(itemstack);
            this.mc.getNetHandler().addToSendQueue(new C17PacketCustomPayload("ADD|Painting", bytebuf));
        }
        catch (Exception exception)
        {
            LogManager.getLogger().error("Couldn\'t send painting info", exception);
        }
        finally
        {
            bytebuf.release();
        }
	}
	
	private void sendPaintUsageDataToServer(ItemStack itemstack)
	{
	
	}
	
	public int[] pixelData;
	public int selectedColorIndex = -1;//index, not actual color
	public EntityPlayer player;
	public int paintingSizeX = 16;
	public int paintingSizeY = 16;
    public DynamicTexture bufferedImage;
    public ResourceLocation texture;
    private static final ResourceLocation paletteTexture = new ResourceLocation("alldemdimensions:gui/easel_palette.png");
    private static final ResourceLocation inventoryTexture = new ResourceLocation("alldemdimensions:gui/easel_inventory.png");
}
