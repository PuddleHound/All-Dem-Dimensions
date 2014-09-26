package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**placeholder (or final?) model*/
public class ModelBee extends ModelBase
{

    ModelRenderer Abdomen;
    ModelRenderer Thorax;
    ModelRenderer Head;
    ModelRenderer LeftWing;
    ModelRenderer RightWing;
    ModelRenderer Stinger;
    ModelRenderer RightHindLeg;
    ModelRenderer LeftHindLeg;
    ModelRenderer RightFrontLeg;
    ModelRenderer LeftFrontLeg;
    ModelRenderer RightMidLeg;
    ModelRenderer LeftMidLeg;
    ModelRenderer RightAntennaBottom;
    ModelRenderer RightAntennaTop;
    ModelRenderer LeftAntennaBottom;
    ModelRenderer LeftAntennaTop;
  
  public ModelBee()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Abdomen = new ModelRenderer(this, 0, 14);
      Abdomen.addBox(-4F, -2F, 2F, 7, 4, 6);
      Abdomen.setRotationPoint(1F, 19F, -2F);
      Abdomen.setTextureSize(64, 32);
      Abdomen.mirror = true;
      setRotation(Abdomen, 0F, 0F, 0F);
      Thorax = new ModelRenderer(this, 0, 7);
      Thorax.addBox(-2F, -1F, -3F, 3, 2, 5);
      Thorax.setRotationPoint(1F, 19F, -2F);
      Thorax.setTextureSize(64, 32);
      Thorax.mirror = true;
      setRotation(Thorax, 0F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 0);
      Head.addBox(-2.5F, -2F, -2.5F, 5, 4, 3);
      Head.setRotationPoint(0.5F, 19F, -5F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, 0F, 0F, 0F);
      LeftWing = new ModelRenderer(this, 22, 0);
      LeftWing.addBox(1F, 0F, -3F, 8, 1, 5);
      LeftWing.setRotationPoint(1F, 19F, -2F);
      LeftWing.setTextureSize(64, 32);
      LeftWing.mirror = true;
      setRotation(LeftWing, 0F, 0F, -0.5585054F);
      RightWing = new ModelRenderer(this, 22, 0);
      RightWing.addBox(-9F, 0F, -3F, 8, 1, 5);
      RightWing.setRotationPoint(1F, 19F, -2F);
      RightWing.setTextureSize(64, 32);
      RightWing.mirror = true;
      setRotation(RightWing, 0F, 0F, 0.5585054F);
      Stinger = new ModelRenderer(this, 0, 24);
      Stinger.addBox(-1F, 0F, 8F, 1, 1, 2);
      Stinger.setRotationPoint(1F, 19F, -2F);
      Stinger.setTextureSize(64, 32);
      Stinger.mirror = true;
      setRotation(Stinger, 0F, 0F, 0F);
      RightHindLeg = new ModelRenderer(this, 16, 9);
      RightHindLeg.addBox(1F, 1F, 0F, 1, 4, 1);
      RightHindLeg.setRotationPoint(1F, 19F, -2F);
      RightHindLeg.setTextureSize(64, 32);
      RightHindLeg.mirror = true;
      setRotation(RightHindLeg, 0.3316126F, 0F, -0.1919862F);
      LeftHindLeg = new ModelRenderer(this, 16, 9);
      LeftHindLeg.addBox(-3F, 1F, 0F, 1, 4, 1);
      LeftHindLeg.setRotationPoint(1F, 19F, -2F);
      LeftHindLeg.setTextureSize(64, 32);
      LeftHindLeg.mirror = true;
      setRotation(LeftHindLeg, 0.3316126F, 0F, 0.1919862F);
      RightFrontLeg = new ModelRenderer(this, 16, 9);
      RightFrontLeg.addBox(1F, 1F, -2F, 1, 4, 1);
      RightFrontLeg.setRotationPoint(1F, 19F, -2F);
      RightFrontLeg.setTextureSize(64, 32);
      RightFrontLeg.mirror = true;
      setRotation(RightFrontLeg, -0.3316126F, 0F, -0.1919862F);
      LeftFrontLeg = new ModelRenderer(this, 16, 9);
      LeftFrontLeg.addBox(-3F, 1F, -2F, 1, 4, 1);
      LeftFrontLeg.setRotationPoint(1F, 19F, -2F);
      LeftFrontLeg.setTextureSize(64, 32);
      LeftFrontLeg.mirror = true;
      setRotation(LeftFrontLeg, -0.3316126F, 0F, 0.1919862F);
      RightMidLeg = new ModelRenderer(this, 16, 9);
      RightMidLeg.addBox(1F, 1F, -1F, 1, 4, 1);
      RightMidLeg.setRotationPoint(1F, 19F, -2F);
      RightMidLeg.setTextureSize(64, 32);
      RightMidLeg.mirror = true;
      setRotation(RightMidLeg, 0F, 0F, -0.3316126F);
      LeftMidLeg = new ModelRenderer(this, 16, 9);
      LeftMidLeg.addBox(-3F, 1F, -1F, 1, 4, 1);
      LeftMidLeg.setRotationPoint(1F, 19F, -2F);
      LeftMidLeg.setTextureSize(64, 32);
      LeftMidLeg.mirror = true;
      setRotation(LeftMidLeg, 0F, 0F, 0.3316126F);
      RightAntennaBottom = new ModelRenderer(this, 16, 2);
      RightAntennaBottom.addBox(-1.5F, -6F, -1.5F, 1, 4, 1);
      RightAntennaBottom.setRotationPoint(0.5F, 19F, -5F);
      RightAntennaBottom.setTextureSize(64, 32);
      RightAntennaBottom.mirror = true;
      setRotation(RightAntennaBottom, 0F, 0F, 0F);
      RightAntennaTop = new ModelRenderer(this, 16, 0);
      RightAntennaTop.addBox(-3.5F, -6F, -1.5F, 2, 1, 1);
      RightAntennaTop.setRotationPoint(0.5F, 19F, -5F);
      RightAntennaTop.setTextureSize(64, 32);
      RightAntennaTop.mirror = true;
      setRotation(RightAntennaTop, 0F, 0F, 0F);
      LeftAntennaBottom = new ModelRenderer(this, 16, 2);
      LeftAntennaBottom.addBox(1F, -6F, -1.5F, 1, 4, 1);
      LeftAntennaBottom.setRotationPoint(0.5F, 19F, -5F);
      LeftAntennaBottom.setTextureSize(64, 32);
      LeftAntennaBottom.mirror = true;
      setRotation(LeftAntennaBottom, 0F, 0F, 0F);
      LeftAntennaTop = new ModelRenderer(this, 16, 0);
      LeftAntennaTop.addBox(2F, -6F, -1.5F, 2, 1, 1);
      LeftAntennaTop.setRotationPoint(0.5F, 19F, -5F);
      LeftAntennaTop.setTextureSize(64, 32);
      LeftAntennaTop.mirror = true;
      setRotation(LeftAntennaTop, 0F, 0F, 0F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Abdomen.render(f5);
    Thorax.render(f5);
    Head.render(f5);
    LeftWing.render(f5);
    RightWing.render(f5);
    Stinger.render(f5);
    RightHindLeg.render(f5);
    LeftHindLeg.render(f5);
    RightFrontLeg.render(f5);
    LeftFrontLeg.render(f5);
    RightMidLeg.render(f5);
    LeftMidLeg.render(f5);
    RightAntennaBottom.render(f5);
    RightAntennaTop.render(f5);
    LeftAntennaBottom.render(f5);
    LeftAntennaTop.render(f5);
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
	LeftWing.rotateAngleZ = MathHelper.cos(f * 1.33324F) * 2.0F * f1 * 0.5F * -1F;
	RightWing.rotateAngleZ = MathHelper.cos(f * 1.33324F) * 2.0F * f1 * 0.5F;
  }

}
