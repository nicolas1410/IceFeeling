package dmz.faction.icefeeling.inventory.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class IFCommonItemFilterSlot extends Slot {

	protected static int maxSlotStackLimit = 64;
	private final int slotIndex;

	public IFCommonItemFilterSlot(IInventory inventoryIn, int slotIndexIn, int xPosition, int yPosition) {
		super(inventoryIn, slotIndexIn, xPosition, yPosition);
		this.slotIndex = slotIndexIn;
	}

	/**
	 * Check if the stack is allowed to be placed in this slot, used for armor slots
	 * as well as furnace fuel.
	 */
	@Override
	public boolean mayPlace(ItemStack stack) {

		if (stack.getItem() == Items.COBBLESTONE || stack.getItem() == Items.DIRT || stack.getItem() == Items.SAND || stack.getItem() == Items.GRAVEL) {
			return true;
		} else {
			return false;
		}
	}
	
	
	public static int SlotStackLimit() {
		return maxSlotStackLimit;
	}

	@Override
	public ItemStack getItem() {
		return this.container.getItem(this.slotIndex);
	}

	@Override
	public int getMaxStackSize() {
		return maxSlotStackLimit;
	}

	@Override
	public int getMaxStackSize(ItemStack stack) {
		return this.getMaxStackSize();
	}

	@Override
	public void setChanged() {
		this.container.setChanged();
	}
}
