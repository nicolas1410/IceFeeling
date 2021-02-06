package dmz.faction.icefeeling.entities.invasion.zombies.abstracts;

import dmz.faction.icefeeling.entities.invasion.zombies.ChargedZombie;
import dmz.faction.icefeeling.entities.invasion.zombies.ChargedZombieModel;
import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.layers.BipedArmorLayer;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class AbstractChargedZombieRenderer<T extends ChargedZombie, M extends ChargedZombieModel<T>> extends BipedRenderer<T, M> {
   private static final ResourceLocation TEXTURES = new ResourceLocation("textures/entity/zombie/zombie.png");

   protected AbstractChargedZombieRenderer(EntityRendererManager renderManager, M p_i50974_2_, M p_i50974_3_, M p_i50974_4_) {
      super(renderManager, p_i50974_2_, 0.5F);
      this.addLayer(new BipedArmorLayer<>(this, p_i50974_3_, p_i50974_4_));
   }

   /**
    * Returns the location of an entity's texture.
    */
   public ResourceLocation getEntityTexture(ZombieEntity entity) {
      return TEXTURES;
   }

}
