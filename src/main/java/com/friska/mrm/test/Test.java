package com.friska.mrm.test;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.ItemIDs;
import com.friska.mrm.mcresources.data.LanguageCodes;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.lang.Translation;
import com.friska.mrm.mcresources.recipes.crafting.CraftingKey;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShaped;
import com.friska.mrm.registries.ResourceManager;

public class Test {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        Lang lang = new Lang(LanguageCodes.AMERICAN_ENGLISH);
        lang.addBlocks(
                new Translation("potato", "Potato"),
                new Translation("tomato", "Tomato Test e")
        );

        CraftingShaped craftingShaped = new CraftingShaped("wr")
                .addKey('w', "#minecraft:plank")
                .addKey('r', ItemIDs.REDSTONE_DUST)
                .setResult(ItemIDs.NOTE_BLOCK)
                .setCount(5).setGroup("minecraft:music_stuff");
        CraftingShaped beacon = new CraftingShaped("ggg", "gng", "ooo").addKeys(
                new CraftingKey('g', ItemIDs.GLASS),
                new CraftingKey('n', ItemIDs.NETHER_STAR),
                new CraftingKey('o', ItemIDs.OBSIDIAN)
        ).setResult(ItemIDs.BEACON);

        CraftingShaped beacon1 = new CraftingShaped().setResult(ItemIDs.BEACON).setCount(2);

        ResourceManager.register(
                craftingShaped,
                beacon,
                beacon1
        );
        ResourceManager.buildAll();
    }
}
