package com.friska.mrm.system.exceptions;

import javax.annotation.Nullable;

public class CraftingRecipeException extends RuntimeException{
    private static final String line = "Issue constructing crafting recipe.";

    public CraftingRecipeException(@Nullable String action){
        super(action == null ? line : line + " " + action);
    }
}
