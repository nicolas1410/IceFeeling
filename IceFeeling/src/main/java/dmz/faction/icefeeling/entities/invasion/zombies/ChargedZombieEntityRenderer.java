package dmz.faction.icefeeling.entities.invasion.zombies;

import dmz.faction.icefeeling.entities.invasion.zombies.abstracts.AbstractChargedZombieRenderer;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)

	public class ChargedZombieEntityRenderer extends AbstractChargedZombieRenderer<ChargedZombie, ChargedZombieModel<ChargedZombie>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, ("textures/entity/invasion/charged_zombie.png"));
	
	public ChargedZombieEntityRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ChargedZombieModel<>(0.0F, false), 0.5F);
		this.addLayer(new ChargedZombieLayer(this));
	}

	/**
	 * 
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getEntityTexture(ChargedZombie entity) {
		return TEXTURE;
	}

}