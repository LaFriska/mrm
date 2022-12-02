package com.friska.mrm.registries;

import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.mcresources.lang.Lang;

import javax.annotation.Nonnull;

public class ResourceManagers{

    public static <T extends MinecraftJSONResource> void register(@Nonnull T manager){
        if(manager instanceof Lang){
            LangRegistry.register((Lang) manager);
        }
    }

    public static void buildAll(){
        LangRegistry.build();
    }

}
