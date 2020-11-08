package MobInfo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonsterInfo {

    @SerializedName("name") @Expose
    private String name;

    @SerializedName("id") @Expose
    private String id;

    @SerializedName("ai") @Expose
    private List<AscensionMoveSet> moveSets = null;

    // --------------------------------------------------------------------------------
    // Getters / setters

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public List<AscensionMoveSet> getMoveSets() { return moveSets; }
    public void setMoveSets(List<AscensionMoveSet> moveSets) { this.moveSets = moveSets; }

    // --------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "MonsterInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", movesets=" + moveSets +
                '}';
    }
}
