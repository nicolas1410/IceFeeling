package dmz.faction.icefeeling.data;

import dmz.faction.icefeeling.data.client.IFBlockStateProvider;
import dmz.faction.icefeeling.data.client.IFItemsModelProvider;
import dmz.faction.icefeeling.data.recipes.IFRecipesProvider;
import dmz.faction.icefeeling.data.tags.IFBlocksTagProvider;
import dmz.faction.icefeeling.data.tags.IFItemsTagProvider;
//import dmz.faction.icefeeling.data.loot.IFLootTable;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators 
{
	
	private DataGenerators() {}
	
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) 
	{
		DataGenerator gen = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		
		IFBlocksTagProvider blockTags = new IFBlocksTagProvider(gen, existingFileHelper);
		
        if (event.includeClient()) 
        {
        	gen.addProvider(new IFItemsModelProvider(gen, existingFileHelper));
        	gen.addProvider(new IFBlockStateProvider(gen, existingFileHelper));
        }
        
        if (event.includeServer())
        {
            gen.addProvider(new IFItemsTagProvider(gen, blockTags, existingFileHelper));
            gen.addProvider(new IFBlocksTagProvider(gen, existingFileHelper));
            gen.addProvider(new IFRecipesProvider(gen));
            //gen.addProvider(new IFLootTable(gen));


        }
        
       

	}
	
}
