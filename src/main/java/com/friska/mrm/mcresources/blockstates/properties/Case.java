package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.interfaces.JObjectBuildable;
import com.friska.mrm.system.util.KeyValue;
import com.friska.mrm.system.serialiser.builder.JArray;
import com.friska.mrm.system.serialiser.builder.JObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ExpectAccess
public class Case implements JObjectBuildable {

    private final ModelPointer modelPointer;

    //When the size of this ArrayList is 1, the OR statement is not needed in the JSON file.
    private final ArrayList<Condition> conditions;

    /**
     * A case in a multipart block state file is a pair of a "when" and "apply" objects. Essentially, it is a conditional statement that applies the model pointer given one, more multiple conditions.
     * Each condition requires one or multiple key-value pairs, and when all of the key-values is true, the condition is true. Having multiple conditions imply an OR statement, meaning that if any of the conditions are true, the model will be applied.
     *
     * @param modelPointer The model that is pointed to when the conditions are met.
     * **/
    public Case(@Nonnull ModelPointer modelPointer){
        this.modelPointer = modelPointer;
        this.conditions = new ArrayList<>();
    }

    //When

    /**
     * Adds a condition with multiple KeyValue pairs.
     *
     * @param keyValues Parse one or multiple instances of KeyValue. Each of the parsed keyValue vararg will be automatically converted into a String type parameter.
     * **/
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public final <T> Case addCondition(KeyValue<T>... keyValues){
        KeyValue<String>[] keyValueStrings = new KeyValue<>[keyValues.length];
        for (int i = 0; i <= keyValues.length - 1; i++) {
            keyValueStrings[i] = keyValues[i].forceToString();
        }
        conditions.add(new Condition(keyValueStrings));
        return this;
    }

    /**
     * Adds a condition with only one KeyValue pair.
     *
     * @param key The key of a condition.
     * @param value The value of the condition will be forced into a String automatically.
     * **/
    public <T> Case addCondition(String key, T value){
        return this.addCondition(new KeyValue<>(key, value).forceToString());
    }

    /**
     * Adds a custom condition.
     *
     * @param condition The condition object, instantiated using Condition.
     * **/
    public Case addCondition(Condition condition){
        conditions.add(condition);
        return this;
    }

    /**
     * Adds multiple conditions.
     *
     * @param conditions The condition objects, instantiated using Condition.
     * **/
    public Case addCondition(Condition... conditions){
        this.conditions.addAll(List.of(conditions));
        return this;
    }

    /**
     * Builds this case to a JObject.
     * **/
    @Override
    public JObject toJObject(String key){

        JObject result = new JObject(key).nest(modelPointer.toJObject("apply"));
        JObject conditions = buildConditionsJObject();
        if(conditions != null) result.nest(conditions);

        return result;
    }

    private @Nullable JObject buildConditionsJObject(){

        JObject conditions;

        if(this.conditions.size() == 1){
            conditions = this.conditions.get(0).toJObject("when");
        }else if (this.conditions.size() == 0){
            return null;
        }else{
            conditions = new JObject("when").nest(new JArray("OR").setArray(this.conditions));
        }
        return conditions;

    }
}
