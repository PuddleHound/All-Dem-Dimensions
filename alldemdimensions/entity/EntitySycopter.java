package alldemdimensions.entity;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Blocks;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.world.environment.EnumZenithDay;

public class EntitySycopter extends EntityZenithFlying
{
    public EntitySycopter(World world)
    {
        super(world);
        this.setSize(0.9F, 0.9F);
    }

    @Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(15D);
    }

    @Override
	public boolean isHostile()
	{
		return true;
	}
	
    @Override
	public int getAttackStrength()
	{
		return 4;
	}
	
    @Override
	public int getMaxTravelDistance()
	{
		return 4;
	}
	
    @Override
	public float getFlightSpeed()
	{
		return targetedEntity != null ? 0.5F : 0.2F;
	}

    @Override
	public boolean isFlightSporadic()
	{
		return true;
	}
	
    @Override
	public boolean getCanSpawnHere()
    {
		return super.getCanSpawnHere() && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)) == Blocks.grass;
	}
	
    @Override
	public boolean canSpawnDuring(EnumZenithDay enumzenithday)
	{
		if(enumzenithday == EnumZenithDay.HIGH_DAY)
		{
			return true;
		}
		return false;
	}
	
    @Override
	protected void updateAITasks()
	{
		super.updateAITasks();
		if(homeLocation == null)
		{
			homeLocation = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		}
		if(targetedEntity != null && rand.nextInt(100) == 0 && homeLocation.getDistanceSquared((int)posX, (int)posY, (int)posZ) > 64F)
		{
			targetedEntity = null;
			currentFlightTarget = homeLocation;
		}
		if(targetedEntity == null && rand.nextInt(500) == 0 && (getState() == STATIONARY || getState() == FLYING) && worldObj.canBlockSeeTheSky(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ)))
		{
			setState(ASCENDING);
		}
		if(getState() == ASCENDING)
		{
			motionY = 1.0D;
			if(posY > 250)
			{
				setState(DESCENDING);
			}
		}
		if(getState() == DESCENDING)
		{
			motionY = -0.1D;
			if(onGround)
			{
				setState(STATIONARY);
			}
		}
	}
	
	public boolean isAscending;
	public boolean isDescending;
	public float rotorRotationX;
	public float rotorRotationY;
	public static final byte ASCENDING = CUSTOM_STATE_0;
	public static final byte DESCENDING = CUSTOM_STATE_1;

}
