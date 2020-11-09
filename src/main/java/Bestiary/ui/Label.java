package Bestiary.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class Label {
    protected String text;
    protected BitmapFont font;
    protected float x, y;
    protected Color color;

    protected float textWidth, textHeight;

    public Label(String text, BitmapFont font, float x, float y, Color color) {
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        this.color = color;

        this.textHeight = font.getLineHeight();
        this.textWidth = FontHelper.getSmartWidth(font, text, 100000, 30) / Settings.scale;
    }

    public void render(SpriteBatch sb) {
        FontHelper.renderFontLeftDownAligned(sb, font, text, x * Settings.scale, y * Settings.scale, color);
    }

    public float getTextWidth() { return textWidth; }
}
