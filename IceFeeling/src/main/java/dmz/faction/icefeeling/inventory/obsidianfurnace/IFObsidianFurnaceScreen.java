package dmz.faction.icefeeling.inventory.obsidianfurnace;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class IFObsidianFurnaceScreen extends IFAbstractFurnaceScreen<IFObsidianFurnaceContainer> {

    public IFObsidianFurnaceScreen(IFObsidianFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}
