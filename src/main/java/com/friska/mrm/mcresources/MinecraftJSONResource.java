package com.friska.mrm.mcresources;

import com.friska.mrm.exceptions.ResourcePathException;
import com.friska.mrm.serialiser.builder.JBuilder;
import com.friska.mrm.config.Config;

import java.util.ArrayList;

public class MinecraftJSONResource {
    protected ArrayList<JBuilder> builders;
    protected String path;
    protected String name;

    public MinecraftJSONResource(){

        if(!Config.isModIDDefined()){
            throw new ResourcePathException("The Mod ID is null, define by calling Config.setModID().");
        }

        this.builders = new ArrayList<>();
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
     * Adds JSONBuilders.
     * **/
    protected void addBuilder(){
        this.builders.add(new JBuilder(path, name));
    }
    protected void addBuilders(int amount){
        for(int i = 0; i <= amount - 1; i++){
            addBuilder();
        }
    }

    protected JBuilder getBuilder (int index){
        return this.builders.get(index);
    }
}
