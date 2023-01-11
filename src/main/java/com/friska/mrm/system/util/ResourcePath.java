package com.friska.mrm.system.util;

import com.friska.mrm.system.ResourceType;
import com.friska.mrm.system.exceptions.ResourcePathException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import java.util.Objects;

import static com.friska.mrm.system.ResourceType.ASSETS;
import static com.friska.mrm.system.ResourceType.DATA;

public record ResourcePath(@Nonnull ResourceType type, @Nonnull String namespace, @Nullable String subPath) {

    @Override
    public String toString() {
        if(subPath != null) {
            return type + "/" + namespace + "/" + subPath;
        }else{
            return type + "/" + namespace;
        }
    }

    public static ResourcePath toPath(String pathString){

        ResourcePathException resourcePathException = new ResourcePathException("Path string \"" + pathString + "\" is invalid.");

        String[] split = pathString.split("/");
        if(split.length < 2) throw resourcePathException;
        if(!split[0].equals(ASSETS.toString()) && !split[0].equals(DATA.toString())) throw resourcePathException;

        //split[0] = data or assets, split[1] = namespace
        String subPath;

        try {
            subPath = pathString.split(split[1] + "/")[1];
        }catch (ArrayIndexOutOfBoundsException e){
            subPath = null;
        }

        return new ResourcePath(Objects.requireNonNull(toResourceType(split[0])), split[1], subPath);

    }

    private static ResourceType toResourceType(String resourceTypeString){
        if(resourceTypeString.equals("assets")) return ASSETS;
        return resourceTypeString.equals("data") ? DATA
                : null;
    }
}


