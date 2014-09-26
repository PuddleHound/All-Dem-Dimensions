package alldemdimensions.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import alldemdimensions.block.Plant;

public class EntityWind extends EntityThrowable
{
    public EntityWind(World world)
    {
        super(world);
    }

    public EntityWind(World world, EntityLiving entityliving)
    {
        super(world, entityliving);
		posX += (world.rand.nextDouble() * 3.0D) - (world.rand.nextDouble() * 3.0D);
		posY += (world.rand.nextDouble() * 3.0D) - (world.rand.nextDouble() * 3.0D);
		posZ += (world.rand.nextDouble() * 3.0D) - (world.rand.nextDouble() * 3.0D);
	}

    public EntityWind(World world, double d, double d1, double d2)
    {
        super(world, d, d1, d2);
    }
	
    @Override
	public void onUpdate()
	{
		super.onUpdate();
		//rotationYaw += 16.0D;
	}

    @Override
    protected void onImpact(MovingObjectPosition movingobjectposition)
    {
        if (movingobjectposition.entityHit != null)
        {
			movingobjectposition.entityHit.addVelocity(motionX * 0.35D, motionY * 0.35D, motionZ * 0.35D);
        }
		
		if(movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
		{
			int x = movingobjectposition.blockX;
			int y = movingobjectposition.blockY;
			int z = movingobjectposition.blockZ;
			if(worldObj.getBlock(x, y, z) == Plant.zenith_flowerCover.plantBlock && worldObj.getBlockMetadata(x, y, z) == Plant.zenith_flowerCover.plantMeta)
			{
				worldObj.setBlockToAir(x, y, z);
			}
		}

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
