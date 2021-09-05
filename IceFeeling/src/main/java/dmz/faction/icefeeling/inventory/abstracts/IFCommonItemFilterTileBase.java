package dmz.faction.icefeeling.inventory.abstracts;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.inventory.IFITileInventory;
import dmz.faction.icefeeling.inventory.slot.IFCommonItemFilterSlot;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public abstract class IFCommonItemFilterTileBase extends TileEntity
		implements IInventory, IFITileInventory, INamedContainerProvider, INameable, ISidedInventory {

	protected ITextComponent name;
	public NonNullList<ItemStack> chestContents = NonNullList.withSize(1, ItemStack.EMPTY);
	/** The current angle of the lid (between 0 and 1) */
	protected float lidAngle;
	/** The angle of the lid last tick */
	protected float prevLidAngle;
	/** The number of players currently using this chest */
	protected int numPlayersUsing;
	protected BlockPos pos = this.worldPosition;

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbtTag = new CompoundNBT();
		this.save(nbtTag);
		this.setChanged();
		return new SUpdateTileEntityPacket(getBlockPos(), -1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getTag();
		this.load(level.getBlockState(pos), tag);
		this.setChanged();
		level.markAndNotifyBlock(pos, null, level.getBlockState(pos).getBlock().defaultBlockState(), level.getBlockState(pos),
				2, 2);
	}

	public IFCommonItemFilterTileBase(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	/**
	 * Returns the number of slots in the inventory.
	 */

	@Override
	public boolean isEmpty() {
		return this.chestContents.stream().allMatch(ItemStack::isEmpty);
	}

	@Override
	public int getMaxStackSize() {
		return IFCommonItemFilterSlot.SlotStackLimit();
	}

	/*
	 * 
	 * 
	 * CHANGER CA POUR AVOIR PLUS DE PLACE DANS LE SLOT
	 * 
	 * 
	 */
	public void setInventorySlotContents(int index, ItemStack stack) {
		this.chestContents.set(index, stack);
		this.getMaxStackSize();
		this.setChanged();
	}

	@Override
	public int getContainerSize() {
		return 1;
	}

	/**
	 * Returns the stack in the given slot.
	 */
	@Override
	public ItemStack getItem(int index) {
		return this.chestContents.get(index);
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns
	 * them in a new stack. In this case, split the stack in 2.
	 */
	@Override
	public ItemStack removeItem(int index, int count) {
		return ItemStackHelper.removeItem(this.chestContents, index, count);
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	@Override
	public ItemStack removeItemNoUpdate(int index) { 
		return ItemStackHelper.takeItem(this.chestContents, index);
	}
	
	@Override
	public void setItem(int index, ItemStack stack) {
		this.chestContents.set(index, stack);
		
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);

		this.chestContents = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.chestContents);

	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, this.chestContents);
		return compound;
	}

	@Override
	public boolean IisItemValidForSlot(int index, ItemStack stack) {
		if (stack.getItem() == Items.COBBLESTONE || stack.getItem() == Items.DIRT || stack.getItem() == Items.SAND || stack.getItem() == Items.GRAVEL) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from
	 * the given side.
	 */
	@Override
	public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
		if (direction == Direction.DOWN && index == 0) {
			Item item = stack.getItem();
			if (item == Items.DIRT || item == Items.COBBLESTONE || item == Items.SAND || item == Items.GRAVEL) {
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return IcanExtractItem(index, stack, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return this.IisItemValidForSlot(index, itemStackIn);
	}

	@Override
	public String IgetName() {
		return "container." + Main.MOD_ID + ".common_item_filter";
	}

	@Override
	public void clearContent() {
		this.chestContents.clear();
	}

	@Override
	public boolean hasCustomName() {
		return this.name != null;
	}

	public void setCustomName(ITextComponent name) {
		this.name = name;
	}

	@Nullable
	@Override
	public ITextComponent getCustomName() {
		return this.name;
	}

	@Override
	public ITextComponent getDisplayName() {
		return this.getName();
	}

	@Override
	public ITextComponent getName() {
		return (this.name != null ? this.name : new TranslationTextComponent(IgetName()));
	}

	@Nullable
	@Override
	public Container createMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return IcreateMenu(id, playerInventory, playerEntity);
	}



	@Override
	public boolean stillValid(PlayerEntity player) {
		if (this.level.getBlockEntity(this.pos) != this) {
			return false;
		} else {
			return !(player.distanceToSqr((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) > 64.0D);
		}
	}
}
