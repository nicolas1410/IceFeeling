package dmz.faction.icefeeling.inventory.slot;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceTileEntity;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class IFFurnaceFuelSlot extends Slot {
	
	IFAbstractFurnaceTileEntity te;
	
   public IFFurnaceFuelSlot(IFAbstractFurnaceTileEntity te, int index, int xPos, int yPos) {
      super(te, index, xPos, yPos);
      this.te = te;
   }

   /**
    * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
    */
   public boolean isItemValid(ItemStack stack) {
      return IFAbstractFurnaceTileEntity.isFuel(stack) || isBucket(stack);
   }

   public int getItemStackLimit(ItemStack stack) {
      return isBucket(stack) ? 1 : super.getItemStackLimit(stack);
   }

   public static boolean isBucket(ItemStack stack) {
      return stack.getItem() == Items.BUCKET;
   }
}
