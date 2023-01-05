package com.friska.mrm.system.config;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.exceptions.CannotFindNamespaceException;

import javax.annotation.Nonnull;

@ExpectAccess
public class Config {

    private static String DEFAULT_NAMESPACE = null;
    private static String SECONDARY_NAMESPACE = "minecraft";

    private static char SECONDARY_NAMESPACE_PREFIX = '@';
    private static int JSON_INDENT_SIZE = 2;
    private static String PATH_ROOT = "src/main/resources";

    /**
     *Sets the path root for the Minecraft resource location. If this method is not called, it will be defaulted as "src/main/resources".
     * @param pathRoot This param specifies a new path root.
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
     * Returns the default namespace. Use setDefaultNamespace to define it.
     * **/
    public static @Nonnull String getDefaultNamespace() {
        if(DEFAULT_NAMESPACE == null) throw new CannotFindNamespaceException("Define your Default namespace before building resources, call the method Config.setDefaultNamespace().");
        return DEFAULT_NAMESPACE;
    }

    public static @Nonnull String getSecondaryNamespace() {
        return SECONDARY_NAMESPACE;
    }

    /**
     * Mutator for the default namespace. This method needs to be called before doing anything else related to managing Minecraft resources,
     * otherwise the program would not know where and how to create all the resources!
     * @param modID The mod ID.
     * **/
    public static void setDefaultNamespace(String modID) {
        DEFAULT_NAMESPACE = modID;
    }

    /**
     * Mutator for the secondary namespace. This is defaulted to "minecraft". Add an "*" before the path to call the secondary namespace.
     * **/
    public static void setSecondaryNamespace(@Nonnull String secondaryNamespace) {
        SECONDARY_NAMESPACE = secondaryNamespace;
    }

    /**
     * Checks whether the default namespace is null.
     * **/
    public static boolean isDefaultNamespaceDefined(){
        return DEFAULT_NAMESPACE != null;
    }

    /**
     * Sets the character prefix that denotes that the secondary namespace should be used. By default, the "@" symbol is used. To denote secondary namespace, please append
     * the prefix in front of the string, such as "@baked_potato".
     * **/
    public static void setSecondaryNamespacePrefix(char secondaryNamespacePrefix) {
        SECONDARY_NAMESPACE_PREFIX = secondaryNamespacePrefix;
    }

    /**
     * Gets the character prefix that denotes that the secondary namespace should be used. By default, the "@" symbol is used. To denote secondary namespace, please append
     * the prefix in front of the string, such as "@baked_potato".
     * **/
    public static char getSecondaryNamespacePrefix() {
        return SECONDARY_NAMESPACE_PREFIX;
    }
}

