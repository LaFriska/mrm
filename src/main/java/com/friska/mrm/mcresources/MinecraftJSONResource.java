package com.friska.mrm.mcresources;

import com.friska.mrm.system.exceptions.ResourcePathException;
import com.friska.mrm.system.serialiser.builder.JBuilder;
import com.friska.mrm.system.config.Config;

import javax.annotation.Nonnull;

public abstract class MinecraftJSONResource {
    protected JBuilder builder;
    protected String path;
    protected String name;

    private String resourceType;

    public MinecraftJSONResource(@Nonnull String resourceType, @Nonnull String path, @Nonnull String name){
        this.resourceType = resourceType;
        setPath(path);
        setName(name);
        if(!Config.isDefaultNamespaceDefined()){
            throw new ResourcePathException("The Mod ID is null, define by calling Config.setModID().");
        }
    }

    public MinecraftJSONResource(@Nonnull String resourceType, @Nonnull String path){
        this(resourceType, path, "null");
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    protected void createBuilder(){
        this.builder = new JBuilder(path, name);
    }

    public JBuilder getBuilder(){
        return this.builder;
    }

    public String getResourceType() {
        return resourceType;
    }

    public abstract void build();
}
