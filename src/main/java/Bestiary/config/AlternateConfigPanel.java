package Bestiary.config;

import Bestiary.utils.ExtraColors;
import Bestiary.utils.ExtraFonts;
import Bestiary.utils.RenderingUtils;
import basemod.ModLabeledToggleButton;
import basemod.ModPanel;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class AlternateConfigPanel extends ModPanel {
        private static Texture TEX_BG = new Texture("Bestiary/config.png");

        public AlternateConfigPanel() {
            addUIElement(new ModLabeledToggleButton("Require holding Shift to open overlay (prevents accidental openings)",
                    395,
                    668,
                    ExtraColors.OJB_GRAY_COLOR,
                    FontHelper.tipBodyFont,
                    Config.requiresShift(),
                    this,
                    modLabel -> {},
                    modToggleButton -> {
                        Config.setRequiresShift(modToggleButton.enabled);
                    }
            ));
        }

        @Override
        public void renderBg(SpriteBatch sb) {
            // Dim to diminish the rest of the config menu
            sb.setColor(ExtraColors.OJB_DIM_COLOR);
            sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

            // Draw our screen texture in the center
            sb.setColor(Color.WHITE);
            sb.draw(TEX_BG,
                    (Settings.WIDTH - (TEX_BG.getWidth() * Settings.scale)) * 0.5f,
                    (Settings.HEIGHT - (TEX_BG.getHeight() * Settings.scale)) * 0.5f,
                    TEX_BG.getWidth() * Settings.scale,
                    TEX_BG.getHeight() * Settings.scale
            );
        }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        // Render title
        FontHelper.renderFontLeftDownAligned(sb, ExtraFonts.overlayTitleFont(), "Bestiary", 395.0f * Settings.scale, 825.0f * Settings.scale, Settings.GOLD_COLOR);

        if (Config.requiresShift()) {
            RenderingUtils.renderSmartText(sb,
                    FontHelper.tipBodyFont,
                    "#mBestiary lets you see which moves a monster can use! NL NL Open up the overlay by #wShift #wRight #wClicking a monster while in combat. You can close this overlay with just a right click. NL NL Bestiary supports all vanilla mobs for all ascension levels. Please let me know on the Steam Workshop page or on the Github issues page if you find any problems. Thanks!",
                    1133 * Settings.scale,
                    702 * Settings.scale,
                    400 * Settings.scale,
                    32 * Settings.scale,
                    ExtraColors.OJB_GRAY_COLOR);

        }
        else {
            RenderingUtils.renderSmartText(sb,
                    FontHelper.tipBodyFont,
                    "#mBestiary lets you see which moves a monster can use! NL NL Open up the overlay by #wRight #wClicking a monster while in combat. You can close this overlay with another right click. NL NL Bestiary supports all vanilla mobs for all ascension levels. Please let me know on the Steam Workshop page or on the Github issues page if you find any problems. Thanks!",
                    1133 * Settings.scale,
                    702 * Settings.scale,
                    400 * Settings.scale,
                    32 * Settings.scale,
                    ExtraColors.OJB_GRAY_COLOR);

        }
    }
}
