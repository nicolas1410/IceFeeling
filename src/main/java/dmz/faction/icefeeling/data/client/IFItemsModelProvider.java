package dmz.faction.icefeeling.data.client;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IFItemsModelProvider extends ItemModelProvider 
{
	    public IFItemsModelProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) 
	    {
	        super(generator, Main.MOD_ID, existingFileHelper);
	    }
	    
	    @Override
	    protected void registerModels() 
	    {
	    	
	        ModelFile itemGenerated = getExistingFile(mcLoc("item/generated"));
	        ModelFile itemHandheld = getExistingFile(mcLoc("item/handheld"));

	        builder(itemGenerated, "knocker"); 
  
	        builder(itemGenerated, "mushpang_egg");
	        
	        builder(itemGenerated, "jade");
	        builder(itemGenerated, "opal");
	        builder(itemGenerated, "fire_opal");
	        builder(itemGenerated, "mythril_ingot");
	        builder(itemGenerated, "enchanted_fragment");
	        builder(itemGenerated, "titanite_ingot");
	        builder(itemGenerated, "obsidian_ingot");
	        builder(itemGenerated, "enchanted_gem");  
	        builder(itemGenerated, "solarium"); 
	        builder(itemGenerated, "robusium_plate"); 
	        builder(itemGenerated, "robusium"); 
	        builder(itemGenerated, "obsidian_fragment");
	        builder(itemGenerated, "obsidian_plate"); 
	        builder(itemGenerated, "steel_stick");
	        builder(itemGenerated, "steel_hammer");
	        builder(itemGenerated, "steel_ingot");
	        builder(itemGenerated, "enchanted_bottle");
	        builder(itemGenerated, "xp_fragment");




	        builder(itemGenerated, "iron_stick"); 
	        builder(itemGenerated, "obsidian_stick"); 


	        // OPAL ARMOR
	        builder(itemGenerated, "opal_helmet");
	        builder(itemGenerated, "opal_chestplate");
	        builder(itemGenerated, "opal_leggings");
	        builder(itemGenerated, "opal_boots");
	        
	        // JADE ARMOR
	        builder(itemGenerated, "jade_helmet");
	        builder(itemGenerated, "jade_chestplate");
	        builder(itemGenerated, "jade_leggings");
	        builder(itemGenerated, "jade_boots");
	        
	        // OBSIDIAN ARMOR
	        builder(itemGenerated, "obsidian_helmet");
	        builder(itemGenerated, "obsidian_chestplate");
	        builder(itemGenerated, "obsidian_leggings");
	        builder(itemGenerated, "obsidian_boots");
	        
	        // MYTHRIL ARMOR
	        builder(itemGenerated, "mythril_helmet");
	        builder(itemGenerated, "mythril_chestplate");
	        builder(itemGenerated, "mythril_leggings");
	        builder(itemGenerated, "mythril_boots");
	        
	        // TITANITE ARMOR
	        builder(itemGenerated, "titanite_helmet");
	        builder(itemGenerated, "titanite_chestplate");
	        builder(itemGenerated, "titanite_leggings");
	        builder(itemGenerated, "titanite_boots");
	        
	        // APPLES
	        builder(itemGenerated, "jade_apple");
	        builder(itemGenerated, "obsidian_apple");
	        builder(itemGenerated, "opal_apple");


	        
	        builderHandheld(itemHandheld, "admin_sword");
	        // PICKAXES
	        builderHandheld(itemHandheld, "opal_pickaxe");
	        builderHandheld(itemHandheld, "jade_pickaxe");
	        builderHandheld(itemHandheld, "mythril_pickaxe");
	        builderHandheld(itemHandheld, "titanite_pickaxe");
	        builderHandheld(itemHandheld, "obsidian_pickaxe");
	        // AXES
	        builderHandheld(itemHandheld, "opal_axe");
	        builderHandheld(itemHandheld, "jade_axe");
	        builderHandheld(itemHandheld, "mythril_axe");
	        builderHandheld(itemHandheld, "titanite_axe");
	        builderHandheld(itemHandheld, "obsidian_axe");
	        // SHOVELS
	        builderHandheld(itemHandheld, "opal_shovel");
	        builderHandheld(itemHandheld, "jade_shovel");
	        builderHandheld(itemHandheld, "mythril_shovel");
	        builderHandheld(itemHandheld, "titanite_shovel");
	        builderHandheld(itemHandheld, "obsidian_shovel");
	        // SWORDS	            
	        builderHandheld(itemHandheld, "enchanted_dagger");
	        builderHandheld(itemHandheld, "fire_opal_sword");
	        builderHandheld(itemHandheld, "opal_sword");
	        builderHandheld(itemHandheld, "jade_sword");
	        builderHandheld(itemHandheld, "mythril_sword");
	        builderHandheld(itemHandheld, "titanite_sword");
	        builderHandheld(itemHandheld, "obsidian_sword");


	    }

		private ItemModelBuilder builder(ModelFile itemGenerated, String name) 
		{
			return getBuilder(name).parent(new ModelFile.UncheckedModelFile("item/generated")).texture("layer0", modLoc("item/" + name));
		}
		
		private ItemModelBuilder builderHandheld(ModelFile itemHandheld, String name) 
		{
			return getBuilder(name).parent(new ModelFile.UncheckedModelFile("item/handheld")).texture("layer0", modLoc("item/" + name));
		}
}

