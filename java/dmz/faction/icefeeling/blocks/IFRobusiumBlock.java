package dmz.faction.icefeeling.blocks;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.state.IntegerProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class IFRobusiumBlock extends Block {

	public static final IntegerProperty TNT_DURABILITY_0_10 = IntegerProperty.create("tnt_durability", 0, 10);
	IntegerProperty TNT = TNT_DURABILITY_0_10;
	
	public IFRobusiumBlock(Properties properties) {
		super(properties);
		
	    this.setDefaultState(this.stateContainer.getBaseState().with(this.getTNTProperty(), Integer.valueOf(0)));

	}

	protected IntegerProperty getTNTProperty() {
		return TNT;
		
	}
	protected int getTNTDurability(BlockState state) {
	      return state.get(this.getTNTProperty());
	}
	
	protected int getMaxDurability() {
		return 10;
	}
	
	protected boolean isMaxDurability(BlockState state) {
		return state.get(this.getTNTProperty()) >= this.getMaxDurability();
	}
	
	protected BlockState withDurability(int durability) {
	      return this.getDefaultState().with(this.getTNTProperty(), Integer.valueOf(durability));
	}
	
	@Override
	public void onBlockExploded(BlockState state, World world, BlockPos pos, Explosion expl) {
		
		if(!world.isAreaLoaded(pos, 1)) return; 
		
		if(expl.getAffectedBlockPositions() == pos) 
		{
			
			System.out.println("Blocks affected by TNT explosion" + pos.toString());
			int i = this.getTNTDurability(state) ;
			
			if(i <= this.getMaxDurability()) 
			{
				world.setBlockState(pos, this.withDurability(i +1), 2);
				System.out.println("Block durability :" + i);
				
				if(i >= this.getMaxDurability()) 
				{					
					System.out.println("BlockState at 10, block replaced by air");
					world.setBlockState(pos, Blocks.AIR.getDefaultState(), 3);
			        getBlock().onExplosionDestroy(world, pos, expl);
					
				}
				
			}
		}

    }
		
}
	
	
	
	
	
	
	
	
	
	/*private int getExplosionDurability(BlockState state) {
	      return state.isIn(this) ? state.get(TNT) : 0;
	   }*/

