package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**needs updated*/
public class ModelCloudGolem extends ModelBase
{
    ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RightShoulder;
    ModelRenderer LeftShoulder;
    ModelRenderer RightArm;
    ModelRenderer LeftArm;
    ModelRenderer Core;
    ModelRenderer BackCloud;
    ModelRenderer FrontCloud;
    ModelRenderer RightCloud;
    ModelRenderer LeftCloud;
  
  public ModelCloudGolem()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-8F, -8F, -4F, 16, 16, 16);
      Head.setRotationPoint(7F, -14F, -1F);
      Head.setTextureSize(256, 128);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      Body = new ModelRenderer(this, 131, 40);
      Body.addBox(0F, 0F, -2F, 42, 28, 20);
      Body.setRotationPoint(-14F, -12F, 0F);
      Body.setTextureSize(256, 128);
      Body.mirror = true;
      setRotation(Body, 0F, 0F, 0F);
      RightShoulder = new ModelRenderer(this, 130, 0);
      RightShoulder.addBox(-20F, -9F, -10F, 25, 18, 18);
      RightShoulder.setRotationPoint(-14F, -5F, 7F);
      RightShoulder.setTextureSize(256, 128);
      RightShoulder.mirror = true;
      setRotation(RightShoulder, 0F, 0F, 0F);
      //LeftShoulder.mirror = true;
      LeftShoulder = new ModelRenderer(this, 130, 0);
      LeftShoulder.addBox(-4F, -10F, -10F, 25, 18, 18);
      LeftShoulder.setRotationPoint(27F, -5F, 7F);
      LeftShoulder.setTextureSize(256, 128);
      LeftShoulder.mirror = true;
      setRotation(LeftShoulder, 0F, 0F, 0F);
      LeftShoulder.mirror = false;
      RightArm = new ModelRenderer(this, 0, 34);
      RightArm.addBox(-18F, -29F, -8F, 16, 44, 16);
      RightArm.setRotationPoint(-14F, -5F, 7F);
      RightArm.setTextureSize(256, 128);
      RightArm.mirror = true;
      setRotation(RightArm, 1.570796F, 0F, 0F);
      //LeftArm.mirror = true;
      LeftArm = new ModelRenderer(this, 0, 34);
      LeftArm.addBox(3F, -29F, -7F, 16, 44, 16);
      LeftArm.setRotationPoint(27F, -5F, 7F);
      LeftArm.setTextureSize(256, 128);
      LeftArm.mirror = true;
      setRotation(LeftArm, 1.570796F, 0F, 0F);
      LeftArm.mirror = false;
      Core = new ModelRenderer(this, 171, 92);
      Core.addBox(-6F, -6F, -7F, 26, 14, 16);
      Core.setRotationPoint(0F, 26F, 7F);
      Core.setTextureSize(256, 128);
      Core.mirror = true;
      setRotation(Core, 0F, 0F, 0F);
      BackCloud = new ModelRenderer(this, 69, 92);
      BackCloud.addBox(-9F, -7F, 18F, 34, 18, 16);
      BackCloud.setRotationPoint(0F, 26F, 7F);
      BackCloud.setTextureSize(256, 128);
      BackCloud.mirror = true;
      setRotation(BackCloud, 0F, 0F, 0F);
      FrontCloud = new ModelRenderer(this, 69, 92);
      FrontCloud.addBox(-9F, -7F, -30F, 34, 18, 16);
      FrontCloud.setRotationPoint(0F, 26F, 7F);
      FrontCloud.setTextureSize(256, 128);
      FrontCloud.mirror = true;
      setRotation(FrontCloud, 0F, 0F, 0F);
      RightCloud = new ModelRenderer(this, 69, 92);
      RightCloud.addBox(-18F, -7F, -27F, 34, 18, 16);
      RightCloud.setRotationPoint(0F, 26F, 7F);
      RightCloud.setTextureSize(256, 128);
      RightCloud.mirror = true;
      setRotation(RightCloud, 0F, 1.570796F, 0F);
      LeftCloud = new ModelRenderer(this, 69, 92);
      LeftCloud.addBox(-18F, -7F, 27F, 34, 18, 16);
      LeftCloud.setRotationPoint(0F, 26F, 7F);
      LeftCloud.setTextureSize(256, 128);
      LeftCloud.mirror = true;
      setRotation(LeftCloud, 0F, 1.570796F, -0.0174533F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Head.render(f5);
    Body.render(f5);
    RightShoulder.render(f5);
    LeftShoulder.render(f5);
    RightArm.render(f5);
    LeftArm.render(f5);
    Core.render(f5);
    BackCloud.render(f5);
    FrontCloud.render(f5);
    RightCloud.render(f5);
    LeftCloud.render(f5);
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
