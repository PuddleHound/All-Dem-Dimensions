package alldemdimensions.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;

public class ModelAlchemyBottles extends ModelBase
{

    ModelRenderer LargeBottleBottom;
    ModelRenderer LargeBottleTop;
    ModelRenderer PyramidBottleBottom;
    ModelRenderer PyramidBottleMiddle;
    ModelRenderer PyramidBottleTop;
    ModelRenderer TallBottleBottom;
    ModelRenderer TallBottleTop;
    ModelRenderer LargeBottleLiquid;
    ModelRenderer TallBottleLiquidBottom;
    ModelRenderer TallBottleLiquidTop;
    ModelRenderer PyramidBottleLiquidBottom;
    ModelRenderer PyramidBottleLiquidMiddle;
    ModelRenderer LargeBottleTube;
    ModelRenderer PyramidBottleTube;
    ModelRenderer ConnectorTube;
    ModelRenderer LargeBottleMovingLiquid;
    ModelRenderer ConnectorMovingLiquid;
    ModelRenderer PyramidBottleMovingLiquid;
  
  public ModelAlchemyBottles()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      LargeBottleBottom = new ModelRenderer(this, 0, 0);
      LargeBottleBottom.addBox(0F, 0F, 0F, 5, 3, 5);
      LargeBottleBottom.setRotationPoint(-6F, 21F, -2F);
      LargeBottleBottom.setTextureSize(64, 32);
      LargeBottleBottom.mirror = true;
      setRotation(LargeBottleBottom, 0F, 0F, 0F);
      LargeBottleTop = new ModelRenderer(this, 20, 0);
      LargeBottleTop.addBox(0F, 0F, 0F, 3, 5, 3);
      LargeBottleTop.setRotationPoint(-5F, 16F, -1F);
      LargeBottleTop.setTextureSize(64, 32);
      LargeBottleTop.mirror = true;
      setRotation(LargeBottleTop, 0F, 0F, 0F);
      PyramidBottleBottom = new ModelRenderer(this, 0, 8);
      PyramidBottleBottom.addBox(0F, 0F, 0F, 5, 3, 5);
      PyramidBottleBottom.setRotationPoint(2F, 21F, 1F);
      PyramidBottleBottom.setTextureSize(64, 32);
      PyramidBottleBottom.mirror = true;
      setRotation(PyramidBottleBottom, 0F, 0F, 0F);
      PyramidBottleMiddle = new ModelRenderer(this, 20, 8);
      PyramidBottleMiddle.addBox(0F, 0F, 0F, 4, 3, 4);
      PyramidBottleMiddle.setRotationPoint(2.5F, 18F, 1.5F);
      PyramidBottleMiddle.setTextureSize(64, 32);
      PyramidBottleMiddle.mirror = true;
      setRotation(PyramidBottleMiddle, 0F, 0F, 0F);
      PyramidBottleTop = new ModelRenderer(this, 36, 8);
      PyramidBottleTop.addBox(0F, 0F, 0F, 3, 3, 3);
      PyramidBottleTop.setRotationPoint(3F, 15F, 2F);
      PyramidBottleTop.setTextureSize(64, 32);
      PyramidBottleTop.mirror = true;
      setRotation(PyramidBottleTop, 0F, 0F, 0F);
      TallBottleBottom = new ModelRenderer(this, 32, 0);
      TallBottleBottom.addBox(0F, 0F, 0F, 4, 3, 4);
      TallBottleBottom.setRotationPoint(0F, 21F, -6F);
      TallBottleBottom.setTextureSize(64, 32);
      TallBottleBottom.mirror = true;
      setRotation(TallBottleBottom, 0F, 0F, 0F);
      TallBottleTop = new ModelRenderer(this, 48, 0);
      TallBottleTop.addBox(0F, 0F, 0F, 3, 7, 3);
      TallBottleTop.setRotationPoint(0.5F, 14F, -5.5F);
      TallBottleTop.setTextureSize(64, 32);
      TallBottleTop.mirror = true;
      setRotation(TallBottleTop, 0F, 0F, 0F);
      LargeBottleLiquid = new ModelRenderer(this, 0, 16);
      LargeBottleLiquid.addBox(0F, 0F, 0F, 4, 2, 4);
      LargeBottleLiquid.setRotationPoint(-5.5F, 21.5F, -1.5F);
      LargeBottleLiquid.setTextureSize(64, 32);
      LargeBottleLiquid.mirror = true;
      setRotation(LargeBottleLiquid, 0F, 0F, 0F);
      TallBottleLiquidBottom = new ModelRenderer(this, 16, 16);
      TallBottleLiquidBottom.addBox(0F, 0F, 0F, 3, 2, 3);
      TallBottleLiquidBottom.setRotationPoint(0.5F, 21.5F, -5.5F);
      TallBottleLiquidBottom.setTextureSize(64, 32);
      TallBottleLiquidBottom.mirror = true;
      setRotation(TallBottleLiquidBottom, 0F, 0F, 0F);
      TallBottleLiquidTop = new ModelRenderer(this, 28, 15);
      TallBottleLiquidTop.addBox(0F, 0F, 0F, 2, 4, 2);
      TallBottleLiquidTop.setRotationPoint(1F, 17.5F, -5F);
      TallBottleLiquidTop.setTextureSize(64, 32);
      TallBottleLiquidTop.mirror = true;
      setRotation(TallBottleLiquidTop, 0F, 0F, 0F);
      PyramidBottleLiquidBottom = new ModelRenderer(this, 36, 14);
      PyramidBottleLiquidBottom.addBox(0F, 0F, 0F, 4, 2, 4);
      PyramidBottleLiquidBottom.setRotationPoint(2.5F, 21.5F, 1.5F);
      PyramidBottleLiquidBottom.setTextureSize(64, 32);
      PyramidBottleLiquidBottom.mirror = true;
      setRotation(PyramidBottleLiquidBottom, 0F, 0F, 0F);
      PyramidBottleLiquidMiddle = new ModelRenderer(this, 48, 10);
      PyramidBottleLiquidMiddle.addBox(0F, 0F, 0F, 3, 2, 3);
      PyramidBottleLiquidMiddle.setRotationPoint(3F, 19.5F, 2F);
      PyramidBottleLiquidMiddle.setTextureSize(64, 32);
      PyramidBottleLiquidMiddle.mirror = true;
      setRotation(PyramidBottleLiquidMiddle, 0F, 0F, 0F);
      LargeBottleTube = new ModelRenderer(this, 0, 22);
      LargeBottleTube.addBox(0F, 0F, 0F, 2, 8, 2);
      LargeBottleTube.setRotationPoint(-4.5F, 13F, -0.5F);
      LargeBottleTube.setTextureSize(64, 32);
      LargeBottleTube.mirror = true;
      setRotation(LargeBottleTube, 0F, 0F, 0F);
      PyramidBottleTube = new ModelRenderer(this, 0, 22);
      PyramidBottleTube.addBox(0F, 0F, 0F, 2, 8, 2);
      PyramidBottleTube.setRotationPoint(3.5F, 13F, 2.5F);
      PyramidBottleTube.setTextureSize(64, 32);
      PyramidBottleTube.mirror = true;
      setRotation(PyramidBottleTube, 0F, 0F, 0F);
      ConnectorTube = new ModelRenderer(this, 0, 22);
      ConnectorTube.addBox(0F, 0F, 0F, 2, 8, 2);
      ConnectorTube.setRotationPoint(-3F, 15F, -0.5F);
      ConnectorTube.setTextureSize(64, 32);
      ConnectorTube.mirror = true;
      setRotation(ConnectorTube, 0.3926991F, 0F, -1.570796F);
      LargeBottleMovingLiquid = new ModelRenderer(this, 8, 22);
      LargeBottleMovingLiquid.addBox(0F, 0F, 0F, 1, 2, 1);
      LargeBottleMovingLiquid.setRotationPoint(-4F, 20.5F, 0F);
      LargeBottleMovingLiquid.setTextureSize(64, 32);
      LargeBottleMovingLiquid.mirror = true;
      setRotation(LargeBottleMovingLiquid, 0F, 0F, 0F);
      ConnectorMovingLiquid = new ModelRenderer(this, 8, 22);
      ConnectorMovingLiquid.addBox(0F, 0F, 0F, 1, 2, 1);
      ConnectorMovingLiquid.setRotationPoint(-3.5F, 14.5F, 0F);
      ConnectorMovingLiquid.setTextureSize(64, 32);
      ConnectorMovingLiquid.mirror = true;
      setRotation(ConnectorMovingLiquid, 0.3926991F, 0F, -1.570796F);
      PyramidBottleMovingLiquid = new ModelRenderer(this, 60, 22);
      PyramidBottleMovingLiquid.addBox(0F, 0F, 0F, 1, 2, 1);
      PyramidBottleMovingLiquid.setRotationPoint(4F, 13.5F, 3F);
      PyramidBottleMovingLiquid.setTextureSize(64, 32);
      PyramidBottleMovingLiquid.mirror = true;
      setRotation(PyramidBottleMovingLiquid, 0F, 0F, 0F);
  }
  
  public void render(TileEntity tileentity, float x, float y, float z, float f, float f1, float f2, float f3, float f4, float f5)
  {
    render((Entity)null, f, f1, f2, f3, f4, f5);
    //GL11.glPushMatrix();
    //GL11.glTranslatef(((tileentity.getWorldObj().getWorldTime() % 320) * 0.003125F), 0F, 0F);
    LargeBottleBottom.render(f5);
    LargeBottleTop.render(f5);
    PyramidBottleBottom.render(f5);
    PyramidBottleMiddle.render(f5);
    PyramidBottleTop.render(f5);
    TallBottleBottom.render(f5);
    TallBottleTop.render(f5);
    LargeBottleLiquid.render(f5);
    TallBottleLiquidBottom.render(f5);
    TallBottleLiquidTop.render(f5);
    PyramidBottleLiquidBottom.render(f5);
    PyramidBottleLiquidMiddle.render(f5);
    /*LargeBottleTube.render(f5);
    PyramidBottleTube.render(f5);
    ConnectorTube.render(f5);
    LargeBottleMovingLiquid.render(f5);
    ConnectorMovingLiquid.render(f5);
    PyramidBottleMovingLiquid.render(f5);*/
    //GL11.glPopMatrix();
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
    @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
