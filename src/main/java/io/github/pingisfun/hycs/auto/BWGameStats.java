package io.github.pingisfun.hycs.auto;

import cc.polyfrost.oneconfig.utils.TickDelay;
import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import io.github.pingisfun.hycs.HyChatStatsMod;
import io.github.pingisfun.hycs.config.ModConfig;
import io.github.pingisfun.hycs.events.TitleEvent;
import io.github.pingisfun.hycs.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.util.IChatComponent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;
import java.util.regex.Pattern;

public class BWGameStats {
    // The cooldown period, in seconds
    private static final int COOLDOWN_PERIOD_SECONDS = 20;
    private static final String GuiPlayerTabOverlay_FOOTER_SRG_NAME = "field_175255_h";
    // The timestamp of the last time the chat message was sent, in milliseconds
    private long lastMessageTime = 0;
    private String backupFooter;
    private String resFooter;

    private static String getFooter(GuiPlayerTabOverlay object) {
        Field targetField;
        try {
            targetField = object.getClass().getDeclaredField(GuiPlayerTabOverlay_FOOTER_SRG_NAME);
        } catch (NoSuchFieldException error) {
            HyChatStatsMod.LOGGER.error(error);
            return "Error: NoSuchFieldException";
        }

        targetField.setAccessible(true);

        try {
            IChatComponent res = (IChatComponent) targetField.get(object);
            if (res == null) {
                return "Error: NullPointerException";
            }
            return unformatFooter(res);
        } catch (IllegalAccessException error) {
            HyChatStatsMod.LOGGER.error(error);
            return "Error: IllegalAccessException";
        }
    }

    private static boolean isFooterRegex(String data) {
        String pattern = "Kills: \\d+ Final Kills: \\d+ Beds Broken: \\d+";
        Pattern p = Pattern.compile(pattern);

        return p.matcher(data).matches();
    }

    private static String unformatFooter(IChatComponent footer) {
        return footer.getUnformattedText().replace("Ranks, Boosters & MORE! STORE.HYPIXEL.NET", "").trim();
    }

    @SubscribeEvent
    public void onTitle(TitleEvent event) {
        if (!event.getTitle().equals("§6§lVICTORY!§r")
                || !ModConfig.isBedwarsGameStatsEnabled
                || !HypixelUtils.INSTANCE.isHypixel()
                || !HypixelUtils.INSTANCE.isInGame()
                || HypixelUtils.INSTANCE.getLocrawInfo().getGameType() != LocrawInfo.GameType.BEDWARS) {
            return;
        }

        GuiPlayerTabOverlay tab = Minecraft.getMinecraft().ingameGUI.getTabList();
        String footer = getFooter(tab);
        if (isFooterRegex(footer)) {
            backupFooter = footer;
        } else {
            backupFooter = "Error: Invalid footer";
            HyChatStatsMod.LOGGER.error("[Error: Invalid footer] " + "\"" + footer + "\"");
        }
        Runnable runnable = new FooterRunnable();
        new TickDelay(runnable, ModConfig.bedwarsGameStatsTabReadDelay * 20);


    }

    private class FooterRunnable implements Runnable {
        public void run() {
            Minecraft client = Minecraft.getMinecraft();
            GuiPlayerTabOverlay tab = client.ingameGUI.getTabList();
            String footer = getFooter(tab);
            // Check if the cooldown period has passed
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastMessageTime > COOLDOWN_PERIOD_SECONDS * 1000) {
                if (isFooterRegex(footer)) {
                    resFooter = footer;
                } else {
                    resFooter = backupFooter;
                }
                // Send the chat message and update the last message time
                lastMessageTime = currentTime;
                if (footer.startsWith("Error:")) {
                    ChatUtil.printError(footer);
                    return;
                }
                Runnable runnable = new ChatMessageDelayRunnable();
                new TickDelay(runnable, ModConfig.bedwarsGameStatsDelay * 20);
            }
        }
    }

    private class ChatMessageDelayRunnable implements Runnable {
        public void run() {
            if (HypixelUtils.INSTANCE.getLocrawInfo().getGameMode().equals("BEDWARS_EIGHT_ONE")) {
                if (!ModConfig.isBedwarsGameStatsEnabledSolo) {
                    return;
                }
                ChatUtil.printFormat(resFooter);
            } else {
                ChatUtil.sendChatMessage("/pc " + resFooter);
            }
        }
    }
}

