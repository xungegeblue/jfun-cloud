package com.central.common.model;

/**
 * @Author: zlt
 */
public enum CodeEnum {
    /**
     * 成功
     */
    SUCCESS(0, "成功"),
    /**
     * 失败
     */
    ERROR(1, "失败");

    private Integer code;
    private String message;

    CodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return this.message;
    }
}
