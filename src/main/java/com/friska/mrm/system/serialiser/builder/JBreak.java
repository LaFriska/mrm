package com.friska.mrm.system.serialiser.builder;

import com.friska.mrm.system.annotations.Empty;

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
