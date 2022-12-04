package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
@ExpectModdersToAccess
public class BlastingRecipe extends CookingRecipe{

    /**
     * This class creates a blasting recipe, used in minecraft blast furnaces.
     * Use the constructor overload to specify a custom cooking time, or it will be defaulted to 100 ticks.
     * @param ingredient The item ID of what is being blasted in the blast furnace.
     * @param result The finished product of the blast furnace.
     * @param experience The amount of experience you get.
     * **/
    public BlastingRecipe(@NotNull String ingredient, @NotNull String result, float experience) {
        super(ingredient, result, experience, "minecraft:blasting");
        cookingTime = 100;
    }
    /**
     * This class creates a blasting recipe, used in minecraft blast furnaces.
     * @param ingredient The item ID of what is being blasted in the blast furnace.
     * @param result The finished product of the blast furnace.
     * @param experience The amount of experience you get.
     * @param cookingTime The amount of time in Minecraft ticks (a tick is 1/20th of a second) it takes to blast.
     * **/
    public BlastingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:blasting");
    }
}
