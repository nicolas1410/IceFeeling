package dmz.faction.icefeeling.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class IFRobusiumGlassBlock extends IFDurabilityTNTBlock {

	public IFRobusiumGlassBlock(AbstractBlock.Properties properties) {
		super(properties);
		properties.setOpaque(IFRobusiumGlassBlock::isntSolid);
		properties.setSuffocates(IFRobusiumGlassBlock::isntSolid);
		properties.setBlocksVision(IFRobusiumGlassBlock::isntSolid);
		properties.setAllowsSpawn(IFRobusiumGlassBlock::neverAllowSpawn);
		properties.notSolid();
		
	}

	@Override
	public VoxelShape getRayTraceShape(BlockState state, IBlockReader reader, BlockPos pos, ISelectionContext context) {
		return VoxelShapes.empty();
	}

	@Override
	public float getAmbientOcclusionLightValue(BlockState state, IBlockReader worldIn, BlockPos pos) {
		return 1.0F;
	}

	@Override
	public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
		return true;
	}

	@Override
	public boolean isSideInvisible(BlockState state, BlockState adjacentBlockState, Direction side) {
		return adjacentBlockState.isIn(this) ? true : super.isSideInvisible(state, adjacentBlockState, side);
	}
	
	@Override
	public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
		
		return 0;
		
	}

	public static Boolean neverAllowSpawn(BlockState state, IBlockReader reader, BlockPos pos, EntityType<?> entity) {
		return (boolean) false;
	}
	
	 public static boolean isntSolid(BlockState state, IBlockReader reader, BlockPos pos) {
	      return false;
	   }
}
