package dmz.faction.icefeeling.inventory.abstracts;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public abstract class IFAbstractFurnaceScreen<T extends IFAbstractFurnaceContainer> extends ContainerScreen<T> {

	private static final ResourceLocation FURNACE_GUI_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/obsidian_furnace.png");

	public IFAbstractFurnaceScreen(T screenContainer, PlayerInventory inv, ITextComponent titleIn) {
		super(screenContainer, inv, titleIn);

	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		this.renderBg(matrixStack, partialTicks, mouseX, mouseY);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderTooltip(matrixStack, mouseX, mouseY);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bind(FURNACE_GUI_TEXTURE);
		int i = this.getGuiLeft();
		int j = this.getGuiTop();
		this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());
		if (this.menu.isBurning()) {
			int k = this.menu.getBurnLeftScaled(13);
			this.blit(matrixStack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.menu.getCookScaled(24);
		this.blit(matrixStack, i + 79, j + 34, 176, 14, l + 1, 16);
	}

	@Override
	public void onClose() {
		super.onClose();
	}

}
