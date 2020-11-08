package MobInfo.database;

import com.google.gson.*;
import com.google.gson.internal.LinkedTreeMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MonsterDatabase {
    private HashMap<String, MonsterInfo> monsters = new HashMap<>(); // a graveyard smash

    public MonsterDatabase() {
        load();
    }

    // Builds a string from an input stream
    private static String resourceStreamToString(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        String read;
        while ((read=br.readLine()) != null) {
            sb.append(read);
        }

        br.close();
        return sb.toString();
    }

    // Pretty sure can only call after postInitialize callback or it won't find the .json (but I honestly didn't test it)
    private void load() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("MobInfo/monsters.json");

        if (in == null) {
            System.out.println("ERROR: failed to load monsters.json (not found?)");
            return;
        }

        try {
            String content = resourceStreamToString(in);

            JsonObject o = new JsonParser().parse(content).getAsJsonObject();
            Gson gson = new Gson();

            if (o.has("monsters") && o.get("monsters").isJsonArray()) {
                JsonArray arr = o.getAsJsonArray("monsters");
                for (JsonElement elt : arr) {
                    MonsterInfo monster = gson.fromJson(elt, MonsterInfo.class);
                    //System.out.println("Made a monster?: " + monster);
                    monsters.put(monster.getId(), monster);
                }

//                ArrayList<LinkedTreeMap> monsterList = gson.fromJson(o.get("monsters").toString(), ArrayList.class);
//
//                monsterList.forEach(m -> {
//                    System.out.println("Monster m: " + m.get("name"));
//
//                    //System.out.println("Monster: " + m.getName());
//                });

//                System.out.println("monster list item class: ");
//                System.out.println(monsterList.get(0).getClass());

                //monsterList.forEach(m -> monsters.put(m.id, m));

                System.out.println("Monsters.size is " + monsters.size());
            }

        } catch (IOException e) {
            System.out.println("Failed to load resource stream to string");
            e.printStackTrace();
        }
    }

    public JsonObject toJsonObject() {
        JsonObject o = new JsonObject();

        JsonArray monsterArray = new JsonArray();

        for (MonsterInfo m : monsters.values()) {
            JsonObject mo = new JsonObject();
            mo.add("name", new JsonPrimitive(m.getName()));
            mo.add("id", new JsonPrimitive(m.getId()));

            JsonArray movesets = new JsonArray();

            for (MoveSet s : m.getMovesets()) {
                JsonObject moveset = new JsonObject();
                moveset.add("min_asc", new JsonPrimitive(s.getMinAsc()));
                moveset.add("desc", new JsonPrimitive(s.getAi()));
                moveset.add("hp", new JsonPrimitive(m.getHp()));

                // Moves
                JsonArray ms = new JsonArray();

                for (Move mv : s.getMoves()) {
                    JsonObject moveobj = new JsonObject();
                    moveobj.add("name", new JsonPrimitive(mv.getName()));

                    JsonArray es = new JsonArray();
                    for (Effect e : mv.getEffects()) {
                        JsonObject eobj = new JsonObject();
                        eobj.add("name", new JsonPrimitive(e.getName()));
                        eobj.add("color", new JsonPrimitive(e.getColor()));
                        es.add(eobj);
                    }

                    moveobj.add("moves", es);
                    ms.add(moveobj);
                }

                moveset.add("moves", ms);
                movesets.add(moveset);
            }

            mo.add("ai", movesets);

            monsterArray.add(mo);
        }

        o.add("monsters", monsterArray);

        System.out.println("---------");
        System.out.println("Made json object:");
        System.out.println(o.toString());
        System.out.println("---------");
        return o;
    }
}
