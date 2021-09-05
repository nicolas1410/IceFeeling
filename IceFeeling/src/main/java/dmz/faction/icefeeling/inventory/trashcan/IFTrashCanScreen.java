package dmz.faction.icefeeling.inventory.trashcan;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class IFTrashCanScreen extends ContainerScreen<IFTrashCanContainer> implements IHasContainer<IFTrashCanContainer> {

	private static final ResourceLocation TRASH_CAN_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/single_item_chest_container.png");
	PlayerInventory playerInv;
	ITextComponent name;
	private static IFTrashCanContainer trashCanContainer;

	public IFTrashCanScreen(PlayerEntity player) {
		super(trashCanContainer, player.inventory, new TranslationTextComponent("container.crafting"));
		this.passEvents = false;
		// int i = 222;
		// int j = 114;
		this.imageHeight = 114 + 6 * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}
	

	public IFTrashCanScreen(IFTrashCanContainer container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
		this.passEvents = false;
		// int i = 222;
		// int j = 114;
		this.imageHeight = 114 + 6 * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(TRASH_CAN_TEXTURE);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(matrixStack, i, j, 0, 0, this.imageWidth, 6 * 18 + 17);
		this.blit(matrixStack, i, j + 6 * 18 + 17, 0, 126, this.imageWidth, 96);
	}

}
