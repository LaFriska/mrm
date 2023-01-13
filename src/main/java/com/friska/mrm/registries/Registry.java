package com.friska.mrm.registries;

import com.friska.mrm.mcresources.Combinable;
import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.mcresources.Registrable;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Registry<T extends MinecraftResource & Registrable<?>> {

    private final String namespace;
    private final ArrayList<T> RESOURCES = new ArrayList<>();

    public Registry(@Nullable /*If null, should be defaulted to default namespace in Config*/ String namespace){
        this.namespace = namespace;
    }

    public Registry(){
        this.namespace = null;
    }

    @SuppressWarnings("unchecked")
    protected <R extends MinecraftResource & Registrable<?>> void register(R resource){
        RESOURCES.add((T) resource);
    }

    protected void build(){

        if(RESOURCES.isEmpty()) return;

        T sample = RESOURCES.get(0);

        if(sample instanceof Combinable<?>) {
            System.out.println("Checking and combining duplicate names for all " + sample.getResourceType() + " resource builders...");
            combine();
        }else {
            System.out.println("Checking and updating duplicate names for all " + sample.getResourceType() + " resource builders...");
            RegistryUtil.updateNames(RESOURCES);
        }

        System.out.println("Building " + RESOURCES.get(0).getResourceType() + "...");

        //Sets the new namespace. When null, the namespace is automatically defaulted as default namespace.
        if(namespace != null){
            RESOURCES.forEach((r) -> r.setNamespace(namespace));
        }

        RESOURCES.forEach(T::build);
    }

    @SuppressWarnings("unchecked")
    private void combine(){

        //Here we can assume that T extends Combinable<?>.

        Set<String> uniqueNames = new HashSet<>();
        RESOURCES.forEach((r) -> uniqueNames.add(r.getName()));
        ArrayList<T> newResources = new ArrayList<>();
        ArrayList<T> search = new ArrayList<>();
        T add;
        for (String uniqueName : uniqueNames) {
            for (T resource : RESOURCES) {
                if(resource.getName().equals(uniqueName)) search.add(resource);
            }
            add = search.get(0);
            for (T s : search) {
                newResources.add(((Combinable<T>) add).combine(s));
            }
            search.clear();
        }
        RESOURCES.clear();
        RESOURCES.addAll(newResources);
    }
}
