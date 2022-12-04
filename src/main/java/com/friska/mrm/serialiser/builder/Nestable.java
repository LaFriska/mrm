package com.friska.mrm.serialiser.builder;

import com.friska.mrm.exceptions.InvalidJSONStringException;

import javax.annotation.Nullable;
import java.util.ArrayList;

public abstract class Nestable {

    public Nestable(@Nullable String key){

        String check;

        if(key != null) {
            check = JSONBuilderUtils.checkJSONString(key);
            if (!check.equals("pass")) {
                throw new InvalidJSONStringException(check);
            }
        }

        this.key = key;
        this.propertyDataClip = new StringBuilder();
        this.properties = new ArrayList<>();
        this.propertiesData = new ArrayList<>();
    }
    protected String data;
    protected String key;

    protected ArrayList<JProperty> properties;
    protected ArrayList<String> propertiesData;

    protected StringBuilder propertyDataClip;

    public void appendPropertyData(){
        this.propertyDataClip = new StringBuilder();
        String propertyData;
        boolean jbreak = false;
        for(int i = 0; i <= this.propertiesData.size() - 1; i++){
            propertyData = propertiesData.get(i);
            if(propertyData.equals("\n")){
                jbreak = true;
            }else {
                if (this.propertyDataClip.isEmpty()) {
                    this.propertyDataClip.append(propertyData);
                } else {
                    if(jbreak) {
                        this.propertyDataClip.append(",\n\n").append(propertyData);
                        jbreak = false;
                    }else{
                        this.propertyDataClip.append(",\n").append(propertyData);
                    }
                }
            }
        }
    }

    protected String appendNull(String brackets){
        return this.key == null ? brackets : JSONBuilderUtils.wrapInString(this.key) + ": " + brackets;
    }

    /**
     * @return raw string format of the particular JSON property, or the JSON as a whole.
     */
    public String getData(){
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }
}