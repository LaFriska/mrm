package com.friska.mrm.serialiser.builder;

public class JBreak implements JProperty{
    @Override
    public void construct() {}

    @Override
    public String getData() {
        return "\n";
    }

    @Override
    public JPropertyTypes getPropertyType() {
        return JPropertyTypes.BREAK;
    }
}
