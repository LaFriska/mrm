package com.friska.mrm.mcresources.blockstates.properties;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.interfaces.JObjectBuildable;
import com.friska.mrm.system.serialiser.builder.JObject;
import com.friska.mrm.system.serialiser.builder.JValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

//TODO javadoc
@ExpectAccess
public class ModelPointer implements JObjectBuildable {

    private final boolean isModded;
    private final String pointer;
    private Boolean uvlock = null;
    private Integer x = null;
    private Integer y = null;

    /**
     * A model pointer points to the block model by specifying the texture path. More things could be specified too compatible with Minecraft.
     *
     * @param textureName Name of the texture png file.
     * @param isModded Whether it is in your modded namespace or the default Minecraft namespace.
     * **/
    public ModelPointer(@Nonnull String textureName, boolean isModded){
        this.isModded = isModded;

        if(isModded){
            this.pointer = Config.getModID() + ":block/" + textureName;
        }else{
            this.pointer = "minecraft:block/" + textureName;
        }
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
