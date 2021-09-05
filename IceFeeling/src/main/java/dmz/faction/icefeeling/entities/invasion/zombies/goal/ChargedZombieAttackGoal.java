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

	public void start() {
		super.start();
		this.raiseArmTicks = 0;
	}

	public void stop() {
		super.stop();
		this.zombie.setAggressive(false);
	}

	public void tick() {
		super.tick();
		++this.raiseArmTicks;
		if (this.raiseArmTicks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2) {
			this.zombie.setAggressive(true);
		} else {
			this.zombie.setAggressive(false);
		}

	}

}
