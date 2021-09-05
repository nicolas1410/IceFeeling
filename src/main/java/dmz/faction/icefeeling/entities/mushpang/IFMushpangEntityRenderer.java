package dmz.faction.icefeeling.entities.mushpang;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IFMushpangEntityRenderer extends MobRenderer<IFMushpangEntity, IFMushpangModel> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MOD_ID, ("textures/entity/mushpang/mushpang.png"));

	public IFMushpangEntityRenderer(EntityRendererManager renderManager) {
		
		super(renderManager, new IFMushpangModel(), 0.5F);
	}
	
	@Override
	public ResourceLocation getTextureLocation(IFMushpangEntity entity) 
	{
		return TEXTURE;
	}

}
