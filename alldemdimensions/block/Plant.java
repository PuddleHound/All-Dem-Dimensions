package alldemdimensions.block;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.util.TextureLibrary;
import alldemdimensions.world.biome.BiomeGenSkyGilded;
import alldemdimensions.world.gen.WorldGenSkyPlants;

public class Plant
{
	//color in inventory
	public Plant(int id, int meta, String s)
	{
		blockName = "ADDPlant" + id;
		plantMeta = meta;
		plantName = s;
		
		if(s.equals("empty"))
		{
			return;
		}
		plantBlock = nameToBlock.get(blockName);
		if(plantBlock == null)
		{
			plantBlock = (BlockPlantBase)(new BlockPlantBase()).setHardness(0.0F).setStepSound(Block.soundTypeGrass).setBlockTextureName("alldemdimensions:transparent").setBlockName(blockName);
			nameToBlock.put(blockName, plantBlock);
			for(int index = 0; index < plantBlock.plants.length; index++)
			{
				plantBlock.plants[index] = new Plant(id, index, "empty");
			}
		}
		plantBlock.plants[plantMeta] = this;
		creativeInventory = true;
		generator = new WorldGenSkyPlants(plantBlock, plantMeta);
		
		plantList.add(this);
	}
	
	public Plant setDisplayInCreative(boolean flag)
	{
		creativeInventory = flag;
		return this;
	}
	
	public Plant setIsReplaceable(boolean flag)
	{
		isReplaceable = flag;
		return this;
	}
	
	public boolean getIsReplaceable(IBlockAccess iblockaccess, int x, int y, int z)
	{
		return isReplaceable;
	}
	
	public Plant setType(int typeId)
	{
		plantType = typeId;
		return this;
	}
	
	public int getType()
	{
		return plantType;
	}
	
	/*==============================RENDERING==============================*/
	
	//public IIcon getIcon(IBlockAccess iblockaccess, int x, int y, int z, int side)
	public IIcon getIcon(int side, int meta)
	{
		return TextureLibrary.getBlockTexture(plantName);
	}
	
	public static long tickCounter;
	
	public void render(BlockRenderer br)
	{
		if(renderer != null)
		{
			renderer.render(br);
			return;
		}
		
        byte metadata = br.getMetadata();
        Random random = new Random();
        random.setSeed((br.getX() % 256) << 16 | (br.getY() % 256) << 8 | br.getZ() % 256);
        int color = 0x00bd40;
        if(!br.isItem())
        {
            color = plantBlock.colorMultiplier(br.getIBlockAccess(), br.getX(), br.getY(), br.getZ());
        } else
        {
        	color = plantBlock.getRenderColor(br.getMetadata());
        }
        if(this == zenith_flowerGrass)//random offsets, rotations?
        {
            br.setUseContrastLighting(false);
            for(byte b = 0; b < 2; b++)
            {
                if(b == 0)
                {
                    br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerGrassOverlay"));
                } else
                {
                    br.setColor(color);
                    br.setBlockTexture(null);
                }
                br.setBlockBounds(0F, 0F, 0.25F, 1F, 1F, 0.25F);
                br.cuboid();
                br.setBlockBounds(0F, 0F, 0.75F, 1F, 1F, 0.75F);
                br.cuboid();
                br.setBlockBounds(0.25F, 0F, 0F, 0.25F, 1F, 1F);
                br.cuboid();
                br.setBlockBounds(0.75F, 0F, 0F, 0.75F, 1F, 1F);
                br.cuboid();
                /*br.quad(0.0F, 0.0F, 0.25F, 0.0F, 1.0F, 0.25F, 1.0F, 1.0F, 0.25F, 1.0F, 0.0F, 0.25F, 2);//negZ
                br.quad(0.0F, 0.0F, 0.75F, 0.0F, 1.0F, 0.75F, 1.0F, 1.0F, 0.75F, 1.0F, 0.0F, 0.75F, 3);//posZ
                br.quad(0.25F, 0.0F, 0.0F, 0.25F, 0.0F, 1.0F, 0.25F, 1.0F, 1.0F, 0.25F, 1.0F, 0.0F, 4);//negX
                br.quad(0.75F, 0.0F, 0.0F, 0.75F, 0.0F, 1.0F, 0.75F, 1.0F, 1.0F, 0.75F, 1.0F, 0.0F, 5);//posX*/
            }
        } else
        if(this == zenith_cotton)
        {
            float rotateX = random.nextFloat() * 360F;
            br.setItemTranslation(0F, -0.25F, 0F);
            //stem
            //renderblocks.overrideBlockTexture = TextureLibrary.getBlockTexture("zenith/leaf3");
            br.setColor(color);
            br.setOrigin(0.5F, 0.0F, 0.5F);
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 0.75F, 0.5625F);
            br.cuboid();
            //leaves
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leaf4"));
            br.setOrigin(0.5F, 0.5F, 0.5F);
            float yOffset;
            for(byte b = 0; b < random.nextInt(3); b++)
            {
                yOffset = (random.nextFloat() * 0.5F) + 0.125F;
                br.setRotation(random.nextFloat() * 360F, 0F, 0F);
                br.setBlockBounds(0.5F, yOffset, 0.25F, 1.0F, yOffset, 0.75F);
                br.cuboid();
            }
            //cotton balls
            br.setRotation(rotateX, 0F, 0F);
            br.clearColor();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flower5"));
            br.setOrigin(0.5F, 0.75F, 0.5F);
            for(float f = 0.1875F; f <= 0.5625F; f += 0.375F)
            {
                for(float f1 = 0.1875F; f1 <= 0.5625F; f1 += 0.375F)
                {
                    br.setBlockBounds(f, 0.75F, f1, f + 0.25F, 1.0F, f1 + 0.25F);
                    br.cuboid();
                }
            }
            br.setBlockTexture(null);
        } else
        if(this == zenith_hibiscus || this == zenith_redstoneFlower)
        {
            float rotateX = random.nextFloat() * 360F;
            float rotateZ = (random.nextFloat() * 40F) - 20F;
            br.setItemScale(0.67F, 0.67F, 0.67F);
            br.setItemTranslation(0F, -0.25F, 0F);
            //stem
            //renderblocks.overrideBlockTexture = TextureLibrary.getBlockTexture("zenith/leaf3");
            br.setColor(color);
            br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 1.0F, 0.5625F);
            br.cuboid();
            //stamen
            br.clearColor();
            if(this == zenith_redstoneFlower){br.setColor(0xff0000);}
            br.setOrigin(0.5F, 1.0F, 0.5F);
            br.setRotation(rotateX, 0F, rotateZ);
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flower3"));
            br.setBlockBounds(0.4375F, 1.0F, 0.4375F, 0.5625F, 1.75F, 0.5625F);
            br.cuboid();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal3"));
            br.setBlockBounds(0.40625F, 1.75F, 0.40625F, 0.59375F, 1.9375F, 0.59375F);
            br.cuboid();
            if(this == zenith_redstoneFlower){br.clearColor();}
            //petals
            br.setOrigin(0.5F, 1.0F, 0.5F);
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal4"));
            if(this == zenith_redstoneFlower){br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetalRedstone"));}
            br.setRotation(rotateX, 0F, rotateZ - 2F);
            br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
            br.cuboid();
            br.setRotation(rotateX + 90F, 0F, rotateZ + 1F);
            br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
            br.cuboid();
            br.setRotation(rotateX + 180F, 0F, rotateZ + 2F);
            br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
            br.cuboid();
            br.setRotation(rotateX + 270F, 0F, rotateZ - 1F);
            br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
            br.cuboid();
            //leaves
            br.setColor(color);
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leaf1"));
            br.setOrigin(0.5F, 0.25F, 0.5F);
            br.setRotation(rotateX, -10F, 0F);
            br.setBlockBounds(0.5F, 0.25F, 0.0F, 1.5F, 0.25F, 1.0F);
            br.cuboid();
            br.setRotation(rotateX + 90F, -10F, 0F);
            br.setBlockBounds(0.5F, 0.25F, 0.0F, 1.5F, 0.25F, 1.0F);
            br.cuboid();
            br.setRotation(rotateX + 180F, -10F, 0F);
            br.setBlockBounds(0.5F, 0.25F, 0.0F, 1.5F, 0.25F, 1.0F);
            br.cuboid();
            br.setRotation(rotateX + 270F, -10F, 0F);
            br.setBlockBounds(0.5F, 0.25F, 0.0F, 1.5F, 0.25F, 1.0F);
            br.cuboid();
            br.setBlockTexture(null);
        } else
        if(this == zenith_lotus || this == zenith_lotusPad)
        {
            boolean flag = this == zenith_lotus;
            if(flag)
            {
                br.setItemScale(0.67F, 0.67F, 0.67F);
            } else
            {
                br.setItemTranslation(0F, 0.5F, 0F);
            }
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/lilyPad"));
            br.setBlockBounds(0.0F, -0.125F, 0.0F, 1.0F, 0.0625F, 1.0F);
            br.cuboid();
            if(flag)
            {
                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal6"));
                for(float angle = 0F; angle < 360F; angle += 45F)
                {
                    br.setOrigin(0.5F, 0.125F, 0.5F);
                    br.setRotation(angle, -20F, 0F);
                    br.setBlockBounds(0.0F, 0.125F, 0.0F, 1.5F, 0.125F, 1.5F);
                    br.cuboid();
                    br.setRotation(angle + 22.5F, -40F, 0F);
                    br.setBlockBounds(0.0F, 0.125F, 0.0F, 1.0F, 0.125F, 1.0F);
                    br.cuboid();
                }
                br.setRotation(0F, 0F, 0F);
                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal3"));
                br.setBlockBounds(0.375F, 0.0625F, 0.375F, 0.625F, 0.25F, 0.625F);
                br.cuboid();
            }
            br.setBlockTexture(null);
        } else
        if(this == zenith_lotus)//difference between water lily and lotus?
        {
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal6"));
            for(float angle = 0F; angle < 360F; angle += 45F)
            {
                /*br.setOrigin(0.5F, 0.125F, 0.5F);
                br.setRotation(angle, -20F, 0F);
                br.setBlockBounds(0.0F, 0.125F, 0.0F, 1.5F, 0.125F, 1.5F);
                br.cuboid();
                br.setRotation(angle + 22.5F, -40F, 0F);
                br.setBlockBounds(0.0F, 0.125F, 0.0F, 1.0F, 0.125F, 1.0F);
                br.cuboid();*/
                br.setOrigin(0.5F, 0.125F, 0.5F);
                br.setRotation(angle, -20F, 0F);
                br.setBlockBounds(0.0F, 0.125F, 0.0F, 2.0F, 0.125F, 2.0F);
                br.cuboid();
                br.setRotation(angle + 15F, -40F, 0F);
                br.cuboid();
                br.setRotation(angle + 30F, -60F, 0F);
                br.cuboid();
            }
            
            br.setRotation(0F, 0F, 0F);
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal3"));
            br.setBlockBounds(0.375F, 0.0625F, 0.375F, 0.625F, 0.25F, 0.625F);
            br.cuboid();
            br.setBlockTexture(null);
        } else
        if(this == zenith_lapisFlower)
        {
            br.setItemScale(0.5F, 0.5F, 0.5F);
            br.setColor(color);
            float rotateX = random.nextFloat() * 360F;
            br.setOrigin(0.5F, 0.5F, 0.5F);
            //stem
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 1.0F, 0.5625F);
            br.cuboid();
            //leaves
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leaf2"));
            float yOffset;
            for(byte b = 0; b < 4; b++)
            {
                br.setRotation(random.nextFloat() * 360F, 20F, 0F);
                yOffset = (random.nextFloat() * 0.5F) + 0.25F;
                br.setBlockBounds(0.5F, yOffset, 0.0F, 1.5F, yOffset, 1.0F);
                br.cuboid();
            }
            br.setRotation(rotateX, 0F, 0F);
            //stamen
            br.clearColor();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flower3"));
            br.setBlockBounds(0.4375F, 1.0F, 0.4375F, 0.5625F, 1.75F, 0.5625F);
            br.cuboid();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal3"));
            br.setBlockBounds(0.40625F, 1.75F, 0.40625F, 0.59375F, 1.9375F, 0.59375F);
            br.cuboid();
            //petals
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal5"));
            br.setBlockBounds(-0.5F, 1.01F, -0.5F, 1.5F, 1.01F, 1.5F);
            br.cuboid();
            br.setRotation(rotateX + 45F, 0F, 0F);
            br.setBlockBounds(-0.5F, 1F, -0.5F, 1.5F, 1F, 1.5F);
            br.cuboid();
            br.setBlockTexture(null);
        } else
        if(this == zenith_delphinium)
        {
            float rotateX = random.nextFloat() * 360F;
            br.setItemTranslation(0F, -0.25F, 0F);
            //stem
            //renderblocks.overrideBlockTexture = TextureLibrary.getBlockTexture("zenith/leaf3");
            br.setColor(color);
            br.setOrigin(0.5F, 0.0F, 0.5F);
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 1.25F, 0.5625F);
            br.cuboid();
            //leaves
            br.setOrigin(0.5F, 0.125F, 0.5F);
            br.setRotation(rotateX, -20F, 0F);
            br.setBlockBounds(0.5F, 0.125F, 0.375F, 0.875F, 0.125F, 0.675F);
            br.cuboid();
            br.setOrigin(0.5F, 0.25F, 0.5F);
            br.setRotation(rotateX + 135F, -20F, 0F);
            br.setBlockBounds(0.5F, 0.25F, 0.375F, 0.875F, 0.25F, 0.675F);
            br.cuboid();
            //flowers
            br.clearColor();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flower1"));
            br.setOrigin(0.5F, 0.5F, 0.5F);
            float distance;
            float offsetX, offsetY, offsetZ;
            for(float f = -0.5F; f < 0.5F; f += 0.125F)
            {
                for(float f1 = 0.5F; f1 < 1.5F; f1 += 0.125F)
                {
                    for(float f2 = -0.5F; f2 < 0.5F; f2 += 0.125F)
                    {
                        distance = Math.abs(f) + Math.abs(f1 * 0.125F) + Math.abs(f2);
                        if(distance <= 0.375F && distance > 0.125F)
                        {
                            offsetX = random.nextFloat() * 0.05F;
                            offsetY = random.nextFloat() * 0.05F;
                            offsetZ = random.nextFloat() * 0.05F;
                            br.setRotation(rotateX, 0F, 0F);
                            br.setBlockBounds(0.4375F + f + offsetX, 0.0F + f1 + offsetY, 0.4375F + f2 + offsetZ, 0.53125F + f + offsetX, 0.09375F + f1 + offsetY, 0.53125F + f2 + offsetZ);
                            br.cuboid();
                        }
                    }
                }
            }
            br.setBlockTexture(null);
        } else
        if(this == zenith_orchid)
        {
            br.setItemScale(0.67F, 0.67F, 0.67F);
            br.setItemTranslation(0F, -0.25F, 0F);
            float rotateX = random.nextFloat() * 360F;
            br.setColor(color);
            //stem
            //renderblocks.overrideBlockTexture = TextureLibrary.getBlockTexture("zenith/leaf3");
            br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.4375F, 0.0F, 0.4375F, 0.5625F, 1.0F, 0.5625F);
            br.cuboid();
            //leaves
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leaf2"));
            float leafY;
            float leafRotateX;
            for(byte count = 0; count < 3; count++)
            {
                leafY = 0.25F + (random.nextFloat() * 0.5F);
                leafRotateX = random.nextFloat() * 360F;
                br.setRotation(leafRotateX, 0F, 0F);
                br.setBlockBounds(0.5F, leafY, 0.25F, 1.0F, leafY, 0.75F);
                br.cuboid();
            }
            br.clearColor();
            br.setRotation(rotateX, 0F, 0F);
            //petals
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal1"));
            br.setBlockBounds(0.0F, 1.125F, 0.5F, 1.0F, 1.125F, 1.5F);
            br.cuboid();
            br.setOrigin(0.5F, 1.25F, 0.5F);
            br.setRotation(rotateX, 90F, 0F);
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal2"));
            br.setBlockBounds(0.0F, 1.25F, 0.0F, 1.0F, 1.25F, 1.0F);
            br.cuboid();
            br.setBlockTexture(null);
        } else
        if(this == zenith_anthurium)
        {
            br.setItemScale(0.67F, 0.67F, 0.67F);
            br.setItemTranslation(0F, -0.25F, 0F);
            float rotateX = random.nextFloat() * 360F;
            br.setColor(color);
            //stem
            //renderblocks.overrideBlockTexture = TextureLibrary.getBlockTexture("zenith/leaf3");
            br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.4375F, 0F, 0.4375F, 0.5625F, 1.0F, 0.5625F);
            br.cuboid();
            //leaves
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leaf1"));
            br.setBlockBounds(0.5F, 0.15F, 0.0F, 1.5F, 0.15F, 1.0F);
            br.cuboid();
            //petal
            br.clearColor();
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal7"));
            br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
            br.cuboid();
            //stamen
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerPetal3"));
            br.setBlockBounds(0.4F, 1.0F, 0.4F, 0.6F, 1.5F, 0.6F);
            br.cuboid();
            br.setBlockTexture(null);
        } else
        if(this == zenith_hangingMoss)
        {
            int i = br.getX(), j = br.getY(), k = br.getZ();
            random.setSeed((i % 256) << 8 | k % 256);
            IBlockAccess iblockaccess = br.getIBlockAccess();
            if(!br.isItem() && iblockaccess.getBlock(i, j - 1, k) == this.plantBlock && iblockaccess.getBlockMetadata(i, j - 1, k) == this.plantMeta)
            {
                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/hangingMoss"));
            } else
            {
                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/hangingMossBottom"));
            }
            float rotateX = random.nextFloat() * 360F;
            br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(rotateX, 0F, 0F);
            br.setBlockBounds(0.5F, 0.0F, 0.0F, 0.501F, 1.0F, 1.0F);
            br.cuboid();
            br.setBlockTexture(null);
        } else
        if(this == zenith_mushroom)
        {
            //br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/mushroomStalk"));
            float rotationX = random.nextFloat() * 360F;
            /*br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(rotationX, 0F, 0F);
            br.setBlockBounds(0.375F, 0.0F, 0.375F, 0.625F, 0.25F, 0.625F);
            br.cuboid();
            //renderblocks.overrideBlockTexture = TextureLibrary.getBlockTexture("zenith/mushroomCap");
            br.setBlockTexture(null);
            br.setBlockBounds(0.25F, 0.25F, 0.25F, 0.75F, 0.625F, 0.75F);
            br.cuboid();
            br.setBlockBounds(0.3125F, 0.625F, 0.3125F, 0.6875F, 1.0F, 0.6875F);
            br.cuboid();*/
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/mushroomCyan"));
            br.setEnableLighting(false);
            br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(rotationX, 0F, 0F);
            br.setBlockBounds(0.45F, 0.0F, 0.45F, 0.55F, 0.35F, 0.55F);
            br.cuboid();
            br.setBlockBounds(0.35F, 0.35F, 0.35F, 0.65F, 0.5F, 0.65F);
            br.cuboid();
            br.setBlockBounds(0.4F, 0.5F, 0.4F, 0.6F, 0.65F, 0.6F);
            br.cuboid();
            br.setEnableLighting(true);
            br.setBlockTexture(null);
        }
	}
	
	public Plant setRenderer(IBlockRenderer iblockrenderer)
	{
		renderer = iblockrenderer;
		return this;
	}
	
	public void renderParticles(World world, int x, int y, int z, Random random)
	{
		if(particleRenderer != null)
		{
			particleRenderer.render(world, x, y, z, random);
		}
	}
	
	public Plant setParticleRenderer(IBlockParticle iblockparticle)
	{
		particleRenderer = iblockparticle;
		return this;
	}
	
	public int getColorInWorld(IBlockAccess iblockaccess, int x, int y, int z)
	{
		if(blockColor != BIOME_FOLIAGE_COLOR && blockColor != BIOME_GRASS_COLOR)
		{
			return blockColor.colorValue;
		}
		int red = 0;
        int green = 0;
        int blue = 0;
        for (int z1 = -1; z1 <= 1; ++z1)
        {
            for (int x1 = -1; x1 <= 1; ++x1)
            {
                int color;
                if(blockColor == BIOME_FOLIAGE_COLOR)
                {
                	color = iblockaccess.getBiomeGenForCoords(x + x1, z + z1).getBiomeFoliageColor(x + x1, y, z + z1);
                } else
                {
                	color = iblockaccess.getBiomeGenForCoords(x + x1, z + z1).getBiomeGrassColor(x + x1, y, z + z1);
                }
                red += (color & 16711680) >> 16;
                green += (color & 65280) >> 8;
                blue += color & 255;
            }
        }
        return (red / 9 & 255) << 16 | (green / 9 & 255) << 8 | blue / 9 & 255;
	}
	
	public int getColorInInventory(int metadata)
	{
		if(blockColor == BIOME_FOLIAGE_COLOR || blockColor == BIOME_GRASS_COLOR)
		{
			return ColorizerFoliage.getFoliageColorBasic();
		}
		return blockColor.colorValue;
	}
	
	public Plant setColor(BlockColor blockcolor)
	{
		blockColor = blockcolor;
		return this;
	}
	
	public static class BlockColor
	{
		public BlockColor(int i)
		{
			colorValue = i;
		}
		
		public int colorValue;
	}
	
	public static final BlockColor NO_COLOR = new BlockColor(16777215);
	public static final BlockColor BIOME_GRASS_COLOR = new BlockColor(-1);
	public static final BlockColor BIOME_FOLIAGE_COLOR = new BlockColor(-1);
	
	public int getLightLevel(IBlockAccess iblockaccess, int i, int j, int k)
	{
		return lightLevel;
	}
	
	public Plant setLightLevel(int i)
	{
		lightLevel = i;
		return this;
	}
	
	
	/*==============================DROPS==============================*/
	
	public ArrayList<ItemStack> getDrops(World world, int x, int y, int z, int meta, int fortune)
	{
		ArrayList<ItemStack> drops = new ArrayList<ItemStack>();
		System.out.println("dropping:");
		if(blockDrops == null || blockDrops.isEmpty())
		{
			System.out.println("no drops");
			return drops;
		}
		for(BlockDrop drop : blockDrops)
		{
			System.out.println("drop: " + drop.itemName);
			if(drop == DROP_SAME_BLOCK)
			{
				drops.add(new ItemStack(Item.getItemFromBlock(plantBlock), 1, plantMeta));
				continue;
			}
			Item item = AllDemDimensions.getItemFromName(drop.itemName);
			if(item == null)
			{
				continue;
			}
			int numberOfDrops = drop.baseDropAmount;
			if(drop.probability < 0 && world.rand.nextInt(-drop.probability) != 0)
			{
				continue;
			} else
			if(drop.probability > 0)
			{
				numberOfDrops += world.rand.nextInt(drop.probability);
			}
			drops.add(new ItemStack(item, numberOfDrops, drop.itemMeta));
		}
		return drops;
	}
	
	public Plant addDrop(String itemName, int itemMeta, int probability, int baseDropAmount)
	{
		blockDrops.add(new BlockDrop(itemName, itemMeta, probability, baseDropAmount));
		return this;
	}
	
	public static final BlockDrop DROP_SAME_BLOCK = new BlockDrop(null, 0, 0, 0);
	
	public static class BlockDrop
	{
		public BlockDrop(String s, int i, int j, int k)
		{
			itemName = s;
			itemMeta = i;
			probability = j;
			baseDropAmount = k;
		}
		
		public String itemName;
		int itemMeta, probability, baseDropAmount;
	}
		
	/*==============================BOUNDING BOXES==============================*/
	
	public void getBoundingBox(IBlockAccess iblockaccess, int x, int y, int z)
	{
		if(boundingBox == null)
		{
			boundingBox = STANDARD_BOUNDING_BOX;
		}
		plantBlock.setBlockBounds(boundingBox.minX, boundingBox.minY, boundingBox.minZ, boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ);
	}
	
	public Plant setBoundingBox(float minX, float minY, float minZ, float maxX, float maxY, float maxZ)
	{
		boundingBox = new BlockBoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
		return this;
	}
	
	public static class BlockBoundingBox
	{
		public BlockBoundingBox(float f, float f1, float f2, float f3, float f4, float f5)
		{
			minX = f;
			minY = f1;
			minZ = f2;
			maxX = f3;
			maxY = f4;
			maxZ = f5;
		}
		
		public float minX, minY, minZ, maxX, maxY, maxZ;
	}
	
	public static final BlockBoundingBox STANDARD_BOUNDING_BOX = new BlockBoundingBox(0F, 0F, 0F, 1F, 1F, 1F);
	
	
	/*==============================CANBLOCKSTAY()==============================*/
	
	public boolean getCanStay(World world, int x, int y, int z)
	{
		//System.out.println("getCanStay");
		if(stayConditions != null && !stayConditions.isEmpty())
		{
			for(HashMap<StayConditionLocation, Object> map : stayConditions)
			{
				boolean meetsCondition = false;
				for(StayConditionLocation location : map.keySet())
				{
					if(location.type == Material.class)
					{
						meetsCondition = world.getBlock(x + location.xOffset, y + location.yOffset, z + location.zOffset).getMaterial() == (Material)map.get(location);
					} else
					if(location.type == Block.class)
					{
						meetsCondition = world.getBlock(x + location.xOffset, y + location.yOffset, z + location.zOffset) == (Block)map.get(location);
					} else
					if(location.type == Integer.class || location.type == Byte.class)
					{
						meetsCondition = world.getBlockMetadata(x + location.xOffset, y + location.yOffset, z + location.zOffset) == (Integer)map.get(location);
					}
					if(!meetsCondition)
					{
						break;
					}
				}
				if(meetsCondition)
				{
					return true;
				}
			}
			return false;
		} else
		{
			return world.getBlock(x, y - 1, z).getMaterial().isSolid();
		}
	}
	
	public Plant addStayCondition(Object... conditions)
	{
		if(stayConditions == null)
		{
			stayConditions = new ArrayList<HashMap<StayConditionLocation, Object>>();
		}
		StayConditionLocation location;
		Object object;
		HashMap<StayConditionLocation, Object> map = new HashMap<StayConditionLocation, Object>();
		for(int i = 0; i < conditions.length; i += 2)
		{
			if(conditions[i] instanceof StayConditionLocation && i + 1 < conditions.length)
			{
				location = (StayConditionLocation)conditions[i];
			} else
			{
				break;
			}
			object = conditions[i + 1];
			if(location.matchesType(object))
			{
				map.put(location, object);
			}
		}
		stayConditions.add(map);
		return this;
	}
	
	public static final StayConditionLocation MATERIAL_ABOVE = new StayConditionLocation(Material.class, 0, 1, 0);
	public static final StayConditionLocation MATERIAL_BELOW = new StayConditionLocation(Material.class, 0, -1, 0);
	public static final StayConditionLocation BLOCK_ABOVE = new StayConditionLocation(Block.class, 0, 1, 0);
	public static final StayConditionLocation BLOCK_BELOW = new StayConditionLocation(Block.class, 0, -1, 0);
	public static final StayConditionLocation META_ABOVE = new StayConditionLocation(Integer.class, 0, 1, 0);
	public static final StayConditionLocation META_BELOW = new StayConditionLocation(Integer.class, 0, -1, 0);
	
	public static class StayConditionLocation
	{
		public StayConditionLocation(Class c, int i, int j, int k)
		{
			type = c;
			xOffset = i;
			yOffset = j;
			zOffset = k;
		}
		
		public boolean matchesType(Object object)
		{
			try
			{
				type.cast(object);
				return true;
			}
			catch(ClassCastException e)
			{
				e.printStackTrace();
			}
			return false;
		}
				
		public Class type;
		public int xOffset, yOffset, zOffset;
	}
	
	public static boolean isFlower(World world, int x, int y, int z)
	{
		Block block = world.getBlock(x, y, z);
		return block instanceof BlockPlantBase && ((BlockPlantBase)block).plants[world.getBlockMetadata(x, y, z)].getType() == TYPE_FLOWER;
	}
	
	public static final HashMap<String, BlockPlantBase> nameToBlock = new HashMap<String, BlockPlantBase>();
	public static final ArrayList<Plant> plantList = new ArrayList<Plant>();
	public String blockName;
	public BlockPlantBase plantBlock;
	public int plantMeta;
	public String plantName;
	private ArrayList<HashMap<StayConditionLocation, Object>> stayConditions;
	private ArrayList<BlockDrop> blockDrops = new ArrayList<BlockDrop>();//when null??
	private IBlockRenderer renderer;
	private IBlockParticle particleRenderer;
	private BlockColor blockColor = NO_COLOR;
	private BlockBoundingBox boundingBox = STANDARD_BOUNDING_BOX;
	private int lightLevel;
	public boolean creativeInventory;
	private boolean isReplaceable;
	private int plantType;
	public WorldGenerator generator;
	
	public static final int TYPE_FLOWER = 1;
	
	public static final Plant zenith_flowerCover = new Plant(0, 0, "zenith/flowerCover").setRenderer(new RenderFlowerCover()).setBoundingBox(0F, 0F, 0F, 1F, 0.125F, 1F).setIsReplaceable(true);
	public static class RenderFlowerCover implements IBlockRenderer
	{
		public void render(BlockRenderer br)
		{
			//if(!br.isItem())
			{
				//br.setUseRenderBlocks(true);
				float height = br.isItem() ? 0.5F : 0.125F;
				if(br.isItem())
				{
					br.setUseRenderBlocks(true);
				}
				boolean gildedBiome = !br.isItem() && br.getIBlockAccess().getBiomeGenForCoords(br.getX(), br.getZ()) instanceof BiomeGenSkyGilded;
				if(gildedBiome)
				{
					br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/flowerCoverGilded"));
				}
	            br.setBlockBounds(0F, height, 0F, 1F, height, 1F);
	            br.cuboid();
	            if(gildedBiome)
	            {
	            	br.setBlockTexture(null);
	            }
			}
		}
	}
	public static final Plant zenith_bamboo = new Plant(0, 1, "zenith/bamboo").setRenderer(new RenderBamboo()).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge).addStayCondition(MATERIAL_BELOW, Material.plants).addDrop("coal", 1, 3, 2).addDrop("pumpkin", 0, 0, 1);//temp
	public static class RenderBamboo implements IBlockRenderer
	{
		@Override
	    public void render(BlockRenderer br)
	    {
	        /*br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/bamboo"));
	        br.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
	        //br.setUseRenderBlocks(true);
	        br.cuboid();
	        br.setBlockTexture(null);
	        if(0 == 0){return;}*/
	        byte metadata = br.getMetadata();
	        /*if(metadata == AllDemDimensions.skyPlant1.FLOWER_COVER)
	        {
	            br.setUseRenderBlocks(true);
	            br.setBlockBounds(0F, 0F, 0F, 1F, 0.125F, 1F);
	            br.cuboid();
	        } else
	        if(metadata == AllDemDimensions.skyPlant1.BAMBOO)*/
	        if(!br.isItem())
	        {
	            //br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/bamboo"));
	            Random random = new Random();
	            random.setSeed((br.getX() % 256) << 16 | (br.getY() % 256) << 8 | (br.getZ() % 256));
	            float x;
	            float y;
	            float z;
	            boolean airAbove = br.getIBlockAccess().isAirBlock(br.getX(), br.getY() + 1, br.getZ());
	            boolean airAbove2 = !airAbove && br.getIBlockAccess().isAirBlock(br.getX(), br.getY() + 2, br.getZ());
	            boolean airAbove3 = !airAbove && br.getIBlockAccess().isAirBlock(br.getX(), br.getY() + 3, br.getZ());
	            boolean airAbove4 = !airAbove && br.getIBlockAccess().isAirBlock(br.getX(), br.getY() + 4, br.getZ());
	            float height = airAbove ? 0.9F : 1.0F;
	            for(int count = 0; count < 1; count++)
	            {
	                br.setUseRenderBlocks(true);
	                    x = 0.375F;//random.nextFloat() * 0.75F;
	                    z = 0.375F;//random.nextFloat() * 0.75F;
	                    br.setBlockBounds(x, 0.0F, z, x + 0.25F, height, z + 0.25F);
	                    br.cuboid();
	                    br.setUseRenderBlocks(false);
	            }
	            
	            height = airAbove ? 1.0F : 1.0F - (random.nextFloat() * 0.33F);
	            if(airAbove || airAbove2 || airAbove3 || airAbove4)
	            {
	                br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/leavesBamboo"));
	                br.setColor(br.getIBlockAccess().getBiomeGenForCoords(br.getX(), br.getZ()).getBiomeFoliageColor(br.getX(), br.getY(), br.getZ()));//(0x34ad00);
	                br.setOrigin(0.5F, height, 0.5F);
	                br.setRotation(0F, 0F, -40F);
	                br.setBlockBounds(0.5F, height, 0.0F, 1.5F, height, 1.0F);
	                br.cuboid();
	                br.setRotation(0F, 0F, 40F);
	                br.setBlockBounds(0.5F, height, 0.0F, -0.5F, height, 1.0F);
	                br.cuboid();
	                br.setRotation(0F, 40F, 0F);
	                br.setBlockBounds(0.0F, height, 0.5F, 1.0F, height, 1.5F);
	                br.cuboid();
	                br.setRotation(0F, -40F, 0F);
	                br.setBlockBounds(0.0F, height, 0.5F, 1.0F, height, -0.5F);
	                br.cuboid();
	                /*br.setRotation(90F, -20F, -40F);
	                br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
	                br.cuboid();
	                br.setRotation(180F, 0F, 20F);
	                br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
	                br.cuboid();
	                br.setRotation(270F, 20F, 40F);
	                br.setBlockBounds(0.5F, 1.0F, 0.0F, 1.5F, 1.0F, 1.0F);
	                br.cuboid();*/
	                br.setBlockTexture(null);
	            }
	        } else
	        {
	        	br.setUseRenderBlocks(true);
	        	float x = 0.375F;//random.nextFloat() * 0.75F;
                float z = 0.375F;//random.nextFloat() * 0.75F;
                br.setBlockBounds(x, 0.0F, z, x + 0.25F, 1.0F, z + 0.25F);
                br.cuboid();
	        }
	    }
	}
		
	public static final Plant zenith_flowerGrass = new Plant(1, 0, "zenith/flowerGrass").setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);//temp
	public static final Plant zenith_cotton = new Plant(0, 2, "zenith/cotton").setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static final Plant zenith_hibiscus = new Plant(0, 3, "zenith/hibiscus").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static  Plant zenith_waterLily;
	public static final Plant zenith_lapisFlower = new Plant(0, 5, "zenith/lapisFlower").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static final Plant zenith_delphinium = new Plant(0, 6, "zenith/delphinium").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static final Plant zenith_orchid = new Plant(0, 7, "zenith/orchid").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static final Plant zenith_redstoneFlower = new Plant(0, 8, "zenith/redstoneFlower").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static final Plant zenith_anthurium = new Plant(0, 9, "zenith/anthurium").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.ground).addStayCondition(MATERIAL_BELOW, Material.grass).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static final Plant zenith_lotus = new Plant(0, 10, "zenith/lotus").setType(TYPE_FLOWER).setColor(BIOME_FOLIAGE_COLOR).addStayCondition(MATERIAL_BELOW, Material.sponge);
	public static  Plant zenith_lotusPad;
	public static  Plant zenith_hangingMoss;
	public static  Plant zenith_mushroom;
	public static  Plant zenith_chestnut;
	public static  Plant zenith_plum;
	public static  Plant zenith_cherry;
	public static  Plant zenith_amber;
	
	//boundingBox
	//drops
	//canStay
	//colorMultiplier
	//lightValue
	//randomDisplayTick
	//render
	
	//.setCanStay("idAbove == 0 && metaAbove == 0")
	
	//public static final Plant bamboo = new Plant().setBoundingBox(0, 0, 0, 1, 1, 1).addDrop("dye", 3, 2, 1);
	//.addStayCondition(MATERIAL_BELOW, Material.water).addStayingCondition(MATERIAL_BELOW, AllDemDimensions.honeyMaterial);//water lily
	//.addStayCondition(BLOCK_BELOW, AllDemDimensions.cloud, META_BELOW, AllDemDimensions.cloud.LOTUS_PAD)
}
