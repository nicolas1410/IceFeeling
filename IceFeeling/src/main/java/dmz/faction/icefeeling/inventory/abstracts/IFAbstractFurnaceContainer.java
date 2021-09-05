package dmz.faction.icefeeling.inventory.abstracts;

import dmz.faction.icefeeling.inventory.slot.IFFurnaceFuelSlot;
import dmz.faction.icefeeling.inventory.slot.IFFurnaceSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class IFAbstractFurnaceContainer extends Container {

	protected IFAbstractFurnaceTileEntity te;
    protected IIntArray fields;
    protected PlayerEntity playerEntity;
    protected IItemHandler playerInventory;
    protected final World world;
    protected static IInventory inventory;
    
    private IRecipeType<? extends AbstractCookingRecipe> recipeType;

    public IFAbstractFurnaceContainer(ContainerType<?> containerType, int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        this(containerType, windowId, world, pos, playerInventory, player, inventory, new IntArray(5));
    }

    public IFAbstractFurnaceContainer(ContainerType<?> containerType, int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IInventory inventory, IIntArray fields) {
        super(containerType, windowId);
        this.te = (IFAbstractFurnaceTileEntity) world.getBlockEntity(pos);
        this.recipeType = te.recipeType;
        checkContainerSize(this.te, 3);
        this.playerEntity = player;
        this.playerInventory = new InvWrapper(playerInventory);
        this.world = playerInventory.player.level;
        this.fields = fields;
        checkContainerDataCount(this.fields, 3);
        this.addDataSlots(this.fields);

        this.addSlot(new Slot(te, 0, 56, 17));
        this.addSlot(new IFFurnaceFuelSlot(this.te, 1, 56, 53));
        this.addSlot(new IFFurnaceSlot(playerEntity, te, 2, 116, 35));
        layoutPlayerInventorySlots(8, 84);
    }
     
 
    public boolean isBurning() {
        return this.te.isBurning();
    }

    public int getCookScaled(int pixels) {
        int i = this.fields.get(2);
        int j = this.fields.get(3);
        return j != 0 && i != 0 ? i * pixels / j : 0;
    }

    public int getBurnLeftScaled(int pixels) {
        int i = this.fields.get(1);
        if (i == 0) {
            i = 100;
        }

        return this.fields.get(0) * pixels / i;
    }

    @Override
    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
    	 ItemStack itemstack = ItemStack.EMPTY;
         Slot slot = this.slots.get(index);
         if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 2) {
               if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                  return ItemStack.EMPTY;
               }

               slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
               if (this.hasRecipe(itemstack1)) {
                  if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                     return ItemStack.EMPTY;
                  }
               } else if (IFAbstractFurnaceTileEntity.isFuel(itemstack1)) {
                  if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                     return ItemStack.EMPTY;
                  }
               } else if (index >= 3 && index < 30) {
                  if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                     return ItemStack.EMPTY;
                  }
               } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                  return ItemStack.EMPTY;
               }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
               return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
               slot.set(ItemStack.EMPTY);
            } else {
               slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
               return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
         }

         return itemstack;
      }

    private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
        for (int i = 0 ; i < amount ; i++) {
            addSlot(new SlotItemHandler(handler, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0 ; j < verAmount ; j++) {
            index = addSlotRange(handler, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }

    private void layoutPlayerInventorySlots(int leftCol, int topRow) {
        // Player inventory
        addSlotBox(playerInventory, 9, leftCol, topRow, 9, 18, 3, 18);

        // Hotbar
        topRow += 58;
        addSlotRange(playerInventory, 0, leftCol, topRow, 9, 18);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	protected boolean hasRecipe(ItemStack stack) {
      
        return this.world.getRecipeManager().getRecipeFor((IRecipeType)this.recipeType, new Inventory(stack), this.world).isPresent();
    }

}
