package dmz.faction.icefeeling.items.foods;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class IFFireOpalApple extends Item {

	public IFFireOpalApple(Properties properties, Rarity rarity) {

		super(new Item.Properties().group(Main.ICEFEELING).rarity(rarity)
				.food(new Food.Builder().hunger(4).saturation(5.0F).setAlwaysEdible().build()));
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack onItemUseFinish(ItemStack stack, World world, LivingEntity entity) {
		if (hasFullSet(stack, stack.getEquipmentSlot(), entity)) {
			
			entity.addPotionEffect(new EffectInstance(Effects.STRENGTH, 1200, 4));
			entity.addPotionEffect(new EffectInstance(Effects.SPEED, 1200, 1));

		}

		return this.isFood() ? entity.onFoodEaten(world, stack) : stack;
	}

	private boolean hasFullSet(ItemStack stack, EquipmentSlotType slot, LivingEntity entity) {

		return 	   entity.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == IFItems.OPAL_HELMET.get()
				&& entity.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == IFItems.OPAL_CHESTPLATE.get()
				&& entity.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == IFItems.OPAL_LEGGINGS.get()
				&& entity.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == IFItems.OPAL_BOOTS.get();
	}

}