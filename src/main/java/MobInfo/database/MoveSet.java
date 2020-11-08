package MobInfo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoveSet {

    @SerializedName("min_asc") @Expose
    private Integer minAsc;

    @SerializedName("ai") @Expose
    private String ai;

    @SerializedName("moves") @Expose
    private List<Move> moves = null;

    public Integer getMinAsc() { return minAsc; }
    public void setMinAsc(Integer minAsc) { this.minAsc = minAsc; }
    public String getAi() { return ai; }
    public void setAi(String ai) { this.ai = ai; }
    public List<Move> getMoves() { return moves; }
    public void setMoves(List<Move> moves) { this.moves = moves; }
}
