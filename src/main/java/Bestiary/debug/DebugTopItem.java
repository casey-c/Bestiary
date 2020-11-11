package Bestiary.debug;

import Bestiary.BestiaryMod;
import Bestiary.ui.MonsterOverlay;
import Bestiary.utils.KeyHelper;
import basemod.TopPanelItem;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;

import java.util.ArrayList;

public class DebugTopItem extends TopPanelItem {
    private static final Texture image = new Texture("Bestiary/debug.png");

    private boolean showOverlay = false;
    private MonsterOverlay overlay;

    private ArrayList<String> monsterKeys;
    private int index = 0;

    public DebugTopItem() {
        super(image, "Bestiary_DebugItem");
        overlay = new MonsterOverlay(BestiaryMod.db);
        monsterKeys = BestiaryMod.db.getAllIDs();
        //overlay.setCurrMonsterByID("null");
    }

    @Override
    public void render(SpriteBatch sb) {
        super.render(sb);

        if (CardCrawlGame.isInARun()) {

            if (showOverlay)
                overlay.render(sb);
        }
    }

    @Override
    protected void onClick() {

        if (KeyHelper.isShiftPressed()) {
            System.out.println("Debug item shift+clicked");

            showOverlay = !showOverlay;
        }
        else if (KeyHelper.isAltPressed()) {
            System.out.println("Debug item alt+clicked");

            index -= 2;
            if (index < 0) index = 0;
        }
        else {
            System.out.println("Debug item clicked");
            if (!showOverlay) {
                showOverlay = true;
            }
            else {
                // Cycle through the next item in the bestiary
                int next = index++ % monsterKeys.size();
                System.out.println("Looking at index " + next + " / " + monsterKeys.size() + " (" + monsterKeys.get(next) + ")");

                overlay.setCurrMonsterByID(monsterKeys.get(next));
            }
        }
    }
}
