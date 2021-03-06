package dmz.faction.icefeeling.items.armors;

import dmz.faction.icefeeling.items.IFItems;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class IFObsidianArmor extends ArmorItem { 
	
	public IFObsidianArmor(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}
	
	@Override
    public void onArmorTick(ItemStack stack, World world, PlayerEntity player)
    {
		if(hasFullSet(stack, slot, player)) 
		{
			
			player.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 0, 0));
		}	
    }
	
	private boolean hasFullSet(ItemStack stack, EquipmentSlotType slot, PlayerEntity player) 
	{
		
		return 	   player.getItemStackFromSlot(EquipmentSlotType.HEAD).getItem() == IFItems.OBSIDIAN_HELMET.get()
				&& player.getItemStackFromSlot(EquipmentSlotType.CHEST).getItem() == IFItems.OBSIDIAN_CHESTPLATE.get()
				&& player.getItemStackFromSlot(EquipmentSlotType.LEGS).getItem() == IFItems.OBSIDIAN_LEGGINGS.get()
				&& player.getItemStackFromSlot(EquipmentSlotType.FEET).getItem() == IFItems.OBSIDIAN_BOOTS.get();
		
	}
		
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
	{	
		String OBSIDIAN = IFArmorMaterial.OBSIDIAN.getName();

		String PATH = Main.MOD_ID + ":" + "textures/models/armor/";
		
		String obsidian_layer_1 = PATH + OBSIDIAN + "_layer_1.png";
		String obsidian_layer_2 = PATH + OBSIDIAN + "_layer_2.png";
		
		if (stack.toString().contains("leggings")) 
		{
			if (stack.toString().contains(OBSIDIAN))
				return obsidian_layer_2;
		}

		if (stack.toString().contains("chestplate") || stack.toString().contains("boots") || stack.toString().contains("helmet")) 
		{
			if (stack.toString().contains(OBSIDIAN))
				return obsidian_layer_1;
		}
		
		return PATH;
	}	
		
		
	
	
	
}


