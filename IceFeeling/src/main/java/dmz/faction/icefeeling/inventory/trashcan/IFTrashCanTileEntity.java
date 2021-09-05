package dmz.faction.icefeeling.inventory.trashcan;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.inventory.abstracts.IFTileEntityInventory;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class IFTrashCanTileEntity extends IFTileEntityInventory implements IInventory, ITickableTileEntity {
	
	protected ITextComponent name;
	protected NonNullList<ItemStack> items = NonNullList.withSize(1, ItemStack.EMPTY);
	private static final int[] SLOT = new int[] { 0 };

	public IFTrashCanTileEntity() {
		super(IFTileRegistry.TRASH_CAN_TILE.get(), 1);
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.items) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}

		return true;
	}

	public void setCustomName(ITextComponent name) {
		this.name = name;
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.level.getBlockEntity(this.pos) != this) {
			return false;
		} else {
			return player.distanceToSqr((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) <= 64.0D;
		}
	}

	public void clear() {
		this.items.clear();
	}

	@Override
	public void tick() {
		if(!this.isEmpty()) {
			this.clear();
		}
		
	}

	@Override
	public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
		return false;
	}

	@Override
	public String IgetName() {
		return "container." + Main.MOD_ID + ".trash_can";
	}

	@Override
	public boolean IisItemValidForSlot(int index, ItemStack stack) {
		return true;
	}

	@Override
	public Container IcreateMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new IFTrashCanContainer(id, playerInventory, pos, level, playerEntity);
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		return SLOT;
	}
	
	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return true;
	}
}
