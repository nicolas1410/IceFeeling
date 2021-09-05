package dmz.faction.icefeeling.blocks.tnts;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.entities.chunktnt.IFChunkTNTEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.common.extensions.IForgeBlock;

public class IFChunkTNTBlock extends Block implements IForgeBlock {


	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;


	public IFChunkTNTBlock(AbstractBlock.Properties properties) 
	{
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(UNSTABLE, Boolean.valueOf(false)));

	}
	
	@Override
	public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter)
	{
		if (!world.isClientSide) 
		{
			IFChunkTNTEntity chunkTntEntity = new IFChunkTNTEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
			world.addFreshEntity(chunkTntEntity);
			world.playSound((PlayerEntity)null, chunkTntEntity.getX(), chunkTntEntity.getY(), chunkTntEntity.getZ(), SoundEvents.TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}
    
	@Override
	public void onPlace(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) 
	{
		      
		if (!oldState.is(state.getBlock())) 
		{
		         if (worldIn.hasNeighborSignal(pos)) 
		         {
		            catchFire(state, worldIn, pos, null, null);
		            worldIn.removeBlock(pos, false);
		         }

		 }
	}

	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		if (worldIn.hasNeighborSignal(pos)) 
		{
			catchFire(state, worldIn, pos, null, null);
		    worldIn.removeBlock(pos, false);
		}
	}
	
	/**
	 * Called before the Block is set to air in the world. Called regardless of if the player's tool can actually collect
	* this block
	 */
	@Override
	public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) 
	{
		if (!worldIn.isClientSide() && !player.isCreative() && state.getValue(UNSTABLE)) 
		{
		         catchFire(state, worldIn, pos, null, null);
		}

		      super.playerWillDestroy(worldIn, pos, state, player);
	}

	/**
	* Called when this Block is destroyed by an Explosion
	*/
	@Override
	public void wasExploded(World worldIn, BlockPos pos, Explosion explosionIn)
	{
		
		if (!worldIn.isClientSide) 
		{
			IFChunkTNTEntity chunkTntEntity = new IFChunkTNTEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, explosionIn.getSourceMob());
			chunkTntEntity.setFuse((short)(worldIn.random.nextInt(chunkTntEntity.getFuse() / 4) + chunkTntEntity.getFuse() / 8));
		     worldIn.addFreshEntity(chunkTntEntity);
		     
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		ItemStack itemstack = player.getItemInHand(handIn);
		Item item = itemstack.getItem();
		if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) 
		{
		    return super.use(state, worldIn, pos, player, handIn, hit);
		    
		} else {
			catchFire(state, worldIn, pos, hit.getDirection(), player);
		    worldIn.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
		    if (!player.isCreative()) 
		    {
		    	if (item == Items.FLINT_AND_STEEL) 
		    	{
		    		itemstack.hurtAndBreak(1, player, (player1) -> 
		    		{
		    			player1.broadcastBreakEvent(handIn);
		    		});
		    	} else {
		    		itemstack.shrink(1);
		    	}
		    }
		    return ActionResultType.sidedSuccess(worldIn.isClientSide);
			}
	}
	
	@Override
	public void onProjectileHit(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) 
	{
		if (!worldIn.isClientSide)
		{
			Entity entity = projectile.getEntity();
			if (projectile.isOnFire()) 
			{
				BlockPos blockpos = hit.getBlockPos();
				catchFire(state, worldIn, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
				worldIn.removeBlock(blockpos, false);
			}
		}

	}

	/**
	* Return whether this block can drop from an explosion.
	*/
	
	@Override
	public boolean dropFromExplosion(Explosion explosionIn) 
	{
		return false;
	}
	
	@Override
	protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(UNSTABLE);
	}
	
}
