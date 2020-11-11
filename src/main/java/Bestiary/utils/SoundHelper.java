package Bestiary.utils;

import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class SoundHelper {
    public static void closeSound() {
        CardCrawlGame.sound.play("MAP_CLOSE");
    }

    public static void openSound() {
        int roll = MathUtils.random(3);

        switch (roll) {
            case 0: {
                CardCrawlGame.sound.play("MAP_SELECT_1");
                break;
            }
            case 1: {
                CardCrawlGame.sound.play("MAP_SELECT_2");
                break;
            }
            case 2: {
                CardCrawlGame.sound.play("MAP_SELECT_3");
                break;
            }
            default: {
                CardCrawlGame.sound.play("MAP_SELECT_4");
            }
        }
    }
}
