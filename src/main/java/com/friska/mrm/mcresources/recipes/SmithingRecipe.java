package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.ExpectModdersToAccess;

import javax.annotation.Nonnull;

@ExpectModdersToAccess
public class SmithingRecipe extends Recipe{
    private String base;
    private String addition;
    /**
     *
     * **/
    public SmithingRecipe(@Nonnull String base, @Nonnull String addition, @Nonnull String result){
        super();
        this.base = base;
        this.addition = addition;
        this.result = result;
    }

}
