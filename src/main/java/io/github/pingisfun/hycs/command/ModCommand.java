package io.github.pingisfun.hycs.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.*;
import io.github.pingisfun.hycs.HyChatStatsMod;
import io.github.pingisfun.hycs.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Command(value = HyChatStatsMod.MODID, description = "Access the " + HyChatStatsMod.NAME + " GUI.")
public class ModCommand {
    @Main
    private void main() {
        HyChatStatsMod.INSTANCE.config.openGui();
    }

    @SubCommandGroup(value = "bedwars", aliases = {"bw"})
    private class BedwarsCommand {

        @Main
        private void bedwars() {
            ChatUtil.printRaw("EMPTY");
//            bedwars(Minecraft.getMinecraft().thePlayer);
        }

        @Main
        private void bedwars(@Description("username") EntityPlayer player) {
            ChatUtil.printRaw(player.getName());
        }
    }
}