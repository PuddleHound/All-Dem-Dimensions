package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHotAirBalloon extends ModelBase
{

    ModelRenderer BasketBottom;
    ModelRenderer BasketSide1;
    ModelRenderer BasketSide2;
    ModelRenderer BasketSide3;
    ModelRenderer BasketSide4;
    ModelRenderer Rope1;
    ModelRenderer Rope2;
    ModelRenderer Rope3;
    ModelRenderer Rope4;
    ModelRenderer Burner;
    ModelRenderer Balloon;
    ModelRenderer BalloonBottom;
    ModelRenderer BalloonAttachment1;
    ModelRenderer BalloonAttachment2;
  
  public ModelHotAirBalloon()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      BasketBottom = new ModelRenderer(this, 144, 0);
      BasketBottom.addBox(-8F, 14F, -8F, 16, 2, 16);
      BasketBottom.setRotationPoint(0F, 8F, 0F);
      BasketBottom.setTextureSize(256, 128);
      BasketBottom.mirror = true;
      setRotation(BasketBottom, 0F, 0F, 0F);
      BasketSide1 = new ModelRenderer(this, 208, 0);
      BasketSide1.addBox(8F, 0F, -8F, 2, 16, 16);
      BasketSide1.setRotationPoint(0F, 6F, 0F);
      BasketSide1.setTextureSize(256, 128);
      BasketSide1.mirror = true;
      setRotation(BasketSide1, 0F, 0F, 0F);
      BasketSide2 = new ModelRenderer(this, 208, 0);
      BasketSide2.addBox(-10F, 0F, -8F, 2, 16, 16);
      BasketSide2.setRotationPoint(0F, 6F, 0F);
      BasketSide2.setTextureSize(256, 128);
      BasketSide2.mirror = true;
      setRotation(BasketSide2, 0F, 0F, 0F);
      BasketSide3 = new ModelRenderer(this, 144, 18);
      BasketSide3.addBox(-8F, 0F, 8F, 16, 16, 2);
      BasketSide3.setRotationPoint(0F, 6F, 0F);
      BasketSide3.setTextureSize(256, 128);
      BasketSide3.mirror = true;
      setRotation(BasketSide3, 0F, 0F, 0F);
      BasketSide4 = new ModelRenderer(this, 144, 18);
      BasketSide4.addBox(-8F, 0F, -10F, 16, 16, 2);
      BasketSide4.setRotationPoint(0F, 6F, 0F);
      BasketSide4.setTextureSize(256, 128);
      BasketSide4.mirror = true;
      setRotation(BasketSide4, 0F, 0F, 0F);
      Rope1 = new ModelRenderer(this, 245, 0);
      Rope1.addBox(-1F, 0F, 0F, 1, 26, 1);
      Rope1.setRotationPoint(0F, -17F, 0F);
      Rope1.setTextureSize(256, 128);
      Rope1.mirror = true;
      setRotation(Rope1, 0.3926991F, 0.7853982F, 0F);
      Rope2 = new ModelRenderer(this, 245, 0);
      Rope2.addBox(0F, 0F, 0F, 1, 26, 1);
      Rope2.setRotationPoint(0F, -17F, 0F);
      Rope2.setTextureSize(256, 128);
      Rope2.mirror = true;
      setRotation(Rope2, 0.3926991F, -2.487094F, 0F);
      Rope3 = new ModelRenderer(this, 245, 0);
      Rope3.addBox(0F, 0F, 0F, 1, 26, 1);
      Rope3.setRotationPoint(0F, -17F, 0F);
      Rope3.setTextureSize(256, 128);
      Rope3.mirror = true;
      setRotation(Rope3, 0.3926991F, 2.487094F, 0F);
      Rope4 = new ModelRenderer(this, 245, 0);
      Rope4.addBox(0F, 0F, 0F, 1, 26, 1);
      Rope4.setRotationPoint(0F, -17F, 0F);
      Rope4.setTextureSize(256, 128);
      Rope4.mirror = true;
      setRotation(Rope4, 0.3926991F, -0.7853982F, 0F);
      Burner = new ModelRenderer(this, 144, 36);
      Burner.addBox(-3F, -4F, -3F, 6, 4, 6);
      Burner.setRotationPoint(0F, -17F, 0F);
      Burner.setTextureSize(256, 128);
      Burner.mirror = true;
      setRotation(Burner, 0F, 0F, 0F);
      Balloon = new ModelRenderer(this, 0, 0);
      Balloon.addBox(-24F, -66F, -24F, 48, 54, 48);
      Balloon.setRotationPoint(0F, -17F, 0F);
      Balloon.setTextureSize(256, 128);
      Balloon.mirror = true;
      setRotation(Balloon, 0F, 0F, 0F);
      BalloonBottom = new ModelRenderer(this, 176, 98);
      BalloonBottom.addBox(-10F, -12F, -10F, 20, 10, 20);
      BalloonBottom.setRotationPoint(0F, -17F, 0F);
      BalloonBottom.setTextureSize(256, 128);
      BalloonBottom.mirror = true;
      setRotation(BalloonBottom, 0F, 0F, 0F);
      BalloonAttachment1 = new ModelRenderer(this, 182, 18);
      BalloonAttachment1.addBox(-0.2F, -3F, 0F, 10, 1, 1);
      BalloonAttachment1.setRotationPoint(0F, -17F, 0F);
      BalloonAttachment1.setTextureSize(256, 128);
      BalloonAttachment1.mirror = true;
      setRotation(BalloonAttachment1, 0F, 0F, 0F);
      BalloonAttachment2 = new ModelRenderer(this, 182, 18);
      BalloonAttachment2.addBox(-9.8F, -3F, 0F, 10, 1, 1);
      BalloonAttachment2.setRotationPoint(0F, -17F, 0F);
      BalloonAttachment2.setTextureSize(256, 128);
      BalloonAttachment2.mirror = true;
      setRotation(BalloonAttachment2, 0F, 0F, 0F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BasketBottom.render(f5);
    BasketSide1.render(f5);
    BasketSide2.render(f5);
    BasketSide3.render(f5);
    BasketSide4.render(f5);
    Rope1.render(f5);
    Rope2.render(f5);
    Rope3.render(f5);
    Rope4.render(f5);
    Burner.render(f5);
    Balloon.render(f5);
    BalloonBottom.render(f5);
    BalloonAttachment1.render(f5);
    BalloonAttachment2.render(f5);
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
