package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;

@ExpectModdersToAccess
public class SmithingRecipe extends Recipe{
    private String base;
    private String addition;
    private String baseType;
    private String additionType;
    /**
     * This class creates a smithing recipe for the smithing table.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param base The base Item ID for the smithing table (the one to the left).
     * @param addition ID of the item you add to the base.
     * @param result The output from the smithing table.
     * **/
    public SmithingRecipe(@Nonnull String base, @Nonnull String addition, @Nonnull String result){
        super();
        if(checkForTags(base, result)){
            this.baseType = "tag";
            this.base = base.substring(1);
        }else{
            this.baseType = "item";
            this.base = base;
        }
        if(checkForTags(addition, result)){
            this.additionType = "tag";
            this.addition = addition.substring(1);
        }else{
            this.additionType = "item";
            this.addition = addition;
        }
        this.result = result;
        this.type = "minecraft:smithing";
        this.setName(result);
        initiateBuilder();
    }

    /**
     * Builds the JSON file.
     * **/
    public void build(){
        getBuilder()
                .nest(new JValue<>("type", type))
                .nest(new JObject("base").nest(new JValue<>(baseType, base)))
                .nest(new JObject("addition").nest(new JValue<>(additionType, addition)))
                .nest(new JObject("result").nest(new JValue<>("item", result)))
                .build()
        ;
    }

}
