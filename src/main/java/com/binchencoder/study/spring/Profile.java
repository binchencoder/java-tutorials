package com.binchencoder.study.spring;

import lombok.Data;

@Data
public class Profile {

    /**
     * spring profiles
     */
    public static final String DEV = "dev";
    public static final String TEST = "test";
    public static final String PROD = "prod";

    private boolean dev;
    private boolean production;
}
