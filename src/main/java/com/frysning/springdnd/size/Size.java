package com.frysning.springdnd.size;import com.fasterxml.jackson.annotation.JsonFormat;import com.fasterxml.jackson.annotation.JsonFormat.Shape;import org.springframework.util.StringUtils;@JsonFormat(shape = Shape.OBJECT)public enum Size {    TINY(1),    SMALL(2),    MEDIUM(3),    LARGE(4),    HUGE(5),    GARGANTUAN(6),    KAIJU(7),    NOT_SUPPORTED(0);    public final int id;    Size(int id) {        this.id = id;    }    public static Size getById(int id) {        Size size = NOT_SUPPORTED;        switch (id) {            case 1:                size = TINY;                break;            case 2:                size = SMALL;                break;            case 3:                size = MEDIUM;                break;            case 4:                size = LARGE;                break;            case 5:                size = HUGE;                break;            case 6:                size = GARGANTUAN;                break;            case 7:                size = KAIJU;                break;        }        return size;    }    public int getId() {        return id;    }    public String getSize() {        return StringUtils.capitalize(name());    }}