package com.friska.mrm.mcresources.models;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.BlockModelParents;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;


@ExpectModdersToAccess
public class BlockModel extends Model{

    private String renderType = null;

    private static final String texturePath = Config.getModID() + ":block/";

    /**
     * This class creates JSON files for block models.
     * Please note that this class does not have enough attributes to support a Block Model creator for all aspects of block models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * For complicated block models, apps like Blockbench is recommended, as they have a full development team creating an app to let you create Minecraft models for free.
     * <a href="https://www.blockbench.net/">Click here to go to Blockbench.</a> (Minecraft Resource Manager's developer is not sponsored by Blockbench.)
     * <p>
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the blocks you are adding, such as stairs, slabs, signs etc.
     *
     * @param blockId Name of the JSON file, should also be the model blockstate.
     * **/
    public BlockModel(@Nonnull String blockId) {
        super("block", blockId);
    }

    //---------------------------------HIGHER LEVEL ACCESS (Templates etc.)---------------------------------//

    /**
     * <b>Default Cube</b>
     * <p>
     * This template creates a normal Minecraft cube used in most blocks, e.g. dirt, stone, diamond ore, etc. Texture is defaulted to be the name of the JSON file.
     * **/
    public BlockModel defaultCube(){
        return this.setParent(BlockModelParents.CUBE_ALL).addTexture("all", getTextureName(name));
    }
    /**
     * <b>Default Cube</b> (Specific texture)
     * <p>
     * This template creates a normal Minecraft cube used in most blocks, e.g. dirt, stone, diamond ore, etc.
     * @param textureName specify the name of the PNG file for the texture.
     * **/
    @NeedsRevision
    public BlockModel defaultCube(String textureName){
        return this.setParent(BlockModelParents.CUBE_ALL).addTexture("all", getTextureName(textureName));
    }

    //Trees
    /**
     * <b>Log Models</b>
     * Call the same method for stripped logs. Both logHorizontal and log block models need to be created for a log to work properly in game.
     * **/
    public BlockModel log(@Nonnull String sideTexture, @Nonnull String endTexture){
        return this.setParent(BlockModelParents.CUBE_COLUMN).addTextures(new ModelTexture("end", getTextureName(endTexture)), new ModelTexture("side", getTextureName(sideTexture)));
    }

    /**
     * <b>Horizontal Log Models</b>
     * Call the same method for stripped logs. Both logHorizontal and log block models need to be created for a log to work properly in game.
     * **/
    public BlockModel logHorizontal(@Nonnull String sideTexture, @Nonnull String endTexture){
        return this.log(sideTexture, endTexture).setParent(BlockModelParents.CUBE_COLUMN_HORIZONTAL);
    }

    /**
     * <b>Leaves Models</b>
     * **/
    public BlockModel leaves(@Nonnull String textureName){
        return this.defaultCube(textureName).setRenderType("translucent");
    }

    /**
     * <b>Sapling Models</b>
     * **/
    public BlockModel sapling(@Nonnull String textureName){
        return this.setParent(BlockModelParents.CROSS).addTexture("cross", getTextureName(textureName)).setRenderType("cutout");
    }

    /**
     * <b>Wood Models</b>
     * Call the same method for stripped wood. Both woodHorizontal and wood block models need to be created for a wood to work properly in game.
     * **/
    public BlockModel wood(@Nonnull String textureName){
        return this.setParent(BlockModelParents.CUBE_COLUMN).addTextures(new ModelTexture("end", getTextureName(textureName)), new ModelTexture("side", getTextureName(textureName)));
    }

    /**
     * <b>Wood Models</b>
     * Call the same method for stripped wood. Both woodHorizontal and wood block models need to be created for a wood to work properly in game.
     * **/
    public BlockModel woodHorizontal(@Nonnull String textureName){
        return this.wood(textureName).setParent(BlockModelParents.CUBE_COLUMN_HORIZONTAL);
    }

    //---------------------------------LOWER LEVEL ACCESS (Serializer-like methods)---------------------------------//

    /**
     * This method sets the parent model. Similarly to java classes, Minecraft model JSON files can "inherit" other model files.
     * @param parent Path and name of the parent model. Use the static strings defined under BlockModelParents for a list of every parent models used in Minecraft.
     * An example of this is "minecraft:block/outer_stairs".
     * **/
    public BlockModel setParent(@Nonnull String parent){
        this.parent = parent;
        return this;
    }

    /**
     * Adds a key-value pair for textures used in models.
     * @param key The key of the texture, for example, "layer0".
     * @param texture The path and name of the texture file, for example, "minecraft:block/acacia_planks".
     * **/
    public BlockModel addTexture(@Nullable String key, @Nonnull String texture){
        if(key == null) key = "";
        this.textures.add(new ModelTexture(key, texture));
        return this;
    }

    /**
     * Adds multiple key-value pair for textures used in models.
     * @param textures Vararg of ModelTextures, which are records with both the key and the value as parameters.
     * **/
    public BlockModel addTextures(@Nonnull ModelTexture... textures){
        List.of(textures).forEach((t) -> addTexture(t.key(), t.texture()));
        return this;
    }

    /**
     * Setting the render type for the block model. Compatibility is unknown with Fabric, this is mainly a forge feature.
     * The render type indicates how the block should be rendered, whether it is transparent or translucent.
     * @param renderType To specify a render type, there is a list of render type strings like "translucent" or "transparent".
     * **/
    public BlockModel setRenderType(String renderType) {
        this.renderType = renderType;
        return this;
    }

    @Override
    public void build() {
        super.build();
        if(renderType != null) getBuilder().nest(new JValue<>("render_type", renderType));
        getBuilder().build();
    }

    //---------------------------------UTIL METHODS---------------------------------//

    private static String getTextureName(String name){
        return texturePath + name;
    }

}
