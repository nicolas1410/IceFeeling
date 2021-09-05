package dmz.faction.icefeeling.mod.init;

import java.awt.event.KeyEvent;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class IFKeybindsInit {
	
		public static KeyBinding ZoomKey;
		public static KeyBinding WhatEver;

		
		public static void register(final FMLClientSetupEvent event) {
			ZoomKey = create("ZoomKey", KeyEvent.VK_V);
			WhatEver = create("WhatEver", KeyEvent.VK_J);

			ClientRegistry.registerKeyBinding(ZoomKey);
			ClientRegistry.registerKeyBinding(WhatEver);

		}
		
		private static KeyBinding create(String name, int key) {
			return new KeyBinding("key." + Main.MOD_ID + "." + name, key, "key.category." + Main.MOD_ID);
		}

}
