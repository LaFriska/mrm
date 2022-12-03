package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.Empty;
import com.friska.mrm.config.Config;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Creates a recipe for furnace, smoker, blast furnace, and campfires, all of which are given their individual classes and inherits this class. DO NOT TRY TO INSTANTIATE THIS CLASS.
 * **/
public class CookingRecipe extends Recipe{
    protected String ingredient;
    protected float experience;
    protected int cookingTime;

    protected CookingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience){
        initiate(ingredient, result, experience);
        cookingTime = 200;
    }

    protected CookingRecipe(@Nonnull String ingredient, @Nonnull String result, float experience, int cookingTime){
        initiate(ingredient, result, experience);
        this.cookingTime = cookingTime;
    }

    private void initiate(@NotNull String ingredient, @NotNull String result, float experience) {
        this.ingredient = ingredient;
        this.result = result;
        this.experience = experience;
        setPath("data/" + Config.getModID() + "/recipes");
        try {
            setName(result.split(":")[1]);
        }catch (ArrayIndexOutOfBoundsException e){
            setName(result);
        }
        addBuilder();
    }

    protected void setCookingTime(@Nonnull Integer cookingTime) {
        this.cookingTime = cookingTime;
    }

    /**
     * Should not be directly called from this class, only from child classes.
     **/
    protected void build(){
        this.getBuilder(0).dontOverrideExistingFiles()
                .nest(new JValue<>("type", this.type))
                .nest(new JObject("ingredient").nest(new JValue<>("item", this.ingredient)))
                .nest(new JValue<>("result", this.result))
                .nest(new JValue<>("experience", experience))
                .nest(new JValue<>("cookingtime", cookingTime)).build();
    }

}
