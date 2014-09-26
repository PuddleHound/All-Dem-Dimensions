package alldemdimensions.block;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class GuiAlchemy extends GuiContainer
{
    public GuiAlchemy(InventoryPlayer inventoryplayer, World world, int i, int j, int k)
    {
        super(new ContainerAlchemy(inventoryplayer, world, i, j, k));
		ySize = 182;
    }

    @Override
    public void onGuiClosed()
    {
        super.onGuiClosed();
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int i, int j)
    {
		fontRendererObj.drawString("Alchemy", 8, 6, 0x404040);
        fontRendererObj.drawString("Inventory", 8, 89, 0x404040);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f, int i, int j)
    {
		//int k = mc.renderEngine.getTexture("/alldemdimensions/gui/alchemy.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        //mc.renderEngine.bindTexture(k);
        mc.renderEngine.bindTexture(guiTexture);
        int l = (width - xSize) / 2;
        int i1 = (height - ySize) / 2;
        drawTexturedModalRect(l, i1, 0, 0, xSize, ySize);
    }
    
    private static final ResourceLocation guiTexture = new ResourceLocation("alldemdimensions:gui/alchemy.png");
}
