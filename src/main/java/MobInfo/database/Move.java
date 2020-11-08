package MobInfo.database;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Move {

    @SerializedName("name") @Expose
    private String name;

    @SerializedName("effects") @Expose
    private List<Effect> effects = null;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public List<Effect> getEffects() { return effects; }
    public void setEffects(List<Effect> effects) { this.effects = effects; }

}

