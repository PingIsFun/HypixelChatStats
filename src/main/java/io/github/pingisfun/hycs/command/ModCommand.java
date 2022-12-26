package io.github.pingisfun.hycs.command;

import cc.polyfrost.oneconfig.utils.commands.annotations.Command;
import cc.polyfrost.oneconfig.utils.commands.annotations.Description;
import cc.polyfrost.oneconfig.utils.commands.annotations.Main;
import cc.polyfrost.oneconfig.utils.commands.annotations.SubCommandGroup;
import io.github.pingisfun.hycs.HyChatStatsMod;
import io.github.pingisfun.hycs.util.ChatUtil;
import net.minecraft.entity.player.EntityPlayer;

@Command(value = HyChatStatsMod.MODID, description = "Access the " + HyChatStatsMod.NAME + " GUI.")
public class ModCommand {
    @Main
    private void main() {
        HyChatStatsMod.config.openGui();
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