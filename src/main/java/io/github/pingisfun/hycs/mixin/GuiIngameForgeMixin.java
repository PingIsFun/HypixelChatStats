package io.github.pingisfun.hycs.mixin;

import cc.polyfrost.oneconfig.utils.hypixel.HypixelUtils;
import io.github.pingisfun.hycs.HyChatStatsMod;
import io.github.pingisfun.hycs.config.ModConfig;
import io.github.pingisfun.hycs.events.TitleEvent;
import io.github.pingisfun.hycs.util.ChatUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(value = GuiIngameForge.class, remap = false)
public class GuiIngameForgeMixin extends GuiIngame {
    private static final String[] IGNORED_TITLES = {"§r", ""};

    public GuiIngameForgeMixin(Minecraft minecraft) {
        super(minecraft);
    }

    @Inject(method = "renderTitle", at = @At("HEAD"))
    private void onRenderTitle(int l, int age, float opacity, CallbackInfo ci) {
        if (!HypixelUtils.INSTANCE.isHypixel() || !HyChatStatsMod.config.enabled) {
            return;
        }
        String title = this.displayedTitle;
        String subtitle = this.displayedSubTitle;

        List<String> ignoredTitlesList = Arrays.asList(IGNORED_TITLES);
        if (ignoredTitlesList.contains(title) && ignoredTitlesList.contains(subtitle)) {
            return;
        }
        TitleEvent event = new TitleEvent(title, subtitle);
        MinecraftForge.EVENT_BUS.post(event);


    }
}
