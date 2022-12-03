package com.friska.mrm.mcresources.recipes;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class CampfireRecipe extends CookingRecipe{
    /**
     * Ingredient is the item ID of what to put in the campfire, and result is the item ID of the output.
     * Experience is the amount of XP you get after the smelting, and to customise cooking time (defaulted to 200),
     * use the constructor overload with a new Integer cooking time.
     * Cooking time is always in Minecraft ticks. (1/20th of a second).
     * **/
    public CampfireRecipe(@NotNull String ingredient, @NotNull String result, float experience) {
        super(ingredient, result, experience);
        cookingTime = 600;
        setName(this.name + "_" + "campfire");
    }

    /**
     *Ingredient is the item ID of what to put in the campfire, and result is the item ID of the output. Experience is the amount of xp you get after the smelting. Cooking time is always in Minecraft ticks. (1/20th of a second).
     * **/
    public CampfireRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime);
        setName(this.name + "_" + "campfire");
    }

    /**
     * Builds the JSON file.
     * **/
    @Override
    public void build() {
        this.type = "minecraft:campfire_cooking";
        super.build();
    }
}
