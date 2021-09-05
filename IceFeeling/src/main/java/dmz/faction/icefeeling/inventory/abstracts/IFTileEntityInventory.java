package dmz.faction.icefeeling.inventory.abstracts;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.inventory.IFITileInventory;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
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

public abstract class IFTileEntityInventory extends TileEntity
		implements IFITileInventory, ISidedInventory, INamedContainerProvider, INameable {

	public NonNullList<ItemStack> inventory;
	protected ITextComponent name;
	protected BlockPos pos = this.worldPosition;

	public IFTileEntityInventory(TileEntityType<?> tileEntityTypeIn, int inventorySize) {
		super(tileEntityTypeIn);
		inventory = NonNullList.withSize(inventorySize, ItemStack.EMPTY);
	}

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbtTag = new CompoundNBT();
		this.save(nbtTag);
		this.setChanged();
		return new SUpdateTileEntityPacket(this.worldPosition, -1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getTag();
		this.load(level.getBlockState(this.worldPosition), tag);
		this.setChanged();
		level.markAndNotifyBlock(this.worldPosition, level.getChunkAt(this.worldPosition), level.getBlockState(this.worldPosition).getBlock().defaultBlockState(), level.getBlockState(this.worldPosition), 2, 2);
	}

	@Override
	public CompoundNBT getUpdateTag() {
		CompoundNBT compound = new CompoundNBT();

		this.save(compound);
		return compound;
	}

	public boolean isItemValidForSlot(int index, ItemStack stack) {
		return this.IisItemValidForSlot(index, stack);
	}

	public void setCustomName(ITextComponent name) {
		this.name = name;
	}

	@Override
	public ITextComponent getName() {
		return (this.name != null ? this.name : new TranslationTextComponent(IgetName()));
	}

	@Override
	public boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
		return IcanExtractItem(index, stack, direction);
	}

	@Override
	public boolean canPlaceItemThroughFace(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	@Override
	public int getContainerSize() {
		return this.inventory.size();
	}

	@Override
	public boolean isEmpty() {
		for (ItemStack itemstack : this.inventory) {
			if (!itemstack.isEmpty()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ItemStack getItem(int index) {
		return this.inventory.get(index);
	}

	@Override
	public ItemStack removeItem(int index, int count) {
		return ItemStackHelper.removeItem(this.inventory, index, count);
	}

	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ItemStackHelper.takeItem(this.inventory, index);
	}

	@Override
	public void setItem(int index, ItemStack stack) {
		this.inventory.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}
	}

	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public void load(BlockState state, CompoundNBT compound) {
		super.load(state, compound);
		this.inventory = NonNullList.withSize(this.getContainerSize(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.inventory);
		if (compound.contains("CustomName", 8)) {
			this.name = ITextComponent.Serializer.fromJson(compound.getString("CustomName"));
		}
	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		ItemStackHelper.saveAllItems(compound, this.inventory);
		if (this.name != null) {
			compound.putString("CustomName", ITextComponent.Serializer.toJson(this.name));
		}
		return compound;
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

	@Override
	public void startOpen(PlayerEntity player) {
	}

	@Override
	public void stopOpen(PlayerEntity player) {
	}

	@Override
	public void clearContent() {
		this.inventory.clear();
	}

	@Override
	public boolean hasCustomName() {
		return this.name != null;
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

	@Nullable
	@Override
	public Container createMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return IcreateMenu(i, playerInventory, playerEntity);
	}
	
	@Override
	public void setChanged()
	{
		super.setChanged();
		
	}
}
