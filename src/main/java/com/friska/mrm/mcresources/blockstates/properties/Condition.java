package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.util.KeyValue;
import com.friska.mrm.serialiser.builder.JObject;

import javax.annotation.Nullable;
//TODO javadoc
public record Condition(KeyValue... keyValues) {
    public JObject toJObject(@Nullable String name){
        JObject jObject = new JObject(name);
        for (KeyValue keyValue : keyValues) {
            jObject.nest(keyValue.toJValue());
        }
        return jObject;
    }

    public JObject toJObject(){
        return toJObject(null);
    }

}
