package dmz.faction.icefeeling.entities.invasion.zombies;

import java.util.Random;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.entities.invasion.zombies.goal.ChargedZombieAttackGoal;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IChargeableMob.class)
public class ChargedZombie extends MonsterEntity implements IChargeableMob {

	public Effect effect;

	public ChargedZombie(EntityType<ChargedZombie> type, World world) {
		super(type, world);

	}

	@Override
	public boolean isPowered() {
		return true;
	}

	protected void registerGoals() {
		this.goalSelector.addGoal(3, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.applyEntityAI();
	}

	protected void applyEntityAI() {
		this.goalSelector.addGoal(2, new ChargedZombieAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute registerAttributes() {

		return MonsterEntity.createMonsterAttributes().add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.MOVEMENT_SPEED, (double) 0.46F).add(Attributes.ATTACK_DAMAGE, 35.0D)
				.add(Attributes.ARMOR, 30.0D).add(Attributes.MAX_HEALTH, 100.0D).add(Attributes.ARMOR_TOUGHNESS, 5.0D)
				.add(Attributes.KNOCKBACK_RESISTANCE, 0.5D);

	}


	@Override
	public boolean doHurtTarget(Entity entity) {
		if (super.doHurtTarget(entity)) {
			if (entity instanceof LivingEntity) {
				if (Math.random() < 0.5) {
					((LivingEntity) entity).addEffect(new EffectInstance(Effects.BLINDNESS, 100));
				}
			}
			return true;
		} else {
			return false;
		}
	}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {

		setRandomEffect(random);

		Effect effect = this.effect;

		if (effect != null) {
			this.addEffect(new EffectInstance(effect, Integer.MAX_VALUE));
		}

		if (this.random.nextFloat() < 0.05F) {
			this.setLeftHanded(true);
		} else {
			this.setLeftHanded(false);
		}

		return spawnDataIn;
	}

	public void setEffectOnSpawn() {
		this.effect = Effects.MOVEMENT_SPEED;
	}

	public void setRandomEffect(Random rand) {
		int i = rand.nextInt(5);
		this.effect = Effects.MOVEMENT_SPEED;
		if (i <= 1) {
			this.effect = Effects.DAMAGE_BOOST;
		} else if (i <= 3) {
			this.effect = Effects.REGENERATION;
		} else if (i <= 4) {
			this.effect = Effects.INVISIBILITY;
		}

	}
}
