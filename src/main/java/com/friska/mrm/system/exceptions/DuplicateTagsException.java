package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class DuplicateTagsException extends RuntimeException{
    private static final String line = "Tags with duplicate IDs and namespaces found.";

    public DuplicateTagsException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
