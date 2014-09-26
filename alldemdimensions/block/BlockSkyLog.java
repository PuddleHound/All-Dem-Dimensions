package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.BlockLog;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockSkyLog extends BlockLog
{
    public BlockSkyLog(Tree tree)
    {
        super();
        treeType = tree;
    }
	
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        return super.getItemDropped(i, random, j);
    }
	
    /*@Override
	public ArrayList<ItemStack> getDrops(World world, int i, int j, int k, int metadata, int fortune)
    {
		if(this == AllDemDimensions.skyLog2 && metadata == Tree.ANCIENT_AMBER)
		{
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			drops.add(new ItemStack(AllDemDimensions.skyLog0, 1, Tree.ANCIENT));
			drops.add(new ItemStack(AllDemDimensions.amber, world.rand.nextInt(2) + 1, 0));
			return drops;
		} else
		if(this == AllDemDimensions.skyLog0 && metadata == Tree.ARROW)
		{
			ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
			drops.add(new ItemStack(this, 1, metadata & 3));
			if(world.rand.nextInt(4) == 0)
			{
				drops.add(new ItemStack(Items.flint, world.rand.nextInt(2) + 1, 0));
			}
			return drops;
		}
		return super.getDrops(world, i, j, k, metadata, fortune);
	}*/
	
    /*@Override
	public boolean canHarvestBlock(EntityPlayer player, int meta) 
    {
		if(this == AllDemDimensions.skyLog2 && meta == Tree.ANCIENT_AMBER)
		{
			ItemStack itemstack = player.getCurrentEquippedItem();
			if(itemstack == null)
			{
				return false;
			}
			Item id = itemstack.getItem();
			if(id == Items.iron_axe || id == Items.golden_axe || id == Items.diamond_axe ||
				id == AllDemDimensions.amberAxe || id == AllDemDimensions.silverAxe ||
				id == AllDemDimensions.emeraldAxe || id == Items.stone_axe)
			{
				return true;
			}
			return false;
		}
        return super.canHarvestBlock(player, meta);
    }*/

    @Override
    public IIcon getIcon(int i, int j)
    {
		int metadata = j & 3;
		int direction = j & 12;
		if((direction == 0 && (i == 1 || i == 0)) || (direction == 4 && (i == 5 || i == 4)) || (direction == 8 && (i == 2 || i == 3)))
		{
			return treeType.logTopTexture;
			
		} else
        {
			return treeType.logSideTexture;
        }
	}
    
    @Override
    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        if(iblockaccess.getBlockMetadata(i, j, k) != Tree.BONSAI)
        {
        	//return 0xd4cee5;//purplish gray
        	return 0x9889c4;//purple
        }
        return super.colorMultiplier(iblockaccess, i, j, k);
    }
    
    public Tree treeType;

}
