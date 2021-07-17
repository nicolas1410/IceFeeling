package dmz.faction.icefeeling.items.consumables;

import dmz.faction.icefeeling.items.IFItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class IFBurnableItems extends Item {
	
	public static int burnTime;
	
	public IFBurnableItems(Properties properties) {
		super(properties);
	}

	@Override
	public int getBurnTime(ItemStack stack) {
		
		if(stack.getItem() == IFItems.SOLARIUM.get()) {
			return burnTime = 12800;
		}

		return burnTime;
	}
	
}
