package MobInfo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Move {
    @SerializedName("name") @Expose
    private String name;

    @SerializedName("effects") @Expose
    private List<MoveEffect> moveEffects = null;

    // --------------------------------------------------------------------------------
    // Getters / setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<MoveEffect> getMoveEffects() { return moveEffects; }
    public void setMoveEffects(List<MoveEffect> moveEffects) { this.moveEffects = moveEffects; }

    // --------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "Move{" +
                "name='" + name + '\'' +
                ", moveEffects=" + moveEffects +
                '}';
    }
}

