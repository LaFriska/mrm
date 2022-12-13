package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.annotations.ExpectAccess;

import javax.annotation.Nonnull;
@ExpectAccess
public class SmokingRecipe extends CookingRecipe {
    /**
     * This class creates a smoking recipe, used in minecraft smokers.
     * Use the constructor overload to specify a custom cooking time, or it will be defaulted to 100 ticks.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being smoked in the smoker.
     * @param result The finished product of the smoker.
     * @param experience The amount of experience you get. (Most food items have this parameter set to 0.35F).
     * **/
    public SmokingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience) {
        super(ingredient, result, experience, "minecraft:smoking");
        this.cookingTime = 100;
    }

    /**
     * This class creates a smoking recipe, used in minecraft smokers.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being smoked in the smoker.
     * @param result The finished product of the smoker.
     * @param experience The amount of experience you get. (Most food items have this parameter set to 0.35F).
     * @param cookingTime The amount of time in Minecraft ticks (a tick is 1/20th of a second) it takes to smoke.
     * **/
    public SmokingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:smoking");
    }
}
