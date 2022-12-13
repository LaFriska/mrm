package com.friska.mrm.mcresources.models;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.mcresources.lang.KeyValue;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class Model<T extends Model<T>> extends MinecraftJSONResource {

    protected String parent = null;
    protected ArrayList<KeyValue> textures = new ArrayList<>();

    protected Model(@Nonnull String type, @Nonnull String name){
        super();
        setPath("assets/" + Config.getModID() + "/models/" + type);
        setName(name);
    }
    protected void build(){
        createBuilder();
        if(parent != null) getBuilder().nest(new JValue<>("parent", parent));
        if(!textures.isEmpty()) {
            JObject texturesJObject = new JObject("textures");
            textures.forEach((mt) -> texturesJObject.nest(mt.toJValue()));
            getBuilder().nest(texturesJObject);
        }
    }

    /**
     * This method sets the parent model. Similarly to java classes, Minecraft model JSON files can "inherit" other model files.
     * @param parent Path and name of the parent model. Use the static strings defined under BlockModelParents for a list of every parent models used in Minecraft.
     * An example of this is "minecraft:block/outer_stairs".
     * **/
    public T setParent(@Nonnull String parent){
        this.parent = parent;
        return (T) this;
    }

    /**
     * Adds a key-value pair for textures used in models.
     * @param key The key of the texture, for example, "layer0".
     * @param texture The path and name of the texture file, for example, "minecraft:block/acacia_planks".
     * **/
    public T addTexture(@Nullable String key, @Nonnull String texture){
        if(key == null) key = "";
        this.textures.add(new KeyValue(key, texture));
        return (T) this;
    }

    /**
     * Adds multiple key-value pair for textures used in models.
     * @param textures Vararg of ModelTextures, which are records with both the key and the value as parameters.
     * **/

    public T addTextures(@Nonnull KeyValue... textures){
        List.of(textures).forEach((t) -> addTexture(t.key(), t.value()));
        return (T) this;
    }
}
