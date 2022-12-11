package io.github.pingisfun.hycs.config;

import io.github.pingisfun.hycs.HyChatStatsMod;
import io.github.pingisfun.hycs.hud.TestHud;
import cc.polyfrost.oneconfig.config.Config;
import cc.polyfrost.oneconfig.config.annotations.HUD;
import cc.polyfrost.oneconfig.config.annotations.Switch;
import cc.polyfrost.oneconfig.config.data.Mod;
import cc.polyfrost.oneconfig.config.data.ModType;

public class ModConfig extends Config {

    @HUD(
            name = "Very cool HUD",
            subcategory = "Test"
    )
    public static TestHud testHud = new TestHud();

    @Switch(
            name = "Test",
            subcategory = "Test",
            size = 2
    )
    public static boolean test = true;

    public ModConfig() {
        super(new Mod(HyChatStatsMod.NAME, ModType.UTIL_QOL), HyChatStatsMod.MODID + ".json");
        initialize();
    }
}

