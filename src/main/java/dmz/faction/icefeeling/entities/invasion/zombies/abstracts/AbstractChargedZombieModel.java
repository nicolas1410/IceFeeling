package dmz.faction.icefeeling.entities.invasion.zombies.abstracts;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractChargedZombieModel<T extends MonsterEntity> extends BipedModel<T> {
   protected AbstractChargedZombieModel(float modelSize, float yOffsetIn, int textureWidthIn, int textureHeightIn) {
      super(modelSize, yOffsetIn, textureWidthIn, textureHeightIn);
   }

   /**
    * Sets this entity's model rotation angles
    */
   public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
      super.setupAnim(entityIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
      ModelHelper.animateZombieArms(this.leftArm, this.rightArm, this.isAggressive(entityIn), this.attackTime, ageInTicks);
   }

   public abstract boolean isAggressive(T entityIn);
}
