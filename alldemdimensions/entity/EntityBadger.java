package alldemdimensions.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsTarget;
import net.minecraft.entity.ai.EntityAIOcelotAttack;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class EntityBadger extends EntityTameable
{

	public EntityBadger(World world)
	{
		super(world);
        setSize(1.0F, 0.5F);
		randomHoverOffset = rand.nextInt(360);
		this.tasks.addTask(1, new EntityAISwimming(this));
        float moveSpeed = 0.25F;
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityBee.class, moveSpeed, true));
		this.tasks.addTask(3, new EntityAIOcelotAttack(this));
		this.tasks.addTask(4, new EntityAIFollowOwner(this, moveSpeed, 5.0F, 2.0F));
		this.tasks.addTask(5, new EntityAIMoveTowardsTarget(this, moveSpeed, 32.0F));
		this.tasks.addTask(6, new EntityAIWander(this, moveSpeed));
		this.tasks.addTask(7, new EntityAILookIdle(this));
		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true));
		this.targetTasks.addTask(2, new EntityAITargetNonTamed(this, EntityBee.class, 200, false));
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
    }
	
    @Override
	public EntityAgeable createChild(EntityAgeable entityageable)
    {
        return null;
    }
	
    @Override
	protected boolean canDespawn()
    {
        return false;
    }
	
    @Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	
        @Override
	public boolean isOnLadder()
    {
        if(!isHovering && getAttackTarget() != null && isCollidedHorizontally)
		{
			return true;
		}
		return super.isOnLadder();
    }

        @Override
	public void onUpdate()
	{
		super.onUpdate();
		if(onGround)
		{
			isHovering = false;
			currentFlightTarget = null;
			lastGroundedLocation = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		}
		if(isHovering)
		{
			byte b = 0;
			while(b < 4)
			{
				if(worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - b, MathHelper.floor_double(posZ)).getMaterial().isSolid())
				{
					isHovering = false;
					break;
				}
				b++;
			}
		}
		if(attackCooldown > 0)
		{
			attackCooldown--;
		}
		EntityLivingBase target = getAttackTarget();
		if(target != null && target.isDead)
		{
			setTarget(null);
		}
		if(isHovering)
		{
			worldObj.spawnParticle("smoke", posX, posY - 1D, posZ, 0.0D, 0.0D, 0.0D);
			motionY = (double)(Math.cos(((worldObj.getWorldTime() + randomHoverOffset) / 4) % 360)) * 0.1D;
			if(prevHadTarget)
			{
				prevHadTarget = false;
				currentFlightTarget = null;
			}
			boolean prevUpwardPathObstructed = upwardPathObstructed;
			upwardPathObstructed = false;
			byte b = 0;
			while(b < 8)
			{
				if(!worldObj.isAirBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + b, MathHelper.floor_double(posZ)))
				{
					upwardPathObstructed = true;
					break;
				}
				b++;
			}
			if(!upwardPathObstructed && currentFlightTarget == null && posY < 255)
			{
				motionY = 0.25D;
			}
			boolean reachedTarget = currentFlightTarget != null && currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F;
			boolean pathNewlyObstructed = upwardPathObstructed && (!prevUpwardPathObstructed || reachedTarget);
			byte counter = 0;
			int x;
			int y;
			int z;
			int maxDistance = 12;
			if(currentFlightTarget == null || pathNewlyObstructed || rand.nextInt(1000) == 0 || reachedTarget)
			{
				currentFlightTarget = null;
				int maxCount = 16;
				boolean useGroundedLocation = rand.nextInt(5) == 0 && lastGroundedLocation != null;
				if(useGroundedLocation)
				{
					maxDistance = 8;
				}
				while(counter < maxCount)
				{
					if(useGroundedLocation)
					{
						x = lastGroundedLocation.posX + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
						y = lastGroundedLocation.posY + rand.nextInt(maxDistance / 2) - (maxDistance / 2);
						z = lastGroundedLocation.posZ + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
					} else
					{
						x = (int)posX + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
						y = (int)posY + rand.nextInt(maxDistance) - (maxDistance);//* 2
						z = (int)posZ + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
					}
					if((y > 0 && y < 255 && (worldObj.getBlock(x, y - 1, z).getMaterial().isSolid() || upwardPathObstructed) && isCourseTraversable(x, y, z)))
					{
						currentFlightTarget = new ChunkCoordinates(x, y, z);
						upwardPathObstructed = false;
						break;
					}
					counter++;
				}
			}
			if(currentFlightTarget != null)
			{
				double distanceToTargetX = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
				double distanceToTargetY = (double)this.currentFlightTarget.posY + 0.5D - this.posY;
				double distanceToTargetZ = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
				this.motionX += (Math.signum(distanceToTargetX) * 0.25F - this.motionX) * 0.10000000149011612D;
				this.motionY += (Math.signum(distanceToTargetY) * /*getFlightSpeed()*/0.699999988079071D - this.motionY) * 0.10000000149011612D;
				this.motionZ += (Math.signum(distanceToTargetZ) * 0.25F - this.motionZ) * 0.10000000149011612D;
				float angleFacing = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
				float yawDiff = MathHelper.wrapAngleTo180_float(angleFacing - this.rotationYaw);
				this.moveForward = 0.25F;
				this.rotationYaw += yawDiff;
			}
		}
		if(target != null)
		{
			double distance = getDistanceSqToEntity(target);
			if(rand.nextInt(50) == 0 && jumpAttackHeight == -100 && distance >= 4.0D && distance <= 16.0D)
			{
				jumpAttackHeight = (int)target.posY;
				//isHovering = false;
			}
			if((int)posY <= jumpAttackHeight)
			{
				motionY = 0.5D;
				//isHovering = false;
			} else
			{
				jumpAttackHeight = -100;
			}
		}
	}
	
	public boolean isCourseTraversable(double x, double y, double z)
    {
		double distanceX = x - posX;
		double distanceY = y - posY;
		double distanceZ = z - posZ;
		double distanceTotal = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        distanceX /= distanceTotal;
        distanceY /= distanceTotal;
        distanceZ /= distanceTotal;
        AxisAlignedBB bounds = boundingBox.copy();
        for(int counter = 1; (double)counter < distanceTotal; counter++)
        {
            bounds.offset(distanceX, distanceY, distanceZ);
            if(!worldObj.getCollidingBoundingBoxes(this, bounds).isEmpty())
            {
                return false;
            }
        }
        return true;
    }
	
	@Override
	public boolean attackEntityFrom(DamageSource damagesource, float f)
    {
	    Entity entity = damagesource.getEntity();
	    if(entity instanceof EntityLiving)
	    {
			if(entity == getOwner())
			{
				setTamed(false);
				setOwner("");
			} else
			if(entity instanceof EntityBee)
			{
				f = 1;
			}
	    }
	    return super.attackEntityFrom(damagesource, f);
    }
	
    @Override
	public boolean attackEntityAsMob(Entity entity)
    {
        int damage = getAttackStrength(entity);
        return entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
    }

    @Override
    protected void attackEntity(Entity entity, float damage)
    {
        if (this.attackTime <= 0 && damage < 2.0F && entity.boundingBox.maxY > this.boundingBox.minY && entity.boundingBox.minY < this.boundingBox.maxY)
        {
            this.attackTime = 20;
            this.attackEntityAsMob(entity);
        }
    }
	
	public int getAttackStrength(Entity entity)
	{
		return isTamed() ? 3 : 5;
	}
		
	//@Override
	protected void fall()
	{
		
	}
	
    @Override
	protected void updateFallState(double d, boolean flag)
	{
		if((!flag && fallDistance > 4.0F) || isHovering)
		{
			isHovering = true;
			fallDistance = 0F;
		} else
		{
			super.updateFallState(d, flag);
		}
	}
	
    @Override
	public boolean interact(EntityPlayer entityplayer)
	{
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
		if(itemstack != null && itemstack.getItem() == AllDemDimensions.bucketHoney)
		{
			if(!isTamed())
			{
				setTamed(true);
				setOwner(entityplayer.getCommandSenderName());
				
			} else
			{
				heal(7);
			}
			entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(Items.bucket));
			return true;
		}
		return false;
	}
	
    @Override
	protected void updateAITasks()
    {
        if(!isHovering)
		{
			super.updateAITasks();
		}
    }
	
	private int jumpAttackHeight = -100;
	private short attackCooldown;
	public boolean isHovering = false;
	private int randomHoverOffset;
	private ChunkCoordinates currentFlightTarget;
	private boolean upwardPathObstructed;
	private ChunkCoordinates lastGroundedLocation;
	private boolean prevHadTarget;

}
