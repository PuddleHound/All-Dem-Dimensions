package alldemdimensions.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.environment.EnumZenithDay;

public class EntityAmpfly extends EntityZenithFlying
{

	public EntityAmpfly(World world)
	{
		super(world);
        setSize(1.0F, 0.5F);
	}
	
    @Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(17, new Integer(0));
		dataWatcher.addObject(18, new Integer(-100));
		dataWatcher.addObject(19, new Integer(0));
		dataWatcher.addObject(20, new Short((short)0));
		dataWatcher.addObject(21, new Integer(0));
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(16D);
    }
	
    @Override
	public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
	
    @Override
	public int getAttackStrength()
	{
		return 0;
	}
	
    @Override
	protected Item getDropItem()
	{
		return AllDemDimensions.phosphorus;
	}
	
    @Override
	public float getFlightSpeed()
	{
		return 0.5F;
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
	public boolean canSpawnDuring(EnumZenithDay enumzenithday)
	{
		if(enumzenithday == EnumZenithDay.NIGHT)
		{
			return true;
		}
		return false;
	}
	
    @Override
	public boolean isHostile()
	{
		return true;
	}
	
    @Override
	public boolean hasRangedAttack()
	{
		return true;
	}
	
    @Override
	public void onUpdate()
	{
		super.onUpdate();
		int i = getEnergyAttackDuration();
		//if(targetedEntity != null)
		{
			//setTargetPosition((int)targetedEntity.posX, (int)targetedEntity.posY, (int)targetedEntity.posZ);
			setAmpflyTarget(targetedEntity);
			if(i <= 0)
			{
				setEnergyAttackDuration((short)0);
				if(targetedEntity != null && rand.nextInt(20) == 0 && getDistanceToEntity(targetedEntity) < 16F && isCourseTraversable(targetedEntity.posX, targetedEntity.posY, targetedEntity.posZ))
				{
					setEnergyAttackDuration((short)1);
					setEnergyAttackLocation((int)targetedEntity.posX, (int)targetedEntity.posY, (int)targetedEntity.posZ);
					isAttackCharging = true;
				}
			} else
			{
				int[] location = getEnergyAttackLocation();
				if(location != null)
				{
					performEnergyAttack(location[0], location[1], location[2], i / 4);
				}
				if(i >= 16)
				{
					isAttackCharging = false;
					//setEnergyAttackDuration((short)(i - 1));
				}
				if(isAttackCharging)
				{
					//short s = (short)(getEnergyAttackDuration() - 1);
					setEnergyAttackDuration((short)(i + 1));
				} else
				{
					setEnergyAttackDuration((short)(i - 1));
				}
			}
		}
		if(targetedEntity == null)
		{
			setAmpflyTarget(null);
			setEnergyAttackDuration((short)0);
			//setTargetPosition(0, -100, 0);
		}
	}
	
	public boolean performEnergyAttack(double x, double y, double z, int damage)
    {
		double distanceX = x - posX;
		double distanceY = y - posY;
		double distanceZ = z - posZ;
		double distanceTotal = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
        distanceX /= distanceTotal;
        distanceY /= distanceTotal;
        distanceZ /= distanceTotal;
        AxisAlignedBB bounds = boundingBox.copy();
		List entitiesInPath;
		ArrayList<Entity> entitiesAttacked = new ArrayList<Entity>();
		Entity entityInPath;
        for(int counter = 1; (double)counter < distanceTotal; counter++)
        {
            bounds.offset(distanceX, distanceY, distanceZ);
            entitiesInPath = worldObj.getEntitiesWithinAABBExcludingEntity(this, bounds);
			for(int i = 0; i < entitiesInPath.size(); i++)
			{
				entityInPath = (Entity)entitiesInPath.get(i);
				if(entityInPath != null && !entitiesAttacked.contains(entityInPath) && !(entityInPath instanceof EntityAmpfly))
				{
					entityInPath.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
					entitiesAttacked.add(entityInPath);
				}
			}
        }
        return true;
    }
	
	public void setAmpflyTarget(EntityLivingBase entity)
	{/*//1.7
		if(entity != null)
		{
			dataWatcher.updateObject(21, Integer.valueOf(entity.entityId));
		} else
		{
			dataWatcher.updateObject(21, Integer.valueOf(-1));
		}*/
	}
	
	public EntityLiving getAmpflyTarget()
	{
		int id = dataWatcher.getWatchableObjectInt(21);
		if(id == -1)
		{
			return null;
		}
		Entity entity = worldObj.getEntityByID(id);
		if(entity != null && entity instanceof EntityLiving)
		{
			return (EntityLiving)entity;
		}
		return null;
	}
	
	public void setEnergyAttackLocation(int i, int j, int k)
	{
		dataWatcher.updateObject(17, Integer.valueOf(i));
		dataWatcher.updateObject(18, Integer.valueOf(j));
		dataWatcher.updateObject(19, Integer.valueOf(k));
	}
	
	public int[] getEnergyAttackLocation()
    {
        return new int[]{dataWatcher.getWatchableObjectInt(17), dataWatcher.getWatchableObjectInt(18),
			dataWatcher.getWatchableObjectInt(19)};
    }
	
	public void setEnergyAttackDuration(short s)
	{
		dataWatcher.updateObject(20, Short.valueOf(s));
	}
	
	public short getEnergyAttackDuration()
	{
		return dataWatcher.getWatchableObjectShort(20);
	}
	
	private boolean isAttackCharging;

}
