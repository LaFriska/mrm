package com.friska.mrm.mcresources.blockstates;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.annotations.BlockStateOnly;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.exceptions.BlockStateTypeMismatchException;
import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.mcresources.blockstates.properties.Case;
import com.friska.mrm.mcresources.blockstates.properties.ModelPointer;
import com.friska.mrm.mcresources.blockstates.properties.Variant;
import com.friska.mrm.system.serialiser.builder.JArray;
import com.friska.mrm.system.serialiser.builder.JObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ExpectAccess
public class BlockState extends MinecraftResource {

    private final ArrayList<Variant> variants = new ArrayList<>();

    private final ArrayList<Case> cases = new ArrayList<>();
    public BlockStateType blockStateType;

    //Template conditions

    private static final String SOUTH = "facing=south";
    private static final String WEST = "facing=west";
    private static final String EAST = "facing=east";
    private static final String NORTH = "facing=north";

    private final static String BOTTOM = "half=bottom";
    private final static String TOP = "half=top";

    private static final String CLOSED = "open=false";
    private static final String OPEN = "open=true";

    /**
     * This class builds a JSON file for block states, which are used in Minecraft to point to different block models given different conditions.
     * There are two types of block state JSON files, <b>variants</b> and <b>multipart</b>, and methods in this class denoted with @BlockStateOnly means that the method
     * may only be called on the specified type of block state, breaking this rule will cause an exception.
     *
     * <a href="https://minecraft.fandom.com/el/wiki/Model">Click here for more information on block states.</a>
     *
     * @param name Name of the JSON file.
     * @param blockStateType Type of block state, variants or multipart, denote using the BlockStateType enumerator.
     * <p>
     * You may leave choose not to specify the blockStateType, but that means only templated methods could be called from this class.
     * **/
    public BlockState(@Nonnull String name, @Nullable BlockStateType blockStateType) {
        super("block state", "assets/" + Config.getDefaultNamespace() + "/blockstates", name);
        this.blockStateType = blockStateType;
    }

    /**
     * This class builds a JSON file for block states, which are used in Minecraft to point to different block models given different conditions.
     * There are two types of block state JSON files, <b>variants</b> and <b>multipart</b>, and methods in this class denoted with @BlockStateOnly means that the method
     * may only be called on the specified type of block state, breaking this rule will cause an exception.
     *
     * @param name Name of the JSON file.
     * <p>
     * You may leave choose not to specify the blockStateType, but that means only templated methods could be called from this class.
     * **/
    public BlockState(@Nonnull String name) {
        this(name, null);
    }

    //----------------------------------------------VARIANTS------------------------------------------------
    /**
     * Adds a variant to your block state JSON file.
     * **/
    @BlockStateOnly.Variants
    public BlockState addVariant(Variant variant){
        onlyAllow(BlockStateType.VARIANTS);
        this.variants.add(variant);
        return this;
    }

    /**
     * Adds multiple variants to your block state JSON file.
     * **/
    @BlockStateOnly.Variants
    public BlockState addVariants(Variant... variants){
        onlyAllow(BlockStateType.VARIANTS);
        this.variants.addAll(List.of(variants));
        return this;
    }
    //----------------------------------------------MULTIPARTS------------------------------------------------

    /**
     * Adds a custom case to a multipart block state JSON file.
     * **/

    @BlockStateOnly.Multipart
    public BlockState addCase(@Nonnull Case case_){
        onlyAllow(BlockStateType.MULTIPART);
        this.cases.add(case_);
        return this;
    }

    /**
     * Adds an unconditional case to a multipart block state JSON file, by only specifying the ModelPointer. Use the method with the Case parameter to add conditions to it.
     * **/
    @BlockStateOnly.Multipart
    public BlockState addCase(@Nonnull ModelPointer modelPointer){
        return this.addCase(new Case(modelPointer));
    }

    /**
     * Adds multiple custom cases to a multipart block state JSON file.
     * **/
    @BlockStateOnly.Multipart
    public BlockState addCases(Case... cases){
        onlyAllow(BlockStateType.MULTIPART);
        this.cases.addAll(List.of(cases));
        return this;
    }

    //----------------------------------------------TEMPLATES------------------------------------------------

    /**
     * <b>Default Block States</b>
     *<p>
     * This template creates a default block block state file, with a single unconditional variant pointing to the block model.
     * **/
    public BlockState defaultBlock(String model){
        return new BlockState(name, BlockStateType.VARIANTS).addVariant(new Variant("").addModelPointer(model));
    }

    //TREE
    /**
     * <b>Log Block States</b>
     * <p>
     * This template creates the block states file for a log block, use this also for stripped logs, wood and stripped wood.
     * **/
    public BlockState log(String model, String modelHorizontal){
        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant("axis=x")
                        .addModelPointer(new ModelPointer(modelHorizontal).x(90).y(90)),
                new Variant("axis=y")
                        .addModelPointer(new ModelPointer(model)),
                new Variant("axis=z")
                        .addModelPointer(new ModelPointer(modelHorizontal).x(90))
        );
    }

    //Plank blocks

    public BlockState slab(String slabModel, String plankModel, String slabTopModel){
        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant("type=bottom").addModelPointer(slabModel),
                new Variant("type=double").addModelPointer(plankModel),
                new Variant("type=top").addModelPointer(slabTopModel)
        );
    }

    public BlockState button(String buttonModel, String buttonModelPressed){

        final String t = "powered=true";
        final String f = "powered=false";

        final String ceiling = "face=ceiling";
        final String floor = "face=floor";
        final String wall = "face=wall";

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(ceiling, EAST, f).addModelPointer(new ModelPointer(buttonModel).x(180).y(270)),
                new Variant(ceiling, EAST, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180).y(270)),
                new Variant(ceiling, NORTH, f).addModelPointer(new ModelPointer(buttonModel).x(180).y(180)),
                new Variant(ceiling, NORTH, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180).y(180)),
                new Variant(ceiling, SOUTH, f).addModelPointer(new ModelPointer(buttonModel).x(180)),
                new Variant(ceiling, SOUTH, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180)),
                new Variant(ceiling, WEST, f).addModelPointer(new ModelPointer(buttonModel).x(180).y(90)),
                new Variant(ceiling, WEST, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180).y(90)),

                new Variant(floor, EAST, f).addModelPointer(new ModelPointer(buttonModel).y(90)),
                new Variant(floor, EAST, t).addModelPointer(new ModelPointer(buttonModelPressed).y(90)),
                new Variant(floor, NORTH, f).addModelPointer(new ModelPointer(buttonModel)),
                new Variant(floor, NORTH, t).addModelPointer(new ModelPointer(buttonModelPressed)),
                new Variant(floor, SOUTH, f).addModelPointer(new ModelPointer(buttonModel).y(180)),
                new Variant(floor, SOUTH, t).addModelPointer(new ModelPointer(buttonModelPressed).y(180)),
                new Variant(floor, WEST, f).addModelPointer(new ModelPointer(buttonModel).y(270)),
                new Variant(floor, WEST, t).addModelPointer(new ModelPointer(buttonModelPressed).y(270)),

                new Variant(wall, EAST, f).addModelPointer(new ModelPointer(buttonModel).uvlock(true).x(90).y(90)),
                new Variant(wall, EAST, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90).y(90)),
                new Variant(wall, NORTH, f).addModelPointer(new ModelPointer(buttonModel).uvlock(true).x(90)),
                new Variant(wall, NORTH, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90)),
                new Variant(wall, SOUTH, f).addModelPointer(new ModelPointer(buttonModel).uvlock(true).x(90).y(180)),
                new Variant(wall, SOUTH, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90).y(180)),
                new Variant(wall, WEST, f).addModelPointer(new ModelPointer(buttonModel).y(270).uvlock(true).x(90)),
                new Variant(wall, WEST, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90).y(270))
        );
    }

    public BlockState door(String botLeft, String botLeftOpen, String botRight, String botRightOpen, String topLeft, String topLeftOpen, String topRight, String topRightOpen){

        final String left = "hinge=left";
        final String right = "hinge=right";

        final String lower = "half=lower";
        final String upper = "half=upper";

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(EAST, lower, left, CLOSED).addModelPointer(new ModelPointer(botLeft)),
                new Variant(EAST, lower, left, OPEN).addModelPointer(new ModelPointer(botLeftOpen).y(90)),
                new Variant(EAST, lower, right, CLOSED).addModelPointer(new ModelPointer(botRight)),
                new Variant(EAST, lower, right, OPEN).addModelPointer(new ModelPointer(botRightOpen).y(270)),
                new Variant(EAST, upper, left, CLOSED).addModelPointer(new ModelPointer(topLeft)),
                new Variant(EAST, upper, left, OPEN).addModelPointer(new ModelPointer(topLeftOpen).y(90)),
                new Variant(EAST, upper, right, CLOSED).addModelPointer(new ModelPointer(topRight)),
                new Variant(EAST, upper, right, OPEN).addModelPointer(new ModelPointer(topRightOpen).y(270)),

                new Variant(NORTH, lower, left, CLOSED).addModelPointer(new ModelPointer(botLeft).y(270)),
                new Variant(NORTH, lower, left, OPEN).addModelPointer(new ModelPointer(botLeftOpen)),
                new Variant(NORTH, lower, right, CLOSED).addModelPointer(new ModelPointer(botRight).y(270)),
                new Variant(NORTH, lower, right, OPEN).addModelPointer(new ModelPointer(botRightOpen).y(180)),
                new Variant(NORTH, upper, left, CLOSED).addModelPointer(new ModelPointer(topLeft).y(270)),
                new Variant(NORTH, upper, left, OPEN).addModelPointer(new ModelPointer(topLeftOpen)),
                new Variant(NORTH, upper, right, CLOSED).addModelPointer(new ModelPointer(topRight).y(270)),
                new Variant(NORTH, upper, right, OPEN).addModelPointer(new ModelPointer(topRightOpen).y(180)),

                new Variant(SOUTH, lower, left, CLOSED).addModelPointer(new ModelPointer(botLeft).y(90)),
                new Variant(SOUTH, lower, left, OPEN).addModelPointer(new ModelPointer(botLeftOpen).y(180)),
                new Variant(SOUTH, lower, right, CLOSED).addModelPointer(new ModelPointer(botRight).y(90)),
                new Variant(SOUTH, lower, right, OPEN).addModelPointer(new ModelPointer(botRightOpen)),
                new Variant(SOUTH, upper, left, CLOSED).addModelPointer(new ModelPointer(topLeft).y(90)),
                new Variant(SOUTH, upper, left, OPEN).addModelPointer(new ModelPointer(topLeftOpen).y(180)),
                new Variant(SOUTH, upper, right, CLOSED).addModelPointer(new ModelPointer(topRight).y(90)),
                new Variant(SOUTH, upper, right, OPEN).addModelPointer(new ModelPointer(topRightOpen)),

                new Variant(WEST, lower, left, CLOSED).addModelPointer(new ModelPointer(botLeft).y(180)),
                new Variant(WEST, lower, left, OPEN).addModelPointer(new ModelPointer(botLeftOpen).y(270)),
                new Variant(WEST, lower, right, CLOSED).addModelPointer(new ModelPointer(botRight).y(180)),
                new Variant(WEST, lower, right, OPEN).addModelPointer(new ModelPointer(botRightOpen).y(90)),
                new Variant(WEST, upper, left, CLOSED).addModelPointer(new ModelPointer(topLeft).y(180)),
                new Variant(WEST, upper, left, OPEN).addModelPointer(new ModelPointer(topLeftOpen).y(270)),
                new Variant(WEST, upper, right, CLOSED).addModelPointer(new ModelPointer(topRight).y(180)),
                new Variant(WEST, upper, right, OPEN).addModelPointer(new ModelPointer(topRightOpen).y(90))
        );

    }

    public BlockState fence(String fenceSide, String fencePost){
        return new BlockState(name, BlockStateType.MULTIPART).addCases(
                new Case(new ModelPointer(fencePost)),
                new Case(new ModelPointer(fenceSide).uvlock(true)).addCondition("north", true),
                new Case(new ModelPointer(fenceSide).uvlock(true).y(90)).addCondition("east", true),
                new Case(new ModelPointer(fenceSide).uvlock(true).y(180)).addCondition("south", true),
                new Case(new ModelPointer(fenceSide).uvlock(true).y(270)).addCondition("west", true)
        );
    }
    public BlockState pressurePlate(String pressurePlate, String pressurePlateDown){
        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant("powered=false").addModelPointer(pressurePlate),
                new Variant("powered=true").addModelPointer(pressurePlateDown)
        );
    }

    public BlockState fenceGate(String gate, String gateWall, String gateOpen, String gateWallOpen){
        final String iwt = "in_wall=true";
        final String iwf = "in_wall=false";
        final String ot = "open=true";
        final String of = "open=false";

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(EAST, iwf, of).addModelPointer(new ModelPointer(gate).uvlock(true).y(270)),
                new Variant(EAST, iwf, ot).addModelPointer(new ModelPointer(gateOpen).uvlock(true).y(270)),
                new Variant(EAST, iwt, of).addModelPointer(new ModelPointer(gateWall).uvlock(true).y(270)),
                new Variant(EAST, iwt, ot).addModelPointer(new ModelPointer(gateWallOpen).uvlock(true).y(270)),

                new Variant(NORTH, iwf, of).addModelPointer(new ModelPointer(gate).uvlock(true).y(180)),
                new Variant(NORTH, iwf, ot).addModelPointer(new ModelPointer(gateOpen).uvlock(true).y(180)),
                new Variant(NORTH, iwt, of).addModelPointer(new ModelPointer(gateWall).uvlock(true).y(180)),
                new Variant(NORTH, iwt, ot).addModelPointer(new ModelPointer(gateWallOpen).uvlock(true).y(180)),

                new Variant(SOUTH, iwf, of).addModelPointer(new ModelPointer(gate).uvlock(true)),
                new Variant(SOUTH, iwf, ot).addModelPointer(new ModelPointer(gateOpen).uvlock(true)),
                new Variant(SOUTH, iwt, of).addModelPointer(new ModelPointer(gateWall).uvlock(true)),
                new Variant(SOUTH, iwt, ot).addModelPointer(new ModelPointer(gateWallOpen).uvlock(true)),

                new Variant(WEST, iwf, of).addModelPointer(new ModelPointer(gate).uvlock(true).y(90)),
                new Variant(WEST, iwf, ot).addModelPointer(new ModelPointer(gateOpen).uvlock(true).y(90)),
                new Variant(WEST, iwt, of).addModelPointer(new ModelPointer(gateWall).uvlock(true).y(90)),
                new Variant(WEST, iwt, ot).addModelPointer(new ModelPointer(gateWallOpen).uvlock(true).y(90))
        );

    }

    public BlockState stairs(String stairs, String inner, String outer){



        final String innerLeft = "shape=inner_left";
        final String innerRight = "shape=inner_right";
        final String outerLeft = "shape=outer_left";
        final String outerRight = "shape=outer_right";
        final String straight = "shape=straight";

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(EAST, BOTTOM, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).y(270)),
                new Variant(EAST, BOTTOM, innerRight).addModelPointer(new ModelPointer(inner)),
                new Variant(EAST, BOTTOM, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).y(270)),
                new Variant(EAST, BOTTOM, outerRight).addModelPointer(new ModelPointer(outer)),
                new Variant(EAST, BOTTOM, straight).addModelPointer(new ModelPointer(stairs)),

                new Variant(EAST, TOP, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).x(180)),
                new Variant(EAST, TOP, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).x(180).y(90)),
                new Variant(EAST, TOP, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).x(180)),
                new Variant(EAST, TOP, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).x(180).y(90)),
                new Variant(EAST, TOP, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).x(180)),

                new Variant(NORTH, BOTTOM, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).y(180)),
                new Variant(NORTH, BOTTOM, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).y(270)),
                new Variant(NORTH, BOTTOM, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).y(180)),
                new Variant(NORTH, BOTTOM, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).y(270)),
                new Variant(NORTH, BOTTOM, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).y(270)),

                new Variant(NORTH, TOP, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).x(180).y(270)),
                new Variant(NORTH, TOP, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).x(180)),
                new Variant(NORTH, TOP, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).x(180).y(270)),
                new Variant(NORTH, TOP, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).x(180)),
                new Variant(NORTH, TOP, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).x(180).y(270)),

                new Variant(SOUTH, BOTTOM, innerLeft).addModelPointer(new ModelPointer(inner)),
                new Variant(SOUTH, BOTTOM, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).y(90)),
                new Variant(SOUTH, BOTTOM, outerLeft).addModelPointer(new ModelPointer(outer)),
                new Variant(SOUTH, BOTTOM, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).y(90)),
                new Variant(SOUTH, BOTTOM, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).y(90)),

                new Variant(SOUTH, TOP, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).x(180).y(90)),
                new Variant(SOUTH, TOP, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).x(180).y(180)),
                new Variant(SOUTH, TOP, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).x(180).y(90)),
                new Variant(SOUTH, TOP, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).x(180).y(180)),
                new Variant(SOUTH, TOP, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).x(180).y(90)),

                new Variant(WEST, BOTTOM, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).y(90)),
                new Variant(WEST, BOTTOM, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).y(180)),
                new Variant(WEST, BOTTOM, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).y(90)),
                new Variant(WEST, BOTTOM, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).y(180)),
                new Variant(WEST, BOTTOM, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).y(180)),

                new Variant(WEST, TOP, innerLeft).addModelPointer(new ModelPointer(inner).uvlock(true).x(180).y(180)),
                new Variant(WEST, TOP, innerRight).addModelPointer(new ModelPointer(inner).uvlock(true).x(180).y(270)),
                new Variant(WEST, TOP, outerLeft).addModelPointer(new ModelPointer(outer).uvlock(true).x(180).y(180)),
                new Variant(WEST, TOP, outerRight).addModelPointer(new ModelPointer(outer).uvlock(true).x(180).y(270)),
                new Variant(WEST, TOP, straight).addModelPointer(new ModelPointer(stairs).uvlock(true).x(180).y(180))
        );
    }

    public BlockState trapdoor(String bottom, String top, String open){

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(EAST, BOTTOM, CLOSED).addModelPointer(new ModelPointer(bottom).y(90)),
                new Variant(EAST, BOTTOM, OPEN).addModelPointer(new ModelPointer(open).y(90)),
                new Variant(EAST, TOP, CLOSED).addModelPointer(new ModelPointer(top).y(90)),
                new Variant(EAST, TOP, OPEN).addModelPointer(new ModelPointer(open).x(180).y(270)),

                new Variant(NORTH, BOTTOM, CLOSED).addModelPointer(new ModelPointer(bottom)),
                new Variant(NORTH, BOTTOM, OPEN).addModelPointer(new ModelPointer(open)),
                new Variant(NORTH, TOP, CLOSED).addModelPointer(new ModelPointer(top)),
                new Variant(NORTH, TOP, OPEN).addModelPointer(new ModelPointer(open).x(180).y(180)),

                new Variant(SOUTH, BOTTOM, CLOSED).addModelPointer(new ModelPointer(bottom).y(180)),
                new Variant(SOUTH, BOTTOM, OPEN).addModelPointer(new ModelPointer(open).y(180)),
                new Variant(SOUTH, TOP, CLOSED).addModelPointer(new ModelPointer(top).y(180)),
                new Variant(SOUTH, TOP, OPEN).addModelPointer(new ModelPointer(open).x(180).y(0)),

                new Variant(WEST, BOTTOM, CLOSED).addModelPointer(new ModelPointer(bottom).y(270)),
                new Variant(WEST, BOTTOM, OPEN).addModelPointer(new ModelPointer(open).y(270)),
                new Variant(WEST, TOP, CLOSED).addModelPointer(new ModelPointer(top).y(270)),
                new Variant(WEST, TOP, OPEN).addModelPointer(new ModelPointer(open).x(180).y(90))
        );

    }


    //----------------------------------------------ALL------------------------------------------------

    private void onlyAllow(BlockStateType blockStateType){

        if(this.blockStateType == null) throw new BlockStateTypeMismatchException("The block state type must be defined using the constructor overload.");

        if (this.blockStateType != blockStateType) {
            switch (blockStateType){
                case MULTIPART -> throw new BlockStateTypeMismatchException("Only methods annotated with \"@Multipart\" could be used in a multiparts block state builder.");
                case VARIANTS -> throw new BlockStateTypeMismatchException("Only methods annotated with \"@Variant\" could be used in a variants block state builder.");
            }
        }
    }

    /**
     * Builds the JSON file.
     * **/
    @Override
    public void build() {
        createBuilder();
        switch (blockStateType){
            case VARIANTS -> buildVariants();
            case MULTIPART -> buildMultipart();
        }
        getBuilder().build();
    }

    private void buildVariants(){
        JObject jObject = new JObject("variants");
        variants.forEach((v) -> jObject.nest(v.build()));
        getBuilder().nest(jObject);
    }

    private void buildMultipart(){
        getBuilder().nest(new JArray("multipart").setArray(this.cases));
    }
}
