package com.friska.mrm.system.util;

import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.interfaces.JObjectBuildable;
import com.friska.mrm.system.serialiser.builder.JObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class Util {

    public static JObject[] getJObjectArray(@Nonnull ArrayList<? extends JObjectBuildable> jObjectBuildables){
        JObject[] result = new JObject[jObjectBuildables.size()];
        for(int i = 0; i <= jObjectBuildables.size() - 1; i++){
            result[i] = jObjectBuildables.get(i).toJObject(null);
        }
        return result;
    }

    public static String getResourceLocation(@Nullable String path, @Nonnull String name){
        String namespace;
        if(name.contains(":")){
            System.out.println("Semicolon detected in the name of resource location name \"" + name + "\". Interpreting the name as the resource location instead.");
            return name;
        }else{
            if(name.charAt(0) == '@'){
                namespace = Config.getSecondaryNamespace().toLowerCase();
                name = name.substring(1);
            }else{
                namespace = Config.getDefaultNamespace().toLowerCase();
            }
            return namespace + ":" + path + "/" + name;
        }
    }

}
