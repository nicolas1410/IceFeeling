package dmz.faction.icefeeling.inventory.singleitem.abstracts;

import dmz.faction.icefeeling.inventory.slot.IFSlotSingleItemChest;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public abstract class IFSingleItemChestContainerBase extends Container {

	protected IFSingleItemChestTileBase te;
	private final IInventory inventory;
	protected IItemHandler playerInventory;

	public IFSingleItemChestContainerBase(ContainerType<?> containerType, int id, PlayerInventory playerInventory,
			BlockPos pos, World world, PlayerEntity player) {
		this(containerType, id, playerInventory, pos, world, player, new Inventory(217));
	}

	public IFSingleItemChestContainerBase(ContainerType<?> containerType, int id, PlayerInventory playerInventory,
			BlockPos pos, World world, PlayerEntity player, IInventory inventory) {
		super(containerType, id);
		assertInventorySize(inventory, 216);
		this.inventory = inventory;
		this.playerInventory = new InvWrapper(playerInventory);
		this.te = (IFSingleItemChestTileBase) world.getTileEntity(pos);
		inventory.openInventory(playerInventory.player);
		// 18*12
		for (int j = 0; j < 12; ++j) {
			for (int k = 0; k < 18; ++k) {
				this.addSlot(new IFSlotSingleItemChest(te, k + j * 18, -73 + k * 18, -40 + j * 18));
			}
		}
		layoutPlayerInventorySlots(-73, 190);


	}

	/**
	 * e Determines whether supplied player can use this container
	 */
	@Override
	public boolean canInteractWith(PlayerEntity playerIn) {
		return this.inventory.isUsableByPlayer(playerIn);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 */

	private int addSlotRange(IItemHandler handler, int index, int x, int y, int amount, int dx) {
		for (int i = 0; i < amount; i++) {
			addSlot(new SlotItemHandler(handler, index, x, y));
			x += dx;
			index++;
		}
		return index;
	}

	private int addSlotBox(IItemHandler handler, int index, int x, int y, int horAmount, int dx, int verAmount,
			int dy) {
		for (int j = 0; j < verAmount; j++) {
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

	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index < this.inventory.getSizeInventory()) {
				if (!this.mergeItemStack(itemstack1, this.inventory.getSizeInventory(), this.inventorySlots.size(),
						true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 0, this.inventory.getSizeInventory(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}
		}

		return itemstack;
	}

	/**
	 * Called when the container is closed.
	 */
	@Override
	public void onContainerClosed(PlayerEntity playerIn) {
		super.onContainerClosed(playerIn);
		this.inventory.closeInventory(playerIn);
	}
}
