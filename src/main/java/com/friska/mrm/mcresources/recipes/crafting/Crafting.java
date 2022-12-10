package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.mcresources.recipes.Recipe;

import javax.annotation.Nullable;

public class Crafting extends Recipe {

    //Count and group are not necessarily defined in crafting recipes.
    protected @Nullable Integer count = null;
    protected @Nullable String group = null;
    protected Crafting(String result){
        super();
        this.type = "minecraft:crafting";
        this.result = result;
    }
}
