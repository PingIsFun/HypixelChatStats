package io.github.pingisfun.hycs;

import io.github.pingisfun.hycs.command.ModCommand;
import io.github.pingisfun.hycs.command.TestCommand;
import io.github.pingisfun.hycs.config.ModConfig;
import cc.polyfrost.oneconfig.utils.commands.CommandManager;

@net.minecraftforge.fml.common.Mod(modid = HyChatStatsMod.MODID, name = HyChatStatsMod.NAME, version = HyChatStatsMod.VERSION)
public class HyChatStatsMod {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @net.minecraftforge.fml.common.Mod.Instance(MODID)
    public static HyChatStatsMod INSTANCE;
    public ModConfig config;

    @net.minecraftforge.fml.common.Mod.EventHandler
    public void onFMLInitialization(net.minecraftforge.fml.common.event.FMLInitializationEvent event) {
        config = new ModConfig();
        CommandManager.INSTANCE.registerCommand(new ModCommand());
    }
}
