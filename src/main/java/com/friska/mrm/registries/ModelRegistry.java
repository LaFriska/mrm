package com.friska.mrm.registries;

import com.friska.mrm.mcresources.models.Model;

import java.util.ArrayList;

public class ModelRegistry {

    private static final ArrayList<Model> MODELS = new ArrayList<>();

    protected static <T extends Model<T>> void register(T model){
        MODELS.add(model);
    }
    protected static void build(){
        if(MODELS.isEmpty()) return;
        System.out.println("Checking and updating duplicate names for all block model builders...");
        RegistryUtil.updateNames(MODELS);
        System.out.println("Building block models...");
        MODELS.forEach(Model::build);
    }
}
