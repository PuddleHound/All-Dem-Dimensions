package alldemdimensions.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.environment.EnumZenithDay;

public class EntityEnderwraith extends EntityZenithFlying implements IEnderMob
{
	//Part of the code here has been removed for further work.
	public EntityEnderwraith(World world)
	{
		super(world);
        setSize(0.5F, 1.5F);
	}
	
    @Override
	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(17, new Byte((byte)0));
    }
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40D);
    }
	
    @Override
	public boolean canSpawnDuring(EnumZenithDay enumzenithday)
	{
		if(enumzenithday == EnumZenithDay.NIGHT || worldObj.provider.dimensionId == Dimension.ender.dimensionId)
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
	public double getVisionDistance()
	{
		return 32D;
	}
	
    @Override
	public boolean canBeCorrupted()
	{
		return false;
	}
	
    @Override
	public int getAttackStrength()
	{
		return 5;
	}
	
    @Override
	public void onLivingUpdate()
	{
    	/*if (this.isWet())
        {
            this.attackEntityFrom(DamageSource.drown, 1);
        }*/
    	
		if(targetedEntity != null)
		{
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32.0D, 32.0D, 32.0D));
			Entity entity;
			for(int i = 0; i < list.size(); i++)
			{
				entity = (Entity)list.get(i);
				if(entity instanceof EntityZenithFlying)
				{
					if(((EntityZenithFlying)entity).canBeCorrupted())
					{
						((EntityZenithFlying)entity).setCorrupted(this);
						double distanceX = entity.posX - posX;
						double distanceY = entity.posY - posY;
						double distanceZ = entity.posZ - posZ;
						double distanceTotal = distanceX * distanceX + distanceY * distanceY + distanceZ * distanceZ;
						distanceX /= distanceTotal;
						distanceY /= distanceTotal;
						distanceZ /= distanceTotal;
						for(int counter = 1; (double)counter < distanceTotal; counter++)
						{
							worldObj.spawnParticle("portal", distanceX * counter, distanceY * counter, distanceZ * counter, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
						}
					}
				}
			}
		}
	
        this.isJumping = false;

        if (this.targetedEntity != null)
        {
            this.faceEntity(this.targetedEntity, 100.0F, 100.0F);
        }
        
		for(int particleCount = 0; particleCount < 2; ++particleCount)
        {
            this.worldObj.spawnParticle("portal", this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
        }
		super.onLivingUpdate();
	}
	
	
    @Override
	protected Item getDropItem()
    {
        return Items.ender_pearl;
    }
	
    @Override
    protected void dropFewItems(boolean flag, int i)
    {
        Item item = this.getDropItem();

        if (item != null)
        {
            int totalItems = this.rand.nextInt(2 + i);

            for (int itemCounter = 0; itemCounter < totalItems; ++itemCounter)
            {
                this.dropItem(item, 1);
            }
        }
    }
	
	
}
