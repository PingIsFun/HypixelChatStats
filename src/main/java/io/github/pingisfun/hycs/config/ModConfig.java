package io.github.pingisfun.hycs.config;

import io.github.pingisfun.hycs.HyChatStatsMod;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.*;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class ModConfig extends Config {

    @Text(
            name = "Hypixel API Key",
            secure = true, multiline = false
    )
    public static String hypixelApiKey = "";

    // Auto
    @Switch(
            name = "Enabled",
            category = "Auto", subcategory = "Bedwars Game Stats")
    public static boolean isBedwarsGameStatsEnabled = false;

    @Slider(
            min = 0, max = 10, step = 1,
            name = "Delay (in seconds)",
            category = "Auto", subcategory = "Bedwars Game Stats"
    )
    public static int bedwarsGameStatsDelay = 3;
    public ModConfig() {
        super(new Mod(HyChatStatsMod.NAME, ModType.HYPIXEL), HyChatStatsMod.MODID + ".json");
        initialize();
    }
}

