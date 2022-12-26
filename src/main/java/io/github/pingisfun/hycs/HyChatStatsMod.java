package io.github.pingisfun.hycs;

import cc.polyfrost.oneconfig.utils.commands.CommandManager;
import io.github.pingisfun.hycs.auto.BWGameStats;
import io.github.pingisfun.hycs.command.ModCommand;
import io.github.pingisfun.hycs.config.ModConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(modid = HyChatStatsMod.MODID, name = HyChatStatsMod.NAME, version = HyChatStatsMod.VERSION)
public class HyChatStatsMod {
    public static final String MODID = "@ID@";
    public static final String NAME = "@NAME@";
    public static final String VERSION = "@VER@";
    @Mod.Instance(MODID)
    public static HyChatStatsMod INSTANCE;
    public static ModConfig config;

    public static final Logger LOGGER = LogManager.getLogger(MODID);
    @Mod.EventHandler
    public void onFMLInitialization(FMLInitializationEvent event) {
        config = new ModConfig();
        CommandManager.INSTANCE.registerCommand(new ModCommand());
//        CommandManager.INSTANCE.registerCommand(new TestCommand());
        MinecraftForge.EVENT_BUS.register(new BWGameStats());
    }
}
