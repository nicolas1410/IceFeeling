package dmz.faction.icefeeling.blocks.tnts;

import javax.annotation.Nullable;

import dmz.faction.icefeeling.entities.chargedtnt.IFChargedTNTEntity;
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

public class IFChargedTNTBock extends Block implements IForgeBlock {


	public static final BooleanProperty UNSTABLE = BlockStateProperties.UNSTABLE;


	public IFChargedTNTBock(AbstractBlock.Properties properties) 
	{
		super(properties);
	    this.setDefaultState(this.getDefaultState().with(UNSTABLE, Boolean.valueOf(false)));

	}
	
	@Override
	public void catchFire(BlockState state, World world, BlockPos pos, @Nullable Direction face, @Nullable LivingEntity igniter)
	{
		if (!world.isRemote) 
		{
			IFChargedTNTEntity chargedtntentity = new IFChargedTNTEntity(world, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, igniter);
			world.addEntity(chargedtntentity);
			world.playSound((PlayerEntity)null, chargedtntentity.getPosX(), chargedtntentity.getPosY(), chargedtntentity.getPosZ(), SoundEvents.ENTITY_TNT_PRIMED, SoundCategory.BLOCKS, 1.0F, 1.0F);
		}
	}
    
	
	public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) 
	{
		      
		if (!oldState.isIn(state.getBlock())) 
		{
		         if (worldIn.isBlockPowered(pos)) 
		         {
		            catchFire(state, worldIn, pos, null, null);
		            worldIn.removeBlock(pos, false);
		         }

		 }
	}

	
	@Override
	public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) 
	{
		if (worldIn.isBlockPowered(pos)) 
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
	public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) 
	{
		if (!worldIn.isRemote() && !player.isCreative() && state.get(UNSTABLE)) 
		{
		         catchFire(state, worldIn, pos, null, null);
		}

		      super.onBlockHarvested(worldIn, pos, state, player);
	}

	/**
	* Called when this Block is destroyed by an Explosion
	*/
	@Override
	public void onExplosionDestroy(World worldIn, BlockPos pos, Explosion explosionIn)
	{
		
		if (!worldIn.isRemote) 
		{
		     IFChargedTNTEntity chargedtntentity = new IFChargedTNTEntity(worldIn, (double)pos.getX() + 0.5D, (double)pos.getY(), (double)pos.getZ() + 0.5D, explosionIn.getExplosivePlacedBy());
		     chargedtntentity.setFuse((short)(worldIn.rand.nextInt(chargedtntentity.getFuse() / 4) + chargedtntentity.getFuse() / 8));
		     worldIn.addEntity(chargedtntentity);
		     
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) 
	{
		ItemStack itemstack = player.getHeldItem(handIn);
		Item item = itemstack.getItem();
		if (item != Items.FLINT_AND_STEEL && item != Items.FIRE_CHARGE) 
		{
		    return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		    
		} else {
			catchFire(state, worldIn, pos, hit.getFace(), player);
		    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState(), 11);
		    if (!player.isCreative()) 
		    {
		    	if (item == Items.FLINT_AND_STEEL) 
		    	{
		    		itemstack.damageItem(1, player, (player1) -> 
		    		{
		    			player1.sendBreakAnimation(handIn);
		    		});
		    	} else {
		    		itemstack.shrink(1);
		    	}
		    }
		    return ActionResultType.func_233537_a_(worldIn.isRemote);
			}
	}
	
	@Override
	public void onProjectileCollision(World worldIn, BlockState state, BlockRayTraceResult hit, ProjectileEntity projectile) 
	{
		if (!worldIn.isRemote)
		{
			Entity entity = projectile.func_234616_v_();
			if (projectile.isBurning()) 
			{
				BlockPos blockpos = hit.getPos();
				catchFire(state, worldIn, blockpos, null, entity instanceof LivingEntity ? (LivingEntity)entity : null);
				worldIn.removeBlock(blockpos, false);
			}
		}

	}

	/**
	* Return whether this block can drop from an explosion.
	*/
	
	@Override
	public boolean canDropFromExplosion(Explosion explosionIn) 
	{
		return false;
	}
	
	@Override
	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) 
	{
		builder.add(UNSTABLE);
	}
	
}
