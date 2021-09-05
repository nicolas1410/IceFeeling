package dmz.faction.icefeeling.world;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IFModSoundEvents {

	
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Main.MOD_ID);
    
    
    public static final RegistryObject<SoundEvent> SANS_COEUR = registerSoundEvent("record.sans_coeur");

    
    private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
		return SOUND_EVENTS.register(soundName, () -> new SoundEvent(new ResourceLocation(Main.MOD_ID, soundName)));
	}
	
    //public static final RegistryObject<SoundEvent> MUSIC_DISC_RTC = SOUND_EVENTS.register("record.music_disc.rtc", () -> new SoundEvent(new ResourceLocation(ARKHESYS.MODID,"records/return_to_chaos")));

}
