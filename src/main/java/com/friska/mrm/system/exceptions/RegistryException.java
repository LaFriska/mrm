package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class RegistryException extends RuntimeException{

    private static final String line = "Unable to process the resource management registries.";

    public RegistryException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }

}
