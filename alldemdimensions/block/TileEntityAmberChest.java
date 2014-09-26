package alldemdimensions.block;

import net.minecraft.tileentity.TileEntityChest;
import alldemdimensions.AllDemDimensions;

public class TileEntityAmberChest extends TileEntityChest
{

    @Override
    public String getInventoryName()
    {
        return "Amber Chest";
    }
	
    @Override
    public void openInventory()
    {
        this.numPlayersUsing++;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, AllDemDimensions.amberChest, 1, this.numPlayersUsing);
    }

    @Override
    public void closeInventory()
    {
        this.numPlayersUsing--;
        this.worldObj.addBlockEvent(this.xCoord, this.yCoord, this.zCoord, AllDemDimensions.amberChest, 1, this.numPlayersUsing);
    }
}
