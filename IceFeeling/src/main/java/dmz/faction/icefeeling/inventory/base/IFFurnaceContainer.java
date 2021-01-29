package dmz.faction.icefeeling.inventory.base;

import dmz.faction.icefeeling.inventory.slot.IFSlotFurnace;
import dmz.faction.icefeeling.inventory.slot.IFSlotFurnaceFuel;
import dmz.faction.icefeeling.tileentity.base.IFFurnaceTileEntity;
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

public abstract class IFFurnaceContainer extends Container {

	protected IInventory furnaceInventory;
	protected IFFurnaceTileEntity te;
	private final IIntArray furnaceData;
	protected PlayerEntity playerEntity;
	protected IItemHandler playerInventory;
	protected final World world;
	private IRecipeType<? extends AbstractCookingRecipe> recipeType;

	public IFFurnaceContainer(ContainerType<?> containerType, int windowId, World world, BlockPos pos,
			PlayerInventory playerInventory, PlayerEntity player) {
		this(containerType, windowId, world, pos, playerInventory, player, new IntArray(4));
	}

	public IFFurnaceContainer(ContainerType<?> containerType, int windowId, World world, BlockPos pos,
			PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
		super(containerType, windowId);
		this.te = (IFFurnaceTileEntity) world.getTileEntity(pos);
		this.recipeType = te.recipeType;
		assertInventorySize(this.te, 3);
		this.playerEntity = player;
		this.playerInventory = new InvWrapper(playerInventory);
		this.world = playerInventory.player.world;
		this.furnaceData = fields;
		assertIntArraySize(this.furnaceData, 4);
		this.trackIntArray(this.furnaceData);

		this.addSlot(new Slot(te, 0, 56, 17));
		this.addSlot(new IFSlotFurnaceFuel(this.te, 1, 56, 53));
		this.addSlot(new IFSlotFurnace(playerEntity, te, 2, 116, 35));
		layoutPlayerInventorySlots(8, 84);

	}

	public BlockPos getPos() {
		return this.te.getPos();
	}

	public boolean isBurning() {
		return this.te.isBurning();
	}

	public int getCookScaled(int pixels) {
		int i = this.furnaceData.get(2);
		int j = this.furnaceData.get(3);
		return j != 0 && i != 0 ? i * pixels / j : 0;
	}

	public int getBurnLeftScaled(int pixels) {
		int i = this.furnaceData.get(1);
		if (i == 0) {
			i = 200;
		}

		return this.furnaceData.get(0) * pixels / i;
	}

	@Override
	public void updateProgressBar(int id, int data) {
		super.updateProgressBar(id, data);
		this.te.furnaceData.set(id, data);
	}

	/**
	 * Handle when the stack in slot {@code index} is shift-clicked. Normally this
	 * moves the stack between the player inventory and the other inventory(s).
	 */
	@Override
	public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.inventorySlots.get(index);
		if (slot != null && slot.getHasStack()) {
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();
			if (index == 2) {
				if (!this.mergeItemStack(itemstack1, 3, 39, true)) {
					return ItemStack.EMPTY;
				}

				slot.onSlotChange(itemstack1, itemstack);
			} else if (index != 1 && index != 0) {
				if (this.hasRecipe(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
						return ItemStack.EMPTY;
					}
				} else if (IFFurnaceTileEntity.isFuel(itemstack1)) {
					if (!this.mergeItemStack(itemstack1, 1, 2, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 3 && index < 30) {
					if (!this.mergeItemStack(itemstack1, 30, 39, false)) {
						return ItemStack.EMPTY;
					}
				} else if (index >= 30 && index < 39 && !this.mergeItemStack(itemstack1, 3, 30, false)) {
					return ItemStack.EMPTY;
				}
			} else if (!this.mergeItemStack(itemstack1, 3, 39, false)) {
				return ItemStack.EMPTY;
			}

			if (itemstack1.isEmpty()) {
				slot.putStack(ItemStack.EMPTY);
			} else {
				slot.onSlotChanged();
			}

			if (itemstack1.getCount() == itemstack.getCount()) {
				return ItemStack.EMPTY;
			}

			slot.onTake(playerIn, itemstack1);
		}

		return itemstack;
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

	protected boolean hasRecipe(ItemStack stack) {
		return this.world.getRecipeManager().getRecipe((IRecipeType) this.recipeType, new Inventory(stack), this.world)
				.isPresent();
	}
}
