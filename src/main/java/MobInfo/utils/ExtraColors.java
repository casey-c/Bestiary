package MobInfo.utils;

import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.Settings;

public class ExtraColors {
//    public static final Color OJB_GRAY_COLOR = new Color(0.6f, 0.6f, 0.6f, 1.0f);
//    public static final Color OJB_BUFF_COLOR = new Color(0.596f, 0.306f, 0.639f, 1.0f);
//    public static final Color OJB_DEBUFF_COLOR = new Color(1.0f, 0.498f, 0.0f, 1.0f);
//    public static final Color OJB_DAMAGE_COLOR = new Color();
//    public static final Color OJB_BLOCK_COLOR = Settings.BLUE_TEXT_COLOR.cpy();
//
//
//    public static final Color OJB_SPECIAL_COLOR = new Color(0.302f, 0.686f, 0.29f, 1.0f);

    public static final Color OJB_DIM_COLOR = new Color(0.0f, 0.0f, 0.0f, 0.75f);

    public static final Color OJB_GRAY_COLOR = new Color(0.6f, 0.6f, 0.6f, 1.0f);
    public static final Color OJB_DARK_GRAY_COLOR = new Color(0.2f, 0.2f, 0.2f, 1.0f);

    public static final Color OJB_BUFF_COLOR = new Color(0.596f, 0.306f, 0.639f, 1.0f);
    public static final Color OJB_DEBUFF_COLOR = new Color(1.0f, 0.498f, 0.0f, 1.0f);
    public static final Color OJB_BLOCK_COLOR = new Color(0.337f, 0.612f, 0.843f, 1.0f);
    public static final Color OJB_DAMAGE_COLOR = new Color(0.91f, 0.22f, 0.227f, 1.0f);
    public static final Color OJB_SPECIAL_COLOR = new Color(0.302f, 0.686f, 0.29f, 1.0f);

    public static final Color OJB_RED_COLOR = Settings.RED_TEXT_COLOR.cpy();

    // --------------------------------------------------------------------------------

    public static Color stringToColor(String s) {
        switch (s) {
            case "DEBUFF":
                return ExtraColors.OJB_DEBUFF_COLOR;
            case "BUFF":
                return ExtraColors.OJB_BUFF_COLOR;
            case "DAMAGE":
                return ExtraColors.OJB_DAMAGE_COLOR;
            case "BLOCK":
                return ExtraColors.OJB_BLOCK_COLOR;
            case "SKIP":
                return ExtraColors.OJB_GRAY_COLOR;
            case "SPECIAL":
                return ExtraColors.OJB_SPECIAL_COLOR;
            default:
                System.out.println("OJB: invalid color at some point (bug in monsters.JSON!)");
                System.out.println("OJB: invalid color is: " + s);
                return ExtraColors.OJB_DARK_GRAY_COLOR;
        }
    }
}
