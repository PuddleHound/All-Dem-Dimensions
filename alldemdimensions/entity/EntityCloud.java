package alldemdimensions.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityCloud extends EntityThrowable
{
    public EntityCloud (World world)
    {
        super(world);
    }

    public EntityCloud(World world, EntityLiving entityliving)
    {
        super(world, entityliving);
	}

    public EntityCloud(World world, double d, double d1, double d2)
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
            byte damage = 5;
			movingobjectposition.entityHit.addVelocity(motionX * 0.35D, motionY * 0.35D, motionZ * 0.35D);
            movingobjectposition.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), damage);
        }

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }
    }
}
