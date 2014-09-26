package alldemdimensions.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.entity.EntityCrystalFX;
import alldemdimensions.util.TextureLibrary;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockNetherCrystal extends Block implements IBlockRenderer
{
    public BlockNetherCrystal()
    {
        super(Material.circuits);
        setBlockBounds(0.25F, 0F, 0.25F, 0.75F, 0.5F, 0.75F);
    }
    
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public int getRenderType()
    {
        return AllDemDimensions.blockRenderId;
    }
    
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
    
    @Override
    public int getRenderBlockPass()
    {
        return 1;
    }
    
    @Override
    public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return true;
    }
    
    @Override
    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return null;
    }
    
    @Override
    public IIcon getIcon(int i, int j)
    {
        if(j == REDSTONE)
        {
            return TextureLibrary.getBlockTexture("nether/crystalRedstone");
        }
        if(j == SULFUR)
        {
            return TextureLibrary.getBlockTexture("nether/crystalSulfur");
        }
        if(j == QUICKSILVER)
        {
            return TextureLibrary.getBlockTexture("nether/crystalQuicksilver");
        }
        if(j == WATER)
        {
            return TextureLibrary.getBlockTexture("nether/crystalWater");
        }
        if(j == SHADOW)
        {
            return TextureLibrary.getBlockTexture("nether/crystalShadow");
        }
        if(j == AIR)
        {
            return TextureLibrary.getBlockTexture("nether/crystalAir");
        }
        return TextureLibrary.getBlockTexture("nether/crystalLapis");
    }
    
    @Override
    public Item getItemDropped(int i, Random random, int j)
    {
        if(i == REDSTONE)
        {
            return Items.redstone;
        } else
        if(i == SULFUR)
        {
            //return AllDemDimensions.sulfur.itemID;
        }
        return null;
    }
    
    @Override
    public int quantityDropped(int meta, int fortune, Random random)
    {
        if(meta == REDSTONE || meta == SULFUR)
        {
            return 1;
        }
        return 0;
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
        if(world.getBlockMetadata(x, y, z) != AIR)
        {
            return;
        }
        for (int i = 0; i < 4; i++)
        {
            double d = (float)x + random.nextFloat();
            double d1 = (float)y + random.nextFloat();
            double d2 = (float)z + random.nextFloat();
            float d3 = 0.0F;
            float d4 = 0.0F;
            float d5 = 0.0F;
            int j = random.nextInt(2) * 2 - 1;
            d3 = (random.nextFloat() - 0.5F) * 0.5F;
            d4 = (random.nextFloat() - 0.5F) * 0.5F;
            d5 = (random.nextFloat() - 0.5F) * 0.5F;
            d = (double)x + 0.5D + 0.25D * (double)j;
            d3 = random.nextFloat() * 2.0F * (float)j;
            AllDemDimensionsClient.spawnParticle(new EntityCrystalFX(world, d, d1, d2, d3, d4, d5));
        }
    }
    
    public boolean canGenerateAtHeight(int metadata, int height)
    {
        if(metadata == REDSTONE && height < 80)
        {
            return true;
        }
        if(metadata >= SULFUR && metadata <= LAPIS)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public void render(BlockRenderer br)
    {
        Random random = new Random();
        byte metadata = br.getMetadata();
        if(br.isItem())
        {
            random.setSeed(metadata * 4);
        } else
        {
            random.setSeed((br.getX() % 256) << 16 | (br.getY() % 256) << 8 | br.getZ() % 256);
        }
        float minX, minY, minZ, maxX, maxY, maxZ;
        float rotX = 0F;
        float rotY = 0F;
        float rotZ = 0F;
        float offset = 0.05F;
        br.setOrigin(0F, 0F, 0F);
        for(int count = 0; count < 6; count++)
        {
            minX = random.nextFloat() * 0.65F;
            minY = 0F;
            minZ = random.nextFloat() * 0.65F;
            maxX = minX + (random.nextFloat() * 0.25F) + 0.1F;
            maxY = minY + (random.nextFloat() * 0.9F) + 0.1F;
            maxZ = minZ + (random.nextFloat() * 0.25F) + 0.1F;
            if(!br.isItem())
            {
                rotX = (random.nextFloat() * 40F) - 20F;
                rotY = (random.nextFloat() * 40F) - 20F;
                rotZ = (random.nextFloat() * 40F) - 20F;
            }
            br.setRotation(rotX, rotY, rotZ);
            br.setBlockBounds(minX, minY, minZ, maxX, maxY, maxZ);
            br.cuboid();
        }
    }
    
    @Override
    public void getSubBlocks(Item item, CreativeTabs creativetabs, List list)
    {
        for(byte b = 0; b <= 6; b++)
        {
            list.add(new ItemStack(item, 1, b));
        }
    }
    
    public static final byte REDSTONE = 0;//drops redstone
    public static final byte SULFUR = 1;//drops sulfur
    public static final byte QUICKSILVER = 2;//drops nothing, spawns quicksilver block on destruction, collect quicksilver with bucket
    public static final byte WATER = 3;//drops nothing; can collect water with bucket
    public static final byte SHADOW = 4;//drops itself
    public static final byte AIR = 5;//drops itself, used to craft crystal blocks for Zenith portal
    public static final byte LAPIS = 6;//drops lapis lazuli, used for Kyther portal

}