package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.client.screens.IFMainMenuScreen;
import net.minecraft.client.gui.screen.MainMenuScreen;
import net.minecraftforge.client.event.GuiOpenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFGuiOpenEvent {

	@SubscribeEvent
	public static void newMainMenu(GuiOpenEvent e) {
		if(e.getGui() != null && e.getGui().getClass() == MainMenuScreen.class) {
		e.setGui(new IFMainMenuScreen());
		}
	}
	
}
