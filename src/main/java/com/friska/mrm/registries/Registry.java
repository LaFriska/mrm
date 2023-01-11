package com.friska.mrm.registries;

import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.mcresources.lang.Lang;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Registry<T extends MinecraftResource> {

    private final String namespace;
    private final ArrayList<T> RESOURCES = new ArrayList<>();

    public Registry(@Nullable /*If null, should be defaulted to default namespace in Config*/ String namespace){
        this.namespace = namespace;
    }

    public Registry(){
        this.namespace = null;
    }

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

        //Sets the new namespace. When null, the namespace is automatically defaulted as default namespace.
        if(namespace != null){
            RESOURCES.forEach((r) -> r.setNamespace(namespace));
        }

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
