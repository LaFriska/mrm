package com.friska.mrm;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.LanguageCodes;
import com.friska.mrm.mcresources.data.MinecraftID;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.lang.Translation;
import com.friska.mrm.mcresources.recipes.SmeltingRecipe;
import com.friska.mrm.registries.LangRegistry;
import com.friska.mrm.registries.ResourceManagers;

public class Test {

    public static void main(String... args) {

        Config.setModID("bourgeoismod");

        SmeltingRecipe smeltingRecipe = new SmeltingRecipe(MinecraftID.ACACIA_BOAT, MinecraftID.ACACIA_BOAT, 2, 100);
        smeltingRecipe.build();
    }
}
