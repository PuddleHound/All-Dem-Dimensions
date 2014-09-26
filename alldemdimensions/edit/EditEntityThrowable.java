package alldemdimensions.edit;

import static org.objectweb.asm.Opcodes.INVOKEVIRTUAL;

import java.util.ArrayList;

import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.util.ChunkCoordinates;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.edit.EditBase.Insert;

public class EditEntityThrowable extends EditBase
{

	public EditEntityThrowable()
	{
		super("net.minecraft.entity.projectile.EntityThrowable", "");
	}
	
    @Insert(memberName = "onUpdate", memberNameObf = "", memberDesc = "()V", instrOpcodes = {-1, -1}, paramIndices = {0})
    public static void onUpdate(Object entity)
    {
    	if(entity instanceof EntitySnowball)
    	{
    		EntitySnowball snowball = (EntitySnowball)entity;
    		int x = (int)Math.floor(snowball.posX);
    		int y = (int)Math.floor(snowball.posY);
    		int z = (int)Math.floor(snowball.posZ);
    		while(y >= 0)
    		{
    			if(AllDemDimensions.skyPortal.checkForValidPortal(snowball.worldObj, x, y, z, 0, 0, 0, x, y, z, true, true, new ArrayList<ChunkCoordinates>()))
    			{
    				break;
    			}
    			y--;
    		}
    	}
    }

}
