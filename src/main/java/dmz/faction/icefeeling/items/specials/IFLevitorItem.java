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

public class IFLevitorItem extends Item {

	public IFLevitorItem(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		
		super.appendHoverText(stack, worldIn, tooltip, flagIn);
		tooltip.add(new StringTextComponent("Press LSHIFT for infos").withStyle(TextFormatting.DARK_GRAY));
		
		if(InputMappings.isKeyDown(Minecraft.getInstance().getWindow().getWindow(), GLFW.GLFW_KEY_LEFT_SHIFT)) 
		{
			tooltip.add(new StringTextComponent("Right Click To Get Levitation 4").withStyle(TextFormatting.ITALIC).withStyle(TextFormatting.GRAY));
			tooltip.add(new StringTextComponent("6 minutes of cooldown").withStyle(TextFormatting.ITALIC).withStyle(TextFormatting.AQUA));

		}
		
	}
	
	@Override
	public ActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn) 
	{
		
			if(!player.getCooldowns().isOnCooldown(this)) 
			{
				player.addEffect(new EffectInstance(Effects.LEVITATION, 800, 3));
				player.getCooldowns().addCooldown(this, 9600);
				
				player.level.playSound(player, player.blockPosition(), SoundEvents.SHULKER_SHOOT, SoundCategory.NEUTRAL, 1.0F, 0.5F);
				//player.world.playSound(player, player.getPosition(), SoundEvents.ENTITY_BLAZE_HURT, SoundCategory.NEUTRAL, 1.0F, 0.5F);
					return ActionResult.success(player.getItemInHand(handIn));			
			}
			
		return ActionResult.fail(player.getItemInHand(handIn));
	
	}
		
}
