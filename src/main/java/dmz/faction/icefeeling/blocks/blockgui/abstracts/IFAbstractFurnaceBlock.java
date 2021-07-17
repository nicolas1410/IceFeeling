package dmz.faction.icefeeling.blocks.blockgui.abstracts;

import java.util.Random;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

public abstract class IFAbstractFurnaceBlock extends Block {

	public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
	public static final BooleanProperty LIT = BlockStateProperties.LIT;

	public IFAbstractFurnaceBlock(Properties properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(BlockStateProperties.LIT, false));
	}

	@Override
	public ToolType getHarvestTool(BlockState state) {
		return ToolType.PICKAXE;
	}

	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}

	@Override
	public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
		return state.get(BlockStateProperties.LIT) ? 14 : 0;
	}

	@Override
	public BlockState getStateForPlacement(BlockItemUseContext ctx) {
		return (BlockState) this.getDefaultState().with(BlockStateProperties.HORIZONTAL_FACING,
				ctx.getPlacementHorizontalFacing().getOpposite());
	}

	@Override
	public void onBlockPlacedBy(World world, BlockPos pos, BlockState state, @Nullable LivingEntity entity,
			ItemStack stack) {
		if (entity != null) {
			IFAbstractFurnaceTileEntity te = (IFAbstractFurnaceTileEntity) world.getTileEntity(pos);
			if (stack.hasDisplayName()) {
				te.setCustomName(stack.getDisplayName());
			}
		}
	}

	@Override
	public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player,
			Hand handIn, BlockRayTraceResult p_225533_6_) {
		if (world.isRemote) {
			return ActionResultType.SUCCESS;
		} else {
			this.interactWith(world, pos, player);
			return ActionResultType.CONSUME;
		}
	}

	private void interactWith(World world, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = world.getTileEntity(pos);
		if (tileEntity instanceof INamedContainerProvider) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity,
					tileEntity.getPos());
			player.addStat(Stats.INTERACT_WITH_FURNACE);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public void animateTick(BlockState state, World world, BlockPos pos, Random rand) {
		if (state.get(BlockStateProperties.LIT)) {
			if (!(world.getTileEntity(pos) instanceof IFAbstractFurnaceTileEntity)) {
				return;
			}
			double d0 = (double) pos.getX() + 0.5D;
			double d1 = (double) pos.getY();
			double d2 = (double) pos.getZ() + 0.5D;
			if (rand.nextDouble() < 0.1D) {
				world.playSound(d0, d1, d2, SoundEvents.BLOCK_FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F,
						false);
			}

			Direction direction = state.get(BlockStateProperties.HORIZONTAL_FACING);
			Direction.Axis direction$axis = direction.getAxis();
			double d3 = 0.52D;
			double d4 = rand.nextDouble() * 0.6D - 0.3D;
			double d5 = direction$axis == Direction.Axis.X ? (double) direction.getXOffset() * 0.52D : d4;
			double d6 = rand.nextDouble() * 6.0D / 16.0D;
			double d7 = direction$axis == Direction.Axis.Z ? (double) direction.getZOffset() * 0.52D : d4;
			world.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
			world.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);

		}
	}

	public void onReplaced(BlockState state, World world, BlockPos pos, BlockState oldState, boolean isMoving) {
		if (state.getBlock() != oldState.getBlock()) {
			TileEntity te = world.getTileEntity(pos);
			if (te instanceof IFAbstractFurnaceTileEntity) {
				InventoryHelper.dropInventoryItems(world, pos, (IFAbstractFurnaceTileEntity) te);
				((IFAbstractFurnaceTileEntity) te).grantStoredRecipeExperience(world, Vector3d.copyCentered(pos));
				world.updateComparatorOutputLevel(pos, this);
			}

			super.onReplaced(state, world, pos, oldState, isMoving);
		}
	}

	public boolean hasComparatorInputOverride(BlockState state) {
		return true;
	}

	public int getComparatorInputOverride(BlockState state, World world, BlockPos pos) {
		return Container.calcRedstone(world.getTileEntity(pos));
	}

	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}

	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.with(FACING, rotation.rotate(state.get(FACING)));

	}

	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.toRotation(state.get(FACING)));
	}

	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
		builder.add(FACING, LIT);
	}

}
