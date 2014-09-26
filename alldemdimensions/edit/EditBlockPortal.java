package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.ALOAD;
import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EditBlockPortal extends EditBase
{
	
	public EditBlockPortal()
    {
		super("net.minecraft.block.BlockPortal", "");
	}

    @Insert(memberName = "onEntityCollidedWithBlock", memberNameObf = "", memberDesc = "(Lnet/minecraft/world/World;IIILnet/minecraft/entity/Entity;)V", instrOpcodes = {ALOAD, INVOKEVIRTUAL}, replaceInstr = {true, true}, paramIndices = {1, 2, 3, 4, 5})
    public static void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        //do nothing
    }
    
}
