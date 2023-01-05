package com.friska.mrm.mcresources.models;

import com.friska.mrm.system.config.Config;
import com.friska.mrm.mcresources.data.ItemModelParents;
import com.friska.mrm.system.util.Util;

import javax.annotation.Nonnull;

public class ItemModel extends Model<ItemModel>{

    /**
     * This class creates JSON files for item models.
     * Please note that this class does not have enough attributes to support an Item Model creator for all aspects of item models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the items you are adding.
     *
     * @param itemID Name of the JSON file as well as the item ID.
     * @param parentType Whether the parent type is a block or an item.
     * **/

    protected ItemModel(@Nonnull String itemID, @Nonnull String parentType) {
        super("item", itemID, parentType);
    }

    /**
     * This class creates JSON files for item models.
     * Please note that this class does not have enough attributes to support an Item Model creator for all aspects of item models. Even if it does, it would be a complete mess to
     * create complex models in Java.
     * Regardlessly, this class provides basic tools and templates that can cover 95% of the items you are adding.
     *
     * Use the constructor overload to specify whether the parenting model is a block or an item.
     *
     * @param itemID Name of the JSON file as well as the item ID.
     * **/
    protected ItemModel(@Nonnull String itemID) {
        super("item", itemID, "item");
    }


    /**
     * <b>Default Item</b>
     * <p>
     * This template creates a normal Minecraft item model (non-block-items), e.g. potato, diamond, etc.
     * @param textureName Name of the texture PNG file.
     * **/
    public ItemModel defaultItem(String textureName){
        return new ItemModel(name).setParent(ItemModelParents.GENERATED).addTexture("layer0", textureName);
    }
    /**
     * <b>Default Item</b>
     * <p>
     * This template creates a normal Minecraft item model (non-block-items), e.g. potato, diamond, etc. Texture is defaulted to be the name of the JSON file. Use the method overload to define the texture.
     * **/
    public ItemModel defaultItem(){
        return new ItemModel(name).defaultItem(name);
    }

    /**
     * <b>Block Item</b>
     * <p>
     * This template creates a Minecraft item model for blocks, e.g. stone, diamond_ore, etc.
     * @param blockID Block ID needed to set the parent block ID.
     * **/
    public ItemModel blockItem(String blockID){
        return new ItemModel(name, "block").setParent(blockID);
    }

    /**
     * <b>Inventory Specific Block Item</b>
     * <p>
     * Instead of parenting the block model with the block ID as its name, inventory specific block items parent a model like "oak_fence_inventory" rather than "oak_fence".
     * This is because the default block model does not work well when forced into the inventory. 99% of the inventory specific block items are: fences, walls, buttons.
     * @param blockID Block ID needed to set the parent block ID.
     * **/
    public ItemModel inventorySpecificBlockItem(String blockID){return new ItemModel(name).setParent(blockID + "_inventory");}
}
