package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.interfaces.JObjectBuildable;
import com.friska.mrm.system.util.KeyValue;
import com.friska.mrm.system.serialiser.builder.JObject;

import javax.annotation.Nullable;

@ExpectAccess
public record Condition(KeyValue<String>... keyValues) implements JObjectBuildable {

    /**
     * A condition is used in multiparted block states JSON objects to denote when a model should be pointed to. A condition has multiple KeyValue pairs,
     * and only when all KeyValues are true, will the model be pointed to.
     * **/
    @SafeVarargs
    public Condition {}

    /**
     * Builds to a JObject.
     * **/
    @Override
    public JObject toJObject(@Nullable String key){
        JObject jObject = new JObject(key);
        for (KeyValue<String> keyValue : keyValues) {
            jObject.nest(keyValue.toJValue());
        }
        return jObject;
    }
}
