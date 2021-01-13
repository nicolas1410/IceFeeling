package dmz.faction.icefeeling.data.client;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IFBlockStateProvider extends BlockStateProvider {

	public IFBlockStateProvider(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Main.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
	
		simpleBlock(IFBlocks.JADE_ORE.get());
        makeBlockItemFromExistingModel(IFBlocks.JADE_ORE.get());
        
        simpleBlock(IFBlocks.OPAL_ORE.get());
        makeBlockItemFromExistingModel(IFBlocks.OPAL_ORE.get());
        
		simpleBlock(IFBlocks.TITANITE_ORE.get());
        makeBlockItemFromExistingModel(IFBlocks.TITANITE_ORE.get());
        
		simpleBlock(IFBlocks.MYTHRIL_ORE.get());
        makeBlockItemFromExistingModel(IFBlocks.MYTHRIL_ORE.get());
    
        simpleBlock(IFBlocks.OPAL_ORE_NETHER.get());
        makeBlockItemFromExistingModel(IFBlocks.OPAL_ORE_NETHER.get());
        
        simpleBlock(IFBlocks.SOLARIUM_ORE.get());
        makeBlockItemFromExistingModel(IFBlocks.SOLARIUM_ORE.get());
        
        simpleBlock(IFBlocks.ROBUSIUM_ORE.get());
        makeBlockItemFromExistingModel(IFBlocks.ROBUSIUM_ORE.get());
        
        simpleBlock(IFBlocks.ROBUSIUM_BLOCK.get());
        makeBlockItemFromExistingModel(IFBlocks.ROBUSIUM_BLOCK.get());


	    //simpleBlock(IFBlocks.OPAL_ORE.get());
        //simpleBlock(IFBlocks.FIRE_OPAL_ORE.get());
        //simpleBlock(IFBlocks.ROBUSIUM_ORE.get());
       //simpleBlock(IFBlocks.SOLARIUM_ORE.get());
	}
	
    private void makeBlockItemFromExistingModel(Block block) {
    	final ModelFile model = models().getExistingFile(block.getRegistryName());
    	simpleBlockItem(block, model);
    }
	

}
