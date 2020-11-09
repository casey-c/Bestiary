package MobInfo.ui;

import MobInfo.database.AscensionMoveSet;
import MobInfo.database.MonsterInfo;
import MobInfo.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;

import java.util.ArrayList;
import java.util.List;

public class MonsterInfoRenderHelper {
    private MonsterInfo currMonster;
    private ArrayList<Label> labels = new ArrayList<>();

    public MonsterInfoRenderHelper(MonsterInfo monster) {
        setCurrMonster(monster);
    }

    // --------------------------------------------------------------------------------
    // UI layout information (WIP - to be tweaked as needed)

    private static final float movesLeft = 315.0f;
    private static final float titleBottom = 877.0f;

    private static final float firstMoveBottom = 775.0f;
    private static final float movesVertSpacing = 30.0f;
    private static final float moveEffectHorizSpacing = 20.0f;

    private static final float descLeft = 1086.0f;
    private static final float descTop = 847.0f;

    private static final float descWidth = 534.0f;
    private static final float descSpacing = 20.0f;

    // --------------------------------------------------------------------------------

    // Loads the proper details about the selected monster and caches them for rendering (so we don't have to recompute
    //   where to put all the labels and such each frame)
    public void setCurrMonster(MonsterInfo monster) {
        this.currMonster = monster;
        labels.clear();


        // Name of the mob
        labels.add(new Label(currMonster.getName(), FontHelper.bannerFont, movesLeft, titleBottom, Settings.GOLD_COLOR));

        if (CardCrawlGame.isInARun()) {
            int asc_level = (AbstractDungeon.isAscensionMode) ? AbstractDungeon.ascensionLevel : 0;
            AscensionMoveSet moveSet = monster.getApplicableMoveSet(asc_level);

            if (moveSet != null) {
                // TODO: all the moves for this particular ascension level
                labels.add(new SmartLabel(moveSet.getDesc(),
                        FontHelper.tipBodyFont,
                        descLeft,
                        descTop,
                        descWidth,
                        descSpacing,
                        ExtraColors.OJB_GRAY_COLOR));
            }
        }

    }


    public void render(SpriteBatch sb) {
        for (Label l : labels)
            l.render(sb);
    }
}
