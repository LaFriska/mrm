package com.friska.mrm.serialiser.builder;

import com.friska.mrm.util.Util;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class JArray extends Nestable implements JProperty {

    protected List<Object> properties;

    public JArray(@Nullable String key) {
        super(key);
        this.propertiesData = new ArrayList<>();
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
                    System.out.println(((JProperty) o).getData() + "123123");
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

    public JArray setArray(@Nullable ArrayList<JObject> jObjectsArrayList){
        return this.setArray(Util.getJObjectArray(jObjectsArrayList));
    }

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
