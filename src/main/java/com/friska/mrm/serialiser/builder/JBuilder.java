package com.friska.mrm.serialiser.builder;

import com.friska.mrm.exceptions.ResourcePathException;

import javax.annotation.Nullable;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static com.friska.mrm.config.Config.getPathRoot;
import static com.friska.mrm.config.Config.isPathRootDefined;

/**
 * JSONBuilder uses recursion to serialize from Java to JSON file format. This serialiser is built to fit into the Minecraft modding scheme.
 **/
public class JBuilder extends JObject {

    /**
     * The curly braces that encapsulates the entire JSON file, which is needed by all JSONs is by default added, via the instantiation of JObject in the constructor.
     */
    public JBuilder(@Nullable String path, @Nullable String name) {
        super(null);
        setPath(path);
        setName(name);
    }

    /**
     * The "nest" method is identical to that of JObject. Nesting allows you to add any properties objects to your JSON.
     *
     * @return JObject instance, which also has "nest" methods you can call to nest more properties.
     */
    @Override
    public <T extends JProperty> JBuilder nest(T property) {
        this.properties.add(property);
        construct();
        return this;
    }

    private String path;
    private String name;

    public JBuilder compactToOneLine() {

        setData(getData().replace("\n", ""));

        return this;
    }

    /**
     * The build method checks the resource location for the name of the JSON specified. If there is none, the algorithm generates one and writes the JSON data to the file. If there is one, the algorithm will simply edit the file.
     **/
    public JBuilder build() {
        if (isPathRootDefined()) {
            String path = getPathRoot() + "/" + getPath();
            try {
                Files.createDirectories(Path.of(path));
                createJSON(path, name);
            } catch (IOException e) {
                System.out.println(path);
                e.printStackTrace();
            }
        } else {
            throw new ResourcePathException("Root path for Minecraft resource is null, call the \".setPathRoot()\" static method before any instantiations of JSONBuilder.");
        }

        return this;
    }

    private void createJSON(String path, String name) throws IOException {
        File file = new File(path, name);
        if (!file.createNewFile()) {
            System.out.println(name + " already exists, updating the file instead.");
        } else {
            System.out.println(name + " successfully created.");
        }
        updateJSON(path, name);
    }

    private void updateJSON(String path, String name) {
        try {
            FileWriter fw = new FileWriter(path + "/" + name);
            fw.write(getData());
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setPath(String path) {
        if(path == null){
            this.path = "";
        }else {
            this.path = path;
        }
    }

    public void setName(String name) {
        this.name = name + ".json";
    }

    public String getPath() {
        return path;
    }

    public boolean isPathDefined() {
        return this.path != null;
    }

}
