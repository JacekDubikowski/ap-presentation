package com.virtuslab.jd;

import java.util.List;

@AllFieldsFinal
public class Data {
    private static final String staticFinalField = "xd";
    private static List<? extends CharSequence> staticField;
    private final String finalField;
    private String field;

    public Data(String finalField) {
        this.finalField = finalField;
    }
}
