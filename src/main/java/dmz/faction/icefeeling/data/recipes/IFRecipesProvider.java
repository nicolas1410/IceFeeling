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
	    ShapelessRecipeBuilder.shapelessRecipe(IFBlocks.ROBUSIUM_BLOCK.get()).addCriterion(getName(), hasItem(IFItems.ROBUSIUM_PLATE.get())).addIngredient(IFItems.ROBUSIUM_PLATE.get(), 9).setGroup("icefeeling").build(consumer);;
	    ShapelessRecipeBuilder.shapelessRecipe(IFBlocks.COMPRESSED_COBBLE.get()).addCriterion(getName(), hasItem(Items.COBBLESTONE)).addIngredient(Items.COBBLESTONE, 9).setGroup("icefeeling").build(consumer);;
	    
	    
	    /*------------------------------------------------------ ITEMS ------------------------------------------------------*/

	    // VANILLA FURNACE
	    
	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(IFBlocks.MYTHRIL_ORE.get().asItem()), IFItems.MYTHRIL_INGOT.get(), 1F, 200).addCriterion(getName(), hasItem(IFBlocks.MYTHRIL_ORE.get())).build(consumer);
	    CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(IFBlocks.TITANITE_ORE.get().asItem()), IFItems.TITANITE_INGOT.get(), 1F, 200).addCriterion(getName(), hasItem(IFBlocks.TITANITE_ORE.get())).build(consumer);
	    
	    // APPLES
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_APPLE.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("ooo").patternLine("oxo").patternLine("ooo").key('x', Items.ENCHANTED_GOLDEN_APPLE).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_APPLE.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("jjj").patternLine("jxj").patternLine("jjj").key('x', Items.GOLDEN_APPLE).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_APPLE.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_INGOT.get())).patternLine("iii").patternLine("ixi").patternLine("iii").key('x', Items.GOLDEN_APPLE).key('i', IFItems.OBSIDIAN_INGOT.get()).setGroup("icefeeling").build(consumer);

	    // TOOLS
		
	    ShapedRecipeBuilder.shapedRecipe(IFItems.JADE_SWORD.get()).addCriterion(getName(), hasItem(IFItems.JADE.get())).patternLine("j").patternLine("j").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('j', IFItems.JADE.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.TITANITE_SWORD.get()).addCriterion(getName(), hasItem(IFItems.TITANITE_INGOT.get())).patternLine("t").patternLine("t").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('t', IFItems.TITANITE_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OBSIDIAN_SWORD.get()).addCriterion(getName(), hasItem(IFItems.OBSIDIAN_INGOT.get())).patternLine("o").patternLine("o").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OBSIDIAN_INGOT.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_SWORD.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("o").patternLine("o").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);

	    ShapedRecipeBuilder.shapedRecipe(IFItems.MYTHRIL_SWORD.get()).addCriterion(getName(), hasItem(IFItems.MYTHRIL_INGOT.get())).patternLine("m").patternLine("m").patternLine("x").key('x', IFItems.IRON_STICK.get()).key('m', IFItems.MYTHRIL_INGOT.get()).setGroup("icefeeling").build(consumer);

	    // ARMORS
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.OPAL_CHESTPLATE.get()).addCriterion(getName(), hasItem(IFItems.OPAL.get())).patternLine("o o").patternLine("ooo").patternLine("ooo").key('o', IFItems.OPAL.get()).setGroup("icefeeling").build(consumer);
	    
	    //MISC
	    
	    ShapedRecipeBuilder.shapedRecipe(IFItems.ROBUSIUM_PLATE.get()).addCriterion(getName(), hasItem(IFItems.ROBUSIUM.get())).patternLine("rr").patternLine("rr").key('r', IFItems.ROBUSIUM.get()).setGroup("icefeeling").build(consumer);

		
		} 

}	

