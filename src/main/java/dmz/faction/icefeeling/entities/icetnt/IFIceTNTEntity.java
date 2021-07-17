package dmz.faction.icefeeling.entities.icetnt;

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

public class IFIceTNTEntity extends Entity {

	IFCustomIceTntExplosion iceTntExplosion;

	private static final DataParameter<Integer> FUSE_CHARGED = EntityDataManager.createKey(IFIceTNTEntity.class,
			DataSerializers.VARINT);

	private int fuse_charged = 40;
	LivingEntity tntPlacedBy;

	public IFIceTNTEntity(EntityType<? extends IFIceTNTEntity> iceTntEntity, World worldIn) {
		super(iceTntEntity, worldIn);
		this.preventEntitySpawning = true;
	}

	public IFIceTNTEntity(World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
		this(IFEntityRegister.ICE_TNT_ENTITY.get(), worldIn);
		this.setPosition(x, y, z);
		double d0 = worldIn.rand.nextDouble() * (double) ((float) Math.PI * 2F);
		this.setMotion(-Math.sin(d0) * 0.02D, (double) 0.2F, -Math.cos(d0) * 0.02D);
		this.setFuse(fuse_charged);
		this.prevPosX = x;
		this.prevPosY = y;
		this.prevPosZ = z;
		this.tntPlacedBy = igniter;
	}

	protected void registerData() {
		this.dataManager.register(FUSE_CHARGED, 10);
	}

	protected boolean canTriggerWalking() {
		return false;
	}

	public boolean canBeCollidedWith() {
		return this.isAlive();
	}

	public void tick() {
		if (!this.hasNoGravity()) {
			this.setMotion(this.getMotion().add(0.0D, -0.04D, 0.0D));
		}

		this.move(MoverType.SELF, this.getMotion());
		this.setMotion(this.getMotion().scale(0.98D));
		if (this.onGround) {
			this.setMotion(this.getMotion().mul(0.7D, -0.5D, 0.7D));
		}

		--this.fuse_charged;
		if (this.fuse_charged <= 0) {
			this.remove();
			if (!this.world.isRemote) {
				this.explode();
			}
		} else {
			this.func_233566_aG_();
			if (this.world.isRemote) {
				this.world.addParticle(ParticleTypes.SMOKE, this.getPosX(), this.getPosY() + 0.5D, this.getPosZ(), 0.0D,
						0.0D, 0.0D);
			}
		}

	}

	public Explosion iceTntExplosion(@Nullable Entity entityIn, double xIn, double yIn, double zIn,
			float explosionRadius, Explosion.Mode modeIn) {
		return this.iceTntExplosion(entityIn, (DamageSource) null, (ExplosionContext) null, xIn, yIn, zIn,
				explosionRadius, false, modeIn);
	}

	public Explosion iceTntExplosion(@Nullable Entity entityIn, double xIn, double yIn, double zIn,
			float explosionRadius, boolean causesFire, Explosion.Mode modeIn) {

		return this.iceTntExplosion(entityIn, (DamageSource) null, (ExplosionContext) null, xIn, yIn, zIn,
				explosionRadius, causesFire, modeIn);
	}

	public Explosion iceTntExplosion(@Nullable Entity exploder, @Nullable DamageSource damageSource,
			@Nullable ExplosionContext context, double x, double y, double z, float size, boolean causesFire,
			Explosion.Mode mode) {

		IFCustomIceTntExplosion iceTntExplosion = new IFCustomIceTntExplosion(world, exploder, damageSource, context, x,
				y, z, size, causesFire, mode);
		if (net.minecraftforge.event.ForgeEventFactory.onExplosionStart(world, iceTntExplosion))
			return iceTntExplosion;

		iceTntExplosion.doExplosionA();
		iceTntExplosion.doExplosionB(true);
		return iceTntExplosion;

	}

	protected void explode() {
		float explosionPower = 2.0F;

		iceTntExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), explosionPower,
				Explosion.Mode.DESTROY);

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
		this.dataManager.set(FUSE_CHARGED, fuseIn);
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
		return this.dataManager.get(FUSE_CHARGED);
	}

	public int getFuse() {
		return this.fuse_charged;
	}

	@Override
	public IPacket<?> createSpawnPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

}
