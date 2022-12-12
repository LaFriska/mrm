package com.friska.mrm.main;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.ItemIDs;
import com.friska.mrm.mcresources.models.BlockModel;
import com.friska.mrm.mcresources.recipes.cooking.BlastingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmeltingRecipe;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShapeless;
import com.friska.mrm.registries.BlockModelRegistry;
import com.friska.mrm.registries.ResourceManager;

public class Main {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        CraftingShapeless craftingShapeless = new CraftingShapeless(ItemIDs.BEACON).addIngredient(ItemIDs.OBSIDIAN, 3).addIngredient(ItemIDs.NETHER_STAR);

        BlockModelRegistry.register(new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end"));
        BlockModelRegistry.register(new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end"));
        BlockModelRegistry.register(new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end"));
        BlockModelRegistry.register(new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end"));
        BlockModelRegistry.build();

        new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_log_horizontal").logHorizontal("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_leaves").leaves("ruby_leaves");
        new BlockModel("ruby_wood").wood("ruby_wood");
        new BlockModel("ruby_wood_horizontal").woodHorizontal("ruby_wood");

        ResourceManager.register(
                new SmeltingRecipe(ItemIDs.SADDLE, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new BlastingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3)
        );

        ResourceManager.buildAll();
    }
}
