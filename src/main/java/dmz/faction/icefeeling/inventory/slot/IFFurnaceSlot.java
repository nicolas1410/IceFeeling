package dmz.faction.icefeeling.inventory.slot;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class IFFurnaceSlot extends Slot {
   private final PlayerEntity player;
   private int removeCount;

   public IFFurnaceSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
      super(inventoryIn, slotIndex, xPosition, yPosition);
      this.player = player;
   }

   /**
    * Check if the stack is allowed to be placed in this slot, used for armor slots as well as furnace fuel.
    */
   @Override
   public boolean mayPlace(ItemStack stack) {
      return false;
   }

   /**
    * Decrease the size of the stack in slot (first int arg) by the amount of the second int arg. Returns the new stack.
    */
   @Override
   public ItemStack remove(int amount) {
      if (this.hasItem()) {
         this.removeCount += Math.min(amount, this.getItem().getCount());
      }

      return super.remove(amount);
   }

   @Override
   public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
      this.checkTakeAchievements(stack);
      super.onTake(thePlayer, stack);
      return stack;
   }

   /**
    * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood. Typically increases an
    * internal count then calls onCrafting(item).
    */
   
   @Override
   protected void onQuickCraft(ItemStack stack, int amount) {
	      this.removeCount += amount;
	      this.checkTakeAchievements(stack);
	   }

   /**
    * the itemStack passed in is the output - ie, iron ingots, and pickaxes, not ore and wood.
    */
  
   protected void checkTakeAchievements(ItemStack stack) {
      stack.onCraftedBy(this.player.level, this.player, this.removeCount);
      if (!this.player.level.isClientSide && this.container instanceof IFAbstractFurnaceTileEntity) {
	         ((IFAbstractFurnaceTileEntity)this.container).awardUsedRecipesAndPopExperience(this.player);
	      }
      this.removeCount = 0;
      net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
   }
   
}
