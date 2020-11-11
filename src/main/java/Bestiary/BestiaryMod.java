package Bestiary;

import Bestiary.database.MonsterDatabase;
import Bestiary.debug.DebugTopItem;
import Bestiary.ui.MonsterOverlay;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.RenderSubscriber;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.core.CardCrawlGame;

@SpireInitializer
public class BestiaryMod implements PostInitializeSubscriber, RenderSubscriber {
    public static MonsterDatabase db;

    public static void initialize() { new BestiaryMod(); }

    public BestiaryMod() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receivePostInitialize() {
        System.out.println("Mob Info init, creating database");

        db = new MonsterDatabase();
        BaseMod.addTopPanelItem(new DebugTopItem());
    }

    @Override
    public void receiveRender(SpriteBatch sb) {
    }
}
