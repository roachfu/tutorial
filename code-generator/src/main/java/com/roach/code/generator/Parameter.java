package com.roach.code.generator;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parameter {

    private String annotation;

    private String type;

    private String name;
}
