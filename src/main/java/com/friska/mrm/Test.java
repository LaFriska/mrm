package com.friska.mrm;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.lang.LanguageCodes;
import com.friska.mrm.mcresources.lang.Translation;
import com.friska.mrm.registries.LangRegistry;

public class Test {

    public static void main(String... args) {

        Config.setModID("pogmod");

        Lang lang = new Lang(LanguageCodes.AMERICAN_ENGLISH);
        lang.addBlocks(
                new Translation("uranium", "Block of Uranium"),
                new Translation("diamond_slab", "Diamond Slab"),
                new Translation("flesh", "Block of Flesh")
        );
        lang.addBlock("test", "Test");
        lang.addItems(
                new Translation("uranium_sword", "Uranium Sword"),
                new Translation("uranium_axe", "Uranium Axe")
        );

        Lang lang1 = new Lang(LanguageCodes.AMERICAN_ENGLISH);
        lang1.addItem("potato", "Potato");
        lang1.addBlocks(
                new Translation("testblock", "Test Block"),
                new Translation("computer", "Computer")
        );
        lang1.addInstruments(
                new Translation("trumpet", "Trumpet"),
                new Translation("french_horn", "French Horn")
        );

        LangRegistry.register(lang1);
        LangRegistry.register(lang);

        LangRegistry.build();


    }

}
