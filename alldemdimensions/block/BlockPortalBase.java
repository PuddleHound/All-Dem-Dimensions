package alldemdimensions.block;

import java.util.ArrayList;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.world.Dimension;

public class BlockPortalBase extends Block
{
	
    public BlockPortalBase(float red, float green, float blue)
    {
        super(Material.portal);
        particleRed = red;
        particleGreen = green;
        particleBlue = blue;
        coloredLightValue = AllDemDimensions.makeColorLightValue(blue, green, red);
    }
    
    public Block getPortalFrame()
    {
    	if(this == AllDemDimensions.enderPortal)
    	{
    		return AllDemDimensions.enderCrystal;
    	}
    	return Dimension.getDimensionForPortal(this).portalFrameId;
    }
    
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }

    @Override
    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
        if (iblockaccess.getBlock(i - 1, j, k) == this || iblockaccess.getBlock(i + 1, j, k) == this 
        		|| iblockaccess.getBlock(i - 1, j, k) == getPortalFrame() || iblockaccess.getBlock(i + 1, j, k) == getPortalFrame())
        {
            float f = 0.5F;
            float f2 = 0.125F;
            setBlockBounds(0.5F - f, 0.0F, 0.5F - f2, 0.5F + f, 1.0F, 0.5F + f2);
        }
        else
        {
            float f1 = 0.125F;
            float f3 = 0.5F;
            setBlockBounds(0.5F - f1, 0.0F, 0.5F - f3, 0.5F + f1, 1.0F, 0.5F + f3);
        }
    }

    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }

    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
	
    @Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
    {/*
    	while(j >= 0)
		{
			if(checkForValidPortal(world, i, j, k, 0, 0, 0, i, j, k, true, true, new ArrayList<ChunkCoordinates>()))
			{
				return;
			}
			j--;
		}
    	world.setBlockToAir(i, j, k);*/
    }

    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if (iblockaccess.getBlock(i, j, k) == this)
        {
            return false;
        }
        boolean flag = iblockaccess.getBlock(i - 1, j, k) == this && iblockaccess.getBlock(i - 2, j, k) != this;
        boolean flag1 = iblockaccess.getBlock(i + 1, j, k) == this && iblockaccess.getBlock(i + 2, j, k) != this;
        boolean flag2 = iblockaccess.getBlock(i, j, k - 1) == this && iblockaccess.getBlock(i, j, k - 2) != this;
        boolean flag3 = iblockaccess.getBlock(i, j, k + 1) == this && iblockaccess.getBlock(i, j, k + 2) != this;
        boolean flag4 = flag || flag1;
        boolean flag5 = flag2 || flag3;
        if (flag4 && l == 4)
        {
            return true;
        }
        if (flag4 && l == 5)
        {
            return true;
        }
        if (flag5 && l == 2)
        {
            return true;
        }
        return flag5 && l == 3;
    }

    @Override
    public int quantityDropped(Random random)
    {
        return 0;
    }

    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }
	
    @Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if (random.nextInt(100) == 0)
        {
            world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "portal.portal", 0.5F, random.nextFloat() * 0.4F + 0.8F);
        }

        for (int i = 0; i < 4; i++)
        {
            double d = (float)x + random.nextFloat();
            double d1 = (float)y + random.nextFloat();
            double d2 = (float)z + random.nextFloat();
            double d3 = 0.0D;
            double d4 = 0.0D;
            double d5 = 0.0D;
            int j = random.nextInt(2) * 2 - 1;
            d3 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d4 = ((double)random.nextFloat() - 0.5D) * 0.5D;
            d5 = ((double)random.nextFloat() - 0.5D) * 0.5D;

            if (world.getBlock(x - 1, y, z) == this || world.getBlock(x + 1, y, z) == this)
            {
                d2 = (double)z + 0.5D + 0.25D * (double)j;
                d5 = random.nextFloat() * 2.0F * (float)j;
            }
            else
            {
                d = (double)x + 0.5D + 0.25D * (double)j;
                d3 = random.nextFloat() * 2.0F * (float)j;
            }

            AllDemDimensionsClient.spawnPortalParticle(world, particleRed, particleGreen, particleBlue, d, d1, d2, d3, d4, d5);
        }
    }
    
    public boolean checkForValidPortal(World world, int i, int j, int k, int dirX, int dirY, int dirZ, int originalX, int originalY, int originalZ, boolean createPortal, boolean firstCheck, ArrayList<ChunkCoordinates> frameBlockLocations)
    {
    	if(world.getBlock(i, j, k) != getPortalFrame())
    	{
    		return false;
    	}
    	if(!firstCheck && i == originalX && j == originalY && k == originalZ)
    	{
    		if(frameBlockLocations.size() < 10)
    		{
    			return false;
    		}
    		if(createPortal)
    		{
	    		ArrayList<ChunkCoordinates> portalBlockLocations = setPortalBlocks(world, i, j + 1, k, dirX, dirZ, new Block[]{}, new ArrayList<ChunkCoordinates>());
				if(portalBlockLocations != null && !portalBlockLocations.isEmpty())
				{
					for(ChunkCoordinates coords : portalBlockLocations)
					{
						world.setBlock(coords.posX, coords.posY, coords.posZ, this);
					}
				}
    		}
    		return true;
    	}
    	if(!firstCheck)
    	{
    		frameBlockLocations.add(new ChunkCoordinates(i, j, k));
    	}
    	int offsetXRange = 1, offsetZRange = 1;
    	if(dirX != 0)
    	{
    		offsetZRange = 0;
    	} else
    	if(dirZ != 0)
    	{
    		offsetXRange = 0;
    	}
    	for(int offsetX = -offsetXRange; offsetX <= offsetXRange; offsetX++)
		{
			for(int offsetY = -1; offsetY <= 1; offsetY++)
			{
				for(int offsetZ = -offsetZRange; offsetZ <= offsetZRange; offsetZ++)
				{
					if(offsetX != 0 && offsetZ != 0)
					{
						continue;
					}
					if(offsetX == 0 && offsetY == 0 && offsetZ == 0)
					{
						continue;
					}
					if(firstCheck && offsetX == 0 && offsetZ == 0 && offsetY != 0)
					{
						continue;
					}
					if(offsetX == -dirX && offsetY == -dirY && offsetZ == -dirZ)
					{
						continue;
					}
					boolean alreadyChecked = false;
					for(ChunkCoordinates coords : frameBlockLocations)
					{
						if(coords.posX == i + offsetX && coords.posY == j + offsetY && coords.posZ == k + offsetZ)
						{
							alreadyChecked = true;
							break;
						}
					}
					if(alreadyChecked)
					{
						continue;
					}
					if(checkForValidPortal(world, i + offsetX, j + offsetY, k + offsetZ, offsetX, offsetY, offsetZ, originalX, originalY, originalZ, createPortal, false, new ArrayList<ChunkCoordinates>(frameBlockLocations)))
					{
						return true;
					}
				}
			}
		}
    	return false;
    }
    
    public ArrayList<ChunkCoordinates> setPortalBlocks(World world, int i, int j, int k, int dirX, int dirZ, Block[] blocksToReplace, ArrayList<ChunkCoordinates> portalBlockLocations)
    {
    	System.out.println("setting portal blocks");
    	System.out.println(world.getBlock(i, j, k).getClass().getName());
    	//world.setBlock(i, j, k, this);
    	portalBlockLocations.add(new ChunkCoordinates(i, j, k));
    	
    	int offsetXRange = 1, offsetZRange = 1;
    	if(dirX != 0)
    	{
    		offsetZRange = 0;
    	} else
    	if(dirZ != 0)
    	{
    		offsetXRange = 0;
    	}
    	for(int offsetX = -offsetXRange; offsetX <= offsetXRange; offsetX++)
		{
			for(int offsetY = -1; offsetY <= 1; offsetY++)
			{
				for(int offsetZ = -offsetZRange; offsetZ <= offsetZRange; offsetZ++)
				{
					if(offsetY != 0 && (offsetX != 0 || offsetZ != 0))
					{
						continue;
					}
					
					boolean blockAlreadyChecked = false;
					for(ChunkCoordinates coords : portalBlockLocations)
					{
						if(coords.posX == i + offsetX && coords.posY == j + offsetY && coords.posZ == k + offsetZ)
						{
							blockAlreadyChecked = true;
							break;
						}
					}
					if(blockAlreadyChecked)
					{
						continue;
					}
					
					boolean canReplace = world.isAirBlock(i + offsetX, j + offsetY, k + offsetZ) || world.getBlock(i + offsetX, j + offsetY, k + offsetZ).getMaterial().isLiquid();
					/*for(Block block : blocksToReplace)
					{
						if(world.getBlock(i + offsetX, j + offsetY, k + offsetZ) == block)
						{
							canReplace = true;
						}
					}*/
					if(canReplace)
					{
						setPortalBlocks(world, i + offsetX, j + offsetY, k + offsetZ, dirX, dirZ, blocksToReplace, portalBlockLocations);
					}
				}
			}
		}
    	return portalBlockLocations;
    }
    
	public boolean activatePortal(World world, int i, int j, int k, Block... blocksToReplace)
	{
		return checkForValidPortal(world, i, j - 1, k, 0, 0, 0, i, j - 1, k, true, true, new ArrayList<ChunkCoordinates>());
	}
	
	public int getLightValue(IBlockAccess iblockaccess, int i, int j, int k)
	{
		return AllDemDimensions.coloredLightsLoaded ? coloredLightValue : getLightValue();
	}
	
	public int coloredLightValue;
	public float particleRed;
	public float particleGreen;
	public float particleBlue;
}
