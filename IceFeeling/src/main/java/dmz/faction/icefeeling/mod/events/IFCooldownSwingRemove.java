package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFCooldownSwingRemove {
	
    @SubscribeEvent
    public static void onAttackEntity(final AttackEntityEvent e) {
    	ItemStack item = e.getEntityLiving().getMainHandItem();

        if (item == IFItems.FIRE_OPAL_SWORD.get().getDefaultInstance() || 
        		item == IFItems.OPAL_SWORD.get().getDefaultInstance() || 
        		item == IFItems.JADE_SWORD.get().getDefaultInstance() || 
        		item == IFItems.MYTHRIL_SWORD.get().getDefaultInstance() ||
        		item == IFItems.OBSIDIAN_SWORD.get().getDefaultInstance() ||
        		item == IFItems.TITANITE_SWORD.get().getDefaultInstance()) {
            PlayerEntity player = e.getPlayer();
            player.getCooldowns().removeCooldown(item.getItem());;
        }   
    }
}
