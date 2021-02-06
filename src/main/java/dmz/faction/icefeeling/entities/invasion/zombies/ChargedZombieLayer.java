package dmz.faction.icefeeling.entities.invasion.zombies;

import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ChargedZombieLayer extends EnergyLayer<ChargedZombie, ChargedZombieModel<ChargedZombie>> {
   private static final ResourceLocation LIGHTNING_TEXTURE = new ResourceLocation("textures/entity/creeper/creeper_armor.png");
   private final EntityModel<ChargedZombie> creeperModel = new ChargedZombieModel<>(2.0F, false);

   public ChargedZombieLayer(IEntityRenderer<ChargedZombie, ChargedZombieModel<ChargedZombie>> model) {
      super(model);
   }

   protected float func_225634_a_(float f) {
      return f * 0.01F;
   }

   protected ResourceLocation func_225633_a_() {
      return LIGHTNING_TEXTURE;
   }

   protected EntityModel<ChargedZombie> func_225635_b_() {
      return this.creeperModel;
   }
}
