package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class CannotFindModIDException extends RuntimeException{

    private static String line = "Cannot locate the mod ID. ";

    public CannotFindModIDException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
