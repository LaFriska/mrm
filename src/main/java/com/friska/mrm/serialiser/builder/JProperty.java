package com.friska.mrm.serialiser.builder;

public interface JProperty {
    void construct();

    String getData();

    JPropertyTypes getPropertyType();
}
