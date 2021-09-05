package dmz.faction.icefeeling.data.tags;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IFBlocksTagProvider extends BlockTagsProvider {

	public IFBlocksTagProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Main.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags() {
       this.tag(IFTags.ICEFEELING_ORES)
       .add(IFBlocks.JADE_ORE.get()).add(IFBlocks.MYTHRIL_ORE.get())
       .add(IFBlocks.TITANITE_ORE.get()).add(IFBlocks.OPAL_ORE.get())
       .add(IFBlocks.ROBUSIUM_ORE.get()).add(IFBlocks.SOLARIUM_ORE.get());
	}

	
}
