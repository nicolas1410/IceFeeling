package dmz.faction.icefeeling.mod.events;

import java.util.UUID;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFLoggedInEvent {

	@SubscribeEvent
	public static void onLoggedInEvent(final PlayerLoggedInEvent e) {
		PlayerEntity player = e.getPlayer();
		UUID uuid = player.getUniqueID();
		String playerName = player.getScoreboardName();
		boolean firstTimeJoining = player.getDataManager().isEmpty();
		
		//Joined For The First Time
		if (firstTimeJoining) { player.sendMessage(new StringTextComponent(TextFormatting.GOLD + Main.SERVER_NAME_END_SPACE + "Bienvenue à " + playerName + " !"), uuid); 
		IFLoggedInEvent.giveItemsOnFirstJoin(player); }
	
		//Played Before
		else player.sendMessage(new StringTextComponent(TextFormatting.GOLD + Main.SERVER_NAME_END_SPACE + playerName + " a rejoint le serveur !"), uuid);

	}
	
	private static void giveItemsOnFirstJoin(PlayerEntity player) {
		
		player.addItemStackToInventory(Items.LEATHER_HELMET.getDefaultInstance());
		player.addItemStackToInventory(Items.LEATHER_CHESTPLATE.getDefaultInstance());
		player.addItemStackToInventory(Items.LEATHER_LEGGINGS.getDefaultInstance());
		player.addItemStackToInventory(Items.LEATHER_BOOTS.getDefaultInstance());
		for(int i = 0; i < 32; i++) 
		{
			player.addItemStackToInventory(Items.COOKED_BEEF.getDefaultInstance());
		}
	}
}
