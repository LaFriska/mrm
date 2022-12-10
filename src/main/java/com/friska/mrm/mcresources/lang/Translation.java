package com.friska.mrm.mcresources.lang;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.config.Config;

/**
 * This record is used to store data for each translation for a lang JSON file.
 * @param id ID of the translation, e.g "diamond_block", "magic_wand", "efficiency" or "deep_dark". Use the raw dot notation for miscellaneous translations.
 * @param translation The actual translation, e.g. when the ID is "diamond_block", you might want to add "Block of Diamond" as the translation.
 * **/
@ExpectModdersToAccess
public record Translation(String id, String translation) {
    public Translation buildSimpleKey(String type){
        return new Translation(type + "." + Config.getModID() + "." + this.id, this.translation);
    }
}
