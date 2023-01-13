package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.serialiser.builder.JObject;
import com.friska.mrm.system.serialiser.builder.JValue;

import javax.annotation.Nonnull;
@ExpectAccess
public class StoneCuttingRecipe extends Recipe{

    private String ingredient;
    private int count;

    /**
     * This class creates a stone cutting recipe for stone cutters.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param ingredient The item ID you input into a stone cutter.
     * @param result The item ID of the output from a stone cutter.
     * **/
    public StoneCuttingRecipe(@Nonnull String ingredient, @Nonnull String result){
        this(ingredient, result, 1);
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
        this.ingredient = ingredient;
        this.result = result;
        this.type = "minecraft:stonecutting";
        setAndTruncateName(result);
        //createBuilder();
        this.count = count;
    }

    /**
     * Sets the count of result output from the stone cutting recipe.
     * @param count The number of results output from the stone cutter, do not call this method if you wish to default the count as 1.
     * **/
    public StoneCuttingRecipe setCount(int count) {
        this.count = count;
        return this;
    }

    /**
     * Builds the JSON file. <b>Calling this method directly from resources is very risky and unsafe, please instantiate a ResourceManager object and register resource there.</b>
     * **/
    @Override
    public void build(){
        super.build();
        String ingredientType = getIDType(this.ingredient);
        if(ingredientType.equals("tag")) this.ingredient = this.ingredient.substring(1);
        this.getBuilder()
                .nest(new JValue<>("type", this.type))
                .nest(new JObject("ingredient").nest(new JValue<>(ingredientType, this.ingredient)))
                .nest(new JValue<>("result", this.result))
                .nest(new JValue<>("count", count)).build();
    }
}
