package alldemdimensions.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSycopter extends ModelBase
{
    ModelRenderer MainBody;
    ModelRenderer Side0;
    ModelRenderer Side1;
    ModelRenderer Side2;
    ModelRenderer Side3;
    ModelRenderer Bottom;
    ModelRenderer Axle;
    ModelRenderer Hub;
    ModelRenderer Rotor0;
    ModelRenderer Rotor1;
    ModelRenderer Top;
    ModelRenderer Rotor2;
    ModelRenderer Rotor3;
  
  public ModelSycopter()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      MainBody = new ModelRenderer(this, 0, 0);
      MainBody.addBox(0F, 0F, 0F, 6, 8, 6);
      MainBody.setRotationPoint(-3F, 11F, -3F);
      MainBody.setTextureSize(64, 32);
      MainBody.mirror = true;
      setRotation(MainBody, 0F, 0F, 0F);
      Side0 = new ModelRenderer(this, 24, 0);
      Side0.addBox(0F, 0F, 0F, 4, 6, 1);
      Side0.setRotationPoint(-2F, 12F, -4F);
      Side0.setTextureSize(64, 32);
      Side0.mirror = true;
      setRotation(Side0, 0F, 0F, 0F);
      Side1 = new ModelRenderer(this, 24, 7);
      Side1.addBox(0F, 0F, 0F, 4, 6, 1);
      Side1.setRotationPoint(-2F, 12F, 3F);
      Side1.setTextureSize(64, 32);
      Side1.mirror = true;
      setRotation(Side1, 0F, 0F, 0F);
      Side2 = new ModelRenderer(this, 34, 10);
      Side2.addBox(0F, 0F, 0F, 1, 6, 4);
      Side2.setRotationPoint(-4F, 12F, -2F);
      Side2.setTextureSize(64, 32);
      Side2.mirror = true;
      setRotation(Side2, 0F, 0F, 0F);
      Side3 = new ModelRenderer(this, 44, 10);
      Side3.addBox(0F, 0F, 0F, 1, 6, 4);
      Side3.setRotationPoint(3F, 12F, -2F);
      Side3.setTextureSize(64, 32);
      Side3.mirror = true;
      setRotation(Side3, 0F, 0F, 0F);
      Bottom = new ModelRenderer(this, 34, 0);
      Bottom.addBox(0F, 0F, 0F, 4, 1, 4);
      Bottom.setRotationPoint(-2F, 19F, -2F);
      Bottom.setTextureSize(64, 32);
      Bottom.mirror = true;
      setRotation(Bottom, 0F, 0F, 0F);
      Axle = new ModelRenderer(this, 50, 0);
      Axle.addBox(0F, 0F, 0F, 2, 2, 2);
      Axle.setRotationPoint(-1F, 9F, -1F);
      Axle.setTextureSize(64, 32);
      Axle.mirror = true;
      setRotation(Axle, 0F, 0F, 0F);
      Hub = new ModelRenderer(this, 50, 4);
      Hub.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);//(0F, 0F, 0F, 3, 1, 3);
      Hub.setRotationPoint(0F, 8F, 0F);//(-1.5F, 8F, -1.5F);
      Hub.setTextureSize(64, 32);
      Hub.mirror = true;
      setRotation(Hub, 0F, 0F, 0F);
      /*Rotor0 = new ModelRenderer(this, 0, 14);
      Rotor0.addBox(0F, 0F, 0F, 8, 0, 3);
      Rotor0.setRotationPoint(-1.5F, 8F, 0.5F);
      Rotor0.setTextureSize(64, 32);
      Rotor0.mirror = true;
      setRotation(Rotor0, -0.2617994F, 3.141593F, 0F);
      Rotor1 = new ModelRenderer(this, 0, 20);
      Rotor1.addBox(0F, 0F, -8F, 3, 0, 8);
      Rotor1.setRotationPoint(-0.5F, 8F, -1.5F);
      Rotor1.setTextureSize(64, 32);
      Rotor1.mirror = true;
      setRotation(Rotor1, 0F, 0F, 0.2617994F);*/
	  /*Rotor0 = new ModelRenderer(this, 0, 14);
      Rotor0.addBox(1.5F, 0F, 0.5F, 8, 0, 3);
      Rotor0.setRotationPoint(0F, 8F, 0F);
      Rotor0.setTextureSize(64, 32);
      Rotor0.mirror = true;
      setRotation(Rotor0, -0.2617994F, 0F3.141593F, 0F);
      Rotor1 = new ModelRenderer(this, 0, 20);
      Rotor1.addBox(-0.5F, 0F, -9.5F, 3, 0, 8);
      Rotor1.setRotationPoint(0F, 8F, 0F);
      Rotor1.setTextureSize(64, 32);
      Rotor1.mirror = true;
      setRotation(Rotor1, 0F, 0F, 0.2617994F);*/
	  Rotor0 = new ModelRenderer(this, 0, 14);
      Rotor0.addBox(-9.5F, 0F, -2.5F, 8, 0, 3);
      Rotor0.setRotationPoint(0F, 8F, 0F);
      Rotor0.setTextureSize(64, 32);
      Rotor0.mirror = true;
      setRotation(Rotor0, 0.2617994F, 0F, 0F);
      Rotor1 = new ModelRenderer(this, 0, 20);
      Rotor1.addBox(-0.5F, 0F, -9.5F, 3, 0, 8);
      Rotor1.setRotationPoint(0F, 8F, 0F);
      Rotor1.setTextureSize(64, 32);
      Rotor1.mirror = true;
      setRotation(Rotor1, 0F, 0F, 0.2617994F);
      Top = new ModelRenderer(this, 34, 5);
      Top.addBox(0F, 0F, 0F, 4, 1, 4);
      Top.setRotationPoint(-2F, 10F, -2F);
      Top.setTextureSize(64, 32);
      Top.mirror = true;
      setRotation(Top, 0F, 0F, 0F);
      /*Rotor2 = new ModelRenderer(this, 0, 17);
      Rotor2.addBox(0F, 0F, 0F, 8, 0, 3);
      Rotor2.setRotationPoint(1.5F, 8F, -0.5F);
      Rotor2.setTextureSize(64, 32);
      Rotor2.mirror = true;
      setRotation(Rotor2, -0.2617994F, 0F, 0F);
      Rotor3 = new ModelRenderer(this, 22, 20);
      Rotor3.addBox(-3F, 0F, 0F, 3, 0, 8);
      Rotor3.setRotationPoint(0.5F, 8F, 1.5F);
      Rotor3.setTextureSize(64, 32);
      Rotor3.mirror = true;
      setRotation(Rotor3, 0F, 0F, -0.2617994F);*/
	  /*Rotor2 = new ModelRenderer(this, 0, 17);
      Rotor2.addBox(1.5F, 0F, -0.5F, 8, 0, 3);
      Rotor2.setRotationPoint(0F, 8F, 0F);
      Rotor2.setTextureSize(64, 32);
      Rotor2.mirror = true;
      setRotation(Rotor2, -0.2617994F, 0F, 0F);
      Rotor3 = new ModelRenderer(this, 22, 20);
      Rotor3.addBox(-2.5F, 0F, 1.5F, 3, 0, 8);
      Rotor3.setRotationPoint(0F, 8F, 0F);
      Rotor3.setTextureSize(64, 32);
      Rotor3.mirror = true;
      setRotation(Rotor3, 0F, 0F, -0.2617994F);*/
	  Rotor2 = new ModelRenderer(this, 0, 17);
      Rotor2.addBox(1.5F, 0F, -0.5F, 8, 0, 3);
      Rotor2.setRotationPoint(0F, 8F, 0F);
      Rotor2.setTextureSize(64, 32);
      Rotor2.mirror = true;
      setRotation(Rotor2, -0.2617994F, 0F, 0F);
      Rotor3 = new ModelRenderer(this, 22, 20);
      Rotor3.addBox(-2.5F, 0F, 1.5F, 3, 0, 8);
      Rotor3.setRotationPoint(0F, 8F, 0F);
      Rotor3.setTextureSize(64, 32);
      Rotor3.mirror = true;
      setRotation(Rotor3, 0F, 0F, -0.2617994F);
  }
  
    @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    MainBody.render(f5);
    Side0.render(f5);
    Side1.render(f5);
    Side2.render(f5);
    Side3.render(f5);
    Bottom.render(f5);
    Axle.render(f5);
    Hub.render(f5);
    Rotor0.render(f5);
    Rotor1.render(f5);
    Top.render(f5);
    Rotor2.render(f5);
    Rotor3.render(f5);
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
	/*EntitySycopter entitysycopter = (EntitySycopter)entity;
	Rotor0.rotateAngleX = entitysycopter.rotorRotationX;//+= 0.1F;
	Rotor1.rotateAngleX = entitysycopter.rotorRotationX;//+= 0.1F;
	Rotor2.rotateAngleX = entitysycopter.rotorRotationX;//+= 0.1F;
	Rotor3.rotateAngleX = entitysycopter.rotorRotationX;//+= 0.1F;
	
	Rotor0.rotateAngleY = entitysycopter.rotorRotationY;//+= 0.1F;
	Rotor1.rotateAngleY = entitysycopter.rotorRotationY;//+= 0.1F;
	Rotor2.rotateAngleY = entitysycopter.rotorRotationY;//+= 0.1F;
	Rotor3.rotateAngleY = entitysycopter.rotorRotationY;//+= 0.1F;
	Hub.rotateAngleY = entitysycopter.rotorRotationY;//+= 0.1F;
	*/
	float rotation = (float)(Math.cos((f * 0.1F) % 360) * Math.PI * 2);
	///float rotation = (float)(((entity.ticksExisted * 0.2F) % (Math.PI * 2)) - Math.PI);
	Rotor0.rotateAngleY = rotation;
	Rotor1.rotateAngleY = rotation;
	Rotor2.rotateAngleY = rotation;
	Rotor3.rotateAngleY = rotation;
	Hub.rotateAngleY = rotation;
  }

}
