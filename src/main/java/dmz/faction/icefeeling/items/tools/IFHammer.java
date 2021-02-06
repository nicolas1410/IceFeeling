package dmz.faction.icefeeling.items.tools;

import java.util.Random;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.collect.ImmutableSet;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ToolType;

public class IFHammer extends Item {
 
	public static final ToolType HAMMER_TOOL = ToolType.get(Main.MOD_ID+"_hammer");

	
	public IFHammer(Properties properties) {
		super(properties.maxDamage(100));
	}
	
	@Nonnull
	@Override
	public ItemStack getContainerItem(@Nonnull ItemStack stack)
	{
		Random rand = new Random();
		ItemStack container = stack.copy();
		if(container.attemptDamageItem(1, rand, null))
			return ItemStack.EMPTY;	
		else
			return container;
	}
	
	@Override
	public boolean hasContainerItem() {
		return true;
	}

	@Nonnull
	@Override
	public Set<ToolType> getToolTypes(ItemStack stack)
	{
		return ImmutableSet.of(HAMMER_TOOL);
	}
	
	@Override
	public boolean isDamageable()
	{
		return true;
	}

}
