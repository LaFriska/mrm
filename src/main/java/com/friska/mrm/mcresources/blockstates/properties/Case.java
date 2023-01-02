package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.util.KeyValue;
import com.friska.mrm.serialiser.builder.JArray;
import com.friska.mrm.serialiser.builder.JObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

//TODO JAVADOC

@ExpectAccess
public class Case {

    private final ModelPointer modelPointer;

    //When the size of this ArrayList is 1, the OR statement is not needed in the JSON file.
    private final ArrayList<Condition> conditions;

    public Case(@Nonnull ModelPointer modelPointer){
        this.modelPointer = modelPointer;
        this.conditions = new ArrayList<>();
    }

    //When
    public Case addCondition(KeyValue... keyValue){
        conditions.add(new Condition(keyValue));
        return this;
    }

    public <T> Case addCondition(String key, T value){
        return this.addCondition(new KeyValue(key, value));
    }

    public Case addCondition(Condition condition){
        conditions.add(condition);
        return this;
    }

    public JObject build(){

        JObject result = new JObject(null).nest(modelPointer.build("apply"));
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
            conditions = new JObject("when").nest(new JArray("OR").setArray(getConditionJObjects()));
        }
        return conditions;

    }

    private ArrayList<JObject> getConditionJObjects(){
        ArrayList<JObject> jObjects = new ArrayList<>();
        for (int i = 0; i <= conditions.size() - 1; i++) {
            jObjects.add(conditions.get(i).toJObject());
        }
        return jObjects;
    }

}
