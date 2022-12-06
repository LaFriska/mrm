package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.annotations.ExpectModdersToAccess;

import javax.annotation.Nullable;

/**
 * The CraftingRecipe class takes in 1-3 instances of the CraftingRow record, and each CraftingRow represents a row in the crafting table, with each ID input
 * representing the items for the crafting recipe from left to right.
 * **/
@ExpectModdersToAccess
public record CraftingRow(@Nullable String id1, @Nullable String id2, @Nullable String id3) {
    public CraftingRow(String id1, String id2){
        this(id1, id2, null);
    }
    public CraftingRow(String id1){
        this(id1, null, null);
    }
}
