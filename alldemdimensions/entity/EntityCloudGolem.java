package alldemdimensions.entity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityGhast;
import net.minecraft.entity.monster.EntityGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityCloudGolem extends EntityGolem
{

	public EntityCloudGolem(World world)
	{
		super(world);
        setSize(3.0F, 3.0F);
	}
	
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100D);
    }
	
    @Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	
    @Override
	public void onUpdate()
	{
		super.onUpdate();
		if(attackCooldown > 0)
		{
			attackCooldown--;
		}
		if(targetedEntity != null && targetedEntity.isDead)
		{
			targetedEntity = null;
		}
		if(attackCooldown == 0 && targetedEntity != null)
		{
			EntityCloud entitycloud = new EntityCloud(worldObj, this/*, targetedEntity, 1.6F, 12.0F*/);
			worldObj.spawnEntityInWorld(entitycloud);
			attackCooldown = 50;
		}
	}
	
        @Override
	protected void updateAITasks()
	{
		if(targetedEntity == null)
		{
			entitiesToAttack.clear();
			List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(32.0D, 32.0D, 32.0D));
			for(int i = 0; i < list.size(); i++)
			{
				Entity entity = (Entity)list.get(i);
				if((entity instanceof EntityMob || entity instanceof EntityGhast) && canEntityBeSeen(entity))
				{
					entitiesToAttack.add((EntityLiving)entity);
				}
			}
			if(entitiesToAttack.size() == 0)
			{
				return;
			} else
			if(entitiesToAttack.size() == 1)
			{
				targetedEntity = entitiesToAttack.get(0);
			} else
			{
				Vec3 golemPosition = Vec3.createVectorHelper(posX, posY, posZ);
				Vec3 mobPosition;
				EntityLiving nearestEntity = null;
				double distance = 100D;
				for(EntityLiving entity : entitiesToAttack)
				{
					mobPosition = Vec3.createVectorHelper(entity.posX, entity.posY, entity.posZ);
					if(nearestEntity == null || mobPosition.distanceTo(golemPosition) < distance)
					{
						nearestEntity = entity;
						distance = mobPosition.distanceTo(golemPosition);
					}
				}
				targetedEntity = nearestEntity;
			}
		}
	}
	
	private ArrayList<EntityLiving> entitiesToAttack = new ArrayList<EntityLiving>();
	private EntityLiving targetedEntity;
	private short attackCooldown = 0;
}
