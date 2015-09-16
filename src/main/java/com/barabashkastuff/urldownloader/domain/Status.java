package com.barabashkastuff.urldownloader.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Status Class
 *
 * @author a.slepakurov
 * @version 9/16/15
 */
public enum Status {
    SUBMITTED("submitted"),
    QUEUE("queue"),
    PROCESSING("processing"),
    FINISHED("finished"),
    ERROR("error");

    private String title;
    private static Map<String, Status> statusTypeMap;
    private static String[] names;

    Status(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static Status getType(String type) {
        if (statusTypeMap == null) {
            initMapping();
        }
        return statusTypeMap.get(type);
    }

    public static String[] names() {
        if (names != null) {
            return names;
        }
        names = new String[values().length];
        for (int i = 0; i < values().length; i++) {
            names[i] = values()[i].getTitle();
        }
        return names;
    }

    private static void initMapping() {
        statusTypeMap = new HashMap<String, Status>();
        for (Status value : values()) {
            statusTypeMap.put(value.getTitle(), value);
        }
    }
}
