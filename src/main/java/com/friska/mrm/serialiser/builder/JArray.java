package com.friska.mrm.serialiser.builder;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class JArray extends Nestable implements JProperty {

    public JArray(@Nullable String key) {
        super(key);
    }

    public JArray setArray(@Nullable Object[] properties){
        this.propertiesData = new ArrayList<>();
        if(properties != null) {
            this.properties = List.of(properties);
            this.properties.forEach((o) -> {
                if (o instanceof String) {
                    this.propertiesData.add(JSONBuilderUtils.wrapInString((String) o));
                } else if (o instanceof JProperty){
                    this.propertiesData.add(((JProperty) o).getData());
                }else{
                    this.propertiesData.add(o.toString());
                }
            });
            construct();
        }else{
            setData(appendNull("[]"));
        }
        return this;
    }
    protected List<Object> properties;

    @Override
    public void construct() {
        appendPropertyData();
        if(this.key != null) {
            this.setData(JSONBuilderUtils.wrapInString(this.key) + ": " + JSONBuilderUtils.wrapInSquareBraces(this.propertyDataClip.toString()));
        }else{
            this.setData(JSONBuilderUtils.wrapInSquareBraces(this.propertyDataClip.toString()));
        }
    }

    @Override
    public JPropertyTypes getPropertyType() {
        return JPropertyTypes.ARRAY;
    }
}
