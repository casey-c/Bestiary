package MobInfo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MonsterInfo {

    @SerializedName("name") @Expose
    private String name;

    @SerializedName("id") @Expose
    private String id;

    @SerializedName("hp") @Expose
    private String hp;

    @SerializedName("movesets") @Expose
    private List<MoveSet> movesets = null;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHp() { return hp; }
    public void setHp(String hp) { this.hp = hp; }

    public List<MoveSet> getMovesets() { return movesets; }
    public void setMovesets(List<MoveSet> movesets) { this.movesets = movesets; }

    @Override
    public String toString() {
        return "MonsterInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", hp='" + hp + '\'' +
                ", movesets=" + movesets +
                '}';
    }
}
