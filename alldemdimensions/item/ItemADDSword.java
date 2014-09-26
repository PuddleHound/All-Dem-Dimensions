package alldemdimensions.item;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.world.World;

public class ItemADDSword extends ItemSword
{
    
    public ItemADDSword(EnumToolMaterialADD toolmaterial)
    {
        super(Item.ToolMaterial.WOOD);
        material = toolmaterial;
		maxStackSize = 1;
        setMaxDamage(toolmaterial.getMaxUses());
        setCreativeTab(CreativeTabs.tabCombat);
        weaponDamage = 4 + toolmaterial.getDamageVsEntity();
    }

    @Override
    public float func_150931_i()
    {
        return material.getDamageVsEntity();
    }
	
    @Override
    public float getDigSpeed(ItemStack itemstack, Block block, int meta)
    {
        if (block == Blocks.web)
        {
            return 15.0F;
        }
        else
        {
            Material material = block.getMaterial();
            return material != Material.plants && material != Material.vine && material != Material.coral && material != Material.leaves && material != Material.gourd ? 1.0F : 1.5F;
        }
    }

    /*@Override
    public float getDamageVsEntity(Entity entity)
    {
		if(material == EnumToolMaterialADD.EMERALD && (entity instanceof EntityFlying || entity instanceof EntityZenithFlying))
		{
			return weaponDamage * 2;
		}
        return weaponDamage;
    }*/

    @Override
    public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer)
    {
        entityplayer.setItemInUse(itemstack, this.getMaxItemUseDuration(itemstack));
        return itemstack;
    }

    @Override
    public boolean func_150897_b(Block block)//canHarvestBlock
    {
        return block == Blocks.web;
    }

    @Override
    public int getItemEnchantability()
    {
        return material.getEnchantability();
    }

    public String func_77825_f()
    {
        return material.toString();
    }

    @Override
    public boolean getIsRepairable(ItemStack itemstack, ItemStack itemstack1)
    {
        return material.getToolCraftingMaterial() == itemstack1.getItem() ? true : super.getIsRepairable(itemstack, itemstack1);
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
    
    private int weaponDamage;
    public final EnumToolMaterialADD material;
}
