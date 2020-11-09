package MobInfo.ui;

import MobInfo.database.AscensionMoveSet;
import MobInfo.database.MonsterInfo;
import MobInfo.database.Move;
import MobInfo.database.MoveEffect;
import MobInfo.utils.ExtraColors;
import MobInfo.utils.ExtraFonts;
import MobInfo.utils.RenderingUtils;
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
    private static final float titleBottom = 886.0f;

    private static final float firstMoveBottom = 780.0f;
    private static final float movesVertSpacing = 80.0f;

    private static final float firstMoveEffectLeft = movesLeft + 170.0f;
    private static final float moveEffectHorizSpacing = 50.0f;

    private static final float descLeft = 1086.0f;
    private static final float descTop = 847.0f;

    private static final float descWidth = 534.0f;
    private static final float descSpacing = 30.0f;

    // --------------------------------------------------------------------------------

    // Loads the proper details about the selected monster and caches them for rendering (so we don't have to recompute
    //   where to put all the labels and such each frame)
    public void setCurrMonster(MonsterInfo monster) {
        this.currMonster = monster;
        labels.clear();

        if (CardCrawlGame.isInARun()) {
            int asc_level = (AbstractDungeon.isAscensionMode) ? AbstractDungeon.ascensionLevel : 0;
            AscensionMoveSet moveSet = monster.getApplicableMoveSet(asc_level);

            if (moveSet != null) {
                // Name of the mob (TODO: better [custom?] font)
                labels.add(new Label(currMonster.getName() + "  " + moveSet.getHp(), ExtraFonts.overlayTitleFont(), movesLeft, titleBottom, Settings.GOLD_COLOR));

                // AI description
                labels.add(new SmartLabel(moveSet.getDesc(),
                        FontHelper.tipBodyFont,
                        descLeft,
                        descTop,
                        descWidth,
                        descSpacing,
                        ExtraColors.OJB_GRAY_COLOR));

                float movesY = firstMoveBottom;

                for (Move m : moveSet.getMoves()) {
                    // Name of the move
                    labels.add(new Label(m.getName(), FontHelper.tipBodyFont, movesLeft, movesY, Settings.CREAM_COLOR));

                    // All its effects
                    float effectX = firstMoveEffectLeft;
                    System.out.println("Starting effectX: " + effectX);
                    for (MoveEffect e : m.getMoveEffects()) {
                        Label effectLabel = new Label(e.getName(), FontHelper.tipBodyFont, effectX, movesY, ExtraColors.stringToColor(e.getColor()));
                        System.out.println("Made effect label " + e.getName() + " and it has width " + effectLabel.getTextWidth());
                        effectX += effectLabel.getTextWidth() + moveEffectHorizSpacing;
                        System.out.println("Final effectX: " + effectX + " after spacing " + moveEffectHorizSpacing);
                        labels.add(effectLabel);
                    }

                    movesY -= movesVertSpacing;
                }
            }
            else {
                // Should show some placeholder text I guess
            }
        }

    }


    public void render(SpriteBatch sb) {
        for (Label l : labels)
            l.render(sb);
    }
}
