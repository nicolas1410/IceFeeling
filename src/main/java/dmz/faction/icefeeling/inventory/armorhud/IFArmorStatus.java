package dmz.faction.icefeeling.inventory.armorhud;

import net.minecraft.item.ItemStack;

public class IFArmorStatus {


	public static int GetItemDamage(ItemStack item) {
		if (item.getDamage() == 0)
			return -1;
		if (item.getMaxDamage() == 0)
			return -2;
		return (item.getMaxDamage() - item.getDamage()) * 100 / item.getMaxDamage();

	}

}
