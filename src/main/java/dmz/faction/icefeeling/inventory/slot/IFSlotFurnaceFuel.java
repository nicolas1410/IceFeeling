package dmz.faction.icefeeling.inventory.slot;

import dmz.faction.icefeeling.tileentity.base.IFFurnaceTileEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class IFSlotFurnaceFuel extends Slot {

	IFFurnaceTileEntity te;
	
	public IFSlotFurnaceFuel(IFFurnaceTileEntity te, int index, int x, int y) {
		super(te, index, x, y);
		this.te = te;
	}

	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots
	 * as well as furnace fuel.
	 */
	public boolean isItemValid(ItemStack stack) {
		return IFFurnaceTileEntity.isFuel(stack) || isBucket(stack);
	}

	public int getItemStackLimit(ItemStack stack) {
		return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
	}

	public static boolean isBucket(ItemStack stack) {
		return stack.getItem() == Items.BUCKET;
	}
}
