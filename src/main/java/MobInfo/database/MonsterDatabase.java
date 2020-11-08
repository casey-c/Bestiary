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
                    monsters.put(monster.getId(), monster);

                    System.out.println("Monster: " + monster);
                    System.out.println();
                }

                System.out.println("Monsters.size is " + monsters.size());
            }

        } catch (IOException e) {
            System.out.println("Failed to load resource stream to string");
            e.printStackTrace();
        }
    }
}
