package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
@ExpectModdersToAccess
public class CampfireRecipe extends CookingRecipe{
    /**
     * This class creates a campfire cooking recipe, used in minecraft campfires.
     * Use the constructor overload to specify a custom cooking time, or it will be defaulted to 600 ticks.
     * @param ingredient The item ID of what is being placed on the campfire.
     * @param result The finished product of the furnace.
     * @param experience The amount of experience you get.
     * **/
    public CampfireRecipe(@NotNull String ingredient, @NotNull String result, float experience) {
        super(ingredient, result, experience, "minecraft:campfire_cooking");
        this.cookingTime = 600;
    }

    /**
     * This class creates a campfire cooking recipe, used in minecraft campfires.
     * @param ingredient The item ID of what is being placed on the campfire.
     * @param result The finished product of the furnace.
     * @param experience The amount of experience you get.
     * @param cookingTime The amount of time in Minecraft ticks (a tick is 1/20th of a second) it takes to smelt.
     * **/
    public CampfireRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:campfire_cooking");
    }
}