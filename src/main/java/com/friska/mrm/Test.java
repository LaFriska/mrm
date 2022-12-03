package com.friska.mrm;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.MinecraftID;
import com.friska.mrm.mcresources.recipes.SmeltingRecipe;
import com.friska.mrm.registries.ResourceManager;

public class Test {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");
        SmeltingRecipe smeltingRecipe = new SmeltingRecipe(MinecraftID.ACACIA_BOAT, MinecraftID.ACACIA_LOG, 1);
        SmeltingRecipe smeltingRecipe1 = new SmeltingRecipe(MinecraftID.ACACIA_BOAT, MinecraftID.ACACIA_LOG, 1);
        SmeltingRecipe smeltingRecipe2 = new SmeltingRecipe(MinecraftID.ACACIA_BOAT, MinecraftID.ACACIA_LOG, 1);
        SmeltingRecipe smeltingRecipe3 = new SmeltingRecipe(MinecraftID.ACACIA_BOAT, MinecraftID.ACACIA_LOG, 1);

        ResourceManager.register(
                smeltingRecipe,
                smeltingRecipe1,
                smeltingRecipe2,
                smeltingRecipe3
        );


        ResourceManager.buildAll();
    }
}
