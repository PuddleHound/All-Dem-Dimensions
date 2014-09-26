package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.*;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.TeleporterMainDimensions;

public class EditTeleporter extends EditBase
{

	public EditTeleporter()
	{
		super("net.minecraft.world.Teleporter", "");
	}
		
    public static Block getPortalBlock(Object teleporter)
    {		
		if(teleporter instanceof TeleporterMainDimensions)
		{
			TeleporterMainDimensions mainTeleporter = (TeleporterMainDimensions)teleporter;
			Dimension dimension = Dimension.getDimensionForId_MC(mainTeleporter.oldDimension);
			return dimension.portalBlockId;
		}
		return Blocks.portal;
    }
    
    public static Block getFrameBlock(Object teleporter)
    {		
		if(teleporter instanceof TeleporterMainDimensions)
		{
			TeleporterMainDimensions mainTeleporter = (TeleporterMainDimensions)teleporter;
			Dimension dimension = Dimension.getDimensionForId_MC(mainTeleporter.oldDimension);
			return dimension.portalFrameId;
		}
		return Blocks.obsidian;
    }
    
    //if (this.worldServerInstance.getBlock(l3, i2, l1) == Blocks.portal)
	@Insert(memberName = "placeInExistingPortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;DDDF)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ILOAD, ILOAD, INVOKEVIRTUAL, GETSTATIC, IF_ACMPNE}, replaceInstr = {false, false, false, false, false, false, true, false}, paramIndices = 0)
	public static Block getPortalBlock_1(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
	//while (this.worldServerInstance.getBlock(l3, i2 - 1, l1) == Blocks.portal)
	@Insert(memberName = "placeInExistingPortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;DDDF)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ILOAD, ICONST_1, ISUB, ILOAD, INVOKEVIRTUAL, GETSTATIC, IF_ACMPNE}, replaceInstr = {false, false, false, false, false, false, false, false, true, false}, paramIndices = 0)
    public static Block getPortalBlock_2(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
	//if (this.worldServerInstance.getBlock(i - 1, j, k) == Blocks.portal)
	@Insert(memberName = "placeInExistingPortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;DDDF)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ICONST_1, ISUB, ILOAD, ILOAD, INVOKEVIRTUAL, GETSTATIC, IF_ACMPNE}, replaceInstr = {false, false, false, false, false, false, false, false, true, false}, paramIndices = 0)
    public static Block getPortalBlock_3(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
    //if (this.worldServerInstance.getBlock(i + 1, j, k) == Blocks.portal)
	@Insert(memberName = "placeInExistingPortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;DDDF)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ICONST_1, IADD, ILOAD, ILOAD, INVOKEVIRTUAL, GETSTATIC, IF_ACMPNE}, replaceInstr = {false, false, false, false, false, false, false, false, true, false}, paramIndices = 0)
    public static Block getPortalBlock_4(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
	//if (this.worldServerInstance.getBlock(i, j, k - 1) == Blocks.portal)
	@Insert(memberName = "placeInExistingPortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;DDDF)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ILOAD, ILOAD, ICONST_1, ISUB, INVOKEVIRTUAL, GETSTATIC, IF_ACMPNE}, replaceInstr = {false, false, false, false, false, false, false, false, true, false}, paramIndices = 0)
    public static Block getPortalBlock_5(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
	//if (this.worldServerInstance.getBlock(i, j, k + 1) == Blocks.portal)
	@Insert(memberName = "placeInExistingPortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;DDDF)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ILOAD, ILOAD, ICONST_1, IADD, INVOKEVIRTUAL, GETSTATIC, IF_ACMPNE}, replaceInstr = {false, false, false, false, false, false, false, false, true, false}, paramIndices = 0)
    public static Block getPortalBlock_6(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
	//this.worldServerInstance.setBlock(l3, i4, j4, flag ? Blocks.obsidian : Blocks.air);
	@Insert(memberName = "makePortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;)Z", instrOpcodes = {GETSTATIC}, instrNames = {"obsidian"}, replaceInstr = {true}, paramIndices = 0)
	public static Block getFrameBlock_1(Object teleporter)
	{
		return getFrameBlock(teleporter);
	}
	
	//this.worldServerInstance.setBlock(l3, i4, j4, (Block)(flag ? Blocks.obsidian : Blocks.portal), 0, 2);
	@Insert(memberName = "makePortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;)Z", instrOpcodes = {GETSTATIC}, instrNames = {"obsidian"}, replaceInstr = {true}, paramIndices = 0, skipMatches = 1)
	public static Block getFrameBlock_2(Object teleporter)
	{
		return getFrameBlock(teleporter);
	}
	
	//this.worldServerInstance.setBlock(l3, i4, j4, (Block)(flag ? EditedTeleporter.getFrameBlock_2(this) : Blocks.portal), 0, 2);
	@Insert(memberName = "makePortal", memberNameObf = "", memberDesc = "(Lnet/minecraft/entity/Entity;)Z", instrOpcodes = {GETSTATIC}, instrNames = {"portal"}, replaceInstr = {true}, paramIndices = 0)
	public static Block getPortalBlock_7(Object teleporter)
	{
		return getPortalBlock(teleporter);
	}
	
}