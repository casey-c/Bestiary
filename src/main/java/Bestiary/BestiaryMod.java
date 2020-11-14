package Bestiary;

import Bestiary.config.Config;
import Bestiary.database.MonsterDatabase;
import Bestiary.ui.MonsterOverlay;
import Bestiary.utils.KeyHelper;
import Bestiary.utils.SoundHelper;
import basemod.BaseMod;
import basemod.ReflectionHacks;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
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

        Config.setupConfigMenu();
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

        // Don't open if we require shift
        if (Config.requiresShift() && !KeyHelper.isShiftPressed()) {
            return;
        }

        AbstractRoom room = AbstractDungeon.getCurrRoom();
        if (room != null && room.monsters != null && room.monsters.hoveredMonster != null) {
            openOverlayForMonster(room.monsters.hoveredMonster.id);
        }
    }

    private void openOverlayForMonster(String id) {
        System.out.println("Currently hovered on: " + id);
        SoundHelper.openSound();
        overlay.setCurrMonsterByID(id);
        showOverlay = true;
    }

    public static AbstractMonster intentRenderingMonster = null;

    public static void update() {
        if (!CardCrawlGame.isInARun()) {
            intentRenderingMonster = null;
            return;
        }

        // Controller support
        if (Settings.isControllerMode) {
            if (CInputActionSet.drawPile.isJustPressed()) {
                if (intentRenderingMonster != null) {
                    if (!showOverlay)
                        instance.openOverlayForMonster(intentRenderingMonster.id);
                }
            }

            if (CInputActionSet.cancel.isJustPressed()) {
                if (showOverlay) {
                    SoundHelper.closeSound();
                    showOverlay = false;
                }
            }
        }

        // Reset (it will update on the next rendering call anyway)
        intentRenderingMonster = null;

        // No controller / normal right clicks
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
}
