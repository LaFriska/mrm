package com.friska.mrm.registries;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.recipes.Recipe;
import com.friska.mrm.mcresources.recipes.cooking.CookingRecipe;

import javax.annotation.Nonnull;
import java.util.List;

@ExpectModdersToAccess
@SuppressWarnings("unused")
public class ResourceManager {

    /**
     * Registration of one resource.
     * @param resource The resource you wish to register (e.g. SmeltingRecipe object).
     * **/
    public static <T extends MinecraftJSONResource> void register(@Nonnull T resource){
        if(resource instanceof Lang){
            LangRegistry.register((Lang) resource);
        } else if (resource instanceof Recipe){
            RecipeRegistry.register((Recipe) resource);
        }
    }

    /**
     * Registration of multiple resources.
     * @param resources The resources you wish to register (e.g. SmeltingRecipe objects, or Lang objects). Separate them via commas.
     * **/
    public static <T extends MinecraftJSONResource> void register(@Nonnull T... resources){
        List.of(resources).forEach(ResourceManager::register);
    }
    /**
     * This method builds all registered managers into actual JSON files. This should be called when all resources are registered.
     * **/
    public static void buildAll(){
        LangRegistry.build();
        RecipeRegistry.build();
    }

}
