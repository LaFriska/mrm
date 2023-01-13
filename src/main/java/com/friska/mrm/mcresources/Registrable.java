package com.friska.mrm.mcresources;

import com.friska.mrm.registries.Registry;
import com.friska.mrm.registries.ResourceManager;

public interface Registrable<T extends MinecraftResource & Registrable<?>> {

    /**
     * Returns the respective registry of a resource from a resource manager object.
     * **/
    Registry<T> getRegistry(ResourceManager resourceManager);
}
