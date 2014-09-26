package alldemdimensions.block; 

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockSoundRecorder extends Block
{
    public BlockSoundRecorder()
    {
        super(Material.rock);
    }
	
	public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
	{
		TileEntitySoundRecorder entity = (TileEntitySoundRecorder)world.getTileEntity(i, j, k);
		if(entity == null)
		{
			System.out.println("Tile entity is null.");
			return false;
		}
		if(world.getBlockMetadata(i, j, k) == 1 && !entity.isRecording)
		{
			System.out.println("Playing");
			entity.isPlaying = true;
		} else
		if(!entity.isRecording)
		{
			System.out.println("Recording");
			entity.isRecording = true;
		} else
		{
			System.out.println("Not recording");
			entity.isRecording = false;
		}
		world.setBlockMetadataWithNotify(i, j, k, 1, 2);//temporary
		return true;
	}
	
	
    @Override
	public TileEntity createTileEntity(World world, int metadata)
    {
        return new TileEntitySoundRecorder();
    }
}
