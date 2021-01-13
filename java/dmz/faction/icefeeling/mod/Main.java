package dmz.faction.icefeeling.mod;

import java.lang.reflect.Field;
import java.util.List;
import java.util.function.Supplier;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.collect.Lists;
import com.mojang.datafixers.util.Pair;

import dmz.faction.icefeeling.blocks.IFBlocks;
import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.world.OreGeneration;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Food;
import net.minecraft.item.Foods;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistryModifiable;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(Main.MOD_ID)
public class Main {

	public static final String MOD_ID = "icefeeling";
	private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(OreGeneration.class);


        //MinecraftForge.EVENT_BUS.register(OreGeneration.JADE_ORE_CONFIG);
        //Register Items
        IFItems.ITEMS.register(bus);
        IFBlocks.BLOCKS.register(bus);
    	
    	
    	
    	
    	
    	
    	
        // Register the setup method for modloading
        bus.addListener(this::setup);
        // Register the enqueueIMC method for modloading
        bus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        //bus.addListener(this::processIMC);
        // Register the doClientStuff method for modloading
        bus.addListener(this::doClientStuff);
        // Register ourselves for server and other game events we are interested in
    }    
	
    public static final ItemGroup ICEFEELING = new ItemGroup(12, "icefeeling") {
        @OnlyIn(Dist.CLIENT)
        public ItemStack createIcon() {
           return new ItemStack(IFItems.JADE.get());
        }
     };
     
    private void setup(final FMLCommonSetupEvent event)
    {
        // some preinit code
        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        
        event.enqueueWork(() -> {
            try {
              Field saturation = ObfuscationReflectionHelper.findField(Food.class, "field_221471_b");
              Field effect = ObfuscationReflectionHelper.findField(Food.class, "field_221475_f");
              List<Pair<Supplier<EffectInstance>, Float>> effects = Lists.newArrayList();
              saturation.setAccessible(true);
              effect.setAccessible(true);

              saturation.setFloat(Foods.BREAD, 1.0F);

              effects.add(Pair.of(() -> new EffectInstance(Effects.REGENERATION, 400, 0), 1.0F));
              effects.add(Pair.of(() -> new EffectInstance(Effects.RESISTANCE, 6000, 0), 1.0F));
              effects.add(Pair.of(() -> new EffectInstance(Effects.FIRE_RESISTANCE, 6000, 0), 1.0F));
              effects.add(Pair.of(() -> new EffectInstance(Effects.ABSORPTION, 2400, 0), 1.0F));
              effect.set(Foods.ENCHANTED_GOLDEN_APPLE, effects);
              
              
              Field recipe = ObfuscationReflectionHelper.findField(IForgeRegistryModifiable.class, "");
              
              
 
            } catch (IllegalAccessException e) {
              throw new RuntimeException(e);
            }
          });
        

        
    }

    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
        LOGGER.info("Got game settings {}", event.getMinecraftSupplier().get().gameSettings);
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    /*private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.getMessageSupplier().get()).
                collect(Collectors.toList()));
    }*/
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
            LOGGER.info("HELLO from Register Block");
        }
    }
}
