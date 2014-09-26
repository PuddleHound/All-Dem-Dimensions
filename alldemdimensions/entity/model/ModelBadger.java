package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBadger extends ModelQuadruped
{
    /*ModelRenderer Head;
    ModelRenderer Body;
    ModelRenderer RightHindLeg;//leg1
    ModelRenderer LeftHindLeg;//leg2
    ModelRenderer RightFrontLeg;//leg3
    ModelRenderer LeftFrontLeg;//leg4
	*/
    ModelRenderer Tail;
    ModelRenderer RightEar;
    ModelRenderer LeftEar;
  
  public ModelBadger()
  {
	super(0, 0F);
    textureWidth = 128;
    textureHeight = 64;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-3F, -2F, -9F, 6, 4, 9);
      head.setRotationPoint(-1F, 20F, -6F);
      head.setTextureSize(128, 64);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      body = new ModelRenderer(this, 42, 0);
      body.addBox(-4F, -3F, -3F, 8, 11, 5);
      body.setRotationPoint(-1F, 19F, -3F);
      body.setTextureSize(128, 64);
      body.mirror = true;
      setRotation(body, 1.570796F, 0F, 0F);
      leg1 = new ModelRenderer(this, 0, 18);
      leg1.addBox(-1F, 0F, -1F, 2, 2, 2);
      leg1.setRotationPoint(-2.5F, 22F, 4F);
      leg1.setTextureSize(128, 64);
      leg1.mirror = true;
      setRotation(leg1, 0F, 0F, 0F);
      leg2 = new ModelRenderer(this, 0, 18);
      leg2.addBox(-1F, 0F, -1F, 2, 2, 2);
      leg2.setRotationPoint(0.5F, 22F, 4F);
      leg2.setTextureSize(128, 64);
      leg2.mirror = true;
      setRotation(leg2, 0F, 0F, 0F);
      leg3 = new ModelRenderer(this, 0, 18);
      leg3.addBox(-1F, 0F, -1F, 2, 2, 2);
      leg3.setRotationPoint(-2.5F, 22F, -4F);
      leg3.setTextureSize(128, 64);
      leg3.mirror = true;
      setRotation(leg3, 0F, 0F, 0F);
      leg4 = new ModelRenderer(this, 0, 18);
      leg4.addBox(-1F, 0F, -1F, 2, 2, 2);
      leg4.setRotationPoint(0.5F, 22F, -4F);
      leg4.setTextureSize(128, 64);
      leg4.mirror = true;
      setRotation(leg4, 0F, 0F, 0F);
      Tail = new ModelRenderer(this, 9, 18);
      Tail.addBox(-1F, 0F, -1F, 2, 3, 2);
      Tail.setRotationPoint(-1F, 19F, 4F);
      Tail.setTextureSize(128, 64);
      Tail.mirror = true;
      setRotation(Tail, 1.130069F, 0F, 0F);
      RightEar = new ModelRenderer(this, 16, 14);
      RightEar.addBox(-3F, -4F, -1F, 2, 2, 1);
      RightEar.setRotationPoint(-1F, 20F, -6F);
      RightEar.setTextureSize(128, 64);
      RightEar.mirror = true;
      setRotation(RightEar, 0F, 0F, 0F);
      LeftEar = new ModelRenderer(this, 16, 14);
      LeftEar.addBox(1F, -4F, -1F, 2, 2, 1);
      LeftEar.setRotationPoint(-1F, 20F, -6F);
      LeftEar.setTextureSize(128, 64);
      LeftEar.mirror = true;
      setRotation(LeftEar, 0F, 0F, 0F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    body.render(f5);
    leg1.render(f5);
    leg2.render(f5);
    leg3.render(f5);
    leg4.render(f5);
    Tail.render(f5);
    RightEar.render(f5);
    LeftEar.render(f5);
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
	RightEar.rotateAngleX = LeftEar.rotateAngleX = head.rotateAngleX;
	RightEar.rotateAngleY = LeftEar.rotateAngleY = head.rotateAngleY;
	RightEar.rotateAngleZ = LeftEar.rotateAngleZ = head.rotateAngleZ;
  }

}
