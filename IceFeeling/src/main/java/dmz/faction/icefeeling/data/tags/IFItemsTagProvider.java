package dmz.faction.icefeeling.data.tags;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IFItemsTagProvider extends ItemTagsProvider {

	public IFItemsTagProvider(DataGenerator gen, BlockTagsProvider blockTagProvider, ExistingFileHelper existingFileHelper) {
		super(gen, blockTagProvider, Main.MOD_ID, existingFileHelper);
		
	}
	
	@Override
	protected void addTags() {

      this.tag(Tags.Items.GEMS).add(IFItems.JADE.get());
      this.tag(Tags.Items.GEMS).add(IFItems.OPAL.get());
      this.tag(Tags.Items.GEMS).add(IFItems.ENCHANTED_GEM.get());
      this.tag(Tags.Items.GEMS).add(IFItems.ENCHANTED_FRAGMENT.get());
      this.tag(Tags.Items.INGOTS).add(IFItems.MYTHRIL_INGOT.get());
      this.tag(Tags.Items.INGOTS).add(IFItems.OBSIDIAN_INGOT.get());
      this.tag(Tags.Items.INGOTS).add(IFItems.TITANITE_INGOT.get());

      this.tag(IFTags.ENCHANTED_DAGGER).add(IFItems.ENCHANTED_DAGGER.get());
      
      this.tag(Tags.Items.RODS).add(IFItems.IRON_STICK.get()).add(IFItems.OBSIDIAN_STICK.get());
      
      this.tag(IFTags.ICEFEELING_APPLES).add(IFItems.OPAL_APPLE.get()).add(IFItems.OBSIDIAN_APPLE.get());




        
	}


}
