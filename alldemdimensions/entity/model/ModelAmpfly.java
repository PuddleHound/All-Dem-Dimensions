package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**the placeholder model*/
public class ModelAmpfly extends ModelBase
{

    ModelRenderer Abdomen;
    ModelRenderer Thorax;
    ModelRenderer Head;
    ModelRenderer LeftFrontWing;
    ModelRenderer RightFrontWing;
    ModelRenderer LeftFrontLeg;
    ModelRenderer LeftMidLeg;
    ModelRenderer LeftHindLeg;
    ModelRenderer RightFrontLeg;
    ModelRenderer RightMidLeg;
    ModelRenderer RightHindLeg;
    ModelRenderer LeftHindWing;
    ModelRenderer RightHindWing;
    ModelRenderer LeftEye;
    ModelRenderer RightEye;
  
  public ModelAmpfly()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Abdomen = new ModelRenderer(this, 6, 11);
      Abdomen.addBox(-1.5F, -1.5F, 0F, 3, 3, 12);
      Abdomen.setRotationPoint(0F, 17F, 0F);
      Abdomen.setTextureSize(64, 32);
      Abdomen.mirror = true;
      setRotation(Abdomen, -0.4886922F, 0F, 0F);
      Thorax = new ModelRenderer(this, 0, 11);
      Thorax.addBox(-1F, -1F, 0F, 4, 4, 5);
      Thorax.setRotationPoint(-1F, 14F, -4F);
      Thorax.setTextureSize(64, 32);
      Thorax.mirror = true;
      setRotation(Thorax, -0.4886922F, 0F, 0F);
      Head = new ModelRenderer(this, 6, 0);
      Head.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3);
      Head.setRotationPoint(0F, 15F, -4F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, -0.2792527F, 0F, 0F);
      LeftFrontWing = new ModelRenderer(this, 0, 6);
      LeftFrontWing.addBox(0F, 0F, -1F, 14, 1, 4);
      LeftFrontWing.setRotationPoint(1F, 15F, -2F);
      LeftFrontWing.setTextureSize(64, 32);
      LeftFrontWing.mirror = true;
      setRotation(LeftFrontWing, -0.4014257F, 0.2617994F, 0F);
      RightFrontWing = new ModelRenderer(this, 0, 6);
      RightFrontWing.addBox(-14F, 0F, -1F, 14, 1, 4);
      RightFrontWing.setRotationPoint(-1F, 15F, -2F);
      RightFrontWing.setTextureSize(64, 32);
      RightFrontWing.mirror = true;
      setRotation(RightFrontWing, -0.4014257F, -0.2617994F, 0F);
      LeftFrontLeg = new ModelRenderer(this, 18, 0);
      LeftFrontLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      LeftFrontLeg.setRotationPoint(0F, 17F, -3F);
      LeftFrontLeg.setTextureSize(64, 32);
      LeftFrontLeg.mirror = true;
      setRotation(LeftFrontLeg, -0.5585054F, 0F, -0.4537856F);
      LeftMidLeg = new ModelRenderer(this, 18, 0);
      LeftMidLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      LeftMidLeg.setRotationPoint(0F, 17.5F, -2.5F);
      LeftMidLeg.setTextureSize(64, 32);
      LeftMidLeg.mirror = true;
      setRotation(LeftMidLeg, -0.0349066F, 0F, -0.4537856F);
      LeftHindLeg = new ModelRenderer(this, 18, 0);
      LeftHindLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      LeftHindLeg.setRotationPoint(0F, 17.5F, -2F);
      LeftHindLeg.setTextureSize(64, 32);
      LeftHindLeg.mirror = true;
      setRotation(LeftHindLeg, 0.2617994F, -0.1570796F, -0.4537856F);
      RightFrontLeg = new ModelRenderer(this, 18, 0);
      RightFrontLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      RightFrontLeg.setRotationPoint(0F, 17F, -3F);
      RightFrontLeg.setTextureSize(64, 32);
      RightFrontLeg.mirror = true;
      setRotation(RightFrontLeg, -0.5585054F, 0F, 0.4537856F);
      RightMidLeg = new ModelRenderer(this, 18, 0);
      RightMidLeg.addBox(-1F, 0F, -0.5F, 1, 4, 1);
      RightMidLeg.setRotationPoint(0F, 17.5F, -2.5F);
      RightMidLeg.setTextureSize(64, 32);
      RightMidLeg.mirror = true;
      setRotation(RightMidLeg, -0.0349066F, 0F, 0.4537856F);
      RightHindLeg = new ModelRenderer(this, 18, 0);
      RightHindLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      RightHindLeg.setRotationPoint(0F, 17.5F, -2F);
      RightHindLeg.setTextureSize(64, 32);
      RightHindLeg.mirror = true;
      setRotation(RightHindLeg, 0.2617994F, -0.1570796F, 0.4537856F);
      LeftHindWing = new ModelRenderer(this, 0, 6);
      LeftHindWing.addBox(0F, 0F, 0F, 14, 1, 4);
      LeftHindWing.setRotationPoint(1F, 15F, -2F);
      LeftHindWing.setTextureSize(64, 32);
      LeftHindWing.mirror = true;
      setRotation(LeftHindWing, -0.4014257F, -0.2617994F, 0F);
      RightHindWing = new ModelRenderer(this, 0, 6);
      RightHindWing.addBox(-14F, 0F, 0F, 14, 1, 4);
      RightHindWing.setRotationPoint(-1F, 15F, -2F);
      RightHindWing.setTextureSize(64, 32);
      RightHindWing.mirror = true;
      setRotation(RightHindWing, -0.4014257F, 0.2617994F, 0F);
      LeftEye = new ModelRenderer(this, 0, 0);
      LeftEye.addBox(1F, -1F, -3F, 1, 2, 2);
      LeftEye.setRotationPoint(0F, 15F, -4F);
      LeftEye.setTextureSize(64, 32);
      LeftEye.mirror = true;
      setRotation(LeftEye, -0.2792527F, 0F, 0F);
      RightEye = new ModelRenderer(this, 0, 0);
      RightEye.addBox(-2F, -1F, -3F, 1, 2, 2);
      RightEye.setRotationPoint(0F, 15F, -4F);
      RightEye.setTextureSize(64, 32);
      RightEye.mirror = true;
      setRotation(RightEye, -0.2792527F, 0F, 0F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Abdomen.render(f5);
    Thorax.render(f5);
    Head.render(f5);
    LeftFrontWing.render(f5);
    RightFrontWing.render(f5);
    LeftFrontLeg.render(f5);
    LeftMidLeg.render(f5);
    LeftHindLeg.render(f5);
    RightFrontLeg.render(f5);
    RightMidLeg.render(f5);
    RightHindLeg.render(f5);
    LeftHindWing.render(f5);
    RightHindWing.render(f5);
    LeftEye.render(f5);
    RightEye.render(f5);
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
	LeftFrontWing.rotateAngleZ = (MathHelper.cos(f * 1.986F) * 2.0F * f1 * 0.5F * -1F) - 0.1F;
	LeftHindWing.rotateAngleZ = (MathHelper.cos(f * 1.986F) * 2.0F * f1 * 0.5F * -1F) + 0.1F;
	RightFrontWing.rotateAngleZ = (MathHelper.cos(f * 1.986F) * 2.0F * f1 * 0.5F) - 0.1F;
	RightHindWing.rotateAngleZ = (MathHelper.cos(f * 1.986F) * 2.0F * f1 * 0.5F) + 0.1F;
  }

}
