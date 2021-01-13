package dmz.faction.icefeeling.items.tools;

import java.util.List;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IItemTier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IFSwordItem extends SwordItem {
	
	public IFSwordItem(IItemTier tier, int attackDamageIn, float attackSpeedIn, Properties builderIn) {
		super(tier, attackDamageIn, attackSpeedIn, builderIn);
		
	}
	
	// Set on fire entitites for 10 seconds on hit, only on FIRE OPAL SWORD
	@Override
	public boolean hitEntity(final ItemStack stack, final LivingEntity target, final LivingEntity attacker) 
	{
		if(target.getFireTimer() <= 0) 
		{
			if(stack.getItem() == IFItems.FIRE_OPAL_SWORD.get()) 
			{
				if(attacker instanceof PlayerEntity) 
				{ target.setFire(10); }
					} else { return false; }
		}
		
	stack.damageItem(1, attacker, (entity) -> {
	           entity.sendBreakAnimation(EquipmentSlotType.MAINHAND);
	        }); return true;
	}
	
	@Override
	public boolean isEnchantable(ItemStack stack) {
	    if (stack.getItem() == IFItems.FIRE_OPAL_SWORD.get()) 
	    {
	        return false;
	        
	    } else {
	         return !stack.isEnchanted();
	      }
	}
	 
	
	@Override
	public boolean getIsRepairable(ItemStack toRepair, ItemStack repair) 
	{
		 if(toRepair.getItem() == IFItems.FIRE_OPAL_SWORD.get()) 
		 {
			 return false;
		 }
		 
	     return this.getTier().getRepairMaterial().test(repair) || super.getIsRepairable(toRepair, repair);
	}
	
	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book)
	{
		if(stack.getItemEnchantability() == 0) 
		{
			return false; 
	    }
		
		return true;
	}
	  
	@Override
	public boolean hasEffect(ItemStack stack) {
		
		if(stack.getItem() == IFItems.FIRE_OPAL_SWORD.get()) 
		{
			return true;
		
		} else if (stack.isEnchanted()){
				  return true;
			  }
		return false;
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		if(stack.getItem() == IFItems.FIRE_OPAL_SWORD.get()) 
		{
			tooltip.add(new StringTextComponent("Fire Aspect II").mergeStyle(TextFormatting.GRAY));		
		}

	}
}
