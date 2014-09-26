package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
//import net.minecraftforge.common.ForgeDirection;

public class BlockModified extends Block implements IBlockRenderer
{
    
    public BlockModified(Material material)
    {
        super(material);
        setCreativeTab(CreativeTabs.tabBlock);//TEMPORARY
    }
    /*
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
	
    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityModified();
    }
    
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    public int getRenderType()
    {
        return AllDemDimensions.blockRenderId;
    }
    
    public void render(BlockRenderer br)
    {
        if(br.isItem())
        {
            br.getRenderBlocks().renderBlockAsItem(Block.stone, br.getMetadata(), 1F);
        } else
        {
            if(isModified(br.getIBlockAccess(), br.getX(), br.getY(), br.getZ(), AlchemyEffect.appearanceI))
            {
                br.getRenderBlocks().overrideBlockTexture = Block.brick.getIcon(0, 0);
            }
            br.getRenderBlocks().renderBlockByRenderType(getBaseBlock(br.getIBlockAccess(), br.getX(), br.getY(), br.getZ()), br.getX(), br.getY(), br.getZ());
            br.getRenderBlocks().clearOverrideBlockTexture();
        }
    }
    
    public float getBlockHardness(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).getBlockHardness(world, x, y, z);
    }
    
    public float getBlockBrightness(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return getBaseBlock(iblockaccess, x, y, z).getBlockBrightness(iblockaccess, x, y, z);
    }
    
    public int getMixedBrightnessForBlock(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return getBaseBlock(iblockaccess, x, y, z).getMixedBrightnessForBlock(iblockaccess, x, y, z);
    }

    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
        return getBaseBlock(iblockaccess, x, y, z).shouldSideBeRendered(iblockaccess, x, y, z, side);
    }
    
    public boolean isBlockSolid(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
        return getBaseBlock(iblockaccess, x, y, z).isBlockSolid(iblockaccess, x, y, z, side);
    }
    
    public IIcon getBlockTexture(IBlockAccess iblockaccess, int x, int y, int z, int side)//EFFECT: Appearance
    {
        return getBaseBlock(iblockaccess, x, y, z).getBlockTexture(iblockaccess, x, y, z, side);
    }
    
    public void addCollisionBoxesToList(World world, int x, int y, int z, AxisAlignedBB axisalignedbb, List list, Entity entity)
    {
        getBaseBlock(world, x, y, z).addCollisionBoxesToList(world, x, y, z, axisalignedbb, list, entity);
    }
    
    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).getSelectedBoundingBoxFromPool(world, x, y, z);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).getCollisionBoundingBoxFromPool(world, x, y, z);
    }
    
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    public void updateTick(World world, int x, int y, int z, Random random)
    {
        if(isModified(world, x, y, z, AlchemyEffect.melting) && world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) > 11 - Block.lightOpacity[this.blockID])
        {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlock(x, y, z, Block.waterStill.blockID);
        }
    }
    
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        getBaseBlock(world, x, y, z).randomDisplayTick(world, x, y, z, random);
    }

    public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
    {
        getBaseBlock(world, x, y, z).onBlockDestroyedByPlayer(world, x, y, z, meta);
    }

    public void onNeighborBlockChange(World world, int x, int y, int z, int neighborId)
    {
        getBaseBlock(world, x, y, z).onNeighborBlockChange(world, x, y, z, neighborId);
    }
    
    public void onBlockAdded(World world, int x, int y, int z)
    {
        getBaseBlock(world, x, y, z).onBlockAdded(world, x, y, z);
    }

    public void breakBlock(World world, int x, int y, int z, int id, int meta)
    {
        getBaseBlock(world, x, y, z).breakBlock(world, x, y, z, id, meta);
    }
    
    //getPlayerRelativeBlockHardness
    //dropBlockAsItemWithChance
    //dropXpOnBlockBreak
    
    public MovingObjectPosition collisionRayTrace(World world, int x, int y, int z, Vec3 vec1, Vec3 vec2)
    {
        return getBaseBlock(world, x, y, z).collisionRayTrace(world, x, y, z, vec1, vec2);
    }
    
    //onBlockDestroyedByExplosion
    
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    public boolean canPlaceBlockOnSide(World world, int x, int y, int z, int side, ItemStack itemstack)
    {
        return getBaseBlock(world, x, y, z).canPlaceBlockOnSide(world, x, y, z, side, itemstack);
    }
    
    public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer entityplayer, int side, float hitX, float hitY, float hitZ)//side?
    {
        return getBaseBlock(world, x, y, z).onBlockActivated(world, x, y, z, entityplayer, side, hitX, hitY, hitZ);
    }

    public void onEntityWalking(World world, int x, int y, int z, Entity entity)
    {
        getBaseBlock(world, x, y, z).onEntityWalking(world, x, y, z, entity);
    }

    public int onBlockPlaced(World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int meta)
    {
        return getBaseBlock(world, x, y, z).onBlockPlaced(world, x, y, z, side, hitX, hitY, hitZ, meta);
    }

    public void onBlockClicked(World world, int x, int y, int z, EntityPlayer entityplayer)
    {
        getBaseBlock(world, x, y, z).onBlockClicked(world, x, y, z, entityplayer);
    }

    public void velocityToAddToEntity(World world, int x, int y, int z, Entity entity, Vec3 vec3)
    {
        getBaseBlock(world, x, y, z).velocityToAddToEntity(world, x, y, z, entity, vec3);
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int x, int y, int z)
    {
        getBaseBlock(iblockaccess, x, y, z).setBlockBoundsBasedOnState(iblockaccess, x, y, z);
    }
    
    public int isProvidingWeakPower(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
        return getBaseBlock(iblockaccess, x, y, z).isProvidingWeakPower(iblockaccess, x, y, z, side);
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return getBaseBlock(iblockaccess, x, y, z).colorMultiplier(iblockaccess, x, y, z);
    }
    
    public boolean canProvidePower()
    {
        return true;
    }
    
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
    {
        getBaseBlock(world, x, y, z).onEntityCollidedWithBlock(world, x, y, z, entity);
    }

    public int isProvidingStrongPower(IBlockAccess iblockaccess, int x, int y, int z, int side)
    {
        return getBaseBlock(iblockaccess, x, y, z).isProvidingStrongPower(iblockaccess, x, y, z, side);
    }
    
    public void harvestBlock(World world, EntityPlayer entityplayer, int x, int y, int z, int meta)
    {
        getBaseBlock(world, x, y, z).harvestBlock(world, entityplayer, x, y, z, meta);
    }
    
    public boolean canBlockStay(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).canBlockStay(world, x, y, z);
    }

    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entitylivingbase, ItemStack itemstack)
    {
        getBaseBlock(world, x, y, z).onBlockPlacedBy(world, x, y, z, entitylivingbase, itemstack);
    }

    public void onPostBlockPlaced(World world, int x, int y, int z, int w)
    {
        getBaseBlock(world, x, y, z).onPostBlockPlaced(world, x, y, z, w);
    }
    
    //onBlockEventReceived
    
    public float getAmbientOcclusionLightValue(IBlockAccess iblockaccess, int x, int y, int z)
    {
        return getBaseBlock(iblockaccess, x, y, z).getAmbientOcclusionLightValue(iblockaccess, x, y, z);
    }
    
    //onFallenUpon
    //idPicked
    //getDamageValue
    
    public void getSubBlocks(int i, CreativeTabs creativetabs, List list)
    {
        super.getSubBlocks(i, creativetabs, list);//TEMPORARY
    }
    
    //onBlockHarvested
    //onBlockPreDestroy
    //fillWithRain
    //getComparatorInputOverride
    
    //-------------FORGE START-------------
    public int getLightValue(IBlockAccess world, int x, int y, int z)
    {
        if(isModified(world, x, y, z, AlchemyEffect.lightI))
        {
            return 9;
        } else
        if(isModified(world, x, y, z, AlchemyEffect.lightII))
        {
            return 12;
        } else
        if(isModified(world, x, y, z, AlchemyEffect.lightIII))
        {
            return 15;
        }
        return Block.lightValue[getBaseBlock(world, x, y, z).blockID];//getBaseBlock(world, x, y, z).getLightValue(world, x, y, z);//FIX
    }
    
    public boolean isLadder(World world, int x, int y, int z, EntityLivingBase entity)
    {
        return getBaseBlock(world, x, y, z).isLadder(world, x, y, z, entity);
    }

    public boolean isBlockNormalCube(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isBlockNormalCube(world, x, y, z);
    }

    public boolean isBlockSolidOnSide(World world, int x, int y, int z, ForgeDirection side)
    {
        return getBaseBlock(world, x, y, z).isBlockSolidOnSide(world, x, y, z, side);
    }

    public boolean isBlockReplaceable(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isBlockReplaceable(world, x, y, z);
    }

    public boolean isBlockBurning(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isBlockBurning(world, x, y, z);
    }

    public boolean isAirBlock(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isAirBlock(world, x, y, z);
    }
    
    public boolean removeBlockByPlayer(World world, EntityPlayer player, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).removeBlockByPlayer(world, player, x, y, z);
    }

    public int getFlammability(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return getBaseBlock(world, x, y, z).getFlammability(world, x, y, z, metadata, face);
    }

    public boolean isFlammable(IBlockAccess world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return getBaseBlock(world, x, y, z).isFlammable(world, x, y, z, metadata, face);
    }

    public int getFireSpreadSpeed(World world, int x, int y, int z, int metadata, ForgeDirection face)
    {
        return getBaseBlock(world, x, y, z).getFireSpreadSpeed(world, x, y, z, metadata, face);
    }

    public boolean isFireSource(World world, int x, int y, int z, int metadata, ForgeDirection side)
    {
        return getBaseBlock(world, x, y, z).isFireSource(world, x, y, z, metadata, side);
    }
    
    public ArrayList<ItemStack> getBlockDropped(World world, int x, int y, int z, int metadata, int fortune)
    {
        return getBaseBlock(world, x, y, z).getBlockDropped(world, x, y, z, metadata, fortune);
    }
    
    public boolean canSilkHarvest(World world, EntityPlayer player, int x, int y, int z, int metadata)
    {
        return false;
    }

    public boolean canCreatureSpawn(EnumCreatureType type, World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).canCreatureSpawn(type, world, x, y, z);
    }
    
    //isBed
    //other bed methods
    
    public void beginLeavesDecay(World world, int x, int y, int z)
    {
        getBaseBlock(world, x, y, z).beginLeavesDecay(world, x, y, z);
    }

    public boolean canSustainLeaves(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).canSustainLeaves(world, x, y, z);
    }

    public boolean isLeaves(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isLeaves(world, x, y, z);
    }

    public boolean canBeReplacedByLeaves(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).canBeReplacedByLeaves(world, x, y, z);
    }

    public boolean isWood(World world, int x, int y, int z)
    {
         return getBaseBlock(world, x, y, z).isWood(world, x, y, z);
    }
    
    @Override
    public float getExplosionResistance(Entity entity, World world, int x, int y, int z, double explosionX, double explosionY, double explosionZ)
    {
        if(getModifiers(world, x, y, z).contains(AlchemyEffect.explosionResistanceI))
        {
            return 10F;
        } else
        if(getModifiers(world, x, y, z).contains(AlchemyEffect.explosionResistanceII))
        {
            return 2000F;
        }
        return getExplosionResistance(entitys);
    }
    
    public void onBlockExploded(World world, int x, int y, int z, Explosion explosion)
    {
        getBaseBlock(world, x, y, z).onBlockExploded(world, x, y, z, explosion);
    }

    public boolean canConnectRedstone(IBlockAccess world, int x, int y, int z, int side)
    {
        return getBaseBlock(world, x, y, z).canConnectRedstone(world, x, y, z, side);
    }

    public boolean canPlaceTorchOnTop(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).canPlaceTorchOnTop(world, x, y, z);
    }
    
    public ItemStack getPickBlock(MovingObjectPosition target, World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).getPickBlock(target, world, x, y, z);
    }

    public boolean isBlockFoliage(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isBlockFoliage(world, x, y, z);
    }

    public boolean addBlockDestroyEffects(World world, int x, int y, int z, int meta, EffectRenderer effectRenderer)
    {
        return getBaseBlock(world, x, y, z).addBlockDestroyEffects(world, x, y, z, meta, effectRenderer);
    }

    public boolean canSustainPlant(World world, int x, int y, int z, ForgeDirection direction, IPlantable plant)
    {
        return getBaseBlock(world, x, y, z).canSustainPlant(world, x, y, z, direction, plant);
    }

    public void onPlantGrow(World world, int x, int y, int z, int sourceX, int sourceY, int sourceZ)
    {
        getBaseBlock(world, x, y, z).onPlantGrow(world, x, y, z, sourceX, sourceY, sourceZ);
    }

    public boolean isFertile(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).isFertile(world, x, y, z);
    }

    public int getLightOpacity(World world, int x, int y, int z)
    {
        if(isModified(world, x, y, z, AlchemyEffect.translucency))
        {
            return 0;
        }
        return getBaseBlock(world, x, y, z).getLightOpacity(world, x, y, z);
    }

    public boolean canEntityDestroy(World world, int x, int y, int z, Entity entity)
    {
        return getBaseBlock(world, x, y, z).canEntityDestroy(world, x, y, z, entity);
    }

    public boolean isBeaconBase(World world, int x, int y, int z, int beaconX, int beaconY, int beaconZ)
    {
        return getBaseBlock(world, x, y, z).isBeaconBase(world, x, y, z, beaconX, beaconY, beaconZ);
    }

    public boolean rotateBlock(World world, int x, int y, int z, ForgeDirection axis)
    {
        return getBaseBlock(world, x, y, z).rotateBlock(world, x, y, z, axis);
    }

    public ForgeDirection[] getValidRotations(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).getValidRotations(world, x, y, z);
    }

    public float getEnchantPowerBonus(World world, int x, int y, int z)
    {
        return getBaseBlock(world, x, y, z).getEnchantPowerBonus(world, x, y, z);
    }

    public boolean recolourBlock(World world, int x, int y, int z, ForgeDirection side, int colour)
    {
        return getBaseBlock(world, x, y, z).recolourBlock(world, x, y, z, side, colour);
    }
    
    public void onNeighborTileChange(World world, int x, int y, int z, int tileX, int tileY, int tileZ)
    {
        getBaseBlock(world, x, y, z).onNeighborTileChange(world, x, y, z, tileX, tileY, tileZ);
    }
    
    //------------------------------------
    
    public boolean isModified(IBlockAccess world, int x, int y, int z, AlchemyEffect effect)
    {
        return getModifiers(world, x, y, z).contains(effect);
    }
    
    public ArrayList getModifiers(IBlockAccess world, int x, int y, int z)
    {
        if(world != null)
        {
            TileEntity tileentity = world.getBlockTileEntity(x, y, z);
            if(tileentity != null && tileentity instanceof TileEntityModified)
            {
                return ((TileEntityModified)tileentity).getModifiers();
            }
        }
        return new ArrayList();
    }
    
    public Block getBaseBlock(IBlockAccess world, int x, int y, int z)
    {
        if(world != null)
        {
            TileEntity tileentity = world.getBlockTileEntity(x, y, z);
            if(tileentity != null && tileentity instanceof TileEntityModified)
            {
                return ((TileEntityModified)tileentity).getBaseBlock();
            }
        }
        return Block.stone;
    }
    */

	@Override
	public void render(BlockRenderer blockrenderer)
	{
		
	}
    
    
	
}
