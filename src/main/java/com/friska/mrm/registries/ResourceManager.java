package com.friska.mrm.registries;

import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.recipes.Recipe;
import com.friska.mrm.mcresources.recipes.cooking.CookingRecipe;

import javax.annotation.Nonnull;
import java.util.List;

@SuppressWarnings("unused")
public class ResourceManager {

    /**
     * This method registers one or multiple managers. A manager could be Lang (for language files), SmokingRecipe etc. To actually make the program build the JSON files, call the buildAll() method.
     * To register multiple managers, separate them with commas in the parameter.
     * **/
    public static <T extends MinecraftJSONResource> void register(@Nonnull T manager){
        if(manager instanceof Lang){
            LangRegistry.register((Lang) manager);
        } else if (manager instanceof Recipe){
            RecipeRegistry.register((Recipe) manager);
        }
    }

    /**
     * This method registers one or multiple managers. A manager could be Lang (for language files), SmokingRecipe etc. To actually make the program build the JSON files, call the buildAll() method.
     * To register multiple managers, separate them with commas in the parameter.
     * **/
    public static <T extends MinecraftJSONResource> void register(@Nonnull T... managers){
        List.of(managers).forEach(ResourceManager::register);
    }
    /**
     * This method builds all registered managers into actual JSON files. Calling it after registering everything is adviced.
     * **/
    public static void buildAll(){
        LangRegistry.build();
        RecipeRegistry.build();
    }

}
