package com.friska.mrm.main;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.ItemIDs;
import com.friska.mrm.mcresources.recipes.cooking.BlastingRecipe;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShapeless;
import com.friska.mrm.registries.ResourceManager;

public class Main {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        CraftingShapeless craftingShapeless = new CraftingShapeless(ItemIDs.BEACON).addIngredient(ItemIDs.OBSIDIAN, 3).addIngredient(ItemIDs.NETHER_STAR);

        ResourceManager.register(
                new BlastingRecipe(ItemIDs.BEACON, ItemIDs.OBSIDIAN, 2),
                new BlastingRecipe(ItemIDs.RABBIT_HIDE, ItemIDs.OBSIDIAN, 2),
                new BlastingRecipe(ItemIDs.SADDLE, ItemIDs.OBSIDIAN, 2),
                craftingShapeless
        );

        ResourceManager.buildAll();
    }
}
