package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.mod.init.IFKeybindsInit;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = Main.MOD_ID, bus = Bus.FORGE, value = Dist.CLIENT)
public class IFInputEvents {

	@SubscribeEvent
	public static void onKeyPress(InputEvent.KeyInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.world == null)
			return;
		onInput(mc, event.getKey(), event.getAction());
	}

	@SubscribeEvent
	public static void onMouseClick(InputEvent.MouseInputEvent event) {
		Minecraft mc = Minecraft.getInstance();
		if (mc.world == null)
			return;
	}

	private static void onInput(Minecraft mc, int key, int action) {
		if (mc.currentScreen == null && IFKeybindsInit.WhatEver.isPressed()) {
		PlayerEntity player = mc.getInstance().player;
		
			player.addItemStackToInventory(IFItems.FIRE_OPAL.get().getDefaultInstance());
			
		}
	}
	
}