package com.friska.mrm.mcresources.lang;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.config.Config;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;

/**
 * This record is used to store data for a key-value pair for a JSON file.
 * **/
@ExpectAccess
public record KeyValue(@Nonnull String key, @Nonnull String value) {
    public KeyValue buildSimpleTranslationKey(String type){
        return new KeyValue(type + "." + Config.getModID() + "." + this.key, this.value);
    }

    public JValue<String> toJValue(){
        return new JValue<>(this.key, this.value);
    }

}
