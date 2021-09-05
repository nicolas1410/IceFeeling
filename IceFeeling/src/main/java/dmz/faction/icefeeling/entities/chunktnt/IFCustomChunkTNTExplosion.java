package dmz.faction.icefeeling.entities.chunktnt;

import java.util.Set;

import javax.annotation.Nullable;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunk;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

public class IFCustomChunkTNTExplosion extends Explosion implements IForgeBlock {

	private final World world;
	private final double x;
	private final double y;
	private final double z;
	@Nullable
	private final Entity exploder;
	@SuppressWarnings("unused")
	private final Vector3d position;

	public IFCustomChunkTNTExplosion(World world, @Nullable Entity exploder, @Nullable DamageSource source,
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

		world.playSound((PlayerEntity) null, this.x, this.y, this.z, SoundEvents.SNOW_PLACE, SoundCategory.BLOCKS,
				5.0F, 1.0F);

	}

	@Override
	public void explode() {
		double posX = this.x;
		double posY = this.y;
		double posZ = this.z;
		BlockPos pos = new BlockPos(posX, posY, posZ);
		this.addSoundsAtExplosion();

		IChunk chunk = world.getChunk(pos);

		if (chunk != null) {

			ChunkPos chunkPos = chunk.getPos();

			int XEnd = chunkPos.getMaxBlockX();

			int ZEnd = chunkPos.getMaxBlockZ();

			int YEnd = chunk.getMaxBuildHeight();


				for (int x = chunkPos.getMinBlockX(); x <= XEnd; x++) {
					for (int y = 0; y <= YEnd; y++) {
						for (int z = chunkPos.getMinBlockZ(); z <= ZEnd; z++) {

							BlockPos target = new BlockPos(x, y, z);
							
							if (chunk.getBlockState(target) != Blocks.BEDROCK.defaultBlockState()) {

							
							world.setBlock(target, Blocks.AIR.defaultBlockState(), 2);
						}
					}
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
