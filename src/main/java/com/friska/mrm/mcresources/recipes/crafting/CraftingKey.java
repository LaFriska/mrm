package com.friska.mrm.mcresources.recipes.crafting;

/**
 * Record holding the value of a crafting key used in the crafting table grid.
 * @param key The char value of the key.
 * @param id The item/tag ID.
 * **/
public record CraftingKey(char key, String id) {
}
