package Bestiary.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
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

    public AscensionMoveSet getApplicableMoveSet(int asc_level) {
        int max = -1;
        AscensionMoveSet target = null;

        for (AscensionMoveSet moveset : moveSets) {
            int curr = moveset.getMinAsc();
            // TODO: need to verify if it is < or <= here probably
            System.out.println("Checking if the moveset with asc level " + curr + " is applicable here at " + asc_level);
            if (curr > max && curr <= asc_level) {
                max = curr;
                target = moveset;
            }
        }

        if (target == null) {
            System.out.println("ERROR: returning a null value - this shouldn't happen!");
        }

        return target;
    }

    // --------------------------------------------------------------------------------

    @Override
    public String toString() {
        return "MonsterInfo{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", movesets=" + moveSets +
                '}';
    }

    public void print() {
        System.out.println(toString());
    }

    public static MonsterInfo getDefault() {
        MonsterInfo defaultMob = new MonsterInfo();
        defaultMob.name = "404 Monster Not Found";
        defaultMob.id = "null";
        defaultMob.moveSets = new ArrayList<>();
        return defaultMob;
    }
}
