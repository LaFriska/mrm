package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.mcresources.recipes.Recipe;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

/**
 * Creates a recipe for furnace, smoker, blast furnace, and campfires, all of which are given their individual classes and inherits this class. DO NOT TRY TO INSTANTIATE THIS CLASS.
 * **/
public class CookingRecipe extends Recipe {
    protected String ingredient;
    protected float experience;
    protected int cookingTime;

    protected CookingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull String type){
        super();
        initialise(ingredient, result, experience, type);
        cookingTime = 200;
    }

    protected CookingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, int cookingTime, @Nonnull String type){
        super();
        initialise(ingredient, result, experience, type);
        this.cookingTime = cookingTime;
    }

    private void initialise(@NotNull String ingredient, @NotNull String result, float experience, @Nonnull String type) {
        this.type = type;
        this.ingredient = ingredient;
        this.result = result;
        this.experience = experience;
        setName(result);
        initiateBuilder();
    }

    /**
     * Sets the cooking time in minecraft ticks (1/20th of a second).
     * **/
    protected void setCookingTime(@Nonnull Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Should not be directly called from this class, only from child classes.
     **/
    public void build(){
        this.getBuilder()
                .nest(new JValue<>("type", this.type))
                .nest(new JObject("ingredient").nest(new JValue<>("item", this.ingredient)))
                .nest(new JValue<>("result", this.result))
                .nest(new JValue<>("experience", experience))
                .nest(new JValue<>("cookingtime", cookingTime)).build();
    }

}
