package dmz.faction.icefeeling.items.foods;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Rarity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class IFApples extends Item {

	
	public IFApples(Properties properties, 		Effect effectIn, int durationIn, int amplifierIn, float probability, 
												Effect effectIn2, int durationIn2, int amplifierIn2, float probability2, 
												Effect effectIn3, int durationIn3, int amplifierIn3, float probability3,
												Effect effectIn4, int durationIn4, int amplifierIn4, float probability4, Rarity rarity) 
	{
		
			super(new Item.Properties().group(Main.ICEFEELING).rarity(rarity)
					.food(new Food.Builder()
					.hunger(4)
					.saturation(1.2F)	
					.setAlwaysEdible()
					.effect(() -> new EffectInstance(effectIn, durationIn, amplifierIn), probability)
					.effect(() -> new EffectInstance(effectIn2, durationIn2, amplifierIn2), probability2)
					.effect(() -> new EffectInstance(effectIn3, durationIn3, amplifierIn3), probability3)
					.effect(() -> new EffectInstance(effectIn4, durationIn4, amplifierIn4), probability4).build()));
				
	}

	@Override
	public boolean hasEffect(ItemStack stack) {
		if (stack.getItem() == IFItems.OPAL_APPLE.get()) {
		      return true;
		   } else {
			   return false;
		   }
	}

}