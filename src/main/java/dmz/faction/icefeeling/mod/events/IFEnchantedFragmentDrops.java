package dmz.faction.icefeeling.mod.events;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.SilverfishEntity;
import net.minecraft.entity.monster.WitherSkeletonEntity;
import net.minecraft.entity.passive.BatEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFEnchantedFragmentDrops {

	@SubscribeEvent
	public static void onLivingDrop(final LivingDropsEvent e) {
		
		ItemStack FRAGMENT = new ItemStack(IFItems.ENCHANTED_FRAGMENT.get());

		System.out.println("onLivingDrop");
		if (e.getEntity() instanceof BatEntity) {
			System.out.println("BatEntity");
			double a = Math.random();

			if (a <= 0.05) {
				System.out.println("a <= 0.5");
				drops(FRAGMENT, e);
			}
		}

		if (e.getEntity() instanceof GhastEntity) {
			System.out.println("GhastEntity");
			double a = Math.random();

			if (a <= 0.05) {
				System.out.println("a <= 0.1");
				drops(FRAGMENT, e);
			}
		}

		if (e.getEntity() instanceof WitherSkeletonEntity) {
			System.out.println("WitherSkeletonEntity");
			double a = Math.random();

			if (a <= 0.005) {
				System.out.println("a <= 0.08");
				drops(FRAGMENT, e);
			}
		}

		if (e.getEntity() instanceof PlayerEntity) {
			System.out.println("PlayerEntity");
			double a = Math.random();

			if (a <= 0.0015) {
				System.out.println("a <= 0.0035");
				drops(FRAGMENT, e);
			}
		}

		if (e.getEntity() instanceof SilverfishEntity) 
		{
			System.out.println("Silverfish");
			double a = Math.random();

			if (a <= 0.04) 
			{
				System.out.println("a <= 0.06");
				drops(FRAGMENT, e);
			}
		}
	}

	

	private static boolean drops(ItemStack stack, LivingDropsEvent event) {
		
		return event.getDrops().add(new ItemEntity(event.getEntity().world, event.getEntity().getPosX(),
				event.getEntity().getPosY(), event.getEntity().getPosZ(), stack));
	}
	
}
