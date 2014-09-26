package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelEnderwraith extends ModelBase
{
    ModelRenderer head;
    ModelRenderer chest;
    ModelRenderer shadow;
  
  public ModelEnderwraith()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      head = new ModelRenderer(this, 0, 0);
      head.addBox(-4F, -9F, -4F, 8, 8, 8);
      head.setRotationPoint(0F, 1F, 0F);
      head.setTextureSize(64, 32);
      head.mirror = true;
      setRotation(head, 0F, 0F, 0F);
      chest = new ModelRenderer(this, 10, 16);
      chest.addBox(-3.5F, -1F, -2F, 7, 8, 4);
      chest.setRotationPoint(0F, 1F, 0F);
      chest.setTextureSize(64, 32);
      chest.mirror = true;
      setRotation(chest, 0F, 0F, 0F);
      shadow = new ModelRenderer(this, 32, 0);
      shadow.addBox(-4F, 0F, -4F, 8, 20, 8);
      shadow.setRotationPoint(0F, 1F, 0F);
      shadow.setTextureSize(64, 32);
      shadow.mirror = true;
      setRotation(shadow, 0F, 0F, 0F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    head.render(f5);
    chest.render(f5);
    shadow.render(f5);
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
