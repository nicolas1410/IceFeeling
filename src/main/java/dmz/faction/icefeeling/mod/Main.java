package dmz.faction.icefeeling.mod;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.blocks.IFRobusiumGlassBlock;
import dmz.faction.icefeeling.entities.registry.IFEntityRegister;
import dmz.faction.icefeeling.events.IFEnchantedFragmentDrops;
import dmz.faction.icefeeling.init.IFModFeatures;
import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.tileentity.registry.IFRegistration;
import dmz.faction.icefeeling.world.IFOreGeneration;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {

	public static final String MOD_ID = "icefeeling";

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        MinecraftForge.EVENT_BUS.register(this);

        IFItems.ITEMS.register(bus);
        IFBlocks.BLOCKS.register(bus);
        IFEntityRegister.ENTITIES.register(bus);
        IFRegistration.CONTAINERS.register(bus);
        IFRegistration.TILES.register(bus);
		MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, IFOreGeneration::generateOres);


        
    }    
	
    public static final ItemGroup ICEFEELING = new ItemGroup(12, "icefeeling") {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
           return new ItemStack(IFItems.JADE.get());
        }
     };
     
    private void setup(final FMLCommonSetupEvent event)
    {

        event.enqueueWork(() -> {
        	
        	
        	RenderTypeLookup.setRenderLayer(IFBlocks.ROBUSIUM_GLASS.get(), RenderType.getTranslucent());
        	
        	IFEntityRegister.registerGlobalEntityAttributes();
    		MinecraftForge.EVENT_BUS.addListener(IFEnchantedFragmentDrops::onLivingDrop);

        	
            try {
              Field saturation = ObfuscationReflectionHelper.findField(Food.class, "field_221471_b");
              Field effect = ObfuscationReflectionHelper.findField(Food.class, "field_221475_f");
              Field tnt_resistance = ObfuscationReflectionHelper.findField(AbstractBlock.class, "field_235689_au_");//blastResistance

              List<Pair<Supplier<EffectInstance>, Float>> effects = Lists.newArrayList();
              saturation.setAccessible(true);
              effect.setAccessible(true);
              tnt_resistance.setAccessible(true);
              
              
              saturation.setFloat(Foods.BREAD, 1.0F);
              tnt_resistance.setFloat(Blocks.OBSIDIAN, 49.0F);

              effects.add(Pair.of(() -> new EffectInstance(Effects.REGENERATION, 400, 0), 1.0F));
              effects.add(Pair.of(() -> new EffectInstance(Effects.RESISTANCE, 6000, 0), 1.0F));
              effects.add(Pair.of(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F));
              effects.add(Pair.of(() -> new EffectInstance(Effects.ABSORPTION, 2400, 0), 1.0F));
              effect.set(Foods.ENCHANTED_GOLDEN_APPLE, effects);                        
 
            } catch (IllegalAccessException e) {
              throw new RuntimeException(e);
            }
          });
        

        
    }

}
