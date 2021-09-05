package dmz.faction.icefeeling.data.recipes;

import java.util.function.Consumer;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;

public class IFRecipesProvider extends RecipeProvider{

	protected final DataGenerator generator;

	public IFRecipesProvider(DataGenerator gen) {
		super(gen);
		generator = gen;
	}
	
	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

		//BLOCKS
	    ShapedRecipeBuilder.shaped(IFBlocks.ROBUSIUM_BLOCK.get()).unlockedBy(getName(), has(IFItems.ROBUSIUM_PLATE.get())).pattern("rrr").pattern("ror").pattern("rrr").define('r', IFItems.ROBUSIUM_PLATE.get()).define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);;
	    ShapelessRecipeBuilder.shapeless(IFBlocks.COMPRESSED_COBBLE.get()).unlockedBy(getName(), has(Items.COBBLESTONE)).requires(Items.COBBLESTONE, 9).group("icefeeling").save(consumer);;
	    
	    
	    /*------------------------------------------------------ ITEMS ------------------------------------------------------*/

	    // VANILLA FURNACE
	    
	    CookingRecipeBuilder.smelting(Ingredient.of(IFBlocks.MYTHRIL_ORE.get().asItem()), IFItems.MYTHRIL_INGOT.get(), 1F, 200).unlockedBy(getName(), has(IFBlocks.MYTHRIL_ORE.get())).save(consumer);
	    CookingRecipeBuilder.smelting(Ingredient.of(IFBlocks.TITANITE_ORE.get().asItem()), IFItems.TITANITE_INGOT.get(), 1F, 200).unlockedBy(getName(), has(IFBlocks.TITANITE_ORE.get())).save(consumer);
	    CookingRecipeBuilder.smelting(Ingredient.of(IFItems.OBSIDIAN_FRAGMENT.get().asItem()), IFItems.OBSIDIAN_INGOT.get(), 1F, 200).unlockedBy(getName(), has(IFItems.OBSIDIAN_FRAGMENT.get())).save(consumer);
	    
	    // TODO CHANGE TO OBSIDIAN FURNACE IN THE FUTURE
	    
	    CookingRecipeBuilder.smelting(Ingredient.of(IFItems.ENDER_DUST.get().asItem()), IFItems.ENDER.get(), 1F, 300).unlockedBy(getName(), has(IFItems.ENDER_DUST.get())).save(consumer);


	    // APPLES
	    ShapedRecipeBuilder.shaped(IFItems.OPAL_APPLE.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("ooo").pattern("oxo").pattern("ooo").define('x', Items.ENCHANTED_GOLDEN_APPLE).define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);
	    
	    ShapedRecipeBuilder.shaped(IFItems.JADE_APPLE.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("jjj").pattern("jxj").pattern("jjj").define('x', Items.GOLDEN_APPLE).define('j', IFItems.JADE.get()).group("icefeeling").save(consumer);
	    
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_APPLE.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_INGOT.get())).pattern("iii").pattern("ixi").pattern("iii").define('x', Items.GOLDEN_APPLE).define('i', IFItems.OBSIDIAN_INGOT.get()).group("icefeeling").save(consumer);

	    // TOOLS
		
	    ShapedRecipeBuilder.shaped(IFItems.JADE_SWORD.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("j").pattern("j").pattern("x").define('x', IFItems.IRON_STICK.get()).define('j', IFItems.JADE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_SWORD.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("t").pattern("t").pattern("x").define('x', IFItems.IRON_STICK.get()).define('t', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_SWORD.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("o").pattern("o").pattern("x").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OPAL_SWORD.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("o").pattern("o").pattern("x").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_SWORD.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("m").pattern("m").pattern("x").define('x', IFItems.IRON_STICK.get()).define('m', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    
	    
	    // AXES
	    

	    ShapedRecipeBuilder.shaped(IFItems.JADE_AXE.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("jj").pattern("jx").pattern(" x").define('x', IFItems.IRON_STICK.get()).define('j', IFItems.JADE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_AXE.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("tt").pattern("tx").pattern(" x").define('x', IFItems.IRON_STICK.get()).define('t', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_AXE.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("oo").pattern("ox").pattern(" x").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OPAL_AXE.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("oo").pattern("ox").pattern(" x").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_AXE.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("mm").pattern("mx").pattern(" x").define('x', IFItems.IRON_STICK.get()).define('m', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    
	    
	    // SHOVELS
	    
	    
	    ShapedRecipeBuilder.shaped(IFItems.JADE_SHOVEL.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("j").pattern("x").pattern("x").define('x', IFItems.IRON_STICK.get()).define('j', IFItems.JADE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_SHOVEL.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("t").pattern("x").pattern("x").define('x', IFItems.IRON_STICK.get()).define('t', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_SHOVEL.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("o").pattern("x").pattern("x").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OPAL_SHOVEL.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("o").pattern("x").pattern("x").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_SHOVEL.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("m").pattern("x").pattern("x").define('x', IFItems.IRON_STICK.get()).define('m', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    
	    
	    // PICKAXES
	    
	    
	    ShapedRecipeBuilder.shaped(IFItems.JADE_PICKAXE.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("jjj").pattern(" x ").pattern(" x ").define('x', IFItems.IRON_STICK.get()).define('j', IFItems.JADE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_PICKAXE.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("ttt").pattern(" x ").pattern(" x ").define('x', IFItems.IRON_STICK.get()).define('t', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_PICKAXE.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("ooo").pattern(" x ").pattern(" x ").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.OPAL_PICKAXE.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("ooo").pattern(" x ").pattern(" x ").define('x', IFItems.IRON_STICK.get()).define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_PICKAXE.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("mmm").pattern(" x ").pattern(" x ").define('x', IFItems.IRON_STICK.get()).define('m', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);

	    
	    // ARMORS
	    
	    ShapedRecipeBuilder.shaped(IFItems.OPAL_HELMET.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("ooo").pattern("o o").define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.JADE_HELMET.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("ooo").pattern("o o").define('o', IFItems.JADE.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_HELMET.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("ooo").pattern("o o").define('o', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_HELMET.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("ooo").pattern("o o").define('o', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_HELMET.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("ooo").pattern("o o").define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);
	    
	    // CHESTPLATES
	    	    
	    ShapedRecipeBuilder.shaped(IFItems.OPAL_CHESTPLATE.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("o o").pattern("ooo").pattern("ooo").define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.JADE_CHESTPLATE.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("o o").pattern("ooo").pattern("ooo").define('o', IFItems.JADE.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_CHESTPLATE.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("o o").pattern("ooo").pattern("ooo").define('o', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_CHESTPLATE.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("o o").pattern("ooo").pattern("ooo").define('o', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_CHESTPLATE.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("o o").pattern("ooo").pattern("ooo").define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);
	    
	    // LEGGINGS
	    
	    ShapedRecipeBuilder.shaped(IFItems.OPAL_LEGGINGS.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("ooo").pattern("o o").pattern("o o").define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.JADE_LEGGINGS.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("ooo").pattern("o o").pattern("o o").define('o', IFItems.JADE.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_LEGGINGS.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("ooo").pattern("o o").pattern("o o").define('o', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_LEGGINGS.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("ooo").pattern("o o").pattern("o o").define('o', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_LEGGINGS.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("ooo").pattern("o o").pattern("o o").define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);
	   	    
	    // BOOTS
	    	    
	    ShapedRecipeBuilder.shaped(IFItems.OPAL_BOOTS.get()).unlockedBy(getName(), has(IFItems.OPAL.get())).pattern("o o").pattern("o o").define('o', IFItems.OPAL.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.JADE_BOOTS.get()).unlockedBy(getName(), has(IFItems.JADE.get())).pattern("o o").pattern("o o").define('o', IFItems.JADE.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.MYTHRIL_BOOTS.get()).unlockedBy(getName(), has(IFItems.MYTHRIL_INGOT.get())).pattern("o o").pattern("o o").define('o', IFItems.MYTHRIL_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.TITANITE_BOOTS.get()).unlockedBy(getName(), has(IFItems.TITANITE_INGOT.get())).pattern("o o").pattern("o o").define('o', IFItems.TITANITE_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_BOOTS.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("o o").pattern("o o").define('o', IFItems.OBSIDIAN_PLATE.get()).group("icefeeling").save(consumer);
	    
	    //MISC
	    
	    ShapedRecipeBuilder.shaped(Items.ENDER_PEARL).unlockedBy(getName(), has(IFItems.ENDER.get())).pattern("xex").pattern("eee").pattern("xex").define('e', IFItems.ENDER.get()).define('x', IFItems.XP_FRAGMENT.get()).group("icefeeling").save(consumer);
	    
	    ShapedRecipeBuilder.shaped(IFItems.ENDER_STICK.get()).unlockedBy(getName(), has(IFItems.ENDER.get())).pattern("e").pattern("e").define('e', IFItems.ENDER.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.IRON_STICK.get()).unlockedBy(getName(), has(Items.IRON_INGOT)).pattern("i").pattern("i").define('i', Items.IRON_INGOT).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.STEEL_STICK.get()).unlockedBy(getName(), has(IFItems.STEEL_INGOT.get())).pattern("s").pattern("s").define('e', IFItems.STEEL_INGOT.get()).group("icefeeling").save(consumer);

	    ShapedRecipeBuilder.shaped(IFItems.ROBUSIUM_PLATE.get()).unlockedBy(getName(), has(IFItems.ROBUSIUM.get())).pattern("rr").pattern("rr").define('r', IFItems.ROBUSIUM.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_PLATE.get()).unlockedBy(getName(), has(IFItems.OBSIDIAN_PLATE.get())).pattern("oo").pattern("oo").define('o', IFItems.OBSIDIAN_INGOT.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.OBSIDIAN_FRAGMENT.get(), 2).unlockedBy(getName(), has(IFItems.STEEL_HAMMER.get())).pattern("ho").define('o', Items.OBSIDIAN).define('h', IFItems.STEEL_HAMMER.get()).group("icefeeling").save(consumer);
	    ShapedRecipeBuilder.shaped(IFItems.STEEL_HAMMER.get()).unlockedBy(getName(), has(IFItems.STEEL_INGOT.get())).pattern(" nf").pattern(" sn").pattern("s  ").define('s', IFItems.STEEL_STICK.get()).define('f', IFItems.STEEL_INGOT.get()).define('n', Items.NETHER_BRICK).group("icefeeling").save(consumer);
	    
	    ShapedRecipeBuilder.shaped(Items.EXPERIENCE_BOTTLE).unlockedBy(getName(), has(IFItems.ENCHANTED_BOTTLE.get()))
	    .pattern("XBX")
	    .pattern("VXV")	
	    .pattern("GEG")
	    .define('B', IFItems.ENCHANTED_BOTTLE.get())
	    .define('X', IFItems.XP_FRAGMENT.get())
	    .define('G', Items.GOLD_INGOT)
	    .define('V', Items.GLASS_BOTTLE)
	    .define('E', Items.EMERALD)
	    .group("icefeeling").save(consumer);;
	    
	    ShapedRecipeBuilder.shaped(IFItems.ENCHANTED_BOTTLE.get()).unlockedBy(getName(), has(IFItems.ENCHANTED_GEM.get()))
	    .pattern("G")
	    .pattern("D")
	    .pattern("B")
	    .define('G', IFItems.ENCHANTED_GEM.get())
	    .define('D', Items.DIAMOND)
	    .define('B', Items.EXPERIENCE_BOTTLE)
	    .group("icefeeling").save(consumer);;
	    
		
		} 

}	

