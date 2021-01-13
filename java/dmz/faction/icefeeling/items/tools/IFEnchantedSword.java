package dmz.faction.icefeeling.items.tools;

import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;

public class IFEnchantedSword extends SwordItem {

	public IFEnchantedSword(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
	}
	
	@Override
	public boolean hitEntity(final ItemStack stack, final LivingEntity target, final LivingEntity attacker) 
	{
		if(target.getFireTimer() <= 0) 
		{
			if(target != attacker) 
			{
					{
						target.setFire(10);
					}	
			}	
		}	
					
		stack.damageItem(1, attacker, (entity) -> {
			entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	    		}); return true; 
	}
	
	@Override
	public boolean makesPiglinsNeutral(ItemStack stack, LivingEntity wearer)
	   {
	       return true;
	   }
	
	@Override
	public float getXpRepairRatio(ItemStack stack)
	   {
	       return 0.1F;
	   }
}
