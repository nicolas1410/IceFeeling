package dmz.faction.icefeeling.entities.invasion.zombies.goal;

import dmz.faction.icefeeling.entities.invasion.zombies.ChargedZombie;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class ChargedZombieAttackGoal extends MeleeAttackGoal {
   private final ChargedZombie zombie;
   private int raiseArmTicks;

   public ChargedZombieAttackGoal(ChargedZombie zombieIn, double speedIn, boolean longMemoryIn) {
      super(zombieIn, speedIn, longMemoryIn);
      this.zombie = zombieIn;
   }

   /**
    * Execute a one shot task or start executing a continuous task
    */
   public void startExecuting() {
      super.startExecuting();
      this.raiseArmTicks = 0;
   }

   /**
    * Reset the task's internal state. Called when this task is interrupted by another one
    */
   public void resetTask() {
      super.resetTask();
      this.zombie.setAggroed(false);
   }

   /**
    * Keep ticking a continuous task that has already been started
    */
   public void tick() {
      super.tick();
      ++this.raiseArmTicks;
      if (this.raiseArmTicks >= 5 && this.func_234041_j_() < this.func_234042_k_() / 2) {
         this.zombie.setAggroed(true);
      } else {
         this.zombie.setAggroed(false);
      }

   }
   
}
