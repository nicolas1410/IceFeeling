package dmz.faction.icefeeling.inventory.ironfurnace;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceTileEntity;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;

public class IFIronFurnaceTileEntity extends IFAbstractFurnaceTileEntity {
	
	private static final int[] INPUT = new int[] { 0 };
	private static final int[] OUTPUT = new int[] { 2 };
	
	public IFIronFurnaceTileEntity() {
		super(IFTileRegistry.IRON_FURNACE_TILE.get());
	}

	@Override
	public String IgetName() {
		return "container.icefeeling.iron_furnace";
	}

	@Override
	public Container IcreateMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new IFIronFurnaceContainer(id, world, pos, playerInventory, playerEntity, this.furnaceData);
	}

	@Override
	public boolean IcanExtractItem(int index, ItemStack stack, Direction direction) {
		if (direction == direction.DOWN) {
			return true;
		}
		return false;
	}

	@Override
	public int[] getSlotsForFace(Direction side) {
		if(side == side.UP)
			return INPUT;
		if(side == side.DOWN)
			return OUTPUT;
		return null;
	}
	
	@Override
	protected int getCookTime() {
		return 50;
	}

}
