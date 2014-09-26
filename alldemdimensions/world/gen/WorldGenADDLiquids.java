package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenADDLiquids extends WorldGenerator
{

    public WorldGenADDLiquids(Block block, Block... blocks)
    {
        liquidBlockId = block;
		replacedBlocks = blocks;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int i = 0;
        for(Block replacedBlock : replacedBlocks)
        {
            if (world.getBlock(x, y + 1, z) != replacedBlock)
            {
                continue;
            }

            if (world.getBlock(x, y - 1, z) != replacedBlock)
            {
                continue;
            }

            if (world.getBlock(x, y, z) != Blocks.air && world.getBlock(x, y, z) != replacedBlock)
            {
                continue;
            }

            if (world.getBlock(x - 1, y, z) == replacedBlock)
            {
                i++;
            }

            if (world.getBlock(x + 1, y, z) == replacedBlock)
            {
                i++;
            }

            if (world.getBlock(x, y, z - 1) == replacedBlock)
            {
                i++;
            }

            if (world.getBlock(x, y, z + 1) == replacedBlock)
            {
                i++;
            }
            
            if(i == 3)
            {
                break;
            }
        }

        int j = 0;

        if (world.isAirBlock(x - 1, y, z))
        {
            j++;
        }

        if (world.isAirBlock(x + 1, y, z))
        {
            j++;
        }

        if (world.isAirBlock(x, y, z - 1))
        {
            j++;
        }

        if (world.isAirBlock(x, y, z + 1))
        {
            j++;
        }

        if (i == 3 && j == 1)
        {
        	func_150515_a(world, x, y, z, liquidBlockId);
            world.scheduledUpdatesAreImmediate = true;
            liquidBlockId.updateTick(world, x, y, z, random);
            world.scheduledUpdatesAreImmediate = false;
        }

        return true;
    }
    
    private Block liquidBlockId;
   	private Block[] replacedBlocks;
}
