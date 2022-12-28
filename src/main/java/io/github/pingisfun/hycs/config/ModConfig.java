package io.github.pingisfun.hycs.config;

import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Info;
import cc.polyfrost.oneconfig.config.annotations.Slider;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.InfoType;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;
import io.github.pingisfun.hycs.HyChatStatsMod;

public class ModConfig extends Config {
//    @NonProfileSpecific
//    @Text(
//            name = "Hypixel API Key",
//            secure = true, multiline = false
//    )
//    public static String hypixelApiKey = "";

    // Auto
    /// Bedwars Game Stats
    @Info(text = "Sends your game statistics (number of kills, final kills & beds broken) to chat/party chat (display's them in chat in solos)",
            size = 2, type = InfoType.INFO, category = "Auto", subcategory = "Bedwars Game Stats")
    private static boolean ignoredBedwarsGameStatsInfo;
    @Switch(
            name = "Enabled",
            category = "Auto", subcategory = "Bedwars Game Stats")
    public static boolean isBedwarsGameStatsEnabled = false;
    @Switch(
            name = "Enabled in solo",
            category = "Auto", subcategory = "Bedwars Game Stats")
    public static boolean isBedwarsGameStatsEnabledSolo = false;
    @Slider(
            min = 0, max = 10, step = 1,
            name = "Chat send delay (in seconds)",
            category = "Auto", subcategory = "Bedwars Game Stats"
    )
    public static int bedwarsGameStatsDelay = 3;
    @Slider(
            min = 0, max = 40,
            name = "Read delay (in ticks) 1 second = 20 ticks",
            description = "Time after which the stats are read from tab (increase if the last kill isn't being count)",
            category = "Auto", subcategory = "Bedwars Game Stats"
    )
    public static int bedwarsGameStatsTabReadDelay = 15;



    public ModConfig() {
        super(new Mod(HyChatStatsMod.NAME, ModType.HYPIXEL), HyChatStatsMod.MODID + ".json");
        initialize();
    }
}

