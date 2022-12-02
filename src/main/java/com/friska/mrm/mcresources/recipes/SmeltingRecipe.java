package com.friska.mrm.mcresources.recipes;

import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SmeltingRecipe extends CookingRecipe{
    /**
     *Ingredient is the item ID of what to put in the furnace, and result is the item ID of the output. Experience is the amount of xp you get after the smelting, and to customise cooking time, use the constructor overload with a new Integer cooking time.
     * **/
    public SmeltingRecipe(@NotNull String ingredient, @NotNull String result, float experience) {
        super(ingredient, result, experience);
    }

    /**
     *Ingredient is the item ID of what to put in the furnace, and result is the item ID of the output. Experience is the amount of xp you get after the smelting.
     * **/
    public SmeltingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull Integer cookingTime){
        super(ingredient, result, experience, cookingTime);
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
