package com.roach.code.generator;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Method {

    private boolean compatible = false;

    private String name;

    private List<Parameter> parameters = new ArrayList<>();

    public Method setCompatible(boolean compatible) {
        this.compatible = compatible;
        return this;
    }

    public Method setName(String name) {
        this.name = name;
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
}
