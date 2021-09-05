package dmz.faction.icefeeling.blocks.blockgui;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.blocks.blockgui.abstracts.IFAbstractFurnaceBlock;
import dmz.faction.icefeeling.inventory.obsidianfurnace.IFObsidianFurnaceTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class IFObsidianFurnaceBlock extends IFAbstractFurnaceBlock {

	public IFObsidianFurnaceBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(BlockStateProperties.LIT, false));

	}

    @Override
    public int getHarvestLevel(BlockState state) {
        return 3;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new IFObsidianFurnaceTileEntity();
    }
}
