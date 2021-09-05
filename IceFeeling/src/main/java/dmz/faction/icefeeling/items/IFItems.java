package dmz.faction.icefeeling.items;

import dmz.faction.icefeeling.items.armors.IFArmorBasic;
import dmz.faction.icefeeling.items.armors.IFArmorMaterial;
import dmz.faction.icefeeling.items.armors.IFJadeArmor;
import dmz.faction.icefeeling.items.armors.IFObsidianArmor;
import dmz.faction.icefeeling.items.armors.IFOpalArmor;
import dmz.faction.icefeeling.items.consumables.IFBurnableItems;
import dmz.faction.icefeeling.items.foods.IFApples;
import dmz.faction.icefeeling.items.foods.IFFireOpalApple;
import dmz.faction.icefeeling.items.specials.IFKnockerItem;
import dmz.faction.icefeeling.items.specials.IFLevitorItem;
import dmz.faction.icefeeling.items.tools.IFEnchantedSword;
import dmz.faction.icefeeling.items.tools.IFHammer;
import dmz.faction.icefeeling.items.tools.IFItemTier;
import dmz.faction.icefeeling.items.tools.IFSwordItem;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.mod.registry.IFEntityRegister;
import dmz.faction.icefeeling.world.IFModSoundEvents;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.Rarity;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IFItems {
	
	 
    public static Rarity ADMIN = Rarity.create("Admin", TextFormatting.DARK_PURPLE);
    public static Rarity LEGENDARY = Rarity.create("Legendary", TextFormatting.GOLD);
    public static Rarity MYTHICAL = Rarity.create("Mythical", TextFormatting.BLUE);
    public static Rarity ANCIENT = Rarity.create("Ancient", TextFormatting.YELLOW);
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Main.MOD_ID);
	
	/*------------------------------------------------------ TO DELETE ------------------------------------------------------*/
	
	public static final RegistryObject<Item> ADMIN_SWORD = ITEMS.register("admin_sword", () -> 
	new SwordItem(IFItemTier.ADMIN_SWORD, 1000, 24, new Item.Properties().tab(Main.ICEFEELING).rarity(ADMIN)));
	
	/*------------------------------------------------------ SPECIALS ------------------------------------------------------*/

	public static final RegistryObject<Item> KNOCKER = ITEMS.register("knocker", () -> new IFKnockerItem(new Item.Properties().tab(Main.ICEFEELING).stacksTo(1).rarity(ANCIENT)));
	public static final RegistryObject<Item> LEVITATOR = ITEMS.register("levitator", () -> new IFLevitorItem(new Item.Properties().tab(Main.ICEFEELING).stacksTo(1).rarity(ANCIENT)));
	public static final RegistryObject<Item> LIGHTNING_JAR = ITEMS.register("lightning_jar", () -> new Item(new Item.Properties().tab(Main.ICEFEELING).stacksTo(1).rarity(LEGENDARY)));

	
	/*------------------------------------------------------ GENERAL ------------------------------------------------------*/		
	
	public static final RegistryObject<Item> JADE = ITEMS.register("jade", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OPAL = ITEMS.register("opal", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> FIRE_OPAL = ITEMS.register("fire_opal", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> MYTHRIL_INGOT = ITEMS.register("mythril_ingot", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> TITANITE_INGOT = ITEMS.register("titanite_ingot", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> ENDER_DUST = ITEMS.register("ender_dust", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> ENDER = ITEMS.register("ender", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));


	public static final RegistryObject<Item> OBSIDIAN_INGOT = ITEMS.register("obsidian_ingot", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OBSIDIAN_PLATE = ITEMS.register("obsidian_plate", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OBSIDIAN_FRAGMENT = ITEMS.register("obsidian_fragment", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OBSIDIAN_STICK = ITEMS.register("obsidian_stick", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	
	public static final RegistryObject<Item> IRON_STICK = ITEMS.register("iron_stick", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> STEEL_STICK = ITEMS.register("steel_stick", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> ENDER_STICK = ITEMS.register("ender_stick", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));

	public static final RegistryObject<Item> ROBUSIUM = ITEMS.register("robusium", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> ROBUSIUM_PLATE = ITEMS.register("robusium_plate", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	
	public static final RegistryObject<Item> ENCHANTED_FRAGMENT = ITEMS.register("enchanted_fragment", () -> new Item(new Item.Properties().tab(Main.ICEFEELING).rarity(ANCIENT)));
	public static final RegistryObject<Item> ENCHANTED_GEM = ITEMS.register("enchanted_gem", () -> new Item(new Item.Properties().tab(Main.ICEFEELING).rarity(MYTHICAL)));
	
	public static final RegistryObject<Item> SOLARIUM = ITEMS.register("solarium", () -> new IFBurnableItems(new Item.Properties().tab(Main.ICEFEELING)));
	
	public static final RegistryObject<Item> RESIN = ITEMS.register("resin", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> POKEBALL = ITEMS.register("pokeball", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> POKELAUNCHER = ITEMS.register("pokelauncher", () -> new Item(new Item.Properties().tab(Main.ICEFEELING).stacksTo(1)));
	
	public static final RegistryObject<Item> MUSHPANG_SPAWN_EGG = ITEMS.register("mushpang_egg", () -> new IFSpawnEggItem(IFEntityRegister.MUSHPANG, 9999878, 454545, (new Item.Properties().tab(Main.ICEFEELING))));
	
	public static final RegistryObject<Item> XP_FRAGMENT = ITEMS.register("xp_fragment", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));

	public static final RegistryObject<IFHammer> STEEL_HAMMER = ITEMS.register("steel_hammer", () -> new IFHammer(new Item.Properties().tab(Main.ICEFEELING).stacksTo(1).durability(100)));
	public static final RegistryObject<IFHammer> ENCHANTED_BOTTLE = ITEMS.register("enchanted_bottle", () -> new IFHammer(new Item.Properties().tab(Main.ICEFEELING).stacksTo(1).durability(320).rarity(ANCIENT)));



	/*------------------------------------------------------ OPAL STUFF ------------------------------------------------------*/
	
	public static final RegistryObject<Item> OPAL_PICKAXE = ITEMS.register("opal_pickaxe", () -> new PickaxeItem(IFItemTier.OPAL, -2, -2.8F, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> OPAL_AXE = ITEMS.register("opal_axe", () -> new AxeItem(IFItemTier.OPAL, 5, -3.0F, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> OPAL_SHOVEL = ITEMS.register("opal_shovel", () -> new ShovelItem(IFItemTier.OPAL, -1.5F, -3.0F, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	public static final RegistryObject<Item> OPAL_SWORD = ITEMS.register("opal_sword", () -> new IFSwordItem(IFItemTier.OPAL, 0, 26, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	
	public static final RegistryObject<Item> FIRE_OPAL_SWORD = ITEMS.register("fire_opal_sword", () -> new IFSwordItem(IFItemTier.FIRE_OPAL, 0, 46, new Item.Properties().tab(Main.ICEFEELING).setNoRepair().fireResistant().rarity(LEGENDARY)));
	
	public static final RegistryObject<Item> ENCHANTED_DAGGER = ITEMS.register("enchanted_dagger", () -> new IFEnchantedSword(IFItemTier.ENCHANTED_SWORD, 0, 26, new Item.Properties().tab(Main.ICEFEELING).setNoRepair().fireResistant().rarity(LEGENDARY)));
	
	public static final RegistryObject<ArmorItem> OPAL_HELMET = ITEMS.register("opal_helmet", () -> new IFOpalArmor(IFArmorMaterial.OPAL, EquipmentSlotType.HEAD, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	public static final RegistryObject<ArmorItem> OPAL_CHESTPLATE = ITEMS.register("opal_chestplate", () -> new IFOpalArmor(IFArmorMaterial.OPAL, EquipmentSlotType.CHEST, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	public static final RegistryObject<ArmorItem> OPAL_LEGGINGS = ITEMS.register("opal_leggings", () -> new IFOpalArmor(IFArmorMaterial.OPAL, EquipmentSlotType.LEGS, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	public static final RegistryObject<ArmorItem> OPAL_BOOTS = ITEMS.register("opal_boots", () -> new IFOpalArmor(IFArmorMaterial.OPAL, EquipmentSlotType.FEET, new Item.Properties().tab(Main.ICEFEELING).rarity(Rarity.RARE)));
	
	/*------------------------------------------------------ MYTHRIL STUFF ------------------------------------------------------*/
	
	public static final RegistryObject<Item> MYTHRIL_PICKAXE = ITEMS.register("mythril_pickaxe", () -> new PickaxeItem(IFItemTier.MYTHRIL, -2, -2.8F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> MYTHRIL_AXE = ITEMS.register("mythril_axe", () -> new AxeItem(IFItemTier.MYTHRIL, 5, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> MYTHRIL_SHOVEL = ITEMS.register("mythril_shovel", () -> new ShovelItem(IFItemTier.MYTHRIL, -1.5F, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> MYTHRIL_SWORD = ITEMS.register("mythril_sword", () -> new IFSwordItem(IFItemTier.MYTHRIL, 0, 26, new Item.Properties().tab(Main.ICEFEELING)));
	
	public static final RegistryObject<ArmorItem> MYTHRIL_HELMET = ITEMS.register("mythril_helmet", () -> new IFArmorBasic(IFArmorMaterial.MYTHRIL, EquipmentSlotType.HEAD, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> MYTHRIL_CHESTPLATE = ITEMS.register("mythril_chestplate", () -> new IFArmorBasic(IFArmorMaterial.MYTHRIL, EquipmentSlotType.CHEST, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> MYTHRIL_LEGGINGS = ITEMS.register("mythril_leggings", () -> new IFArmorBasic(IFArmorMaterial.MYTHRIL, EquipmentSlotType.LEGS, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> MYTHRIL_BOOTS = ITEMS.register("mythril_boots", () -> new IFArmorBasic(IFArmorMaterial.MYTHRIL, EquipmentSlotType.FEET, new Item.Properties().tab(Main.ICEFEELING)));
	
	/*------------------------------------------------------ JADE STUFF ------------------------------------------------------*/
	
	public static final RegistryObject<Item> JADE_PICKAXE = ITEMS.register("jade_pickaxe", () -> new PickaxeItem(IFItemTier.JADE, -2, -2.8F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> JADE_AXE = ITEMS.register("jade_axe", () -> new AxeItem(IFItemTier.JADE, 5, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> JADE_SHOVEL = ITEMS.register("jade_shovel", () -> new ShovelItem(IFItemTier.JADE, -1.5F, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> JADE_SWORD = ITEMS.register("jade_sword", () -> new IFSwordItem(IFItemTier.JADE, 0, 26, new Item.Properties().tab(Main.ICEFEELING)));
		
	public static final RegistryObject<ArmorItem> JADE_HELMET = ITEMS.register("jade_helmet", () -> new IFJadeArmor(IFArmorMaterial.JADE, EquipmentSlotType.HEAD, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> JADE_CHESTPLATE = ITEMS.register("jade_chestplate", () -> new IFJadeArmor(IFArmorMaterial.JADE, EquipmentSlotType.CHEST, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> JADE_LEGGINGS = ITEMS.register("jade_leggings", () -> new IFJadeArmor(IFArmorMaterial.JADE, EquipmentSlotType.LEGS, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> JADE_BOOTS = ITEMS.register("jade_boots", () -> new IFJadeArmor(IFArmorMaterial.JADE, EquipmentSlotType.FEET, new Item.Properties().tab(Main.ICEFEELING)));
	
	/*------------------------------------------------------ TITANITE STUFF ------------------------------------------------------*/
	
	public static final RegistryObject<Item> TITANITE_PICKAXE = ITEMS.register("titanite_pickaxe", () -> new PickaxeItem(IFItemTier.TITANITE, -2, -2.8F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> TITANITE_AXE = ITEMS.register("titanite_axe", () -> new AxeItem(IFItemTier.TITANITE, 5, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> TITANITE_SHOVEL = ITEMS.register("titanite_shovel", () -> new ShovelItem(IFItemTier.TITANITE, -1.5F, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> TITANITE_SWORD = ITEMS.register("titanite_sword", () -> new IFSwordItem(IFItemTier.TITANITE, 0, 26, new Item.Properties().tab(Main.ICEFEELING)));
		
	public static final RegistryObject<ArmorItem> TITANITE_HELMET = ITEMS.register("titanite_helmet", () -> new IFArmorBasic(IFArmorMaterial.TITANITE, EquipmentSlotType.HEAD, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> TITANITE_CHESTPLATE = ITEMS.register("titanite_chestplate", () -> new IFArmorBasic(IFArmorMaterial.TITANITE, EquipmentSlotType.CHEST, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> TITANITE_LEGGINGS = ITEMS.register("titanite_leggings", () -> new IFArmorBasic(IFArmorMaterial.TITANITE, EquipmentSlotType.LEGS, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> TITANITE_BOOTS = ITEMS.register("titanite_boots", () -> new IFArmorBasic(IFArmorMaterial.TITANITE, EquipmentSlotType.FEET, new Item.Properties().tab(Main.ICEFEELING)));
	
	/*------------------------------------------------------ OBSIDIAN STUFF ------------------------------------------------------*/
	
	public static final RegistryObject<Item> OBSIDIAN_PICKAXE = ITEMS.register("obsidian_pickaxe", () -> new PickaxeItem(IFItemTier.OBSIDIAN, -2, -2.8F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OBSIDIAN_AXE = ITEMS.register("obsidian_axe", () -> new AxeItem(IFItemTier.OBSIDIAN, 5, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OBSIDIAN_SHOVEL = ITEMS.register("obsidian_shovel", () -> new ShovelItem(IFItemTier.OBSIDIAN, -1.5F, -3.0F, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> OBSIDIAN_SWORD = ITEMS.register("obsidian_sword", () -> new IFSwordItem(IFItemTier.OBSIDIAN, 0, 26, new Item.Properties().tab(Main.ICEFEELING)));
		
	public static final RegistryObject<ArmorItem> OBSIDIAN_HELMET = ITEMS.register("obsidian_helmet", () -> new IFObsidianArmor(IFArmorMaterial.OBSIDIAN, EquipmentSlotType.HEAD, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> OBSIDIAN_CHESTPLATE = ITEMS.register("obsidian_chestplate", () -> new IFObsidianArmor(IFArmorMaterial.OBSIDIAN, EquipmentSlotType.CHEST, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> OBSIDIAN_LEGGINGS = ITEMS.register("obsidian_leggings", () -> new IFObsidianArmor(IFArmorMaterial.OBSIDIAN, EquipmentSlotType.LEGS, new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<ArmorItem> OBSIDIAN_BOOTS = ITEMS.register("obsidian_boots", () -> new IFObsidianArmor(IFArmorMaterial.OBSIDIAN, EquipmentSlotType.FEET, new Item.Properties().tab(Main.ICEFEELING)));
	
	/*------------------------------------------------------ FOOD ------------------------------------------------------*/
	public static final RegistryObject<Item> FIRE_OPAL_APPLE = ITEMS.register("fire_opal_apple", () -> new IFFireOpalApple(new Item.Properties(), Rarity.EPIC));
	
	public static final RegistryObject<Item> OPAL_APPLE = ITEMS.register("opal_apple", () -> new IFApples(new Item.Properties(), 
			Effects.ABSORPTION, 2400, 1, 1.0F, 
			Effects.FIRE_RESISTANCE, 6000, 0, 1.0F, 
			Effects.REGENERATION, 400, 1, 1.0F, 
			Effects.DAMAGE_RESISTANCE, 6000, 0, 1, Rarity.EPIC 
			));
	
	public static final RegistryObject<Item> OBSIDIAN_APPLE = ITEMS.register("obsidian_apple", () -> new IFApples(new Item.Properties(), 
			Effects.ABSORPTION, 2400, 1, 1.0F, 
			Effects.FIRE_RESISTANCE, 9600, 0, 1.0F, 
			Effects.MOVEMENT_SLOWDOWN, 9600, 0, 1.0F, 
			Effects.DAMAGE_RESISTANCE, 6000, 2, 1.0F, 
			Effects.MOVEMENT_SLOWDOWN, 6000, 0, 1.0F,
			Rarity.EPIC
			));
	
	public static final RegistryObject<Item> JADE_APPLE = ITEMS.register("jade_apple", () -> new IFApples(new Item.Properties(), 
			Effects.MOVEMENT_SPEED, 4800, 0, 1.0F, 
			Effects.FIRE_RESISTANCE, 6000, 0, 1.0F, 
			Effects.DIG_SPEED, 2400, 0, 1.0F, 
			Effects.HUNGER, 4800, 0, 1.0F, Rarity.COMMON 
			));

	public static final RegistryObject<Item> GINGERBREAD = ITEMS.register("gingerbread", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> CUPCAKE = ITEMS.register("cupcake", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> HONEY_POT = ITEMS.register("honey_pot", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	public static final RegistryObject<Item> STEAK_HONEY = ITEMS.register("steak_honey", () -> new Item(new Item.Properties().tab(Main.ICEFEELING)));
	
	public static final RegistryObject<Item> SHIT = ITEMS.register("shit", () -> new Item(new Item.Properties().tab(Main.ICEFEELING).food(new Food.Builder().nutrition(0).saturationMod(0)
			.effect( () -> new EffectInstance(Effects.JUMP, 200, 0), 1.0F).build())));

	/*------------------------------------------------------ DISC ------------------------------------------------------*/

	
	public static final RegistryObject<MusicDiscItem> SANS_COEUR = ITEMS.register("sans_coeur", () -> new MusicDiscItem(15, IFModSoundEvents.SANS_COEUR, new Item.Properties().tab(Main.ICEFEELING).stacksTo(1).rarity(ANCIENT)));
	
	
	
	
}
