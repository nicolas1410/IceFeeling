package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFAttackIndicator {
	
    @SubscribeEvent
    public static void onLivingHurt(LivingHurtEvent e) {
    	
        Entity entityAttacked = e.getEntityLiving();               
        if (entityAttacked != null) {
            if (!entityAttacked.getEntityWorld().isRemote()) {
                if (e.getSource().getTrueSource() instanceof PlayerEntity) {
                	
                	LivingEntity trueSource = (LivingEntity) e.getSource().getTrueSource().getEntity();
                	String name = e.getSource().getTrueSource().getScoreboardName();
                	String itemHeldName = trueSource.getHeldItemMainhand().getItem().getName().getString();
                	               
                	System.out.println(Main.SERVER_NAME_END_SPACE + name + " a fait " + e.getAmount() + " dégâts" + " avec " + itemHeldName + " à " + entityAttacked.getName().getString() + " avec ses effets: " + trueSource.getActivePotionEffects());
                
                	}
            }
        }
    } 
}
