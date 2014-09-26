package alldemdimensions.block; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class BlockEasel extends Block implements IBlockRenderer
{
    public BlockEasel()
    {
        super(Material.wood);
    }
    
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = Blocks.planks.getBlockTextureFromSide(0);
    }
    
    @Override
    public void render(BlockRenderer br)
    {
        //float scale = 1.0F;
        //float translateY = 0.0F;
        //int color = colorMultiplier(br.getIBlockAccess(), br.getX(), br.getY(), br.getZ());
        byte metadata = br.getMetadata();
        br.setUseRenderBlocks(true);
        if(br.isItem())
        {
            br.setScale(0.67F, 0.67F, 0.67F);
            br.setTranslation(0F, -0.25F, 0F);
            //scale = 0.67F;
            //translateY = -0.25F;
        }
        if(metadata == 2 || metadata == 3)
        {
            //feet
            br.setBlockBounds(0.0F, 0.0F, 0.0F, 0.125F, 0.125F, 1.0F);
            br.cuboid();
            br.setBlockBounds(0.875F, 0.0F, 0.0F, 1.0F, 0.125F, 1.0F);
            br.cuboid();
            br.setBlockBounds(0.125F, 0.0F, 0.4375F, 0.875F, 0.125F, 0.5625F);
            br.cuboid();
            //legs
            br.setBlockBounds(0.125F, 0.125F, 0.4375F, 0.25F, 1.5F, 0.5625F);
            br.cuboid();
            br.setBlockBounds(0.75F, 0.125F, 0.4375F, 0.875F, 1.5F, 0.5625F);
            br.cuboid();
            //painting stand
            if(metadata == 2)
            {
                    br.setBlockBounds(0.0F, 0.5F, 0.25F, 1.0F, 0.625F, 0.5700F);
                    br.cuboid();
            } else
            {
                    br.setBlockBounds(0.0F, 0.5F, 0.4300F, 1.0F, 0.625F, 0.75F);
                    br.cuboid();
            }
            br.setBlockBounds(0.4375F, 0.625F, 0.4375F, 0.5625F, 2.0F, 0.5625F);
            br.cuboid();
        } else
        {
            //feet
            br.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.125F, 0.125F);
            br.cuboid();
            br.setBlockBounds(0.0F, 0.0F, 0.875F, 1.0F, 0.125F, 1.0F);
            br.cuboid();
            br.setBlockBounds(0.4375F, 0.0F, 0.125F, 0.5625F, 0.125F, 0.875F);
            br.cuboid();
            //legs
            br.setBlockBounds(0.4375F, 0.125F, 0.125F, 0.5625F, 1.5F, 0.25F);
            br.cuboid();
            br.setBlockBounds(0.4375F, 0.125F, 0.75F, 0.5625F, 1.5F, 0.875F);
            br.cuboid();
            //painting stand
            if(metadata == 4)
            {
                    br.setBlockBounds(0.25F, 0.5F, 0.0F, 0.5700F, 0.625F, 1.0F);
                    br.cuboid();
            } else
            {
                    br.setBlockBounds(0.4300F, 0.5F, 0.0F, 0.75F, 0.625F, 1.0F);
                    br.cuboid();
            }
            br.setBlockBounds(0.4375F, 0.625F, 0.4375F, 0.5625F, 2.0F, 0.5625F);
            br.cuboid();
        }
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return false;
	}
	
    @Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
	{
		return null;
	}

    @Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}
	
    @Override
	public int getRenderType()
	{
		return AllDemDimensions.blockRenderId;
	}
	
    @Override
	public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	
    @Override
	public TileEntity createTileEntity(World world, int metadata)
    {
		return new TileEntityEasel();
	}
	
    @Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2)
    {
		//if(world.isRemote)
		if(((TileEntityEasel)world.getTileEntity(i, j, k)).getStackInSlot(16) == null)
		{
			System.out.println("Did not find a painting item");
		}
		ItemStack itemstack = entityplayer.getCurrentEquippedItem();
		if(itemstack != null && itemstack.getItem() == AllDemDimensions.canvas)
		{
			TileEntityEasel easel = (TileEntityEasel)world.getTileEntity(i, j, k);
			if(easel.getStackInSlot(TileEntityEasel.PAINTING_SLOT) == null)
			{
				if(!itemstack.hasTagCompound())
				{
					itemstack.setTagCompound(new NBTTagCompound());
				}
				if(itemstack.getTagCompound().getIntArray("pixels") == null)
				{
					int[] pixelData = new int[16 * 16];
					java.util.Arrays.fill(pixelData, (255 << 24) | (255 << 16) | (255 << 8) | 255);
					itemstack.getTagCompound().setIntArray("pixels", pixelData);
				}
				easel.setInventorySlotContents(TileEntityEasel.PAINTING_SLOT, itemstack.copy());
				itemstack.stackSize--;
			}
		}
		entityplayer.openGui(AllDemDimensions.getInstance(), AllDemDimensions.GUI_EASEL, world, i, j, k);
		return true;
	}

    @Override
	public void breakBlock(World world, int x, int y, int z, Block block, int metadata)
    {
        TileEntityEasel tileentity = (TileEntityEasel)world.getTileEntity(x, y, z);

        if (tileentity != null)
        {
            for (int slotIndex = 0; slotIndex < tileentity.getSizeInventory(); ++slotIndex)
            {
                ItemStack itemstack = tileentity.getStackInSlot(slotIndex);

                if (itemstack != null)
                {
                    float xOffset = world.rand.nextFloat() * 0.8F + 0.1F;
                    float yOffset = world.rand.nextFloat() * 0.8F + 0.1F;
                    EntityItem entityitem;

                    for (float zOffset = world.rand.nextFloat() * 0.8F + 0.1F; itemstack.stackSize > 0; world.spawnEntityInWorld(entityitem))
                    {
                        int subtractFromStack = world.rand.nextInt(21) + 10;

                        if (subtractFromStack > itemstack.stackSize)
                        {
                            subtractFromStack = itemstack.stackSize;
                        }

                        itemstack.stackSize -= subtractFromStack;
                        entityitem = new EntityItem(world, (double)((float)x + xOffset), (double)((float)y + yOffset), (double)((float)z + zOffset), new ItemStack(itemstack.getItem(), subtractFromStack, itemstack.getItemDamage()));//1.7.2
                        float f = 0.05F;
                        entityitem.motionX = (double)((float)world.rand.nextGaussian() * f);
                        entityitem.motionY = (double)((float)world.rand.nextGaussian() * f + 0.2F);
                        entityitem.motionZ = (double)((float)world.rand.nextGaussian() * f);

                        if (itemstack.hasTagCompound())
                        {
                            entityitem.getDataWatcher().getWatchableObjectItemStack(10).setTagCompound((NBTTagCompound)itemstack.getTagCompound().copy());
                        }
                    }
                }
            }
        }

        super.breakBlock(world, x, y, z, block, metadata);
    }
	
    @Override
    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityliving, ItemStack itemstack)
    {
		int metadata = 0;
        int l = MathHelper.floor_double((double)(entityliving.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        if(l == 0)
		{
			metadata = 2;
		}
		if(l == 1)
		{
			metadata = 5;
		}
		if(l == 2)
		{
			metadata = 3;
		}
		if(l == 3)
		{
			metadata = 4;
		}
		world.setBlockMetadataWithNotify(i, j, k, metadata, 2);
    }
        
}
