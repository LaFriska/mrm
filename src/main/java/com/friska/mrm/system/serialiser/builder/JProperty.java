package com.friska.mrm.system.serialiser.builder;

public interface JProperty {
    void construct();

    String getData();

    JPropertyTypes getPropertyType();
}
