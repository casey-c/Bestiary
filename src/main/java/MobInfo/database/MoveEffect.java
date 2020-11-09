package MobInfo.database;

import MobInfo.utils.ExtraColors;
import com.badlogic.gdx.graphics.Color;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.megacrit.cardcrawl.core.Settings;

public class MoveEffect {
    @SerializedName("name") @Expose
    private String name;

    @SerializedName("color") @Expose
    private String color;

    // --------------------------------------------------------------------------------
    // Getters / setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    // --------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "MoveEffect{" +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
