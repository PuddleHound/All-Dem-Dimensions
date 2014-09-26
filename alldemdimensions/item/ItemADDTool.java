package alldemdimensions.item; 

import java.util.Iterator;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;
import alldemdimensions.block.AstronomyManager;

public class ItemADDTool extends ItemTool
{
	
	public ItemADDTool(int j, EnumToolMaterialADD toolmaterial, Set ablock)
    {
        super(j, Item.ToolMaterial.WOOD, ablock);
		material = toolmaterial;
		blocksEffectiveAgainst = ablock;
        maxStackSize = 1;
        setMaxDamage(material.getMaxUses());
        efficiencyOnProperMaterial = material.getEfficiencyOnProperMaterial();
        //damageVsEntity = j + material.getDamageVsEntity();//1.7.2
        setCreativeTab(CreativeTabs.tabTools);
    }
	
    @Override
	public float getDigSpeed(ItemStack itemstack, Block block, int meta)
    {
		float f = efficiencyOnProperMaterial;
		Iterator iterator = blocksEffectiveAgainst.iterator();
        while(iterator.hasNext())
		{
        	if(iterator.next() == block)
            {
                return AstronomyManager.getModifiedToolEfficiency(f);
            }
        }

        return AstronomyManager.getModifiedToolEfficiency(1.0F);
    }
	
    @Override
	public int getItemEnchantability()
    {
        return this.material.getEnchantability();
    }

    @Override
    public String getToolMaterialName()
    {
        return this.material.toString();
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack itemstack1)
    {
        return this.material.getToolCraftingMaterial() == itemstack1.getItem() ? true : super.getIsRepairable(itemstack, itemstack1);
    }
	
    @Override
	public boolean onItemUse(ItemStack itemstack, EntityPlayer entityplayer, World world, int i, int j, int k, int l, float f, float f1, float f2)
	{
		if(material != EnumToolMaterialADD.SILVER)
		{
			return false;
		}
		InventoryPlayer inventory = entityplayer.inventory;
		if(inventory.currentItem != 8 && inventory.getStackInSlot((inventory.currentItem) + 1) != null)
		{
			ItemStack itemstack1 = inventory.getStackInSlot((inventory.currentItem) + 1);
			if(itemstack1.getItem() instanceof ItemTool || itemstack1.getItem() instanceof ItemSword || itemstack1.getItem() instanceof ItemADDTool || itemstack1.getItem() instanceof ItemADDSword)
			{
				return false;
			}
			itemstack1.tryPlaceItemIntoWorld(entityplayer, world, i, j, k, l, f, f1, f2);
			itemstack.damageItem(1, entityplayer);
			return true;
		}
		return false;
	}
	
	private Set blocksEffectiveAgainst;
	public EnumToolMaterialADD material;
}
