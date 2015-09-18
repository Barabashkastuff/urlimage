package com.barabashkastuff.urldownloader.domain.status;

import java.util.HashMap;
import java.util.Map;

/**
 * ImageStatus Class
 *
 * @author a.slepakurov
 * @version 9/17/15
 */
public enum ImageStatus {
    NON_DOWNLOADED("non-downloaded"),
    DOWNLOADING("downloading"),
    DOWNLOADED("downloaded"),
    ERROR("error");

    private String title;
    private static Map<String, ImageStatus> statusTypeMap;
    private static String[] names;

    ImageStatus(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static ImageStatus getType(String type) {
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
        statusTypeMap = new HashMap<String, ImageStatus>();
        for (ImageStatus value : values()) {
            statusTypeMap.put(value.getTitle(), value);
        }
    }
}
