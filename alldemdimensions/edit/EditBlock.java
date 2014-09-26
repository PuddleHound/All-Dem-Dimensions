package alldemdimensions.edit;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;

public class EditBlock extends EditBase
{
    
    public EditBlock()
    {
		super("net.minecraft.block.Block", "");
	}

    @Insert(memberName = "onNeighborBlockChange", memberNameObf = "", memberDesc = "(Lnet/minecraft/world/World;IIILnet/minecraft/block/Block;)V", instrOpcodes = {-1, -1}, paramIndices = {1, 2, 3, 4})
    public static void onNeighborBlockChange(World world, int i, int j, int k)
    {
    	checkForPortal(world, i, j, k, false);
    }
    
    @Insert(memberName = "onBlockAdded", memberNameObf = "", memberDesc = "(Lnet/minecraft/world/World;III)V", instrOpcodes = {-1, -1}, paramIndices = {1, 2, 3, 4})
    public static void onBlockAdded(World world, int i, int j, int k)
    {
    	checkForPortal(world, i, j, k, false);
    }
    
    @Insert(memberName = "onBlockActivated", memberNameObf = "", memberDesc = "(Lnet/minecraft/world/World;IIILnet/minecraft/entity/player/EntityPlayer;IFFF)Z", instrOpcodes = {-1, -1}, paramIndices = {1, 2, 3, 4, 5})
    public static void onBlockActivated(World world, int i, int j, int k, EntityPlayer player)
    {
    	if(player.getCurrentEquippedItem() != null && player.getCurrentEquippedItem().getItem() == Items.water_bucket)
    	{
    		checkForPortal(world, i, j, k, true);
    	}
    }
    
    public static void checkForPortal(World world, int i, int j, int k, boolean activatedWithBucket)
    {
        if(world.getBlock(i, j, k) == Blocks.lapis_block && (world.getBlock(i, j + 1, k).getMaterial() == Material.water || activatedWithBucket))
        {
        	AllDemDimensions.waterPortal.activatePortal((World)world, i, j + 1, k);
        }
    }
    
}
