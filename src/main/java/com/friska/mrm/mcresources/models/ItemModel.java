package com.friska.mrm.mcresources.models;

import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.data.ItemModelParents;

import javax.annotation.Nonnull;

public class ItemModel extends Model<ItemModel>{

    /**
     * This class creates JSON files for item models.
     * Please note that this class does not have enough attributes to support an Item Model creator for all aspects of item models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the items you are adding.
     *
     * @param itemID Name of the JSON file as well as the item ID.
     * **/

    protected ItemModel(@Nonnull String itemID) {
        super("item", itemID, true);
    }
    /**
     * This class creates JSON files for item models.
     * Please note that this class does not have enough attributes to support an Item Model creator for all aspects of item models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the items you are adding.
     *
     * @param itemID Name of the JSON file as well as the item ID.
     * @param isModded Whether the item is modded. This will determine whether to use the mod ID or "minecraft" as the namespace.
     * **/

    protected ItemModel(@Nonnull String itemID, boolean isModded) {
        super("item", itemID, isModded);
    }


    /**
     * <b>Default Item</b>
     * <p>
     * This template creates a normal Minecraft item model (non-block-items), e.g. potato, diamond, etc.
     * @param textureName Name of the texture PNG file.
     * **/
    public ItemModel defaultItem(String textureName){
        return new ItemModel(name, isModded).setParent(ItemModelParents.GENERATED).addTexture("layer0", getTextureName(textureName));
    }
    /**
     * <b>Default Item</b>
     * <p>
     * This template creates a normal Minecraft item model (non-block-items), e.g. potato, diamond, etc. Texture is defaulted to be the name of the JSON file. Use the method overload to define the texture.
     * **/
    public ItemModel defaultItem(){
        return new ItemModel(name, isModded).defaultItem(name);
    }

    /**
     * <b>Block Item</b>
     * <p>
     * This template creates a Minecraft item model for blocks, e.g. stone, diamond_ore, etc.
     * @param blockID Block ID needed to set the parent block ID.
     * **/
    public ItemModel blockItem(String blockID){
        return new ItemModel(name, isModded).setParent(getModBlockParentStr(blockID));
    }

    /**
     * <b>Inventory Specific Block Item</b>
     * <p>
     * Instead of parenting the block model with the block ID as its name, inventory specific block items parent a model like "oak_fence_inventory" rather than "oak_fence".
     * This is because the default block model does not work well when forced into the inventory. 99% of the inventory specific block items are: fences, walls, buttons.
     * @param blockID Block ID needed to set the parent block ID.
     * **/
    public ItemModel inventorySpecificBlockItem(String blockID){return new ItemModel(name, isModded).setParent(getModBlockParentStr(blockID + "_inventory"));}

    private String getModBlockParentStr(String blockID){
        return isModded ? Config.getModID() + ":block/" + blockID : "minecraft:block/" + blockID;
    }
}
