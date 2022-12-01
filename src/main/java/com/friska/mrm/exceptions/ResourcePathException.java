package com.friska.mrm.exceptions;

import javax.annotation.Nullable;

public class ResourcePathException extends RuntimeException {

    private static String line = "Issue in following path to resource directories.";

    public ResourcePathException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}

