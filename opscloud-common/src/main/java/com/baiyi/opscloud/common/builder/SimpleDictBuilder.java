package com.baiyi.opscloud.common.builder;

import org.apache.commons.lang3.StringUtils;
/**
 * @Author baiyi
 * @Date 2021/6/16 1:24 下午
 * @Version 1.0
 */
public class SimpleDictBuilder {

    private final SimpleDict simpleDict = new SimpleDict();

    private SimpleDictBuilder() {
    }

    static public SimpleDictBuilder newBuilder() {
        return new SimpleDictBuilder();
    }

    public SimpleDictBuilder putParam(String name, String value) {
        if (!StringUtils.isEmpty(value))
            simpleDict.put(name, value);
        return this;
    }

    public SimpleDict build() {
        return simpleDict;
    }
}
