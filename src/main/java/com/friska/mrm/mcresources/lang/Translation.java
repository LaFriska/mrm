package com.friska.mrm.mcresources.lang;

import com.friska.mrm.config.Config;

import java.lang.reflect.Field;

/**
 * This record is used for Lang addBlock, addItem, etc. overloads, that could take in a large amount of translations with just 1 method call.
 * **/
public record Translation(String id, String translation) {
    public Translation buildSimpleKey(String type){
        return new Translation(type + "." + Config.getModID() + "." + this.id, this.translation);
    }
}
