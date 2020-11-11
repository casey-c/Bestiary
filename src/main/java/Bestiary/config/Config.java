package Bestiary.config;

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;

import java.io.IOException;
import java.util.Properties;

@SpireInitializer
public class Config {
    private Properties defaults = new Properties();
    private SpireConfig spireConfig;

    private static Config instance;

    public static void initialize() {
        instance = new Config();
    }

    public Config() {
        defaults.put("requireShift", Boolean.toString(true));

        try {
            spireConfig = new SpireConfig("Bestiary", "config", defaults);
            spireConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setRequiresShift(boolean val) {
        System.out.println("Set requires shift to " + val);

        instance.spireConfig.setBool("requireShift", val);

        try {
            instance.spireConfig.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean requiresShift() {
        return instance.spireConfig.getBool("requireShift");
    }

    public static void setupConfigMenu() {
        BaseMod.registerModBadge(new Texture("Bestiary/badge.png"),
                "Bestiary",
                "ojb",
                "Right click monsters to open up an overlay showing their AI patterns. Right click again to close the overlay",
                new AlternateConfigPanel());

    }
}
