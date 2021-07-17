package dmz.faction.icefeeling.entities.meteorhead;

import java.util.Arrays;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class IFMeteorHeadModel<T extends Entity> extends SegmentedModel<T> {

	private final ModelRenderer[] meteorCubes;
	private final ModelRenderer meteorHead = new ModelRenderer(this, 0, 0);
	private final ImmutableList<ModelRenderer> immutableList;

	public IFMeteorHeadModel() {

		this.meteorHead.addBox(-4.0F, 5.0F, -4.0F, 8F, 8F, 8F);
		this.meteorCubes = new ModelRenderer[8];

		for (int i = 0; i < this.meteorCubes.length; ++i) {
			this.meteorCubes[i] = new ModelRenderer(this, 0, 16);
			this.meteorCubes[i].addBox(0.0F, 0.0F, 0.0F, 2.0F, 2.0F, 2.0F);
		}

		Builder<ModelRenderer> builder = ImmutableList.builder();
		builder.add(this.meteorHead);
		builder.addAll(Arrays.asList(this.meteorCubes));
		this.immutableList = builder.build();

		textureWidth = 64;
		textureHeight = 32;
		
	}

	@Override
	public Iterable<ModelRenderer> getParts() {
		return this.immutableList;
	}

	@Override
	public void setRotationAngles(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		
		float f = ageInTicks * (float) Math.PI * 0.06F;
		
		for (int i = 0; i < 2; ++i) {
			this.meteorCubes[i].rotationPointY = 7.0F + MathHelper.cos(((float) (i * 2) + ageInTicks) * 0.6F);
			this.meteorCubes[i].rotationPointX = MathHelper.cos(f) * 9.0F;
			this.meteorCubes[i].rotationPointZ = MathHelper.sin(f) * 9.0F;
			++f;
		}

		f = ((float) Math.PI / 4F) + ageInTicks * (float) Math.PI * 0.09F;

		for (int j = 2; j < 8; ++j) {
			this.meteorCubes[j].rotationPointY = 7.5F + MathHelper.cos(((float) (j * 2) + ageInTicks) * 0.45F);
			this.meteorCubes[j].rotationPointX = MathHelper.cos(f) * 9.0F;
			this.meteorCubes[j].rotationPointZ = MathHelper.sin(f) * 9.0F;
			++f;
		}

		f = 0.47123894F + ageInTicks * (float) Math.PI * -0.08F;

		for (int k = 4; k < 6; ++k) {
			this.meteorCubes[k].rotationPointY = 3.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.5F);
			this.meteorCubes[k].rotationPointX = MathHelper.cos(f) * 11.0F;
			this.meteorCubes[k].rotationPointZ = MathHelper.sin(f) * 11.0F;
			++f;
		}
		
		f = 0.27123894F + ageInTicks * (float) Math.PI * -0.12F;

		for (int k = 6; k < 8; ++k) {
			this.meteorCubes[k].rotationPointY = 5.0F + MathHelper.cos(((float) k * 1.5F + ageInTicks) * 0.7F);
			this.meteorCubes[k].rotationPointX = MathHelper.cos(f) * 9.0F;
			this.meteorCubes[k].rotationPointZ = MathHelper.sin(f) * 9.0F;
			++f;
		}

		this.meteorHead.rotateAngleY = netHeadYaw * ((float) Math.PI / 180F);
		this.meteorHead.rotateAngleX = headPitch * ((float) Math.PI / 180F);
	}



}