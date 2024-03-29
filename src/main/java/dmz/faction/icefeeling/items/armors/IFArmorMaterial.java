package dmz.faction.icefeeling.items.armors;

import java.util.function.Supplier;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

public enum IFArmorMaterial implements IArmorMaterial {

	
	JADE("jade", 40, new int[]{2, 4, 6, 3}, 20, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0F, () -> { //15 points
		return Ingredient.of(IFItems.JADE.get());
	}),
	TITANITE("titanite", 150, new int[]{2, 5, 6, 3}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0F, () -> { //16 points
		return Ingredient.of(IFItems.TITANITE_INGOT.get());
	}),
	MYTHRIL("mythril", 40, new int[]{3, 6, 7, 4}, 15, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0F, () -> { //20 points
		return Ingredient.of(IFItems.MYTHRIL_INGOT.get());
	}),

	OBSIDIAN("obsidian", 80, new int[]{4, 7, 9, 4}, 7, SoundEvents.ARMOR_EQUIP_GOLD, 2.0F, 0.1F, () -> { //24 points
		return Ingredient.of(IFItems.OBSIDIAN_INGOT.get());
	}),
	OPAL("opal", 60, new int[]{5, 8, 10, 5}, 5, SoundEvents.ARMOR_EQUIP_GOLD, 3.0F, 0.1F, () -> { //28 points
		return Ingredient.of(IFItems.OPAL.get());
	});
	
	//NETHERITE("netherite", 37, new int[]{3, 6, 8, 3}, 15, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.0F, 0.1F, () -> { 20
		 

	private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
	private final String name;
	private final int maxDamageFactor;
	private final int[] damageReductionAmountArray;
	private final int enchantability;
	private final SoundEvent soundEvent;
	private final float toughness;
	private final float knockbackResistance;
	private final LazyValue<Ingredient> repairMaterial;


	private IFArmorMaterial(String name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, Supplier<Ingredient> repairMaterial) {
		this.name = name;
		this.maxDamageFactor = maxDamageFactor;
		this.damageReductionAmountArray = damageReductionAmountArray;
		this.enchantability = enchantability;
		this.soundEvent = soundEvent;
		this.toughness = toughness;
		this.knockbackResistance = knockbackResistance;
		this.repairMaterial = new LazyValue<>(repairMaterial);
		   }

	@Override
	public int getDurabilityForSlot(EquipmentSlotType slotIn) 
	{
			return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
	}

	@Override
	public int getDefenseForSlot(EquipmentSlotType slotIn) 
	{
			return this.damageReductionAmountArray[slotIn.getIndex()];
	}

	@Override
	public int getEnchantmentValue()
	{
			return this.enchantability;
	}

	@Override
	public SoundEvent getEquipSound() {
			return this.soundEvent;
	}

	@Override
	public Ingredient getRepairIngredient() {
			return this.repairMaterial.get();
	}

	public String getName() {
			return this.name;
	}

	public float getToughness() {
			return this.toughness;
	}

	/**
	 * Gets the percentage of knockback resistance provided by armor of the material. 
	*/
	public float getKnockbackResistance() {
			return this.knockbackResistance;
	}

}

