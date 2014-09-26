package alldemdimensions.entity;

import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.world.environment.EnumZenithDay;

public class EntityHeliumSlime extends EntitySlime
{

	public EntityHeliumSlime(World world)
	{
		super(world);
	}
	
    @Override
	protected void entityInit()
    {
        super.entityInit();
        dataWatcher.addObject(17, new Integer(0));
    }
	
    @Override
	public void onLivingUpdate()
	{
		super.onLivingUpdate();
		for(int particleCount = 0; particleCount < getSlimeSize(); ++particleCount)
        {
			AllDemDimensions.proxyInstance.spawnParticle("heliumBubble", worldObj, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height - 0.25D, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, (this.rand.nextDouble() - 0.5D) * 2.0D, -this.rand.nextDouble(), (this.rand.nextDouble() - 0.5D) * 2.0D);
		}
	}
	
    @Override
	public void onUpdate()
	{
		super.onUpdate();
		if(jumpTarget != null && jumpTarget.getDistanceSquared((int)posX, (int)posY, (int)posZ) < 4.0F)
		{
			slimeJumpTick = 0;
		}
		if(onGround || isCollidedHorizontally || rand.nextInt(200) == 0)
		{
			slimeJumpTick = 0;
		}
		if(onGround)
		{
			needsJumpTarget = false;
			isDescending = false;
		}
		if(needsJumpTarget && isBlockSolidBelow(16))
		{
			slimeJumpTick = 0;
			needsJumpTarget = false;
			isDescending = true;
		}
		if(slimeJumpTick > 0)
		{
			slimeJumpTick++;
		} else
		{
			jumpTarget = null;
		}
		setJumpTick(slimeJumpTick);
	}
	
	public boolean isBlockSolidBelow(int distance)
	{
		int i = 0;
		while(i < distance)
		{
			if(worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - distance, MathHelper.floor_double(posZ)).getMaterial().isSolid())
			{
				return true;
			}
			i++;
		}
		return false;
	}
	
    @Override
	protected void updateEntityActionState()
	{
		if(isDescending)
		{
			motionY = -0.1D;
			return;
		}
		if(slimeJumpTick <= 0)
		{
			super.updateEntityActionState();
		}
		if(slimeJumpTick > 0 && jumpTarget != null)
		{
			motionY = /*Math.abs*/(Math.cos((slimeJumpTick * 0.1D) % 180)) * 0.25D;
			
			double d = (double)this.jumpTarget.posX + 0.5D - this.posX;
			double d1 = (double)this.jumpTarget.posZ + 0.5D - this.posZ;
			this.motionX += (Math.signum(d) * 0.35D - this.motionX) * 0.10000000149011612D;//0.5D
			this.motionZ += (Math.signum(d1) * 0.35D - this.motionZ) * 0.10000000149011612D;//0.5D
			float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
			float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
			this.moveForward = 0.35F;//0.5F
			this.rotationYaw += f1;
		}
		if((!needsJumpTarget || jumpTarget != null) && (rand.nextInt(100) != 0 || !onGround || slimeJumpTick > 0 || worldObj.getClosestVulnerablePlayerToEntity(this, 16.0D) != null))
		{
			return;
		}
		int x;
		int y;
		int z;
		int size = getSlimeSize();
		for(int counter = 0; counter < 16; counter++)
		{
			x = (int)posX + rand.nextInt(size * 4) - rand.nextInt(size * 4);
			y = (int)posY + rand.nextInt(size * 2) - rand.nextInt(size * 2);
			z = (int)posZ + rand.nextInt(size * 4) - rand.nextInt(size * 4);
			if(/*worldObj.getBlockMaterial(x, y - 1, z).isSolid() && */worldObj.isAirBlock(x, y, z)/* && isCourseTraversable(x, y, z, size)*/)
			{
				jumpTarget = new ChunkCoordinates(x, y, z);
				slimeJumpTick = 1;
			}
		}
	}
	
	public boolean isCourseTraversable(double x, double y, double z, int height)
    {
		//% 180 + 90
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
	protected EntitySlime createInstance()
    {
        return new EntityHeliumSlime(worldObj);
    }

    @Override
	protected String getSlimeParticle()
    {
        return "bubble";
    }
	
    @Override
	protected Item getDropItem()
    {
        return null;
    }
	
    @Override
	public boolean getCanSpawnHere()
    {
		return EnumZenithDay.getCurrentTime(worldObj) == EnumZenithDay.HIGH_DAY && this.worldObj.checkNoEntityCollision(this.boundingBox) && this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && !this.worldObj.isAnyLiquid(this.boundingBox) && worldObj.getBlock(MathHelper.floor_double(posX), MathHelper.floor_double(posY) - 1, MathHelper.floor_double(posZ)).getMaterial().isSolid();
    }
	
	protected void fall()
	{
	}
	
    @Override
	protected void updateFallState(double d, boolean flag)
	{
		if(isDescending)
		{
			return;
		}
		if(fallDistance > 2 && (slimeJumpTick <= 0 || jumpTarget == null))
		{
			needsJumpTarget = true;
		} else
		if(slimeJumpTick <= 0)
		{
			super.updateFallState(d, flag);
		}
	}
	
    @Override
	public boolean interact(EntityPlayer entityplayer)
	{
		if(getSlimeSize() != 1)
		{
			return super.interact(entityplayer);
		}
		ItemStack itemstack = entityplayer.inventory.getCurrentItem();
        if (itemstack != null && itemstack.getItem() == Items.bucket)//gas cylinder instead of bucket?
        {
            if(itemstack.stackSize-- <= 0)
            {
                entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem, new ItemStack(AllDemDimensions.bucketThermal));//helium
            } else 
			if(!entityplayer.inventory.addItemStackToInventory(new ItemStack(AllDemDimensions.bucketThermal)))
            {
                entityplayer.func_146097_a(new ItemStack(AllDemDimensions.bucketThermal), true, false);//1.7.2 - dropPlayerItem - booleans?
            }
			setDead();
            return true;
        } else
        {
            return super.interact(entityplayer);
        }
	}
	
	public int getJumpTick()
	{
		return dataWatcher.getWatchableObjectInt(17);
	}
	
	protected void setJumpTick(int i)
    {
        dataWatcher.updateObject(17, new Integer(i));
	}
	
    @Override
	protected String getHurtSound()
    {
        return null;
    }

    @Override
    protected String getDeathSound()
    {
        return null;
    }
	
     @Override
	protected String getJumpSound()
    {
        return null;
    }
	
	private int slimeJumpTick;
	private ChunkCoordinates jumpTarget;
	private boolean needsJumpTarget;
	private boolean isDescending;
}
