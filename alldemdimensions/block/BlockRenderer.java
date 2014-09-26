package alldemdimensions.block;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

import org.lwjgl.opengl.GL11;

import alldemdimensions.util.TextureLibrary;

/**This class works somehow, despite being a complete mess.*/
public final class BlockRenderer
{

    public BlockRenderer(RenderBlocks rb, Block b, byte b1)
    {
        renderblocks = rb;
        block = b;
        metadata = b1;
        item = true;
    }
    
    public BlockRenderer(RenderBlocks rb, IBlockAccess iba, Block b, byte b1, int i, int j, int k)
    {
        renderblocks = rb;
        iblockaccess = iba;
        block = b;
        metadata = b1;
        blockCoordinates = new int[3];
        blockCoordinates[0] = i;
        blockCoordinates[1] = j;
        blockCoordinates[2] = k;
        item = false;
    }
        
    public void setBlockBounds(float... params)
    {
        if(blockBounds == null)
        {
            blockBounds = new float[6];
        }
        for(byte b = 0; b < blockBounds.length; b++)
        {
            blockBounds[b] = params[b];
        }
        blockVertices = null;
    }
    
    public void setStretchTexture(boolean flag)
    {
        stretchTexture = flag;
        if(stretchTexture)
        {
            setUseRenderBlocks(false);
        }
    }
    
    public void setUseRenderBlocks(boolean flag)
    {
        useRenderBlocks = flag;
    }
    
    public void setSideTexture(IIcon icon, int... sides)
    {
        if(sideTextures == null)
        {
            sideTextures = new IIcon[6];
        }
        if(sides.length == 0)
        {
            Arrays.fill(sideTextures, icon);
        } else
        {
            for(int side : sides)
            {
                sideTextures[side] = icon;
            }
        }
    }
    
    /*public void clearSideTextures()
    {
        Arrays.fill(sideTextures, null);
    }*/
    
    public void setTextureRotation(float rotation, int... sides)
    {
        if(textureRotation == null)
        {
            textureRotation = new float[6];
        }
        if(sides.length == 0)
        {
            Arrays.fill(textureRotation, rotation);
        } else
        {
            for(int side : sides)
            {
                textureRotation[side] = rotation;
            }
        }
    }
    
    /*public void clearTextureRotation()
    {
        Arrays.fill(textureRotation, 0F);
    }*/
    
    public boolean isItem()
    {
        return item;
    }
    
    public byte getMetadata()
    {
        return metadata;
    }
    
    public int[] getCoordinates()
    {
        return blockCoordinates;
    }
    
    public int getX()
    {
        return blockCoordinates != null ? blockCoordinates[0] : 0;
    }
    
    public int getY()
    {
        return blockCoordinates != null ? blockCoordinates[1] : 0;
    }
    
    public int getZ()
    {
        return blockCoordinates != null ? blockCoordinates[2] : 0;
    }
    
    public float[][] getBlockVertices()
    {
        return blockVertices;
    }
    
    public void setBlockTexture(IIcon icon)
    {
        renderblocks.overrideBlockTexture = icon;
    }
    
    public void setTexture(String string)
    {
        setBlockTexture(TextureLibrary.getBlockTexture(string));
    }
    
    public void clearTexture()
    {
        setBlockTexture((IIcon)null);
    }
    
    public void setColor(int i)
    {
        color = i;
    }
    
    public void clearColor()
    {
        color = 16777215;
    }
    
    public void setOrigin(float f, float f1, float f2)
    {
        if(blockOrigin == null)
        {
            blockOrigin = new float[3];
        }
        blockOrigin[0] = f;
        blockOrigin[1] = f1;
        blockOrigin[2] = f2;
    }
    
    public void setRotation(float f, float f1, float f2)//y axis, x axis, z axis
    {
        if(blockRotation == null)
        {
            blockRotation = new float[3];
        }
        blockRotation[0] = f;
        blockRotation[1] = f1;
        blockRotation[2] = f2;
    }
    
    public void addRotation(float f, float f1, float f2)
    {
        if(blockRotation == null)
        {
            setRotation(f, f1, f2);
        } else
        {
            blockRotation[0] = (blockRotation[0] + f) % 360F;
            blockRotation[1] = (blockRotation[1] + f1) % 360F;
            blockRotation[2] = (blockRotation[2] + f2) % 360F;
        }
    }
    
    public RenderBlocks getRenderBlocks()
    {
        return renderblocks;
    }
    
    public IBlockAccess getIBlockAccess()
    {
        return iblockaccess;
    }
    
    public void setTranslation(float f, float f1, float f2)
    {
        if(blockTranslation == null)
        {
            blockTranslation = new float[3];
        }
        blockTranslation[0] = f;
        blockTranslation[1] = f1;
        blockTranslation[2] = f2;
    }
    
    public void setItemTranslation(float f, float f1, float f2)
    {
        if(item)
        {
            setTranslation(f, f1, f2);
        }
    }
    
    public void addTranslation(float f, float f1, float f2)
    {
        if(blockTranslation == null)
        {
            setTranslation(f, f1, f2);
        } else
        {
            blockTranslation[0] = blockTranslation[0] + f;
            blockTranslation[1] = blockTranslation[1] + f1;
            blockTranslation[2] = blockTranslation[2] + f2;
        }
    }
    
    public void setScale(float f, float f1, float f2)
    {
        if(blockScale == null)
        {
            blockScale = new float[3];
        }
        blockScale[0] = f;
        blockScale[1] = f1;
        blockScale[2] = f2;
    }
    
    public void setItemScale(float f, float f1, float f2)
    {
        if(item)
        {
            setScale(f, f1, f2);
        }
    }
    
    public void setUseContrastLighting(boolean flag)
    {
        contrastLighting = flag;
    }
    
    public void setEnableLighting(boolean flag)
    {
        enableLighting = flag;
    }
    
    public void cuboid()
    {
        float minX = blockBounds[0];//change to depend on whether block bounds are null; if so, default to block's render bounds
        float minY = blockBounds[1];
        float minZ = blockBounds[2];
        float maxX = blockBounds[3];
        float maxY = blockBounds[4];
        float maxZ = blockBounds[5];
        textureBounds = blockBounds;
        boolean quadX = minX == maxX;
        boolean quadY = minY == maxY;
        boolean quadZ = minZ == maxZ;
        if(blockVertices == null)
        {
            boolean noRotation = blockRotation == null || (blockRotation[0] == 0F && blockRotation[1] == 0F && blockRotation[2] == 0F);
            if(item && noRotation && useRenderBlocks)
            {
                if(noRotation)
                {
                    renderblocks.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                    block.setBlockBoundsForItemRender();
                    GL11.glPushMatrix();
                    if(blockTranslation != null)
                    {
                        GL11.glTranslatef(-0.5F + blockTranslation[0], -0.5F + blockTranslation[1], -0.5F + blockTranslation[2]);
                    } else
                    {
                        GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
                    }
                    if(blockScale != null)
                    {
                        GL11.glScalef(blockScale[0], blockScale[1], blockScale[2]);
                    }
                    Tessellator tessellator = Tessellator.instance;
                    tessellator.startDrawingQuads(); 
                    tessellator.setNormal(0.0F, -1F, 0.0F); 
                    renderblocks.renderFaceYNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 0, metadata)); 
                    tessellator.draw(); 
                    tessellator.startDrawingQuads(); 
                    tessellator.setNormal(0.0F, 1.0F, 0.0F); 
                    renderblocks.renderFaceYPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 1, metadata)); 
                    tessellator.draw(); 
                    tessellator.startDrawingQuads(); 
                    tessellator.setNormal(0.0F, 0.0F, -1F); 
                    renderblocks.renderFaceXPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 2, metadata)); 
                    tessellator.draw(); 
                    tessellator.startDrawingQuads(); 
                    tessellator.setNormal(0.0F, 0.0F, 1.0F); 
                    renderblocks.renderFaceXNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 3, metadata)); 
                    tessellator.draw(); 
                    tessellator.startDrawingQuads(); 
                    tessellator.setNormal(-1F, 0.0F, 0.0F); 
                    renderblocks.renderFaceZNeg(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 4, metadata)); 
                    tessellator.draw(); 
                    tessellator.startDrawingQuads(); 
                    tessellator.setNormal(1.0F, 0.0F, 0.0F); 
                    renderblocks.renderFaceZPos(block, 0.0D, 0.0D, 0.0D, renderblocks.getBlockIconFromSideAndMetadata(block, 5, metadata)); 
                    tessellator.draw(); 
                    GL11.glPopMatrix();
                }
                return;
            } else
            {
                if(useRenderBlocks && noRotation)//stretchTexture
                {
                    renderblocks.setRenderBounds(minX, minY, minZ, maxX, maxY, maxZ);
                    renderStandardBlock(renderblocks, block, color, blockCoordinates[0], blockCoordinates[1], blockCoordinates[2]);
                    return;
                } else
                {
                    if(noRotation)
                    {
                        blockRotation = new float[3];
                        Arrays.fill(blockRotation, 0F);
                    }
                    blockVertices = new float[8][3];
                    float pointX, pointY, pointZ, d, d1, d2;
                    float rotateX = blockRotation[0] * (float)Math.PI / 180F;
                    float rotateY = blockRotation[1] * (float)Math.PI / 180F;
                    float rotateZ = blockRotation[2] * (float)Math.PI / 180F;
                    float sinX = (float)Math.sin(rotateX);
                    float sinY = (float)Math.sin(rotateY);
                    float sinZ = (float)Math.sin(rotateZ);
                    float cosX = (float)Math.cos(rotateX);
                    float cosY = (float)Math.cos(rotateY);
                    float cosZ = (float)Math.cos(rotateZ);
                    float originX = 0F, originY = 0F, originZ = 0F;
                    if(blockOrigin != null)
                    {
                        originX = blockOrigin[0];
                        originY = blockOrigin[1];
                        originZ = blockOrigin[2];
                    }
                    for(byte b = 0; b < 2; b++)
                    {
                        if(b == 0)
                        {
                            pointX = minX - originX;
                        } else
                        {
                            pointX = maxX - originX;
                        }
                        for(byte b1 = 0; b1 < 2; b1++)
                        {
                            if(b1 == 0)
                            {
                                pointY = minY - originY;
                            } else
                            {
                                pointY = maxY - originY;
                            }
                            for(byte b2 = 0; b2 < 2; b2++)
                            {
                                if(b2 == 0)
                                {
                                    pointZ = minZ - originZ;
                                } else
                                {
                                    pointZ = maxZ - originZ;
                                }
                                d2 = pointY * sinY + pointZ * cosY;
                                d = pointX * cosX - d2 * sinX;
                                d1 = pointY * cosY - pointZ * sinY;
                                blockVertices[b << 2 | b1 << 1 | b2][0] = (d * cosZ - d1 * sinZ) + originX;
                                blockVertices[b << 2 | b1 << 1 | b2][1] = (d * sinZ + d1 * cosZ) + originY;
                                blockVertices[b << 2 | b1 << 1 | b2][2] = (pointX * sinX + d2 * cosX) + originZ;
                            }
                        }
                    }
                }
            }
        }
        if(item)
        {
            //return;
        }
        boolean quad = quadX || quadY || quadZ;
        /*int i = blockCoordinates[0];
        int j = blockCoordinates[1];
        int k = blockCoordinates[2];*/
        //if(item){block.setBlockBoundsForItemRender();}
        
        if(!quad)
        {
            quad(blockVertices[MAXX_MINY_MINZ][X], blockVertices[MAXX_MINY_MINZ][Y], blockVertices[MAXX_MINY_MINZ][Z], blockVertices[MAXX_MINY_MAXZ][X], blockVertices[MAXX_MINY_MAXZ][Y], blockVertices[MAXX_MINY_MAXZ][Z],
                blockVertices[MINX_MINY_MAXZ][X], blockVertices[MINX_MINY_MAXZ][Y], blockVertices[MINX_MINY_MAXZ][Z], blockVertices[MINX_MINY_MINZ][X], blockVertices[MINX_MINY_MINZ][Y], blockVertices[MINX_MINY_MINZ][Z], SIDE_BOTTOM);//bottom
        }
        if(!quad || quadY)
        {
            quad(
                blockVertices[MINX_MAXY_MINZ][X], blockVertices[MINX_MAXY_MINZ][Y], blockVertices[MINX_MAXY_MINZ][Z], blockVertices[MINX_MAXY_MAXZ][X], blockVertices[MINX_MAXY_MAXZ][Y], blockVertices[MINX_MAXY_MAXZ][Z],
                blockVertices[MAXX_MAXY_MAXZ][X], blockVertices[MAXX_MAXY_MAXZ][Y], blockVertices[MAXX_MAXY_MAXZ][Z], blockVertices[MAXX_MAXY_MINZ][X], blockVertices[MAXX_MAXY_MINZ][Y], blockVertices[MAXX_MAXY_MINZ][Z], SIDE_TOP);//top
        }
        if(!quad || quadX)
        {
            quad(
                blockVertices[MINX_MINY_MINZ][X], blockVertices[MINX_MINY_MINZ][Y], blockVertices[MINX_MINY_MINZ][Z], blockVertices[MINX_MINY_MAXZ][X], blockVertices[MINX_MINY_MAXZ][Y], blockVertices[MINX_MINY_MAXZ][Z],
                blockVertices[MINX_MAXY_MAXZ][X], blockVertices[MINX_MAXY_MAXZ][Y], blockVertices[MINX_MAXY_MAXZ][Z], blockVertices[MINX_MAXY_MINZ][X], blockVertices[MINX_MAXY_MINZ][Y], blockVertices[MINX_MAXY_MINZ][Z], SIDE_WEST);//neg x//north
        }
        if(!quad)
        {
            quad(
                blockVertices[MAXX_MINY_MAXZ][X], blockVertices[MAXX_MINY_MAXZ][Y], blockVertices[MAXX_MINY_MAXZ][Z], blockVertices[MAXX_MINY_MINZ][X], blockVertices[MAXX_MINY_MINZ][Y], blockVertices[MAXX_MINY_MINZ][Z],
                blockVertices[MAXX_MAXY_MINZ][X], blockVertices[MAXX_MAXY_MINZ][Y], blockVertices[MAXX_MAXY_MINZ][Z], blockVertices[MAXX_MAXY_MAXZ][X], blockVertices[MAXX_MAXY_MAXZ][Y], blockVertices[MAXX_MAXY_MAXZ][Z], SIDE_EAST);//pos x//south
        }
        if(!quad || quadZ)
        {
            quad(
                blockVertices[MINX_MINY_MINZ][X], blockVertices[MINX_MINY_MINZ][Y], blockVertices[MINX_MINY_MINZ][Z], blockVertices[MINX_MAXY_MINZ][X], blockVertices[MINX_MAXY_MINZ][Y], blockVertices[MINX_MAXY_MINZ][Z],
                blockVertices[MAXX_MAXY_MINZ][X], blockVertices[MAXX_MAXY_MINZ][Y], blockVertices[MAXX_MAXY_MINZ][Z], blockVertices[MAXX_MINY_MINZ][X], blockVertices[MAXX_MINY_MINZ][Y], blockVertices[MAXX_MINY_MINZ][Z], SIDE_NORTH);//neg z//east
        }
        if(!quad)
        {
            quad(
                blockVertices[MAXX_MINY_MAXZ][X], blockVertices[MAXX_MINY_MAXZ][Y], blockVertices[MAXX_MINY_MAXZ][Z], blockVertices[MAXX_MAXY_MAXZ][X], blockVertices[MAXX_MAXY_MAXZ][Y], blockVertices[MAXX_MAXY_MAXZ][Z],
                blockVertices[MINX_MAXY_MAXZ][X], blockVertices[MINX_MAXY_MAXZ][Y], blockVertices[MINX_MAXY_MAXZ][Z], blockVertices[MINX_MINY_MAXZ][X], blockVertices[MINX_MINY_MAXZ][Y], blockVertices[MINX_MINY_MAXZ][Z], SIDE_SOUTH);//pos z//west
        }
    }
    
    public void quad(int[] ai, int[] ai1, int[] ai2, int[] ai3, int side)
    {
        quad(ai[0], ai[1], ai[2], ai1[0], ai1[1], ai1[2], ai2[0], ai2[1], ai2[2], ai3[0], ai3[1], ai3[2], side);
    }
    
    public void quad(float f, float f1, float f2, float f3, float f4, float f5, float f6, float f7, float f8, float f9, float f10, float f11, int side)
    {
        int x = 0, y = 0, z = 0;
        if(!item)
        {
            x = blockCoordinates[X];
            y = blockCoordinates[Y];
            z = blockCoordinates[Z];
            if(!block.shouldSideBeRendered(iblockaccess, x + sideOrientation[side][X], y + sideOrientation[side][Y], z + sideOrientation[side][Z], side))
	        {
	            return;
	        }
        }
        Tessellator tessellator = Tessellator.instance;
        IIcon icon;
        if(item)
        {
            icon = block.getIcon(side, metadata);
        } else
        {
            icon = block.getIcon(side, iblockaccess.getBlockMetadata(x, y, z));
        }
        if(renderblocks.overrideBlockTexture != null)
        {
            icon = renderblocks.overrideBlockTexture;
        }
        if(sideTextures != null && sideTextures[side] != null)
        {
            icon = sideTextures[side];
        }
        if(icon == null)
        {
            return;
        }
        if(!item)
        {
            if(enableLighting)
            {
                int brightness = block.getMixedBrightnessForBlock(iblockaccess, x, y, z);
                if(contrastLighting && (side == SIDE_EAST || side == SIDE_WEST))
                {
                    brightness = (int)(brightness * 0.95F);
                }
                tessellator.setBrightness(brightness);
            } else
            {
                tessellator.setBrightness(15728880);
            }
        }
        float red = (float)(color >> 16 & 255) / 255.0F;
        float green = (float)(color >> 8 & 255) / 255.0F;
        float blue = (float)(color & 255) / 255.0F;
        if(EntityRenderer.anaglyphEnable)
        {
                red = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
                green = (red * 30.0F + green * 70.0F) / 100.0F;
                blue = (red * 30.0F + blue * 70.0F) / 100.0F;
        }
        tessellator.setColorOpaque_F(red, green, blue);
        if(item)
        {
            if(renderblocks.useInventoryTint)
            {
                GL11.glColor4f(red * 1F, green * 1F, blue * 1F, 1F);
            }
            if(blockTranslation != null)
            {
                GL11.glTranslatef(-0.5F + blockTranslation[0], -0.5F + blockTranslation[1], -0.5F + blockTranslation[2]);
            } else
            {
                GL11.glTranslatef(-0.5F, -0.5F, -0.5F);
            }
            if(blockScale != null)
            {
                GL11.glScalef(blockScale[0], blockScale[1], blockScale[2]);
            }
            tessellator.startDrawingQuads();
            tessellator.setNormal(normals[side][0], normals[side][1], normals[side][2]);
        }

        float maxU = 0F, maxV = 0F, minU = 0F, minV = 0F;
        /*if(textureRotation == null || textureRotation[side] == 0F)
        {
            maxX = icon.getMaxU();
            maxY = icon.getMinV();
            minX = icon.getMinU();
            minY = icon.getMaxV();
        } else
        if(textureRotation != null)
        {
            float rotation = textureRotation[side];
            switch((int)rotation)
            {
                case 180:
                    maxX = icon.getMinU();
                    maxY = icon.getMaxV();
                    minX = icon.getMaxU();
                    minY = icon.getMinV();
                    break;
            }
        }*/

        if(side == SIDE_BOTTOM || side == SIDE_TOP)
        {
        	float minX = textureBounds[MIN_X];
        	float maxX = textureBounds[MAX_X];
        	float minZ = textureBounds[MIN_Z];
        	float maxZ = textureBounds[MAX_Z];
        	float floorMinX = (float)Math.floor(minX);
        	float floorMaxX = (float)Math.floor(maxX);
        	float floorMinZ = (float)Math.floor(minZ);
        	float floorMaxZ = (float)Math.floor(maxZ);
        	if(floorMinX == floorMaxX)
        	{
        		minU = minX - floorMinX;
        		maxU = maxX - floorMaxX;
        	} else
        	{
            	maxU = maxX - minX;
        	}
        	if(floorMinZ == floorMaxZ)
        	{
        		minV = minZ - floorMinZ;
        		maxV = maxZ - floorMaxZ;
        	} else
        	{
            	maxV = maxZ - minZ;
        	}
        } else
        if(side == SIDE_NORTH || side == SIDE_SOUTH)
        {
        	//minX = textureBounds[0]; minY = textureBounds[1]; maxX = textureBounds[3]; maxY = textureBounds[4];
        	minU = 0;//textureBounds[0] - (float)Math.floor(textureBounds[0]);
        	minV = 0;//textureBounds[2] - (float)Math.floor(textureBounds[2]);
        	maxU = textureBounds[MAX_X] - textureBounds[MIN_X];//textureBounds[3] - (float)Math.floor(textureBounds[0]);
        	maxV = textureBounds[MAX_Y] - textureBounds[MIN_Y];//textureBounds[5] - (float)Math.floor(textureBounds[2]);
        	
        	float minX = textureBounds[MIN_X];
        	float maxX = textureBounds[MAX_X];
        	float minY = textureBounds[MIN_Y];
        	float maxY = textureBounds[MAX_Y];
        	float floorMinX = (float)Math.floor(minX);
        	float floorMaxX = (float)Math.floor(maxX);
        	float floorMinY = (float)Math.floor(minY);
        	float floorMaxY = (float)Math.floor(maxY);
        	if(floorMinX == floorMaxX)
        	{
        		minU = minX - floorMinX;
        		maxU = maxX - floorMaxX;
        	} else
        	{
            	maxU = maxX - minX;
        	}
        	if(floorMinY == floorMaxY)
        	{
        		minV = minY - floorMinY;
        		maxV = maxY - floorMaxY;
        	} else
        	{
            	maxV = maxY - minY;
        	}
        } else
        if(side == SIDE_EAST || side == SIDE_WEST)
        {
        	//minX = textureBounds[0]; minY = textureBounds[1]; maxX = textureBounds[3]; maxY = textureBounds[4];
        	minU = 0;//textureBounds[0] - (float)Math.floor(textureBounds[0]);
        	minV = 0;//textureBounds[2] - (float)Math.floor(textureBounds[2]);
        	maxU = textureBounds[MAX_Z] - textureBounds[MIN_Z];//textureBounds[3] - (float)Math.floor(textureBounds[0]);
        	maxV = textureBounds[MAX_Y] - textureBounds[MIN_Y];//textureBounds[5] - (float)Math.floor(textureBounds[2]);
        	
        	float minZ = textureBounds[MIN_Z];
        	float maxZ = textureBounds[MAX_Z];
        	float minY = textureBounds[MIN_Y];
        	float maxY = textureBounds[MAX_Y];
        	float floorMinZ = (float)Math.floor(minZ);
        	float floorMaxZ = (float)Math.floor(maxZ);
        	float floorMinY = (float)Math.floor(minY);
        	float floorMaxY = (float)Math.floor(maxY);
        	if(floorMinZ == floorMaxZ)
        	{
        		minU = minZ - floorMinZ;
        		maxU = maxZ - floorMaxZ;
        	} else
        	{
            	maxU = maxZ - minZ;
        	}
        	if(floorMinY == floorMaxY)
        	{
        		minV = minY - floorMinY;
        		maxV = maxY - floorMaxY;
        	} else
        	{
            	maxV = maxY - minY;
        	}
        } 
        /*else
    	if(side == SIDE_EAST || side == SIDE_WEST)
        {
        	minX = textureBounds[1]; minY = textureBounds[2]; maxX = textureBounds[4]; maxY = textureBounds[5];
        }*/
        
        //texture options:
        //stretch or shrink texture to fit surface
        //make texture originate from top left
        //set texture bounds to arbitrary coordinates
        //set texture bounds to random coordinates, with seed based on block location
        //map texture to corresponding world coordinates (default Minecraft method - does not work for surfaces that extend beyond one block but are less than a block long)
        float texMinU = 0;
        float texMaxU = 0;
        float texMinV = 0;
        float texMaxV = 0;
        if(side == SIDE_BOTTOM || side == SIDE_TOP)
        {
        	texMinU = icon.getInterpolatedU(minU * 16D);
            texMaxU = icon.getInterpolatedU(maxU * 16D);
        	texMinV = icon.getInterpolatedV(minV * 16D);
            texMaxV = icon.getInterpolatedV(maxV * 16D);
        } else
        if(side == SIDE_NORTH)
        {
        	texMinU = icon.getInterpolatedU(maxU * 16D);
            texMaxU = icon.getInterpolatedU(minU * 16D);
        	texMinV = icon.getInterpolatedV(16D - maxV * 16D);
            texMaxV = icon.getInterpolatedV(16D - minV * 16D);
        } else
        if(side == SIDE_SOUTH)
        {
        	texMinU = icon.getInterpolatedU(minU * 16D);
            texMaxU = icon.getInterpolatedU(maxU * 16D);
        	texMinV = icon.getInterpolatedV(16D - maxV * 16D);
            texMaxV = icon.getInterpolatedV(16D - minV * 16D);
        } else
        if(side == SIDE_EAST)
        {
        	texMinU = icon.getInterpolatedU(maxU * 16D);
            texMaxU = icon.getInterpolatedU(minU * 16D);
        	texMinV = icon.getInterpolatedV(16D - maxV * 16D);
            texMaxV = icon.getInterpolatedV(16D - minV * 16D);
        } else
        if(side == SIDE_WEST)
        {
        	texMinU = icon.getInterpolatedU(minU * 16D);
            texMaxU = icon.getInterpolatedU(maxU * 16D);
        	texMinV = icon.getInterpolatedV(16D - maxV * 16D);
            texMaxV = icon.getInterpolatedV(16D - minV * 16D);
        }
        
        
        if (maxU - minU > 1D)//(minX < 0.0D || maxX > 1.0D)
        {
            texMinU = icon.getMinU();
            texMaxU = icon.getMaxU();
        }
        if (maxV - minV > 1D)//(minY < 0.0D || maxY > 1.0D)
        {
            texMinV = icon.getMinV();
            texMaxV = icon.getMaxV();
        }
        //System.out.println(maxY);
        float[] textureVertices = getTextureVertexOrder(side, texMinU, texMaxU, texMinV, texMaxV);
        
        
        //RenderBlocks
        
        /*if(side == SIDE_TOP || side == SIDE_BOTTOM)
        {
            tessellator.addVertexWithUV(x + f, y + f1, z + f5, textureVertices[0], textureVertices[1]); 
            tessellator.addVertexWithUV(x + f3, y + f4, z + f2, textureVertices[2], textureVertices[3]);
            tessellator.addVertexWithUV(x + f6, y + f7, z + f11, textureVertices[4], textureVertices[5]);
            tessellator.addVertexWithUV(x + f9, y + f10, z + f8, textureVertices[6], textureVertices[7]);
            if(block.getRenderBlockPass() == 0)
            {
                tessellator.addVertexWithUV(x + f9, y + f10, z + f8, textureVertices[6], textureVertices[7]);
                tessellator.addVertexWithUV(x + f6, y + f7, z + f11, textureVertices[4], textureVertices[5]);
                tessellator.addVertexWithUV(x + f3, y + f4, z + f2, textureVertices[2], textureVertices[3]);
                tessellator.addVertexWithUV(x + f, y + f1, z + f5, textureVertices[0], textureVertices[1]);
            }
        } else
        if(side == SIDE_NORTH)
        {
        	tessellator.addVertexWithUV(x + f3, y + f4, z + f2, textureVertices[0], textureVertices[1]); 
            tessellator.addVertexWithUV(x + f6, y + f7, z + f5, textureVertices[2], textureVertices[3]);
            tessellator.addVertexWithUV(x + f9, y + f10, z + f8, textureVertices[4], textureVertices[5]);
            tessellator.addVertexWithUV(x + f, y + f1, z + f11, textureVertices[6], textureVertices[7]);
            if(block.getRenderBlockPass() == 0)
            {
                tessellator.addVertexWithUV(x + f, y + f1, z + f11, textureVertices[6], textureVertices[7]);
                tessellator.addVertexWithUV(x + f9, y + f10, z + f8, textureVertices[4], textureVertices[5]);
                tessellator.addVertexWithUV(x + f6, y + f7, z + f5, textureVertices[2], textureVertices[3]);
                tessellator.addVertexWithUV(x + f3, y + f4, z + f2, textureVertices[0], textureVertices[1]);
            }
        } else*/
        {
        //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[0]);
        tessellator.addVertexWithUV(x + f, y + f1, z + f2, textureVertices[0], textureVertices[1]); 
        //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[1]);
        tessellator.addVertexWithUV(x + f3, y + f4, z + f5, textureVertices[2], textureVertices[3]);
        //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[2]);
        tessellator.addVertexWithUV(x + f6, y + f7, z + f8, textureVertices[4], textureVertices[5]);
        //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[3]);
        tessellator.addVertexWithUV(x + f9, y + f10, z + f11, textureVertices[6], textureVertices[7]);
        if(block.getRenderBlockPass() == 0)
        {
            //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[3]);
            tessellator.addVertexWithUV(x + f9, y + f10, z + f11, textureVertices[6], textureVertices[7]);
            //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[2]);
            tessellator.addVertexWithUV(x + f6, y + f7, z + f8, textureVertices[4], textureVertices[5]);
            //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[1]);
            tessellator.addVertexWithUV(x + f3, y + f4, z + f5, textureVertices[2], textureVertices[3]);
            //tessellator.setColorOpaque_F(red, green, blue); tessellator.setBrightness(brightness[0]);
            tessellator.addVertexWithUV(x + f, y + f1, z + f2, textureVertices[0], textureVertices[1]);
        }
        }
        if(item)
        {
            tessellator.draw();
            if(blockScale != null)
            {
                GL11.glScalef(1 / blockScale[0], 1 / blockScale[1], 1 / blockScale[2]);
            }
            if(blockTranslation != null)
            {
                GL11.glTranslatef(0.5F - blockTranslation[0], 0.5F - blockTranslation[1], 0.5F - blockTranslation[2]);
            } else
            {
                GL11.glTranslatef(0.5F, 0.5F, 0.5F);
            }
            if(renderblocks.useInventoryTint)
            {
                GL11.glColor4f(1F, 1F, 1F, 1F);
            }
        }
    }
    
    public static float[] getTextureVertexOrder(int side, float minX, float maxX, float minY, float maxY)
    {
        if(side == SIDE_EAST)
        {
            return new float[]{maxX, minY, minX, minY, minX, maxY, maxX, maxY};
        } else
        if(side == SIDE_WEST)
        {
            return new float[]{maxX, minY, minX, minY, minX, maxY, maxX, maxY};
        } else
        if(side == SIDE_SOUTH)
        {
            return new float[]{minX, minY, minX, maxY, maxX, maxY, maxX, minY};//y opposite works
        } else
        if(side == SIDE_NORTH)
        {
            return new float[]{minX, minY, minX, maxY, maxX, maxY, maxX, minY};//y opposite works
        } else
        if(side == SIDE_TOP)
        {
            return new float[]{maxX, maxY, maxX, minY, minX, minY, minX, maxY};
        } else
        {
            return new float[]{minX, maxY, minX, minY, maxX, minY, maxX, maxY};
        }
    }
    
    public static boolean renderStandardBlock(RenderBlocks renderblocks, Block block, int color, int i, int j, int k)
    {
        float f = (float)(color >> 16 & 255) / 255.0F;
        float f1 = (float)(color >> 8 & 255) / 255.0F;
        float f2 = (float)(color & 255) / 255.0F;
        if (EntityRenderer.anaglyphEnable)
        {
            float f3 = (f * 30.0F + f1 * 59.0F + f2 * 11.0F) / 100.0F;
            float f4 = (f * 30.0F + f1 * 70.0F) / 100.0F;
            float f5 = (f * 30.0F + f2 * 70.0F) / 100.0F;
            f = f3;
            f1 = f4;
            f2 = f5;
        }
        return Minecraft.isAmbientOcclusionEnabled() && block.getLightValue(renderblocks.minecraftRB.theWorld, i, j, k) == 0 ? (renderblocks.partialRenderBounds ? renderblocks.renderStandardBlockWithAmbientOcclusionPartial(block, i, j, k, f, f1, f2) : renderblocks.renderStandardBlockWithAmbientOcclusion(block, i, j, k, f, f1, f2)) : renderblocks.renderStandardBlockWithColorMultiplier(block, i, j, k, f, f1, f2);
    }
    
    private RenderBlocks renderblocks;
    private IBlockAccess iblockaccess;
    private Block block;
    private byte metadata;
    private boolean item;
    private boolean stretchTexture = true;
    private boolean useRenderBlocks = false;
    private int color = 16777215;
    private int[] blockCoordinates;
    private float[] blockBounds;
    private float[] blockScale;
    private float[] blockRotation;
    private float[] blockOrigin;
    private float[] blockTranslation;
    private float[][] blockVertices;
    private float[] textureScale;
    private float[] textureRotation;
    private float[] textureOrigin;
    private float[] textureTranslation;
    private IIcon[] sideTextures;
    private int[] lightValues;
    private boolean contrastLighting = true;
    private boolean enableLighting = true;
    
    private float[] textureBounds = new float[6];//1.7.2
    
    //sides
    public static final byte SIDE_BOTTOM = 0;
    public static final byte SIDE_TOP = 1;
    public static final byte SIDE_EAST = 5;//posX
    public static final byte SIDE_WEST = 4;//negX
    public static final byte SIDE_NORTH = 2;//negZ
    public static final byte SIDE_SOUTH = 3;//posZ
    public static final byte[][] sideOrientation = new byte[6][3];
    static
    {
    	sideOrientation[SIDE_BOTTOM] = new byte[]{0, -1, 0};
    	sideOrientation[SIDE_TOP] = new byte[]{0, 1, 0};
    	sideOrientation[SIDE_EAST] = new byte[]{1, 0, 0};
    	sideOrientation[SIDE_WEST] = new byte[]{-1, 0, 0};
    	sideOrientation[SIDE_NORTH] = new byte[]{0, 0, -1};
    	sideOrientation[SIDE_SOUTH] = new byte[]{0, 0, 1};
    }
    
    //vertices
    public static final byte X = 0;
    public static final byte Y = 1;
    public static final byte Z = 2;
    public static final byte MIN_X = 0;
    public static final byte MIN_Y = 1;
    public static final byte MIN_Z = 2;
    public static final byte MAX_X = 3;
    public static final byte MAX_Y = 4;
    public static final byte MAX_Z = 5;
    public static final byte MINX_MINY_MINZ = 0 << 2 | 0 << 1 | 0;
    public static final byte MINX_MINY_MAXZ = 0 << 2 | 0 << 1 | 1;
    public static final byte MAXX_MINY_MAXZ = 1 << 2 | 0 << 1 | 1;
    public static final byte MAXX_MINY_MINZ = 1 << 2 | 0 << 1 | 0;
    public static final byte MINX_MAXY_MINZ = 0 << 2 | 1 << 1 | 0;
    public static final byte MINX_MAXY_MAXZ = 0 << 2 | 1 << 1 | 1;
    public static final byte MAXX_MAXY_MAXZ = 1 << 2 | 1 << 1 | 1;
    public static final byte MAXX_MAXY_MINZ = 1 << 2 | 1 << 1 | 0;
    
    public static final int[][] vertices = new int[8][3];
    static
    {
        vertices[MINX_MINY_MINZ] = new int[]{0, 0, 0};
        vertices[MINX_MINY_MAXZ] = new int[]{0, 0, 1};
        vertices[MAXX_MINY_MAXZ] = new int[]{1, 0, 1};
        vertices[MAXX_MINY_MINZ] = new int[]{1, 0, 0};
        vertices[MINX_MAXY_MINZ] = new int[]{0, 1, 0};
        vertices[MINX_MAXY_MAXZ] = new int[]{0, 1, 1};
        vertices[MAXX_MAXY_MAXZ] = new int[]{1, 1, 1};
        vertices[MAXX_MAXY_MINZ] = new int[]{1, 1, 0};
    }
    
    public static final float[][] normals = new float[][]{{0F, -1F, 0F,}, {0F, 1F, 0F}, {0F, 0F, -1F}, {0F, 0F, 1F}, {-1F, 0F, 0F}, {1F, 0F, 0F}};
    public static final float maxDist = (float)Math.sqrt(3D);
}
