package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.interfaces.JObjectBuildable;
import com.friska.mrm.system.serialiser.builder.JObject;
import com.friska.mrm.system.serialiser.builder.JValue;
import com.friska.mrm.system.util.Util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//TODO javadoc
@ExpectAccess
public class ModelPointer implements JObjectBuildable {

    private final String pointer;
    private Boolean uvlock = null;
    private Integer x = null;
    private Integer y = null;

    /**
     * A model pointer points to the block model by specifying the texture path. More things could be specified too compatible with Minecraft.
     *
     * @param model Name of the model JSON file.
     * **/
    public ModelPointer(@Nonnull String model){
        this.pointer = Util.getResourceLocation("block", model);
    }

    /**
     * Sets the rotation of the model on the x-axis.
     * **/
    public ModelPointer x(int x){
        this.x = x;
        return this;
    }

    /**
     * Sets the rotation of the model on the y-axis.
     * **/
    public ModelPointer y(int y){
        this.y = y;
        return this;
    }

    /**
     * Setting the uvlock. This denotes whether the rotation of the texture of a block is locked.
     * This way the texture will not rotate with the block when using the x and y tag.
     * **/
    public ModelPointer uvlock(boolean uvlock){
        this.uvlock = uvlock;
        return this;
    }

    /**
     * Buidls to a JObject.
     * **/
    @Override
    public JObject toJObject(@Nullable String key) {
        JObject jObject = new JObject(key);
        jObject.nest(new JValue<>("model", pointer));
        if(uvlock != null) jObject.nest(new JValue<>("uvlock", uvlock));
        if(x != null) jObject.nest(new JValue<>("x", x));
        if(y != null) jObject.nest(new JValue<>("y", y));
        return jObject;
    }
}
