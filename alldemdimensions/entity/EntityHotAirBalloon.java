package alldemdimensions.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityHotAirBalloon extends Entity
{

    public EntityHotAirBalloon(World world)
    {
        super(world);
        preventEntitySpawning = true;
        setSize(0.1F, 0.5F);
    }
	
    @Override
	public boolean interactFirst(EntityPlayer entityplayer)
    {
        if(!super.interactFirst(entityplayer))
        {
            if(!worldObj.isRemote && (riddenByEntity == null || riddenByEntity == entityplayer))
            {
                entityplayer.mountEntity(this);
                return true;
            }
        }
		return true;
    }
	
    @Override
	public double getMountedYOffset()
    {
        return -1D;
    }
	
    @Override
	public boolean shouldRiderSit()
    {
        return false;
    }
	
    @Override
	protected void entityInit()
	{
		dataWatcher.addObject(16, new Integer(0));
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Integer(0));
		dataWatcher.addObject(19, new Integer(0));
		dataWatcher.addObject(20, new Integer(0));
	}
	
	public void setBalloonMotion(double d, double d1, double d2, double d3, double d4)
    {
		int i = (int)(d * 100000000D);
		int j = (int)(d1 * 100000000D);
		int k = (int)(d2 * 100000000D);
		int l = (int)(d3 * 100000000D);
		int i1 = (int)(d4 * 100000000D);
        dataWatcher.updateObject(16, Integer.valueOf(i));
		dataWatcher.updateObject(17, Integer.valueOf(j));
		dataWatcher.updateObject(18, Integer.valueOf(k));
		dataWatcher.updateObject(19, Integer.valueOf(l));
		dataWatcher.updateObject(20, Integer.valueOf(i1));
    }

    public double[] getBalloonMotion()
    {
		int i = dataWatcher.getWatchableObjectInt(16);
		int j = dataWatcher.getWatchableObjectInt(17);
		int k = dataWatcher.getWatchableObjectInt(18);
		int l = dataWatcher.getWatchableObjectInt(19);
		int i1 = dataWatcher.getWatchableObjectInt(20);
        return new double[]{(double)i / 100000000D, (double)j / 100000000D, (double)k / 100000000D,
			(double)l / 100000000D, (double)i1 / 100000000D};
    }
	
    @Override
    protected boolean canTriggerWalking()
    {
        return false;
    }
	
    @Override
    public void onUpdate()
    {
		super.onUpdate();
		if(!worldObj.isRemote)
		{
			if(riddenByEntity != null && riddenByEntity instanceof EntityPlayer)
			{
				EntityPlayer player = (EntityPlayer)riddenByEntity;
				player.capabilities.isFlying = true;
				setBalloonMotion(player.motionX, 0, player.motionZ, player.rotationYaw, player.rotationPitch);
				/*motionX = player.motionX;
				motionY = 0D;
				motionZ = player.motionZ;
				moveEntity(motionX, motionY, motionZ);*/
			} else
			{
				setBalloonMotion(0, 0, 0, rotationPitch, rotationYaw);
			}
		}
		double[] motion = getBalloonMotion();
		if(motion != null)
		{
			motionX = motion[0];
			motionY = motion[1];
			motionZ = motion[2];
			rotationYaw = (float)motion[3];
			rotationPitch = (float)motion[4];
			moveEntity(motionX, motionY, motionZ);
			/*if(!worldObj.isRemote)
			{
				moveEntity(motionX, motionY, motionZ);
				if(riddenByEntity != null)
				{
					riddenByEntity.setPosition(posX, posY, posZ);
				}
			} else
			{
				setPosition(posX + motionX, posY + motionY, posZ + motionZ);
				if(riddenByEntity != null)
				{
					riddenByEntity.setPosition(posX, posY, posZ);
				}
			}*/
		}
    }
	
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean canBeCollidedWith()
    {
        return true;
    }
	
	/*public AxisAlignedBB getCollisionBox(Entity entity)
    {
        return entity.boundingBox;
    }

    public AxisAlignedBB getBoundingBox()
    {
        return this.boundingBox;
    }

    public boolean canBePushed()
    {
        return true;
    }*/

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float damage)
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
