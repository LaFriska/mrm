package com.friska.mrm.mcresources.tags;

import com.friska.mrm.mcresources.Combinable;
import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.mcresources.Registrable;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.registries.Registry;
import com.friska.mrm.registries.ResourceManager;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.serialiser.builder.JArray;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

//TODO JAVADOC

public class Tag extends MinecraftResource implements Combinable<Tag>, Registrable<Tag> {

    private final ArrayList<String> values = new ArrayList<>();


    /**
     * This class creates a Tag object. A tag in minecraft is effectively a way to group multiple objects together, so that they could be treated as just one.
     * Tags store an array of strings, in a JSON array named "values".
     * @param tagName Name of the Tag, which will also be the name of the JSON file.
     * **/
    public Tag(@Nonnull String tagName) {
        super("tag", "data/" + Config.getDefaultNamespace() + "/tags", tagName);
    }

    /**
     * This class creates a Tag object. A tag in minecraft is effectively a way to group multiple objects together, so that they could be treated as just one.
     * Tags store an array of strings, in a JSON array named "values".
     * @param subPath Specification of a subpath, as most tags are nested in different paths branching off from the data/path directory.
     * @param tagName Name of the Tag, which will also be the name of the JSON file.
     * **/
    public Tag(@Nonnull String subPath, @Nonnull String tagName) {
        super("tag", "data/" + Config.getDefaultNamespace() + "/tags" + subPath,  tagName);
    }

    /**
     * Adds a value to the tag.
     * **/
    public Tag add(String value){
        values.add(value);
        return this;
    }

    /**
     * Adds multiple values to the tag as a string vararg.
     * **/
    public Tag add(String... values){
        this.values.addAll(List.of(values));
        return this;
    }

    /**
     * Adds multiple values to the tag as a string ArrayList.
     * **/
    public Tag add(ArrayList<String> values){
        this.values.addAll(values);
        return this;
    }

    /**
     * Builds the JSON file. <b>Calling this method directly from resources is very risky and unsafe, please instantiate a ResourceManager object and register resource there.</b>
     * **/
    @Override
    public void build() {
        createBuilder();
        getBuilder().nest(new JArray("values").setArray(values.toArray()));
        getBuilder().build();
    }

    @Override
    public Tag combine(Tag object) {
        return this.add(object.values);
    }

    @Override
    public Registry<Tag> getRegistry(ResourceManager resourceManager) {
        return resourceManager.regTag;
    }
}
