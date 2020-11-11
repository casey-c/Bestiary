package Bestiary;

import Bestiary.database.MonsterDatabase;
import Bestiary.ui.MonsterOverlay;
import Bestiary.utils.SoundHelper;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.PostRenderSubscriber;
import basemod.interfaces.PostUpdateSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

@SpireInitializer
public class BestiaryMod implements PostInitializeSubscriber, RenderSubscriber {
    private MonsterDatabase db;
    private MonsterOverlay overlay;

    private static BestiaryMod instance;

    public static boolean showOverlay = false;
    private boolean mouseDownRight = false;

    // --------------------------------------------------------------------------------

    public static void initialize() {
        instance = new BestiaryMod();
    }

    public BestiaryMod() {
        BaseMod.subscribe(this);
    }


    @Override
    public void receivePostInitialize() {
        System.out.println("Mob Info init, creating database");

        db = new MonsterDatabase();
        overlay = new MonsterOverlay(db);
        //BaseMod.addTopPanelItem(new DebugTopItem());
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
        if (CardCrawlGame.isInARun() && showOverlay) {
            instance.overlay.render(sb);
        }
    }

    // --------------------------------------------------------------------------------

    private void handleRightClick() {
        if (showOverlay) {
            SoundHelper.closeSound();
            showOverlay = false;
            return;
        }

        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room != null && room.monsters != null && room.monsters.hoveredMonster != null) {
            String id = room.monsters.hoveredMonster.id;
            System.out.println("Currently hovered on: " + id);

            SoundHelper.openSound();
            overlay.setCurrMonsterByID(id);
            showOverlay = true;
        }

    }

    public static void update() {
        if (!CardCrawlGame.isInARun())
            return;

        // Right clicks
        if (InputHelper.isMouseDown_R) {
            instance.mouseDownRight = true;
        } else {
            // We already had the mouse down, and now we released, so fire our right click event
            if (instance.mouseDownRight) {
                instance.handleRightClick();
                instance.mouseDownRight = false;
            }
        }
    }


//    @Override
//    public void receivePostUpdate() {
//        if (!CardCrawlGame.isInARun())
//            return;
//
//        // Right clicks
//        if (InputHelper.isMouseDown_R) {
//            mouseDownRight = true;
//        } else {
//            // We already had the mouse down, and now we released, so fire our right click event
//            if (mouseDownRight) {
//                handleRightClick();
//                mouseDownRight = false;
//            }
//        }
//    }
}
