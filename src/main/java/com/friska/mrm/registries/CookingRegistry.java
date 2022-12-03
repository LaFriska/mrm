package com.friska.mrm.registries;

import com.friska.mrm.mcresources.recipes.*;

import java.util.ArrayList;

public class CookingRegistry {

    private static ArrayList<BlastingRecipe> BLASTING = new ArrayList<>();
    private static ArrayList<SmokingRecipe> SMOKING = new ArrayList<>();
    private static ArrayList<SmeltingRecipe> SMELTING = new ArrayList<>();
    private static ArrayList<CampfireRecipe> CAMPFIRING = new ArrayList<>();

    /**
     * Registers any blasting, smoking, smelting, campfire cooking objects.
     * **/
    public static <T extends CookingRecipe> void register (T recipe){
        if(recipe instanceof SmeltingRecipe){
            SMELTING.add((SmeltingRecipe) recipe);
        }else if(recipe instanceof BlastingRecipe){
            BLASTING.add((BlastingRecipe) recipe);
        }else if(recipe instanceof SmokingRecipe){
            SMOKING.add((SmokingRecipe) recipe);
        }else if(recipe instanceof CampfireRecipe){
            CAMPFIRING.add((CampfireRecipe) recipe);
        }
    }

    /**
     * Builds all cooking recipes.
     * **/
    public static void build(){
        SMELTING.forEach(SmeltingRecipe::build);
        BLASTING.forEach(BlastingRecipe::build);
        SMOKING.forEach(SmokingRecipe::build);
        CAMPFIRING.forEach(CampfireRecipe::build);
    }


}
