package dmz.faction.icefeeling.items.armors;

import com.google.common.collect.Multimap;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class IFOpalArmor extends ArmorItem {
	
	
	public static Multimap<Attribute, AttributeModifier> obsiArmorAttributes;

	
	public IFOpalArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}
	
	@Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {
		if(hasFullSet(stack, slot, player)) 
		{
			player.addPotionEffect(new EffectInstance(Effects.SPEED, 0, 0));
			player.addPotionEffect(new EffectInstance(Effects.DOLPHINS_GRACE, 0, 0));
			player.addPotionEffect(new EffectInstance(Effects.HERO_OF_THE_VILLAGE, 0, 0));

		}				

    }
	
	private boolean hasFullSet(ItemStack stack, EquipmentSlotType slot, PlayerEntity player) 
	{
		return 	   player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == IFItems.OPAL_HELMET.get()
				&& player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == IFItems.OPAL_CHESTPLATE.get()
				&& player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == IFItems.OPAL_LEGGINGS.get()
				&& player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == IFItems.OPAL_BOOTS.get();
	}
		
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
	{	
		String OPAL = IFArmorMaterial.OPAL.getName();

		String PATH = Main.MOD_ID + ":" + "textures/models/armor/";
		
		String opal_layer_1 = PATH + OPAL + "_layer_1.png";
		String opal_layer_2 = PATH + OPAL + "_layer_2.png";
		
		if (stack.toString().contains("leggings")) 
		{
			if (stack.toString().contains(OPAL))
				return opal_layer_2;
		}

		if (stack.toString().contains("chestplate") || stack.toString().contains("boots") || stack.toString().contains("helmet")) 
		{
			if (stack.toString().contains(OPAL))
				return opal_layer_1;
		}	
		return PATH;
	}	
}


