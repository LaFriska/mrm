package com.friska.mrm.mcresources.models;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.annotations.NeedsRevision;
import com.friska.mrm.mcresources.data.BlockModelParents;
import com.friska.mrm.system.util.KeyValue;

import javax.annotation.Nonnull;


@ExpectAccess
@SuppressWarnings("unchecked")
public class BlockModel extends Model<BlockModel>{

    /**
     * This class creates JSON files for block models.
     * Please note that this class does not have enough attributes to support a Block Model creator for all aspects of block models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * For complicated block models, apps like Blockbench is recommended, as they have a full development team creating an app to let you create Minecraft models for free.
     * <a href="https://www.blockbench.net/">Click here to go to Blockbench.</a> (Minecraft Resource Manager's developer is not sponsored by Blockbench.)
     * <p>
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the blocks you are adding, such as stairs, slabs, signs etc.
     *
     * @param blockId Name of the model JSON file, which should be pointed by the block state.
     * **/
    public BlockModel(@Nonnull String blockId) {
        super("block", blockId, true);
    }

    /**
     * This class creates JSON files for block models.
     * Please note that this class does not have enough attributes to support a Block Model creator for all aspects of block models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * For complicated block models, apps like Blockbench is recommended, as they have a full development team creating an app to let you create Minecraft models for free.
     * <a href="https://www.blockbench.net/">Click here to go to Blockbench.</a> (Minecraft Resource Manager's developer is not sponsored by Blockbench.)
     * <p>
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the blocks you are adding, such as stairs, slabs, signs etc.
     *
     * @param blockId Name of the model JSON file, which should be pointed by the block state.
     * @param isModded Whether the block is modded. This will determine whether to use the mod ID or "minecraft" as the namespace.
     * **/
    public BlockModel(@Nonnull String blockId, boolean isModded) {
        super("block", blockId, isModded);
    }

    /**
     * <b>Default Cube</b>
     * <p>
     * This template creates a normal Minecraft cube used in most blocks, e.g. dirt, stone, diamond ore, etc. Texture is defaulted to be the name of the JSON file.
     * **/
    public BlockModel defaultCube(){
        return this.defaultCube(name);
    }
    /**
     * <b>Default Cube</b> (Specific texture)
     * <p>
     * This template creates a normal Minecraft cube used in most blocks, e.g. dirt, stone, diamond ore, etc.
     * @param textureName specify the name of the PNG file for the texture.
     * **/
    @NeedsRevision
    public BlockModel defaultCube(String textureName){
        return new BlockModel(name).setParent(BlockModelParents.CUBE_ALL).addTexture("all", getTextureName(textureName));
    }

    //----------------------------------------------------------Trees----------------------------------------------------------//
    /**
     * <b>Log Models</b>
     * Call the same method for stripped logs. Both logHorizontal and log block models need to be created for a log to work properly in game.
     * **/
    public BlockModel log(@Nonnull String sideTexture, @Nonnull String endTexture){
        return new BlockModel(name, isModded).setParent(BlockModelParents.CUBE_COLUMN).addTextures(new KeyValue<>("end", getTextureName(endTexture)), new KeyValue<>("side", getTextureName(sideTexture)));
    }

    /**
     * <b>Horizontal Log Models</b>
     * Call the same method for stripped logs. Both logHorizontal and log block models need to be created for a log to work properly in game.
     * **/
    public BlockModel logHorizontal(@Nonnull String sideTexture, @Nonnull String endTexture){
        return new BlockModel(name, isModded).log(sideTexture, endTexture).setParent(BlockModelParents.CUBE_COLUMN_HORIZONTAL);
    }

    /**
     * <b>Leaves Models</b>
     * **/
    public BlockModel leaves(@Nonnull String textureName){
        return new BlockModel(name, isModded).defaultCube(textureName).setRenderType("translucent");
    }

    /**
     * <b>Sapling Models</b>
     * **/
    public BlockModel sapling(@Nonnull String textureName){
        return new BlockModel(name, isModded).setParent(BlockModelParents.CROSS).addTexture("cross", getTextureName(textureName)).setRenderType("cutout");
    }

    /**
     * <b>Wood Models</b>
     * Call the same method for stripped wood. Both woodHorizontal and wood block models need to be created for a wood to work properly in game.
     * **/
    public BlockModel wood(@Nonnull String textureName){
        return new BlockModel(name, isModded).setParent(BlockModelParents.CUBE_COLUMN).addTextures(new KeyValue<>("end", getTextureName(textureName)), new KeyValue<>("side", getTextureName(textureName)));
    }

    /**
     * <b>Wood Models</b>
     * Call the same method for stripped wood. Both woodHorizontal and wood block models need to be created for a wood to work properly in game.
     * **/
    public BlockModel woodHorizontal(@Nonnull String textureName){
        return new BlockModel(name, isModded).wood(textureName).setParent(BlockModelParents.CUBE_COLUMN_HORIZONTAL);
    }

    //--------------------------------------------------------------------------------------------------------------------//
}
