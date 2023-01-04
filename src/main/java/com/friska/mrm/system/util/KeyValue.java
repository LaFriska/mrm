package com.friska.mrm.system.util;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.serialiser.builder.JValue;

import javax.annotation.Nonnull;

//TODO javadoc

/**
 * This record is used to store data for a key-value pair for a JSON file.
 **/
@ExpectAccess
public record KeyValue<T>(String key, T value) {

    public KeyValue(@Nonnull String key, @Nonnull T value) {
        this.key = key;
        this.value = value;
    }

    public KeyValue<String> forceToString(){
        return new KeyValue<>(key, value.toString());
    }

    public String getValueString() {
        if (value instanceof String) return (String) value;
        return value.toString();
    }

    public KeyValue<String> buildSimpleTranslationKey(String type) {
        return new KeyValue<>(type + "." + Config.getModID() + "." + this.key, this.value.toString());
    }

    public JValue<T> toJValue() {
        return new JValue<>(this.key, this.value);
    }

}
