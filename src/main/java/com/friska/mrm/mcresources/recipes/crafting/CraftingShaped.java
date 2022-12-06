package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.annotations.ExpectModdersToAccess;

@ExpectModdersToAccess
public class CraftingShaped extends Crafting {

    private final CraftingRow row1;
    private final CraftingRow row2;
    private final CraftingRow row3;

    /**
     * Create instances of the CraftingRow record as params to construct CraftingRecipe. Each CraftingRow can contain 1 - 3 items, to represent a row in a crafting recipe formation.
     * Parse null if a row should be empty, or use the constructor overload and only parse 1 or 2 params.
     * @param result item id of the result.
     * **/
    public CraftingShaped(CraftingRow row1, CraftingRow row2, CraftingRow row3, String result){
        super(result);
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = row3;
    }
    /**
     * Create instances of the CraftingRow record as params to construct CraftingRecipe. Each CraftingRow can contain 1 - 3 items, to represent a row in a crafting recipe formation.
     * Parse null if a row should be empty, or use the constructor overload and only parse 1 or 2 params.
     * @param result item id of the result.
     * **/
    public CraftingShaped(CraftingRow row1, CraftingRow row2, String result){
        super(result);
        this.row1 = row1;
        this.row2 = row2;
        this.row3 = null;
    }
    /**
     * Create instances of the CraftingRow record as params to construct CraftingRecipe. Each CraftingRow can contain 1 - 3 items, to represent a row in a crafting recipe formation.
     * Parse null if a row should be empty, or use the constructor overload and only parse 1 or 2 params.
     * @param result item id of the result.
     * **/
    public CraftingShaped(CraftingRow row1, String result){
        super(result);
        this.row1 = row1;
        this.row2 = null;
        this.row3 = null;
    }


}
