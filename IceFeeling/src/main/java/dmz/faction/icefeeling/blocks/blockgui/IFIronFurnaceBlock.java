package dmz.faction.icefeeling.blocks.blockgui;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.blocks.blockgui.abstracts.IFAbstractFurnaceBlock;
import dmz.faction.icefeeling.inventory.ironfurnace.IFIronFurnaceTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class IFIronFurnaceBlock extends IFAbstractFurnaceBlock {

	public IFIronFurnaceBlock(AbstractBlock.Properties properties) {
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
        return new IFIronFurnaceTileEntity();
    }
}
