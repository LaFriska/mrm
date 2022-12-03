package com.friska.mrm.mcresources.recipes;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SmeltingRecipe extends CookingRecipe{
    /**
     *Ingredient is the item ID of what to put in the furnace, and result is the item ID of the output. Experience is the amount of XP you get after the smelting, and to customise cooking time, use the constructor overload with a new Integer cooking time. Cooking time is always in Minecraft ticks. (1/20th of a second).
     * **/
    public SmeltingRecipe(@NotNull String ingredient, @NotNull String result, float experience) {
        super(ingredient, result, experience);
        setName(this.name + "_" + "smelting");
    }

    /**
     *Ingredient is the item ID of what to put in the furnace, and result is the item ID of the output. Experience is the amount of xp you get after the smelting. Cooking time is always in Minecraft ticks. (1/20th of a second).
     * **/
    public SmeltingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime);
        setName(this.name + "_" + "smelting");
    }

    /**
     * Builds the JSON file.
     * **/
    @Override
    public void build() {
        this.type = "minecraft:smelting";
        super.build();
    }
}
