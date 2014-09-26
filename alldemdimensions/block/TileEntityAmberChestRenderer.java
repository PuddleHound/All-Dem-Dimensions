package alldemdimensions.block;

import net.minecraft.client.renderer.tileentity.TileEntityChestRenderer;
import net.minecraft.util.ResourceLocation;

public class TileEntityAmberChestRenderer extends TileEntityChestRenderer
{
   
    @Override
    public void bindTexture(ResourceLocation resourcelocation)
    {
    	super.bindTexture(resourcelocation);
    }
    
    public static final ResourceLocation texture = new ResourceLocation("alldemdimensions", "textures/tileentity/amberChest.png");
    public static final ResourceLocation largeTexture = new ResourceLocation("alldemdimensions", "textures/tileentity/amberChestLarge.png");

}
