package com.friska.mrm.serialiser.builder;

import org.jetbrains.annotations.Nullable;
import javax.annotation.Nonnull;

public class JValue <T> implements JProperty {

    private T value;
    private String data;
    private String key;

    public JValue(@Nonnull String key, @Nullable T value){
        this.key = key;
        this.value = value;
        construct();
    }

    public JValue(@Nonnull String customData){
        this.data = customData;
    }

    public void set(String customData){
        this.data = customData;
    }
    public void construct(){
        String key = JSONBuilderUtils.wrapInString(this.key);
        String value;
        if(this.value instanceof String){
            value = JSONBuilderUtils.wrapInString((String) this.value);
        }else if (this.value != null){
            value = this.value.toString();
        }else{
            value = "null";
        }
        this.data = key + ": " + value;
    }

    @Override
    public String getData() {
        return this.data;
    }

    @Override
    public JPropertyTypes getPropertyType(){
        return JPropertyTypes.VALUE;
    }
}
