package dmz.faction.icefeeling.entities.invasion.zombies;

import dmz.faction.icefeeling.entities.invasion.zombies.abstracts.AbstractChargedZombieModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChargedZombieModel<T extends ChargedZombie> extends AbstractChargedZombieModel<T> {

	public ChargedZombieModel(float modelSize, boolean b) {
		this(modelSize, 0.0F, 64, b ? 32 : 64);
	}

	protected ChargedZombieModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
		super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
	}

	/**
	 * Sets this entity's model rotation angles
	 */
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);
	}

	public boolean isAggressive(T entityIn) {
		return true;
	}

}