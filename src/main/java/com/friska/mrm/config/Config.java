package com.friska.mrm.config;

public class Config {

    private static String MODID = null;
    private static int JSON_INDENT_SIZE = 2;
    private static String PATH_ROOT = "src/main/resources";

    /**
     *Path root is defined as "src/main/resources" by default. You can change this using setPathRoot method.
     * <p>
     * <b>DO NOT use absolute path, it will probably not work when building the JAR file for your mod.</b>
     * **/
    public static void setPathRoot(String pathRoot) {
        PATH_ROOT = pathRoot;
    }

    public static String getPathRoot() {
        return PATH_ROOT;
    }

    public static boolean isPathRootDefined(){
        return getPathRoot() != null;
    }

    public static int getJsonIndentSize() {
        return JSON_INDENT_SIZE;
    }

    /**
     * Set the size of indents when a JSON file is formatted, default is 2.
     * **/
    public static void setJsonIndentSize(int jsonIndentSize) {
        JSON_INDENT_SIZE = jsonIndentSize;
    }

    public static String getModID() {
        return MODID;
    }

    public static void setModID(String ModID) {
        MODID = ModID;
    }

    public static boolean isModIDDefined(){
        return MODID != null;
    }
}

