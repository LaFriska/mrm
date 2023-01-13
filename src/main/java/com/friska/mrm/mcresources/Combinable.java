package com.friska.mrm.mcresources;

@FunctionalInterface
public interface Combinable<T extends MinecraftResource> {

    /**
     * Combines multiple instances of a combinable minecraft resource together. Notable ones are: tag and lang JSON files.
     * **/
    T combine(T object);

}
