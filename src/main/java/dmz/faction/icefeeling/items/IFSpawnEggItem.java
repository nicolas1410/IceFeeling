package dmz.faction.icefeeling.items;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.fml.RegistryObject;

public class IFSpawnEggItem extends SpawnEggItem {
	private final Lazy<? extends EntityType<?>> entityTypeSupplier;

	public IFSpawnEggItem(RegistryObject<? extends EntityType<?>> entityTypeSupplier, int primaryColorIn,
			int secondaryColorIn, Item.Properties builder) {
		super(null, primaryColorIn, secondaryColorIn, builder);
		this.entityTypeSupplier = Lazy.of(entityTypeSupplier::get);
	}

	public EntityType<?> getType(@Nullable CompoundNBT nbt) {
		return (EntityType<?>) this.entityTypeSupplier.get();
	}
}
