package com.friska.mrm.system.interfaces;

import com.friska.mrm.system.serialiser.builder.JObject;

import javax.annotation.Nullable;

@FunctionalInterface
public interface JObjectBuildable {

    JObject toJObject(@Nullable String key);

}
