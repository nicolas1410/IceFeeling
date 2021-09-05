package dmz.faction.icefeeling.inventory.slot;

import dmz.faction.icefeeling.inventory.trashcan.IFTrashCanTileEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class IFSlotTrashCan extends Slot {

	private final int slotIndex;
	IFTrashCanTileEntity te;
	
	public IFSlotTrashCan(IFTrashCanTileEntity te, int slotIndexIn, int xPosition, int yPosition) {
		super(te, slotIndexIn, xPosition, yPosition);
		this.slotIndex = slotIndexIn;
		this.te = te;
	}
	
	@Override
	public ItemStack getItem() {
		return this.container.getItem(this.slotIndex);
	}

	@Override
	public void setChanged() {
		this.container.setChanged();;
	}
}
