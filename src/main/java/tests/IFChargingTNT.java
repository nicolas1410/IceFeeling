package tests;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.LightningBoltEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class IFChargingTNT {

	@SubscribeEvent
	public static void onHitByLightning(EntityJoinWorldEvent event) {
		
		
		
		if (event.getEntity() instanceof LightningBoltEntity) 
		{
			Entity entity = event.getEntity();
			World world = event.getWorld();
			if (!world.isRemote) 
			{

					BlockPos blockpos = entity.getPosition().down();
					Block tnt = Blocks.TNT;
	
					if (world.getBlockState(blockpos) == tnt.getDefaultState()) 
					{
	
						System.out.println("onTntCharged");
	
						world.setBlockState(blockpos, IFBlocks.CHARGED_TNT.get().getDefaultState(), 3);
	
					}

			}
		}
	}
}
