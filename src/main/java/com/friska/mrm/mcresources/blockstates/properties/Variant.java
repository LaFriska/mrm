package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.annotations.NeedsRevision;
import com.friska.mrm.system.serialiser.builder.JArray;
import com.friska.mrm.system.serialiser.builder.JObject;
import com.friska.mrm.system.serialiser.builder.JProperty;
import com.friska.mrm.system.util.Util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

//TODO javadoc
@ExpectAccess
public class Variant {

    private final ArrayList<String> conditions = new ArrayList<>();
    private final ArrayList<ModelPointer> modelPointers = new ArrayList<>();

    /**
     * A variant in terms of block states contains one or a set of conditions, and if the conditions are true, a block model will be pointed to.
     * @param conditions These conditions are named in your java code for your mod, they are likely to be block state variables. For example, "LIT=true" may be a valid condition for a redstone lamp.
     *                   <b>Leave this param as an empty string, or null casted as a string array in case that no conditions are needed. </b>
     * **/
    public Variant(@Nullable String... conditions){
        if(conditions == null){
            this.conditions.add("");
        }else {
            this.conditions.addAll(List.of(conditions));
        }
    }

    /**
     * This method adds a model pointer. It is needed to specify at least one model pointer in the case that the conditions are met.
     * **/
    public Variant addModelPointer(ModelPointer modelPointer){
        this.modelPointers.add(modelPointer);
        return this;
    }

    /**
     * This method adds a model pointer. It is needed to specify at least one model pointer in the case that the conditions are met.
     * **/
    public Variant addModelPointer(@Nonnull String textureName, boolean isModded){
        this.modelPointers.add(new ModelPointer(textureName, isModded));
        return this;
    }

    /**
     * This method adds multiple model pointers. It is needed to specify at least one model pointer in the case that the conditions are met.
     * **/
    public Variant addModelPointers(ModelPointer... modelPointers){
        this.modelPointers.addAll(List.of(modelPointers));
        return this;
    }

    @NeedsRevision("Inefficient, messy as fuck")
    public JProperty build(){
        StringBuilder nameBuilder = new StringBuilder();

        for(int i = 0; i <= conditions.size() - 1; i++){
            if(i != 0) nameBuilder.append(",");
            nameBuilder.append(conditions.get(i));
        }

        String name = nameBuilder.toString();

        if(modelPointers.size() == 1){
            return modelPointers.get(0).toJObject(name);
        }else{
            JArray jArray = new JArray(name);
            jArray.setArray(Util.getJObjectArray(this.modelPointers));
            return jArray;
        }
    }
}
