package alldemdimensions.block; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;
import alldemdimensions.world.Dimension;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class BlockStellarAltar extends Block implements IBlockRenderer
{
    public BlockStellarAltar()
    {
        super(Material.rock);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
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
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
    }
	
    @Override
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int l, float f, float f1, float f2)
	{
		if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT && world.provider.dimensionId == Dimension.zenith.dimensionId)
		{
			entityplayer.openGui(AllDemDimensions.getInstance(), AllDemDimensions.GUI_STELLAR_ALTAR, world, i, j, k);
		}
		return true;
	}
	
    @Override
	public IIcon getIcon(int side, int metadata)
    {
		if(side == 1)
		{
			return TextureLibrary.getBlockTexture("zenith/stellarAltarTop");
		}
		return TextureLibrary.getBlockTexture("zenith/gneiss");
	}
        
    @Override
    public void render(BlockRenderer br)
    {
        br.setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
        br.cuboid();
        for(float offsetX = 0.0625F; offsetX <= 0.9375F; offsetX += 0.3125F)
        {
            for(float offsetZ = 0.0625F; offsetZ <= 0.9375F; offsetZ += 0.3125F)
            {
                if(!(offsetX == 0.375F && offsetZ == 0.375F))
                {
                    br.setBlockBounds(0.0F + offsetX, 0.75F, 0.0F + offsetZ, 0.25F + offsetX, 1.0F, 0.25F + offsetZ);
                    br.cuboid();
                }
            }
        }
    }
}
