package alldemdimensions.entity;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityAurora extends Entity
{

    public EntityAurora(World world)
    {
        super(world);
        setSize(24.0F, 4.0F);
		noClip = true;
    }
	
    @Override
	protected void entityInit()
	{
	}
	
    @Override
    public void onUpdate()
    {
		super.onUpdate();
		/*if(worldObj.isRemote)
		{
			return;
		}*/
		motionX = 0.1D;
		motionY = 0.0D;
		motionZ = 0.0D;
		moveEntity(motionX, motionY, motionZ);
		
		if(!worldObj.isRemote && rand.nextInt(3000) == 0)
		{
			setDead();
		}
	}
	
    @Override
	public int getBrightnessForRender(float f)
    {
        return 15728880;
    }
	
    @Override
    public float getBrightness(float f)
    {
        return 1.0F;
    }
	
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }
	
	protected void collideWithEntity(Entity entity) {}
	
	protected void func_85033_bc() {}

	public boolean isOnLadder()
	{
		return false;
	}

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float f)
    {
		return false;
    }
	
    @Override
	protected void updateFallState(double d, boolean flag)
    {
	}
	
    @Override
	protected void fall(float f)
    {
	}

}
