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
	protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {

		//BLOCKS
	    ShapedRecipeBuilder.shapedRecipe(IFBlocks.ROBUSIUM_BLOCK.get()).addCriterion(getName(), hasItem(IFItems.ROBUSIUM_PLATE.get())).patternLine("rrr").patternLine("ror").patternLine("rrr").key('r', IFItems.ROBUSIUM_PLATE.get()).key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);;
	    ShapelessRecipeBuilder.shapelessRecipe(IFBlocks.COMPRESSED_COBBLE.get()).addCriterion(getName(), hasItem(Items.COBBLESTONE)).addIngredient(Items.COBBLESTONE, 9).setGroup("icefeeling").build(consumer);;
	    
	    
	    /*------------------------------------------------------ ITEMS ------------------------------------------------------*/

	    // VANILLA FURNACE
	    
	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(IFBlocks.MYTHRIL_ORE.get().asItem()), IFItems.MYTHRIL_INGOT.get(), 1F, 200).addCriterion(getName(), hasItem(IFBlocks.MYTHRIL_ORE.get())).build(consumer);
	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(IFBlocks.TITANITE_ORE.get().asItem()), IFItems.TITANITE_INGOT.get(), 1F, 200).addCriterion(getName(), hasItem(IFBlocks.TITANITE_ORE.get())).build(consumer);
	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(IFItems.OBSIDIAN_FRAGMENT.get().asItem()), IFItems.OBSIDIAN_INGOT.get(), 1F, 200).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_FRAGMENT.get())).build(consumer);
	    
	    // TODO CHANGE TO OBSIDIAN FURNACE IN THE FUTURE
	    
	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(IFItems.ENDER_DUST.get().asItem()), IFItems.ENDER.get(), 1F, 300).addCriterion(getName(), hasItem(IFItems.ENDER_DUST.get())).build(consumer);


	    // APPLES
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_APPLE.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("ooo").patternLine("oxo").patternLine("ooo").key('x', Items.ENCHANTED_GOLDEN_APPLE).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_APPLE.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("jjj").patternLine("jxj").patternLine("jjj").key('x', Items.GOLDEN_APPLE).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_APPLE.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_INGOT.get())).patternLine("iii").patternLine("ixi").patternLine("iii").key('x', Items.GOLDEN_APPLE).key('i', IFItems.OBSIDIAN_INGOT.get()).setGroup("icefeeling").build(consumer);

	    // TOOLS
		
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_SWORD.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("j").patternLine("j").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_SWORD.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("t").patternLine("t").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('t', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_SWORD.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("o").patternLine("o").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_SWORD.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("o").patternLine("o").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_SWORD.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("m").patternLine("m").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('m', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    
	    
	    // AXES
	    

	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_AXE.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("jj").patternLine("jx").patternLine(" x").key('x', IFItems.IRON_STICK.get()).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_AXE.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("tt").patternLine("tx").patternLine(" x").key('x', IFItems.IRON_STICK.get()).key('t', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_AXE.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("oo").patternLine("ox").patternLine(" x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_AXE.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("oo").patternLine("ox").patternLine(" x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_AXE.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("mm").patternLine("mx").patternLine(" x").key('x', IFItems.IRON_STICK.get()).key('m', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    
	    
	    // SHOVELS
	    
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_SHOVEL.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("j").patternLine("x").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_SHOVEL.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("t").patternLine("x").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('t', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_SHOVEL.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("o").patternLine("x").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_SHOVEL.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("o").patternLine("x").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_SHOVEL.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("m").patternLine("x").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('m', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    
	    
	    // PICKAXES
	    
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_PICKAXE.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("jjj").patternLine(" x ").patternLine(" x ").key('x', IFItems.IRON_STICK.get()).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_PICKAXE.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("ttt").patternLine(" x ").patternLine(" x ").key('x', IFItems.IRON_STICK.get()).key('t', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_PICKAXE.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("ooo").patternLine(" x ").patternLine(" x ").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_PICKAXE.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("ooo").patternLine(" x ").patternLine(" x ").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_PICKAXE.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("mmm").patternLine(" x ").patternLine(" x ").key('x', IFItems.IRON_STICK.get()).key('m', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);

	    
	    // ARMORS
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_HELMET.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("ooo").patternLine("o o").key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_HELMET.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("ooo").patternLine("o o").key('o', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_HELMET.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("ooo").patternLine("o o").key('o', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_HELMET.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("ooo").patternLine("o o").key('o', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_HELMET.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("ooo").patternLine("o o").key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);
	    
	    // CHESTPLATES
	    	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_CHESTPLATE.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("o o").patternLine("ooo").patternLine("ooo").key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_CHESTPLATE.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("o o").patternLine("ooo").patternLine("ooo").key('o', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_CHESTPLATE.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("o o").patternLine("ooo").patternLine("ooo").key('o', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_CHESTPLATE.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("o o").patternLine("ooo").patternLine("ooo").key('o', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_CHESTPLATE.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("o o").patternLine("ooo").patternLine("ooo").key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);
	    
	    // LEGGINGS
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_LEGGINGS.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("ooo").patternLine("o o").patternLine("o o").key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_LEGGINGS.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("ooo").patternLine("o o").patternLine("o o").key('o', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_LEGGINGS.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("ooo").patternLine("o o").patternLine("o o").key('o', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_LEGGINGS.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("ooo").patternLine("o o").patternLine("o o").key('o', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_LEGGINGS.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("ooo").patternLine("o o").patternLine("o o").key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);
	   	    
	    // BOOTS
	    	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_BOOTS.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("o o").patternLine("o o").key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_BOOTS.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("o o").patternLine("o o").key('o', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_BOOTS.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("o o").patternLine("o o").key('o', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_BOOTS.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("o o").patternLine("o o").key('o', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_BOOTS.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("o o").patternLine("o o").key('o', IFItems.OBSIDIAN_PLATE.get()).setGroup("icefeeling").build(consumer);
	    
	    //MISC
	    
	    ShapedRecipeBuilder.shapedRecipe(Items.ENDER_PEARL).addCriterion(getName(), hasItem(IFItems.ENDER.get())).patternLine("xex").patternLine("eee").patternLine("xex").key('e', IFItems.ENDER.get()).key('x', IFItems.XP_FRAGMENT.get()).setGroup("icefeeling").build(consumer);
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.ENDER_STICK.get()).addCriterion(getName(), hasItem(IFItems.ENDER.get())).patternLine("e").patternLine("e").key('e', IFItems.ENDER.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.IRON_STICK.get()).addCriterion(getName(), hasItem(Items.IRON_INGOT)).patternLine("i").patternLine("i").key('i', Items.IRON_INGOT).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.STEEL_STICK.get()).addCriterion(getName(), hasItem(IFItems.STEEL_INGOT.get())).patternLine("s").patternLine("s").key('e', IFItems.STEEL_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.ROBUSIUM_PLATE.get()).addCriterion(getName(), hasItem(IFItems.ROBUSIUM.get())).patternLine("rr").patternLine("rr").key('r', IFItems.ROBUSIUM.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_PLATE.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_PLATE.get())).patternLine("oo").patternLine("oo").key('o', IFItems.OBSIDIAN_INGOT.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_FRAGMENT.get(), 2).addCriterion(getName(), hasItem(IFItems.STEEL_HAMMER.get())).patternLine("ho").key('o', Items.OBSIDIAN).key('h', IFItems.STEEL_HAMMER.get()).setGroup("icefeeling").build(consumer);
	    ShapedRecipeBuilder.shapedRecipe(IFItems.STEEL_HAMMER.get()).addCriterion(getName(), hasItem(IFItems.STEEL_INGOT.get())).patternLine(" nf").patternLine(" sn").patternLine("s  ").key('s', IFItems.STEEL_STICK.get()).key('f', IFItems.STEEL_INGOT.get()).key('n', Items.NETHER_BRICK).setGroup("icefeeling").build(consumer);
	    
	    ShapedRecipeBuilder.shapedRecipe(Items.EXPERIENCE_BOTTLE).addCriterion(getName(), hasItem(IFItems.ENCHANTED_BOTTLE.get()))
	    .patternLine("XBX")
	    .patternLine("VXV")	
	    .patternLine("GEG")
	    .key('B', IFItems.ENCHANTED_BOTTLE.get())
	    .key('X', IFItems.XP_FRAGMENT.get())
	    .key('G', Items.GOLD_INGOT)
	    .key('V', Items.GLASS_BOTTLE)
	    .key('E', Items.EMERALD)
	    .setGroup("icefeeling").build(consumer);;
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.ENCHANTED_BOTTLE.get()).addCriterion(getName(), hasItem(IFItems.ENCHANTED_GEM.get()))
	    .patternLine("G")
	    .patternLine("D")
	    .patternLine("B")
	    .key('G', IFItems.ENCHANTED_GEM.get())
	    .key('D', Items.DIAMOND)
	    .key('B', Items.EXPERIENCE_BOTTLE)
	    .setGroup("icefeeling").build(consumer);;
	    
		
		} 

}	

