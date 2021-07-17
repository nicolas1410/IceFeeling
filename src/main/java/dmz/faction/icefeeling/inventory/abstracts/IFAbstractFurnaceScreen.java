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
		this.guiLeft = 0;
		this.guiTop = 0;

	}

	public void init() {
		super.init();
	}

	public void tick() {
		super.tick();
	}

	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		this.drawGuiContainerBackgroundLayer(matrixStack, partialTicks, mouseX, mouseY);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(FURNACE_GUI_TEXTURE);
		int i = this.guiLeft;
		int j = this.guiTop;
		this.blit(matrixStack, i, j, 0, 0, this.xSize, this.ySize);
		if (this.container.isBurning()) {
			int k = this.container.getBurnLeftScaled(13);
			this.blit(matrixStack, i + 56, j + 36 + 12 - k, 176, 12 - k, 14, k + 1);
		}

		int l = this.container.getCookScaled(24);
		this.blit(matrixStack, i + 79, j + 34, 176, 14, l + 1, 16);
	}

	public void onClose() {
		super.onClose();
	}

}
