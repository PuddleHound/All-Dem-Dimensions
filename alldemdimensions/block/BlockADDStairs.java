package alldemdimensions.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class BlockADDStairs extends BlockStairs
{
    public BlockADDStairs(Block block, int j)
    {
        super(block, j);
		useNeighborBrightness = true;
    }
	
}
