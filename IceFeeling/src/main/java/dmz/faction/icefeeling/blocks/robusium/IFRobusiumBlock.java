package dmz.faction.icefeeling.blocks.robusium;

import net.minecraft.block.AbstractBlock;
import net.minecraftforge.common.extensions.IForgeBlock;

public class IFRobusiumBlock extends IFDurabilityTNTBlock implements IForgeBlock {

	public IFRobusiumBlock(AbstractBlock.Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(this.getTNTProperty(), Integer.valueOf(0)));

	}
}
