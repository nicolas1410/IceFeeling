package dmz.faction.icefeeling.blocks.flowers;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class IFSlownessFlower extends FlowerBlock {
	
	public IFSlownessFlower(Effect effect, int effectDuration, Properties properties) {
		super(effect, effectDuration, properties);
	}

	@Override
	public void entityInside(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
		if (!worldIn.isClientSide && worldIn.getDifficulty() != Difficulty.PEACEFUL) {
			if (entityIn instanceof LivingEntity) {
				LivingEntity livingentity = (LivingEntity) entityIn;
				livingentity.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 200));
			}
		}
	}
}
