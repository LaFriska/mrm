package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.annotations.ExpectModdersToAccess;

import javax.annotation.Nonnull;
@ExpectModdersToAccess
public class SmeltingRecipe extends CookingRecipe {
    /**
     * This class creates a smelting recipe, used in minecraft furnaces.
     * Use the constructor overload to specify a custom cooking time, or it will be defaulted to 200 ticks.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being smelted in the furnace.
     * @param result The finished product of the furnace.
     * @param experience The amount of experience you get.
     * **/
    public SmeltingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience) {
        super(ingredient, result, experience, "minecraft:smelting");
        this.cookingTime = 200;
    }

    /**
     * This class creates a smelting recipe, used in minecraft furnaces.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID of what is being smelted in the furnace.
     * @param result The finished product of the furnace.
     * @param experience The amount of experience you get.
     * @param cookingTime The amount of time in Minecraft ticks (a tick is 1/20th of a second) it takes to smelt.
     * **/
    public SmeltingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime, "minecraft:smelting");
    }
}
