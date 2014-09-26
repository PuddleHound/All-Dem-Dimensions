package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelButterfly extends ModelBase
{
    ModelRenderer Abdomen;
    ModelRenderer Thorax;
    ModelRenderer Head;
    ModelRenderer LeftFrontWing;
    ModelRenderer LeftWingAttachment;
    ModelRenderer LeftHindWing;
    ModelRenderer RightWingAttachment;
    ModelRenderer RightFrontWing;
    ModelRenderer RightHindWing;
    ModelRenderer RightAntenna;
    ModelRenderer RightAntennaTop;
    ModelRenderer LeftAntenna;
    ModelRenderer LeftAtennaTop;
    ModelRenderer LeftFrontLeg;
    ModelRenderer LeftMidLeg;
    ModelRenderer LeftHindLeg;
    ModelRenderer RightFrontLeg;
    ModelRenderer RightMidLeg;
    ModelRenderer RightHindLeg;
  
  public ModelButterfly()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      Abdomen = new ModelRenderer(this, 0, 22);
      Abdomen.addBox(-1.5F, -1.5F, 0F, 3, 3, 6);
      Abdomen.setRotationPoint(0F, 17F, 0F);
      Abdomen.setTextureSize(64, 32);
      Abdomen.mirror = true;
      setRotation(Abdomen, -0.4886922F, 0F, 0F);
      Thorax = new ModelRenderer(this, 0, 15);
      Thorax.addBox(-1F, -1F, 0F, 2, 2, 5);
      Thorax.setRotationPoint(0F, 15F, -4F);
      Thorax.setTextureSize(64, 32);
      Thorax.mirror = true;
      setRotation(Thorax, -0.4886922F, 0F, 0F);
      Head = new ModelRenderer(this, 0, 9);
      Head.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 3);
      Head.setRotationPoint(0F, 15F, -4F);
      Head.setTextureSize(64, 32);
      Head.mirror = true;
      setRotation(Head, -0.2792527F, 0F, 0F);
      LeftFrontWing = new ModelRenderer(this, 0, 0);
      LeftFrontWing.addBox(2F, 0F, -4F, 8, 1, 8);
      LeftFrontWing.setRotationPoint(1F, 15F, -2F);
      LeftFrontWing.setTextureSize(64, 32);
      LeftFrontWing.mirror = true;
      setRotation(LeftFrontWing, -0.4014257F, 0F, 0F);
      LeftWingAttachment = new ModelRenderer(this, 18, 9);
      LeftWingAttachment.addBox(0F, 0F, -1F, 2, 1, 3);
      LeftWingAttachment.setRotationPoint(1F, 15F, -2F);
      LeftWingAttachment.setTextureSize(64, 32);
      LeftWingAttachment.mirror = true;
      setRotation(LeftWingAttachment, -0.4014257F, 0F, 0F);
      LeftHindWing = new ModelRenderer(this, 32, 0);
      LeftHindWing.addBox(3F, 0F, 4F, 6, 1, 8);
      LeftHindWing.setRotationPoint(1F, 15F, -2F);
      LeftHindWing.setTextureSize(64, 32);
      LeftHindWing.mirror = true;
      setRotation(LeftHindWing, -0.4014257F, 0F, 0F);
      RightWingAttachment = new ModelRenderer(this, 18, 9);
      RightWingAttachment.addBox(-2F, 0F, -1F, 2, 1, 3);
      RightWingAttachment.setRotationPoint(-1F, 15F, -2F);
      RightWingAttachment.setTextureSize(64, 32);
      RightWingAttachment.mirror = true;
      setRotation(RightWingAttachment, -0.4014257F, 0F, 0F);
      RightFrontWing = new ModelRenderer(this, 0, 0);
      RightFrontWing.addBox(-10F, 0F, -4F, 8, 1, 8);
      RightFrontWing.setRotationPoint(-1F, 15F, -2F);
      RightFrontWing.setTextureSize(64, 32);
      RightFrontWing.mirror = true;
      setRotation(RightFrontWing, -0.4014257F, 0F, 0F);
      RightHindWing = new ModelRenderer(this, 32, 0);
      RightHindWing.addBox(-9F, 0F, 4F, 6, 1, 8);
      RightHindWing.setRotationPoint(-1F, 15F, -2F);
      RightHindWing.setTextureSize(64, 32);
      RightHindWing.mirror = true;
      setRotation(RightHindWing, -0.4014257F, 0F, 0F);
      RightAntenna = new ModelRenderer(this, 12, 12);
      RightAntenna.addBox(-1F, -7F, -2F, 1, 6, 1);
      RightAntenna.setRotationPoint(0F, 15F, -4F);
      RightAntenna.setTextureSize(64, 32);
      RightAntenna.mirror = true;
      setRotation(RightAntenna, -0.122173F, 0F, -0.4537856F);
      RightAntennaTop = new ModelRenderer(this, 12, 9);
      RightAntennaTop.addBox(-1F, -7F, -3F, 1, 1, 2);
      RightAntennaTop.setRotationPoint(0F, 15F, -4F);
      RightAntennaTop.setTextureSize(64, 32);
      RightAntennaTop.mirror = true;
      setRotation(RightAntennaTop, -0.122173F, 0F, -0.4537856F);
      LeftAntenna = new ModelRenderer(this, 12, 12);
      LeftAntenna.addBox(0F, -7F, -2F, 1, 6, 1);
      LeftAntenna.setRotationPoint(0F, 15F, -4F);
      LeftAntenna.setTextureSize(64, 32);
      LeftAntenna.mirror = true;
      setRotation(LeftAntenna, -0.122173F, 0F, 0.4537856F);
      LeftAtennaTop = new ModelRenderer(this, 12, 9);
      LeftAtennaTop.addBox(0F, -7F, -3F, 1, 1, 2);
      LeftAtennaTop.setRotationPoint(0F, 15F, -4F);
      LeftAtennaTop.setTextureSize(64, 32);
      LeftAtennaTop.mirror = true;
      setRotation(LeftAtennaTop, -0.122173F, 0F, 0.4537856F);
      LeftFrontLeg = new ModelRenderer(this, 14, 21);
      LeftFrontLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      LeftFrontLeg.setRotationPoint(0F, 16F, -3F);
      LeftFrontLeg.setTextureSize(64, 32);
      LeftFrontLeg.mirror = true;
      setRotation(LeftFrontLeg, -0.5585054F, 0F, -0.4537856F);
      LeftMidLeg = new ModelRenderer(this, 14, 21);
      LeftMidLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      LeftMidLeg.setRotationPoint(0F, 16.5F, -2.5F);
      LeftMidLeg.setTextureSize(64, 32);
      LeftMidLeg.mirror = true;
      setRotation(LeftMidLeg, -0.0349066F, 0F, -0.4537856F);
      LeftHindLeg = new ModelRenderer(this, 14, 21);
      LeftHindLeg.addBox(0F, 0F, 0F, 1, 4, 1);
      LeftHindLeg.setRotationPoint(0F, 16.5F, -2F);
      LeftHindLeg.setTextureSize(64, 32);
      LeftHindLeg.mirror = true;
      setRotation(LeftHindLeg, 0.2617994F, -0.1570796F, -0.4537856F);
      RightFrontLeg = new ModelRenderer(this, 14, 21);
      RightFrontLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      RightFrontLeg.setRotationPoint(0F, 16F, -3F);
      RightFrontLeg.setTextureSize(64, 32);
      RightFrontLeg.mirror = true;
      setRotation(RightFrontLeg, -0.5585054F, 0F, 0.4537856F);
      RightMidLeg = new ModelRenderer(this, 14, 21);
      RightMidLeg.addBox(-1F, 0F, -0.5F, 1, 4, 1);
      RightMidLeg.setRotationPoint(0F, 16.5F, -2.5F);
      RightMidLeg.setTextureSize(64, 32);
      RightMidLeg.mirror = true;
      setRotation(RightMidLeg, -0.0349066F, 0F, 0.4537856F);
      RightHindLeg = new ModelRenderer(this, 14, 21);
      RightHindLeg.addBox(-1F, 0F, 0F, 1, 4, 1);
      RightHindLeg.setRotationPoint(0F, 16.5F, -2F);
      RightHindLeg.setTextureSize(64, 32);
      RightHindLeg.mirror = true;
      setRotation(RightHindLeg, 0.2617994F, -0.1570796F, 0.4537856F);
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
    LeftWingAttachment.render(f5);
    LeftHindWing.render(f5);
    RightWingAttachment.render(f5);
    RightFrontWing.render(f5);
    RightHindWing.render(f5);
    RightAntenna.render(f5);
    RightAntennaTop.render(f5);
    LeftAntenna.render(f5);
    LeftAtennaTop.render(f5);
    LeftFrontLeg.render(f5);
    LeftMidLeg.render(f5);
    LeftHindLeg.render(f5);
    RightFrontLeg.render(f5);
    RightMidLeg.render(f5);
    RightHindLeg.render(f5);
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
		LeftWingAttachment.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F * -1F;
		LeftFrontWing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F * -1F;
		LeftHindWing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F * -1F;
		RightWingAttachment.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		RightFrontWing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
		RightHindWing.rotateAngleZ = MathHelper.cos(f * 0.6662F) * 2.0F * f1 * 0.5F;
	}

}
