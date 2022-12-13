package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.annotations.ExpectAccess;

import javax.annotation.Nonnull;
@ExpectAccess
public class BlastingRecipe extends CookingRecipe{

    /**
     * This class creates a blasting recipe, used in minecraft blast furnaces.
     * Use the constructor overload to specify a custom cooking time, or it will be defaulted to 100 ticks.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being blasted in the blast furnace.
     * @param result The finished product of the blast furnace.
     * @param experience The amount of experience you get.
     * **/
    public BlastingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience) {
        super(ingredient, result, experience, "minecraft:blasting");
        cookingTime = 100;
    }
    /**
     * This class creates a blasting recipe, used in minecraft blast furnaces.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being blasted in the blast furnace.
     * @param result The finished product of the blast furnace.
     * @param experience The amount of experience you get.
     * @param cookingTime The amount of time in Minecraft ticks (a tick is 1/20th of a second) it takes to blast.
     * **/
    public BlastingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:blasting");
    }
}
