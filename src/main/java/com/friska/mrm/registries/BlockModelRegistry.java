package com.friska.mrm.registries;

import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.models.BlockModel;

import java.util.ArrayList;

public class BlockModelRegistry {

    private static final ArrayList<BlockModel> MODELS = new ArrayList<>();

    protected static void register(BlockModel blockModel){
        MODELS.add(blockModel);
    }

    protected static void build(){
        if(MODELS.isEmpty()){return;}
        System.out.println("Checking and updating duplicate names for all block model builders...");
        RegistryUtil.updateNames(MODELS);
        System.out.println("Building block models...");
        MODELS.forEach(BlockModel::build);
    }

}
