package dmz.faction.icefeeling.blocks.robusium;

import dmz.faction.icefeeling.entities.chargedtnt.IFChargedTNTEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlockState;

public class IFDurabilityTNTBlock extends Block implements IForgeBlockState {

	public static final IntegerProperty TNT_DURABILITY_0_12 = IntegerProperty.create("tnt_durability", 0, 12);
	public static final IntegerProperty TNT = TNT_DURABILITY_0_12;
	
	public IFDurabilityTNTBlock(AbstractBlock.Properties properties) {
		super(properties);
	}

	protected IntegerProperty getTNTProperty() {
		return TNT;	
	}
	
	protected int getTNTDurability(BlockState state) {
	      return state.get(this.getTNTProperty());
	}
	
	protected int getMaxDurability() {
		return 9;
	}

	protected boolean isMaxDurability(BlockState state) {
		return state.get(this.getTNTProperty()) >= this.getMaxDurability();
	}

	protected BlockState withDurability(int durability) {
	      return this.getDefaultState().with(this.getTNTProperty(), Integer.valueOf(durability));
	}

	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion expl) 
	{
		/*	Add the durability with Blockstates
		 * 	Fired at each explosion
		 * 	Creeper + 2 durability
		 * 	Charged TNT + 2 durability
		 * 	TNT + 1 durability
		 * 
		 * 	When durability hits 10 or more, the block is suppressed
		 * 
		 * */
		if(!world.isRemote ) 
		{
			int i = this.getTNTDurability(state) ;
			
					if(i < this.getMaxDurability()) 
					{
						if(expl.getExploder() instanceof CreeperEntity) 
						{
							world.setBlockState(pos, this.withDurability(i + 2), 3);
							System.out.println("Block durability Creeper :" + i);
							
						} else if((expl.getExploder() instanceof IFChargedTNTEntity)) 
						{
							world.setBlockState(pos, this.withDurability(i + 2), 3);
							System.out.println("Block durability Charged TNT :" + i);
							
						} else if((expl.getExploder() instanceof TNTEntity))
						{
							world.setBlockState(pos, this.withDurability(i + 1), 3);
							System.out.println("Block durability TNT :" + i);
						}
						
					}

					if(i >= this.getMaxDurability()) 
					{					
						System.out.println("Blockstate at max durability, block destroyed");
				        world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
						getBlock().onExplosionDestroy(world, pos, expl);
					}
		}	
	}
	
	@Override
    public boolean canDropFromExplosion(BlockState state, IBlockReader world, BlockPos pos, Explosion explosion)
    {
        return false;
    }
	
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
	      builder.add(TNT);
	}
}

