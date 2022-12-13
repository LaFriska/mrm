package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.mcresources.recipes.Recipe;

import javax.annotation.Nullable;

@SuppressWarnings("unchecked")
public abstract class Crafting<T extends Crafting<T>> extends Recipe {

    //Count and group are not necessarily defined in crafting recipes.
    protected @Nullable Integer count = null;
    protected @Nullable String group = null;
    protected Crafting(String result){
        super();
        this.type = "minecraft:crafting";
        this.result = result;
    }

    /**
     * Sets the count of result output from the crafting recipe.
     * @param count The number of results output from the crafting table, do not call this method, or simply parse null through it if you wish to leave the count undefined (which would be defaulted to 1 when Minecraft reads the JSON).
     * **/
    public T setCount(@Nullable Integer count) {
        this.count = count;
        return (T) this;
    }

    /**
     * Sets the group of your recipe. For example, all wooden door crafting recipes are grouped under "wooden_door"
     * @param group parse the String ID of the group.
     * **/
    public T setGroup(@Nullable String group) {
        this.group = group;
        return (T) this;
    }
}
