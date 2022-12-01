package com.friska.mrm.serialiser.builder;

import javax.annotation.Nullable;
import java.util.ArrayList;

public class JObject extends Nestable implements JProperty {

    public JObject(@Nullable String key) {
        super(key);
        construct();
    }

    public <T extends JProperty> JObject nest(T property){
        this.properties.add(property);
        construct();
        return this;
    }

    @Override
    public void construct() {
        if(!properties.isEmpty()) {
            this.propertiesData = new ArrayList<>();
            this.properties.forEach((p) -> this.propertiesData.add(p.getData()));
            appendPropertyData();
            if (this.key != null) {
                this.setData(JSONBuilderUtils.wrapInString(this.key) + ": " + JSONBuilderUtils.wrapInCurlyBraces(this.propertyDataClip.toString()));
            } else {
                this.setData(JSONBuilderUtils.wrapInCurlyBraces(this.propertyDataClip.toString()));
            }
        }else{
            setData(appendNull("{}"));
        }
    }

    @Override
    public JPropertyTypes getPropertyType(){
        return JPropertyTypes.OBJECT;
    }
}
