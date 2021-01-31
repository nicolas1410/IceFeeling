package dmz.faction.icefeeling.tileentity.obsidian;

import dmz.faction.icefeeling.inventory.obsidian.IFObsidianFurnaceContainer;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.tileentity.base.IFFurnaceTileEntity;
import dmz.faction.icefeeling.tileentity.registry.IFRegistration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Container;

public class IFObsidianFurnaceTile extends IFFurnaceTileEntity {
	
	public IFObsidianFurnaceTile() {
		
		super(IFRegistration.OBSIDIAN_FURNACE_TILE.get());
	}

	@Override
	public String IgetName() {
		return "container." + Main.MOD_ID + ".obsidian_furnace";
	}

	@Override
	public Container IcreateMenu(int id, PlayerInventory playerInventory, PlayerEntity playerEntity) {
		return new IFObsidianFurnaceContainer(id, world, pos, playerInventory, playerEntity);
	}

}
