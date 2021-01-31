package dmz.faction.icefeeling.world;

import dmz.faction.icefeeling.blocks.IFBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class OreGeneration {

	public static ConfiguredFeature<?, ?> JADE_ORE_CONFIG;
	public static ConfiguredFeature<?, ?> OPAL_ORE_CONFIG;
	
	@SubscribeEvent
	public static void setup(FMLCommonSetupEvent event) {
	JADE_ORE_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "jade_ore",
	Feature.ORE.withConfiguration(
	                new OreFeatureConfig(
	                    OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
	                    IFBlocks.JADE_ORE.get().getDefaultState(), 16)
	            ).range(64).square().func_242731_b(100)
	        );
	OPAL_ORE_CONFIG = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, "jade_ore",
			Feature.ORE.withConfiguration(
			                new OreFeatureConfig(
			                    OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
			                    IFBlocks.OPAL_ORE.get().getDefaultState(), 16)
			            ).range(64).square().func_242731_b(100)
			        );
	    }

	@SubscribeEvent
	public void onBiomeLoading(final BiomeLoadingEvent biome) {
		if(biome.getCategory() == Biome.Category.PLAINS) return;

		biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
        .add(() -> OreGeneration.JADE_ORE_CONFIG);

		biome.getGeneration().getFeatures(GenerationStage.Decoration.UNDERGROUND_ORES)
        .add(() -> OreGeneration.OPAL_ORE_CONFIG);

	}
}

	
		/*//Ores
		public WorldGenerator Opal_Ore;
		public WorldGenerator Titanite_Ore;
		public WorldGenerator Mythril_Ore;
		public WorldGenerator Jade_Ore;

		
		public IFWorldGen () {
			
			this.Opal_Ore = new IFWorldGenNetherMinable(IFBlocks.Opal_Ore.getDefaultState(), 8);
			this.Titanite_Ore = new IFWorldGenMinable(IFBlocks.Titanite_Ore.getDefaultState(), 8);
			this.Mythril_Ore = new IFWorldGenMinable(IFBlocks.Mythril_Ore.getDefaultState(), 8);
			this.Jade_Ore = new IFWorldGenMinable(IFBlocks.Jade_Ore.getDefaultState(), 8);
	
			
		
		}
		
		public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
			public void runGen(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight) {

	


		
	}

			{
			
			case 0: //OverWorld
				this.runGen(this.Titanite_Ore, world, random, chunkX, chunkZ, 2, 0, 16);
				this.runGen(this.Mythril_Ore, world, random, chunkX, chunkZ, 2, 0, 16);
				this.runGen(this.Jade_Ore, world, random, chunkX, chunkZ, 10, 0, 64);
		
				
				break;
				
				
			case -1: //Nether
				this.runGen(this.Opal_Ore, world, random, chunkX, chunkZ, 1, 100, 128);
		
				
				break;
			
			case 1: //End
				break;
				
			}
			
		}
		
		public void runGen(WorldGenerator generator, World world, Random random, int chunkX, int chunkZ, int chanceToSpawn, int minHeight, int maxHeight) {
			
			if(minHeight < 0 ||maxHeight > 256 || minHeight > maxHeight)
				throw new IllegalArgumentException("Bad Height for WorldGen");
			
			int heightDiff = maxHeight - minHeight + 1;
			for (int i =0; i < chanceToSpawn; i++) {
				int x = chunkX *16 + random.nextInt(16);
				int y = minHeight + random.nextInt(heightDiff);
				int z = chunkZ * 16 + random.nextInt(16);
				generator.generate(world, random, new BlockPos(x, y, z));
			}
		}	
		*/

