package com.friska.mrm.test;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.LanguageCodes;
import com.friska.mrm.mcresources.data.IDs;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.lang.Translation;
import com.friska.mrm.mcresources.recipes.SmithingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmokingRecipe;
import com.friska.mrm.registries.ResourceManager;

public class Test {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        Lang lang = new Lang(LanguageCodes.AMERICAN_ENGLISH);
        lang.addBlocks(
                new Translation("potato", "Potato"),
                new Translation("cum", "Cum")
        );

        ResourceManager.register(

                new SmokingRecipe(IDs.POTATO, IDs.BAKED_POTATO, 0.35F, 300),
                new SmokingRecipe(IDs.POISONOUS_POTATO, IDs.BAKED_POTATO, 0.35F, 300),
                new SmokingRecipe(IDs.END_PORTAL_FRAME, IDs.BAKED_POTATO, 0.35F, 300),
                new SmokingRecipe(IDs.IRON_AXE, IDs.BAKED_POTATO, 0.35F, 300),
                new SmokingRecipe(IDs.ACACIA_BUTTON, IDs.BAKED_POTATO, 0.35F, 300),
                new SmokingRecipe(IDs.POTATO, IDs.BAKED_POTATO, 0.35F, 300)

                /*new CampfireRecipe(MinecraftID.POTATO, MinecraftID.BAKED_POTATO, 0.1F),
                new BlastingRecipe(MinecraftID.IRON_AXE, MinecraftID.BLOCK_OF_IRON, 2),
                new SmeltingRecipe(MinecraftID.IRON_AXE, MinecraftID.BLOCK_OF_IRON, 2, 25),*/
        );



        ResourceManager.buildAll();
    }
}
