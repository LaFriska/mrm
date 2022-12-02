package com.friska.mrm.serialiser.builder;

import com.friska.mrm.annotations.Empty;

public class JBreak implements JProperty{

    @Empty
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
