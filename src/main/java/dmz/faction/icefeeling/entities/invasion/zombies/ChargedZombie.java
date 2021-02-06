package dmz.faction.icefeeling.entities.invasion.zombies;

import java.util.Random;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.entities.invasion.zombies.goal.ChargedZombieAttackGoal;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.ILivingEntityData;
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
	public boolean isCharged() {
		return true;
	}


	protected void registerGoals() {
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
		this.applyEntityAI();
	}

	protected void applyEntityAI() {
		this.goalSelector.addGoal(3, new ChargedZombieAttackGoal(this, 1.0D, false));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	public static AttributeModifierMap.MutableAttribute func_234342_eQ_() {
		
		return MonsterEntity.func_234295_eP_().createMutableAttribute(Attributes.FOLLOW_RANGE, 32.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.4F)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 15.0D)
				.createMutableAttribute(Attributes.ARMOR, 30.0D)
				.createMutableAttribute(Attributes.MAX_HEALTH, 100.0D)
				.createMutableAttribute(Attributes.ARMOR_TOUGHNESS, 5.0D)
				.createMutableAttribute(Attributes.KNOCKBACK_RESISTANCE, 1.0D);	
		}

	@Nullable
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
	
		setRandomEffect(rand);

		if (this.rand.nextFloat() < 0.05F) {
			this.setLeftHanded(true);
		} else {
			this.setLeftHanded(false);
		}

		return spawnDataIn;
	}


	public void setRandomEffect(Random rand) {
		int i = rand.nextInt(5);
		if (i <= 1) {
			this.effect = Effects.SPEED;
		} else if (i <= 2) {
			this.effect = Effects.STRENGTH;
		} else if (i <= 3) {
			this.effect = Effects.REGENERATION;
		} else if (i <= 4) {
			this.effect = Effects.INVISIBILITY;
		}

	}
}
