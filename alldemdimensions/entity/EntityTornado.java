package alldemdimensions.entity;

import java.util.HashMap;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityTornado extends Entity
{

    public EntityTornado(World world)
    {
        super(world);
        setSize(8.0F, 16.0F);
		noClip = true;
    }
	
    @Override
	protected void entityInit()
	{
	}
	
    @Override
    public void onUpdate()
    {
		super.onUpdate();
		/*if(worldObj.isRemote)
		{
			return;
		}*/
		
		int x = MathHelper.floor_double(posX);
		int y = MathHelper.floor_double(posY);
		int z = MathHelper.floor_double(posZ);
		
		if(destination == null || destination.getDistanceSquared(x, y, z) <= 4.0D)
		{
			int x1 = x + rand.nextInt(16) - rand.nextInt(16);
			int y1 = y + rand.nextInt(16) - rand.nextInt(16);
			int z1 = z + rand.nextInt(16) - rand.nextInt(16);
			destination = new ChunkCoordinates(x1, y1, z1);
		}
		
		if(destination != null)
		{
			int diffX = destination.posX - x;
			int diffY = destination.posY - y;
			int diffZ = destination.posZ - z;
			int diffTotal = (int)(Math.abs(diffX) + Math.abs(diffY) + Math.abs(diffZ));			
			motionX = ((double)diffX / (double)diffTotal);
			motionY = ((double)diffY / (double)diffTotal);
			motionZ = ((double)diffZ / (double)diffTotal);
			moveEntity(motionX, motionY, motionZ);/*
			this.boundingBox.offset(motionX, motionY, motionZ);
            this.posX = (this.boundingBox.minX + this.boundingBox.maxX) / 2.0D;
            this.posY = this.boundingBox.minY + (double)this.yOffset - (double)this.ySize;
            this.posZ = (this.boundingBox.minZ + this.boundingBox.maxZ) / 2.0D;*/
		}
		
		List list = worldObj.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expand(4D, 8D, 4D));
		Entity entity;
		for(int counter = 0; counter < list.size(); counter++)
		{
			entity = (Entity)list.get(counter);
			if(entity != null && isEntityVulnerable(entity))
			{
				if(rand.nextInt(100) == 0)
				{
					entity.motionX = rand.nextDouble();
					entity.motionY = rand.nextDouble();
					entity.motionZ = rand.nextDouble();
				} else
				{
					entity.motionX = Math.cos(ticksExisted);
					entity.motionY = 0.2D;
					entity.motionZ = -Math.sin(ticksExisted);
				}
			}
		}
		int blockCounter = 0;
		Block block;
		int vulnerability;
		for(int i = -4; i < 4; i++)
		{
			for(int j = 0; j < 16; j++)
			{
				for(int k = -4; k < 4; k++)
				{
					if(!worldObj.isAirBlock(x + i, y + j, z + k))
					{
						block = worldObj.getBlock(x + i, y + j, z + k);
						vulnerability = getMaterialVulnerability(block.getMaterial());
						if(vulnerability > 0 && rand.nextInt(vulnerability) == 0)
						{
							block.dropBlockAsItem(worldObj, x + i, y + j, z + k, worldObj.getBlockMetadata(x + i, y + j, z + k), 0);
							worldObj.setBlockToAir(x + i, y + j, z + k);
						}
						blockCounter++;
					}
				}
			}
		}
		if(blockCounter > 2048 || rand.nextInt(1000) == 0)
		{
			setDead();
		}
	}
	
	public int getMaterialVulnerability(Material material)
	{
		if(materialVulnerability.containsKey(material))
		{
			return materialVulnerability.get(material);
		}
		return 0;
	}
	
	public boolean isEntityVulnerable(Entity entity)
	{
		if(entity instanceof EntityAurora || entity instanceof EntityTornado)
		{
			return false;
		}
		return true;
	}
	
    @Override
    protected void writeEntityToNBT(NBTTagCompound nbttagcompound) {}

    @Override
    protected void readEntityFromNBT(NBTTagCompound nbttagcompound) {}

    @Override
    public boolean canBeCollidedWith()
    {
        return false;
    }

    @Override
    public boolean canBePushed()
    {
        return false;
    }
	
	protected void collideWithEntity(Entity entity) {}
	
	protected void func_85033_bc() {}

	public boolean isOnLadder()
	{
		return false;
	}

    @Override
    public boolean attackEntityFrom(DamageSource damagesource, float damage)
    {
		return false;
    }
	
    @Override
	protected void updateFallState(double d, boolean flag)
    {
	}
	
    @Override
	protected void fall(float f)
    {
	}
	
	private ChunkCoordinates destination;
	public static final HashMap<Material, Integer> materialVulnerability = new HashMap<Material, Integer>();
	static
	{
		materialVulnerability.put(Material.wood, 128);
		materialVulnerability.put(Material.gourd, 64);
		materialVulnerability.put(Material.tnt, 64);
		materialVulnerability.put(Material.grass, 32);
		materialVulnerability.put(Material.ground, 32);
		materialVulnerability.put(Material.leaves, 32);
		materialVulnerability.put(Material.cloth, 32);
		materialVulnerability.put(Material.clay, 32);
		materialVulnerability.put(Material.sand, 16);
		materialVulnerability.put(Material.craftedSnow, 16);
		materialVulnerability.put(Material.snow, 8);
		materialVulnerability.put(Material.plants, 8);
		materialVulnerability.put(Material.vine, 8);
		materialVulnerability.put(Material.circuits, 8);
		materialVulnerability.put(Material.cactus, 8);
		materialVulnerability.put(Material.cake, 8);
	}

}
