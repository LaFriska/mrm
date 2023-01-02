package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class UnexpectedTagException extends RuntimeException{
    private static String line = "An unexpected tag found.";

    public UnexpectedTagException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
