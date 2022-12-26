package io.github.pingisfun.hycs.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class TitleEvent extends Event {
    private final String title;
    private final String subtitle;

    public TitleEvent(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }
}
