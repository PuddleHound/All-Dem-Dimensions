package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;

public class BlockADDOre extends Block
{

    public BlockADDOre()
    {
        super(Material.rock);
    }
    
    @Override
    public IIcon getIcon(int i, int j)
    {
        if(this == AllDemDimensions.silverOre)
        {
            return TextureLibrary.getBlockTexture("zenith/oreSilver");
        }
        if(this == AllDemDimensions.phosphorusOre)
        {
            return TextureLibrary.getBlockTexture("zenith/orePhosphorus");
        }
        return TextureLibrary.getBlockTexture("zenith/oreEmerald");
    }
    
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
		if(this == AllDemDimensions.emeraldOre)
		{
			return AllDemDimensions.emerald;
		}
		if(this == AllDemDimensions.phosphorusOre)
		{
			return AllDemDimensions.phosphorus;
		}
		return super.getItemDropped(i, random, j);
	}
	
    @Override
	public int quantityDropped(Random random)
    {
		if(this == AllDemDimensions.phosphorusOre)
		{
			return random.nextInt(3) + 1;
		}
		return 1;
    }

}
