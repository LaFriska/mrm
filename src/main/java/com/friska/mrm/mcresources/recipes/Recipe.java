package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.config.Config;
import com.friska.mrm.exceptions.UnexpectedTagException;
import com.friska.mrm.mcresources.MinecraftJSONResource;


public class Recipe extends MinecraftJSONResource {
    protected String type;
    protected String result;

    protected Recipe(){
        setPath("data/" + Config.getModID() + "/recipes");
    }

    @NeedsRevision("Update tag system to fit with that of Crafting")
    protected static boolean checkForTags(String ingredient, String result){
        checkResultForTags(result);
        if(ingredient.charAt(0) == '#'){
            return true;
        }else if(ingredient.contains("#") || result.contains("#")){
            throw new UnexpectedTagException("Hashtags should only be used the prefix the tag ID.");
        } else{
            return false;
        }
    }

    @NeedsRevision("Update tag system to fit with that of Crafting")
    protected static void checkResultForTags(String result){
        if(result.charAt(0) == '#'){
            throw new UnexpectedTagException("Result of a recipe cannot be a tag. You must specify an item.");
        }
    }

    protected static String getIDType(String id){
        if(id.charAt(0) == '#'){
            return "tag";
        }else if(id.contains("#")){
            throw new UnexpectedTagException("Hashtags should only be used the prefix the tag ID.");
        } else{
            return "item";
        }
    }

    private void checkResultForTag(String result){
        if(getIDType(result).equals("tag")) throw new UnexpectedTagException("Result of a recipe must be a specific item, not a tag.");
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

    protected void build(){
        checkResultForTag(result);
    }
}
