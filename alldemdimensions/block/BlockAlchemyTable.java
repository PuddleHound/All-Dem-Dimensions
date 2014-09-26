package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockAlchemyTable extends Block
{
    public BlockAlchemyTable()
    {
        super(Material.rock);
        setBlockBounds(0F, 0F, 0F, 1F, 0.5F, 1F);
    }
	
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        blockIcon = TextureLibrary.getBlockTexture("zenith/alchemyTableSide");
    }
    
    @Override
    public IIcon getIcon(int i, int j)
    {
        if(i == 1)
        {
            return TextureLibrary.getBlockTexture("zenith/alchemyTableTop");
        }
        if(i == 0)
        {
            return TextureLibrary.getBlockTexture("zenith/lightSensorBottom");
        } else
        {
            return blockIcon;
        }
    }

    @Override
    public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2)
    {
    	entityplayer.openGui(AllDemDimensions.getInstance(), AllDemDimensions.GUI_ALCHEMY, world, i, j, k);
        return true;
    }
    
    @Override
    public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntityAlchemyTable();
    }
    
    @Override
    public boolean hasTileEntity(int metadata)
    {
        return true;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
    }
    
    @Override
    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
        world.spawnParticle("spell", i + random.nextFloat(), j + random.nextFloat() + 0.5F, k + random.nextFloat(), 0F, 0.125F, 0F);
    }
}
