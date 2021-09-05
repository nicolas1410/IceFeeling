package dmz.faction.icefeeling.mod.events;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

public class IFOnPlayerDeath  {

	public IFOnPlayerDeath(LivingDeathEvent event) {
		
    	if(event.getEntity() instanceof PlayerEntity) {
    		
    		LivingEntity player = (PlayerEntity) event.getEntity();
    		
    		player.getLastDamageSource().getEntity().awardKillScore(player, 5, event.getSource());
    		
    	}
    	
	}

}
