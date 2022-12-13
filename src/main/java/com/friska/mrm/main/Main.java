package com.friska.mrm.main;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.ItemIDs;
import com.friska.mrm.mcresources.data.LanguageCodes;
import com.friska.mrm.mcresources.lang.KeyValue;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.models.BlockModel;
import com.friska.mrm.mcresources.recipes.cooking.BlastingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmeltingRecipe;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShaped;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShapeless;
import com.friska.mrm.registries.BlockModelRegistry;
import com.friska.mrm.registries.ResourceManager;
import com.friska.mrm.templates.TreeBlocks;

public class Main {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        CraftingShapeless craftingShapeless = new CraftingShapeless(ItemIDs.BEACON).addIngredient(ItemIDs.OBSIDIAN, 3).addIngredient(ItemIDs.NETHER_STAR);

        new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_log_horizontal").logHorizontal("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_leaves").leaves("ruby_leaves");
        new BlockModel("ruby_wood").wood("ruby_wood");
        new BlockModel("ruby_wood_horizontal").woodHorizontal("ruby_wood");

        Lang lang = new Lang(LanguageCodes.AMERICAN_ENGLISH);
        lang.addBiome("infection", "The Infection").addItem("cock", "Cock").addBlocks(
                new KeyValue("uranium", "Uranium Ore"),
                new KeyValue("dirt", "Block of Dirt"),
                new KeyValue("cup", "The Cup")
        );

        ResourceManager.register(
                new SmeltingRecipe(ItemIDs.SADDLE, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new BlastingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new CraftingShaped("xxx", "xox","xxx").setCount(3).addKey('x', ItemIDs.NETHER_STAR).addKey('o', ItemIDs.REDSTONE_DUST).setResult(ItemIDs.OBSIDIAN).setGroup("lmao"),
                new CraftingShapeless(ItemIDs.NOTE_BLOCK).addIngredient(ItemIDs.REDSTONE_DUST).setCount(3).addIngredient(ItemIDs.OBSIDIAN),
                new BlockModel("uranium_block").defaultCube(),
                lang
        );

        ResourceManager.buildAll();
    }
}
