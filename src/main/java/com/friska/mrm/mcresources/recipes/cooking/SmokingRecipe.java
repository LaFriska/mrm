package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.mcresources.recipes.cooking.CookingRecipe;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class SmokingRecipe extends CookingRecipe {
    /**
     *Ingredient is the item ID of what to put in the smoker, and result is the item ID of the output.
     * Experience is the amount of XP you get after the smelting, and to customise cooking time (defaulted to 200),
     * use the constructor overload with a new Integer cooking time.
     * Cooking time is always in Minecraft ticks. (1/20th of a second).
     * **/
    public SmokingRecipe(@NotNull String ingredient, @NotNull String result, float experience) {
        super(ingredient, result, experience, "minecraft:smoking");
        this.cookingTime = 100;
    }

    /**
     *Ingredient is the item ID of what to put in the furnace, and result is the item ID of the output. Experience is the amount of xp you get after the smelting. Cooking time is always in Minecraft ticks. (1/20th of a second).
     * **/
    public SmokingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:smoking");
    }
}
