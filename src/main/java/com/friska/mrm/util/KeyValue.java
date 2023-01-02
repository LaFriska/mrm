package com.friska.mrm.util;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.config.Config;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;

//TODO javadoc

/**
 * This record is used to store data for a key-value pair for a JSON file.
 * **/
@ExpectAccess
public class KeyValue<T> {

    private String key;
    private T value;

    public KeyValue(@Nonnull String key, @Nonnull T value){
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValueString() {
        if (value instanceof String) return (String) value;
        return value.toString();
    }

    public T getValue() {
        return value;
    }

    public KeyValue buildSimpleTranslationKey(String type){
        return new KeyValue(type + "." + Config.getModID() + "." + this.key, this.value);
    }

    public JValue<T> toJValue(){
        return new JValue<>(this.key, this.value);
    }

}
