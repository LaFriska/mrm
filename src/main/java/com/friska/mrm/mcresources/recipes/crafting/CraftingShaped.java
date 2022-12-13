package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.exceptions.CraftingRecipeException;
import com.friska.mrm.serialiser.builder.JArray;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@NeedsRevision("Inefficient and messy")
@ExpectAccess
public class CraftingShaped extends Crafting<CraftingShaped> {

    private ArrayList<CraftingKey> keys = new ArrayList<>();
    private String[] rows;

    /**
     * Similar to a crafting JSON recipe, Minecraft Resource Manager uses the crafting grid map, as a string array, which should be parsed into the constructor, and define the key(s) by calling the .addKey or .addKeys method.
     * <p>
     * For example, to craft a beacon, we parse in:
     * ("GGG",
     *  "GNG",
     *  "OOO")
     *  We then define the keys as such, for <b>all</b> the keys used:
     *  .addKey('G', "ItemIDs.GLASS")
     *
     *  We also need to call the .setResult method to set the result.
     * **/
    public CraftingShaped(@Nonnull String... rows){
        super(null);
        checkRows(rows);
        this.rows = rows;
    }

    /**
     * Sets the result of the recipe.
     * @param result Item/Tag ID of the result.
     * **/
    public CraftingShaped setResult(@Nonnull String result){
        this.result = result;
        setAndTruncateName(result);
        //createBuilder();
        return this;
    }

    /**
     * Defines a key used in the crafting grid.
     * @param key The char value of the key.
     * @param id The item/tag ID.
     * **/
    public CraftingShaped addKey(char key, String id){
        if(!toSingleString().contains(String.valueOf(key))){
            throw new CraftingRecipeException("Crafting grid does not contain the key \'" + key + "\'.");
        }
        keys.add(new CraftingKey(key, id));
        return this;
    }

    /**
     * Defines a key used in the crafting grid.
     * @param keys List of CraftingKey instances.
     * **/
    public CraftingShaped addKeys(CraftingKey... keys){
        List.of(keys).forEach((k) -> addKey(k.key(), k.id()));
        return this;
    }

    /**
     * Builds the JSON file.
     * **/
    @Override
    public void build(){
        super.build();
        buildCheck();

        //Key
        JObject key = new JObject("key");
        String idType;
        for (CraftingKey craftingKey : keys) {
            idType = getIDType(craftingKey.id());
            key.nest(new JObject(String.valueOf(craftingKey.key())).nest(new JValue<>(idType, idType.equals("tag") ? craftingKey.id().substring(1) : craftingKey.id())));
        }

        //Pattern
        JArray pattern = new JArray("pattern");
        pattern.setArray(rows);

        //Results
        JObject result = new JObject("result");
        if(count != null){
            result.nest(new JValue<>("count", count));
        }
        result.nest(new JValue<>("item", this.result));

        //Constructing the builder
        getBuilder().nest(new JValue<>("type", "minecraft:crafting_shaped"));
        if(this.group != null) getBuilder().nest(new JValue<>("group", group));
        getBuilder().nest(key);
        getBuilder().nest(pattern);
        getBuilder().nest(result);

        getBuilder().build();

    }

    private void buildCheck(){
        if(result == null){
            throw new CraftingRecipeException("The result of the crafting recipe must be defined. Please call the .result() method.");
        }
        StringBuilder st = new StringBuilder();
        for (CraftingKey key : keys) {
            st.append(key.key());
        }
        for (Character character : toCharacterArray()) {
            if(!st.toString().contains(String.valueOf(character)) && character != ' ') throw new CraftingRecipeException("Not all characters used in the crafting grid have been defined.");
        }
        checkResultForTags(result);
    }

    private Character[] toCharacterArray() {
        String s = toSingleString();
        int len = s.length();
        Character[] array = new Character[len];
        for (int i = 0; i < len ; i++) {
            array[i] = s.charAt(i);
        }
        return array;
    }

    private String toSingleString(){
        StringBuilder st = new StringBuilder();
        for (String row : rows) {
            st.append(row);
        }
        return st.toString();
    }

    private void checkRows(String[] rows){
        if(rows.length > 3){
            throw new CraftingRecipeException("The number of rows must be 3 or under.");
        }else if (rows.length == 0){
            throw new CraftingRecipeException("There should be at least 1 row in the crafting grid.");
        }
        for (String row : rows) {
            if(row.length() > 3 || row.length() == 0){
                throw  new CraftingRecipeException("The length of a row must be 3 or under.");
            }
        }
    }
}
