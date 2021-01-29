package dmz.faction.icefeeling.entities.icetnt;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class IFCustomIceTntExplosion extends Explosion implements IForgeBlock {
	
	private final World world;
	private final double x;
	private final double y;
	private final double z;
	@Nullable
	private final Entity exploder;
	@SuppressWarnings("unused")
	private final Vector3d position;

	public IFCustomIceTntExplosion(World world, @Nullable Entity exploder, @Nullable DamageSource source,
			@Nullable ExplosionContext context, double x, double y, double z, float size, boolean causesFire,
			Explosion.Mode mode) {

		super(world, exploder, x, y, z, size, causesFire, mode);

		this.world = world;
		this.exploder = exploder;
		this.x = x;
		this.y = y;
		this.z = z;
		this.position = new Vector3d(this.x, this.y, this.z);
	}


	private boolean isWaterFull(BlockPos pos, World world, BlockState state, FluidState fstate) {
		return state == Blocks.WATER.getDefaultState();

	}

	private boolean isWaterOrFalling(BlockPos pos, World world, BlockState state, FluidState fstate) {
		return fstate == Fluids.FLOWING_WATER.getFlowingFluidState(1, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(2, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(3, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(4, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(5, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(6, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(7, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(8, true)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(1, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(2, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(3, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(4, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(5, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(6, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(7, false)
				|| fstate == Fluids.FLOWING_WATER.getFlowingFluidState(8, false);

	}
	
	private void addSoundsAtExplosion() {

		world.playSound((PlayerEntity) null, this.x, this.y, this.z, SoundEvents.BLOCK_SNOW_PLACE, SoundCategory.BLOCKS,
				5.0F, 1.0F);

	}

	@Override
	public void doExplosionA() {
		double posX = this.x;
		double posY = this.y;
		double posZ = this.z;
		BlockPos pos = new BlockPos(posX, posY, posZ);
		BlockState bstate = this.world.getBlockState(pos);
		FluidState fstate = this.world.getFluidState(pos);
		this.addSoundsAtExplosion();

		if (this.isWaterFull(pos, world, bstate, fstate)) {

			world.setBlockState(pos, Blocks.PACKED_ICE.getDefaultState(), 3);

		}
		if (this.isWaterOrFalling(pos, world, bstate, fstate)) {

			world.setBlockState(pos, Blocks.ICE.getDefaultState(), 3);

		}
	}

	@Override
	public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, Entity entity) {
		return 0;
	}

	@Override
	public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing,
			IPlantable plantable) {
		return false;
	}

	@Override
	public ToolType getHarvestTool(BlockState state) {
		return null;
	}

	@Override
	public int getHarvestLevel(BlockState state) {
		return 0;
	}

	@Override
	public Set<ResourceLocation> getTags() {
		return null;
	}
}
