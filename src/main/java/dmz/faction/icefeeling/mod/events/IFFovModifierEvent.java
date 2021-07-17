package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.mod.init.IFKeybindsInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.client.event.EntityViewRenderEvent.FOVModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFFovModifierEvent {
	
	@SubscribeEvent
	public static void Zoom(FOVModifier e) {
		if (Minecraft.getInstance().currentScreen == null && IFKeybindsInit.ZoomKey.isKeyDown()) {
			e.setFOV(25);
		}
		
	}

}
