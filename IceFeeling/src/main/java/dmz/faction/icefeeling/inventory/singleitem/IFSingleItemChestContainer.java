package dmz.faction.icefeeling.inventory.singleitem;

import dmz.faction.icefeeling.inventory.singleitem.abstracts.IFSingleItemChestContainerBase;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IFSingleItemChestContainer extends IFSingleItemChestContainerBase {

	public IFSingleItemChestContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, PlayerEntity player) {
		super(IFTileRegistry.SINGLE_ITEM_CHEST_CONTAINER_BIG.get(), id, playerInventory, pos, world, player);

	}

}
