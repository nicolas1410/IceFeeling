package dmz.faction.icefeeling.inventory.base;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class IFFurnaceScreen<T extends IFFurnaceContainer> extends ContainerScreen<T> {

	public ResourceLocation GUI = new ResourceLocation(Main.MOD_ID + ":" + "textures/gui/obsidian_furnace.png");
	PlayerInventory playerInv;
	ITextComponent name;

	public IFFurnaceScreen(T screenContainer, PlayerInventory inv, ITextComponent name) {
		super(screenContainer, inv, name);
		playerInv = inv;
		this.name = name;
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(matrixStack);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
		RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.minecraft.getTextureManager().bindTexture(GUI);
		int relX = (this.width - this.xSize) / 2;
		int relY = (this.height - this.ySize) / 2;
		this.blit(matrixStack, relX, relY, 0, 0, this.xSize, this.ySize);
		int i;
		if (container.isBurning()) {
			i = (container.getBurnLeftScaled());
			this.blit(matrixStack, guiLeft + 56, guiTop + 36 + 12 - i, 176, 12 - i, 14, i + 1);
		}
		i = (container.getCookScaled());
		this.blit(matrixStack, guiLeft + 79, guiTop + 34, 176, 14, i + 1, 16);

	}

	@Override
	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		return super.mouseClicked(mouseX, mouseY, button);

	}

	@Override
	public void onClose() {
		super.onClose();
	}
}
