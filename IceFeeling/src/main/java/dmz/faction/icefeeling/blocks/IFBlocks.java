package dmz.faction.icefeeling.blocks;

import java.util.function.Supplier;

import dmz.faction.icefeeling.blocks.blockgui.IFIronFurnaceBlock;
import dmz.faction.icefeeling.blocks.blockgui.IFObsidianFurnaceBlock;
import dmz.faction.icefeeling.blocks.blockgui.IFSingleItemChestBlock;
import dmz.faction.icefeeling.blocks.blockgui.IFTrashCanBlock;
import dmz.faction.icefeeling.blocks.blockgui.abstracts.IFCommonItemFilterBlock;
import dmz.faction.icefeeling.blocks.flowers.IFMagicFlower;
import dmz.faction.icefeeling.blocks.flowers.IFPoisonFlower;
import dmz.faction.icefeeling.blocks.flowers.IFSlownessFlower;
import dmz.faction.icefeeling.blocks.robusium.IFRobusiumBlock;
import dmz.faction.icefeeling.blocks.robusium.IFRobusiumGlassBlock;
import dmz.faction.icefeeling.blocks.tnts.IFChargedTNTBock;
import dmz.faction.icefeeling.blocks.tnts.IFChunkTNTBlock;
import dmz.faction.icefeeling.blocks.tnts.IFIceTNTBlock;
import dmz.faction.icefeeling.blocks.tnts.IFLavaTNTBlock;
import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.potion.Effects;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IFBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID); 

	
	/* ----------------------------------------------- ORES ----------------------------------------------- */
	
	/**/
	public static final RegistryObject<Block> JADE_ORE = registerBlockWithDefaultItem("jade_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).strength(3, 9)));
	
	/*BEST ARMURE*/
	public static final RegistryObject<Block> OPAL_ORE = registerBlockWithDefaultItem("opal_ore", () 
			-> new Block(Block.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(2, 9)));
	
	public static final RegistryObject<Block> OPAL_ORE_NETHER = registerBlockWithDefaultItem("opal_ore_nether", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(2, 9)));
	
	/*BEST EPEES*/
	public static final RegistryObject<Block> FIRE_OPAL_ORE = registerBlockWithDefaultItem("fire_opal_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(1, 9)));
	
	/*ARMURE AVEC*/
	public static final RegistryObject<Block> MYTHRIL_ORE = registerBlockWithDefaultItem("mythril_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(3, 9)));
	
	/*ARMURE LA PLUS DURABLE*/
	public static final RegistryObject<Block> TITANITE_ORE = registerBlockWithDefaultItem("titanite_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(15, 9)));
	
	/*FUEL POUR FURNACE EN TITANITE*/
	public static final RegistryObject<Block> SOLARIUM_ORE = registerBlockWithDefaultItem("solarium_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(30, 9)));
	
	/*OBSI++*/
	public static final RegistryObject<Block> ROBUSIUM_ORE = registerBlockWithDefaultItem("robusium_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(4).strength(65, 9)));
	
	// HAMMER
	public static final RegistryObject<Block> STEEL_ORE = registerBlockWithDefaultItem("steel_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(4).strength(3, 9)));
	
	
	public static final RegistryObject<Block> XP_ORE = registerBlockWithDefaultItem("xp_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(4).strength(3, 9)));
	
	
	public static final RegistryObject<Block> ENDER_ORE = registerBlockWithDefaultItem("ender_ore", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().harvestLevel(4).strength(3, 9)));
	
	
	
	/* ----------------------------------------------- BLOCKS ----------------------------------------------- */
	
	public static final RegistryObject<IFRobusiumBlock> ROBUSIUM_BLOCK = registerBlockWithDefaultItem("robusium_block", () 
			-> new IFRobusiumBlock(AbstractBlock.Properties.of(Material.STONE, MaterialColor.COLOR_BLACK).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(120, 40)));
	
	public static final RegistryObject<IFRobusiumGlassBlock> ROBUSIUM_GLASS = registerBlockWithDefaultItem("robusium_glass", () 
			-> new IFRobusiumGlassBlock(AbstractBlock.Properties.of(Material.GLASS).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(120, 9)
					.noOcclusion()
					.isValidSpawn(IFRobusiumGlassBlock::neverAllowSpawn)
					.isSuffocating(IFRobusiumGlassBlock::isntSolid)
					.isViewBlocking(IFRobusiumGlassBlock::isntSolid)));  

    public static final RegistryObject<Block> COMPRESSED_COBBLE = registerBlockWithDefaultItem("compressed_cobble", () 
    		-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(4, 12)));
    
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlockWithDefaultItem("steel_block", () 
			-> new Block(AbstractBlock.Properties.of(Material.STONE).harvestTool(ToolType.PICKAXE).requiresCorrectToolForDrops().strength(3, 9)));	
    
    
	/* ----------------------------------------------- TNT ----------------------------------------------- */

    
    public static final RegistryObject<Block> LAVA_TNT = registerBlockWithDefaultItem("lava_tnt", () 
    		-> new IFLavaTNTBlock(AbstractBlock.Properties.of(Material.EXPLOSIVE).sound(SoundType.SCAFFOLDING).lightLevel((state) -> {
  		      return 5;})));
    
    public static final RegistryObject<Block> CHARGED_TNT = registerBlockWithDefaultItem("charged_tnt", () 
    		-> new IFChargedTNTBock(AbstractBlock.Properties.of(Material.EXPLOSIVE).sound(SoundType.SCAFFOLDING).lightLevel((state) -> {
  		      return 5;})));
    
    public static final RegistryObject<Block> ICE_TNT = registerBlockWithDefaultItem("ice_tnt", () 
    		-> new IFIceTNTBlock(AbstractBlock.Properties.of(Material.EXPLOSIVE).sound(SoundType.BAMBOO)));
    
    public static final RegistryObject<Block> CHUNK_TNT = registerBlockWithDefaultItem("chunk_tnt", () 
    		-> new IFChunkTNTBlock(AbstractBlock.Properties.of(Material.EXPLOSIVE).sound(SoundType.BAMBOO)));
    
    
    
	/* ----------------------------------------------- FLOWERS ----------------------------------------------- */

    
    public static final RegistryObject<IFPoisonFlower> POISON_FLOWER = registerBlockWithDefaultItem("poison_flower", () 
    		-> new IFPoisonFlower(Effects.POISON, 10, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    
    public static final RegistryObject<IFMagicFlower> MAGIC_FLOWER = registerBlockWithDefaultItem("magic_flower", () 
    		-> new IFMagicFlower(Effects.HARM, 1, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    
    public static final RegistryObject<IFSlownessFlower> SLOWNESS_FLOWER = registerBlockWithDefaultItem("slowness_flower", () 
    		-> new IFSlownessFlower(Effects.MOVEMENT_SLOWDOWN, 10, AbstractBlock.Properties.of(Material.PLANT).noCollission().instabreak().sound(SoundType.CROP)));
    
    
	/* ----------------------------------------------- TEST AREA 51 ZONE ----------------------------------------------- */

    
    public static final RegistryObject<IFIronFurnaceBlock> IRON_FURNACE = registerBlockWithDefaultItem("iron_furnace", () 
    		-> new IFIronFurnaceBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.ANVIL).strength(3, 9)));
    
    public static final RegistryObject<IFObsidianFurnaceBlock> OBSIDIAN_FURNACE = registerBlockWithDefaultItem("obsidian_furnace", () 
    		-> new IFObsidianFurnaceBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.ANVIL).strength(3, 9)));
    
    
    public static final RegistryObject<IFCommonItemFilterBlock> COMMON_ITEM_FILTER = registerBlockWithDefaultItem("common_item_filter", () 
    		-> new IFCommonItemFilterBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.ANVIL)));
    
    
    public static final RegistryObject<IFSingleItemChestBlock> SINGLE_ITEM_CHEST_BIG = registerBlockWithDefaultItem("single_item_chest_big", () 
    		-> new IFSingleItemChestBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.ANVIL)));
 
    
    public static final RegistryObject<IFTrashCanBlock> TRASH_CAN = registerBlockWithDefaultItem("trash_can", () 
    		-> new IFTrashCanBlock(AbstractBlock.Properties.of(Material.STONE).sound(SoundType.SLIME_BLOCK)));
    
    
    
	/* ----------------------------------------------- REGISTERING ----------------------------------------------- */

	public static <T extends Block> RegistryObject<T> registerBlockWithDefaultItem(String name, Supplier<? extends T> blockSupplier)
	{
		RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
		IFItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(Main.ICEFEELING)));
		return block;
	}

	/*public static Block 	Opal_Ore;
    public static Block		Mythril_Ore;
    public static Block		Titanite_Ore;
    public static Block		Jade_Ore;
    public static Block		Solarium_Ore;//Fuel TitaFurnace
    public static Block		Robusium_Ore;//Obsi+
    
    public static Block		Ruche;

    public static Block		biblio_spruce, biblio_birch, biblio_jungle, biblio_acacia, biblio_dark_oak, biblio_oak;
    
    
    //Tests
    public static Block		Nuke;
    public static LuckyBlock LuckyLuckBlock;
    //DécoBlock
    public static Block 	Iron_Stairs, Iron_Slab, Iron_Fence;	
    public static Block 	Wool_Stairs, Wool_Slab, Wood_Fence;
    public static Block		Diamond_Stairs, Diamond_Slab, Diamond_Fence;
    public static Block	 	Lapis_Stairs, Lapis_Slab, Lapis_Fence;
    public static Block		Redstone_Stairs, Redstone_Slab, Redstone_Fence;
    public static Block		Gold_Stairs, Gold_Slab, Gold_Fence;
    public static Block		Coal_Stairs, Coal_Slab, Coal_Fence;
    public static Block		Emerald_Stairs, Emerald_Slab, Emerald_Fence;
    public static Block		Wheat_Stairs, Wheat_Slab, Wheat_Fence;
    
    
     Blocs Multiblock
    
    public static Block		Enchanted_Block;
    
    
    //Block de minerais
    public static Block 	Opal_Block;
    public static Block 	Mythril_Block;
    public static Block 	Titanite_Block;
    public static Block 	Jade_Block;
    public static Block 	Solarium_Block;
    public static Block		Robus;

    //public static Block Compressed_Obsidian;
    public static Block 	Obsidian_Fake;

    public static Block 	Obsidian_Anvil;
    public static Block 	Titanite_Furnace;
    public static Block 	Titanite_Furnace_Lit;
    //public static Block 	Test1, Test2, Test3;*/
	
}

