package dmz.faction.icefeeling.entities.chunktnt;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.mod.registry.IFEntityRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.Pose;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.DamageSource;
import net.minecraft.world.Explosion;
import net.minecraft.world.ExplosionContext;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class IFChunkTNTEntity extends Entity {

	IFCustomChunkTNTExplosion chunkTntExplosion;

	private static final DataParameter<Integer> FUSE_CHUNK_TNT = EntityDataManager.defineId(IFChunkTNTEntity.class,
			DataSerializers.INT);

	private int fuse_chunk_tnt = 40;
	LivingEntity tntPlacedBy;

	public IFChunkTNTEntity(EntityType<? extends IFChunkTNTEntity> iceTntEntity, World worldIn) {
		super(iceTntEntity, worldIn);
		this.blocksBuilding = true;
	}

	public IFChunkTNTEntity(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
		this(IFEntityRegister.CHUNK_TNT_ENTITY.get(), worldIn);
		this.setPos(x, y, z);
		double d0 = worldIn.random.nextDouble() * (double) ((float) Math.PI * 2F);
		this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double) 0.2F, -Math.cos(d0) * 0.02D);
		this.setFuse(fuse_chunk_tnt);
		this.xo = x;
		this.yo = y;
		this.zo = z;
		this.tntPlacedBy = igniter;
	}

	protected void registerData() {
		this.entityData.define(FUSE_CHUNK_TNT, 10);
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean canBeCollidedWith() {
		return this.isAlive();
	}

	public void tick() {
		if (!this.isNoGravity()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.04D, 0.0D));
		}

		this.move(MoverType.SELF, this.getDeltaMovement());
		this.setDeltaMovement(this.getDeltaMovement().scale(0.98D));
		if (this.onGround) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(0.7D, -0.5D, 0.7D));
		}

		--this.fuse_chunk_tnt;
		if (this.fuse_chunk_tnt <= 0) {
			this.remove();
			if (!this.level.isClientSide) {
				this.explode();
			}
		} else {
			this.updateInWaterStateAndDoFluidPushing();
			if (this.level.isClientSide) {
				this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D,
						0.0D);
			}
		}

	}

	public Explosion chunkTntExplosion(@Nullable Entity entityIn, double xIn, double yIn, double zIn,
			float explosionRadius, Explosion.Mode modeIn) {
		return this.chunkTntExplosion(entityIn, (DamageSource) null, (ExplosionContext) null, xIn, yIn, zIn,
				explosionRadius, false, modeIn);
	}

	public Explosion chunkTntExplosion(@Nullable Entity entityIn, double xIn, double yIn, double zIn,
			float explosionRadius, boolean causesFire, Explosion.Mode modeIn) {

		return this.chunkTntExplosion(entityIn, (DamageSource) null, (ExplosionContext) null, xIn, yIn, zIn,
				explosionRadius, causesFire, modeIn);
	}

	public Explosion chunkTntExplosion(@Nullable Entity exploder, @Nullable DamageSource damageSource,
			@Nullable ExplosionContext context, double x, double y, double z, float size, boolean causesFire,
			Explosion.Mode mode) {

		IFCustomChunkTNTExplosion chunkTntExplosion = new IFCustomChunkTNTExplosion(level, exploder, damageSource,
				context, x, y, z, size, causesFire, mode);
		if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(level, chunkTntExplosion))
			return chunkTntExplosion;

		chunkTntExplosion.explode();
		chunkTntExplosion.finalizeExplosion(true);
		return chunkTntExplosion;

	}

	protected void explode() {
		float explosionPower = 2.0F;

		chunkTntExplosion(this, this.getX(), this.getY(0.0625D), this.getZ(), explosionPower, Explosion.Mode.DESTROY);

	}

	protected void writeAdditional(CompoundNBT compound) {
		compound.putShort("Chunk TNT Fuse", (short) this.getFuse());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readAdditional(CompoundNBT compound) {
		this.setFuse(compound.getShort("Chunk TNT Fuse"));
	}

	/**
	 * returns null or the entityliving it was placed or ignited by
	 */
	@Nullable
	public LivingEntity getTntPlacedBy() {
		return this.tntPlacedBy;
	}

	protected float getEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.15F;
	}

	public void setFuse(int fuseIn) {
		this.entityData.set(FUSE_CHUNK_TNT, fuseIn);
		this.fuse_chunk_tnt = fuseIn;
	}

	public void notifyentityDataChange(DataParameter<?> key) {
		if (FUSE_CHUNK_TNT.equals(key)) {
			this.fuse_chunk_tnt = this.getFuseentityData();
		}

	}

	/**
	 * Gets the fuse from the data manager
	 */
	public int getFuseentityData() {
		return this.entityData.get(FUSE_CHUNK_TNT);
	}

	public int getFuse() {
		return this.fuse_chunk_tnt;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(FUSE_CHUNK_TNT, 40);
	}

	@Override
	protected void addAdditionalSaveData(CompoundNBT p_213281_1_) {
		p_213281_1_.putShort("Fuse", (short) this.getFuse());
	}

	@Override
	protected void readAdditionalSaveData(CompoundNBT p_70037_1_) {
		this.setFuse(p_70037_1_.getShort("Fuse"));
	}

	@Override
	public IPacket<?> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
