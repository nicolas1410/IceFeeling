package dmz.faction.icefeeling.entities.chargedtnt;

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
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class IFChargedTNTEntity extends Entity {

	private static final DataParameter<Integer> FUSE_CHARGED = EntityDataManager.defineId(IFChargedTNTEntity.class,
			DataSerializers.INT);

	private int fuse_charged = 40;
	LivingEntity tntPlacedBy;

	public IFChargedTNTEntity(EntityType<? extends IFChargedTNTEntity> chargedTntEntity, World worldIn) {
		super(chargedTntEntity, worldIn);
		this.blocksBuilding = true;
	}

	public IFChargedTNTEntity(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
		this(IFEntityRegister.CHARGED_TNT_ENTITY.get(), worldIn);
		this.setPos(x, y, z);
		double d0 = worldIn.random.nextDouble() * (double) ((float) Math.PI * 2F);
		this.setDeltaMovement(-Math.sin(d0) * 0.02D, (double) 0.2F, -Math.cos(d0) * 0.02D);
		this.setFuse(fuse_charged);
		this.xo = x;
		this.yo = y;
		this.zo = z;
		this.tntPlacedBy = igniter;
	}

	@Override
	protected void defineSynchedData() {
		this.entityData.define(FUSE_CHARGED, 80);
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

		--this.fuse_charged;
		if (this.fuse_charged <= 0) {
			this.remove();
			if (!this.level.isClientSide) {
				this.explode();
			}
		} else {
			this.updateInWaterStateAndDoFluidPushing();
			if (this.level.isClientSide) {
				this.level.addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D, 0.0D,
						0.0D);
				this.level.addParticle(ParticleTypes.WHITE_ASH, this.getX(), this.getY() + 0.5D, this.getZ(), 0.0D,
						0.0D, 0.0D);

			}
		}

	}

	protected void explode() {
		float explosionPower = 12.0F;
		this.level.explode(this, this.getX(), this.getY(0.0625D), this.getZ(), explosionPower, Explosion.Mode.DESTROY);
	}

	protected void writeAdditional(CompoundNBT compound) {
		compound.putShort("Charged Fuse", (short) this.getFuse());
	}

	/**
	 * (abstract) Protected helper method to read subclass entity data from NBT.
	 */
	protected void readAdditional(CompoundNBT compound) {
		this.setFuse(compound.getShort("Charged Fuse"));
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
		this.entityData.set(FUSE_CHARGED, fuseIn);
		this.fuse_charged = fuseIn;
	}

	public void notifyDataManagerChange(DataParameter<?> key) {
		if (FUSE_CHARGED.equals(key)) {
			this.fuse_charged = this.getFuseDataManager();
		}

	}

	/**
	 * Gets the fuse from the data manager
	 */
	public int getFuseDataManager() {
		return this.entityData.get(FUSE_CHARGED);
	}

	public int getFuse() {
		return this.fuse_charged;
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