package dmz.faction.icefeeling.inventory.singleitem;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import dmz.faction.icefeeling.inventory.abstracts.IFCommonItemFilterContainerBase;
import dmz.faction.icefeeling.inventory.singleitem.abstracts.IFSingleItemChestContainerBase;
import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.gui.IHasContainer;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class IFSingleItemChestScreen extends ContainerScreen<IFSingleItemChestContainerBase> implements IHasContainer<IFSingleItemChestContainerBase> {
	/** The ResourceLocation containing the chest GUI texture. */
	
	//To easily handle 512*512 textures for bigger chests
	private static final ResourceLocation CHEST_GUI_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/single_item_chest_container_big.png");
	private static final ResourceLocation RIGHT_GUI_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/single_item_chest_container_big_right.png");
	private static final ResourceLocation INVENTORY_GUI_TEXTURE = new ResourceLocation(Main.MOD_ID, "textures/gui/single_item_chest_container_big_player.png");


	public IFSingleItemChestScreen(IFSingleItemChestContainerBase container, PlayerInventory playerInventory, ITextComponent title) {
		super(container, playerInventory, title);
		this.passEvents = false;
	    this.ySize = 114 + 6 * 18;
	    this.playerInventoryTitleY = this.ySize - 44;
	    this.playerInventoryTitleX = this.xSize - 249;
	    this.titleY = -52;
	    this.titleX = -72;

	    
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
		this.minecraft.getTextureManager().bindTexture(CHEST_GUI_TEXTURE);
		int i = (this.width - this.xSize) / 2;
		int j = (this.height - this.ySize) / 2;
		this.blit(matrixStack, i - 81, j + 12 * 18 - 274, 0, 0, 256, 249);
		
		this.minecraft.getTextureManager().bindTexture(RIGHT_GUI_TEXTURE);
		int d = (this.width - this.xSize) / 2;
		int c = (this.height - this.ySize) / 2;
		this.blit(matrixStack, d + 175, c + 12 * 18 - 274, 0, 0, 81, 249);
		
		this.minecraft.getTextureManager().bindTexture(INVENTORY_GUI_TEXTURE);
		int h = (this.width - this.xSize) / 2;
		int n = (this.height - this.ySize) / 2;
		this.blit(matrixStack, h - 81, n + 168, 0, 249, 255, 12 * 18 + 17);
		}
	
}
