package io.github.pingisfun.hycs.auto;

import cc.polyfrost.oneconfig.libs.universal.wrappers.message.UTextComponent;
import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo;
import io.github.pingisfun.hycs.config.ModConfig;
import io.github.pingisfun.hycs.events.TitleEvent;
import io.github.pingisfun.hycs.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IChatComponent;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.lang.reflect.Field;

public class BWGameStats {
    // The timestamp of the last time the chat message was sent, in milliseconds
    private long lastMessageTime = 0;

    // The cooldown period, in seconds
    private static final int COOLDOWN_PERIOD_SECONDS = 5;
    private static final String GuiPlayerTabOverlay_FOOTER_SRG_NAME = "field_175255_h";

    @SubscribeEvent
    public void onTitle(TitleEvent event) {
        if (!ModConfig.isBedwarsGameStatsEnabled
                && !(HypixelUtils.INSTANCE.isInGame()
                && HypixelUtils.INSTANCE.isHypixel()
                && HypixelUtils.INSTANCE.getLocrawInfo().getGameType() == LocrawInfo.GameType.BEDWARS)) {
            return;
        }
        Minecraft client = Minecraft.getMinecraft();
        GuiPlayerTabOverlay tab = client.ingameGUI.getTabList();
        IChatComponent footer = getFooter(tab);
        if (footer == null) {
            return;
        }
        if (event.getTitle().equals("§6§lVICTORY!§r")) {
            // Check if the cooldown period has passed
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastMessageTime > COOLDOWN_PERIOD_SECONDS * 1000) {
                // Send the chat message and update the last message time
                ChatUtil.sendChatMessage("/pc " + footer.getUnformattedText().replace("Ranks, Boosters & MORE! STORE.HYPIXEL.NET", ""));
                lastMessageTime = currentTime;
            }
        }
    }

    private IChatComponent getFooter(GuiPlayerTabOverlay object) {
        Field targetField = null;
        try {
            targetField = object.getClass().getDeclaredField(GuiPlayerTabOverlay_FOOTER_SRG_NAME);
        } catch (NoSuchFieldException ignored) {}

        targetField.setAccessible(true);

        try {
            return (IChatComponent) targetField.get(object);
        } catch (IllegalAccessException ignored) {
            return new UTextComponent("test");
        }
    }
}

