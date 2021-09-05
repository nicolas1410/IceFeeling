package dmz.faction.icefeeling.mod.events;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFRemoveSweepAttack {
	
    @SubscribeEvent
    public static void onAttackEntity(final AttackEntityEvent e) {
        // If the player cannot do any sweeping damage, they do not have the enchantment.
        if (EnchantmentHelper.getSweepingDamageRatio(e.getPlayer()) == 0.0F) {
            PlayerEntity player = e.getPlayer();
            // If player is on ground, set them (for this tick) to onGround = false;
            if (player.isOnGround()) {
                player.setOnGround(false);
            }
        }   
    }
}
