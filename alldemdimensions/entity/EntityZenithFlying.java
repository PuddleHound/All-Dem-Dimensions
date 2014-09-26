package alldemdimensions.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import alldemdimensions.block.Plant;
import alldemdimensions.world.environment.EnumZenithDay;

public abstract class EntityZenithFlying extends EntityAmbientCreature//EntityLiving implements IAnimals
{

	public EntityZenithFlying(World world)
	{
		super(world);
	}
	
    @Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(16, new Byte((byte)0));
	}
	
    @Override
	protected void fall(float f) {}

    @Override
    protected void updateFallState(double d, boolean flag) {}
	
    @Override
	protected void collideWithEntity(Entity entity) {}
	
    @Override
	protected void collideWithNearbyEntities() {}

    @Override
	public boolean isOnLadder()
	{
		return false;
	}
	
    @Override
	protected boolean canTriggerWalking()
    {
        return false;
    }
	
    @Override
	public boolean canBePushed()
    {
        return false;
    }
	
    @Override
	public boolean doesEntityNotTriggerPressurePlate()
    {
        return true;
    }
	
    @Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	
    @Override
	public boolean getCanSpawnHere()
    {
		return super.getCanSpawnHere() && rand.nextInt(32) == 0 && canSpawnDuring(EnumZenithDay.getCurrentTime(worldObj));
	}
	
	public boolean canSpawnDuring(EnumZenithDay enumzenithday)
	{
		return true;
	}
	
	public boolean isHostile()
	{
		return isCorrupted();
	}
	
	public boolean isAttractedToFlowers()
	{
		return false;
	}
	
	public double getVisionDistance()
	{
		return 8.0D;
	}
	
	public int getCourseChangeFrequency()
	{
		return 300;
	}
	
	public int getAttackStrength()
	{
		return 1;
	}
	
	public boolean canBeCorrupted()
	{
		return true;
	}
	
	public int getMaxTravelDistance()
	{
		return 16;
	}

	public float getFlightSpeed()
	{
		return 0.35F;
	}
	
	public boolean isFlightSporadic()
	{
		return false;
	}
	
	public boolean hasRangedAttack()
	{
		return false;
	}
	
	public boolean isPitchAdjustable()
	{
		return false;
	}
	
	public boolean onReachFlightTarget()
	{
		return false;
	}
	
    @Override
	public void onUpdate()
	{
		super.onUpdate();
		/*if(worldObj.isRemote)
		{
			return;
		}*/
		if(getState() == FLYING || getState() == STING)
		{
			motionY *= 0.6D;
		}
		if(targetedEntity != null && targetedEntity.isDead)
		{
			targetedEntity = null;
		}
		if(isHostile() && targetedEntity == null)
		{
			EntityPlayer entityplayer = worldObj.getClosestPlayerToEntity(this, getVisionDistance());
			if(entityplayer != null && shouldTargetPlayer(entityplayer))
			{
				targetedEntity = entityplayer;
				stationaryTick = 0;
			}
		}
		if(corruptingEnderwraith != null && (corruptingEnderwraith.isDead || getDistanceToEntity(corruptingEnderwraith) > 16F))
		{
			corruptingEnderwraith = null;
		}
	}
	
    @Override
	protected void updateAITasks()
	{
		super.updateAITasks();
		if(getState() == CUSTOM_STATE_0 || getState() == CUSTOM_STATE_1)
		{
			return;
		}
		if(isAttractedToFlowers())
		{
			if(flowerCooldown == 0 && Plant.isFlower(worldObj, MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)))
			{
				setState(ON_FLOWER);
				currentFlightTarget = null;
			}
			if(getState() == ON_FLOWER && rand.nextInt(100) != 0)
			{
				motionX = motionY = motionZ = 0;
				flowerCooldown = 500;
				return;
			}
			if(flowerCooldown > 0)
			{
				flowerCooldown--;
			}
		}
		setState(FLYING);
		
		if(targetedEntity == null && isFlightSporadic() && rand.nextInt(500) == 0 && isBlockSolidBelow(3))
		{
			stationaryTick = rand.nextInt(200) + 100;
		}
		if(stationaryTick > 0)
		{
			setState(STATIONARY);
			stationaryTick--;
		}
		if(getState() == STATIONARY)
		{
			if(targetedEntity != null)
			{
				stationaryTick = 0;
			}
			return;
		}
		
		/*if (this.currentFlightTarget != null && !this.worldObj.isAirBlock(this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ) && !BlockSkyPlant.isFlower(worldObj, this.currentFlightTarget.posX, this.currentFlightTarget.posY, this.currentFlightTarget.posZ))
        {
            this.currentFlightTarget = null;
        }*/
		if(attackCooldown > 0)
		{
			attackCooldown--;
		}
		if(targetedEntity != null && attackCooldown == 0)
		{
			if(hasRangedAttack())
			{
				if(currentFlightTarget.getDistanceSquared((int)posX, (int)posY, (int)posZ) < 4.0F)
				{
					//targetedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength());
					byte counter = 0;
					while(counter < 16)
					{
						int x = (int)targetedEntity.posX + rand.nextInt(4) - rand.nextInt(4);
						int y = (int)(targetedEntity.posY + targetedEntity.yOffset) + rand.nextInt(4) - rand.nextInt(4);
						int z = (int)targetedEntity.posZ + rand.nextInt(4) - rand.nextInt(4);
						if(isCourseTraversable(x, y, z))
						{
							currentFlightTarget = new ChunkCoordinates(x, y, z);
							break;
						}
						if(counter > 12)
						{
							x = (int)posX + rand.nextInt(4) - rand.nextInt(4);
							y = (int)posY + rand.nextInt(4) - rand.nextInt(4);
							z = (int)posZ + rand.nextInt(4) - rand.nextInt(4);
							if(isCourseTraversable(x, y, z))
							{
								currentFlightTarget = new ChunkCoordinates(x, y, z);
								break;
							}
						}
						counter++;
					}
					attackCooldown = 30;
				}
			} else
			{
				currentFlightTarget = new ChunkCoordinates((int)targetedEntity.posX + rand.nextInt(2) - rand.nextInt(2), (int)(targetedEntity.posY + targetedEntity.yOffset) + rand.nextInt(2) - rand.nextInt(2), (int)targetedEntity.posZ + rand.nextInt(2) - rand.nextInt(2));
				if(currentFlightTarget.getDistanceSquared((int)posX, (int)posY, (int)posZ) < 4.0F)
				{
					targetedEntity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength());
					currentFlightTarget = new ChunkCoordinates((int)targetedEntity.posX + rand.nextInt(4) - rand.nextInt(4), (int)(targetedEntity.posY + targetedEntity.yOffset) + rand.nextInt(4) - rand.nextInt(4), (int)targetedEntity.posZ + rand.nextInt(4) - rand.nextInt(4));
					attackCooldown = 30;
				}
			}
		} else
		if (this.currentFlightTarget == null || this.rand.nextInt(getCourseChangeFrequency()) == 0 || this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
        {
			if(!isAttractedToFlowers() || !tryToFindFlower())
			{
				byte counter = 0;
				int x;
				int y;
				int z;
				int maxDistance = getMaxTravelDistance();
				while(counter < 16)
				{
					if(homeLocation != null && rand.nextInt(3) == 0)
					{
						x = homeLocation.posX + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
						y = homeLocation.posY + rand.nextInt(maxDistance / 2) - (maxDistance / 4);
						z = homeLocation.posZ + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
					} else
					{
						x = (int)posX + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
						y = (int)posY + rand.nextInt(maxDistance / 2) - (maxDistance / 4);
						z = (int)posZ + rand.nextInt(maxDistance) - rand.nextInt(maxDistance);
					}
					if((y > 0 && y < 255 && isCourseTraversable(x, y, z)) || counter == 15)
					{
						currentFlightTarget = new ChunkCoordinates(x, y, z);
						break;
					}
					counter++;
					//this.currentFlightTarget = new ChunkCoordinates((int)this.posX + this.rand.nextInt(16) - this.rand.nextInt(16), (int)this.posY + this.rand.nextInt(8) - 2, (int)this.posZ + this.rand.nextInt(16) - this.rand.nextInt(16));
				}
			}
			if(this.currentFlightTarget.getDistanceSquared((int)this.posX, (int)this.posY, (int)this.posZ) < 4.0F)
			{
				onReachFlightTarget();
			}
		}

        double x = (double)this.currentFlightTarget.posX + 0.5D - this.posX;
        double y = (double)this.currentFlightTarget.posY + 0.5D - this.posY;
        double z = (double)this.currentFlightTarget.posZ + 0.5D - this.posZ;
        this.motionX += (Math.signum(x) * getFlightSpeed() - this.motionX) * 0.10000000149011612D;//0.5D
        this.motionY += (Math.signum(y) * /*getFlightSpeed()*/0.699999988079071D - this.motionY) * 0.10000000149011612D;
        this.motionZ += (Math.signum(z) * getFlightSpeed() - this.motionZ) * 0.10000000149011612D;//0.5D
        float angleFacing = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
        float diffYaw = MathHelper.wrapAngleTo180_float(angleFacing - this.rotationYaw);
        this.moveForward = 0.35F;//0.5F
        this.rotationYaw += diffYaw;
		
		if(isPitchAdjustable())
		{
			float f = (float)(Math.atan2(this.moveForward, this.motionY) * 180.0D / Math.PI) - 90.0F;
			float pitch = MathHelper.wrapAngleTo180_float(f - this.rotationPitch);
			this.rotationPitch += pitch;
		}
	}
	
	private boolean tryToFindFlower()
	{
		for(int counter = 0; counter < 16; counter++)
        {
            int i = MathHelper.floor_double(posX + (double)worldObj.rand.nextInt(20) - 10.0D);
            int j = MathHelper.floor_double(posY + (double)worldObj.rand.nextInt(20) - 10.0D);
            int k = MathHelper.floor_double(posZ + (double)worldObj.rand.nextInt(20) - 10.0D);
            if(Plant.isFlower(worldObj, i, j - 1, k))
            {
				currentFlightTarget = new ChunkCoordinates(i, j, k);
				return true;
            }
        }
		return false;
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
	
	public boolean isBlockSolidBelow(int distance)
	{
		for(int y = 0; y < distance; y++)
		{
			if(worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - y, MathHelper.floor_double(posZ)).getMaterial().isSolid())
			{
				return true;
			}
		}
		return false;
	}
	
	public boolean isBlockSolidAbove(int distance)
	{
		for(int y = 0; y < distance; y++)
		{
			if(worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) + y, MathHelper.floor_double(posZ)).getMaterial().isSolid())
			{
				return true;
			}
		}
		return false;
	}
	
	public void setState(byte b)
	{
		dataWatcher.updateObject(16, Byte.valueOf(b));
	}
	
	public byte getState()
    {
        return dataWatcher.getWatchableObjectByte(16);
    }
	
    @Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
	{
		super.attackEntityFrom(damagesource, i);
		if(!isHostile())
		{
			return true;
		}
		if(damagesource.getEntity() instanceof EntityLiving)
		{
			targetedEntity = (EntityLiving)damagesource.getEntity();
			stationaryTick = 0;
		}
		return true;
	}
	
	public boolean isCorrupted()
	{
		return corruptingEnderwraith != null;
	}
	
	public void setCorrupted(EntityEnderwraith entityenderwraith)
	{
		if(canBeCorrupted())
		{
			corruptingEnderwraith = entityenderwraith;
		}
	}
	
	public boolean shouldTargetPlayer(EntityPlayer entityplayer)
	{
		if(this instanceof IEnderMob)
		{
			ItemStack itemstack = entityplayer.inventory.armorInventory[3];
			if(itemstack != null && itemstack.getItem() == Item.getItemFromBlock(Blocks.pumpkin))
			{
				return false;
			} else
			{
				Vec3 vec3 = entityplayer.getLook(1.0F).normalize();
				Vec3 vec3_1 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX - entityplayer.posX, this.boundingBox.minY + (double)(this.height / 2.0F) - (entityplayer.posY + (double)entityplayer.getEyeHeight()), this.posZ - entityplayer.posZ);
				double d = vec3_1.lengthVector();
				vec3_1 = vec3_1.normalize();
				double d1 = vec3.dotProduct(vec3_1);
				return d1 > 1.0D - 0.025D / d ? entityplayer.canEntityBeSeen(this) : false;
			}
		}
		return true;
	}
	
	public void setHomeLocation(int i, int j, int k)
	{
		homeLocation = new ChunkCoordinates(i, j, k);
	}
	
	public ChunkCoordinates getHomeLocation()
	{
		return homeLocation;
	}

	protected EntityLivingBase targetedEntity;
	protected ChunkCoordinates currentFlightTarget;
	protected short flowerCooldown;
	protected short attackCooldown = 0;
	protected EntityEnderwraith corruptingEnderwraith;
	protected ChunkCoordinates homeLocation;
	protected int stationaryTick;
	public static final byte FLYING = 0;
	public static final byte ON_FLOWER = 1;
	public static final byte WALKING = 2;
	public static final byte SWIMMING = 3;
	public static final byte HOVERING = 4;
	public static final byte GLIDING = 5;
	public static final byte STATIONARY = 6;
	public static final byte STING = 7;
	public static final byte CUSTOM_STATE_0 = 8;
	public static final byte CUSTOM_STATE_1 = 9;
}
