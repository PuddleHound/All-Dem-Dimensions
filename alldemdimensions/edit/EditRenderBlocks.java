package alldemdimensions.edit;

import java.lang.reflect.Method;

import alldemdimensions.AllDemDimensions;
import alldemdimensions.edit.EditBase.Insert;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.init.Blocks;
import net.minecraft.util.Vec3;
import net.minecraft.world.IBlockAccess;
import static org.objectweb.asm.Opcodes.*;

public class EditRenderBlocks extends EditBase
{
	
	public EditRenderBlocks()
	{
		super("net.minecraft.client.renderer.RenderBlocks", "");
	}
	
	@Override
	public void init()
	{
		method_BlockLiquid_getFlowVector = ReflectionManager.accessMethod(BlockLiquid.class, "getFlowVector", "", IBlockAccess.class, int.class, int.class, int.class);
	}
	
    @Insert(memberName = "renderBlockLiquid", memberNameObf = "", memberDesc = "(Lnet/minecraft/block/Block;III)Z", instrOpcodes = {ALOAD, GETFIELD, ILOAD, ILOAD, ILOAD, ALOAD, INVOKESTATIC}, replaceInstr = {true, true, true, true, true, true, true}, paramIndices = {0, 1, 2, 3, 4})
	public static double getFlowDirection(Object object, Block block, int x, int y, int z)
	{
		System.out.println("getting flow direction");
		Vec3 vec3 = null;
        if(block.getMaterial() == Material.lava)
        {
        	vec3 = (Vec3)ReflectionManager.invokeMethod(Blocks.flowing_lava, method_BlockLiquid_getFlowVector, ((RenderBlocks)object).blockAccess, x, y, z);
        } else
        {
        	vec3 = (Vec3)ReflectionManager.invokeMethod(Blocks.flowing_water, method_BlockLiquid_getFlowVector, ((RenderBlocks)object).blockAccess, x, y, z);
        }
        return vec3.xCoord == 0.0D && vec3.zCoord == 0.0D ? -1000.0D : Math.atan2(vec3.zCoord, vec3.xCoord) - (Math.PI / 2D);
	}
    
    private static Method method_BlockLiquid_getFlowVector;
	
}
