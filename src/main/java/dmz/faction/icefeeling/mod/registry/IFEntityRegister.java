package dmz.faction.icefeeling.mod.registry;

import dmz.faction.icefeeling.entities.chargedtnt.IFChargedTNTEntity;
import dmz.faction.icefeeling.entities.chargedtnt.IFChargedTNTRenderer;
import dmz.faction.icefeeling.entities.icetnt.IFIceTNTEntity;
import dmz.faction.icefeeling.entities.icetnt.IFIceTNTRenderer;
import dmz.faction.icefeeling.entities.invasion.zombies.ChargedZombie;
import dmz.faction.icefeeling.entities.invasion.zombies.ChargedZombieEntityRenderer;
import dmz.faction.icefeeling.entities.lavatnt.IFLavaTNTEntity;
import dmz.faction.icefeeling.entities.lavatnt.IFLavaTNTRenderer;
import dmz.faction.icefeeling.entities.meteorhead.IFMeteorHeadEntity;
import dmz.faction.icefeeling.entities.meteorhead.IFMeteorHeadRenderer;
import dmz.faction.icefeeling.entities.mushpang.IFMushpangEntity;
import dmz.faction.icefeeling.entities.mushpang.IFMushpangEntityRenderer;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class IFEntityRegister {

	
	public static final DeferredRegister<EntityType<?>> ENTITIES = 
			DeferredRegister.create(ForgeRegistries.ENTITIES, Main.MOD_ID);

	/*------------------------------------------------------ CHARGED TNT ------------------------------------------------------*/
    
	public static final RegistryObject<EntityType<IFChargedTNTEntity>> CHARGED_TNT_ENTITY = ENTITIES.register("charged_tnt_entity", 
 			() -> EntityType.Builder.<IFChargedTNTEntity>create(IFChargedTNTEntity::new, EntityClassification.MISC)
 			.size(0.5F, 0.5F)
            .setTrackingRange(4)
            .setUpdateInterval(20)
            .setShouldReceiveVelocityUpdates(true)
            .build("charged_tnt_entity"));
	
	
	
	/*------------------------------------------------------ ICE TNT ------------------------------------------------------*/
	
	public static final RegistryObject<EntityType<IFIceTNTEntity>> ICE_TNT_ENTITY = ENTITIES.register("ice_tnt_entity", 
 			() -> EntityType.Builder.<IFIceTNTEntity>create(IFIceTNTEntity::new, EntityClassification.MISC)
 			.size(0.5F, 0.5F)
            .setTrackingRange(4)
            .setUpdateInterval(20)
            .setShouldReceiveVelocityUpdates(true)
            .build("ice_tnt_entity"));
	
	
	
	/*------------------------------------------------------ ICE TNT ------------------------------------------------------*/
	
	public static final RegistryObject<EntityType<IFLavaTNTEntity>> LAVA_TNT_ENTITY = ENTITIES.register("lava_tnt_entity", 
 			() -> EntityType.Builder.<IFLavaTNTEntity>create(IFLavaTNTEntity::new, EntityClassification.MISC)
 			.size(0.5F, 0.5F)
            .setTrackingRange(4)
            .setUpdateInterval(20)
            .setShouldReceiveVelocityUpdates(true)
            .build("lava_tnt_entity"));
	
	
	/*------------------------------------------------------ *************** ------------------------------------------------------*/
	
	/*-------------------------------------------------------  MOB ENTITIY  -------------------------------------------------------*/
	
	/*------------------------------------------------------ *************** ------------------------------------------------------*/
	
	

	/*------------------------------------------------------ MUSHPANG ------------------------------------------------------*/
	
	public static final RegistryObject<EntityType<IFMushpangEntity>> MUSHPANG = ENTITIES.register("mushpang_entity", 
 			() -> EntityType.Builder.<IFMushpangEntity>create(IFMushpangEntity::new, EntityClassification.CREATURE)
 			.size(1.5F, 1.5F)
 			.build("mushpang_entity"));
	
	
	
	/*------------------------------------------------------ CHARGED_ZOMBIE ------------------------------------------------------*/
	
	public static final RegistryObject<EntityType<ChargedZombie>> CHARGED_ZOMBIE = ENTITIES.register("charged_zombie_entity", 
 			() -> EntityType.Builder.<ChargedZombie>create(ChargedZombie::new, EntityClassification.MONSTER)
 			.size(0.6F, 1.95F)
 			.trackingRange(16)
 			.build("charged_zombie_entity"));
	
	
	
	/*------------------------------------------------------ CHARGED_ZOMBIE ------------------------------------------------------*/
	
	public static final RegistryObject<EntityType<IFMeteorHeadEntity>> METEOR_HEAD = ENTITIES.register("meteor_head_entity", 
 			() -> EntityType.Builder.<IFMeteorHeadEntity>create(IFMeteorHeadEntity::new, EntityClassification.MONSTER)
 			.size(0.65F, 0.65F)
 			.trackingRange(16)
 			.immuneToFire()
 			.build("meteor_head_entity"));
	
	
	
	
	
	
	
	
	
	
	/*------------------------------------------------------ *************** ------------------------------------------------------*/
	
	/*------------------------------------------------------ register Method ------------------------------------------------------*/
	
	/*------------------------------------------------------ *************** ------------------------------------------------------*/


	public static void registerGlobalEntityAttributes()
	{
		
		GlobalEntityTypeAttributes.put(IFEntityRegister.MUSHPANG.get(), IFMushpangEntity.registerAttributes().create());
		GlobalEntityTypeAttributes.put(IFEntityRegister.CHARGED_ZOMBIE.get(), ChargedZombie.registerAttributes().create());
		GlobalEntityTypeAttributes.put(IFEntityRegister.METEOR_HEAD.get(), IFMeteorHeadEntity.registerAttributes().create());

	}

	
    public static void registerRenderers()
	{
    	
		// Entity renderers
		RenderingRegistry.registerEntityRenderingHandler(LAVA_TNT_ENTITY.get(), IFLavaTNTRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(CHARGED_TNT_ENTITY.get(), IFChargedTNTRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(ICE_TNT_ENTITY.get(), IFIceTNTRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(MUSHPANG.get(), IFMushpangEntityRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(CHARGED_ZOMBIE.get(), ChargedZombieEntityRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(METEOR_HEAD.get(), IFMeteorHeadRenderer::new);


	}
    
	
}