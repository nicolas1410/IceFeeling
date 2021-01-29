package dmz.faction.icefeeling.entities.mushpang;

import java.util.Optional;
import java.util.UUID;
import java.util.function.Predicate;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.entities.registry.IFEntityRegister;
import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class IFMushpangEntity extends TameableEntity implements IAngerable {
	
	protected static final DataParameter<Optional<UUID>> MUSHPANG_OWNER_ID = EntityDataManager.createKey(TameableEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final RangedInteger RANDOM_ANGER_TIME = TickRangeConverter.convertRange(20, 39);
	protected static final DataParameter<Byte> MUSHPANG_TAMED = EntityDataManager.createKey(TameableEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> VARINT = EntityDataManager.createKey(WolfEntity.class, DataSerializers.VARINT);
	
	private boolean sitting;
	private UUID uuid;
	
	public IFMushpangEntity(EntityType<IFMushpangEntity> type, World world) 
	{
		super(type, world);
		this.setTamed(false);
	}
	
	@Override
	protected void registerData() {
	      super.registerData();
	      this.dataManager.register(VARINT, 0);
	      this.dataManager.register(MUSHPANG_OWNER_ID, Optional.empty());

	   }
	
	@Override
	protected void registerGoals() 
	{
		
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
	      this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
	      this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, false, this::func_233680_b_));
	      this.targetSelector.addGoal(5, new NonTamedTargetGoal<>(this, AnimalEntity.class, false, WolfEntity.TARGET_ENTITIES));
	      this.targetSelector.addGoal(6, new NonTamedTargetGoal<>(this, TurtleEntity.class, false, TurtleEntity.TARGET_DRY_BABY));
	      this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, AbstractSkeletonEntity.class, false));
	      this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
	      this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, ZombieEntity.class, false));

	}
	
	public static AttributeModifierMap.MutableAttribute registerAttributes() 
	{
		return MobEntity.func_233666_p_().
				createMutableAttribute(Attributes.MAX_HEALTH, 10D).
				createMutableAttribute(Attributes.MOVEMENT_SPEED, (double) 0.3F).
				createMutableAttribute(Attributes.ATTACK_DAMAGE, 20D).
				createMutableAttribute(Attributes.ARMOR, 10D).
				createMutableAttribute(Attributes.ATTACK_SPEED, (double) 0.5F).
				createMutableAttribute(Attributes.FOLLOW_RANGE, 16.0D);
	}
	
	public boolean shouldAttackEntity(LivingEntity target, LivingEntity owner) {
		      if (!(target instanceof CreeperEntity) && !(target instanceof GhastEntity)) {
		         if (target instanceof IFMushpangEntity) {
		        	 IFMushpangEntity mushpang = (IFMushpangEntity)target;
		            return !mushpang.isTamed() || mushpang.getOwner() != owner;
		         } else if (target instanceof PlayerEntity && owner instanceof PlayerEntity && !((PlayerEntity)owner).canAttackPlayer((PlayerEntity)target)) {
		            return false;
		         } else if (target instanceof AbstractHorseEntity && ((AbstractHorseEntity)target).isTame()) {
		            return false;
		         } else {
		            return !(target instanceof TameableEntity) || !((TameableEntity)target).isTamed();
		         }
		      } else {
		         return false;
		      }
	}

	//Taming the pet
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
	      ItemStack itemstack = player.getHeldItem(hand);
	      Item item = itemstack.getItem();
	      if (this.world.isRemote) 
	      {
	         boolean flag = this.isOwner(player) || this.isTamed() || item == IFItems.ENCHANTED_GEM.get() && !this.isTamed() && !this.func_233678_J__();
	         return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
	      } 
	      else 
	      {
	         if (this.isTamed()) 
	         {
	            if (this.isBreedingItem(itemstack) && this.getHealth() < this.getMaxHealth()) 
	            {
	               if (!player.abilities.isCreativeMode)
	               {
	                  itemstack.shrink(1);
	               }

	               this.heal((float)item.getFood().getHealing());
	               return ActionResultType.SUCCESS;
	            } 
	         } 
	         else if (item == IFItems.ENCHANTED_GEM.get() && !this.func_233678_J__()) 
	         {
	            if (!player.abilities.isCreativeMode)
	            {
	               itemstack.shrink(1);      
	            }

	            if (this.rand.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) 
	            {
	               this.setTamedBy(player);
	               this.navigator.clearPath();
	               this.setAttackTarget((LivingEntity)null);
	               this.func_233687_w_(true);
	               this.world.setEntityState(this, (byte)7);
	            }
	            else 
	            {
	               this.world.setEntityState(this, (byte)6);
	            }

	            return ActionResultType.SUCCESS;
	         }

	         return super.func_230254_b_(player, hand);
	    }
	}
	
	@Override
	public IFMushpangEntity func_241840_a(ServerWorld server, AgeableEntity ageable) 
	{
		IFMushpangEntity mushpangentity = IFEntityRegister.MUSHPANG.get().create(server);
		UUID uuid = this.getOwnerId();
		if (uuid != null) 
		{
			mushpangentity.setOwnerId(uuid);
			mushpangentity.setTamed(true);
		}

		 return mushpangentity;
	}
	
	@Override
	public boolean canSpawn(IWorld worldIn, SpawnReason spawnReasonIn) 
	{
		return this.getBlockPathWeight(this.getPosition(), worldIn) >= 0.0F;
	}

	
	@Override
	public void setTamed(boolean tamed) {
		      super.setTamed(tamed);
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
	public void livingTick() {
		super.livingTick();
	}

	@Override
	public void tick() {
	      super.tick();
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
	      super.writeAdditional(compound);
	      if (this.getOwnerId() != null) {
	         compound.putUniqueId("Owner", this.getOwnerId());
	      }

	      compound.putBoolean("Sitting", this.sitting);
	}
	
	@Nullable
	@Override
	public UUID getOwnerId() {
	      return this.dataManager.get(MUSHPANG_OWNER_ID).orElse((UUID)null);
	   }
	@Override
	public void setOwnerId(@Nullable UUID uuid) {
	    	this.dataManager.set(MUSHPANG_OWNER_ID, Optional.ofNullable(uuid));
	}
	
	@Override
	public int getAngerTime() {
		return this.dataManager.get(VARINT);
		
	}

	@Override
	public void setAngerTime(int time) {
	      this.dataManager.set(VARINT, time);
	}

	@Override
	public UUID getAngerTarget() {
	     return this.uuid;
	}

	@Override
	public void setAngerTarget(UUID target) {
		this.uuid = target;
		
	}

	@Override
	public void func_230258_H__() {
	      this.setAngerTime(RANDOM_ANGER_TIME.getRandomWithinRange(this.rand));
	}

}
