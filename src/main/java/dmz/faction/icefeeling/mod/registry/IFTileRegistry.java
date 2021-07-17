package dmz.faction.icefeeling.mod.registry;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.inventory.ironfurnace.IFIronFurnaceContainer;
import dmz.faction.icefeeling.inventory.ironfurnace.IFIronFurnaceTileEntity;
import dmz.faction.icefeeling.inventory.itemfilter.IFCommonItemFilterContainer;
import dmz.faction.icefeeling.inventory.itemfilter.IFCommonItemFilterTile;
import dmz.faction.icefeeling.inventory.obsidianfurnace.IFObsidianFurnaceContainer;
import dmz.faction.icefeeling.inventory.obsidianfurnace.IFObsidianFurnaceTileEntity;
import dmz.faction.icefeeling.inventory.singleitem.IFSingleItemChestContainer;
import dmz.faction.icefeeling.inventory.singleitem.IFSingleItemChestTile;
import dmz.faction.icefeeling.inventory.trashcan.IFTrashCanContainer;
import dmz.faction.icefeeling.inventory.trashcan.IFTrashCanTileEntity;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import tests.multiblocks.TileEntityMultiBlock;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IFTileRegistry {

	public static final DeferredRegister<TileEntityType<?>> TILES = DeferredRegister
			.create(ForgeRegistries.TILE_ENTITIES, Main.MOD_ID);

	public static final DeferredRegister<ContainerType<?>> CONTAINERS = DeferredRegister
			.create(ForgeRegistries.CONTAINERS, Main.MOD_ID);

	/*------------------------------------------------------ TILE ENTITIES ------------------------------------------------------*/
	/* OBSIDIAN FURNACE ------------------------------------------------------*/

	public static final RegistryObject<TileEntityType<IFIronFurnaceTileEntity>> IRON_FURNACE_TILE = TILES
			.register("iron_furnace_tile", () -> TileEntityType.Builder
					.create(IFIronFurnaceTileEntity::new, IFBlocks.IRON_FURNACE.get()).build(null));
	/* OBSIDIAN FURNACE ------------------------------------------------------*/

	public static final RegistryObject<TileEntityType<IFObsidianFurnaceTileEntity>> OBSIDIAN_FURNACE_TILE = TILES
			.register("obsidian_furnace_tile", () -> TileEntityType.Builder
					.create(IFObsidianFurnaceTileEntity::new, IFBlocks.OBSIDIAN_FURNACE.get()).build(null));
	/* SINGLE ITEM CHEST ------------------------------------------------------*/

	public static final RegistryObject<TileEntityType<IFCommonItemFilterTile>> COMMON_ITEM_FILTER_TILE = TILES
			.register("common_item_filter_tile", () -> TileEntityType.Builder
					.create(IFCommonItemFilterTile::new, IFBlocks.COMMON_ITEM_FILTER.get()).build(null));
	
	/* TRASH CAN ------------------------------------------------------*/

	public static final RegistryObject<TileEntityType<IFTrashCanTileEntity>> TRASH_CAN_TILE = TILES
			.register("trash_can_tile", () -> TileEntityType.Builder
					.create(IFTrashCanTileEntity::new, IFBlocks.TRASH_CAN.get()).build(null));

	

	/*------------------------------------------------------ CONTAINERS ------------------------------------------------------*/

	/* IRON FURNACE ------------------------------------------------------*/

	public static final RegistryObject<ContainerType<IFIronFurnaceContainer>> IRON_FURNACE_CONTAINER = CONTAINERS
			.register("iron_furnace_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				World world = inv.player.getEntityWorld();
				return new IFIronFurnaceContainer(windowId, world, pos, inv, inv.player);
			}));
	
	
	/* OBSIDIAN FURNACE ------------------------------------------------------*/
	
	public static final RegistryObject<ContainerType<IFObsidianFurnaceContainer>> OBSIDIAN_FURNACE_CONTAINER = CONTAINERS
			.register("obsidian_furnace_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				World world = inv.player.getEntityWorld();
				return new IFObsidianFurnaceContainer(windowId, world, pos, inv, inv.player);
			}));

	/* SINGLE ITEM CHEST ------------------------------------------------------*/

	public static final RegistryObject<ContainerType<IFCommonItemFilterContainer>> COMMON_ITEM_FILTER_CONTAINER = CONTAINERS
			.register("common_item_filter", () -> IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				World world = inv.player.getEntityWorld();
				return new IFCommonItemFilterContainer(windowId, inv, pos, world, inv.player);
			}));
	
	/* TRASH CAN ------------------------------------------------------*/

	public static final RegistryObject<ContainerType<IFTrashCanContainer>> TRASH_CAN_CONTAINER = CONTAINERS
			.register("trash_can_container", () -> IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				World world = inv.player.getEntityWorld();
				return new IFTrashCanContainer(windowId, inv, pos, world, inv.player);
			}));

	
	/* TESTS ------------------------------------------------------*/
	
	public static final RegistryObject<TileEntityType<TileEntityMultiBlock>> MULTI_BLOCK_TILE = TILES
			.register("multi_block_tile", () -> TileEntityType.Builder
					.create(TileEntityMultiBlock::new, IFBlocks.ENCHANTED_BLOCK.get()).build(null));
	
	public static final RegistryObject<TileEntityType<IFSingleItemChestTile>> SINGLE_ITEM_CHEST_TILE_BIG = TILES
			.register("single_item_chest_tile_big", () -> TileEntityType.Builder
					.create(IFSingleItemChestTile::new, IFBlocks.SINGLE_ITEM_CHEST_BIG.get()).build(null));

	
	public static final RegistryObject<ContainerType<IFSingleItemChestContainer>> SINGLE_ITEM_CHEST_CONTAINER_BIG = CONTAINERS
			.register("single_item_chest_container_big", () -> IForgeContainerType.create((windowId, inv, data) -> {
				BlockPos pos = data.readBlockPos();
				World world = inv.player.getEntityWorld();
				return new IFSingleItemChestContainer(windowId, inv, pos, world, inv.player);
			}));
	

	
}