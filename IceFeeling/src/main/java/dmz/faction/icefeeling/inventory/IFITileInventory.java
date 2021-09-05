package dmz.faction.icefeeling.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public interface IFITileInventory {
	
	public boolean IcanExtractItem(int index, ItemStack stack, Direction direction);

	public String IgetName();

	public boolean IisItemValidForSlot(int index, ItemStack stack);

	public Container IcreateMenu(int i, PlayerInventory playerInventory, PlayerEntity playerEntity);

}