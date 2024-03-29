package com.friska.mrm.mcresources.recipes;

import com.friska.mrm.mcresources.Registrable;
import com.friska.mrm.registries.Registry;
import com.friska.mrm.registries.ResourceManager;
import com.friska.mrm.system.annotations.NeedsRevision;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.exceptions.UnexpectedTagException;
import com.friska.mrm.mcresources.MinecraftResource;


public abstract class Recipe extends MinecraftResource implements Registrable<Recipe> {

    protected String type;
    protected String result;

    protected Recipe(){
        super("recipe", "data/" + Config.getDefaultNamespace() + "/recipes");
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
    public void setAndTruncateName(String result) {
        try{
            this.name = result.split(":")[1] + "_" + type.split(":")[1];
        }catch (ArrayIndexOutOfBoundsException e){
            this.name = result + "_" + type;
        }
    }

    @NeedsRevision("May be messy code")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void build(){
        createBuilder();
        checkResultForTag(result);
    }

    @Override
    public Registry<Recipe> getRegistry(ResourceManager resourceManager) {
        return resourceManager.regRecipe;
    }
}
