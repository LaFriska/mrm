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
    protected String ingredientType;

    /**
     * This class creates a stone cutting recipe for stone cutters.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID you input into a stone cutter.
     * @param result The item ID of the output from a stone cutter.
     * **/
    public StoneCuttingRecipe(@Nonnull String ingredient, @Nonnull String result){
        super();
        initialise(ingredient, result);
    }
    /**
     * This class creates a stone cutting recipe for stone cutters.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID you input into a stone cutter.
     * @param result The item ID of the output from a stone cutter.
     * @param count The number of result items.
     * **/
    public StoneCuttingRecipe(@Nonnull String ingredient, @Nonnull String result, int count){
        super();
        initialise(ingredient, result);
        this.count = count;
    }
    private void initialise(@NotNull String ingredient, @NotNull String result) {
        if(checkForTags(ingredient, result)){
            this.ingredientType = "tag";
            this.ingredient = ingredient.substring(1);
        }else{
            this.ingredientType = "item";
            this.ingredient = ingredient;
        }
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
                .nest(new JObject("ingredient").nest(new JValue<>(this.ingredientType, this.ingredient)))
                .nest(new JValue<>("result", this.result))
                .nest(new JValue<>("count", count)).build();
    }
}
