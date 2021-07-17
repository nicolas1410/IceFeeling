package dmz.faction.icefeeling.entities.lavatnt;

import java.util.Set;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.blocks.IFBlocks;
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

public class IFCustomLavaTntExplosion extends Explosion implements IForgeBlock {

	private final World world;
	private final double x;
	private final double y;
	private final double z;
	@Nullable
	private final Entity exploder;
	@SuppressWarnings("unused")
	private final Vector3d position;

	public IFCustomLavaTntExplosion(World world, @Nullable Entity exploder, @Nullable DamageSource source,
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
		this.addSoundsAtExplosion();

		do3x3(pos, IFBlocks.STEEL_BLOCK.get().getDefaultState(), 3);

	}

	public void do3x3(BlockPos pos, BlockState state, int flag) {

		for (int x = 0; x < 2; x++) {
			for (int y = 0; y < 2; y++) {
				for (int z = 0; z < 2; z++) {

					/*world.setBlockState(pos.east(x), state, 3);
					
					world.setBlockState(pos.up(y), state, 3);
					
					world.setBlockState(pos.west(z), state, 3);
					
					world.setBlockState(pos.north(x), state, 3);
					
					world.setBlockState(pos.south(z), state, 3);
					
					world.setBlockState(pos.down(y), state, 3);*/
					
					
					world.setBlockState(pos.south(z).east(x).down(y), state, 3);
					world.setBlockState(pos.south(z).east(x).up(y), state, 3);
					
					world.setBlockState(pos.south(z).west(x).down(y), state, 3);
					world.setBlockState(pos.south(z).west(x).up(y), state, 3);
					
					
					world.setBlockState(pos.north(x).east(x).down(y), state, 3);
					world.setBlockState(pos.north(x).east(x).up(y), state, 3);
					
					world.setBlockState(pos.north(x).west(x).down(y), state, 3);
					world.setBlockState(pos.north(x).west(x).up(y), state, 3);



					
					
					world.setBlockState(pos.up(y).east(x), state, 3);
					world.setBlockState(pos.up(y).west(z), state, 3);
					world.setBlockState(pos.up(y).north(x), state, 3);
					world.setBlockState(pos.up(y).south(z), state, 3);


					world.setBlockState(pos.down(y).east(x), state, 3);
					world.setBlockState(pos.down(y).west(z), state, 3);
					world.setBlockState(pos.down(y).north(x), state, 3);
					world.setBlockState(pos.down(y).south(z), state, 3);


					

				}
			}
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
		return ToolType.PICKAXE;
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
