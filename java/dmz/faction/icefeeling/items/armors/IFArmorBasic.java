package dmz.faction.icefeeling.items.armors;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.entity.Entity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.ItemStack;

public class IFArmorBasic extends ArmorItem {

	public IFArmorBasic(IArmorMaterial materialIn, EquipmentSlotType slot, Properties builderIn) {
		super(materialIn, slot, builderIn);
	}


	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type)
	{	
		String TITANITE = IFArmorMaterial.TITANITE.getName();
		String MYTHRIL = IFArmorMaterial.MYTHRIL.getName();

		String PATH = Main.MOD_ID + ":" + "textures/models/armor/";
		

		String titanite_layer_1 = PATH + TITANITE + "_layer_1.png";
		String titanite_layer_2 = PATH + TITANITE + "_layer_2.png";
		String mythril_layer_1 = PATH + MYTHRIL + "_layer_1.png";
		String mythril_layer_2 = PATH + MYTHRIL + "_layer_2.png";

		if (stack.toString().contains("leggings")) 
		{
			if (stack.toString().contains(TITANITE))
				return titanite_layer_2;
			
			if (stack.toString().contains(MYTHRIL))
				return mythril_layer_2;
		}

		if (stack.toString().contains("chestplate") || stack.toString().contains("boots") || stack.toString().contains("helmet")) 
		{
			if (stack.toString().contains(TITANITE))
				return titanite_layer_1;
			
			if (stack.toString().contains(MYTHRIL))
				return mythril_layer_1;	
		}
		
		return PATH;
	}
}
	
