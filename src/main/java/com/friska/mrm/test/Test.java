package com.friska.mrm.test;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.LanguageCodes;
import com.friska.mrm.mcresources.data.ItemIDs;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.lang.Translation;
import com.friska.mrm.mcresources.recipes.SmithingRecipe;
import com.friska.mrm.mcresources.recipes.StoneCuttingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.BlastingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.CampfireRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmeltingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmokingRecipe;
import com.friska.mrm.registries.ResourceManager;

public class Test {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        Lang lang = new Lang(LanguageCodes.AMERICAN_ENGLISH);
        lang.addBlocks(
                new Translation("potato", "Potato"),
                new Translation("tomato", "Tomato Test e")
        );

        ResourceManager.register(


                new StoneCuttingRecipe("#minecraft:stone", ItemIDs.ACACIA_BOAT, 3),
                new CampfireRecipe("#minecraft:campfire", ItemIDs.BAKED_POTATO, 0.1F),
                new BlastingRecipe("#minecraft:blasting", ItemIDs.BLOCK_OF_IRON, 2),
                new SmeltingRecipe("#minecraft:smelting", ItemIDs.BLOCK_OF_IRON, 2, 25),
                new SmokingRecipe("#minecraft:smoking", ItemIDs.BLOCK_OF_IRON, 2, 2),
                new SmithingRecipe("#minecraft:smithingbase", ItemIDs.BLOCK_OF_IRON, ItemIDs.BAKED_POTATO),
                new SmithingRecipe(ItemIDs.getModdedID("moditem"), "#minecraft:smithing_addition", ItemIDs.BAKED_POTATO),
                new SmithingRecipe("#minecraft:smithing_addition", "#minecraft:smithing_addition", ItemIDs.BAKED_POTATO)
        );



        ResourceManager.buildAll();
    }
}
