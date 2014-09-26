package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelHeliumSlime extends ModelBase
{

    ModelRenderer Cube;
    ModelRenderer AnotherCube;
    ModelRenderer OrbitingSquare;
    ModelRenderer AnotherOrbitingSquare;
    ModelRenderer RoundOrbitingSquare;
    ModelRenderer Bigcube;
  
  public ModelHeliumSlime()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      Cube = new ModelRenderer(this, 0, 23);
      Cube.addBox(-2F, -2F, -3.5F, 4, 4, 7);
      Cube.setRotationPoint(0F, 21F, 0F);
      Cube.setTextureSize(64, 64);
      Cube.mirror = true;
      setRotation(Cube, 0F, 1.570796F, 0F);
      AnotherCube = new ModelRenderer(this, 0, 34);
      AnotherCube.addBox(-2.5F, -3.5F, -2.5F, 5, 6, 5);
      AnotherCube.setRotationPoint(0F, 21F, 0F);
      AnotherCube.setTextureSize(64, 64);
      AnotherCube.mirror = true;
      setRotation(AnotherCube, 0F, 0F, 0F);
      OrbitingSquare = new ModelRenderer(this, -2, 12);
      OrbitingSquare.addBox(-5.5F, 0F, -5.5F, 11, 0, 11);
      OrbitingSquare.setRotationPoint(0F, 21F, 0F);
      OrbitingSquare.setTextureSize(64, 64);
      OrbitingSquare.mirror = true;
      setRotation(OrbitingSquare, 0.4461433F, 0.5205006F, 0F);
      AnotherOrbitingSquare = new ModelRenderer(this, 31, 11);
      AnotherOrbitingSquare.addBox(-5.5F, 0F, -5.5F, 11, 0, 11);
      AnotherOrbitingSquare.setRotationPoint(0F, 21F, 0F);
      AnotherOrbitingSquare.setTextureSize(64, 64);
      AnotherOrbitingSquare.mirror = true;
      setRotation(AnotherOrbitingSquare, -0.5576792F, -0.2602503F, 0.4461433F);
      RoundOrbitingSquare = new ModelRenderer(this, 31, 0);
      RoundOrbitingSquare.addBox(-5.5F, 0F, -5.5F, 11, 0, 11);
      RoundOrbitingSquare.setRotationPoint(0F, 21F, 0F);
      RoundOrbitingSquare.setTextureSize(64, 64);
      RoundOrbitingSquare.mirror = true;
      setRotation(RoundOrbitingSquare, 0.0371786F, 0F, 0F);
      Bigcube = new ModelRenderer(this, 7, 0);
      Bigcube.addBox(-3F, -3F, -3F, 6, 6, 6);
      Bigcube.setRotationPoint(0F, 21F, 0F);
      Bigcube.setTextureSize(64, 64);
      Bigcube.mirror = true;
      setRotation(Bigcube, 0F, 0F, 0F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Cube.render(f5);
    AnotherCube.render(f5);
    OrbitingSquare.render(f5);
    AnotherOrbitingSquare.render(f5);
    RoundOrbitingSquare.render(f5);
    Bigcube.render(f5);
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
	//float rotation = (float)(Math.cos((entity.ticksExisted * 0.05F) % 360) * Math.PI * 2);
	float rotation = (float)(((entity.ticksExisted * 0.1F) % (Math.PI * 2)) - Math.PI);
	/*OrbitingSquare.rotateAngleY = rotation + 0.5205006F;
	AnotherOrbitingSquare.rotateAngleY = rotation - 0.2602503F;
	RoundOrbitingSquare.rotateAngleY = rotation + 0F;*/
	OrbitingSquare.rotateAngleX = rotation + 0.4461433F;
	AnotherOrbitingSquare.rotateAngleX = rotation - 0.5576792F;
	RoundOrbitingSquare.rotateAngleX = rotation + 0.0371786F;
  }

}
