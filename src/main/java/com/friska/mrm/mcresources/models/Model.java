package com.friska.mrm.mcresources.models;

import com.friska.mrm.mcresources.Registrable;
import com.friska.mrm.registries.Registry;
import com.friska.mrm.registries.ResourceManager;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.system.util.KeyValue;
import com.friska.mrm.system.serialiser.builder.JObject;
import com.friska.mrm.system.serialiser.builder.JValue;
import com.friska.mrm.system.util.Util;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public abstract class Model<T extends Model<T>> extends MinecraftResource implements Registrable<Model<?>> {

    protected String parent = null;

    protected String parentType;
    protected ArrayList<KeyValue<String>> textures = new ArrayList<>();
    protected String renderType = null;

    protected final String type;

    protected Model(@Nonnull String type, @Nonnull String name, @Nonnull String parentType){
        super(type + " model", "assets/" + Config.getDefaultNamespace() + "/models/" + type, name);
        this.type = type;
        this.parentType = parentType;
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
     * @param texture The texture location.
     * **/
    public T addTexture(@Nullable String key, @Nonnull String texture){
        if(key == null) key = "";
        this.textures.add(new KeyValue<>(key, texture));
        return (T) this;
    }

    /**
     * Adds multiple key-value pair for textures used in models.
     * @param textures Vararg of KeyValue pairs.
     * **/
    public T addTextures(@Nonnull KeyValue<String>... textures){
        List.of(textures).forEach((t) -> addTexture(t.key(), t.value()));
        return (T) this;
    }

    /**
     * Edits the KeyValue holding the textures with a certain key by replacing it with a new texture.
     * Keep in mind that all textures that share the same key will be editted. Note that this method is computational expensive.
     * @param key The key pointing to the KeyValue.
     * @param newTexture The new texture.
     * **/
    public T editTexture(@Nonnull String key, @Nonnull String newTexture){

        for (int i = 0; i <= textures.size() - 1; i++) {
            KeyValue t = textures.get(i);
            if(t.key().equals(key)){
                textures.remove(i);
                textures.add(i, new KeyValue<>(key, newTexture));
            }
        }
        return (T) this;
    }

    /**
     * Setting the render type for the block model. Compatibility is unknown with Fabric, this is mainly a forge feature.
     * The render type indicates how the block should be rendered, whether it is transparent or translucent.
     * @param renderType To specify a render type, there is a list of render type strings like "translucent" or "transparent".
     * **/
    public T setRenderType(String renderType) {
        this.renderType = renderType;
        return (T) this;
    }

    /**Builds the JSON file. <b>Calling this method directly from resources is very risky and unsafe, please instantiate a ResourceManager object and register resource there.</b>**/
    @Override
    public void build(){
        createBuilder();
        if(parent != null) getBuilder().nest(new JValue<>("parent", Util.getResourceLocation(parentType, parent)));
        if(!textures.isEmpty()) {
            JObject texturesJObject = new JObject("textures");
            textures.forEach((mt) -> texturesJObject.nest(mt.toJValue(type)));
            getBuilder().nest(texturesJObject);
        }
        if(renderType != null) getBuilder().nest(new JValue<>("render_type", renderType));
        getBuilder().build();
    }

    @Override
    public Registry<Model<?>> getRegistry(ResourceManager resourceManager) {
        return resourceManager.regModel;
    }
}
