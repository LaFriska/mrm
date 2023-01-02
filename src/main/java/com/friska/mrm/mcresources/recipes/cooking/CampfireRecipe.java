package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.system.annotations.ExpectAccess;

import javax.annotation.Nonnull;
@ExpectAccess
public class CampfireRecipe extends CookingRecipe{
    /**
     * This class creates a campfire cooking recipe, used in minecraft campfires.
     * Use the constructor overload to specify a custom cooking time, or it will be defaulted to 600 ticks.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being placed on the campfire.
     * @param result The finished product of the furnace.
     * @param experience The amount of experience you get.
     * **/
    public CampfireRecipe(@Nonnull String ingredient, @Nonnull String result, float experience) {
        super(ingredient, result, experience, "minecraft:campfire_cooking");
        this.cookingTime = 600;
    }

    /**
     * This class creates a campfire cooking recipe, used in minecraft campfires.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being placed on the campfire.
     * @param result The finished product of the furnace.
     * @param experience The amount of experience you get.
     * @param cookingTime The amount of time in Minecraft ticks (a tick is 1/20th of a second) it takes to smelt.
     * **/
    public CampfireRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:campfire_cooking");
    }
}
