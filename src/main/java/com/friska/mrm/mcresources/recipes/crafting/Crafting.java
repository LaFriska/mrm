package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.mcresources.recipes.Recipe;

public class Crafting extends Recipe {
    protected Crafting(String result){
        super();
        checkResultForTags(result);
        this.result = result;
    }
}
