package dmz.faction.icefeeling.items.consumables;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IFBurnableItems extends Item {

	public IFBurnableItems(Properties properties) {
		super(properties);
	}

	@Override
	public int getBurnTime(ItemStack stack) {
		
		if(stack.getItem() == IFItems.SOLARIUM.get()) {
			return 12000;
		}

		return 0;
	}
	
}
