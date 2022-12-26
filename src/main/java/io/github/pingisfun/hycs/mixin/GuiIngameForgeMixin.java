package io.github.pingisfun.hycs.mixin;

import io.github.pingisfun.hycs.events.TitleEvent;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.GuiIngameForge;
import net.minecraftforge.common.MinecraftForge;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.List;

@Mixin(value = GuiIngameForge.class, remap = false)
public class GuiIngameForgeMixin {
    private static final String[] IGNORED_TITLES = {"§r", ""};

    @Inject(method = "renderTitle", at = @At("HEAD"))
    private void onRenderTitle(int l, int age, float opacity, CallbackInfo ci) {
//        if (!HypixelUtils.INSTANCE.isHypixel()) {
//            return;
//        }
        Minecraft client = Minecraft.getMinecraft();
        String title = client.ingameGUI.displayedTitle;
        String subtitle = client.ingameGUI.displayedSubTitle;

        List<String> ignoredTitlesList = Arrays.asList(IGNORED_TITLES);
        if (ignoredTitlesList.contains(title) && ignoredTitlesList.contains(subtitle)) {
            return;
        }
        TitleEvent event = new TitleEvent(title, subtitle);
        MinecraftForge.EVENT_BUS.post(event);


    }
}
