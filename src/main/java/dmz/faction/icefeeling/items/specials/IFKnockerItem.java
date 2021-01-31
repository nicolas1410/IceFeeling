package dmz.faction.icefeeling.items.specials;

import java.util.List;

import javax.annotation.Nullable;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.client.util.InputMappings;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IFKnockerItem extends Item {

	public IFKnockerItem(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		
		super.addInformation(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Press LSHIFT for infos").mergeStyle(TextFormatting.DARK_GRAY));
		
		if(InputMappings.isKeyDown(Minecraft.getInstance().getMainWindow().getHandle(), GLFW.GLFW_KEY_LEFT_SHIFT)) 
		{
			tooltip.add(new StringTextComponent("Right Click To Get Haste 3").mergeStyle(TextFormatting.ITALIC).mergeStyle(TextFormatting.GRAY));
			tooltip.add(new StringTextComponent("6 minutes of cooldown").mergeStyle(TextFormatting.ITALIC).mergeStyle(TextFormatting.AQUA));

		}
	
		
	}
	
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity player, Hand handIn) 
	{
		
			if(!player.getCooldownTracker().hasCooldown(this)) 
			{
				player.addPotionEffect(new EffectInstance(Effects.HASTE, 2400, 2));
				player.getCooldownTracker().setCooldown(this, 9600);
				
				player.world.playSound(player, player.getPosition(), SoundEvents.ENTITY_BLAZE_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 0.5F);
				player.world.playSound(player, player.getPosition(), SoundEvents.ENTITY_BLAZE_HURT, SoundCategory.NEUTRAL, 1.0F, 0.5F);
					return ActionResult.resultSuccess(player.getHeldItem(handIn));			
			}
			
		return ActionResult.resultFail(player.getHeldItem(handIn));
	
	}
	
	
}
