package Bestiary.ui;

import Bestiary.database.MonsterDatabase;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;

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
        sb.setColor(Color.WHITE);
        sb.draw(TEX_BG,
                (Settings.WIDTH - (TEX_BG.getWidth() * Settings.scale)) * 0.5f,
                (Settings.HEIGHT - (TEX_BG.getHeight() * Settings.scale)) * 0.5f,
                TEX_BG.getWidth() * Settings.scale,
                TEX_BG.getHeight() * Settings.scale
        );
    }

    private void renderForeground(SpriteBatch sb) {
        if (helper != null)
            helper.render(sb);
    }

    public void render(SpriteBatch sb) {
        renderBackground(sb);
        renderForeground(sb);
    }
}
