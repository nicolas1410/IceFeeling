package dmz.faction.icefeeling.data.tags;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class IFTags {

		
		//BlockTags
        public static final ITag.INamedTag<Block> ICEFEELING_ORES = makeBlockTag("ores/icefeeling_ores");
        public static final ITag.INamedTag<Block> ICEFEELING_BLOCKS = makeBlockTag("blocks/icefeeling_blocks");

        //ItemTags
        public static final ITag.INamedTag<Item> ICEFEELING_APPLES = makeItemTag("apples");
        public static final ITag.INamedTag<Item> ENCHANTED_DAGGER = makeItemTag("enchanted_dagger");


 

	
        public static ITag.INamedTag<Block> makeBlockTag(final String name) 
        {
		return BlockTags.bind(new ResourceLocation(Main.MOD_ID, name).toString());
        }
        public static ITag.INamedTag<Item> makeItemTag(final String name) 
    	{
    		return ItemTags.bind(new ResourceLocation(Main.MOD_ID, name).toString());
    	}

}
