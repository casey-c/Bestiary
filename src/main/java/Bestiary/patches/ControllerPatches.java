package Bestiary.patches;

import Bestiary.BestiaryMod;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ControllerPatches {
    @SpirePatch(
            clz = AbstractMonster.class,
            method = "renderTip",
            paramtypez = SpriteBatch.class
    )
    public static class RenderIntentsPatch {
        @SpirePrefixPatch
        public static void Prefix(AbstractMonster monster, SpriteBatch _sb) {
            BestiaryMod.intentRenderingMonster = monster;
        }
    }
}
