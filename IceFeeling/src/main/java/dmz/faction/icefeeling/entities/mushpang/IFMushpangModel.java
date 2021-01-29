package dmz.faction.icefeeling.entities.mushpang;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import dmz.faction.icefeeling.entities.helper.IFBlockBenchModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IFMushpangModel extends IFBlockBenchModel<IFMushpangEntity> {
	private final ModelRenderer mushpang;
	private final ModelRenderer hat;
	private final ModelRenderer lowerbody;
	private final ModelRenderer eyeright;
	private final ModelRenderer eyeleft;
	private final ModelRenderer armleft;
	private final ModelRenderer handleft;
	private final ModelRenderer armright;
	private final ModelRenderer handright;

	public IFMushpangModel() {
		textureWidth = 64;
		textureHeight = 32;

		mushpang = new ModelRenderer(this);
		mushpang.setRotationPoint(0.0F, 24.0F, 0.0F);
		

		hat = new ModelRenderer(this);
		hat.setRotationPoint(0.0F, 0.0F, 0.0F);
		mushpang.addChild(hat);
		hat.setTextureOffset(0, 14).addBox(-4.0F, -10.0F, -2.0F, 8.0F, 1.0F, 7.0F, 0.0F, false);
		hat.setTextureOffset(0, 11).addBox(-5.0F, -9.0F, -3.0F, 10.0F, 1.0F, 9.0F, 0.0F, false);
		hat.setTextureOffset(0, 0).addBox(-6.0F, -8.0F, -4.0F, 12.0F, 1.0F, 11.0F, 0.0F, false);
		hat.setTextureOffset(0, 18).addBox(-1.0F, -13.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		hat.setTextureOffset(35, 4).addBox(-2.0F, -12.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		hat.setTextureOffset(12, 4).addBox(-3.0F, -11.0F, -1.0F, 6.0F, 1.0F, 5.0F, 0.0F, false);

		lowerbody = new ModelRenderer(this);
		lowerbody.setRotationPoint(0.0F, 0.0F, 0.0F);
		mushpang.addChild(lowerbody);
		lowerbody.setTextureOffset(29, 12).addBox(-3.0F, -2.0F, -1.6F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		lowerbody.setTextureOffset(26, 25).addBox(-2.5F, -1.0F, -1.2F, 5.0F, 1.0F, 5.0F, 0.0F, false);
		lowerbody.setTextureOffset(0, 22).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 2.0F, 7.0F, 0.0F, false);
		lowerbody.setTextureOffset(0, 22).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 2.0F, 7.0F, 0.0F, false);
		lowerbody.setTextureOffset(23, 24).addBox(-4.0F, -7.0F, -2.0F, 8.0F, 1.0F, 7.0F, 0.0F, false);
		lowerbody.setTextureOffset(35, 0).addBox(-3.0F, -6.0F, 4.4F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		lowerbody.setTextureOffset(35, 21).addBox(-1.0F, -3.0F, -2.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		eyeright = new ModelRenderer(this);
		eyeright.setRotationPoint(3.0F, -5.0F, -4.0F);
		lowerbody.addChild(eyeright);
		eyeright.setTextureOffset(30, 13).addBox(-1.0F, 0.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeright.setTextureOffset(30, 13).addBox(-2.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeright.setTextureOffset(35, 21).addBox(-2.0F, 0.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeright.setTextureOffset(30, 13).addBox(-1.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		eyeleft = new ModelRenderer(this);
		eyeleft.setRotationPoint(-1.0F, -4.0F, -4.0F);
		lowerbody.addChild(eyeleft);
		eyeleft.setTextureOffset(36, 21).addBox(-1.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeleft.setTextureOffset(30, 13).addBox(-1.0F, -2.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeleft.setTextureOffset(30, 13).addBox(-2.0F, -2.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeleft.setTextureOffset(30, 13).addBox(-2.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		armleft = new ModelRenderer(this);
		armleft.setRotationPoint(0.0F, 0.0F, 0.0F);
		mushpang.addChild(armleft);
		armleft.setTextureOffset(0, 22).addBox(-7.0F, -10.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		armleft.setTextureOffset(0, 12).addBox(-7.0F, -10.0F, -7.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		armleft.setTextureOffset(30, 24).addBox(-7.0F, -6.0F, -6.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		armleft.setTextureOffset(0, 0).addBox(-6.0F, -5.0F, -1.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		handleft = new ModelRenderer(this);
		handleft.setRotationPoint(0.0F, 0.0F, 0.0F);
		armleft.addChild(handleft);
		handleft.setTextureOffset(7, 0).addBox(-7.0F, -11.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		handleft.setTextureOffset(6, 6).addBox(-6.0F, -12.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		armright = new ModelRenderer(this);
		armright.setRotationPoint(0.0F, 0.0F, 0.0F);
		mushpang.addChild(armright);
		armright.setTextureOffset(0, 22).addBox(5.0F, -10.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		armright.setTextureOffset(0, 5).addBox(5.0F, -10.0F, -7.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		armright.setTextureOffset(30, 24).addBox(5.0F, -6.0F, -6.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		armright.setTextureOffset(0, 0).addBox(4.0F, -5.0F, -1.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		handright = new ModelRenderer(this);
		handright.setRotationPoint(0.0F, 0.0F, 0.0F);
		armright.addChild(handright);
		handright.setTextureOffset(6, 6).addBox(5.0F, -12.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		handright.setTextureOffset(7, 0).addBox(6.0F, -11.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}
	
	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){

		mushpang.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}

}