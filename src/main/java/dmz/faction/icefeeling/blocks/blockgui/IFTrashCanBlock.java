package dmz.faction.icefeeling.blocks.blockgui;

import dmz.faction.icefeeling.inventory.trashcan.IFTrashCanTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class IFTrashCanBlock extends Block {

	public IFTrashCanBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
		if (worldIn.isClientSide) {
			return ActionResultType.SUCCESS;
		} else {
			this.openContainer(worldIn, pos, player);
			return ActionResultType.CONSUME;
		}
	}

	
	private void openContainer(World world, BlockPos pos, PlayerEntity player) {
		TileEntity tileEntity = world.getBlockEntity(pos);
		if (tileEntity instanceof INamedContainerProvider) {
			NetworkHooks.openGui((ServerPlayerEntity) player, (INamedContainerProvider) tileEntity,
					tileEntity.getBlockPos());
		}
	}
	
	@Override
	public TileEntity createTileEntity(BlockState state, IBlockReader world) {
		return new IFTrashCanTileEntity();
	}
	
	@Override
	public boolean hasTileEntity(BlockState state) {
		return true;
	}
	
	@Override
	public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
		if (stack.hasCustomHoverName()) {
			TileEntity tileentity = worldIn.getBlockEntity(pos);
			if (tileentity instanceof IFTrashCanTileEntity) {
				((IFTrashCanTileEntity) tileentity).setCustomName(stack.getDisplayName());
			}
		}

	}

}
