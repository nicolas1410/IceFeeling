package dmz.faction.icefeeling.items.tools;

import java.util.function.Supplier;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum IFItemTier implements IItemTier {
	
	//int harvestLevelIn, int Durability, float efficiency, float attackDamage, int enchantability
	ADMIN_SWORD(5, 100000, 12.0F, 11.0F, 5, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	JADE		(4, 560, 14.0F, 3.0F, 25, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	OBSIDIAN	(3, 4505, 9.0F, 6, 15, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	TITANITE	(4, 6808, 10.0F, 7, 20, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	MYTHRIL		(4, 2545, 12.0F, 8, 5, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	OPAL		(5, 1648, 11.0F, 9, 5, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	FIRE_OPAL	(0, 1412, 0, 14, 0, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	}),
	ENCHANTED_SWORD	(0, 2500, 0, 4.5F, 1, () -> 
	{
			return Ingredient.of(IFItems.ENCHANTED_GEM.get());
	});
	
		   /*IRON(2, 250, 6.0F, 2.0F, 14, () -> {
		      return Ingredient.fromItems(Items.IRON_INGOT);
		   }),
		   DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
		      return Ingredient.fromItems(Items.DIAMOND);
		   }),
		   GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
		      return Ingredient.fromItems(Items.GOLD_INGOT);
		   }),
		   NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> { === 7 ATTACK DAMAGE
		      return Ingredient.fromItems(Items.NETHERITE_INGOT);
		   });*/
	
	private final int harvestLevel;
    private final int maxUses;
    private final float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;
	
	IFItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn, int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
	      this.harvestLevel = harvestLevelIn;
	      this.maxUses = maxUsesIn;
	      this.efficiency = efficiencyIn;
	      this.attackDamage = attackDamageIn;
	      this.enchantability = enchantabilityIn;
	      this.repairMaterial = new LazyValue<>(repairMaterialIn);
	   }
	
	
	
	@Override
	public int getUses() {
		return maxUses;
	}

	@Override
	public float getSpeed() {
		return efficiency;
	}

	@Override
	public float getAttackDamageBonus() {
		return attackDamage;
	}

	@Override
	public int getLevel() {
		return harvestLevel;
	}

	@Override
	public int getEnchantmentValue() {
		return enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return repairMaterial.get();
	}


}
