package com.friska.mrm.system.config;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.exceptions.CannotFindModIDException;

import javax.annotation.Nonnull;

@ExpectAccess
public class Config {

    private static String MODID = null;
    private static int JSON_INDENT_SIZE = 2;
    private static String PATH_ROOT = "src/main/resources";

    /**
     *Sets the path root for the Minecraft resource location. If this method is not called, it will be defaulted as "src/main/resources".
     * @param pathRoot This param specifies a new path root. <b>DO NOT USE ABSOLUTE PATH. </b>
     * **/
    public static void setPathRoot(@Nonnull String pathRoot) {
        PATH_ROOT = pathRoot;
    }

    /**
     * Returns the path root.
     * **/
    public static String getPathRoot() {
        return PATH_ROOT;
    }

    /**
     * Checks to see if the path root is null.
     * **/
    public static boolean isPathRootDefined(){
        return getPathRoot() != null;
    }

    /**
     * Gets the indent size.
     * **/
    public static int getJSONIndentSize() {
        return JSON_INDENT_SIZE;
    }

    /**
     * Sets the size of indents when a JSON file is formatted, default is 2.
     * @param newIndentSize New indent size.
     * **/
    public static void setJSONIndentSize(int newIndentSize) {
        JSON_INDENT_SIZE = newIndentSize;
    }

    /**
     * Returns the mod ID. Use setModID to define it.
     * **/
    public static String getModID() {
        if(MODID == null) throw new CannotFindModIDException("Define your Mod ID before running any other code, call the method Config.setModID().");
        return MODID;
    }

    /**
     * Mutator for the mod ID. This method needs to be called before doing anything else related to managing Minecraft resources,
     * otherwise the program would not know where and how to create all the resources!
     * @param modID The mod ID.
     * **/
    public static void setModID(String modID) {
        MODID = modID;
    }

    /**
     * Checks whether the mod ID is null.
     * **/
    public static boolean isModIDDefined(){
        return MODID != null;
    }
}

