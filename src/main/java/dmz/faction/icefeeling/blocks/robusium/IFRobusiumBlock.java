package dmz.faction.icefeeling.blocks.robusium;

import net.minecraftforge.common.extensions.IForgeBlock;

public class IFRobusiumBlock extends IFDurabilityTNTBlock implements IForgeBlock{
	
	public IFRobusiumBlock(Properties properties) {
		super(properties);
	    this.setDefaultState(this.stateContainer.getBaseState().with(this.getTNTProperty(), Integer.valueOf(0)));
	}
}
