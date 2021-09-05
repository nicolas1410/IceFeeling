package dmz.faction.icefeeling.entities.mushpang;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.registry.IFEntityRegister;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LeapAtTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.NonTamedTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.AbstractSkeletonEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.GhastEntity;
import net.minecraft.entity.monster.ZombieEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.passive.horse.AbstractHorseEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class IFMushpangEntity extends TameableEntity implements IAngerable {

	private static final DataParameter<Boolean> DATA_INTERESTED_ID = EntityDataManager.defineId(IFMushpangEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> DATA_REMAINING_ANGER_TIME = EntityDataManager
			.defineId(IFMushpangEntity.class, DataSerializers.INT);
	protected static final DataParameter<Optional<UUID>> MUSHPANG_OWNER_ID = EntityDataManager
			.defineId(TameableEntity.class, DataSerializers.OPTIONAL_UUID);
	private static final RangedInteger PERSISTENT_ANGER_TIME = TickRangeConverter.rangeOfSeconds(20, 39);

	private static final Predicate<LivingEntity> PLAYER_ENTITY = (p_213626_0_) -> {
		return p_213626_0_ instanceof PlayerEntity && ((PlayerEntity) p_213626_0_).isAttackable();
	};

	private UUID persistentAngerTarget;

	public IFMushpangEntity(EntityType<IFMushpangEntity> type, World world) {
		super(type, world);
		this.setTame(false);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 8;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.entityData.get(DATA_REMAINING_ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int p_230260_1_) {
		this.entityData.set(DATA_REMAINING_ANGER_TIME, p_230260_1_);
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.randomValue(this.random));
	}

	@Override
	@Nullable
	public UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
		this.persistentAngerTarget = uuid;
	}

	public void setIsInterested(boolean isInterested) {
		this.entityData.set(DATA_INTERESTED_ID, isInterested);
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(DATA_INTERESTED_ID, false);
		this.entityData.define(DATA_REMAINING_ANGER_TIME, 0);
	}

	public boolean isInSittingPose() {
		return (this.entityData.get(DATA_FLAGS_ID) & 1) != 0;
	}

	public void setInSittingPose(boolean isSitting) {
		byte b0 = this.entityData.get(DATA_FLAGS_ID);
		if (isSitting) {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 | 1));
		} else {
			this.entityData.set(DATA_FLAGS_ID, (byte) (b0 & -2));
		}

	}

	@Override
	protected void registerGoals() {

		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new SitGoal(this));
		this.goalSelector.addGoal(4, new LeapAtTargetGoal(this, 0.4F));
		this.goalSelector.addGoal(5, new MeleeAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(7, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(10, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(3,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, PLAYER_ENTITY));
		this.targetSelector.addGoal(5,
				new NonTamedTargetGoal<>(this, AnimalEntity.class, false, WolfEntity.PREY_SELECTOR));
		this.targetSelector.addGoal(6,
				new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.BABY_ON_LAND_SELECTOR));
		this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
		this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
		this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, false));

	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {
		return MobEntity.createLivingAttributes().add(Attributes.MAX_HEALTH, 10D)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.3F).add(Attributes.ATTACK_DAMAGE, 20D)
				.add(Attributes.ARMOR, 10D).add(Attributes.ATTACK_SPEED, (double) 0.5F)
				.add(Attributes.FOLLOW_RANGE, 16.0D);
	}

	public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
		if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
			if (target instanceof IFMushpangEntity) {
				IFMushpangEntity mushpang = (IFMushpangEntity) target;
				return !mushpang.isTame() || mushpang.getOwner() != owner;
			} else if (target instanceof PlayerEntity && owner instanceof PlayerEntity
					&& !((PlayerEntity) owner).canHarmPlayer((PlayerEntity) target)) {
				return false;
			} else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity) target).isTamed()) {
				return false;
			} else {
				return !(target instanceof TameableEntity) || !((TameableEntity) target).isTame();
			}
		} else {
			return false;
		}
	}

	// Taming the pet
	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();
		if (this.level.isClientSide) {
			boolean flag = this.isOwnedBy(player) || this.isTame()
					|| item == IFItems.ENCHANTED_GEM.get() && !this.isTame() && !this.isAngry();
			return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
		} else {
			if (this.isTame()) {
				if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
					if (!player.abilities.instabuild) {
						itemstack.shrink(1);
					}

					this.heal((float) item.getFoodProperties().getNutrition());
					return ActionResultType.SUCCESS;
				}
			} else if (item == IFItems.ENCHANTED_GEM.get() && !this.isAngry()) {
				if (!player.abilities.instabuild) {
					itemstack.shrink(1);
				}

				if (this.random.nextInt(3) == 0
						&& !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					this.setOrderedToSit(true);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}
				return ActionResultType.SUCCESS;
			}

			return super.mobInteract(player, hand);
		}
	}

	@Override
	public IFMushpangEntity getBreedOffspring(ServerWorld server, AgeableEntity ageable) {
		IFMushpangEntity mushpangentity = IFEntityRegister.MUSHPANG.get().create(server);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			mushpangentity.setOwnerUUID(uuid);
			mushpangentity.setTame(true);
		}

		return mushpangentity;
	}

	@Override
	public void setTame(boolean tamed) {
		super.setTame(tamed);
		if (tamed) {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
			this.getAttribute(Attributes.ATTACK_SPEED).setBaseValue(20.0D);

			this.setHealth(20.0F);
		} else {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(20.0D);
		}

		this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(25.0D);
	}

	@Override
	public void tick() {
		super.tick();
	}

}
