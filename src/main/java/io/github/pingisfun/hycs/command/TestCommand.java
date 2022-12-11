package io.github.pingisfun.hycs.command;

import io.github.pingisfun.hycs.HyChatStatsMod;
import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import io.github.pingisfun.hycs.util.ChatUtil;

@Command(value = "test_" + HyChatStatsMod.MODID, description = "Access the " + HyChatStatsMod.NAME + " GUI.")
public class TestCommand {

    @Main
    private static void main() {
        ChatUtil.test();
    }
}