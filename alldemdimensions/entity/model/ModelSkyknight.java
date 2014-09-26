package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSkyknight extends ModelBiped
{
  /*
    ModelRenderer bipedCloak;
    ModelRenderer bipedHead;
    ModelRenderer bipedBody;
    ModelRenderer bipedRightArm;
    ModelRenderer bipedLeftArm;
    ModelRenderer bipedRightLeg;
    ModelRenderer bipedLeftLeg;*/
  
  public ModelSkyknight()
  {
    textureWidth = 128;
    textureHeight = 128;
    
      bipedCloak = new ModelRenderer(this, 88, 0);
      bipedCloak.addBox(0F, 0F, 0F, 8, 19, 1);
      bipedCloak.setRotationPoint(-4F, 0F, 2F);
      bipedCloak.setTextureSize(128, 128);
      bipedCloak.mirror = true;
      setRotation(bipedCloak, 0.1487144F, 0F, 0F);
      bipedHead = new ModelRenderer(this, 56, 14);
      bipedHead.addBox(-4F, -8F, -4F, 8, 10, 8);
      bipedHead.setRotationPoint(0F, -2F, 0F);
      bipedHead.setTextureSize(128, 128);
      bipedHead.mirror = true;
      setRotation(bipedHead, 0F, 0F, 0F);
      bipedBody = new ModelRenderer(this, 16, 16);
      bipedBody.addBox(-4F, 0F, -2F, 8, 12, 4);
      bipedBody.setRotationPoint(0F, 0F, 0F);
      bipedBody.setTextureSize(128, 128);
      bipedBody.mirror = true;
      setRotation(bipedBody, 0F, 0F, 0F);
      bipedRightArm.mirror = true;
      bipedRightArm = new ModelRenderer(this, 40, 0);
      bipedRightArm.addBox(-3F, -2F, -2F, 4, 12, 4);
      bipedRightArm.setRotationPoint(-5F, 2F, 0F);
      bipedRightArm.setTextureSize(128, 128);
      bipedRightArm.mirror = true;
      setRotation(bipedRightArm, 0F, 0F, 0F);
      bipedRightArm.mirror = false;
      bipedLeftArm.mirror = true;
      bipedLeftArm = new ModelRenderer(this, 40, 16);
      bipedLeftArm.addBox(-1F, -2F, -2F, 4, 12, 4);
      bipedLeftArm.setRotationPoint(5F, 2F, 0F);
      bipedLeftArm.setTextureSize(128, 128);
      bipedLeftArm.mirror = true;
      setRotation(bipedLeftArm, 0F, 0F, 0F);
      bipedLeftArm.mirror = false;
      bipedRightLeg = new ModelRenderer(this, 0, 16);
      bipedRightLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
      bipedRightLeg.setRotationPoint(-2F, 12F, 0F);
      bipedRightLeg.setTextureSize(128, 128);
      bipedRightLeg.mirror = true;
      setRotation(bipedRightLeg, 0F, 0F, 0F);
      bipedLeftLeg = new ModelRenderer(this, 0, 16);
      bipedLeftLeg.addBox(-2F, 0F, -2F, 4, 12, 4);
      bipedLeftLeg.setRotationPoint(2F, 12F, 0F);
      bipedLeftLeg.setTextureSize(128, 128);
      bipedLeftLeg.mirror = true;
      setRotation(bipedLeftLeg, 0F, 0F, 0F);
  }
  
  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    //super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    bipedCloak.render(f5);
    bipedHead.render(f5);
    bipedBody.render(f5);
    bipedRightArm.render(f5);
    bipedLeftArm.render(f5);
    bipedRightLeg.render(f5);
    bipedLeftLeg.render(f5);
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
