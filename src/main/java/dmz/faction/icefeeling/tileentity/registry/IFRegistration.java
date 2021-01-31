package dmz.faction.icefeeling.tileentity.registry;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.inventory.obsidian.IFObsidianFurnaceContainer;
import dmz.faction.icefeeling.mod.Main;
import dmz.faction.icefeeling.tileentity.obsidian.IFObsidianFurnaceTile;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IFRegistration {

	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);
	
	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

	/*------------------------------------------------------ TILE ENTITIES ------------------------------------------------------*/

	public static final RegistryObject<TileEntityType<IFObsidianFurnaceTile>> OBSIDIAN_FURNACE_TILE = TILES
			.register("obsidian_furnace_tile", () -> TileEntityType.Builder
					.create(IFObsidianFurnaceTile::new, IFBlocks.OBSIDIAN_FURNACE.get()).build(null));
	
	
	public static final RegistryObject<ContainerType<IFObsidianFurnaceContainer>> OBSIDIAN_FURNACE_CONTAINER = CONTAINERS.register("obsidian_furnace_container", 
		() -> IForgeContainerType.create((windowId, inv, data) -> {
        BlockPos pos = data.readBlockPos();
        World world = inv.player.getEntityWorld();
        return new IFObsidianFurnaceContainer(windowId, world, pos, inv, inv.player); }));

}