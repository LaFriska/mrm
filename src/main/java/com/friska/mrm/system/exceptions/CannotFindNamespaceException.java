package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class CannotFindNamespaceException extends RuntimeException{

    private static final String line = "Minecraft Resource Manager failed to point towards the namespace. ";

    public CannotFindNamespaceException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
