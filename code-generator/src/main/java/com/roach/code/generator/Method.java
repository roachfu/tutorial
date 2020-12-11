package com.roach.code.generator;

import com.google.common.base.CaseFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Method {

    /**
     * 是否需要兼容
     */
    private boolean compatible = false;

    /**
     * 方法名
     */
    private String name;

    /**
     * 方法参数
     */
    private List<Parameter> parameters = new ArrayList<>();

    /**
     * 方法（反）序列化解析的编号
     */
    private int handleType;

    /**
     * 大写下划线分割的方法名
     */
    private String upperUnderscoreName;

    public Method setCompatible(boolean compatible) {
        this.compatible = compatible;
        return this;
    }

    public Method setName(String name) {
        this.name = name;
        return this;
    }

    public Method setHandleType(int handleType) {
        this.handleType = handleType;
        return this;
    }

    public Method setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
        return this;
    }

    public Method addParameter(Parameter parameter) {
        parameters.add(parameter);
        return this;
    }

    public String getUpperUnderscoreName() {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, name);
    }
}
