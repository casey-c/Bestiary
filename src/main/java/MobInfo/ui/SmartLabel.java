package MobInfo.ui;

import MobInfo.utils.RenderingUtils;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class SmartLabel extends Label {
    private float lineWidth, lineSpacing;

    public SmartLabel(String text, BitmapFont font, float x, float y, float lineWidth, float lineSpacing, Color color) {
        super(text,font, x, y, color);
        this.lineWidth = lineWidth;
        this.lineSpacing = lineSpacing;

        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, lineWidth, lineSpacing);
    }

    @Override
    public void render(SpriteBatch sb) {
        RenderingUtils.renderSmartText(sb, font, text, x * Settings.scale, y * Settings.scale, lineWidth, lineSpacing, color);
    }
}
