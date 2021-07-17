package dmz.faction.icefeeling.entities.meteorhead;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IFMeteorHeadRenderer extends MobRenderer<IFMeteorHeadEntity, IFMeteorHeadModel<IFMeteorHeadEntity>> {
   private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/entity/meteor/meteor_head_entity.png");

   public IFMeteorHeadRenderer(EntityRendererManager renderManagerIn) {
      super(renderManagerIn, new IFMeteorHeadModel<>(), 0.5F);
   }

   protected int getBlockLight(BlazeEntity entityIn, BlockPos partialTicks) {
      return 7;
   }

   /**
    * Returns the location of an entity's texture.
    */
   @Override
   public ResourceLocation getEntityTexture(IFMeteorHeadEntity entity) {
      return TEXTURE;
   }


}