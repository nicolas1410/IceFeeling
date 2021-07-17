package dmz.faction.icefeeling.inventory.singleitem;

import java.util.stream.IntStream;

import dmz.faction.icefeeling.inventory.abstracts.IFCommonItemFilterTileBase;
import dmz.faction.icefeeling.inventory.singleitem.abstracts.IFSingleItemChestTileBase;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.util.Direction;

public class IFSingleItemChestTile extends IFSingleItemChestTileBase {

	private static final int [] SLOTS = IntStream.rangeClosed(0, 216).toArray(); // From 0 to 216

	
	public IFSingleItemChestTile() {
		super(IFTileRegistry.SINGLE_ITEM_CHEST_TILE_BIG.get());
	}
	
	@Override
	public String IgetName() {
		return "container." + Main.MOD_ID + ".single_item_chest_big";
	}

	@Override
	public Container IcreateMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new IFSingleItemChestContainer(id, playerInventory, pos, world, playerEntity);
	}

	
	/**Had to make this method because it's asked by 
	 * the implementation of ISidedInventory
	 * to be able to remove the insertion/extraction 
	 * of other items than items valid for the slot via hoppers
	 * **/
	@Override
	public int[] getSlotsForFace(Direction side) {
		return SLOTS;
	}


}
