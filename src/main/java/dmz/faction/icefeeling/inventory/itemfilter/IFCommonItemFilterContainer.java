package dmz.faction.icefeeling.inventory.itemfilter;

import dmz.faction.icefeeling.inventory.abstracts.IFCommonItemFilterContainerBase;
import dmz.faction.icefeeling.mod.registry.IFTileRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IFCommonItemFilterContainer extends IFCommonItemFilterContainerBase {

	public IFCommonItemFilterContainer(int id, PlayerInventory playerInventory, BlockPos pos, World world, PlayerEntity player) {
		super(IFTileRegistry.COMMON_ITEM_FILTER_CONTAINER.get(), id, playerInventory, pos, world, player);

	}

}
