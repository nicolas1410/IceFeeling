package dmz.faction.icefeeling.client.screens;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import javax.annotation.Nullable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.util.concurrent.Runnables;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;

import dmz.faction.icefeeling.mod.Main;
import net.minecraft.client.gui.AccessibilityScreen;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.ConfirmScreen;
import net.minecraft.client.gui.screen.ConnectingScreen;
import net.minecraft.client.gui.screen.LanguageScreen;
import net.minecraft.client.gui.screen.MultiplayerScreen;
import net.minecraft.client.gui.screen.MultiplayerWarningScreen;
import net.minecraft.client.gui.screen.OptionsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WinGameScreen;
import net.minecraft.client.gui.screen.WorldSelectionScreen;
import net.minecraft.client.gui.toasts.SystemToast;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.button.ImageButton;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.RenderSkyboxCube;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SharedConstants;
import net.minecraft.util.Util;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.gen.settings.DimensionGeneratorSettings;
import net.minecraft.world.storage.SaveFormat;
import net.minecraft.world.storage.WorldSummary;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IFMainMenuScreen extends Screen {

	private static final String SERVER_IP = "hypixel.net";
	private Button directPlayButton;

	private static final Logger LOGGER = LogManager.getLogger();
	public static final RenderSkyboxCube PANORAMA_RESOURCES = new RenderSkyboxCube(
			new ResourceLocation(Main.MOD_ID, "textures/gui/title/background/panorama"));
	private static final ResourceLocation PANORAMA_OVERLAY_TEXTURES = new ResourceLocation(
			"textures/gui/title/background/panorama_overlay.png");
	private static final ResourceLocation ACCESSIBILITY_TEXTURES = new ResourceLocation(
			"textures/gui/accessibility.png");
	private static final ResourceLocation DISCORD_ICON_TEXTURE = new ResourceLocation(Main.MOD_ID,
			"textures/gui/title/discord_icon.png");
	private static final ResourceLocation WEBSITE_ICON_TEXTURE = new ResourceLocation(Main.MOD_ID,
			"textures/gui/title/website_icon.png");

	private final boolean showTitleWronglySpelled;
	@Nullable
	private String splashText;
	private Button buttonResetDemo;
	private static final ResourceLocation MINECRAFT_TITLE_TEXTURES = new ResourceLocation(Main.MOD_ID,
			"textures/gui/title/minecraft.png");
	private static final ResourceLocation MINECRAFT_TITLE_EDITION = new ResourceLocation(Main.MOD_ID,
			"textures/gui/title/edition.png");

	private int widthCopyright;
	private int widthCopyrightRest;
	private final RenderSkybox panorama = new RenderSkybox(PANORAMA_RESOURCES);
	private final boolean showFadeInAnimation;
	private long firstRenderTime;
	

	public IFMainMenuScreen() {
		this(false);
	}

	public IFMainMenuScreen(boolean fadeIn) {
		super(new TranslationTextComponent("narrator.screen.title"));
		this.showFadeInAnimation = fadeIn;
		this.showTitleWronglySpelled = (double) (new Random()).nextFloat() < 1.0E-4D;
	}

	public static CompletableFuture<Void> loadAsync(TextureManager texMngr, Executor backgroundExecutor) {
		return CompletableFuture.allOf(texMngr.preload(MINECRAFT_TITLE_TEXTURES, backgroundExecutor),
				texMngr.preload(MINECRAFT_TITLE_EDITION, backgroundExecutor),
				texMngr.preload(PANORAMA_OVERLAY_TEXTURES, backgroundExecutor),
				PANORAMA_RESOURCES.preload(texMngr, backgroundExecutor));
	}

	public boolean isPauseScreen() {
		return false;
	}

	public boolean shouldCloseOnEsc() {
		return false;
	}

	protected void init() {
		if (this.splashText == null) {
			this.splashText = this.minecraft.getSplashManager().getSplash();
		}

		this.widthCopyright = this.font.width("Copyright Mojang AB. Do not distribute! IceFeeling Server");
		this.widthCopyrightRest = this.width - this.widthCopyright - 2;

		int i = 24;
		int j = this.height / 4 + 48;
		if (this.minecraft.isDemo()) {
			this.addDemoButtons(j, i);
		} else {
			this.addSingleplayerMultiplayerButtons(j, i);
			/*
			 * modButton = this.addButton(new Button(this.width / 2 - 100, j + 24 * 2, 98,
			 * 20, new TranslationTextComponent("fml.menu.mods"), button -> {
			 * this.minecraft.displayGuiScreen(new
			 * net.minecraftforge.fml.client.gui.screen.ModListScreen(this));
			 */
			// }));
		}

		this.addLinkButtons(j, 24);
		// Direct Connection to server
		this.addDirectConnectionButton(j);

		this.addButton(new ImageButton(this.width / 2 - 124, j + 72 + 12, 20, 20, 0, 106, 20, Button.WIDGETS_LOCATION,
				256, 256, (p_213090_1_) -> {
					this.minecraft.setScreen(
							new LanguageScreen(this, this.minecraft.options, this.minecraft.getLanguageManager()));
				}, new TranslationTextComponent("narrator.button.language")));

		this.addButton(new Button(this.width / 2 - 100, j + 72 + 12, 98, 20,
				new TranslationTextComponent("menu.options"), (p_213096_1_) -> {
					this.minecraft.setScreen(new OptionsScreen(this, this.minecraft.options));
				}));
		this.addButton(new Button(this.width / 2 + 2, j + 72 + 12, 98, 20, new TranslationTextComponent("menu.quit"),
				(p_213094_1_) -> {
					this.minecraft.stop();
				}));
		this.addButton(new ImageButton(this.width / 2 + 104, j + 72 + 12, 20, 20, 0, 0, 20, ACCESSIBILITY_TEXTURES, 32,
				64, (p_213088_1_) -> {
					this.minecraft.setScreen(new AccessibilityScreen(this, this.minecraft.options));
				}, new TranslationTextComponent("narrator.button.accessibility")));

	}

	public boolean isServerOn() {
		return true;

	}

	public void addDirectConnectionButton(int yIn) {

		if (this != null) {

			this.directPlayButton = this.addButton(new Button(this.width / 2 - 100, yIn + 48 * 1, 200, 20,
					new TranslationTextComponent("menu.server"), (p_213096_1_) -> {
						this.connectToIceFeeling();

					}));

			if (!isServerOn()) {
				/*
				 * this.lockButton = this.addButton(new LockIconButton(this.directPlayButton.x +
				 * this.directPlayButton.getWidth() - 20, this.directPlayButton.y, (p_213054_1_)
				 * -> { this.connectToIceFeeling(); })); this.lockButton.setLocked(true);
				 * this.lockButton.active = !this.lockButton.isLocked();
				 */
				// this.directPlayButton.active = !this.lockButton.isLocked();
				this.directPlayButton.active = false;
			}

		}
		// }
	}

	public void connectToIceFeeling() {

		this.connectToServer(new ServerData("IceFeeling Server", SERVER_IP, false));
	}

	private void connectToServer(ServerData server) {
		this.minecraft.setScreen(new ConnectingScreen(this, this.minecraft, server));

	}

	/**
	 * Adds Singleplayer and Multiplayer buttons on Main Menu for players who have
	 * bought the game.
	 * 
	 * createNormalMenuOptions
	 */
	private void addSingleplayerMultiplayerButtons(int yIn, int rowHeightIn) {
		this.addButton(new Button(this.width / 2 - 100, yIn, 200, 20, new TranslationTextComponent("menu.singleplayer"),
				(p_213089_1_) -> {
					this.minecraft.setScreen(new WorldSelectionScreen(this));
				}));
		boolean flag = this.minecraft.allowsMultiplayer();
		Button.ITooltip button$itooltip = flag ? Button.NO_TOOLTIP
				: (p_238659_1_, p_238659_2_, p_238659_3_, p_238659_4_) -> {
					if (!p_238659_1_.active) {
						this.renderTooltip(p_238659_2_,
								this.minecraft.font.split(new TranslationTextComponent("title.multiplayer.disabled"),
										Math.max(this.width / 2 - 43, 170)),
								p_238659_3_, p_238659_4_);

					}

				};
		(this.addButton(new Button(this.width / 2 - 100, yIn + rowHeightIn * 1, 200, 20,
				new TranslationTextComponent("menu.multiplayer"), (p_213095_1_) -> {
					Screen screen = (Screen) (this.minecraft.options.skipMultiplayerWarning
							? new MultiplayerScreen(this)
							: new MultiplayerWarningScreen(this));
					this.minecraft.setScreen(screen);
				}, button$itooltip))).active = flag;

	}

	/**
	 * Adds link buttons
	 */
	private void addLinkButtons(int yIn, int rowHeightIn) {
		this.addButton(new ImageButton(this.width / 2 - 124, yIn + 12 + 12, 20, 20, 20, 0, 20, DISCORD_ICON_TEXTURE, 20,
				40, (p_213090_1_) -> {
					this.minecraft.setScreen(new ConfirmOpenLinkScreen((p_244739_1_) -> {
						if (p_244739_1_) {
							Util.getPlatform().openUri("https://facebook.com");
						}

						this.minecraft.setScreen(this);
					}, "https://facebook.com", true));
				}));

		this.addButton(new ImageButton(this.width / 2 + 104, yIn + rowHeightIn * 1, 20, 20, 20, 0, 20,
				WEBSITE_ICON_TEXTURE, 20, 40, (p_213090_1_) -> {
					this.minecraft.setScreen(new ConfirmOpenLinkScreen((p_244739_1_) -> {
						if (p_244739_1_) {
							Util.getPlatform().openUri("https://icefeeling.com");
						}

						this.minecraft.setScreen(this);
					}, "https://icefeeling.com", true));
				}));

	}

	/**
	 * Adds Demo buttons on Main Menu for players who are playing Demo.
	 */
	private void addDemoButtons(int yIn, int rowHeightIn) {
		boolean flag = this.checkDemoWorldPresence();
		this.addButton(new Button(this.width / 2 - 100, yIn, 200, 20, new TranslationTextComponent("menu.playdemo"),
				(p_213091_2_) -> {
					if (flag) {
						this.minecraft.loadLevel("Demo_World");
					} else {
						DynamicRegistries.Impl dynamicregistries$impl = DynamicRegistries.builtin();
						this.minecraft.createLevel("Demo_World", MinecraftServer.DEMO_SETTINGS, dynamicregistries$impl,
								DimensionGeneratorSettings.demoSettings(dynamicregistries$impl));
					}

				}));
		this.buttonResetDemo = this.addButton(new Button(this.width / 2 - 100, yIn + rowHeightIn * 1, 200, 20,
				new TranslationTextComponent("menu.resetdemo"), (p_238658_1_) -> {

					try (SaveFormat.LevelSave saveformat$levelsave = this.minecraft.getLevelSource()
							.createAccess("Demo_World")) {
						WorldSummary worldsummary = saveformat$levelsave.getSummary();

						if (worldsummary != null) {
							this.minecraft.setScreen(new ConfirmScreen(this::deleteDemoWorld,
									new TranslationTextComponent("selectWorld.deleteQuestion"),
									new TranslationTextComponent("selectWorld.deleteWarning",
											worldsummary.getLevelName()),
									new TranslationTextComponent("selectWorld.deleteButton"), DialogTexts.GUI_CANCEL));
						}
					} catch (IOException ioexception) {
						SystemToast.onWorldAccessFailure(this.minecraft, "Demo_World");
						LOGGER.warn("Failed to access demo world", (Throwable) ioexception);
					}
				}));

		this.buttonResetDemo.active = flag;

	}

	private boolean checkDemoWorldPresence() {

		try (SaveFormat.LevelSave saveformat$levelsave = this.minecraft.getLevelSource().createAccess("Demo_World")) {
			return saveformat$levelsave.getSummary() != null;

		} catch (IOException ioexception) {
			SystemToast.onWorldAccessFailure(this.minecraft, "Demo_World");
			LOGGER.warn("Failed to read demo world data", (Throwable) ioexception);
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		if (this.firstRenderTime == 0L && this.showFadeInAnimation) {
			this.firstRenderTime = Util.getMillis();
		}

		float f = this.showFadeInAnimation ? (float) (Util.getMillis() - this.firstRenderTime) / 1000.0F : 1.0F;
		fill(matrixStack, 0, 0, this.width, this.height, -1);
		this.panorama.render(partialTicks, MathHelper.clamp(f, 0.0F, 1.0F));
		int j = this.width / 2 - 137;
		this.minecraft.getTextureManager().bind(PANORAMA_OVERLAY_TEXTURES);
		RenderSystem.enableBlend();
		RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
		RenderSystem.color4f(1.0F, 1.0F, 1.0F,
				this.showFadeInAnimation ? (float) MathHelper.ceil(MathHelper.clamp(f, 0.0F, 1.0F)) : 1.0F);
		blit(matrixStack, 0, 0, this.width, this.height, 0.0F, 0.0F, 16, 128, 16, 128);
		float f1 = this.showFadeInAnimation ? MathHelper.clamp(f - 1.0F, 0.0F, 1.0F) : 1.0F;
		int l = MathHelper.ceil(f1 * 255.0F) << 24;
		if ((l & -67108864) != 0) {
			this.minecraft.getTextureManager().bind(MINECRAFT_TITLE_TEXTURES);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, f1);
			if (this.showTitleWronglySpelled) {
				this.blitOutlineBlack(j, 30, (p_238660_2_, p_238660_3_) -> {
					this.blit(matrixStack, p_238660_2_ + 0, p_238660_3_, 0, 0, 99, 44);
					this.blit(matrixStack, p_238660_2_ + 99, p_238660_3_, 129, 0, 27, 44);
					this.blit(matrixStack, p_238660_2_ + 99 + 26, p_238660_3_, 126, 0, 3, 44);
					this.blit(matrixStack, p_238660_2_ + 99 + 26 + 3, p_238660_3_, 99, 0, 26, 44);
					this.blit(matrixStack, p_238660_2_ + 155, p_238660_3_, 0, 45, 155, 44);
				});
			} else {
				this.blitOutlineBlack(j, 30, (p_238657_2_, p_238657_3_) -> {
					this.blit(matrixStack, p_238657_2_ + 0, p_238657_3_, 0, 0, 155, 44);
					this.blit(matrixStack, p_238657_2_ + 155, p_238657_3_, 0, 45, 155, 44);
				});
			}

			this.minecraft.getTextureManager().bind(MINECRAFT_TITLE_EDITION);
			blit(matrixStack, j + 88, 67, 0.0F, 0.0F, 98, 14, 128, 16);
			if (this.splashText != null) {
				RenderSystem.pushMatrix();
				RenderSystem.translatef((float) (this.width / 2 + 90), 70.0F, 0.0F);
				RenderSystem.rotatef(-20.0F, 0.0F, 0.0F, 1.0F);
				float f2 = 1.8F - MathHelper.abs(
						MathHelper.sin((float) (Util.getMillis() % 1000L) / 1000.0F * ((float) Math.PI * 2F)) * 0.1F);
				f2 = f2 * 100.0F / (float) (this.font.width(this.splashText) + 32);
				RenderSystem.scalef(f2, f2, f2);
				drawCenteredString(matrixStack, this.font, this.splashText, 0, -8, 16776960 | l);
				RenderSystem.popMatrix();
			}

			String s = "Minecraft " + SharedConstants.getCurrentVersion().getName();
			if (this.minecraft.isDemo()) {
				s = s + " Demo";
			} else {
				s = s + ("release".equalsIgnoreCase(this.minecraft.getVersionType()) ? ""
						: "/" + this.minecraft.getVersionType());
			}

			if (this.minecraft.isProbablyModded()) {
				s = s + I18n.get("menu.modded");
			}

			/*
			 * Forge Version Minecraft Version Mod Counts MCP Version
			 * 
			 * net.minecraftforge.fml.BrandingControl.forEachLine(true, true, (brdline, brd)
			 * -> drawString(matrixStack, this.font, brd, 2, this.height - ( 10 + brdline *
			 * (this.font.FONT_HEIGHT + 1)), 16777215 | l) );
			 * 
			 * net.minecraftforge.fml.BrandingControl.forEachAboveCopyrightLine((brdline,
			 * brd) -> drawString(matrixStack, this.font, brd, this.width -
			 * font.getStringWidth(brd), this.height - (10 + (brdline + 1) * (
			 * this.font.FONT_HEIGHT + 1)), 16777215 | l) );
			 */

			drawString(matrixStack, this.font, "Copyright Mojang AB. Do not distribute! IceFeeling Server",
					this.widthCopyrightRest, this.height - 10, 16777215 | l);
			if (mouseX > this.widthCopyrightRest && mouseX < this.widthCopyrightRest + this.widthCopyright
					&& mouseY > this.height - 10 && mouseY < this.height) {
				fill(matrixStack, this.widthCopyrightRest, this.height - 1,
						this.widthCopyrightRest + this.widthCopyright, this.height, 16777215 | l);
			}

			for (Widget widget : this.buttons) {
				widget.setAlpha(f1);
			}

			super.render(matrixStack, mouseX, mouseY, partialTicks);

		}
	}

	public boolean mouseClicked(double mouseX, double mouseY, int button) {
		if (super.mouseClicked(mouseX, mouseY, button)) {
			return true;
		} else {
			if (mouseX > (double) this.widthCopyrightRest
					&& mouseX < (double) (this.widthCopyrightRest + this.widthCopyright)
					&& mouseY > (double) (this.height - 10) && mouseY < (double) this.height) {
				this.minecraft.setScreen(new WinGameScreen(false, Runnables.doNothing()));
			}

			return false;
		}
	}

	private void deleteDemoWorld(boolean p_213087_1_) {
		if (p_213087_1_) {
			try (SaveFormat.LevelSave saveformat$levelsave = this.minecraft.getLevelSource()
					.createAccess("Demo_World")) {
				saveformat$levelsave.deleteLevel();

			} catch (IOException ioexception) {
				SystemToast.onWorldDeleteFailure(this.minecraft, "Demo_World");
				LOGGER.warn("Failed to delete demo world", (Throwable) ioexception);
			}
		}

		this.minecraft.setScreen(this);
	}
}
