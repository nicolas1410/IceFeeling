package dmz.faction.icefeeling.blocks;

import dmz.faction.icefeeling.tileentity.obsidian.IFObsidianFurnaceTile;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class IFObsidianFurnaceBlock extends IFFurnaceBlock {

	public IFObsidianFurnaceBlock(Properties properties) {
		super(properties);
	}

	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new IFObsidianFurnaceTile();
	}
}
