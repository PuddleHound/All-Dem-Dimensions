package alldemdimensions.world.gen; import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenEndTemple extends WorldGenerator
{

    public WorldGenEndTemple()
    {
    }

    @Override
    public boolean generate(World world, Random random, int i, int j, int k)
    {
		if(world.getBlock(i, j, k) == Blocks.end_stone && world.isAirBlock(i, j + 1, k))
		{
		addRoom(world, random, i + 11, j, k + 11, 4, 28, 4, Blocks.obsidian);
		addRoom(world, random, i + 11, j, k - 11, 4, 28, 4, Blocks.obsidian);
		addRoom(world, random, i - 11, j, k + 11, 4, 28, 4, Blocks.obsidian);
		addRoom(world, random, i - 11, j, k - 11, 4, 28, 4, Blocks.obsidian);
		addRoom(world, random, i, j, k + 11, 14, 14, 3, Blocks.obsidian);
		addRoom(world, random, i + 11, j, k, 3, 14, 14, Blocks.obsidian);
		addRoom(world, random, i, j, k - 11, 14, 14, 3, Blocks.obsidian);
		addRoom(world, random, i - 11, j, k, 3, 14, 14, Blocks.obsidian);
		
		addBox(world, random, i + 11, j + 28, k + 11, 3, 1, 3, -1, Blocks.obsidian);
		addBox(world, random, i + 11, j + 29, k + 11, 2, 1, 2, -1, Blocks.obsidian);
		addBox(world, random, i + 11, j + 30, k + 11, 1, 1, 1, -1, Blocks.obsidian);
		addBox(world, random, i + 11, j + 28, k - 11, 3, 1, 3, -1, Blocks.obsidian);
		addBox(world, random, i + 11, j + 29, k - 11, 2, 1, 2, -1, Blocks.obsidian);
		addBox(world, random, i + 11, j + 30, k - 11, 1, 1, 1, -1, Blocks.obsidian);
		addBox(world, random, i - 11, j + 28, k + 11, 3, 1, 3, -1, Blocks.obsidian);
		addBox(world, random, i - 11, j + 29, k + 11, 2, 1, 2, -1, Blocks.obsidian);
		addBox(world, random, i - 11, j + 30, k + 11, 1, 1, 1, -1, Blocks.obsidian);
		addBox(world, random, i - 11, j + 28, k - 11, 3, 1, 3, -1, Blocks.obsidian);
		addBox(world, random, i - 11, j + 29, k - 11, 2, 1, 2, -1, Blocks.obsidian);
		addBox(world, random, i - 11, j + 30, k - 11, 1, 1, 1, -1, Blocks.obsidian);
		
		func_150515_a(world, i + 11, j + 30, k + 11, Blocks.netherrack);
		func_150515_a(world, i + 11, j + 31, k + 11, Blocks.fire);
		func_150515_a(world, i + 11, j + 30, k - 11, Blocks.netherrack);
		func_150515_a(world, i + 11, j + 31, k - 11, Blocks.fire);
		func_150515_a(world, i - 11, j + 30, k + 11, Blocks.netherrack);
		func_150515_a(world, i - 11, j + 31, k + 11, Blocks.fire);
		func_150515_a(world, i - 11, j + 30, k - 11, Blocks.netherrack);
		func_150515_a(world, i - 11, j + 31, k - 11, Blocks.fire);
		}
        return true;
    }
	
	private void addBox(World world, Random random, int i, int j, int k, int l, int l1, int l2, int l3, Block blockID)
	{
		for(int i1 = -l; i1 < l; i1++)
		{
			for(int j1 = l3; j1 < l1; j1++)
			{
				for(int k1 = -l2; k1 < l2; k1++)
				{
					func_150515_a(world, i + i1, j + j1, k + k1, blockID);
				}
			}
		}
	}
	
	private void addRoom(World world, Random random, int i, int j, int k, int l, int l1, int l2, Block blockID)
	{
		addBox(world, random, i, j, k, l, l1, l2, -1, blockID);
		addBox(world, random, i, j, k, l - 1, l1 - 1, l2 - 1, 0, Blocks.air);
	}

}
