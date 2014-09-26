package alldemdimensions.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import alldemdimensions.AllDemDimensions;
import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.entity.EntityCrystalFX;
import alldemdimensions.util.TextureLibrary;
import alldemdimensions.world.Dimension;

public class BlockCrystal extends Block implements IBlockRenderer
{
	
    public BlockCrystal()
    {
        super(Material.rock);
        setLightOpacity(0);
    }
	
    /*@Override
	public int getRenderType()
	{
		return AllDemDimensions.blockRenderId;//1.7
	}*/
	
    @Override
    public void registerBlockIcons(IIconRegister iconregister)
    {
        //blockIcon = TextureLibrary.getBlockTexture("zenith/crystal");//("zenith/crystalExterior");//1.7
        //textureInterior = TextureLibrary.getBlockTexture("zenith/crystalInterior");
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
	public boolean onBlockActivated(World world, int i, int j, int k, EntityPlayer entityplayer, int j1, float f, float f1, float f2)
    {
		int l = world.getBlockMetadata(i, j, k);
		int i1 = world.provider.dimensionId;
		if(world.isRemote || entityplayer.getCurrentEquippedItem() == null)
		{
			return false;
		} else
		if(entityplayer.getCurrentEquippedItem().getItem() == Items.snowball && l == 0 && 
			(i1 == Dimension.nether.dimensionId || i1 == Dimension.overworld.dimensionId || i1 == Dimension.ender.dimensionId || i1 == Dimension.kyther.dimensionId))
		{
			AllDemDimensions.skyPortal.activatePortal(world, i, j + 1, k, Blocks.snow, Blocks.snow_layer);
			return true;
		}
		return false;
	}	
	
    @Override
	public void onNeighborBlockChange(World world, int i, int j, int k, Block block)
	{
		int i1 = world.getBlockMetadata(i, j, k);
		Block j1 = world.getBlock(i, j + 1, k);
		int k1 = world.provider.dimensionId;
		if(i1 == 0 && (j1 == Blocks.snow || j1 == Blocks.snow_layer) && 
		(k1 == Dimension.nether.dimensionId || k1 == Dimension.overworld.dimensionId || k1 == Dimension.ender.dimensionId || k1 == Dimension.kyther.dimensionId))
		{
			AllDemDimensions.skyPortal.activatePortal(world, i, j + 1, k, AllDemDimensions.crystal, Blocks.snow, Blocks.snow_layer);
		}
	}
	
    @Override
	public void randomDisplayTick(World world, int x, int y, int z, Random random)
    {
		//1.7
        for (int i = 0; i < 2; i++)
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
            AllDemDimensionsClient.spawnParticle(new EntityCrystalFX(world, d, d1, d2, d3, d4, d5));
        }
    }
	
    @Override
    public void render(BlockRenderer br)
    {
        Random random = new Random();
        random.setSeed(((br.getX() % 256) << 16) | ((br.getY() % 256) << 8) | (br.getZ() % 256));
        if(br.getMetadata() == 1)
        {
            br.setUseRenderBlocks(true);
            br.setBlockBounds(0F, 0F, 0F, 1F, 1F, 1F);
            br.cuboid();
            return;
        } else
        if(br.getMetadata() == 2)
        {
            br.setBlockBounds(0.45F, -0.5F, 0.45F, 0.55F, 1.0F, 0.55F);
            br.cuboid();
            br.setOrigin(0.5F, -0.75F, 0.5F);
            br.setRotation(45F, 45F, 45F);
            br.setBlockBounds(0.25F, -1.0F, 0.25F, 0.75F, -0.5F, 0.75F);
            br.cuboid();
            return;
        }
        float minX = (random.nextFloat() * 0.5F) - 0.25F;
        float minZ = (random.nextFloat() * 0.5F) - 0.25F;
        float maxX = minX + random.nextFloat() * 0.5F;//minX + (random.nextFloat() * 2.25F) + 0.25F;
        float maxZ = minZ + random.nextFloat() * 0.5F;//minZ + (random.nextFloat() * 2.25F) + 0.25F;
        float height = (random.nextFloat() * 4F) + 1F;//(random.nextFloat() * 40F) + 1F;
        float rotateX = random.nextFloat() * 360F, rotateY = (random.nextFloat() * 40F) - 20F, rotateZ = (random.nextFloat() * 40F) - 20F;
        br.setBlockBounds(minX, 0F, minZ, maxX, height, maxZ);
        br.setOrigin(minX, 0F, minZ);
        br.setRotation(rotateX, rotateY, rotateZ);
        br.cuboid();
        float[][] vertices = br.getBlockVertices();
        float diffX = vertices[br.MINX_MINY_MINZ][br.X] - vertices[br.MINX_MAXY_MINZ][br.X];
        float diffY = vertices[br.MINX_MINY_MINZ][br.Y] - vertices[br.MINX_MAXY_MINZ][br.Y];
        float diffZ = vertices[br.MINX_MINY_MINZ][br.Z] - vertices[br.MINX_MAXY_MINZ][br.Z];
        float diffTotal = Math.abs(diffX) + Math.abs(diffY) + Math.abs(diffZ);
        diffX = diffX / diffTotal;
        diffY = diffY / diffTotal;
        diffZ = diffZ / diffTotal;
        float pointX = ((vertices[br.MINX_MAXY_MINZ][br.X] + vertices[br.MINX_MAXY_MAXZ][br.X] + vertices[br.MAXX_MAXY_MAXZ][br.X] + vertices[br.MAXX_MAXY_MINZ][br.X]) * 0.25F) - diffX;
        float pointY = ((vertices[br.MINX_MAXY_MINZ][br.Y] + vertices[br.MINX_MAXY_MAXZ][br.Y] + vertices[br.MAXX_MAXY_MAXZ][br.Y] + vertices[br.MAXX_MAXY_MINZ][br.Y]) * 0.25F) - diffY;
        float pointZ = ((vertices[br.MINX_MAXY_MINZ][br.Z] + vertices[br.MINX_MAXY_MAXZ][br.Z] + vertices[br.MAXX_MAXY_MAXZ][br.Z] + vertices[br.MAXX_MAXY_MINZ][br.Z]) * 0.25F) - diffZ;
        
        br.quad(vertices[br.MINX_MAXY_MINZ][br.X], vertices[br.MINX_MAXY_MINZ][br.Y], vertices[br.MINX_MAXY_MINZ][br.Z],
                vertices[br.MINX_MAXY_MINZ][br.X], vertices[br.MINX_MAXY_MINZ][br.Y], vertices[br.MINX_MAXY_MINZ][br.Z],
                vertices[br.MINX_MAXY_MAXZ][br.X], vertices[br.MINX_MAXY_MAXZ][br.Y], vertices[br.MINX_MAXY_MAXZ][br.Z],
                pointX, pointY, pointZ, 0);
        br.quad(vertices[br.MINX_MAXY_MAXZ][br.X], vertices[br.MINX_MAXY_MAXZ][br.Y], vertices[br.MINX_MAXY_MAXZ][br.Z],
                vertices[br.MINX_MAXY_MAXZ][br.X], vertices[br.MINX_MAXY_MAXZ][br.Y], vertices[br.MINX_MAXY_MAXZ][br.Z],
                vertices[br.MAXX_MAXY_MAXZ][br.X], vertices[br.MAXX_MAXY_MAXZ][br.Y], vertices[br.MAXX_MAXY_MAXZ][br.Z],
                pointX, pointY, pointZ, 0);
        br.quad(vertices[br.MAXX_MAXY_MAXZ][br.X], vertices[br.MAXX_MAXY_MAXZ][br.Y], vertices[br.MAXX_MAXY_MAXZ][br.Z],
                vertices[br.MAXX_MAXY_MAXZ][br.X], vertices[br.MAXX_MAXY_MAXZ][br.Y], vertices[br.MAXX_MAXY_MAXZ][br.Z],
                vertices[br.MAXX_MAXY_MINZ][br.X], vertices[br.MAXX_MAXY_MINZ][br.Y], vertices[br.MAXX_MAXY_MINZ][br.Z],
                pointX, pointY, pointZ, 0);
        br.quad(vertices[br.MAXX_MAXY_MINZ][br.X], vertices[br.MAXX_MAXY_MINZ][br.Y], vertices[br.MAXX_MAXY_MINZ][br.Z],
                vertices[br.MAXX_MAXY_MINZ][br.X], vertices[br.MAXX_MAXY_MINZ][br.Y], vertices[br.MAXX_MAXY_MINZ][br.Z],
                vertices[br.MINX_MAXY_MINZ][br.X], vertices[br.MINX_MAXY_MINZ][br.Y], vertices[br.MINX_MAXY_MINZ][br.Z],
                pointX, pointY, pointZ, 0);
        /*br.setBlockBounds(minX, height, minZ, maxX * 0.5F, height + 0.5F, minZ);
        br.cuboid();
        br.setBlockBounds(minX, height, maxZ, maxX * 0.5F, height + 0.5F, maxZ);
        br.cuboid();
        br.setBlockBounds(minX, height, minZ, minX, height + 0.5F, maxZ * 0.5F);
        br.cuboid();
        br.setBlockBounds(maxX, height, minZ, maxX, height + 0.5F, maxZ * 0.5F);
        br.cuboid();*/
        br.getRenderBlocks().clearOverrideBlockTexture();
        
        /*
        if(br.getMetadata() == 0)
        {
            //Random random = new Random();
            int i = -1, j = -1, k = -1;
            if(!br.isItem())
            {
                i = br.getX();
                j = br.getY();
                k = br.getZ();
            }
            random.setSeed((i % 256) << 16 | (j % 256) << 8 | k % 256);
            int offset = j % 16;
            br.setUseContrastLighting(false);
            br.setBlockTexture(TextureLibrary.getBlockTexture("zenith/crystal/crystal" + offset));
            br.setColor(0xe5e5e5);
            br.setBlockBounds(0.125F, 0.001F, 0.125F, 0.875F, 0.999F, 0.875F);
            br.cuboid();
            br.setColor(0xf2f2f2);
            br.setStretchTexture(false);
            br.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
            br.setOrigin(0.5F, 0.5F, 0.5F);
            br.setRotation(random.nextFloat() * 360F, random.nextFloat() * 360F, random.nextFloat() * 360F);
            br.cuboid();
            br.clearColor();
            //random.setSeed(((br.getX() + 128) % 256) << 16 | ((br.getY() + 128) % 256) << 8 | (br.getZ() + 128) % 256);
            br.setRotation(random.nextFloat() * 360F, random.nextFloat() * 360F, random.nextFloat() * 360F);
            br.setBlockBounds(0.125F, 0.0F, 0.125F, 0.875F, 1.0F, 0.875F);
            br.cuboid();
            br.setBlockTexture(null);
        }*/
    }
    
    private IIcon textureInterior;
    public static final byte CUBE = 1;
}

