package dmz.faction.icefeeling.inventory.obsidian;

import dmz.faction.icefeeling.inventory.base.IFFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class IFObsidianFurnaceScreen extends IFFurnaceScreen<IFObsidianFurnaceContainer> {

	public IFObsidianFurnaceScreen(IFObsidianFurnaceContainer screenContainer, PlayerInventory inv, ITextComponent name) {
		super(screenContainer, inv, name);
	}

}
