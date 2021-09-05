package dmz.faction.icefeeling.inventory.itemfilter;

import dmz.faction.icefeeling.inventory.abstracts.IFCommonItemFilterTileBase;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public class IFCommonItemFilterTile extends IFCommonItemFilterTileBase {

	private static final int[] SLOT = new int[] { 0 };
	
	public IFCommonItemFilterTile() {
		super(IFTileRegistry.COMMON_ITEM_FILTER_TILE.get());
	}
	
	@Override
	public String IgetName() {
		return "container." + Main.MOD_ID + ".common_item_filter";
	}

	@Override
	public Container IcreateMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new IFCommonItemFilterContainer(id, playerInventory, pos, level, playerEntity);
	}

	
	/**Had to make this method because it's asked by 
	 * the implementation of ISidedInventory
	 * to be able to remove the insertion/extraction 
	 * of other items than items valid for the slot via hoppers
	 * **/
	@Override
	public int[] getSlotsForFace(Direction side) {
		return SLOT;
	}

	@Override
	public ItemStack getItem(int p_70301_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeItem(int p_70298_1_, int p_70298_2_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemStack removeItemNoUpdate(int p_70304_1_) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setItem(int p_70299_1_, ItemStack p_70299_2_) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearContent() {
		// TODO Auto-generated method stub
		
	}


}
