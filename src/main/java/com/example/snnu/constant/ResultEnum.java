package com.example.snnu.constant;

public enum  ResultEnum {


    SHOW_NEWIMAGE(21),
    SHOW_NEWVIDEO(22),
    SWITCH_FRAGMENT5(15),
    SWITCH_FRAGMENT6(16);

    ResultEnum(int code) {
        this.code = code;
    }

    private int code;

    public int getCode() {
        return code;
    }
}
