package alldemdimensions.block; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockADDStorage extends Block
{
    public BlockADDStorage(Material material)
    {
        super(material);
    }
    
    @Override
    public IIcon getIcon(int i, int j)
    {
        if(this == AllDemDimensions.amberBlock)
        {
            return TextureLibrary.getBlockTexture("zenith/amberBlock");
        }
        if(this == AllDemDimensions.silverBlock)
        {
            return TextureLibrary.getBlockTexture("zenith/silverBlock");
        }
        return TextureLibrary.getBlockTexture("zenith/emeraldBlock");
    }
	
    @Override
	public boolean isOpaqueCube()
	{
		return this != AllDemDimensions.amberBlock;
	}
	
    @Override
	public int getRenderBlockPass()
	{
		return this == AllDemDimensions.amberBlock ? 1 : 0;
	}
	
    @Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
		if(this == AllDemDimensions.amberBlock)
		{
			Block i1 = iblockaccess.getBlock(i, j, k);
			if(i1 == AllDemDimensions.amberBlock)
			{
				return false;
			}
		}
		return super.shouldSideBeRendered(iblockaccess, i, j, k, l);
	}
	
}
