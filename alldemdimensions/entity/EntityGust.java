package alldemdimensions.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.block.Plant;

public class EntityGust extends Entity
{

    public EntityGust(World world)
    {
        super(world);
        setSize(0.9F, 0.9F);
		//noClip = true;
    }
	
    @Override
	protected void entityInit()
	{
	}
	
    @Override
    public void onUpdate()
    {
		super.onUpdate();
		setDead();//temporary
		//tick causes extreme lag
        worldObj.theProfiler.startSection("wind tick");
		/*if(worldObj.isRemote)
		{
			return;
		}*/
		motionX = 0.4D;
		motionY = 0.0D;
		motionZ = 0.4D;
		moveEntity(motionX, motionY, motionZ);

		/*if(rand.nextInt(1000) == 0)
		{
			worldObj.playSoundEffect((int)posX, (int)posY, (int)posZ, "alldemdimensions.sound.zenith.wind", 1.0F, 1.0F);
		}*/
		
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(4D, 4D, 4D));
		Entity entity;
		for(int counter = 0; counter < list.size(); counter++)
		{
			entity = (Entity)list.get(counter);
			if(entity != null)
			{
				entity.addVelocity(motionX * 0.1D, 0D, motionZ * 0.1D);
			}
		}
		
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		for(int i = -4; i < 4; i++)
		{
			for(int j = -4; j < 4; j++)
			{
				for(int k = -4; k < 4; k++)
				{
					if(worldObj.getBlock(x + i, y + j, z + k) == Plant.zenith_flowerCover.plantBlock && worldObj.getBlockMetadata(x + i, y + j, z + k) == Plant.zenith_flowerCover.plantMeta)
					{
						worldObj.setBlockToAir(x + i, y + j, z + k);
					}
				}
			}
		}
		
		if(!worldObj.isRemote && (isCollidedHorizontally || rand.nextInt(3000) == 0))
		{
			setDead();
		}
                worldObj.theProfiler.endSection();
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
