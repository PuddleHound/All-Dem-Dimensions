package alldemdimensions.item;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class ItemCrossbow extends Item implements IItemRenderer
{
    
    public ItemCrossbow()
    {
        super();
    }

    @Override
    public boolean handleRenderType(ItemStack item, ItemRenderType type)
    {
        return true;
    }

    @Override
    public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) 
    {
        return true;
    }

    @Override
    public void renderItem(ItemRenderType type, ItemStack item, Object... data)
    {
        if((type == ItemRenderType.EQUIPPED || type == ItemRenderType.EQUIPPED_FIRST_PERSON) && data[1] instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)data[1];
            ItemRenderer itemrenderer = Minecraft.getMinecraft().entityRenderer.itemRenderer;
            //itemrenderer = RenderManager.instance.itemRenderer;
            ItemStack itemstack = null;
            if(player.inventory.currentItem < 8)
            {
                itemstack = (player.inventory.getStackInSlot((player.inventory.currentItem) + 1));
            }
            
            //crossbow
            GL11.glPopMatrix();
            //itemrenderer.renderItem(player, new ItemStack(Item.bow), 0, type);
            GL11.glPushMatrix();
            IIcon icon = getIcon(item, 0);
            if(icon == null)
            {
                GL11.glPopMatrix();
                return;
            }
            TextureManager texturemanager = Minecraft.getMinecraft().getTextureManager();
            texturemanager.bindTexture(texturemanager.getResourceLocation(item.getItemSpriteNumber()));
            Tessellator tessellator = Tessellator.instance;
            float f = icon.getMinU();
            float f1 = icon.getMaxU();
            float f2 = icon.getMinV();
            float f3 = icon.getMaxV();
            float f4 = 0.0F;
            float f5 = 0.3F;
            GL11.glEnable(GL12.GL_RESCALE_NORMAL);
            GL11.glTranslatef(-f4, -f5, 0.0F);
            float f6 = 1.5F;
            GL11.glScalef(f6, f6, f6);
            GL11.glRotatef(50.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(335.0F, 0.0F, 0.0F, 1.0F);
            GL11.glTranslatef(-0.9375F, -0.0625F, 0.0F);
            //////////
            MovingObjectPosition object = Minecraft.getMinecraft().objectMouseOver;
            int swingCooldown = player.getEntityData().getInteger("ADDSwingCooldown");
            //System.out.println(swingCooldown);
            if(object != null && swingCooldown > 0)//player.swingProgress == 0)
            {
            	//GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
            	int blockX = object.blockX;
            	int blockY = object.blockY;
            	int blockZ = object.blockZ;
                float travelMultiplier = 1F;
            	if(swingCooldown == 11)
                {
                	player.getEntityData().setInteger("ADDLastBlockX", object.blockX);
                	player.getEntityData().setInteger("ADDLastBlockY", object.blockY);
                	player.getEntityData().setInteger("ADDLastBlockZ", object.blockZ);
                }
            	if(swingCooldown <= 10)
                {
            		travelMultiplier = swingCooldown * 0.1F;
                	blockX = player.getEntityData().getInteger("ADDLastBlockX");
                	blockY = player.getEntityData().getInteger("ADDLastBlockY");
                	blockZ = player.getEntityData().getInteger("ADDLastBlockZ");
                	swingCooldown = swingCooldown % 3;
                	if(swingCooldown % 6 == 0)
                	{
                		swingCooldown = -swingCooldown;
                	}
                	GL11.glRotatef(swingCooldown * 5F, 0F, 1F, 0F);
                }
            	float distance = (float)Math.sqrt(Math.pow(player.posX - blockX - 0.5D, 2) + Math.pow(player.posY - blockY - 0.5D, 2) + Math.pow(player.posZ - blockZ - 0.5D, 2));
            	//GL11.glTranslatef(distance - 1.5F, distance / 2F, 0F);
            	GL11.glTranslatef((distance - 1.5F) * travelMultiplier, (distance / 2F) * travelMultiplier, 0F);
            }

            itemrenderer.renderItemIn2D(tessellator, f1, f2, f, f3, icon.getIconWidth(), icon.getIconHeight(), 0.0625F);
            GL11.glDisable(GL12.GL_RESCALE_NORMAL);
            if(itemstack != null)
            {
                //loaded item
                GL11.glPushMatrix();
                GL11.glTranslatef(1F, 1F, 0F);
                GL11.glScalef(0.4F, 0.4F, 0.4F);
                itemrenderer.renderItem((EntityLivingBase)data[1], itemstack, 0, type);
                //GL11.glTranslatef(0, -0.5F, 0);
                GL11.glPopMatrix();
            }
        }
    }
        
}
