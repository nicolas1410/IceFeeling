package dmz.faction.icefeeling.inventory.obsidian;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.inventory.base.IFFurnaceContainer;
import dmz.faction.icefeeling.tileentity.registry.IFRegistration;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IWorldPosCallable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class IFObsidianFurnaceContainer extends IFFurnaceContainer {

    public IFObsidianFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player) {
        super(IFRegistration.OBSIDIAN_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player);
    }

    public IFObsidianFurnaceContainer(int windowId, World world, BlockPos pos, PlayerInventory playerInventory, PlayerEntity player, IIntArray fields) {
        super(IFRegistration.OBSIDIAN_FURNACE_CONTAINER.get(), windowId, world, pos, playerInventory, player, fields);
    }
    
    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return isWithinUsableDistance(IWorldPosCallable.of(te.getWorld(), te.getPos()), playerEntity, IFBlocks.OBSIDIAN_FURNACE.get());
    }
}
