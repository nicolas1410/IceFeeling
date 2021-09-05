package dmz.faction.icefeeling.mod.init;

import dmz.faction.icefeeling.inventory.ironfurnace.IFIronFurnaceScreen;
import dmz.faction.icefeeling.inventory.itemfilter.IFCommonItemFilterScreen;
import dmz.faction.icefeeling.inventory.obsidianfurnace.IFObsidianFurnaceScreen;
import dmz.faction.icefeeling.inventory.singleitem.IFSingleItemChestScreen;
import dmz.faction.icefeeling.inventory.trashcan.IFTrashCanScreen;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.mod.registry.IFEntityRegister;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;


@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientSetup {
	
	@SubscribeEvent
	public static void init(final FMLClientSetupEvent event) {
		
	IFKeybindsInit.register(event);
		
	// GUIS	
	
	ScreenManager.register(IFTileRegistry.IRON_FURNACE_CONTAINER.get(), IFIronFurnaceScreen::new);
	
	ScreenManager.register(IFTileRegistry.OBSIDIAN_FURNACE_CONTAINER.get(), IFObsidianFurnaceScreen::new);

	ScreenManager.register(IFTileRegistry.COMMON_ITEM_FILTER_CONTAINER.get(), IFCommonItemFilterScreen::new);
	
	ScreenManager.register(IFTileRegistry.SINGLE_ITEM_CHEST_CONTAINER_BIG.get(), IFSingleItemChestScreen::new);

	ScreenManager.register(IFTileRegistry.TRASH_CAN_CONTAINER.get(), IFTrashCanScreen::new);
	
	// HUD
	//MinecraftForge.EVENT_BUS.register(new IFArmorHUD());
	//Renderers
	IFEntityRegister.registerRenderers();

	
	}
}
