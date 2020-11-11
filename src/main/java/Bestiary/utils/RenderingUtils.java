package Bestiary.utils;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;

public class RenderingUtils {

    // Modified version can return null (instead of white) and can use newer colors
    // TODO: verify if the .cpy() copies are actually required (i doubt it, but original codebase does it)
    private static Color identifyColor(String word) {
        // note: fixed the word.length bug (original could crash on out of bounds)
        if (word.length() > 1 && word.charAt(0) == '#') {
            switch(word.charAt(1)) {
                // Currently:
                //    #w    white (cream)
                //    #g    gray (darker than the cream - 60% black)
                //    #r    red
                //    #d    debuff
                //    #u    buff ("upgrade")
                //    #b    block
                case 'w':
                    return Settings.CREAM_COLOR.cpy();
                case 'g':
                    return ExtraColors.OJB_GRAY_COLOR.cpy();
                case 'r':
                    return ExtraColors.OJB_RED_COLOR.cpy();
                case 'd':
                    return ExtraColors.OJB_DEBUFF_COLOR.cpy();
                case 'u':
                    return ExtraColors.OJB_BUFF_COLOR.cpy();
                case 'b':
                    return ExtraColors.OJB_BLOCK_COLOR.cpy();
                case 's':
                    return ExtraColors.OJB_SPECIAL_COLOR.cpy();
                case 'm':
                    return ExtraColors.rainbow();
                default:
                    return null;
                // NOTE: old version returned white; this returns null to let the modified smartText function handle it
                // a little bit better and more flexible
                //return Color.WHITE;
            }
        } else {
            return null;
        }
    }

    // Modified version of the base code to handle my own special font colors and rendering
    public static void renderSmartText(SpriteBatch sb, BitmapFont font, String msg, float x, float y, float lineWidth, float lineSpacing, Color baseColor) {
        if (msg != null) {
            float curWidth = 0.0F;
            float curHeight = 0.0F;

            GlyphLayout layout = FontHelper.layout;

            layout.setText(font, " ");
            float spaceWidth = layout.width;


            String[] var8 = msg.split(" ");
            int var9 = var8.length;

            for(int var10 = 0; var10 < var9; ++var10) {
                String word = var8[var10];
                if (word.equals("NL")) {
                    curWidth = 0.0F;
                    curHeight -= lineSpacing;
                } else if (word.equals("TAB")) {
                    curWidth += spaceWidth * 5.0F;
                } else {
                    Color color = identifyColor(word);

                    // No color specified for this word
                    if (color == null) {
                        font.setColor(baseColor);
                    }
                    // Use the result of the identify color method
                    else {
                        word = word.substring(2);
                        color.a = baseColor.a;
                        font.setColor(color);
                    }

                    layout.setText(font, word);
                    if (curWidth + layout.width > lineWidth) {
                        curHeight -= lineSpacing;
                        font.draw(sb, word, x, y + curHeight);
                        curWidth = layout.width + spaceWidth;
                    } else {
                        font.draw(sb, word, x + curWidth, y + curHeight);
                        curWidth += layout.width + spaceWidth;
                    }
                }
            }

            layout.setText(font, msg);
        }
    }
}
