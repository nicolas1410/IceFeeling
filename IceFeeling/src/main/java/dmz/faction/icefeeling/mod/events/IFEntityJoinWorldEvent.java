package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.entities.chargedtnt.IFChargedTNTEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFEntityJoinWorldEvent {

	@SubscribeEvent
    public static void onEntityJoinWorld(final EntityJoinWorldEvent e) {
    	
    	if(e.getEntity() instanceof TNTEntity) {
    		
    		double entityPosX = e.getEntity().getX();
    		double entityPosY = e.getEntity().getY();
    		double entityPosZ = e.getEntity().getZ();
    		
    		World world = e.getWorld();
    		e.getEntity().remove();
    		world.addFreshEntity(new IFChargedTNTEntity(world, entityPosX, entityPosY, entityPosZ, null));
    		
    	}
    	
    }

}
