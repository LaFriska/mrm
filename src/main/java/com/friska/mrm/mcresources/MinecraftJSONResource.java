package com.friska.mrm.mcresources;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.exceptions.ResourcePathException;
import com.friska.mrm.serialiser.builder.JBuilder;
import com.friska.mrm.config.Config;

public class MinecraftJSONResource {
    protected JBuilder builder;
    protected String path;
    protected String name;

    public MinecraftJSONResource(){
        if(!Config.isModIDDefined()){
            throw new ResourcePathException("The Mod ID is null, define by calling Config.setModID().");
        }
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

    /**
     * Initiates JBuilder;
     * **/
    protected void initiateBuilder(){
        this.builder = new JBuilder(path, name);
    }

    public JBuilder getBuilder(){
        return this.builder;
    }

    /**
     * SetName is only a mutator for the variable "name", once the builder is added, the SetName method will not change the builders' name. Call this method to change the name of the builder.
     * **/
    public void changeBuilderName(String newName){
        setName(newName);
        getBuilder().setName(newName);
    }
}
