package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
@ExpectModdersToAccess
public class StoneCuttingRecipe extends Recipe{

    private String ingredient;
    private int count = 1;

    /**
     * @param ingredient The item ID you input into a stonecutter.
     * @param result The item ID of the output from a stonecutter.
     * **/
    public StoneCuttingRecipe(@Nonnull String ingredient, @Nonnull String result){
        super();
        initialise(ingredient, result);
    }
    /**
     * @param ingredient The item ID you input into a stonecutter.
     * @param result The item ID of the output from a stonecutter.
     * @param count The number of result items.
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
