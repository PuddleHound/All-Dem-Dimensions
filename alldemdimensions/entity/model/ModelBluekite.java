package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

/**needs updated*/
public class ModelBluekite extends ModelBase
{
    ModelRenderer Body;
    ModelRenderer neck;
    ModelRenderer head;
    ModelRenderer Left_middle_leg;
    ModelRenderer Left_upper_leg;
    ModelRenderer right_upper_leg;
    ModelRenderer right_middle_leg;
    ModelRenderer left_lower_leg;
    ModelRenderer right_lower_leg;
    ModelRenderer left_foot;
    ModelRenderer right_foot;
    ModelRenderer left_left_toe;
    ModelRenderer right_left_toe;
    ModelRenderer left_right_toe;
    ModelRenderer right_right_toe;
    ModelRenderer tail;
    ModelRenderer left_wing;
    ModelRenderer right_wing;
    ModelRenderer upper_beak;
    ModelRenderer lower_beak;
    ModelRenderer Piece1;
  
  public ModelBluekite()
  {
    textureWidth = 256;
    textureHeight = 128;
    
      Body = new ModelRenderer(this, 92, 47);
      Body.addBox(-7F, 0F, -4F, 14, 20, 13);
      Body.setRotationPoint(0F, -5F, 2F);
      Body.setTextureSize(256, 128);
      Body.mirror = true;
      setRotation(Body, -0.9599311F, 0F, 0F);
      neck = new ModelRenderer(this, 102, 29);
      neck.addBox(-4F, -3F, 0F, 8, 7, 10);
      neck.setRotationPoint(0F, -2F, 2F);
      neck.setTextureSize(256, 128);
      neck.mirror = true;
      setRotation(neck, 1.041001F, 0F, 0F);
      head = new ModelRenderer(this, 99, 6);
      head.addBox(-5F, -16F, 1F, 10, 10, 11);
      head.setRotationPoint(0F, -2F, 2F);
      head.setTextureSize(256, 128);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      Left_middle_leg = new ModelRenderer(this, 71, 44);
      Left_middle_leg.addBox(0F, 0F, 1.2F, 4, 9, 4);
      Left_middle_leg.setRotationPoint(2.5F, 6F, -3F);
      Left_middle_leg.setTextureSize(256, 128);
      Left_middle_leg.mirror = true;
      setRotation(Left_middle_leg, -0.4363323F, 0F, 0F);
      Left_upper_leg = new ModelRenderer(this, 70, 35);
      Left_upper_leg.addBox(0F, 0F, 0F, 4, 2, 5);
      Left_upper_leg.setRotationPoint(2.5F, 6F, -3F);
      Left_upper_leg.setTextureSize(256, 128);
      Left_upper_leg.mirror = true;
      setRotation(Left_upper_leg, 0F, 0F, 0F);
      right_upper_leg = new ModelRenderer(this, 149, 35);
      right_upper_leg.addBox(0F, 0F, 0F, 4, 2, 5);
      right_upper_leg.setRotationPoint(-6.5F, 6F, -3F);
      right_upper_leg.setTextureSize(256, 128);
      right_upper_leg.mirror = true;
      setRotation(right_upper_leg, 0F, 0F, 0F);
      right_middle_leg = new ModelRenderer(this, 150, 44);
      right_middle_leg.addBox(0F, 0F, 1.2F, 4, 9, 4);
      right_middle_leg.setRotationPoint(-6.5F, 6F, -3F);
      right_middle_leg.setTextureSize(256, 128);
      right_middle_leg.mirror = true;
      setRotation(right_middle_leg, -0.4363323F, 0F, 0F);
      left_lower_leg = new ModelRenderer(this, 71, 59);
      left_lower_leg.addBox(-2F, 0F, -2F, 4, 12, 4);
      left_lower_leg.setRotationPoint(4.5F, 14F, -4F);
      left_lower_leg.setTextureSize(256, 128);
      left_lower_leg.mirror = true;
      setRotation(left_lower_leg, 0.6283185F, 0F, 0F);
      right_lower_leg = new ModelRenderer(this, 150, 58);
      right_lower_leg.addBox(-2F, 0F, -2F, 4, 12, 4);
      right_lower_leg.setRotationPoint(-4.5F, 14F, -4F);
      right_lower_leg.setTextureSize(256, 128);
      right_lower_leg.mirror = true;
      setRotation(right_lower_leg, 0.6283185F, 0F, 0F);
      left_foot = new ModelRenderer(this, 69, 77);
      left_foot.addBox(-2F, 8F, 5F, 4, 2, 5);
      left_foot.setRotationPoint(4.5F, 14F, -4F);
      left_foot.setTextureSize(256, 128);
      left_foot.mirror = true;
      setRotation(left_foot, 0F, 0F, 0F);
      right_foot = new ModelRenderer(this, 150, 76);
      right_foot.addBox(-2F, 8F, 5F, 4, 2, 5);
      right_foot.setRotationPoint(-4.5F, 14F, -4F);
      right_foot.setTextureSize(256, 128);
      right_foot.mirror = true;
      setRotation(right_foot, 0F, 0F, 0F);
      left_left_toe = new ModelRenderer(this, 64, 86);
      left_left_toe.addBox(9F, 9F, 2.5F, 3, 1, 2);
      left_left_toe.setRotationPoint(4.5F, 14F, -4F);
      left_left_toe.setTextureSize(256, 128);
      left_left_toe.mirror = true;
      setRotation(left_left_toe, 0F, -1.134464F, 0F);
      right_left_toe = new ModelRenderer(this, 149, 86);
      right_left_toe.addBox(9F, 9F, 2.5F, 3, 1, 2);
      right_left_toe.setRotationPoint(-4.5F, 14F, -4F);
      right_left_toe.setTextureSize(256, 128);
      right_left_toe.mirror = true;
      setRotation(right_left_toe, 0F, -1.134464F, 0F);
      left_right_toe = new ModelRenderer(this, 82, 86);
      left_right_toe.addBox(9F, 9F, -4F, 3, 1, 2);
      left_right_toe.setRotationPoint(4.5F, 14F, -4F);
      left_right_toe.setTextureSize(256, 128);
      left_right_toe.mirror = true;
      setRotation(left_right_toe, 0F, -1.973249F, 0F);
      right_right_toe = new ModelRenderer(this, 166, 86);
      right_right_toe.addBox(8F, 9F, -4F, 4, 1, 2);
      right_right_toe.setRotationPoint(-4.5F, 14F, -4F);
      right_right_toe.setTextureSize(256, 128);
      right_right_toe.mirror = true;
      setRotation(right_right_toe, 0F, -1.973249F, 0F);
      tail = new ModelRenderer(this, 107, 84);
      tail.addBox(-7F, 0F, 0F, 14, 19, 0);
      tail.setRotationPoint(0F, 3F, -16F);
      tail.setTextureSize(256, 128);
      tail.mirror = true;
      setRotation(tail, -0.5402259F, 0F, 0F);
      left_wing = new ModelRenderer(this, 44, 33);
      left_wing.addBox(0F, 0F, -5F, 0, 24, 10);
      left_wing.setRotationPoint(7F, -6F, -4F);
      left_wing.setTextureSize(256, 128);
      left_wing.mirror = true;
      setRotation(left_wing, 0F, 0F, -0.4833219F);
      right_wing = new ModelRenderer(this, 170, 35);
      right_wing.addBox(0F, 0F, -5F, 0, 24, 10);
      right_wing.setRotationPoint(-7F, -6F, -4F);
      right_wing.setTextureSize(256, 128);
      right_wing.mirror = true;
      setRotation(right_wing, 0F, 0F, 0.4833717F);
      upper_beak = new ModelRenderer(this, 73, 9);
      upper_beak.addBox(-2F, -11F, 12F, 4, 1, 5);
      upper_beak.setRotationPoint(0F, -2F, 2F);
      upper_beak.setTextureSize(256, 128);
      upper_beak.mirror = true;
      setRotation(upper_beak, 0F, 0F, 0F);
      lower_beak = new ModelRenderer(this, 74, 20);
      lower_beak.addBox(-1.5F, -10F, 11F, 3, 1, 5);
      lower_beak.setRotationPoint(0F, -2F, 2F);
      lower_beak.setTextureSize(256, 128);
      lower_beak.mirror = true;
      setRotation(lower_beak, 0.0371786F, 0F, 0F);
    Piece1 = new ModelRenderer(this, "Piece1");
    Piece1.setRotationPoint(0F, 0F, 0F);
    setRotation(Piece1, 0F, 0F, 0F);
    Piece1.mirror = true;
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Body.render(f5);
    neck.render(f5);
    head.render(f5);
    Left_middle_leg.render(f5);
    Left_upper_leg.render(f5);
    right_upper_leg.render(f5);
    right_middle_leg.render(f5);
    left_lower_leg.render(f5);
    right_lower_leg.render(f5);
    left_foot.render(f5);
    right_foot.render(f5);
    left_left_toe.render(f5);
    right_left_toe.render(f5);
    left_right_toe.render(f5);
    right_right_toe.render(f5);
    tail.render(f5);
    left_wing.render(f5);
    right_wing.render(f5);
    upper_beak.render(f5);
    lower_beak.render(f5);
    Piece1.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y + 1F;
    model.rotateAngleZ = z;
  }
  
    @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }

}
