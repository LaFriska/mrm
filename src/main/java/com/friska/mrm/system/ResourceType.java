package com.friska.mrm.system;

public enum ResourceType {
    ASSETS("assets"),
    DATA("data");

    final String id;

    ResourceType(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }

}
