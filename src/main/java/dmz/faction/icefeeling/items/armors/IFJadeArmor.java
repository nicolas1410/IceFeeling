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

public class IFJadeArmor extends ArmorItem {
	
	
	public static Multimap<Attribute, AttributeModifier> obsiArmorAttributes;

	
	public IFJadeArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}
	
	@Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {
		if(hasFullSet(stack, slot, player)) 
		{
			player.addEffect(new EffectInstance(Effects.DIG_SPEED, 0, 1)); // HASTE 2
			player.addEffect(new EffectInstance(Effects.MOVEMENT_SPEED, 0, 2)); // SPEED 3
			player.addEffect(new EffectInstance(Effects.NIGHT_VISION, 0, 0));
		}				

    }
	
	private boolean hasFullSet(ItemStack stack, EquipmentSlotType slot, PlayerEntity player) 
	{
		return 	   player.getItemBySlot(EquipmentSlotType.HEAD).getItem() == IFItems.JADE_HELMET.get()
				&& player.getItemBySlot(EquipmentSlotType.CHEST).getItem() == IFItems.JADE_CHESTPLATE.get()
				&& player.getItemBySlot(EquipmentSlotType.LEGS).getItem() == IFItems.JADE_LEGGINGS.get()
				&& player.getItemBySlot(EquipmentSlotType.FEET).getItem() == IFItems.JADE_BOOTS.get();
	}
		
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
	{	
		String JADE = IFArmorMaterial.JADE.getName();

		String PATH = Main.MOD_ID + ":" + "textures/models/armor/";
		
		String jade_layer_1 = PATH + JADE + "_layer_1.png";
		String jade_layer_2 = PATH + JADE + "_layer_2.png";
		
		if (stack.toString().contains("leggings")) 
		{
			if (stack.toString().contains(JADE))
				return jade_layer_2;
		}

		if (stack.toString().contains("chestplate") || stack.toString().contains("boots") || stack.toString().contains("helmet")) 
		{
			if (stack.toString().contains(JADE))
				return jade_layer_1;
		}	
		return PATH;
	}	
}


