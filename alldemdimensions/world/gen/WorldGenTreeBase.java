package alldemdimensions.world.gen; import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.block.Tree;

public abstract class WorldGenTreeBase extends WorldGenerator
{
	public WorldGenTreeBase()
	{
	}
	
	public Tree treeType;
	public BlockLog logBlock;
	public int logMeta;
	public BlockLeaves leavesBlock;
	public int leavesMeta;
}
