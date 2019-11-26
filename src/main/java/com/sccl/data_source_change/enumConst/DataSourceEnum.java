package com.sccl.data_source_change.enumConst;

/**
 * Create by wangbin
 * 2019-11-19-16:54
 */
public enum DataSourceEnum {
    MASTER("master"),
    SLAVE("slave");
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     DataSourceEnum(String name) {
        this.name = name;
    }
}
