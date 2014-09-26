package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.*;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntityChest;
import alldemdimensions.block.TileEntityAmberChestRenderer;

public class EditTileEntityChestRenderer extends EditBase
{
	
	public EditTileEntityChestRenderer()
	{
		super("net.minecraft.client.renderer.tileentity.TileEntityChestRenderer", "");
	}
	
	@Insert(memberName = "renderTileEntityAt", memberNameObf = "", memberDesc = "(Lnet/minecraft/tileentity/TileEntityChest;DDDF)V", instrNames = {"glPushMatrix"}, instrOpcodes = {INVOKESTATIC}, insertOffset = -1, paramIndices = {0, 1})
	public static void bindTexture(Object object, TileEntityChest tileentity)
	{
		if(object instanceof TileEntityAmberChestRenderer)
		{
			TileEntityAmberChestRenderer renderer = (TileEntityAmberChestRenderer)object;
			if(tileentity.adjacentChestXPos == null && tileentity.adjacentChestZPos == null)
			{
				renderer.bindTexture(renderer.texture);
			} else
			{
				renderer.bindTexture(renderer.largeTexture);
			}
		}
	}

}
