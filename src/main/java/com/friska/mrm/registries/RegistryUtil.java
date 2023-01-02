package com.friska.mrm.registries;

import com.friska.mrm.system.annotations.NeedsRevision;
import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.system.util.KeyValue;
import com.friska.mrm.mcresources.lang.Lang;

import java.util.ArrayList;

public class RegistryUtil {

    //UTIL
    protected static void updateNames(ArrayList<? extends MinecraftJSONResource> resources){
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

    @NeedsRevision
    //THIS METHOD DOES NOT CHECK WHETHER ALL THE LANGUAGE CODES ARE THE SAME, IN RETURN FOR BETTER EFFICIENCY.
    public static Lang combineLang(ArrayList<Lang> candidates){

        System.out.println("Combining multiple instances of " + candidates.get(0).getLanguageCode() + " language files...");

        ArrayList<ArrayList<KeyValue<String>>> all = candidates.get(0).all();
        ArrayList<ArrayList<KeyValue<String>>> needle;

        for(int o = 1; o <= candidates.size() - 1; o++) {
            needle = candidates.get(o).all();
            for (int i = 0; i <= all.size() - 1; i++) {
                all.get(i).addAll(needle.get(i));
            }
        }
        return candidates.get(0);
    }


    //PRIVATE METHODS

    private static String[] getAllNames(ArrayList<? extends MinecraftJSONResource> resources){
        String[] result = new String[resources.size()];
        for(int i = 0; i <= result.length - 1; i++){
            result[i] = resources.get(i).getName();
        }
        return result;
    }
}
