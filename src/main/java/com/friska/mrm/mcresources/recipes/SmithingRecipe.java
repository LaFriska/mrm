package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.config.Config;
import com.friska.mrm.exceptions.ResourcePathException;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;

@ExpectAccess
public class SmithingRecipe extends Recipe{
    private String base;
    private String addition;
    /**
     * This class creates a smithing recipe for the smithing table.
     * (To indicate that an ID is a tag, prefix it with a hashtag: e.g "#minecraft:logs" or "#coolmod:amogus_woods")
     * @param base The base Item ID for the smithing table (the one to the left).
     * @param addition ID of the item you add to the base.
     * @param result The output from the smithing table.
     * **/
    public SmithingRecipe(@Nonnull String base, @Nonnull String addition, @Nonnull String result){
        super();
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.type = "minecraft:smithing";
        this.setAndTruncateName(result);
    }

    /**
     * Builds the JSON file.
     * **/
    @Override
    public void build(){
        super.build();

        String baseType = getIDType(this.base);
        if(baseType.equals("tag")) this.base = this.base.substring(1);
        String additionType = getIDType(this.addition);
        if(baseType.equals("tag")) this.addition = this.addition.substring(1);

        getBuilder()
                .nest(new JValue<>("type", type))
                .nest(new JObject("base").nest(new JValue<>(baseType, base)))
                .nest(new JObject("addition").nest(new JValue<>(additionType, addition)))
                .nest(new JObject("result").nest(new JValue<>("item", result)))
                .build()
        ;
    }

}
