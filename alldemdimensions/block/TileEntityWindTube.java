package alldemdimensions.block;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import alldemdimensions.AllDemDimensions;

public class TileEntityWindTube extends TileEntity
{
    public TileEntityWindTube()
    {
    }
	
    @Override
	public void updateEntity()
    {
		int metadata = worldObj.getBlockMetadata(xCoord, yCoord, zCoord);
		double[] range = directionToRangeMap[metadata];
		double[] windRange = directionToWindRangeMap[metadata];
				List<Entity> list = (List<Entity>)worldObj.getLoadedEntityList();
				for(Entity entity : list)
				{
					float strength = calculateWindStrength(metadata);
					float minXStrength = strength;
					float minYStrength = strength;
					float minZStrength = strength;
					if(metadata == AllDemDimensions.windTube.POS_X || metadata == AllDemDimensions.windTube.NEG_X)
					{
						minXStrength = 1F;
					} else
					if(metadata == AllDemDimensions.windTube.POS_Z || metadata == AllDemDimensions.windTube.NEG_Z)
					{
						minZStrength = 1F;
					} else
					if(metadata == AllDemDimensions.windTube.POS_Y || metadata == AllDemDimensions.windTube.NEG_Y)
					{
						minYStrength = 1F;
					}
					if(entity != null && entity instanceof EntityItem && isEntityNearby(entity, range[0], range[1], range[2], range[3], range[4], range[5]) && canEntityBeMoved(entity, 0.5D, 0.5D, 0.5D, metadata))
					{
						Vec3 vec3d = Vec3.createVectorHelper(xCoord + 0.5D - entity.posX, yCoord + 0.5D - entity.posY, zCoord + 0.5D - entity.posZ);
						entity.moveEntity(vec3d.xCoord, vec3d.yCoord, vec3d.zCoord);
					} else
					if(entity != null && isEntityNearby(entity, windRange[0] * minXStrength, windRange[1] * strength, windRange[2] * minYStrength, windRange[3] * strength, windRange[4] * minZStrength, windRange[5] * strength))
					{
						boolean flag = entity instanceof EntityItem;
						Block i = worldObj.getBlock(xCoord + 1, yCoord, zCoord);//check for solid block in front
						Block j = worldObj.getBlock(xCoord - 1, yCoord, zCoord);
						Block k = worldObj.getBlock(xCoord, yCoord, zCoord + 1);
						Block l = worldObj.getBlock(xCoord, yCoord, zCoord - 1);
						Block m = worldObj.getBlock(xCoord, yCoord + 1, zCoord);
						Block n = worldObj.getBlock(xCoord, yCoord - 1, zCoord);
						int i1 = worldObj.getBlockMetadata(xCoord + 1, yCoord, zCoord);
						int j1 = worldObj.getBlockMetadata(xCoord - 1, yCoord, zCoord);
						int k1 = worldObj.getBlockMetadata(xCoord, yCoord, zCoord + 1);
						int l1 = worldObj.getBlockMetadata(xCoord, yCoord, zCoord - 1);
						int m1 = worldObj.getBlockMetadata(xCoord, yCoord + 1, zCoord);
						int n1 = worldObj.getBlockMetadata(xCoord, yCoord - 1, zCoord);
						if(metadata == AllDemDimensions.windTube.POS_X)
						{
							if(flag && (i == Blocks.chest || i == AllDemDimensions.amberChest))
							{
								placeItemInChest((EntityItem)entity, xCoord + 1, yCoord, zCoord, i);
							} else
							if((flag || i != AllDemDimensions.windTube || i1 != AllDemDimensions.windTube.POS_X) && canEntityBeMoved(entity, 1.1D, 0.5D, 0.5D, metadata))
							{
								//entity.motionX = 0.2D;
								entity.moveEntity(0.25D, 0, 0);
							}
							//entity.motionX += 0.5F;
						}
						if(metadata == AllDemDimensions.windTube.NEG_X)
						{
							if(flag && (j == Blocks.chest || j == AllDemDimensions.amberChest))
							{
								placeItemInChest((EntityItem)entity, xCoord - 1, yCoord, zCoord, j);
							} else
							if((flag || j != AllDemDimensions.windTube || j1 != AllDemDimensions.windTube.NEG_X) && canEntityBeMoved(entity, -0.1D, 0.5D, 0.5D, metadata))
							{
								entity.moveEntity(-0.25D, 0, 0);
								//entity.motionX -= 0.2D;
							}
							//entity.motionX -= 0.5F;
						}
						if(metadata == AllDemDimensions.windTube.POS_Z)
						{
							if(flag && (k == Blocks.chest || k == AllDemDimensions.amberChest))
							{
								placeItemInChest((EntityItem)entity, xCoord, yCoord, zCoord + 1, k);
							} else
							if((flag || k != AllDemDimensions.windTube || k1 != AllDemDimensions.windTube.POS_Z) && canEntityBeMoved(entity, 0.5D, 0.5D, 1.1D, metadata))
							{
								entity.moveEntity(0, 0, 0.25D);
								//entity.motionZ += 0.2D;
							}
							//entity.motionZ += 0.5F;
						}
						if(metadata == AllDemDimensions.windTube.NEG_Z)
						{
							if(flag && (l == Blocks.chest || l == AllDemDimensions.amberChest))
							{
								placeItemInChest((EntityItem)entity, xCoord, yCoord, zCoord - 1, l);
							} else
							if((flag || l != AllDemDimensions.windTube || l1 != AllDemDimensions.windTube.NEG_Z) && canEntityBeMoved(entity, 0.5D, 0.5D, -0.1D, metadata))
							{
								entity.moveEntity(0, 0, -0.25D);
								//entity.motionZ -= 0.2D;
							}
							//entity.motionZ -= 0.5F;
						}
						if(metadata == AllDemDimensions.windTube.POS_Y)
						{
							if(flag && (k == Blocks.chest || k == AllDemDimensions.amberChest))
							{
								placeItemInChest((EntityItem)entity, xCoord, yCoord + 1, zCoord, k);
							} else
							if((flag || k != AllDemDimensions.windTube || k1 != AllDemDimensions.windTube.POS_Y) && canEntityBeMoved(entity, 0.5D, 1.1D, 0.5D, metadata))
							{
								entity.moveEntity(0, 0.25D, 0);
								//entity.motionY += 0.2D;
							}
							//entity.motionZ += 0.5F;
						}
						if(metadata == AllDemDimensions.windTube.NEG_Y)
						{
							if(flag && (l == Blocks.chest || l == AllDemDimensions.amberChest))
							{
								placeItemInChest((EntityItem)entity, xCoord, yCoord - 1, zCoord, l);
							} else
							if((flag || l != AllDemDimensions.windTube || l1 != AllDemDimensions.windTube.NEG_Y) && canEntityBeMoved(entity, 0.5D, -0.1D, 0.5D, metadata))
							{
								entity.moveEntity(0, -0.25D, 0);
								//entity.motionY -= 0.2D;
							}
							//entity.motionZ -= 0.5F;
						}
					}
				}
    }
	
	public boolean isEntityNearby(Entity entity, double i, double j, double k, double l, double i1, double j1)
	{
		return entity.posX > xCoord + i && entity.posX < xCoord + j && entity.posY > yCoord + k + (entity.yOffset * 0.5D) && entity.posY < yCoord + l + (entity.yOffset * 0.5D) && entity.posZ > zCoord + i1 && entity.posZ < zCoord + j1;
	}
	
	public boolean canEntityBeMoved(Entity entity, double d, double d1, double d2, int metadata)
	{
		for(float yOffset = 0; yOffset < entity.height; yOffset += entity.height * 0.25F)
		{
			double x = (xCoord + d) - (entity.posX/* - (Math.signum(d) * entity.width * 0.5D)*/);
			double y = (yCoord + d1) - (entity.posY - yOffset/* - (entity.height * 0.5D)*/);
			double z = (zCoord + d2) - (entity.posZ/* - (Math.signum(d2) * entity.width * 0.5D)*/);
			double distance = x * x + y * y + z * z;
			x /= distance;
	        y /= distance;
	        z /= distance;
	        AxisAlignedBB bounds = entity.boundingBox.copy();
			int boxes = 0;
	        for(int count = 1; (double)count < distance; ++count)
	        {
	            bounds.offset(x, y, z);
	            if(!worldObj.getCollidingBoundingBoxes(entity, bounds).isEmpty())
	            {
					boxes++;
	                //return false;
	            }
	        }
			if(boxes == 0)
			{
				return true;
			}
		}
        return false;
	}
	
	public void placeItemInChest(EntityItem entityitem, int i, int j, int k, Block chestId)
	{
		TileEntityChest chest = (TileEntityChest)worldObj.getTileEntity(i, j, k);
		if(chest == null || worldObj.isRemote)
		{
			return;
		}
		ItemStack itemstack = entityitem.getDataWatcher().getWatchableObjectItemStack(10);
		ItemStack itemstack1;
		int slot = 0;
		while(slot < chest.getSizeInventory())
		{
			itemstack1 = chest.getStackInSlot(slot);
			if(itemstack1 == null)
			{
				chest.setInventorySlotContents(slot, itemstack);
				break;
			}
			if(itemstack1.getItem() == itemstack.getItem() && itemstack1.getItemDamage() == itemstack.getItemDamage())
			{
				if(itemstack.stackSize + itemstack1.stackSize <= itemstack1.getMaxStackSize())
				{
					itemstack1.stackSize += itemstack.stackSize - 1;
					entityitem.setDead();
					break;
				} else
				{
					int size = itemstack1.stackSize;
					itemstack1.stackSize = itemstack1.getMaxStackSize();
					itemstack.stackSize -= itemstack1.getMaxStackSize() - size;	
				}
			}
			slot++;
		}
	}
	
	public float calculateWindStrength(int metadata)
	{
			int offsetX = 0;
			int offsetY = 0;
			int offsetZ = 0;
			if(metadata == AllDemDimensions.windTube.POS_X)
			{
				offsetX = -1;
			} else
			if(metadata == AllDemDimensions.windTube.NEG_X)
			{
				offsetX = 1;
			} else
			if(metadata == AllDemDimensions.windTube.POS_Z)
			{
				offsetZ = -1;
			} else
			if(metadata == AllDemDimensions.windTube.NEG_Z)
			{
				offsetZ = 1;
			} else
			if(metadata == AllDemDimensions.windTube.POS_Y)
			{
				offsetY = -1;
			} else
			if(metadata == AllDemDimensions.windTube.NEG_Y)
			{
				offsetY = 1;
			}
			int counter = 0;
			while(counter < 8)
			{
				if(worldObj.getBlock(xCoord + (offsetX * counter), yCoord + (offsetY * counter), zCoord + (offsetZ * counter)) == AllDemDimensions.windTube && worldObj.getBlockMetadata(xCoord + (offsetX * counter), yCoord + (offsetY * counter), zCoord + (offsetZ * counter)) == metadata)
				{
					counter++;
				} else
				{
					break;
				}
			}
			return 1F + (counter * 0.125F);
	}
	
	private static final double[][] directionToRangeMap = new double[6][6];
	private static final double[][] directionToWindRangeMap = new double[6][6];
	static
	{
		directionToRangeMap[AllDemDimensions.windTube.NEG_X] = new double[]{0, 2, -1, 1, -1, 1};
		directionToRangeMap[AllDemDimensions.windTube.POS_X] = new double[]{-2, 0, -1, 1, -1, 1};
		directionToRangeMap[AllDemDimensions.windTube.NEG_Z] = new double[]{-1, 1, -1, 1, 0, 2};
		directionToRangeMap[AllDemDimensions.windTube.POS_Z] = new double[]{-1, 1, -1, 1, -2, 0};
		directionToRangeMap[AllDemDimensions.windTube.NEG_Y] = new double[]{-1, 1, 0, 2, -1, 1};
		directionToRangeMap[AllDemDimensions.windTube.POS_Y] = new double[]{-1, 1, -2, 0, -1, 1};
	
		directionToWindRangeMap[AllDemDimensions.windTube.NEG_X] = new double[]{-3, 0, -0.5D, 1.5D, -0.5D, 1.5D};
		directionToWindRangeMap[AllDemDimensions.windTube.POS_X] = new double[]{1, 4, -0.5D, 1.5D, -0.5D, 1.5D};
		directionToWindRangeMap[AllDemDimensions.windTube.NEG_Z] = new double[]{-0.5D, 1.5D, -0.5D, 1.5D, -3, 0};
		directionToWindRangeMap[AllDemDimensions.windTube.POS_Z] = new double[]{-0.5D, 1.5D, -0.5D, 1.5D, 1, 4};
		directionToWindRangeMap[AllDemDimensions.windTube.NEG_Y] = new double[]{-0.5D, 1.5D, -3, 0, -0.5D, 1.5D};
		directionToWindRangeMap[AllDemDimensions.windTube.POS_Y] = new double[]{-0.5D, 1.5D, 1, 4, -0.5D, 1.5D};
	}
}