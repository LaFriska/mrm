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

    private boolean overrideExistingFile = true;
    private int nameNumberChain = 0;

    private String path;
    private String name;

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
            if(this.overrideExistingFile){
                System.out.println("    " + name + " already exists, updating file instead.");
                updateJSON(path, name);
            }else{
                if(this.nameNumberChain == 0) System.out.println("    " + name + " already exists, creating JSON with another name.");
                this.nameNumberChain++;
                createJSON(path, getName(false) + "_" + nameNumberChain + ".json");
            }
        } else {
            updateJSON(path, name);
            System.out.println("    " + name + " successfully created.");
        }
    }

    @Deprecated
    public JBuilder dontOverrideExistingFiles(){
        this.overrideExistingFile = false;
        return this;
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

    private String getName(boolean withDotJSON) {
        if(this.name.substring(this.name.length() - 5).equals(".json")){
            if(withDotJSON){
                return this.name;
            }else{
                return this.name.substring(0, this.name.length() - 5);
            }
        }else{
            return this.name;
        }
    }

    public String getPath() {
        return path;
    }

    public boolean isPathDefined() {
        return this.path != null;
    }

}
