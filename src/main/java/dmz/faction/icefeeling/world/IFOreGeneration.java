package dmz.faction.icefeeling.world;

import dmz.faction.icefeeling.blocks.IFBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.template.RuleTest;
import net.minecraft.world.gen.placement.DepthAverageConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;

public class IFOreGeneration {

	
	public static void generateOres(final BiomeLoadingEvent event) {
		if (!(event.getCategory().equals(Biome.Category.NETHER) || event.getCategory().equals(Biome.Category.THEEND))) {
			
			//Generate from Y = 90 / VeinSize of 3 max
			generateOreTopSolid(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					IFBlocks.OPAL_ORE.get().getDefaultState(), 3, 90, 0, 256, 40);
			
			//
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					IFBlocks.JADE_ORE.get().getDefaultState(), 7, 24, 8);
			
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					IFBlocks.TITANITE_ORE.get().getDefaultState(), 4, 16, 10);
			
			// Generate like Lapis Lazuli, but 5 of veinSize instead of 7
			generateOreDepthAverage(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					IFBlocks.MYTHRIL_ORE.get().getDefaultState(), 6, 16, 10);
			
			/*generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					IFBlocks.SOLARIUM_ORE.get().getDefaultState(), 3, 90, 0, 256, 40);*/
			
			generateOre(event.getGeneration(), OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD,
					IFBlocks.ROBUSIUM_ORE.get().getDefaultState(), 8, 5, 10);
			
		      //y = random.nextInt(config.maximum - config.topOffset) + config.bottomOffset;

					//( maximum - topOffset ) + bottomOffset;
			
			
			  // public static final ConfiguredFeature<?, ?> ORE_MAGMA = register("ore_magma", Feature.ORE.withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, Features.States.MAGMA_BLOCK, 33)).withPlacement(Placement.MAGMA.configure(NoPlacementConfig.INSTANCE)).square().func_242731_b(4));

		}
	}
	

	private static void generateOreTopSolid(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int bottomOffset, int topOffset, int maximum, int amount) {
		settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
						.withPlacement(Placement.RANGE.configure(new TopSolidRangeConfig(bottomOffset, topOffset, maximum)))
						.square().func_242731_b(amount));
		 /*	 
	     * 	Calculation of the RangePlacement
	     *	int k = random.nextInt(config.maximum - config.topOffset) + config.bottomOffset;
	     */
		

	}

	private static void generateOre(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int range, int amount) {
		settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
						.square().range(range).func_242731_b(amount));
		

	    /*	 
	     * 	Calculation of the DepthAveragePlacement
	     * 	int i = config.baseline;
	     *	int j = config.spread;
	     * 	int k = pos.getX();
	     * 	int l = pos.getZ();
	     * 	int i1 = random.nextInt(j) + random.nextInt(j) - j + i;
	     * 	return Stream.of(new BlockPos(k, i1, l));
	     */
	}  

	private static void generateOreDepthAverage(BiomeGenerationSettingsBuilder settings, RuleTest fillerType, BlockState state, int veinSize, int baseline, int spread) {
		settings.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
				Feature.ORE.withConfiguration(new OreFeatureConfig(fillerType, state, veinSize))
						.withPlacement(Placement.DEPTH_AVERAGE.configure(new DepthAverageConfig(baseline, spread))
						.square()));
		
	}
}