package dmz.faction.icefeeling.items.tools;

import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class IFEnchantedSword extends SwordItem {

	public IFEnchantedSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public float getXpRepairRatio(ItemStack stack)
	{
	       return 0F;
	}
}
