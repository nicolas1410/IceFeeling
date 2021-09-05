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

		super(new Item.Properties().tab(Main.ICEFEELING).rarity(rarity)
				.food(new Food.Builder().nutrition(4).saturationMod(5.0F).alwaysEat().build()));
	}

	@Override
	public boolean isFoil(ItemStack stack) {
		return true;
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, World world, LivingEntity entity) {
		if (hasFullSet(stack, stack.getEquipmentSlot(), entity)) {
			
			entity.addEffect(new EffectInstance(Effects.DAMAGE_BOOST, 1200, 4));
			entity.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 1200, 1));

		}

		return this.isEdible() ? entity.eat(world, stack) : stack;
	}

	private boolean hasFullSet(ItemStack stack, EquipmentSlotType slot, LivingEntity entity) {

		return 	   entity.getItemBySlot(EquipmentSlotType.HEAD).getItem() == IFItems.OPAL_HELMET.get()
				&& entity.getItemBySlot(EquipmentSlotType.CHEST).getItem() == IFItems.OPAL_CHESTPLATE.get()
				&& entity.getItemBySlot(EquipmentSlotType.LEGS).getItem() == IFItems.OPAL_LEGGINGS.get()
				&& entity.getItemBySlot(EquipmentSlotType.FEET).getItem() == IFItems.OPAL_BOOTS.get();
	}

}