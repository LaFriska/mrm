package com.friska.mrm.main;

import com.friska.mrm.system.config.Config;
import com.friska.mrm.mcresources.blockstates.BlockState;
import com.friska.mrm.mcresources.blockstates.BlockStateType;
import com.friska.mrm.mcresources.blockstates.properties.Case;
import com.friska.mrm.mcresources.blockstates.properties.ModelPointer;
import com.friska.mrm.mcresources.blockstates.properties.Variant;
import com.friska.mrm.mcresources.data.ItemIDs;
import com.friska.mrm.mcresources.models.BlockModel;
import com.friska.mrm.mcresources.recipes.cooking.BlastingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmeltingRecipe;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShaped;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShapeless;
import com.friska.mrm.registries.ResourceManager;

public class Main {

    public static void main(String... args) {
        Config.setModID("bourgeoismod");

        CraftingShapeless craftingShapeless = new CraftingShapeless(ItemIDs.BEACON).addIngredient(ItemIDs.OBSIDIAN, 3).addIngredient(ItemIDs.NETHER_STAR);

        new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_log_horizontal").logHorizontal("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_leaves").leaves("ruby_leaves");
        new BlockModel("ruby_wood").wood("ruby_wood");
        new BlockModel("ruby_wood_horizontal").woodHorizontal("ruby_wood");



        BlockState redstoneLamp = new BlockState("redstone_lamp", BlockStateType.VARIANTS);
        redstoneLamp.addVariant(new Variant("lit=false").addModelPointer("redstone_lamp", false))
                        .addVariant(new Variant("lit=true").addModelPointer("redstone_lamp_on", false));

        BlockState redStoneWire = new BlockState("oak_fence_post", BlockStateType.MULTIPART)
                .addCases(
                        new Case(new ModelPointer("oak_fence_post", false)),
                        new Case(new ModelPointer("oak_fence_side", false).uvlock(true)).addCondition("north", true)
                );
        redStoneWire.build();

        redstoneLamp.build();


        ResourceManager.register(
                new SmeltingRecipe(ItemIDs.SADDLE, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new BlastingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new CraftingShaped("xxx", "xox","xxx").setCount(3).addKey('x', ItemIDs.NETHER_STAR).addKey('o', ItemIDs.REDSTONE_DUST).setResult(ItemIDs.OBSIDIAN).setGroup("lmao"),
                new CraftingShapeless(ItemIDs.NOTE_BLOCK).addIngredient(ItemIDs.REDSTONE_DUST).setCount(3).addIngredient(ItemIDs.OBSIDIAN),
                new BlockModel("uranium_block", false).defaultCube().log("uranium_end", "uranium_side")
        );

        ResourceManager.buildAll();
    }
}
