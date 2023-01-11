package com.friska.mrm.mcresources.tags;

import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.serialiser.builder.JArray;

import javax.annotation.Nonnull;
import java.util.*;

public class Tag extends MinecraftResource {

    private static final HashSet<Tag> TAG_POOL = new HashSet<>();

    private final ArrayList<String> values = new ArrayList<>();


    public Tag(@Nonnull String tagName) {
        super("tag", "assets/" + Config.getDefaultNamespace() + "/blockstates", tagName);
    }

    public Tag add(String value){
        values.add(value);
        return this;
    }

    public Tag add(String... values){
        this.values.addAll(List.of(values));
        return this;
    }

    @Override
    public void build() {
        createBuilder();
        getBuilder().nest(new JArray("values").setArray(values.toArray()));
        getBuilder().build();
    }
}
