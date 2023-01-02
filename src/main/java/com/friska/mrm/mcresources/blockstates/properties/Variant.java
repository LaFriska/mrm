package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.serialiser.builder.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

//TODO javadoc
@ExpectAccess
public class Variant {

    private final ArrayList<String> conditions = new ArrayList<>();
    private final ArrayList<ModelPointer> modelPointers = new ArrayList<>();

    public Variant(@Nullable String... conditions){
        if(conditions == null){
            this.conditions.add("");
        }else {
            this.conditions.addAll(List.of(conditions));
        }
    }

    public Variant addModelPointer(ModelPointer modelPointer){
        this.modelPointers.add(modelPointer);
        return this;
    }

    public Variant addModelPointer(@Nonnull String textureName, boolean isModded){
        this.modelPointers.add(new ModelPointer(textureName, isModded));
        return this;
    }

    public Variant addModelPointers(ModelPointer... modelPointers){
        this.modelPointers.addAll(List.of(modelPointers));
        return this;
    }

    @NeedsRevision("Inefficient")
    public JProperty build(){
        StringBuilder nameBuilder = new StringBuilder();

        for(int i = 0; i <= conditions.size() - 1; i++){
            if(i != 0) nameBuilder.append(",");
            nameBuilder.append(conditions.get(i));
        }

        String name = nameBuilder.toString();

        System.out.println("TEST " + name);

        if(modelPointers.size() == 1){
            return modelPointers.get(0).build(name);
        }else{
            JArray jArray = new JArray(name);
            ArrayList<JObject> modelPointerBuilds = new ArrayList<>();
            this.modelPointers.forEach((m) -> modelPointerBuilds.add(m.build(name)));
            jArray.setArray(List.of(modelPointerBuilds).toArray());
            return jArray;
        }
    }
}
