package dmz.faction.icefeeling.client.screens;

import net.minecraft.client.AbstractOption;
import net.minecraft.client.GameSettings;
import net.minecraft.client.gui.DialogTexts;
import net.minecraft.client.gui.screen.ConfirmOpenLinkScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.WithNarratorSettingsScreen;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.util.Util;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class IFDiscordScreen extends WithNarratorSettingsScreen {
	   private static final AbstractOption[] OPTIONS = new AbstractOption[]{AbstractOption.NARRATOR, AbstractOption.SHOW_SUBTITLES, AbstractOption.TEXT_BACKGROUND_OPACITY, AbstractOption.TEXT_BACKGROUND, AbstractOption.CHAT_OPACITY, AbstractOption.CHAT_LINE_SPACING, AbstractOption.CHAT_DELAY, AbstractOption.AUTO_JUMP, AbstractOption.TOGGLE_CROUCH, AbstractOption.TOGGLE_SPRINT, AbstractOption.SCREEN_EFFECTS_SCALE, AbstractOption.FOV_EFFECTS_SCALE};

   public IFDiscordScreen(Screen parentScreen, GameSettings settings) {
      super(parentScreen, settings, new TranslationTextComponent("options.accessibility.title"), OPTIONS);
   }

   protected void func_244718_c() {
      this.addButton(new Button(this.width / 2 - 155, this.height - 27, 150, 20, new TranslationTextComponent("options.accessibility.link"), (p_244738_1_) -> {
         this.minecraft.setScreen(new ConfirmOpenLinkScreen((p_244739_1_) -> {
            if (p_244739_1_) {
               Util.getPlatform().openUri("https://facebook.com");
            }

            this.minecraft.setScreen(this);
         }, "https://facebook.com", true));
      }));
      this.addButton(new Button(this.width / 2 + 5, this.height - 27, 150, 20, DialogTexts.GUI_DONE, (p_244737_1_) -> {
         this.minecraft.setScreen(this.lastScreen);
      }));
   }
}
