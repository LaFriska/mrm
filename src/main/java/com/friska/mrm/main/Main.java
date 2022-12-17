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
import com.friska.mrm.registries.ResourceManager;

public class Main {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        CraftingShapeless craftingShapeless = new CraftingShapeless(ItemIDs.BEACON).addIngredient(ItemIDs.OBSIDIAN, 3).addIngredient(ItemIDs.NETHER_STAR);

        new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_log_horizontal").logHorizontal("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_leaves").leaves("ruby_leaves");
        new BlockModel("ruby_wood").wood("ruby_wood");
        new BlockModel("ruby_wood_horizontal").woodHorizontal("ruby_wood");

        Lang en = new Lang(LanguageCodes.AMERICAN_ENGLISH).addBlocks(
                new KeyValue("test1", "Test1"),
                new KeyValue("test2", "Test2"),
                new KeyValue("test3", "Test3")
        );

        Lang en2 = new Lang(LanguageCodes.AMERICAN_ENGLISH).addBlocks(
                new KeyValue("test4", "Test4"),
                new KeyValue("test5", "Test5"),
                new KeyValue("test6", "Test6")
        );

        Lang en3 = new Lang(LanguageCodes.AMERICAN_ENGLISH).addBlocks(
                new KeyValue("test7", "Test7"),
                new KeyValue("test8", "Test8"),
                new KeyValue("test9", "Test9")
        );

        Lang de = new Lang(LanguageCodes.DANISH).addBlocks(
                new KeyValue("test1", "Danish Test1"),
                new KeyValue("test2", "Danish Test2"),
                new KeyValue("test3", "Danish Test3")
        );

        Lang de1 = new Lang(LanguageCodes.DANISH).addBlocks(
                new KeyValue("test12", "Dani3252sh Test1"),
                new KeyValue("test23", "Danis235h Test2"),
                new KeyValue("test34", "Danis235h Test3")
        );

        Lang fre = new Lang(LanguageCodes.FRENCH).addBlocks(
                new KeyValue("test1AAAA", "Test1"),
                new KeyValue("test2AA", "Test2"),
                new KeyValue("test3A", "Test3")
        );

        Lang fre1 = new Lang(LanguageCodes.FRENCH).addBlocks(
                new KeyValue("test1B", "Test1"),
                new KeyValue("test2B", "Test2"),
                new KeyValue("test3B", "Test3")
        );
        Lang fre2 = new Lang(LanguageCodes.FRENCH).addBlocks(
                new KeyValue("test1C", "Test1"),
                new KeyValue("test2C", "Test2"),
                new KeyValue("test3C", "Test3")
        );
        Lang fre3 = new Lang(LanguageCodes.FRENCH).addBlocks(
                new KeyValue("test1D", "Test1"),
                new KeyValue("test2D", "Test2"),
                new KeyValue("test3D", "Test3")
        );

        ResourceManager.register(
                new SmeltingRecipe(ItemIDs.SADDLE, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new BlastingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new CraftingShaped("xxx", "xox","xxx").setCount(3).addKey('x', ItemIDs.NETHER_STAR).addKey('o', ItemIDs.REDSTONE_DUST).setResult(ItemIDs.OBSIDIAN).setGroup("lmao"),
                new CraftingShapeless(ItemIDs.NOTE_BLOCK).addIngredient(ItemIDs.REDSTONE_DUST).setCount(3).addIngredient(ItemIDs.OBSIDIAN),
                new BlockModel("uranium_block", false).defaultCube().log("uranium_end", "uranium_side"),
                en,
                en2,
                en3,
                de,
                de1,
                fre,
                fre2,
                fre3
        );

        ResourceManager.buildAll();
    }
}
