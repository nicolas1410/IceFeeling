package dmz.faction.icefeeling.init;

import dmz.faction.icefeeling.inventory.obsidian.IFObsidianFurnaceScreen;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.tileentity.registry.IFRegistration;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	
	
	public static void init(final FMLClientSetupEvent event) {
		
	ScreenManager.registerFactory(IFRegistration.OBSIDIAN_FURNACE_CONTAINER.get(), IFObsidianFurnaceScreen::new);
	
	}
}
