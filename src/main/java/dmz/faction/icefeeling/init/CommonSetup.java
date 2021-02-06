package dmz.faction.icefeeling.init;

import dmz.faction.icefeeling.mod.Main;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CommonSetup {
	
	@SubscribeEvent
	public static void init(final FMLCommonSetupEvent event) {
	 	
	
	}
}

