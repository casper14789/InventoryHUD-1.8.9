package com.example.backpackhud;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class HUDSettings {
    public boolean hudVisible = true;
    public int hudX = 10;
    public int hudY = 10;
    public int slotSize = 18;

    private static final File configFile = new File("config/backpackhud.json");

    public void save() {
        try {
            configFile.getParentFile().mkdirs();
            try (Writer writer = new FileWriter(configFile)) {
                new GsonBuilder().setPrettyPrinting().create().toJson(this, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HUDSettings load() {
        if (configFile.exists()) {
            try (Reader reader = new FileReader(configFile)) {
                return new Gson().fromJson(reader, HUDSettings.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new HUDSettings(); // default
    }
}
