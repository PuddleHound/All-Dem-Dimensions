package alldemdimensions.block;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Direction;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockFloatingWire extends Block implements IBlockRenderer
{

    public BlockFloatingWire()
    {
        super(Material.circuits);
        this.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
    }

    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/flower5");
    }
    
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		return true;
	}

    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
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
    public int getRenderType()
    {
        return AllDemDimensions.blockRenderId;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
		int metadata = iblockaccess.getBlockMetadata(i, j, k);
		return (255 - ((15 - metadata) * 8)) << 16 | 0 << 8 | 0;
    }

    @Override
    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        return true;
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public Item getItem(World world, int i, int j, int k)
    {
        return AllDemDimensions.floatingRedstone;
    }
    
    @Override
    public Item getItemDropped(int metadata, Random random, int fortune)
    {
        return AllDemDimensions.floatingRedstone;
    }
    
    @Override
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        Item item = getItem(world, x, y, z);

        if (item == null)
        {
            return null;
        }

        Block block = item instanceof ItemBlock && !isFlowerPot() ? Block.getBlockFromItem(item) : this;
        return new ItemStack(item, 1, block.getDamageValue(world, x, y, z));
    }

    @Override
    public void render(BlockRenderer br)
    {
        if(br.isItem())
        {
            return;
        }
        RenderBlocks renderblocks = br.getRenderBlocks();
        IBlockAccess iblockaccess = br.getIBlockAccess();
        byte metadata = br.getMetadata();
        int i = br.getX();
        int j = br.getY();
        int k = br.getZ();
        Tessellator tessellator = Tessellator.instance;
        tessellator.setBrightness(this.getMixedBrightnessForBlock(iblockaccess, i, j, k));
        float f = 1.0F;
        float f1 = (float)metadata / 15F;
        float f2 = f1 * 0.6F + 0.4F;

        if (metadata == 0)
        {
            f2 = 0.3F;
        }

        float f3 = f1 * f1 * 0.7F - 0.5F;
        float f4 = f1 * f1 * 0.6F - 0.7F;

        if (f3 < 0.0F)
        {
            f3 = 0.0F;
        }

        if (f4 < 0.0F)
        {
            f4 = 0.0F;
        }

        tessellator.setColorOpaque_F(f2, f3, f4);
        int l1 = (j & 0xf) << 4;
        int l = j & 0xf0;
        double d = (float)l1 / 256F;
        double d2 = ((float)l1 + 15.99F) / 256F;
        double d4 = (float)l / 256F;
        double d6 = ((float)l + 15.99F) / 256F;
        boolean flag = BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i - 1, j, k, 1) || !iblockaccess.getBlock(i - 1, j, k).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i - 1, j - 1, k, -1);
        boolean flag1 = BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i + 1, j, k, 3) || !iblockaccess.getBlock(i + 1, j, k).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i + 1, j - 1, k, -1);
        boolean flag2 = BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j, k - 1, 2) || !iblockaccess.getBlock(i, j, k - 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j - 1, k - 1, -1);
        boolean flag3 = BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j, k + 1, 0) || !iblockaccess.getBlock(i, j, k + 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j - 1, k + 1, -1);

        if (!iblockaccess.getBlock(i, j + 1, k).isBlockNormalCube())
        {
            if (iblockaccess.getBlock(i - 1, j, k).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i - 1, j + 1, k, -1))
            {
                flag = true;
            }
			/*
			if (BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j + 1, k, -1))
            {
                flag = true;
            }*/

            if (iblockaccess.getBlock(i + 1, j, k).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i + 1, j + 1, k, -1))
            {
                flag1 = true;
            }

            if (iblockaccess.getBlock(i, j, k - 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j + 1, k - 1, -1))
            {
                flag2 = true;
            }

            if (iblockaccess.getBlock(i, j, k + 1).isBlockNormalCube() && BlockRedstoneWire.isPowerProviderOrWire(iblockaccess, i, j + 1, k + 1, -1))
            {
                flag3 = true;
            }
        }

        float f5 = i + 0;
        float f6 = i + 1;
        float f7 = k + 0;
        float f8 = k + 1;
        byte byte0 = 0;

        if ((flag || flag1) && !flag2 && !flag3)
        {
            byte0 = 1;
        }

        if ((flag2 || flag3) && !flag1 && !flag)
        {
            byte0 = 2;
        }

        if (byte0 != 0)
        {
            d = (float)(l1 + 16) / 256F;
            d2 = ((float)(l1 + 16) + 15.99F) / 256F;
            d4 = (float)l / 256F;
            d6 = ((float)l + 15.99F) / 256F;
        }

        if (byte0 == 0)
        {
            if (!flag)
            {
                f5 += 0.3125F;
            }

            if (!flag)
            {
                d += 0.01953125D;
            }

            if (!flag1)
            {
                f6 -= 0.3125F;
            }

            if (!flag1)
            {
                d2 -= 0.01953125D;
            }

            if (!flag2)
            {
                f7 += 0.3125F;
            }

            if (!flag2)
            {
                d4 += 0.01953125D;
            }

            if (!flag3)
            {
                f8 -= 0.3125F;
            }

            if (!flag3)
            {
                d6 -= 0.01953125D;
            }
			if(iblockaccess.getBlock(i, j + 1, k) != Blocks.redstone_wire && iblockaccess.getBlock(i, j - 1, k) != Blocks.redstone_wire
					&& iblockaccess.getBlock(i, j + 1, k) != AllDemDimensions.floatingWire && iblockaccess.getBlock(i, j - 1, k) != AllDemDimensions.floatingWire)
			//1.7.2
			{
			renderblocks.setRenderBounds(0.3F, 0.4F, 0.35F, 0.4F, 0.5F, 0.45F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.55F, 0.5F, 0.4F, 0.65F, 0.6F, 0.5F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.45F, 0.4F, 0.3F, 0.55F, 0.5F, 0.4F);
			renderblocks.renderStandardBlock(this, i, j, k);
			//^single redstone wire
			}
        }
        else if (byte0 == 1)
        {
			renderblocks.setRenderBounds(0.05F, 0.55F, 0.55F, 0.15F, 0.65F, 0.65F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.25F, 0.4F, 0.35F, 0.35F, 0.5F, 0.45F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.45F, 0.5F, 0.4F, 0.55F, 0.6F, 0.5F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.65F, 0.4F, 0.3F, 0.75F, 0.5F, 0.4F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.85F, 0.45F, 0.6F, 0.95F, 0.55F, 0.7F);
			renderblocks.renderStandardBlock(this, i, j, k);
			//renderblocks.setRenderBounds(0.0F, 0.25F, 0.25F, 1.0F, 0.75F, 0.75F);
			//straight
        }
        else if (byte0 == 2)
        {
			renderblocks.setRenderBounds(0.55F, 0.55F, 0.05F, 0.65F, 0.65F, 0.15F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.35F, 0.4F, 0.25F, 0.45F, 0.5F, 0.35F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.4F, 0.5F, 0.45F, 0.5F, 0.6F, 0.55F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.3F, 0.4F, 0.65F, 0.4F, 0.5F, 0.75F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.6F, 0.45F, 0.85F, 0.7F, 0.55F, 0.95F);
			renderblocks.renderStandardBlock(this, i, j, k);
			//straight, other direction
		}
		if(iblockaccess.getBlock(i, j + 1, k) == Blocks.redstone_wire || iblockaccess.getBlock(i, j - 1, k) == Blocks.redstone_wire
				|| iblockaccess.getBlock(i, j + 1, k) == AllDemDimensions.floatingWire || iblockaccess.getBlock(i, j - 1, k) == AllDemDimensions.floatingWire)//(iblockaccess.isBlockNormalCube(i - 1, j, k) && iblockaccess.getBlockId(i - 1, j + 1, k) == Block.redstoneWire.blockID)
        //1.7.2
		{
			renderblocks.setRenderBounds(0.55F, 0.05F, 0.55F, 0.65F, 0.15F, 0.65F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.35F, 0.25F, 0.4F, 0.45F, 0.35F, 0.5F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.4F, 0.45F, 0.5F, 0.5F, 0.55F, 0.6F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.3F, 0.65F, 0.4F, 0.4F, 0.75F, 0.5F);
			renderblocks.renderStandardBlock(this, i, j, k);
			renderblocks.setRenderBounds(0.6F, 0.85F, 0.45F, 0.7F, 0.95F, 0.55F);
			renderblocks.renderStandardBlock(this, i, j, k);
		}
    }
    
    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
    	Blocks.redstone_wire.randomDisplayTick(world, x, y, z, random);
    }

    /**======================REDSTONE LOGIC START============================*/
        
    private void updateAndPropagateCurrentStrength(World world, int x, int y, int z)
    {
        this.calculateCurrentChanges(world, x, y, z);
        ArrayList<ChunkPosition> arraylist = new ArrayList<ChunkPosition>(this.blocksNeedingUpdate);
        this.blocksNeedingUpdate.clear();

        for (int index = 0; index < arraylist.size(); ++index)
        {
            ChunkPosition chunkposition = (ChunkPosition)arraylist.get(index);
            world.notifyBlocksOfNeighborChange(chunkposition.chunkPosX, chunkposition.chunkPosY, chunkposition.chunkPosZ, this);
        }
    }

    private void calculateCurrentChanges(World world, int i, int j, int k)
    {
        int metadata = world.getBlockMetadata(i, j, k);
        int strength = 0;
        this.wiresProvidePower = false;
        boolean isPowered = world.isBlockIndirectlyGettingPowered(i, j, k);
        this.wiresProvidePower = true;
        int counter;
        int x;
		int y;
        int z;

        if (isPowered)
        {
            strength = 15;
        }
        else
        {
            for (counter = 0; counter < 6; counter++)
            {
                x = i;
				y = j;
                z = k;

                if (counter == 0)
                {
                    x = i - 1;
                }

                if (counter == 1)
                {
                    ++x;
                }

                if (counter == 2)
                {
                    z = k - 1;
                }

                if (counter == 3)
                {
                    ++z;
                }
				
				if(counter == 4)
				{
					y = j - 1;
				}
				
				if(counter == 5)
				{
					++y;
				}

                if (x != i || y != j || z != k)
                {
                    strength = this.getMaxCurrentStrength(world, x, y, z, strength);
                }
            }

            if (strength > 0)
            {
                --strength;
            }
            else
            {
                strength = 0;
            }
        }

        if (metadata != strength)
        {
            world.setBlockMetadataWithNotify(i, j, k, strength, 2);
            world.markBlockRangeForRenderUpdate(i, j, k, i, j, k);

            for (counter = 0; counter < 6; ++counter)
            {
                x = i;
				y = j;
                z = k;

                if (counter == 0)
                {
                    x = i - 1;
                }

                if (counter == 1)
                {
                    ++x;
                }

                if (counter == 2)
                {
                    z = k - 1;
                }

                if (counter == 3)
                {
                    ++z;
                }
				
				if (counter == 4)
				{
					y = j - 1;
				}
				
				if (counter == 5)
				{
					++y;
				}

                int maxStrength = this.getMaxCurrentStrength(world, x, y, z, -1);
                strength = world.getBlockMetadata(i, j, k);

                if (strength > 0)
                {
                    --strength;
                }

                if (maxStrength >= 0 && maxStrength != strength)
                {
                    this.calculateCurrentChanges(world, x, y, z);
                }

                maxStrength = this.getMaxCurrentStrength(world, x, y, z, -1);
                strength = world.getBlockMetadata(i, j, k);

                if (strength > 0)
                {
                    --strength;
                }

                if (maxStrength >= 0 && maxStrength != strength)
                {
                    this.calculateCurrentChanges(world, x, y, z);
                }
            }

            if (metadata < strength || strength == 0)
            {
                this.blocksNeedingUpdate.add(new ChunkPosition(i, j, k));
                this.blocksNeedingUpdate.add(new ChunkPosition(i - 1, j, k));
                this.blocksNeedingUpdate.add(new ChunkPosition(i + 1, j, k));
                this.blocksNeedingUpdate.add(new ChunkPosition(i, j - 1, k));
                this.blocksNeedingUpdate.add(new ChunkPosition(i, j + 1, k));
                this.blocksNeedingUpdate.add(new ChunkPosition(i, j, k - 1));
                this.blocksNeedingUpdate.add(new ChunkPosition(i, j, k + 1));
            }
        }
    }

    private void notifyWireNeighborsOfNeighborChange(World world, int i, int j, int k)
    {
        if (world.getBlock(i, j, k) == this)
        {
            world.notifyBlocksOfNeighborChange(i, j, k, this);
            world.notifyBlocksOfNeighborChange(i - 1, j, k, this);
            world.notifyBlocksOfNeighborChange(i + 1, j, k, this);
            world.notifyBlocksOfNeighborChange(i, j, k - 1, this);
            world.notifyBlocksOfNeighborChange(i, j, k + 1, this);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, this);
            world.notifyBlocksOfNeighborChange(i, j + 1, k, this);
        }
    }

    @Override
    public void onBlockAdded(World world, int i, int j, int k)
    {
        super.onBlockAdded(world, i, j, k);

        if (!world.isRemote)
        {
            this.updateAndPropagateCurrentStrength(world, i, j, k);
            this.notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
            this.notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
            this.notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
            this.notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
			this.notifyWireNeighborsOfNeighborChange(world, i, j + 1, k);
            this.notifyWireNeighborsOfNeighborChange(world, i, j - 1, k);
        }
    }

    @Override
    public void breakBlock(World world, int i, int j, int k, Block block, int l)
    {
        super.breakBlock(world, i, j, k, block, l);

        if (!world.isRemote)
        {
            world.notifyBlocksOfNeighborChange(i, j + 1, k, this);
            world.notifyBlocksOfNeighborChange(i, j - 1, k, this);
            world.notifyBlocksOfNeighborChange(i + 1, j, k, this);
            world.notifyBlocksOfNeighborChange(i - 1, j, k, this);
            world.notifyBlocksOfNeighborChange(i, j, k + 1, this);
            world.notifyBlocksOfNeighborChange(i, j, k - 1, this);
            this.updateAndPropagateCurrentStrength(world, i, j, k);
            this.notifyWireNeighborsOfNeighborChange(world, i - 1, j, k);
            this.notifyWireNeighborsOfNeighborChange(world, i + 1, j, k);
            this.notifyWireNeighborsOfNeighborChange(world, i, j, k - 1);
            this.notifyWireNeighborsOfNeighborChange(world, i, j, k + 1);
			this.notifyWireNeighborsOfNeighborChange(world, i, j - 1, k);
            this.notifyWireNeighborsOfNeighborChange(world, i, j + 1, k);
        }
    }

    private int getMaxCurrentStrength(World world, int i, int j, int k, int l)
    {
        if (world.getBlock(i, j, k) != this)
        {
            return l;
        }
        else
        {
            int metadata = world.getBlockMetadata(i, j, k);
            return metadata > l ? metadata : l;
        }
    }

    @Override
    public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
    {
        if (!world.isRemote)
        {
            int metadata = world.getBlockMetadata(i, j, k);
            boolean canPlaceBlock = this.canPlaceBlockAt(world, i, j, k);

            if (canPlaceBlock)
            {
                this.updateAndPropagateCurrentStrength(world, i, j, k);
            }
            else
            {
                this.dropBlockAsItem(world, i, j, k, metadata, 0);
                world.setBlockToAir(i, j, k);
            }

            super.onNeighborBlockChange(world, i, j, k, block);
        }
    }

    @Override
    public int isProvidingStrongPower(IBlockAccess world, int i, int j, int k, int l)
    {
        return !this.wiresProvidePower ? 0 : this.isProvidingWeakPower(world, i, j, k, l);
    }

    @Override
    public int isProvidingWeakPower(IBlockAccess world, int i, int j, int k, int l)
    {
        if (!this.wiresProvidePower)
        {
            return 0;
        }
        else if (world.getBlockMetadata(i, j, k) == 0)
        {
            return 0;
        }
        else
        {
            boolean poweredNegX = isPoweredOrRepeater(world, i - 1, j, k, 1);
            boolean poweredPosX = isPoweredOrRepeater(world, i + 1, j, k, 3);
            boolean poweredNegZ = isPoweredOrRepeater(world, i, j, k - 1, 2);
            boolean poweredPosZ = isPoweredOrRepeater(world, i, j, k + 1, 0);
			boolean poweredNegY = isPoweredOrRepeater(world, i, j - 1, k, -1);
            boolean poweredPosY = isPoweredOrRepeater(world, i, j + 1, k, -1);		
            
			if((l == 2 && poweredNegZ) || (l == 3 && poweredPosZ) || (l == 4 && poweredNegX) || (l == 5 && poweredPosX) || (l == 0 && poweredNegY) || (l == 1 && poweredPosY))
			{
				return world.getBlockMetadata(i, j, k);
			}
			return 0;
        }
    }

    @Override
    public boolean canProvidePower()
    {
        return this.wiresProvidePower;
    }

    public static boolean isPowerProviderOrWire(IBlockAccess world, int i, int j, int k, int l)
    {
        Block block = world.getBlock(i, j, k);

        if (block == AllDemDimensions.floatingWire || block == Blocks.redstone_wire)
        {
            return true;
        }
        else if (block.isAir(world, i, j, k))
        {
            return false;
        }
        else if (block != Blocks.unpowered_repeater && block != Blocks.powered_repeater)
        {
            return (block != null && block.canConnectRedstone(world, i, j, k, l));
        }
        else
        {
            int metadata = world.getBlockMetadata(i, j, k);
            return l == (metadata & 3) || l == Direction.rotateOpposite[metadata & 3];
        }
    }

    public static boolean isPoweredOrRepeater(IBlockAccess world, int i, int j, int k, int l)
    {
        if (isPowerProviderOrWire(world, i, j, k, l))
        {
            return true;
        }
        else
        {
            Block block = world.getBlock(i, j, k);

            if (block == Blocks.powered_repeater)
            {
                int metadata = world.getBlockMetadata(i, j, k);
                return l == (metadata & 3);
            }
            else
            {
                return false;
            }
        }
    }
    
    private boolean wiresProvidePower = true;
    private Set<ChunkPosition> blocksNeedingUpdate = new HashSet<ChunkPosition>();
}
