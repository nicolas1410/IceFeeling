package dmz.faction.icefeeling.world;

import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ChorusPlantFeature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DecoratedFeature;
import net.minecraft.world.gen.feature.DecoratedFeatureConfig;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class IFBiomeChanges {

	@SubscribeEvent()
	public static void removeChorusFromVanillaBiomes(final BiomeLoadingEvent event) {
		if (event.getCategory() == Category.THEEND) 
		{
			if (event.getName() == null || !event.getName().getNamespace().equals("minecraft")) return;

			String path = event.getName().getPath();
			if (path.equals("end_highlands") || path.equals("end_midlands") || path.equals("small_end_islands")) {
				event.getGeneration().getFeatures(Decoration.VEGETAL_DECORATION).removeIf((supplier) -> {
					ConfiguredFeature<?, ?> feature = supplier.get();

					// original feature
					while (feature.getFeatures() instanceof DecoratedFeature) {
						feature = ((DecoratedFeatureConfig) feature.config()).feature.get();
					}

					if (feature.feature instanceof ChorusPlantFeature) {
						return true;
					}
					return false;
					
					
					
				});
			}
			
			
		}
		
		
	}
	
	
}
