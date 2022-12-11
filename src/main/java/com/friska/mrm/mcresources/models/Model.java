package com.friska.mrm.mcresources.models;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.MinecraftJSONResource;

import javax.annotation.Nonnull;

public class Model extends MinecraftJSONResource {

    protected String parent = null;

    protected Model(@Nonnull String type, @Nonnull String name){
        super();
        setPath("assets/" + Config.getModID() + "/models/" + type);
        setName(name);
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
