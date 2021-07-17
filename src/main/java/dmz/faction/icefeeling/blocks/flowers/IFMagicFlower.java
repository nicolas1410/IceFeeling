package dmz.faction.icefeeling.blocks.flowers;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class IFMagicFlower extends FlowerBlock {

	public IFMagicFlower(Effect effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}

	@Override
	public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isRemote && worldIn.getDifficulty() != Difficulty.PEACEFUL) {
			if (entityIn instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity) entityIn;
				// livingentity.addPotionEffect(new EffectInstance(Effects.INSTANT_DAMAGE, 1));
				entityIn.attackEntityFrom(DamageSource.MAGIC, 0.5F);
			}
		}

	}
}
