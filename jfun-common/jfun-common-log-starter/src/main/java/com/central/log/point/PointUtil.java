package com.central.log.point;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointUtil {
    private PointUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static final String SPLIT = "|";

    public static void info(String id, String type, String message) {
        log.info(id + SPLIT + type + SPLIT + message);
    }

    public static void debug(String id, String type, String message) {
        log.debug(id + SPLIT + type + SPLIT + message);
    }


}
