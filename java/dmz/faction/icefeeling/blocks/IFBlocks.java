package dmz.faction.icefeeling.blocks;

import java.util.function.Supplier;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IFBlocks {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Main.MOD_ID); 

	
	/* ----------------------------------------------- ORES ----------------------------------------------- */
	
	/**/
	public static final RegistryObject<Block> JADE_ORE = registerBlockWithDefaultItem("jade_ore", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3, 15)));
	
	/*BEST ARMURE*/
	public static final RegistryObject<Block> OPAL_ORE = registerBlockWithDefaultItem("opal_ore", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(2, 15)));
	
	public static final RegistryObject<Block> OPAL_ORE_NETHER = registerBlockWithDefaultItem("opal_ore_nether", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(2, 15)));
	
	/*BEST EPEES*/
	public static final RegistryObject<Block> FIRE_OPAL_ORE = registerBlockWithDefaultItem("fire_opal_ore", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(1, 15)));
	
	/*ARMURE AVEC*/
	public static final RegistryObject<Block> MYTHRIL_ORE = registerBlockWithDefaultItem("mythril_ore", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(3, 15)));
	
	/*ARMURE LA PLUS DURABLE*/
	public static final RegistryObject<Block> TITANITE_ORE = registerBlockWithDefaultItem("titanite_ore", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(15, 30)));
	
	/*FUEL POUR FURNACE EN TITANITE*/
	public static final RegistryObject<Block> SOLARIUM_ORE = registerBlockWithDefaultItem("solarium_ore", () 
			-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(30, 45)));
	
	/*OBSI++*/
	public static final RegistryObject<Block> ROBUSIUM_ORE = registerBlockWithDefaultItem("robusium_ore", () 
			-> new IFRobusiumBlock(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().harvestLevel(4).hardnessAndResistance(65, 4000)));
	
	public static final RegistryObject<Block> ROBUSIUM_BLOCK = registerBlockWithDefaultItem("robusium_block", () 
    		-> new Block(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.BLACK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(120, 8000)));
	
	
    public static final RegistryObject<Block> COMPRESSED_COBBLE = registerBlockWithDefaultItem("compressed_cobble", () 
    		-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4, 12)));

	
    //50.0F, 1200.0F = obsi
	
	/* ----------------------------------------------- 899889 ----------------------------------------------- */

	
	
	/* ----------------------------------------------- REGISTERING ----------------------------------------------- */
	
    
  
	
	public static <T extends Block> RegistryObject<T> registerBlockWithDefaultItem(String name, Supplier<? extends T> blockSupplier)
	{
		RegistryObject<T> block = BLOCKS.register(name, blockSupplier);
		IFItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().group(Main.ICEFEELING)));
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

