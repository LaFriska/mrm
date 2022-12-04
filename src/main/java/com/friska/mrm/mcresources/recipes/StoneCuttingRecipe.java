package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class StoneCuttingRecipe extends Recipe{

    private String ingredient;
    private int count = 1;

    /**
     * The first param: ingredient, is what to input into the stonecutter, and the second param: result, is the output. Use the constructor overload to specify a custom "count", which is the amount of the result output.
     * Count is defaulted to 1 if unspecified. The count parameter cannot exceed 64.
     * **/
    public StoneCuttingRecipe(@Nonnull String ingredient, @Nonnull String result){
        super();
        initialise(ingredient, result);
    }
    /**
     * The first param: ingredient, is what to input into the stonecutter, and the second param: result, is the output. Use the constructor overload to specify a custom "count", which is the amount of the result output.
     * Count is defaulted to 1 if unspecified. The count parameter cannot exceed 64.
     * **/
    public StoneCuttingRecipe(@Nonnull String ingredient, @Nonnull String result, int count){
        super();
        initialise(ingredient, result);
        this.count = count;
    }
    private void initialise(@NotNull String ingredient, @NotNull String result) {
        this.ingredient = ingredient;
        this.result = result;
        this.type = "minecraft:stonecutting";
        setName(result);
        initiateBuilder();
    }

    /**
     * Builds the JSON file.
     * **/
    public void build(){
        this.getBuilder()
                .nest(new JValue<>("type", this.type))
                .nest(new JObject("ingredient").nest(new JValue<>("item", this.ingredient)))
                .nest(new JValue<>("result", this.result))
                .nest(new JValue<>("count", count)).build();
    }
}
