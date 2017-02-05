package com.vandit.samples.appcomponents.enums;

/**
 * Created by vandi on 2/4/2017.
 */

public enum MenuActionItem {
    ALARM(0),
    APPS(1),
    ATTACHMENT(2),
    BATTERY(3),
    BLUETOOTH(4),
    BOOK(5),
    BOOKMARK(6),
    BRIGHTNESS(7),
    CAST(8),
    SEND(9),
    STORAGE(10);

    private int position;

    MenuActionItem(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
