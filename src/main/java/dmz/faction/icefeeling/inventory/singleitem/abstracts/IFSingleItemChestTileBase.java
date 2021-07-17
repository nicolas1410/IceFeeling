package dmz.faction.icefeeling.inventory.singleitem.abstracts;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.blocks.blockgui.abstracts.IFCommonItemFilterBlock;
import dmz.faction.icefeeling.inventory.IFITileInventory;
import dmz.faction.icefeeling.inventory.slot.IFCommonItemFilterSlot;
import dmz.faction.icefeeling.inventory.slot.IFSlotSingleItemChest;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.Block;
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
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.INameable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public abstract class IFSingleItemChestTileBase extends TileEntity
		implements IInventory, IFITileInventory, INamedContainerProvider, INameable, ISidedInventory {

	protected ITextComponent name;
	public NonNullList<ItemStack> chestContents = NonNullList.withSize(216, ItemStack.EMPTY);
	/** The current angle of the lid (between 0 and 1) */
	protected float lidAngle;
	/** The angle of the lid last tick */
	protected float prevLidAngle;
	/** The number of players currently using this chest */
	protected int numPlayersUsing;

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbtTag = new CompoundNBT();
		this.write(nbtTag);
		this.markDirty();
		return new SUpdateTileEntityPacket(getPos(), -1, nbtTag);
	}

	@Override
	public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
		CompoundNBT tag = pkt.getNbtCompound();
		this.read(world.getBlockState(pos), tag);
		this.markDirty();
		world.notifyBlockUpdate(pos, world.getBlockState(pos).getBlock().getDefaultState(), world.getBlockState(pos),
				2);
	}

	public IFSingleItemChestTileBase(TileEntityType<?> tileEntityTypeIn) {
		super(tileEntityTypeIn);
	}

	/**
	 * Returns the number of slots in the inventory.
	 */

	@Override
	public boolean isEmpty() {
		return this.chestContents.stream().allMatch(ItemStack::isEmpty);
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
		this.getInventoryStackLimit();
		this.markDirty();
	}

	@Override
	public int getSizeInventory() {
		return 217;
	}

	/**
	 * Returns the stack in the given slot.
	 */
	public ItemStack getStackInSlot(int index) {
		return this.chestContents.get(index);
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns
	 * them in a new stack. In this case, split the stack in 2.
	 */
	public ItemStack decrStackSize(int index, int count) {
		return ItemStackHelper.getAndSplit(this.chestContents, index, count);
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	public ItemStack removeStackFromSlot(int index) {
		return ItemStackHelper.getAndRemove(this.chestContents, index);
	}

	@Override
	public void read(BlockState state, CompoundNBT compound) {
		super.read(state, compound);

		this.chestContents = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(compound, this.chestContents);

	}

	@Override
	public CompoundNBT write(CompoundNBT compound) {
		super.write(compound);
		ItemStackHelper.saveAllItems(compound, this.chestContents);
		return compound;
	}

	/*
	 * public static int getNumberOfPlayersUsing(World worldIn, LockableTileEntity
	 * lockableTileEntity, int ticksSinceSync, int x, int y, int z, int
	 * numPlayersUsing) { if (!worldIn.isRemote && numPlayersUsing != 0 &&
	 * (ticksSinceSync + x + y + z) % 200 == 0) { numPlayersUsing =
	 * getNumberOfPlayersUsing(worldIn, lockableTileEntity, x, y, z); }
	 * 
	 * return numPlayersUsing; }
	 * 
	 * public static int getNumberOfPlayersUsing(World world, LockableTileEntity
	 * lockableTileEntity, int x, int y, int z) { int i = 0;
	 * 
	 * for (PlayerEntity playerentity :
	 * world.getEntitiesWithinAABB(PlayerEntity.class, new AxisAlignedBB((double)
	 * ((float) x - 5.0F), (double) ((float) y - 5.0F), (double) ((float) z - 5.0F),
	 * (double) ((float) (x + 1) + 5.0F), (double) ((float) (y + 1) + 5.0F),
	 * (double) ((float) (z + 1) + 5.0F)))) { if (playerentity.openContainer
	 * instanceof IFSingleItemChestContainerBase) { ++i; } }
	 * 
	 * return i; }
	 */

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
	public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
		return IcanExtractItem(index, stack, direction);
	}

	@Override
	public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return this.IisItemValidForSlot(index, itemStackIn);
	}

	@Override
	public String IgetName() {
		return "container." + Main.MOD_ID + ".single_item_chest_big";
	}

	@Override
	public void clear() {
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
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.world.getTileEntity(this.pos) != this) {
			return false;
		} else {
			return !(player.getDistanceSq((double) this.pos.getX() + 0.5D, (double) this.pos.getY() + 0.5D,
					(double) this.pos.getZ() + 0.5D) > 64.0D);
		}
	}
}
