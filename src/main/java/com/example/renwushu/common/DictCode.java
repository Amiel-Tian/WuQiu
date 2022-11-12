package com.example.renwushu.common;

public enum DictCode {
    STATUS ("状态", "status"),
    DICTTYPE ("字典类型", "dictType"),
    DICTTREE ("字典是否级联", "dictTree"),
    MENUTYPE ("菜单类型", "menuType");

    public String dictName;
    public String dictValue;

    private DictCode(String dictName, String dictValue) {
        this.dictName = dictName;
        this.dictValue = dictValue;
    }
}
