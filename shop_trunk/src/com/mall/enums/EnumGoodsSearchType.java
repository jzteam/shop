package com.mall.enums;

public enum EnumGoodsSearchType {

    ALL(0, "查询所有"),

    HOT(1, "查询热卖"),

    NEW(2, "查询新到"),

    SALE(3, "打折"),

    SPECIAL(4, "特别推荐"),

    SUPER(9, "分类查看");



    private EnumGoodsSearchType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
