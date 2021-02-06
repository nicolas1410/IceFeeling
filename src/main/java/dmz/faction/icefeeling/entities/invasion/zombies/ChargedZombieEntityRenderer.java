package dmz.faction.icefeeling.entities.invasion.zombies;

import dmz.faction.icefeeling.entities.invasion.zombies.abstracts.AbstractChargedZombieRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)

	public class ChargedZombieEntityRenderer extends AbstractChargedZombieRenderer<ChargedZombie, ChargedZombieModel<ChargedZombie>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation("textures/entity/zombie/zombie.png");
	
	public ChargedZombieEntityRenderer(EntityRendererManager renderManager) {
		super(renderManager, new ChargedZombieModel<>(0.0F, false), new ChargedZombieModel<>(0.5F, true), new ChargedZombieModel<>(1.0F, true));
		this.addLayer(new ChargedZombieLayer(this));
	}

	/**      super(renderManagerIn, new ZombieModel<>(0.0F, false), new ZombieModel<>(0.5F, true), new ZombieModel<>(1.0F, true));

	 * 
	 * Returns the location of an entity's texture.
	 */
	public ResourceLocation getEntityTexture(ChargedZombie entity) {
		return TEXTURE;
	}

}