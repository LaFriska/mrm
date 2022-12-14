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
        Config.setDefaultNamespace("bourgeoismod");

        CraftingShapeless craftingShapeless = new CraftingShapeless(ItemIDs.BEACON).addIngredient(ItemIDs.OBSIDIAN, 3).addIngredient(ItemIDs.NETHER_STAR);

        new BlockModel("ruby_log").log("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_log_horizontal").logHorizontal("ruby_log_side", "ruby_log_end");
        new BlockModel("ruby_leaves").leaves("ruby_leaves");
        new BlockModel("ruby_wood").wood("ruby_wood");
        new BlockModel("ruby_wood_horizontal").woodHorizontal("ruby_wood");



        BlockState redstoneLamp = new BlockState("redstone_lamp", BlockStateType.VARIANTS);
        redstoneLamp.addVariant(new Variant("lit=false")
                        .addModelPointers(
                                new ModelPointer("redstone_lamp").x(90),
                                new ModelPointer("@redston325e_lamp").x(234),
                                new ModelPointer("namespace:block/redstone_l32amp").x(923)
                                )
                )
                        .addVariant(new Variant("lit=true").addModelPointer("redstone_lamp_on"));

        BlockState redStoneWire = new BlockState("oak_fence_post", BlockStateType.MULTIPART)
                .addCases(
                        new Case(new ModelPointer("oak_fence_post")),
                        new Case(new ModelPointer("oak_fence_side").uvlock(true)).addCondition("north", true)
                );
        redStoneWire.build();

        redstoneLamp.build();

        BlockState test = new BlockState("spruce_log").log("spruce_log", "spruce_log_horizontal");
        test.build();


        ResourceManager.register(
                new SmeltingRecipe(ItemIDs.SADDLE, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new BlastingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new SmeltingRecipe(ItemIDs.CACTUS, ItemIDs.BEACON, 2, 3),
                new CraftingShaped("xxx", "xox","xxx").setCount(3).addKey('x', ItemIDs.NETHER_STAR).addKey('o', ItemIDs.REDSTONE_DUST).setResult(ItemIDs.OBSIDIAN).setGroup("lmao"),
                new CraftingShapeless(ItemIDs.NOTE_BLOCK).addIngredient(ItemIDs.REDSTONE_DUST).setCount(3).addIngredient(ItemIDs.OBSIDIAN),
                new BlockModel("uranium_block").defaultCube().log("lmaomod:block/uranium_end", "@uranium_side"),
                new BlockState("acacia_door").door("@acacia_door_bottom_left", "@acacia_door_bottom_left_open", "@acacia_door_bottom_right", "@acacia_door_bottom_right_open", "@acacia_door_top_left", "@acacia_door_top_left_open", "@acacia_door_top_right", "@acacia_door_top_right_open"),
                new BlockState("acacia_fence").fence("@acacia_fence_side","@acacia_fence_post" ),
                new BlockState("acacia_pressure_plate").pressurePlate("@acacia_pressure_plate", "@acacia_pressure_plate_down"),
                new BlockState("acacia_fence_gate").fenceGate("@acacia_fence_gate", "@acacia_fence_gate_wall", "@acacia_fence_gate_open", "@acacia_fence_gate_wall_open"),
                new BlockState("acacia_stairs").stairs("@acacia_stairs", "@acacia_stairs_inner", "@acacia_stairs_outer"),
                new BlockState("acacia_trapdoor").trapdoor("@acacia_trapdoor_bottom", "@acacia_trapdoor_top", "@acacia_trapdoor_open")
        );

        ResourceManager.buildAll();
    }
}
