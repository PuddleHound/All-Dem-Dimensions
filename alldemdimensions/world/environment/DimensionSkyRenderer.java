package alldemdimensions.world.environment;

import java.awt.image.BufferedImage;
import java.lang.reflect.Method;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.IRenderHandler;//SkyProvider;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import alldemdimensions.AllDemDimensionsClient;
import alldemdimensions.edit.ReflectionManager;
import alldemdimensions.world.Dimension;
import alldemdimensions.world.DimensionZenith;
import alldemdimensions.world.biome.BiomeGenMainDimension;
import alldemdimensions.world.biome.BiomeGenSkyBase;
import alldemdimensions.world.biome.BiomeGenSkyCrystal;

public class DimensionSkyRenderer extends IRenderHandler
{

	public DimensionSkyRenderer()
	{
		this.starGLCallList = GLAllocation.generateDisplayLists(3);
        GL11.glPushMatrix();
        GL11.glNewList(this.starGLCallList, GL11.GL_COMPILE);
        //Minecraft.getMinecraft().renderGlobal.renderStars();
        ReflectionManager.invokeMethod(Minecraft.getMinecraft().renderGlobal, method_RenderGlobal_renderStars);
        GL11.glEndList();
        GL11.glPopMatrix();
        Tessellator tessellator = Tessellator.instance;
        this.glSkyList = this.starGLCallList + 1;
        GL11.glNewList(this.glSkyList, GL11.GL_COMPILE);
		byte b = 64;
        int i = 256 / b + 2;
        float f = 16.0F;
        int j;
        int k;

        for (j = -b * i; j <= b * i; j += b)
        {
            for (k = -b * i; k <= b * i; k += b)
            {
                tessellator.startDrawingQuads();
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + b), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + b), (double)f, (double)(k + b));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + b));
                tessellator.draw();
            }
        }

        GL11.glEndList();
        this.glSkyList2 = this.starGLCallList + 2;
        GL11.glNewList(this.glSkyList2, GL11.GL_COMPILE);
        f = -16.0F;
        tessellator.startDrawingQuads();

        for (j = -b * i; j <= b * i; j += b)
        {
            for (k = -b * i; k <= b * i; k += b)
            {
                tessellator.addVertex((double)(j + b), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + 0));
                tessellator.addVertex((double)(j + 0), (double)f, (double)(k + b));
                tessellator.addVertex((double)(j + b), (double)f, (double)(k + b));
            }
        }

        tessellator.draw();
        GL11.glEndList();
		
		try
		{
			Minecraft mc = Minecraft.getMinecraft();
			java.io.InputStream stream = getClass().getResourceAsStream("assets/alldemdimensions/textures/sky/sleet.png");
			BufferedImage image = javax.imageio.ImageIO.read(stream);
			int color;
			int red;
			int green;
			int blue;
			int alpha;
			int height = image.getHeight();
			int width = image.getWidth();
			snowTexture = new int[height * width];
			snowTextureTemp = new int[height * width];
			for(int x = 0; x < width; x++)
			{
				for(int y = 0; y < height; y++)
				{
					color = image.getRGB(x, y);
					snowTexture[x + (y * width)] = color;
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		loadFieldsAndMethods();
	}
	
	public static void clientTick()
	{
		if(!enableSkyColorMap)
		{
			return;
		}
		EntityLivingBase cameraEntity = Minecraft.getMinecraft().renderViewEntity;
		int red, green, blue, alpha = 255, x, y, biomeX, biomeZ;
        BiomeGenMainDimension biome;
        for(int i = 0; i < 8; i++)
        {
        	biomeX = (int)cameraEntity.posX + ((i - 8) * 16);
        	for(int j = 0; j < 8; j++)
        	{
        		biomeZ = (int)cameraEntity.posZ + ((j - 8) * 16);
        		BiomeGenBase tempBiome = cameraEntity.worldObj.getBiomeGenForCoords(biomeX, biomeZ);
        		if(tempBiome instanceof BiomeGenMainDimension)
        		{
        			biome = (BiomeGenMainDimension)tempBiome;
        		} else
        		{
        			continue;
        		}
        		red = (int)(biome.skyColorBlue * 255F);//green
        		green = (int)(biome.skyColorGreen * 255F);//red
        		blue = (int)(biome.skyColorRed * 255F);//alpha
        		for(int i1 = 0; i1 < 32; i1++)
        		{
        			x = (i * 32) + i1;
        			for(int j1 = 0; j1 < 32; j1++)
        			{
        				y = (j * 32) + j1;
        				//skyColorBuffer.put((blue << 24) | (green << 16) | (red << 8) | alpha);
        				//skyColorArray[x + (y * 256)] = (alpha << 24) | (red << 16) | (green << 8) | blue;
        			}
        		}
        	}
        }
	}

        @Override
	public void render(float partialTicks, WorldClient world, Minecraft mc)
	{
        if(world.provider.dimensionId == Dimension.nether.dimensionId)
        {
            return;
        }
        EntityLivingBase cameraEntity = mc.renderViewEntity;
        if(world.provider.dimensionId == Dimension.ender.dimensionId || 
                (world.provider.dimensionId == Dimension.zenith.dimensionId && (enableSkyColorMap || cameraEntity.worldObj.getBiomeGenForCoords((int)cameraEntity.posX, (int)cameraEntity.posZ) instanceof BiomeGenSkyCrystal)))
        {
            GL11.glDisable(GL11.GL_FOG);
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderHelper.disableStandardItemLighting();
            GL11.glDepthMask(false);
            ResourceLocation texture;
            if(world.provider.dimensionId == Dimension.ender.dimensionId)
            {
                texture = enderSkyTexture;
            } else
            {
                texture = crystalSkyTexture;
            }
            mc.renderEngine.bindTexture(texture);
            Tessellator tessellator = Tessellator.instance;

            clientTick();
            
            if(enableSkyColorMap)
            {
            	chunkToSkyColorMap.clear();
            	int playerPosX = (int)Math.floor(cameraEntity.posX);
	            int playerPosZ = (int)Math.floor(cameraEntity.posZ);
            	for(int relChunkX = -64; relChunkX < 64; relChunkX += 16)
            	{
            		for(int relChunkZ = -64; relChunkZ < 64; relChunkZ += 16)
            		{
            			int absChunkX = playerPosX - (playerPosX % 16) + relChunkX;
            			int absChunkZ = playerPosZ - (playerPosZ % 16) + relChunkZ;
        	            double minX = absChunkX - cameraEntity.posX;
        	            double maxX = minX + 16;
        	            double minZ = absChunkZ - cameraEntity.posZ;
        	            double maxZ = minZ + 16;
        	            Chunk chunk = cameraEntity.worldObj.getChunkFromBlockCoords(absChunkX, absChunkZ);
        	            SkyColorMap colorMap = chunkToSkyColorMap.get(chunk);
        	            if(colorMap == null)
        	            {
        	            	colorMap = new SkyColorMap();
	        	            for(int biomeX = 0; biomeX < 16; biomeX++)
	        	            {
	        	            	for(int biomeZ = 0; biomeZ < 16; biomeZ++)
	        	            	{
	        	            		int color = 16777215;
	        	            		int red = 0, green = 0, blue = 0;
	        	            		int divisor = 0;
	        	            		for(int offsetX = -2; offsetX < 2; offsetX++)
	        	            		{
	        	            			for(int offsetZ = -2; offsetZ < 2; offsetZ++)
	        	            			{
	        	            				BiomeGenBase biomegenbase = cameraEntity.worldObj.getBiomeGenForCoords(absChunkX + biomeX + offsetX, absChunkZ + biomeZ + offsetZ);
	                	            		BiomeGenMainDimension biome = null;
	                	            		if(biomegenbase instanceof BiomeGenMainDimension)
	                	            		{
	                	            			biome = (BiomeGenMainDimension)biomegenbase;
	                	            			red += (int)(biome.skyColorRed * 255F);
	                	            			green += (int)(biome.skyColorGreen * 255F);
	                	            			blue += (int)(biome.skyColorBlue * 255F);
	                	            			divisor++;
	                	            		}
	        	            			}
	        	            		}
	        	            		if(divisor == 0)
	        	            		{
	        	            			divisor = 1;
	        	            		}
	        	            		red /= divisor;
	        	            		green /= divisor;
	        	            		blue /= divisor;
	        	            		color = 255 << 24 | red << 16 | green << 8 | blue;
	        	            		//skyColorArray[biomeX + (biomeZ * 16)] = color;
	        	            		
	        	            		colorMap.pixelData_Y[biomeX + (biomeZ * 16)] = color;
	        	            		
	        	            	}
	        	            }
	        	            for(int y = 0; y < 16; y++)
	        	            {
		        	            for(int x = 0; x < 16; x++)
	    	            		{
	    	            			colorMap.pixelData_negZ[x + (y * 16)] = colorMap.pixelData_Y[x];
	    	            		}
	        	            }
	        	            chunkToSkyColorMap.put(chunk, colorMap);
        	            }
        	            skyColorArray = colorMap.pixelData_Y;
        	            //GL11.glColor3f(1F, 0F, 1F);
        	            skyColorBuffer.clear();
        	            skyColorBuffer.put(skyColorArray);
        	            skyColorBuffer.rewind();
        	            GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 16, 16, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, skyColorBuffer);
        	            GL11.glPushMatrix();
        	            tessellator.startDrawingQuads();
        	            tessellator.addVertexWithUV(minX, -99.0D, minZ, 0.0D, 0.0D);
        	            tessellator.addVertexWithUV(minX, -99.0D, maxZ, 0.0D, 1.0D);
        	            tessellator.addVertexWithUV(maxX, -99.0D, maxZ, 1.0D, 1.0D);
        	            tessellator.addVertexWithUV(maxX, -99.0D, minZ, 1.0D, 0.0D);
        	            tessellator.draw();
        	            
        	            GL11.glRotatef(180F, 1F, 0F, 0F);
        	            GL11.glRotatef(180F, 0F, 1F, 0F);
        	            GL11.glRotatef(180F, 0F, 0F, 1F);
        	            tessellator.startDrawingQuads();
        	            tessellator.addVertexWithUV(minX, -99.0D, minZ, 0.0D, 0.0D);
        	            tessellator.addVertexWithUV(minX, -99.0D, maxZ, 0.0D, 1.0D);
        	            tessellator.addVertexWithUV(maxX, -99.0D, maxZ, 1.0D, 1.0D);
        	            tessellator.addVertexWithUV(maxX, -99.0D, minZ, 1.0D, 0.0D);
        	            tessellator.draw();
        	            GL11.glRotatef(-180F, 0F, 0F, 1F);
        	            GL11.glRotatef(-180F, 0F, 1F, 0F);
        	            GL11.glRotatef(-180F, 1F, 0F, 0F);
        	            GL11.glRotatef(90F, 1F, 0F, 0F);
        	            //GL11.glTranslatef(0, -64, 0);
        	            if(relChunkZ == -64)
        	            {
        	            	for(int offsetY = 0; offsetY < 256; offsetY += 16)
        	            	{
        	            		skyColorArray = colorMap.pixelData_negZ;
        	            		skyColorBuffer.clear();
        	            		skyColorBuffer.put(skyColorArray);
        	            		skyColorBuffer.rewind();
        	            		GL11.glTexSubImage2D(GL11.GL_TEXTURE_2D, 0, 0, 0, 16, 16, GL12.GL_BGRA, GL12.GL_UNSIGNED_INT_8_8_8_8_REV, skyColorBuffer);
        	            		tessellator.startDrawingQuads();
                	            tessellator.addVertexWithUV(minX, -99D, offsetY, 0.0D, 0.0D);
                	            tessellator.addVertexWithUV(minX, -99D, offsetY + 16, 0.0D, 1.0D);
                	            tessellator.addVertexWithUV(maxX, -99D, offsetY + 16, 1.0D, 1.0D);
                	            tessellator.addVertexWithUV(maxX, -99D, offsetY, 1.0D, 0.0D);
                	            tessellator.draw();
        	            	}
        	            }
        	            GL11.glPopMatrix();
            		}
            	}
            }
            
            if(!enableSkyColorMap)
            {
	            for (int i = 0; i < 6; ++i)
	            {
	                GL11.glPushMatrix();
	                
	                if (i == 1)
	                {
	                    GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
	                }
	
	                if (i == 2)
	                {
	                    GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
	                }
	
	                if (i == 3)
	                {
	                    GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
	                }
	
	                if (i == 4)
	                {
	                    GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
	                }
	
	                if (i == 5)
	                {
	                    GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
	                }
	
	                tessellator.startDrawingQuads();
	                //tessellator.setColorOpaque_I(2631720);
	                tessellator.addVertexWithUV(-100.0D, -100.0D, -100.0D, 0.0D, 0.0D);
	                tessellator.addVertexWithUV(-100.0D, -100.0D, 100.0D, 0.0D, 1.0D);
	                tessellator.addVertexWithUV(100.0D, -100.0D, 100.0D, 1.0D, 1.0D);
	                tessellator.addVertexWithUV(100.0D, -100.0D, -100.0D, 1.0D, 0.0D);
	                tessellator.draw();
	                GL11.glPopMatrix();
	            }
            }
            GL11.glDepthMask(true);
            GL11.glEnable(GL11.GL_TEXTURE_2D);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            if(world.provider.dimensionId == Dimension.ender.dimensionId)
            {
                return;
            }
            }
            boolean zenith = world.provider.dimensionId == Dimension.zenith.dimensionId;
			if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglDisableT2D, 3553);
			} else
			{
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}

			Vec3 vec3 = world.getSkyColor(mc.renderViewEntity, partialTicks);
            float skyRed = (float)vec3.xCoord;
            float skyGreen = (float)vec3.yCoord;
            float skyBlue = (float)vec3.zCoord;

            float red;

            if (mc.gameSettings.anaglyph)
            {
                float f = (skyRed * 30.0F + skyGreen * 59.0F + skyBlue * 11.0F) / 100.0F;
                float f1 = (skyRed * 30.0F + skyGreen * 70.0F) / 100.0F;
                red = (skyRed * 30.0F + skyBlue * 70.0F) / 100.0F;
                skyRed = f;
                skyGreen = f1;
                skyBlue = red;
            }

			//set sky color
            GL11.glColor3f(skyRed, skyGreen, skyBlue);
            Tessellator tessellator = Tessellator.instance;
            GL11.glDepthMask(false);
			if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglEnableFog, 2912);
			} else
			{
				GL11.glEnable(GL11.GL_FOG);
			}
            GL11.glColor3f(skyRed, skyGreen, skyBlue);
			//render plain sky
            /**GL11.glCallList(this.glSkyList);**///don't render in crystal biome
			if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglDisableFog, 2912);
			} else
			{
				GL11.glDisable(GL11.GL_FOG);
			}
            GL11.glDisable(GL11.GL_ALPHA_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            RenderHelper.disableStandardItemLighting();
            float[] afloat = world.provider.calcSunriseSunsetColors(world.getCelestialAngle(partialTicks), partialTicks);
            float green;
            float blue;
            float f;
            float f1;

			//sunrise and sunset
            if (afloat != null)
            {
                if(shaders)
				{
					ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglDisableT2D, 3553);
				} else
				{
					GL11.glDisable(GL11.GL_TEXTURE_2D);
				}
                GL11.glShadeModel(GL11.GL_SMOOTH);
                GL11.glPushMatrix();
                GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
                //GL11.glRotatef(MathHelper.sin(world.getCelestialAngleRadians(partialTicks)) < 0.0F ? 180.0F : 0.0F, 0.0F, 0.0F, 1.0F);
                GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
                red = afloat[0];
                green = afloat[1];
                blue = afloat[2];
                float f2;

                if (mc.gameSettings.anaglyph)
                {
                    f = (red * 30.0F + green * 59.0F + blue * 11.0F) / 100.0F;
                    f1 = (red * 30.0F + green * 70.0F) / 100.0F;
                    f2 = (red * 30.0F + blue * 70.0F) / 100.0F;
                    red = f;
                    green = f1;
                    blue = f2;
                }

                tessellator.startDrawing(6);
                tessellator.setColorRGBA_F(red, green, blue, afloat[3]);
                tessellator.addVertex(0.0D, 100.0D, 0.0D);
                byte b = 16;
                tessellator.setColorRGBA_F(afloat[0], afloat[1], afloat[2], 0.0F);

                for (int vertex = 0; vertex <= b; ++vertex)
                {
                    f2 = (float)vertex * (float)Math.PI * 2.0F / (float)b;
                    float f3 = MathHelper.sin(f2);
                    float f4 = MathHelper.cos(f2);
                    tessellator.addVertex((double)(f3 * 120.0F), (double)(f4 * 120.0F), (double)(-f4 * 40.0F * afloat[3]));
                }

                tessellator.draw();
                GL11.glPopMatrix();
                GL11.glShadeModel(GL11.GL_FLAT);
            }

			float starBrightness = world.getStarBrightness(partialTicks);
			if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_setCelestialPosition);
			}

			GL11.glPushMatrix();
            if (starBrightness > 0.0F)
            {
                GL11.glColor4f(starBrightness, starBrightness, starBrightness, starBrightness);
                GL11.glCallList(this.starGLCallList);
            }
			GL11.glPopMatrix();
			
			if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglEnableT2D, 3553);
			} else
			{
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
            GL11.glPushMatrix();
			//adjust sky color for rain
            red = 1.0F;/// - world.getRainStrength(partialTicks);
            green = 0.0F;
            blue = 0.0F;
            f = 0.0F;
            GL11.glColor4f(1.0F, 1.0F, 1.0F, red);
            GL11.glTranslatef(green, blue, f);
            GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			
            if(zenith)
            {
				for(Planet planet : Planet.allPlanets)
				{
					planet.render(mc);
				}
	            
				mc.renderEngine.bindTexture(starTexture);
				for(Constellation constellation : Constellation.allConstellations)
				{
					if(constellation != null)
					{
						constellation.render(mc, partialTicks);
					}
				}
				
				if(DimensionZenith.currentRainbow != null)
				{
					DimensionZenith.currentRainbow.render(mc);
				}
            }
            
            float rotation;
            /*if(zenith)
            {
                rotation = this.getSunMoonAngle(world.getWorldTime(), partialTicks);
            } else*/
            {
                rotation = world.getCelestialAngle(partialTicks);
            }
			GL11.glRotatef(rotation * 360.0F, 1.0F, 0.0F, 0.0F);
			
            //render sun and moon
			
            f1 = 30F;
            BiomeGenBase biomegenbase = world.getBiomeGenForCoords((int)mc.renderViewEntity.posX, (int)mc.renderViewEntity.posZ);
            BiomeGenMainDimension biome = null;
            if(biomegenbase instanceof BiomeGenMainDimension)
            {
                biome = (BiomeGenMainDimension)biomegenbase;
                f1 = biome.sunSize;
            }
            ResourceLocation sun;
            if(zenith)
            {
                sun = zenithSunTexture;
            } else
            {
                sun = sunTexture;
            }
            mc.renderEngine.bindTexture(sun);
            if(zenith)
            {
                int worldTime = (int)(world.getWorldTime() % 48000);
                int i = worldTime;
                while(i % 6000 != 0)
                {
                        i--;
                }
                int j = i + 6000;
                int k = j;
                if(k >= 48000)
                {
                        k = 0;
                }
                float[] af = sunColors[i / 6000];//sunColorMap.get(i);
                float[] af1 = sunColors[k / 6000];//sunColorMap.get(j);
                i = 6000 - (worldTime - i);
                j = 6000 - (j - worldTime);
                float f2 = (float)i / 6000F;
                float f3 = (float)j / 6000F;
                GL11.glColor4f((af[0] * f2) + (af1[0] * f3), (af[1] * f2) + (af1[1] * f3), (af[2] * f2) + (af1[2] * f3), 1.0F);
            }
			tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)(-f1), 100.0D, (double)(-f1), 0.0D, 0.0D);
            tessellator.addVertexWithUV((double)f1, 100.0D, (double)(-f1), 1.0D, 0.0D);
            tessellator.addVertexWithUV((double)f1, 100.0D, (double)f1, 1.0D, 1.0D);
            tessellator.addVertexWithUV((double)(-f1), 100.0D, (double)f1, 0.0D, 1.0D);
            tessellator.draw();
            if(zenith)
            {
                GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);//change color back
            }
			
            f1 = 30.0F;//larger moon
            if(biome != null)
            {
                f1 = biome.moonSize;
            }
            mc.renderEngine.bindTexture(moonTexture);
            int moonPhase = world.getMoonPhase();
            int moonPhase1 = moonPhase % 4;
            int moonPhase2 = moonPhase / 4 % 2;
            float f2 = (float)(moonPhase1 + 0) / 4.0F;
            float f3 = (float)(moonPhase2 + 0) / 2.0F;
            float f4 = (float)(moonPhase1 + 1) / 4.0F;
            float f5 = (float)(moonPhase2 + 1) / 2.0F;
            tessellator.startDrawingQuads();
            tessellator.addVertexWithUV((double)(-f1), -100.0D, (double)f1, (double)f4, (double)f5);
            tessellator.addVertexWithUV((double)f1, -100.0D, (double)f1, (double)f2, (double)f5);
            tessellator.addVertexWithUV((double)f1, -100.0D, (double)(-f1), (double)f2, (double)f3);
            tessellator.addVertexWithUV((double)(-f1), -100.0D, (double)(-f1), (double)f4, (double)f3);
            tessellator.draw();
			if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglDisableT2D, 3553);
			} else
			{
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
			
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glEnable(GL11.GL_ALPHA_TEST);
            if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglEnableFog, 2912);
			} else
			{
				GL11.glEnable(GL11.GL_FOG);
			}
            GL11.glPopMatrix();
            if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglDisableT2D, 3553);
			} else
			{
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
            GL11.glColor3f(0.0F, 0.0F, 0.0F);
            double diffPlayerHorizon = mc.thePlayer.getPosition(partialTicks).yCoord - world.getHorizon();

            if (world.provider.isSkyColored())
            {
                GL11.glColor3f(skyRed * 0.2F + 0.04F, skyGreen * 0.2F + 0.04F, skyBlue * 0.6F + 0.1F);
            }
            else
            {
                GL11.glColor3f(skyRed, skyGreen, skyBlue);
            }

			//render lower sky square
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, -((float)(diffPlayerHorizon - 16.0D)), 0.0F);
            //GL11.glCallList(this.glSkyList2);
            GL11.glPopMatrix();
            if(shaders)
			{
				ReflectionManager.invokeMethod(null, method_shaders_Shaders_sglEnableT2D, 3553);
			} else
			{
				GL11.glEnable(GL11.GL_TEXTURE_2D);
			}
            GL11.glDepthMask(true);
	}
	

	
	public void renderPrecipitation(Minecraft mc, float partialTicks)
    {
		Random random = new Random();
        float rainStrength = mc.theWorld.getRainStrength(partialTicks);
        if (rainStrength > 0.0F)
        {
            mc.entityRenderer.enableLightmap((double)partialTicks);

            if (this.rainXCoords == null)
            {
                this.rainXCoords = new float[1024];
                this.rainYCoords = new float[1024];

                for (int i = 0; i < 32; ++i)
                {
                    for (int j = 0; j < 32; ++j)
                    {
                        float f = (float)(j - 16);
                        float f1 = (float)(i - 16);
                        float f2 = MathHelper.sqrt_float(f * f + f1 * f1);
                        this.rainXCoords[i << 5 | j] = -f1 / f2;
                        this.rainYCoords[i << 5 | j] = f / f2;
                    }
                }
            }

            EntityLivingBase player = mc.renderViewEntity;
            WorldClient world = mc.theWorld;
            int playerPosX = MathHelper.floor_double(player.posX);
            int playerPosY = MathHelper.floor_double(player.posY);
            int playerPosZ = MathHelper.floor_double(player.posZ);
            Tessellator tessellator = Tessellator.instance;
            GL11.glDisable(GL11.GL_CULL_FACE);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.01F);

            double d = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks;
            double d1 = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks;
            double d2 = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks;
            int i = MathHelper.floor_double(d1);
            int distanceRendered = mc.gameSettings.fancyGraphics ? 10 : 5;

            boolean flag = false;
            byte b = -1;
            float ticks = (float)AllDemDimensionsClient.updateCount + partialTicks;

            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            flag = false;

            for (int z = playerPosZ - distanceRendered; z <= playerPosZ + distanceRendered; z++)
            {
                for (int x = playerPosX - distanceRendered; x <= playerPosX + distanceRendered; x++)
                {
                    int j = (z - playerPosZ + 16) * 32 + x - playerPosX + 16;
                    float f = this.rainXCoords[j] * 0.5F;
                    float f1 = this.rainYCoords[j] * 0.5F;
                    BiomeGenBase biomegenbase = world.getBiomeGenForCoords(x, z);
					
					BiomeGenSkyBase biome = null;
					if(biomegenbase instanceof BiomeGenSkyBase)
					{
						biome = (BiomeGenSkyBase)biomegenbase;
					} else
					{
						break;
					}
					EnumZenithWeather weatherType = biome.getWeatherType();

                    //if (biomegenbase.canSpawnLightningBolt() || biomegenbase.getEnableSnow())
                    {
                        int precipHeight = world.getPrecipitationHeight(x, z);
                        int yNeg = playerPosY - distanceRendered;
                        int yPos = playerPosY + distanceRendered;

                        if (yNeg < precipHeight)
                        {
                            yNeg = precipHeight;
                        }

                        if (yPos < precipHeight)
                        {
                            yPos = precipHeight;
                        }

                        float f2 = 1.0F;
                        int k = precipHeight;

                        if (precipHeight < i)
                        {
                            k = i;
                        }

                        if (yNeg != yPos)
                        {
                            random.setSeed((long)(x * x * 3121 + x * 45238971 ^ z * z * 418711 + z * 13761));
                            float temperature = biomegenbase.getFloatTemperature(x, yNeg, z);//1.7.2
                            double d3;
                            float f3;

                            //if (world.getWorldChunkManager().getTemperatureAtHeight(temperature, precipHeight) >= 0.15F)
                            if(weatherType == EnumZenithWeather.RAIN)
							{
                                if (b != 0)
                                {
                                    if (b >= 0)
                                    {
                                        tessellator.draw();
                                    }

                                    b = 0;
                                    //GL11.glBindTexture(GL11.GL_TEXTURE_2D, mc.renderEngine.getTexture("%blur%" + weatherType.getTexture()));//rain
                                    mc.renderEngine.bindTexture(weatherType.getTexture());
                                    tessellator.startDrawingQuads();
                                }

                                f3 = ((float)((AllDemDimensionsClient.updateCount) + x * x * 3121 + x * 45238971 + z * z * 418711 + z * 13761 & 31) + partialTicks) / 32.0F * (3.0F + random.nextFloat());
                                double d4 = (double)((float)x + 0.5F) - player.posX;
                                d3 = (double)((float)z + 0.5F) - player.posZ;
                                float f4 = MathHelper.sqrt_double(d4 * d4 + d3 * d3) / (float)distanceRendered;
                                float f5 = 1.0F;
                                tessellator.setBrightness(world.getLightBrightnessForSkyBlocks(x, k, z, 0));
                                tessellator.setColorRGBA_F(f5, f5, f5, ((1.0F - f4 * f4) * 0.5F + 0.5F) * rainStrength);
                                tessellator.setTranslation(-d * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.addVertexWithUV((double)((float)x - f) + 0.5D, (double)yNeg, (double)((float)z - f1) + 0.5D, (double)(0.0F * f2), (double)((float)yNeg * f2 / 4.0F + f3 * f2));
                                tessellator.addVertexWithUV((double)((float)x + f) + 0.5D, (double)yNeg, (double)((float)z + f1) + 0.5D, (double)(1.0F * f2), (double)((float)yNeg * f2 / 4.0F + f3 * f2));
                                tessellator.addVertexWithUV((double)((float)x + f) + 0.5D, (double)yPos, (double)((float)z + f1) + 0.5D, (double)(1.0F * f2), (double)((float)yPos * f2 / 4.0F + f3 * f2));
                                tessellator.addVertexWithUV((double)((float)x - f) + 0.5D, (double)yPos, (double)((float)z - f1) + 0.5D, (double)(0.0F * f2), (double)((float)yPos * f2 / 4.0F + f3 * f2));
                                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                            }
                            else
                            {
                                if (b != 1)
                                {
                                    if (b >= 0)
                                    {
                                        tessellator.draw();
                                    }
                                    b = 1;

									mc.renderEngine.bindTexture(weatherType.getTexture());
									tessellator.startDrawingQuads();
                                }

                                f3 = ((float)(AllDemDimensionsClient.updateCount & 511) + partialTicks) / 512.0F;
                                float f4 = random.nextFloat() + ticks * 0.01F * (float)random.nextGaussian();
                                float f5 = random.nextFloat() + ticks * (float)random.nextGaussian() * 0.001F;
                                d3 = (double)((float)x + 0.5F) - player.posX;
                                double d4 = (double)((float)z + 0.5F) - player.posZ;
                                float f6 = MathHelper.sqrt_double(d3 * d3 + d4 * d4) / (float)distanceRendered;
                                float f7 = 1.0F;
                                tessellator.setBrightness(240);//getLightBrightnessForSkyBlocks
                                tessellator.setColorRGBA_F(f7, f7, f7, ((1.0F - f6 * f6) * 0.3F + 0.5F) * rainStrength);
                                tessellator.setTranslation(-d * 1.0D, -d1 * 1.0D, -d2 * 1.0D);
                                tessellator.addVertexWithUV((double)((float)x - f) + 0.5D, (double)yNeg, (double)((float)z - f1) + 0.5D, (double)(0.0F * f2 + f4), (double)((float)yNeg * f2 / 4.0F + f3 * f2 + f5));
                                tessellator.addVertexWithUV((double)((float)x + f) + 0.5D, (double)yNeg, (double)((float)z + f1) + 0.5D, (double)(1.0F * f2 + f4), (double)((float)yNeg * f2 / 4.0F + f3 * f2 + f5));
                                tessellator.addVertexWithUV((double)((float)x + f) + 0.5D, (double)yPos, (double)((float)z + f1) + 0.5D, (double)(1.0F * f2 + f4), (double)((float)yPos * f2 / 4.0F + f3 * f2 + f5));
                                tessellator.addVertexWithUV((double)((float)x - f) + 0.5D, (double)yPos, (double)((float)z - f1) + 0.5D, (double)(0.0F * f2 + f4), (double)((float)yPos * f2 / 4.0F + f3 * f2 + f5));
                                tessellator.setTranslation(0.0D, 0.0D, 0.0D);
                            }
                        }
                    }
                }
            }

            if (b >= 0)
            {
                tessellator.draw();
            }

            GL11.glEnable(GL11.GL_CULL_FACE);
            GL11.glDisable(GL11.GL_BLEND);
            GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
            mc.entityRenderer.disableLightmap((double)partialTicks);
        }
    }
	
	public void onWorldTick(Minecraft mc)
	{
		WorldClient world = mc.theWorld;
		if(world.isRaining() && world.getWorldTime() % 10 == 0)
		{
			int color;
			int alpha;
         /*
			for(int i = 0; i < snowTexture.length; i++)
			{
				color = snowTexture[i];
				alpha = (color >> 24) & 255;
				//red = (color >> 16) & 255;
				//green = (color >> 8) & 255;
				//blue = color & 255;
				if(alpha > 0 && world.rand.nextInt(10) == 0)
				{
					snowTextureTemp[i] = (255 << 24) | (255 << 16) | (255 << 8) | 255;
				} else
				{
					snowTextureTemp[i] = color;
				}
			}*/
		}
	}
	
	private void loadFieldsAndMethods()
	{
		class_shaders_Shaders = ReflectionManager.accessClass("net.minecraft.src.Shaders", "Shaders");
		if(class_shaders_Shaders != null)
		{
			shaders = true;
			method_shaders_Shaders_sglEnableT2D = ReflectionManager.accessMethod(class_shaders_Shaders, "sglEnableT2D", "sglEnableT2D", int.class);
			method_shaders_Shaders_sglDisableT2D = ReflectionManager.accessMethod(class_shaders_Shaders, "sglDisableT2D", "sglDisableT2D", int.class);
			method_shaders_Shaders_sglEnableFog = ReflectionManager.accessMethod(class_shaders_Shaders, "sglEnableFog", "sglEnableFog", int.class);
			method_shaders_Shaders_sglDisableFog = ReflectionManager.accessMethod(class_shaders_Shaders, "sglDisableFog", "sglDisableFog", int.class);
			method_shaders_Shaders_setCelestialPosition = ReflectionManager.accessMethod(class_shaders_Shaders, "setCelestialPosition", "setCelestialPosition");
		}
	}
        
    public static class DimensionCloudRenderer extends DimensionSkyRenderer
    {
        @Override
        public void render(float partialTicks, WorldClient world, Minecraft mc)
        {
            int id = world.provider.dimensionId;
            if(id == Dimension.nether.dimensionId || id == Dimension.ender.dimensionId)
            {
                return;
            }
        }
    }

    public static final IRenderHandler cloudRenderer = new DimensionCloudRenderer();
	
	private boolean shaders = false;
	private Class class_shaders_Shaders;
	private Method method_shaders_Shaders_sglEnableT2D;
	private Method method_shaders_Shaders_sglDisableT2D;
	private Method method_shaders_Shaders_sglEnableFog;
	private Method method_shaders_Shaders_sglDisableFog;
	private Method method_shaders_Shaders_setCelestialPosition;
	
    private int starGLCallList;
    private int glSkyList;
    private int glSkyList2;
	private static final float[][] sunColors = new float[8][3];
	private float[] rainXCoords;
	private float[] rainYCoords;
	private static int[] snowTexture;
	private static int[] snowTextureTemp;
    private static final ResourceLocation sunTexture = new ResourceLocation("textures/environment/sun.png");
    private static final ResourceLocation zenithSunTexture = new ResourceLocation("alldemdimensions:textures/sky/zenithSun.png");
    private static final ResourceLocation moonTexture = new ResourceLocation("textures/environment/moon_phases.png");
    private static final ResourceLocation starTexture = new ResourceLocation("alldemdimensions", "textures/sky/stars.png");
    private static final ResourceLocation enderSkyTexture = new ResourceLocation("textures/misc/tunnel.png");
	private static final ResourceLocation crystalSkyTexture = new ResourceLocation("alldemdimensions", "textures/sky/crystalSky.png");
        
	static
	{
		sunColors[0] = new float[]{1.0F, 1.0F, 0.0F};
		sunColors[1] = new float[]{1.0F, 1.0F, 1.0F};
		sunColors[2] = new float[]{0.0F, 0.0F, 1.0F};
		sunColors[3] = new float[]{1.0F, 1.0F, 1.0F};
		sunColors[4] = new float[]{1.0F, 1.0F, 0.0F};
		sunColors[5] = new float[]{1.0F, 0.5F, 0.0F};
		sunColors[6] = new float[]{1.0F, 0.0F, 0.0F};
		sunColors[7] = new float[]{1.0F, 0.5F, 0.0F};
	}
	
	private static final IntBuffer skyColorBuffer = BufferUtils.createIntBuffer(256);
	private static int[] skyColorArray = new int[256];
	public static boolean enableSkyColorMap = false;//disabled for now; currently much too laggy
	private static final HashMap<Chunk, SkyColorMap> chunkToSkyColorMap = new HashMap<Chunk, SkyColorMap>();
	public static final Method method_RenderGlobal_renderStars = ReflectionManager.accessMethod(RenderGlobal.class, "renderStars", "");
}
