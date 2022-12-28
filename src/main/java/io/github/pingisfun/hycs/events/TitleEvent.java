package io.github.pingisfun.hycs.events;

import net.minecraftforge.fml.common.eventhandler.Event;

public class TitleEvent extends Event {
    private final String eventTitle;
    private final String eventSubtitle;

    public TitleEvent(String title, String subtitle) {
        this.eventTitle = title;
        this.eventSubtitle = subtitle;
    }

    public String getTitle() {
        return this.eventTitle;
    }

    public String getSubtitle() {
        return this.eventSubtitle;
    }
}
