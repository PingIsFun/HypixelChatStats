package io.github.pingisfun.hycs.config;

import io.github.pingisfun.hycs.HyChatStatsMod;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.annotations.Text;
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
            name = "Bedwars Game Stats",
            category = "Auto"
    )
    public static boolean isBedwarsGameStatsEnabled = false;
    public ModConfig() {
        super(new Mod(HyChatStatsMod.NAME, ModType.HYPIXEL), HyChatStatsMod.MODID + ".json");
        initialize();
    }
}

