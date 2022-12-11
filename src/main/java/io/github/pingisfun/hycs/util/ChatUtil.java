package io.github.pingisfun.hycs.util;

import cc.polyfrost.oneconfig.libs.universal.ChatColor;
import cc.polyfrost.oneconfig.libs.universal.UChat;
import net.minecraft.client.Minecraft;

public class ChatUtil {
    public static void printFormat(String data) {
        UChat.chat(ChatColor.Companion.translateAlternateColorCodes('&', data));
    }

    public static void printRaw(String data) {
        UChat.chat(data);
    }

    public static void test() {
        UChat.chat("" +
                ChatColor.DARK_GREEN + "--------------------------------------------\n" +
                ChatColor.GREEN + "PingIsFun's bridge stats:\n" +
                "    " + ChatColor.WHITE +  "Goals: " + ChatColor.AQUA + "6969\n" +
                "    " + ChatColor.WHITE +  "Kills: " + ChatColor.AQUA + "420\n" +
                ChatColor.DARK_GREEN + "--------------------------------------------\n");
    }

    public static void sendChatMessage(String message) {
        Minecraft.getMinecraft().thePlayer.sendChatMessage(message);
    }

}

/*

--------------------------------------------
PingIsFun's bridge stats:
    Goals: 6969
    Kills: 420
--------------------------------------------



*/
