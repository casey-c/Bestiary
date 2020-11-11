package Bestiary.ui;

import Bestiary.database.MonsterDatabase;
import Bestiary.database.MonsterInfo;
import Bestiary.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.ImageMaster;

public class MonsterOverlay {
    private static final Texture TEX_BG = new Texture("Bestiary/screen.png");

    private MonsterDatabase db;
    private MonsterInfoRenderHelper helper;

    public MonsterOverlay(MonsterDatabase db) {
        this.db = db;
    }

    public void setCurrMonsterByID(String id) {
        this.helper = new MonsterInfoRenderHelper(db.getByID(id));
    }

    private void renderBackground(SpriteBatch sb) {
        // TODO: fade in / out?
        sb.setColor(ExtraColors.OJB_DIM_COLOR);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0, 0, Settings.WIDTH, Settings.HEIGHT);

        sb.setColor(Color.WHITE);
        sb.draw(TEX_BG,
                (Settings.WIDTH - (TEX_BG.getWidth() * Settings.scale)) * 0.5f,
                (Settings.HEIGHT - (TEX_BG.getHeight() * Settings.scale)) * 0.5f,
                TEX_BG.getWidth() * Settings.scale,
                TEX_BG.getHeight() * Settings.scale
        );
    }

    private void renderForeground(SpriteBatch sb) {
        if (helper == null)
            helper = new MonsterInfoRenderHelper(MonsterInfo.getDefault());

        helper.render(sb);
    }

    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
