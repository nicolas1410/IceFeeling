package dmz.faction.icefeeling.inventory.abstracts;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import dmz.faction.icefeeling.blocks.blockgui.abstracts.IFAbstractFurnaceBlock;
import dmz.faction.icefeeling.items.consumables.IFBurnableItems;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public abstract class IFAbstractFurnaceTileEntity extends IFTileEntityInventory implements ITickableTileEntity, IRecipeHolder, IRecipeHelperPopulator {


	protected NonNullList<ItemStack> items = NonNullList.withSize(3, ItemStack.EMPTY);
	public int burnTime;
	public int recipesUsed;
	public int cookTime;
	public int cookTimeTotal;
	
	public final IIntArray furnaceData = new IIntArray() {
	      public int get(int index) {
	         switch(index) {
	         case 0:
	            return IFAbstractFurnaceTileEntity.this.burnTime;
	         case 1:
	            return IFAbstractFurnaceTileEntity.this.recipesUsed;
	         case 2:
	            return IFAbstractFurnaceTileEntity.this.cookTime;
	         case 3:
	            return IFAbstractFurnaceTileEntity.this.cookTimeTotal;
	         default:
	            return 0;
	         }
	      }

	      public void set(int index, int value) {
	         switch(index) {
	         case 0:
	        	 IFAbstractFurnaceTileEntity.this.burnTime = value;
	            break;
	         case 1:
	        	 IFAbstractFurnaceTileEntity.this.recipesUsed = value;
	            break;
	         case 2:
	        	 IFAbstractFurnaceTileEntity.this.cookTime = value;
	            break;
	         case 3:
	        	 IFAbstractFurnaceTileEntity.this.cookTimeTotal = value;
	         }

	      }
	      
	      public int getCount() {
	          return 4;
	       }
	   };

	private final Object2IntOpenHashMap<ResourceLocation> recipes = new Object2IntOpenHashMap<>();
	public final IRecipeType<? extends AbstractCookingRecipe> recipeType;

	@Override
	public SUpdateTileEntityPacket getUpdatePacket() {
		CompoundNBT nbtTag = new CompoundNBT();
		this.save(nbtTag);
		this.setChanged();
		return new SUpdateTileEntityPacket(this.worldPosition, -1, nbtTag);
	}

	protected IFAbstractFurnaceTileEntity(TileEntityType<?> tileTypeIn) {
		super(tileTypeIn, 4);
		this.recipeType = IRecipeType.SMELTING;

	}

	@SuppressWarnings("deprecation")
	public static int getBurnTimes(ItemStack burnTime) {
		return ForgeHooks.getBurnTime(burnTime);
	}

	public boolean isBurning() {
	    return this.furnaceData.get(0) > 0;
	}

	@Override
	public void load(BlockState state, CompoundNBT nbt) {
		super.load(state, nbt);
		this.items = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
		ItemStackHelper.loadAllItems(nbt, this.items);
		this.burnTime = nbt.getInt("BurnTime");
		this.cookTime = nbt.getInt("CookTime");
		this.cookTimeTotal = nbt.getInt("CookTimeTotal");
		this.recipesUsed = this.getBurnTime(this.items.get(1));
		CompoundNBT compoundnbt = nbt.getCompound("RecipesUsed");

		for (String s : compoundnbt.getAllKeys()) {
			this.recipes.put(new ResourceLocation(s), compoundnbt.getInt(s));
		}

	}

	@Override
	public CompoundNBT save(CompoundNBT compound) {
		super.save(compound);
		compound.putInt("BurnTime", this.burnTime);
		compound.putInt("CookTime", this.cookTime);
		compound.putInt("CookTimeTotal", this.cookTimeTotal);
		ItemStackHelper.saveAllItems(compound, this.items);
		CompoundNBT compoundnbt = new CompoundNBT();
		this.recipes.forEach((recipeId, craftedAmount) -> {
			compoundnbt.putInt(recipeId.toString(), craftedAmount);
		});
		compound.put("RecipesUsed", compoundnbt);
		return compound;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void tick() {
		boolean flag = this.isBurning();
		boolean flag1 = false;
		if (this.isBurning()) {
			--this.burnTime;
		}

		if (!this.level.isClientSide) {
			ItemStack itemstack = this.items.get(1);
			if (this.isBurning() || !itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
				IRecipe<?> irecipe = this.level.getRecipeManager()
						.getRecipeFor((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.level).orElse(null);
				if (!this.isBurning() && this.canSmelt(irecipe)) {
					this.burnTime = this.getBurnTime(itemstack);
					this.recipesUsed = this.burnTime;
					if (this.isBurning()) {
						flag1 = true;
						if (itemstack.hasContainerItem())
							this.items.set(1, itemstack.getContainerItem());
						else if (!itemstack.isEmpty()) {
							itemstack.shrink(1);
							if (itemstack.isEmpty()) {
								this.items.set(1, itemstack.getContainerItem());
							}
						}
					}
				}

				if (this.isBurning() && this.canSmelt(irecipe)) {
					++this.cookTime;
					if (this.cookTime == this.cookTimeTotal) {
						this.cookTime = 0;
						this.cookTimeTotal = this.getCookTime();
						this.smelt(irecipe);
						flag1 = true;
					}
				} else {
					this.cookTime = 0;
				}
			} else if (!this.isBurning() && this.cookTime > 0) {
				this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
			}

			if (flag != this.isBurning()) {
				flag1 = true;
				this.level.setBlock(this.worldPosition, this.level.getBlockState(this.worldPosition).setValue(IFAbstractFurnaceBlock.LIT,Boolean.valueOf(this.isBurning())), 3);
			}
		}

		if (flag1) {
			this.setChanged();
		}

	}

	protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
		if (!this.items.get(0).isEmpty() && recipeIn != null) {
			ItemStack itemstack = recipeIn.getResultItem();
			if (itemstack.isEmpty()) {
				return false;
			} else {
				ItemStack itemstack1 = this.items.get(2);
				if (itemstack1.isEmpty()) {
					return true;
				} else if (!itemstack1.sameItem(itemstack)) {
					return false;
				} else if (itemstack1.getCount() + itemstack.getCount() <= this.getInventoryStackLimit()
						&& itemstack1.getCount() + itemstack.getCount() <= itemstack1.getMaxStackSize()) { // Forge fix:
																											// make
																											// furnace
																											// respect
																											// stack
																											// sizes in
																											// furnace
																											// recipes
					return true;
				} else {
					return itemstack1.getCount() + itemstack.getCount() <= itemstack.getMaxStackSize(); // Forge fix:
																										// make furnace
																										// respect stack
																										// sizes in
																										// furnace
																										// recipes
				}
			}
		} else {
			return false;
		}
	}

	private void smelt(@Nullable IRecipe<?> recipe) {
		if (recipe != null && this.canSmelt(recipe)) {
			ItemStack itemstack = this.items.get(0);
			ItemStack itemstack1 = recipe.getResultItem();
			ItemStack itemstack2 = this.items.get(2);
			if (itemstack2.isEmpty()) {
				this.items.set(2, itemstack1.copy());
			} else if (itemstack2.getItem() == itemstack1.getItem()) {
				itemstack2.grow(itemstack1.getCount());
			}

			if (!this.level.isClientSide) {
				this.setRecipeUsed(recipe);
			}

			if (itemstack.getItem() == Blocks.WET_SPONGE.asItem() && !this.items.get(1).isEmpty()
					&& this.items.get(1).getItem() == Items.BUCKET) {
				this.items.set(1, new ItemStack(Items.WATER_BUCKET));
			}

			itemstack.shrink(1);
		}
	}

	@SuppressWarnings("deprecation")
	protected int getBurnTime(ItemStack fuel) {
		if (fuel.isEmpty()) {
			return 0;
		} else {
			return net.minecraftforge.common.ForgeHooks.getBurnTime(fuel);
		}
	}

	@SuppressWarnings("unchecked")
	protected int getCookTime() {
		return this.level.getRecipeManager().getRecipeFor((IRecipeType<AbstractCookingRecipe>) this.recipeType, this, this.level).map(AbstractCookingRecipe::getCookingTime).orElse(200);
	}

	public static boolean isFuel(ItemStack stack) {
		if(stack.getItem() instanceof IFBurnableItems) {
			return true;
		}
			
		return false;
	}

	/**
	 * Returns true if automation can insert the given item in the given slot from
	 * the given side.
	 */
	public boolean canInsertItem(int index, ItemStack itemStackIn, @Nullable Direction direction) {
		return this.isItemValidForSlot(index, itemStackIn);
	}

	/**
	 * Returns true if automation can extract the given item in the given slot from
	 * the given side.
	 */

	@Override
	public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
		if (direction == Direction.DOWN && index == 1) {
			Item item = stack.getItem();
			if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Returns the number of slots in the inventory.
	 */
	public int getSizeInventory() {
		return this.items.size();
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

	/**
	 * Returns the stack in the given slot.
	 */
	@Override
	public ItemStack getItem(int index) {
		return this.items.get(index);
	}

	/**
	 * Removes up to a specified number of items from an inventory slot and returns
	 * them in a new stack.
	 */
	@Override
	public ItemStack removeItem(int index, int count) {
		return ItemStackHelper.removeItem(this.items, index, count);
	}

	/**
	 * Removes a stack from the given slot and returns it.
	 */
	@Override
	public ItemStack removeItemNoUpdate(int index) {
		return ItemStackHelper.takeItem(this.items, index);
	}

	/**
	 * Sets the given item stack to the specified slot in the inventory (can be
	 * crafting or armor sections).
	 */
	public void setInventorySlotContents(int index, ItemStack stack) {
		ItemStack itemstack = this.items.get(index);
		boolean flag = !stack.isEmpty() && stack.sameItem(itemstack)
				&& ItemStack.isSame(stack, itemstack);
		this.items.set(index, stack);
		if (stack.getCount() > this.getInventoryStackLimit()) {
			stack.setCount(this.getInventoryStackLimit());
		}

		if (index == 0 && !flag) {
			this.cookTimeTotal = this.getCookTime();
			this.cookTime = 0;
			this.setChanged();
		}

	}

	/**
	 * Don't rename this method to canInteractWith due to conflicts with Container
	 */
	public boolean isUsableByPlayer(PlayerEntity player) {
		if (this.level.getBlockEntity(this.worldPosition) != this) {
			return false;
		} else {
			return player.distanceToSqr((double) this.worldPosition.getX() + 0.5D, (double) this.worldPosition.getY() + 0.5D,
					(double) this.worldPosition.getZ() + 0.5D) <= 64.0D;
		}
	}

	/**
	 * Returns true if automation is allowed to insert the given stack (ignoring
	 * stack size) into the given slot. For guis use Slot.isItemValid
	 */
	@Override
	public boolean IisItemValidForSlot(int index, ItemStack stack) {
		if (index == 2) {
			return false;
		} else if (index != 1) {
			return true;
		} else {
			ItemStack itemstack = this.items.get(1);
			return isFuel(stack) || stack.getItem() == Items.BUCKET && itemstack.getItem() != Items.BUCKET;
		}
	}

	public void clear() {
		this.items.clear();
	}

	public void setRecipeUsed(@Nullable IRecipe<?> recipe) {
		if (recipe != null) {
			ResourceLocation resourcelocation = recipe.getId();
			this.recipes.addTo(resourcelocation, 1);
		}

	}

	@Nullable
	public IRecipe<?> getRecipeUsed() {
		return null;
	}

	public void onCrafting(PlayerEntity player) {
	}

	public void awardUsedRecipesAndPopExperience(PlayerEntity player) {
		List<IRecipe<?>> list = this.getRecipesToAwardAndPopExperience(player.level, player.getDeltaMovement());
		player.awardRecipes(list);
		this.recipes.clear();
	}

	public List<IRecipe<?>> getRecipesToAwardAndPopExperience(World world, Vector3d vector) {
		List<IRecipe<?>> list = Lists.newArrayList();

		for (Entry<ResourceLocation> entry : this.recipes.object2IntEntrySet()) {
			world.getRecipeManager().byKey(entry.getKey()).ifPresent((recipe) -> {
				list.add(recipe);
	            createExperience(world, vector, entry.getIntValue(), ((AbstractCookingRecipe)recipe).getExperience());

			});
		}

		return list;
	}

	private static void createExperience(World world, Vector3d pos, int craftedAmount, float experience) {
		int i = MathHelper.floor((float) craftedAmount * experience);
		float f = MathHelper.frac((float) craftedAmount * experience);
		if (f != 0.0F && Math.random() < (double) f) {
			++i;
		}

		while (i > 0) {
			int j = ExperienceOrbEntity.getExperienceValue(i);
			i -= j;
			world.addFreshEntity(new ExperienceOrbEntity(world, pos.x, pos.y, pos.z, j));
		}

	}

	public void fillStackedContents(RecipeItemHelper helper) {
		for (ItemStack itemstack : this.items) {
			helper.accountStack(itemstack);
		}

	}

	net.minecraftforge.common.util.LazyOptional<? extends net.minecraftforge.items.IItemHandler>[] handlers = net.minecraftforge.items.wrapper.SidedInvWrapper
			.create(this, Direction.UP, Direction.DOWN, Direction.NORTH);

	@Override
	public <T> net.minecraftforge.common.util.LazyOptional<T> getCapability(
			net.minecraftforge.common.capabilities.Capability<T> capability, @Nullable Direction facing) {
		if (!this.remove && facing != null
				&& capability == net.minecraftforge.items.CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
			if (facing == Direction.UP)
				return handlers[0].cast();
			else if (facing == Direction.DOWN)
				return handlers[1].cast();
			else
				return handlers[2].cast();
		}
		return super.getCapability(capability, facing);
	}

	@Override
	protected void invalidateCaps() {
		super.invalidateCaps();
		for (int x = 0; x < handlers.length; x++)
			handlers[x].invalidate();
	}
	
}