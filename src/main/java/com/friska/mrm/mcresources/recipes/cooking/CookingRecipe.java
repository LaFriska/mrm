package com.friska.mrm.mcresources.recipes.cooking;

import com.friska.mrm.mcresources.recipes.Recipe;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;

/**
 * Creates a recipe for furnace, smoker, blast furnace, and campfires, all of which are given their individual classes and inherits this class. DO NOT TRY TO INSTANTIATE THIS CLASS.
 * **/
public abstract class CookingRecipe extends Recipe {
    protected String ingredient;
    protected float experience;
    protected int cookingTime;

    protected CookingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, @Nonnull String type){
        this(ingredient, result, experience, 200, type);
    }

    protected CookingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, int cookingTime, @Nonnull String type){
        super();
        this.ingredient = ingredient;
        this.type = type;
        this.result = result;
        this.experience = experience;
        setAndTruncateName(result);
        //createBuilder();
        this.cookingTime = cookingTime;
    }

    /**
     * Should not be directly called from this class, only from child classes.
     **/
    @Override
    public void build(){
        super.build();
        String ingredientType = getIDType(this.ingredient);
        if(ingredientType.equals("tag")) this.ingredient = this.ingredient.substring(1);
        this.getBuilder()
                .nest(new JValue<>("type", this.type))
                .nest(new JObject("ingredient").nest(new JValue<>(ingredientType, this.ingredient)))
                .nest(new JValue<>("result", this.result))
                .nest(new JValue<>("experience", experience))
                .nest(new JValue<>("cookingtime", cookingTime)).build();
    }
}
