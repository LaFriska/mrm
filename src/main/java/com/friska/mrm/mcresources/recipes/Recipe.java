package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.MinecraftJSONResource;

public class Recipe extends MinecraftJSONResource {
    protected String type;
    protected String result;

    protected Recipe(){
        setPath("data/" + Config.getModID() + "/recipes");
    }

    @NeedsRevision("May be messy code")
    @Override
    public void setName(String result) {
        try{
            this.name = result.split(":")[1] + "_" + type.split(":")[1];
        }catch (ArrayIndexOutOfBoundsException e){
            this.name = result + "_" + type;
        }
    }

    @NeedsRevision("May be messy code")
    public void setName(String name, boolean rawName) {
        if(rawName){
            this.name = result;
        }else {
            setName(name);
        }
    }

    @NeedsRevision("May be messy code")
    @Override
    public void changeBuilderName(String newName) {
        setName(newName, true);
        getBuilder().setName(newName);
    }
}