package com.friska.mrm.registries;

import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.MinecraftJSONResource;
import com.friska.mrm.mcresources.lang.Lang;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Registry<T extends MinecraftJSONResource> {
    private final ArrayList<T> RESOURCES = new ArrayList<>();
    protected <R extends T> void register(R resource){
        RESOURCES.add(resource);
    }

    protected void build(){
        if(RESOURCES.isEmpty()) return;
        T sample = RESOURCES.get(0);
        if(sample instanceof Lang) checkLangs();
        System.out.println("Checking and updating duplicate names for all " + sample.getResourceType() + " resource builders...");
        RegistryUtil.updateNames(RESOURCES);
        System.out.println("Building " + RESOURCES.get(0).getResourceType() + "...");
        RESOURCES.forEach(T::build);
    }

    @SuppressWarnings("unchecked")
    private void checkLangs(){
        Set<String> uniqueNames = new HashSet<>();
        RESOURCES.forEach((r) -> uniqueNames.add(r.getName()));
        System.out.println("Found the following unique language codes: " + uniqueNames);
        ArrayList<Lang> newlangs = new ArrayList<>();
        ArrayList<Lang> search = new ArrayList<>();
        uniqueNames.forEach((n) -> {
            RESOURCES.forEach((r) -> {
                if(r.getName().equals(n)) search.add((Lang)r);
            });
            newlangs.add(RegistryUtil.combineLang(search));
            search.clear();
        });
        RESOURCES.clear();
        newlangs.forEach((l) -> RESOURCES.add((T) l));
    }
}
