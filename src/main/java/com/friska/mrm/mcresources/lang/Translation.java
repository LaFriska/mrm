package com.friska.mrm.mcresources.lang;

import com.friska.mrm.config.Config;

/**
 * This record is used to store data for each translation for a lang JSON file.
 * **/
public record Translation(String id, String translation) {

    public Translation buildSimpleKey(String type){
        return new Translation(type + "." + Config.getModID() + "." + this.id, this.translation);
    }
}
