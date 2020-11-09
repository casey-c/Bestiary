package MobInfo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AscensionMoveSet {
    @SerializedName("min_asc") @Expose
    private int minAsc;

    @SerializedName("desc") @Expose
    private String desc;

    @SerializedName("hp") @Expose
    private String hp;

    @SerializedName("notes") @Expose
    private String notes = "";

    @SerializedName("moves") @Expose
    private List<Move> moves = null;

    // --------------------------------------------------------------------------------
    // Getters / setters

    public int getMinAsc() { return minAsc; }
    public void setMinAsc(int minAsc) { this.minAsc = minAsc; }

    public String getDesc() { return desc; }
    public void setDesc(String desc) { this.desc = desc; }

    public String getHp() { return hp; }
    public void setHp(String hp) { this.hp = hp; }

    public boolean hasNotes() { return !notes.isEmpty(); }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public List<Move> getMoves() { return moves; }
    public void setMoves(List<Move> moves) { this.moves = moves; }

    // --------------------------------------------------------------------------------


    @Override
    public String toString() {
        return "AscensionMoveSet{" +
                "minAsc=" + minAsc +
                ", desc='" + desc + '\'' +
                ", hp='" + hp + '\'' +
                ", hasNotes='" + hasNotes() + '\'' +
                ", notes='" + notes + '\'' +
                ", moves=" + moves +
                '}';
    }
}
