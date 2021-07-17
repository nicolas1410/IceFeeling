package dmz.faction.icefeeling.inventory.armorhud;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.IngameGui;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

//@Mod.EventBusSubscriber(modid = Main.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IFArmorHUD extends IngameGui {

	public final int[][] armorPosX = new int[][] { { -136, -136, 136, 136, 136, -136, 119},
			{ -136, -136, 136, 136, 136, -136, 136, -136 } };
											/*
											 * 1 = HELMET
											 * 2 = CHESTPLATE
											 * 3 = LEGGINGS
											 * 4 = BOOTS
											 * 5 = SHIELD
											 * 6 = SWORD
											 */
	private final int[] armorPosY = new int[] { -164, -152, -140, -128, -116, -104 };

	public static String[] armorTextures = new String[] { "textures/item/empty_armor_slot_helmet.png",
			"textures/item/empty_armor_slot_chestplate.png", "textures/item/empty_armor_slot_leggings.png",
			"textures/item/empty_armor_slot_boots.png", "textures/item/empty_armor_slot_shield.png",
			"textures/item/empty_main_hand_slot.png" };

	private final Minecraft minecraft;

	private final ItemRenderer itemRenderer;

	private final FontRenderer fontRenderer;

	public static int armX;
	public static int armY;

	public enum ArmorDamage {
		DAMAGE;
	}
	public enum ArmorType {
		ARMOR;
	}

	public static ArmorDamage armDamage;
	public static ArmorType armType;

	public IFArmorHUD() {
		super(Minecraft.getInstance());
		this.minecraft = Minecraft.getInstance();
		this.itemRenderer = this.minecraft.getItemRenderer();
		this.fontRenderer = this.minecraft.fontRenderer;

	}

	@SubscribeEvent
	public void onPreRenderGui(RenderGameOverlayEvent.Pre event) {
		if (event.getType() != RenderGameOverlayEvent.ElementType.ALL)
			return;
		MatrixStack mat = new MatrixStack();
		int width = this.mc.getMainWindow().getWidth();
		int height = this.mc.getMainWindow().getHeight();
		RenderArmorStatus(mat, width, height);

	}

	private void RenderArmorStatus(MatrixStack mat, int width, int height) {
		if (armY <= 90 && Math.abs(armX) <= 90) {
			int i = 5;
			RenderSystem.pushMatrix();
			RenderSystem.translated(0.0D, 0.0D, -255.0D);
			for (ItemStack item : this.mc.player.getEquipmentAndArmor()) {
				if (armType == ArmorType.ARMOR && i > 3) {
					i--;
					continue;
				}
				int Damage = IFArmorStatus.GetItemDamage(item);
				if (Damage >= 0) {
					String p, text = "";
					int xp = width / 2 + this.armorPosX[1][i];
					p = String.valueOf(item.getMaxDamage() - item.getDamage());
					text = getDamageText(p, Damage);
					xp += 23 - p.length() * 6;


					this.itemRenderer.renderItemAndEffectIntoGUI(item, width + this.armorPosX[0][i], height + this.armorPosY[i]);
					this.fontRenderer.drawStringWithShadow(mat, text, xp, (height + this.armorPosY[i]), 16777215);
					blit(mat, width / 2 + this.armorPosX[0][i], height + this.armorPosY[i], 16.0F, 16.0F, 16, 16, 16, 16);
					
				}

				if (i == 5) {
					this.mc.getTextureManager().bindTexture(new ResourceLocation("icefeeling", armorTextures[i]));

				}
				i--;

			}
			RenderSystem.popMatrix();

		} else

		{

			int x = 0;
			int y = 0;
			boolean right = false;

			x = width / 2 - 45 - armX;
			if (armX > 0)
				right = true;

			if (x < 0) {
				x = 0;
			} else if (x > width - 90) {
				x = width - 90;
			}
			if (y < 0) {
				y = 0;
			} else if (y > height - 70) {
				y = height - 70;
			}
			RenderSystem.pushMatrix();
			RenderSystem.translated(x, y, -320.0D);
			renderItemInArmorSlot(mat, 0, 0, right);
			RenderSystem.popMatrix();

		}

	}

	private void renderItemInArmorSlot(MatrixStack mat, int xpos, int ypos, boolean right) {
		int i = 5;
		int xOffset = right ? -42 : 42;
		int xOffset2 = right ? 75 : 0;
		for (ItemStack item : this.mc.player.getEquipmentAndArmor()) {
			if (armType == ArmorType.ARMOR && i > 3) {
				i--;
				continue;
			}
			int Damage = IFArmorStatus.GetItemDamage(item);
			if (Damage >= 0) {
				String p, text = "";
				int tLength = -17;
				p = String.valueOf(item.getDamage());
				text = getDamageText(p, Damage);
				if (right)
					tLength = p.length() * 6 + 1;
	
				if (i == 5) {
					this.itemRenderer.renderItemAndEffectIntoGUI(item, xpos + xOffset + xOffset2, ypos);
					this.fontRenderer.drawStringWithShadow(mat, text, (xpos - tLength + xOffset + xOffset2), (ypos + 4),
							16777215);
				} else if (i == 4) {
					this.itemRenderer.renderItemAndEffectIntoGUI(item, xpos + xOffset + xOffset2, ypos + 18);
					this.fontRenderer.drawStringWithShadow(mat, text, (xpos - tLength + xOffset + xOffset2),
							(ypos + 18 + 4), 16777215);
				} else {
					this.itemRenderer.renderItemAndEffectIntoGUI(item, xpos + xOffset2, ypos + i * 18);
					this.fontRenderer.drawStringWithShadow(mat, text, (xpos - tLength + xOffset2), (ypos + i * 18 + 4),
							16777215);
				}
			} else if (i == 5) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation("icefeeling", armorTextures[i]));
				blit(mat, xpos + xOffset + xOffset2, ypos, 16.0F, 16.0F, 16, 16, 16, 16);
			} else if (i == 4) {
				this.mc.getTextureManager().bindTexture(new ResourceLocation(armorTextures[i]));
				blit(mat, xpos + xOffset + xOffset2, ypos + 18, 16.0F, 16.0F, 16, 16, 16, 16);
			} else {
				this.mc.getTextureManager().bindTexture(new ResourceLocation(armorTextures[i]));
				blit(mat, xpos + xOffset2, ypos + i * 18, 16.0F, 16.0F, 16, 16, 16, 16);
			}
			i--;
		}

	}

	private String getDamageText(String damageDisplayed, int damageItem) {
		
		String result = damageDisplayed;
		if (damageItem >= 100) {
			result = TextFormatting.GREEN + result;
		} else if (damageItem < 50) {
			result = TextFormatting.DARK_RED + result;
		} else if (damageItem <= 100) {
			result = TextFormatting.RED + result;
		} else if (damageItem <= 150) {
			result = TextFormatting.GOLD + result;
		} else if (damageItem <= 200) {
			result = TextFormatting.YELLOW + result;
		}
		return result;
	}

}
