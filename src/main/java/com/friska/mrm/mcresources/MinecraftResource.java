package com.friska.mrm.mcresources;

import com.friska.mrm.registries.Registry;
import com.friska.mrm.system.exceptions.ResourcePathException;
import com.friska.mrm.system.serialiser.builder.JBuilder;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.util.ResourcePath;

import javax.annotation.Nonnull;

public abstract class MinecraftResource {

    protected JBuilder builder;
    protected ResourcePath path;
    protected String name;

    private final String resourceType;

    public MinecraftResource(@Nonnull String resourceType, @Nonnull String path, @Nonnull String name){
        this.resourceType = resourceType;
        setPath(path);
        setName(name);
        /*if(!Config.isDefaultNamespaceDefined()){
            throw new ResourcePathException("The Mod ID is null, define by calling Config.setModID().");
        }*/

    }

    public MinecraftResource(@Nonnull String resourceType, @Nonnull ResourcePath path, @Nonnull String name){
        this(resourceType, path.toString(), name);
    }

    public MinecraftResource(@Nonnull String resourceType, @Nonnull String path){
        this(resourceType, path, "null");
    }

    public void setPath(String path) {
        this.path = ResourcePath.toPath(path);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNamespace(String newNamespace){
        this.path = new ResourcePath(path.type(), newNamespace, path.subPath());
    }

    public String getPath() {
        return path.toString();
    }

    public String getName() {
        return name;
    }

    protected void createBuilder(){
        this.builder = new JBuilder(path.toString(), name);
    }

    public JBuilder getBuilder(){
        return this.builder;
    }

    public String getResourceType() {
        return resourceType;
    }

    public abstract void build();
}
