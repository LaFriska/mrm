package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class BlockStateTypeMismatchException extends RuntimeException{
    private static String line = "Mismatch occured between a block state builder and a method specific to one block state type. ";

    public BlockStateTypeMismatchException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
