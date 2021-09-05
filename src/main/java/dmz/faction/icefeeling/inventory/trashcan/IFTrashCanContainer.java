package dmz.faction.icefeeling.inventory.trashcan;

import dmz.faction.icefeeling.inventory.slot.IFSlotTrashCan;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;

public class IFTrashCanContainer extends Container {

	private final IInventory inventory;
	protected PlayerEntity playerEntity;
	protected IItemHandler playerInventory;
	protected IFTrashCanTileEntity te;

	public IFTrashCanContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world,
			PlayerEntity player) {
		this(id, playerInventory, pos, world, player, new Inventory(1));
	}

	public IFTrashCanContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, PlayerEntity player,
			IInventory inventory) {
		super(IFTileRegistry.TRASH_CAN_CONTAINER.get(), id);
		checkContainerSize(inventory, 1);
		this.inventory = inventory;
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(playerInventory);
		this.te = (IFTrashCanTileEntity) world.getBlockEntity(pos);
		inventory.startOpen(playerInventory.player);
		// slot
		this.addSlot(new IFSlotTrashCan(te, 0, 80, 62));
		layoutPlayerInventorySlots(8, 139);

	}

	@Override
	public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack itemstack1 = slot.getItem();
			itemstack = itemstack1.copy();
			if (index < this.inventory.getContainerSize()) {
				if (!this.moveItemStackTo(itemstack1, this.inventory.getContainerSize(), this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.moveItemStackTo(itemstack1, 0, this.inventory.getContainerSize(), false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}

		return itemstack;
	}

	@Override
	public boolean stillValid(PlayerEntity p_75145_1_) {
		return this.inventory.stillValid(p_75145_1_);
	}

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
}
