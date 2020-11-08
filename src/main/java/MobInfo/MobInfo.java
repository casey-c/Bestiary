package MobInfo;

import MobInfo.database.MonsterDatabase;
import basemod.BaseMod;
import basemod.interfaces.PostInitializeSubscriber;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.JsonObject;

@SpireInitializer
public class MobInfo implements PostInitializeSubscriber {
    MonsterDatabase db;

    public static void initialize() { new MobInfo(); }

    public MobInfo() {
        BaseMod.subscribe(this);
    }

    @Override
    public void receivePostInitialize() {
        System.out.println("Mob Info init, creating database");

        db = new MonsterDatabase();
        db.toJsonObject();
    }
}
