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
		texWidth = 64;
		texHeight = 32;

		mushpang = new ModelRenderer(this);
		mushpang.setPos(0.0F, 24.0F, 0.0F);

		hat = new ModelRenderer(this);
		hat.setPos(0.0F, 0.0F, 0.0F);
		mushpang.addChild(hat);
		hat.setTexSize(0, 14).addBox(-4.0F, -10.0F, -2.0F, 8.0F, 1.0F, 7.0F, 0.0F, false);
		hat.setTexSize(0, 11).addBox(-5.0F, -9.0F, -3.0F, 10.0F, 1.0F, 9.0F, 0.0F, false);
		hat.setTexSize(0, 0).addBox(-6.0F, -8.0F, -4.0F, 12.0F, 1.0F, 11.0F, 0.0F, false);
		hat.setTexSize(0, 18).addBox(-1.0F, -13.0F, 1.0F, 2.0F, 1.0F, 1.0F, 0.0F, false);
		hat.setTexSize(35, 4).addBox(-2.0F, -12.0F, 0.0F, 4.0F, 1.0F, 3.0F, 0.0F, false);
		hat.setTexSize(12, 4).addBox(-3.0F, -11.0F, -1.0F, 6.0F, 1.0F, 5.0F, 0.0F, false);

		lowerbody = new ModelRenderer(this);
		lowerbody.setPos(0.0F, 0.0F, 0.0F);
		mushpang.addChild(lowerbody);
		lowerbody.setTexSize(29, 12).addBox(-3.0F, -2.0F, -1.6F, 6.0F, 1.0F, 6.0F, 0.0F, false);
		lowerbody.setTexSize(26, 25).addBox(-2.5F, -1.0F, -1.2F, 5.0F, 1.0F, 5.0F, 0.0F, false);
		lowerbody.setTexSize(0, 22).addBox(-4.0F, -4.0F, -2.0F, 8.0F, 2.0F, 7.0F, 0.0F, false);
		lowerbody.setTexSize(0, 22).addBox(-4.0F, -6.0F, -2.0F, 8.0F, 2.0F, 7.0F, 0.0F, false);
		lowerbody.setTexSize(23, 24).addBox(-4.0F, -7.0F, -2.0F, 8.0F, 1.0F, 7.0F, 0.0F, false);
		lowerbody.setTexSize(35, 0).addBox(-3.0F, -6.0F, 4.4F, 6.0F, 3.0F, 1.0F, 0.0F, false);
		lowerbody.setTexSize(35, 21).addBox(-1.0F, -3.0F, -2.3F, 2.0F, 1.0F, 1.0F, 0.0F, false);

		eyeright = new ModelRenderer(this);
		eyeright.setPos(3.0F, -5.0F, -4.0F);
		lowerbody.addChild(eyeright);
		eyeright.setTexSize(30, 13).addBox(-1.0F, 0.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeright.setTexSize(30, 13).addBox(-2.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeright.setTexSize(35, 21).addBox(-2.0F, 0.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeright.setTexSize(30, 13).addBox(-1.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		eyeleft = new ModelRenderer(this);
		eyeleft.setPos(-1.0F, -4.0F, -4.0F);
		lowerbody.addChild(eyeleft);
		eyeleft.setTexSize(36, 21).addBox(-1.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeleft.setTexSize(30, 13).addBox(-1.0F, -2.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeleft.setTexSize(30, 13).addBox(-2.0F, -2.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		eyeleft.setTexSize(30, 13).addBox(-2.0F, -1.0F, 1.7F, 1.0F, 1.0F, 1.0F, 0.0F, false);

		armleft = new ModelRenderer(this);
		armleft.setPos(0.0F, 0.0F, 0.0F);
		mushpang.addChild(armleft);
		armleft.setTexSize(0, 22).addBox(-7.0F, -10.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		armleft.setTexSize(0, 12).addBox(-7.0F, -10.0F, -7.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		armleft.setTexSize(30, 24).addBox(-7.0F, -6.0F, -6.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		armleft.setTexSize(0, 0).addBox(-6.0F, -5.0F, -1.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		handleft = new ModelRenderer(this);
		handleft.setPos(0.0F, 0.0F, 0.0F);
		armleft.addChild(handleft);
		handleft.setTexSize(7, 0).addBox(-7.0F, -11.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
		handleft.setTexSize(6, 6).addBox(-6.0F, -12.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);

		armright = new ModelRenderer(this);
		armright.setPos(0.0F, 0.0F, 0.0F);
		mushpang.addChild(armright);
		armright.setTexSize(0, 22).addBox(5.0F, -10.0F, -6.0F, 2.0F, 4.0F, 1.0F, 0.0F, false);
		armright.setTexSize(0, 5).addBox(5.0F, -10.0F, -7.0F, 2.0F, 5.0F, 1.0F, 0.0F, false);
		armright.setTexSize(30, 24).addBox(5.0F, -6.0F, -6.0F, 2.0F, 2.0F, 6.0F, 0.0F, false);
		armright.setTexSize(0, 0).addBox(4.0F, -5.0F, -1.0F, 2.0F, 2.0F, 3.0F, 0.0F, false);

		handright = new ModelRenderer(this);
		handright.setPos(0.0F, 0.0F, 0.0F);
		armright.addChild(handright);
		handright.setTexSize(6, 6).addBox(5.0F, -12.0F, -5.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);
		handright.setTexSize(7, 0).addBox(6.0F, -11.0F, -6.0F, 1.0F, 1.0F, 1.0F, 0.0F, false);
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {

		mushpang.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setupAnim(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.xRot = x;
		modelRenderer.yRot = y;
		modelRenderer.zRot = z;
	}

}