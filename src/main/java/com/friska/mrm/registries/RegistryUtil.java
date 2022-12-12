package com.friska.mrm.registries;

import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.mcresources.recipes.Recipe;

import java.util.ArrayList;

public class RegistryUtil {
    public static void updateNames(ArrayList<? extends MinecraftJSONResource> resources){
        String[] names = getAllNames(resources);
        int duplicates;
        String check;
        String newName;
        for(int i = 0; i <= resources.size() - 1; i++){
            check = names[i];
            duplicates = 0;
            for(int b = i + 1; b <= resources.size() - 1; b++){
                if(names[b].equals(check)){
                    duplicates++;
                    newName = names[b] + "_" + duplicates;
                    resources.get(b).setName(newName);
                    names[b] = newName;
                }
            }
        }
    }
    private static String[] getAllNames(ArrayList<? extends MinecraftJSONResource> resources){
        String[] result = new String[resources.size()];
        for(int i = 0; i <= result.length - 1; i++){
            result[i] = resources.get(i).getName();
        }
        return result;
    }
}
