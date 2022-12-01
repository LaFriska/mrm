package com.friska.mrm.exceptions;

import javax.annotation.Nullable;

public class InvalidJSONStringException extends RuntimeException{
    private static String line = "Invalid string attempted to be parsed into a JSON file.";

    public InvalidJSONStringException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
