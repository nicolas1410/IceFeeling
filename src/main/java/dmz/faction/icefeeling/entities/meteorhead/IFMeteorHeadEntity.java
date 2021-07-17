package dmz.faction.icefeeling.entities.meteorhead;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class IFMeteorHeadEntity extends MonsterEntity {

	private Effect effect;

	public IFMeteorHeadEntity(EntityType<IFMeteorHeadEntity> type, World worldIn) {
		super(type, worldIn);

		this.experienceValue = 100;

		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.setPathPriority(PathNodeType.WATER_BORDER, -1.0F);

	}

	/**
	 * Returns the Y Offset of this entity.
	 */
	public double getYOffset() {
		return 0.7D;
	}

	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 1.0F;
	}

	public float getBrightness() {
		return 7.0F;
	}

	public float getBlockPathWeight(BlockPos pos, IWorldReader worldIn) {
		return worldIn.getBlockState(pos).isAir() ? 10.0F : 0.0F;
	}

	protected void registerGoals() {

		this.goalSelector.addGoal(1, new IFMeteorHeadEntity.MeteorAttackGoal(this, 1.0D, true));
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));

	}

	@Override
	public void livingTick() {
		if (this.world.isRemote) {
			if (this.rand.nextInt(24) == 0 && !this.isSilent()) {
				this.world.playSound(this.getPosX() + 0.5D, this.getPosY() + 0.5D, this.getPosZ() + 0.5D,
						SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, this.getSoundCategory(),
						1.0F + this.rand.nextFloat(), this.rand.nextFloat() * 0.7F + 0.3F, false);
			}

			double a = Math.random();
			if (a <= 0.08) {

				for (int i = 0; i < 2; ++i) {
					this.world.addParticle(ParticleTypes.FALLING_OBSIDIAN_TEAR, this.getPosXRandom(0.5D),
							this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
				}

			}
		}

		super.livingTick();
	}

	@Override
	public boolean isWaterSensitive() {
		return true;
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {

		return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.23F)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D).createMutableAttribute(Attributes.ARMOR, 15.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
				.createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 5.0D);

	}

	@Override
	public boolean attackEntityAsMob(Entity entityIn) {
		if (!super.attackEntityAsMob(entityIn)) {
			return false;
		} else {
			if (entityIn instanceof LivingEntity) {
				((LivingEntity) entityIn).addPotionEffect(new EffectInstance(Effects.GLOWING, 100));
			}

			return true;
		}
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {

		setRandomEffect(rand);

		Effect effect = this.effect;

		if (effect != null) {
			this.addPotionEffect(new EffectInstance(effect, Integer.MAX_VALUE));
		}

		return spawnDataIn;
	}

	public void setEffectOnSpawn() {
		this.effect = Effects.FIRE_RESISTANCE;
	}

	public void setRandomEffect(Random rand) {
		int i = rand.nextInt(5);
		if (i <= 1) {
			this.effect = Effects.STRENGTH;
		} else if (i <= 3) {
			this.effect = Effects.SPEED;
		} else if (i <= 4) {
			this.effect = Effects.RESISTANCE;
		}

	}

	class MeteorAttackGoal extends MeleeAttackGoal {

		private final IFMeteorHeadEntity meteor;

		MeteorAttackGoal(IFMeteorHeadEntity meteor, double speedIn, boolean useLongMemory) {
			super(meteor, speedIn, useLongMemory);
			this.meteor = meteor;
		}

		public void startExecuting() {
			super.startExecuting();
		}

		/**
		 * Reset the task's internal state. Called when this task is interrupted by
		 * another one
		 */
		public void resetTask() {
			super.resetTask();
			this.meteor.setAggroed(false);
		}
	}
}
