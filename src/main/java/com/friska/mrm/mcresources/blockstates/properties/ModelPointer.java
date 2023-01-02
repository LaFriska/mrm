package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.config.Config;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;

//TODO javadoc
@ExpectAccess
public class ModelPointer {

    private final boolean isModded;
    private final String pointer;
    private Boolean uvlock = null;
    private Integer x = null;
    private Integer y = null;

    public ModelPointer(@Nonnull String textureName, boolean isModded){
        this.isModded = isModded;

        if(isModded){
            this.pointer = Config.getModID() + "block/" + textureName;
        }else{
            this.pointer = "minecraft:block/" + textureName;
        }
    }

    public ModelPointer X(int x){
        this.x = x;
        return this;
    }

    public ModelPointer Y(int y){
        this.y = y;
        return this;
    }

    public ModelPointer uvlock(boolean uvlock){
        this.uvlock = uvlock;
        return this;
    }

    public JObject build(String key){
        JObject jObject = new JObject(key);
        jObject.nest(new JValue<>("model", pointer));
        if(uvlock != null) jObject.nest(new JValue<>("uvlock", uvlock));
        if(x != null) jObject.nest(new JValue<>("x", x));
        if(y != null) jObject.nest(new JValue<>("y", y));
        return jObject;
    }

}
