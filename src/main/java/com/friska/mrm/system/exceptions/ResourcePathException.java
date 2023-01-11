package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class ResourcePathException extends RuntimeException {

    private static final String line = "Issue in following path to resource directories.";

    public ResourcePathException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}

