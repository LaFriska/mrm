package com.friska.mrm.mcresources.models;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class Model extends MinecraftJSONResource {

    protected String parent = null;
    protected ArrayList<ModelTexture> textures = new ArrayList<>();

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
            textures.forEach((mt) -> {
                texturesJObject.nest(new JValue<>(mt.key(), mt.texture()));
            });
            getBuilder().nest(texturesJObject);
        }
    }

}
