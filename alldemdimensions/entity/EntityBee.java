package alldemdimensions.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class EntityBee extends EntityZenithFlying
{

	public EntityBee(World world)
	{
		super(world);
        setSize(1.0F, 0.5F);
	}
	
	/*public EntityBee(World world, byte type)
	{
		this(world);
		dataWatcher.addObject(17, new Byte((byte)0));
		setBeeType(type);
		if(getBeeType() == TYPE_WORKER_HIVE)
		{
			homeLocation = new ChunkCoordinates(MathHelper.floor_double(posX), MathHelper.floor_double(posY), MathHelper.floor_double(posZ));
		}
	}*/
	
	@Override
	protected void entityInit()
	{
		super.entityInit();
		dataWatcher.addObject(17, new Byte((byte)0));
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(8D);
    }
	
    @Override
	public EnumCreatureAttribute getCreatureAttribute()
    {
        return EnumCreatureAttribute.ARTHROPOD;
    }
	
    @Override
	public int getAttackStrength()
	{
		return 2;
	}
	
    @Override
	public boolean isAttractedToFlowers()
	{
		return targetedEntity == null;
	}
	
    @Override
	protected void updateAITasks()
	{
		super.updateAITasks();
		if(targetedEntity != null)
		{
			setState(STING);
		}
		if(homeLocation != null && rand.nextInt(16) == 0 && (Math.abs(homeLocation.posX - posX) /*+ Math.abs(homeLocation.posY - posY)*/ +
			Math.abs(homeLocation.posZ - posZ)) > 64)
		{
			targetedEntity = null;
			currentFlightTarget = homeLocation;
		}
	}
	
    @Override
	protected Item getDropItem()
	{
		return AllDemDimensions.wax;
	}
	
    @Override
	public boolean attackEntityFrom(DamageSource damagesource, float i)
    {
	    Entity entity = damagesource.getEntity();
	    if(entity instanceof EntityLiving)
	    {
			alertBeesOfAttack(worldObj, (EntityLiving)entity);
	    }
	    return super.attackEntityFrom(damagesource, i);
    }
	
	public static void alertBeesOfAttack(World world, EntityLivingBase attacker)
	{
		List list = world.getEntitiesWithinAABBExcludingEntity(attacker, attacker.boundingBox.expand(32.0D, 32.0D, 32.0D));
        for(int counter = 0; counter < list.size(); counter++)
        {
            Entity entity = (Entity)list.get(counter);
            if (entity instanceof EntityBee)
            {
                EntityBee bee = (EntityBee)entity;
				bee.targetedEntity = attacker;
            }
        }
	}
	
    @Override
	public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
		if(homeLocation != null)
		{
			nbttagcompound.setIntArray("hiveLocation", new int[]{homeLocation.posX, homeLocation.posY, homeLocation.posZ});
		}
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        int[] location = nbttagcompound.getIntArray("hiveLocation");
		if(location.length == 3)
		{
			homeLocation = new ChunkCoordinates(location[0], location[1], location[2]);
		}
    }
	
	public void setBeeType(byte b)
    {
        dataWatcher.updateObject(17, Byte.valueOf(b));
    }

    public byte getBeeType()
    {
        return dataWatcher.getWatchableObjectByte(17);
    }
	
	public static final byte TYPE_WORKER_SOLITARY = 0;
	public static final byte TYPE_WORKER_HIVE = 1;
	public static final byte TYPE_QUEEN = 2;
}
