package com.friska.mrm.registries;

import com.friska.mrm.mcresources.recipes.Recipe;

import java.util.ArrayList;
public class RecipeRegistry {

    private static final ArrayList<Recipe> RECIPES = new ArrayList<>();

    protected static <T extends Recipe> void register (T recipe){
        RECIPES.add(recipe);
    }
    protected static void build(){
        if(RECIPES.isEmpty()) return;
        System.out.println("Checking and updating duplicate names for all recipe builders...");
        RegistryUtil.updateNames(RECIPES);
        System.out.println("Building all recipes...");
        RECIPES.forEach(Recipe::build);
    }
}
