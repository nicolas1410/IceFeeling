package dmz.faction.icefeeling.inventory.obsidianfurnace;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceContainer;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IFObsidianFurnaceContainer extends IFAbstractFurnaceContainer {

    public IFObsidianFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(IFTileRegistry.OBSIDIAN_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public IFObsidianFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(IFTileRegistry.OBSIDIAN_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, inventory, fields);
    }

	@Override
	public boolean stillValid(PlayerEntity player) {
		return IFAbstractFurnaceContainer.inventory.stillValid(player);
	}
}

