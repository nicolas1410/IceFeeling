package tests.multiblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

public class IFBlockMulti extends Block {

	public IFBlockMulti(Properties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new TileEntityMultiBlock();
	}
}
