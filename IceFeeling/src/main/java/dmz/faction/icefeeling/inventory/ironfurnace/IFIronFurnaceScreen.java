package dmz.faction.icefeeling.inventory.ironfurnace;

import dmz.faction.icefeeling.inventory.abstracts.IFAbstractFurnaceScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.text.ITextComponent;

public class IFIronFurnaceScreen extends IFAbstractFurnaceScreen<IFIronFurnaceContainer> {

    public IFIronFurnaceScreen(IFIronFurnaceContainer container, PlayerInventory inv, ITextComponent name) {
        super(container, inv, name);
    }
}
