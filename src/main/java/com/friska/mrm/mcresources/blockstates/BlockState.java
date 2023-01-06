package com.friska.mrm.mcresources.blockstates;

import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.annotations.BlockStateOnly;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.exceptions.BlockStateTypeMismatchException;
import com.friska.mrm.mcresources.MinecraftJSONResource;
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
public class BlockState extends MinecraftJSONResource {

    private final ArrayList<Variant> variants = new ArrayList<>();

    private final ArrayList<Case> cases = new ArrayList<>();
    public BlockStateType blockStateType;

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

        final String south = "facing=south";
        final String west = "facing=west";
        final String east = "facing=east";
        final String north = "facing=north";

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(ceiling, east, f).addModelPointer(new ModelPointer(buttonModel).x(180).y(270)),
                new Variant(ceiling, east, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180).y(270)),
                new Variant(ceiling, north, f).addModelPointer(new ModelPointer(buttonModel).x(180).y(180)),
                new Variant(ceiling, north, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180).y(180)),
                new Variant(ceiling, south, f).addModelPointer(new ModelPointer(buttonModel).x(180)),
                new Variant(ceiling, south, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180)),
                new Variant(ceiling, west, f).addModelPointer(new ModelPointer(buttonModel).x(180).y(90)),
                new Variant(ceiling, west, t).addModelPointer(new ModelPointer(buttonModelPressed).x(180).y(90)),

                new Variant(floor, east, f).addModelPointer(new ModelPointer(buttonModel).y(90)),
                new Variant(floor, east, t).addModelPointer(new ModelPointer(buttonModelPressed).y(90)),
                new Variant(floor, north, f).addModelPointer(new ModelPointer(buttonModel)),
                new Variant(floor, north, t).addModelPointer(new ModelPointer(buttonModelPressed)),
                new Variant(floor, south, f).addModelPointer(new ModelPointer(buttonModel).y(180)),
                new Variant(floor, south, t).addModelPointer(new ModelPointer(buttonModelPressed).y(180)),
                new Variant(floor, west, f).addModelPointer(new ModelPointer(buttonModel).y(270)),
                new Variant(floor, west, t).addModelPointer(new ModelPointer(buttonModelPressed).y(270)),

                new Variant(wall, east, f).addModelPointer(new ModelPointer(buttonModel).uvlock(true).x(90).y(90)),
                new Variant(wall, east, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90).y(90)),
                new Variant(wall, north, f).addModelPointer(new ModelPointer(buttonModel).uvlock(true).x(90)),
                new Variant(wall, north, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90)),
                new Variant(wall, south, f).addModelPointer(new ModelPointer(buttonModel).uvlock(true).x(90).y(180)),
                new Variant(wall, south, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90).y(180)),
                new Variant(wall, west, f).addModelPointer(new ModelPointer(buttonModel).y(270).uvlock(true).x(90)),
                new Variant(wall, west, t).addModelPointer(new ModelPointer(buttonModelPressed).uvlock(true).x(90).y(270))
        );
    }

    public BlockState door(String botLeft, String botLeftOpen, String botRight, String botRightOpen, String topLeft, String topLeftOpen, String topRight, String topRightOpen){

        final String f = "open=false";
        final String t = "open=true";

        final String left = "hinge=left";
        final String right = "hinge=right";

        final String lower = "half=lower";
        final String upper = "half=upper";

        final String south = "facing=south";
        final String west = "facing=west";
        final String east = "facing=east";
        final String north = "facing=north";

        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant(east, lower, left, f).addModelPointer(new ModelPointer(botLeft)),
                new Variant(east, lower, left, t).addModelPointer(new ModelPointer(botLeftOpen).y(90)),
                new Variant(east, lower, right, f).addModelPointer(new ModelPointer(botRight)),
                new Variant(east, lower, right, t).addModelPointer(new ModelPointer(botRightOpen).y(270)),
                new Variant(east, upper, left, f).addModelPointer(new ModelPointer(topLeft)),
                new Variant(east, upper, left, t).addModelPointer(new ModelPointer(topLeftOpen).y(90)),
                new Variant(east, upper, right, f).addModelPointer(new ModelPointer(topRight)),
                new Variant(east, upper, right, t).addModelPointer(new ModelPointer(topRightOpen).y(270)),

                new Variant(north, lower, left, f).addModelPointer(new ModelPointer(botLeft).y(270)),
                new Variant(north, lower, left, t).addModelPointer(new ModelPointer(botLeftOpen)),
                new Variant(north, lower, right, f).addModelPointer(new ModelPointer(botRight).y(270)),
                new Variant(north, lower, right, t).addModelPointer(new ModelPointer(botRightOpen).y(180)),
                new Variant(north, upper, left, f).addModelPointer(new ModelPointer(topLeft).y(270)),
                new Variant(north, upper, left, t).addModelPointer(new ModelPointer(topLeftOpen)),
                new Variant(north, upper, right, f).addModelPointer(new ModelPointer(topRight).y(270)),
                new Variant(north, upper, right, t).addModelPointer(new ModelPointer(topRightOpen).y(180)),

                new Variant(south, lower, left, f).addModelPointer(new ModelPointer(botLeft).y(90)),
                new Variant(south, lower, left, t).addModelPointer(new ModelPointer(botLeftOpen).y(180)),
                new Variant(south, lower, right, f).addModelPointer(new ModelPointer(botRight).y(90)),
                new Variant(south, lower, right, t).addModelPointer(new ModelPointer(botRightOpen)),
                new Variant(south, upper, left, f).addModelPointer(new ModelPointer(topLeft).y(90)),
                new Variant(south, upper, left, t).addModelPointer(new ModelPointer(topLeftOpen).y(180)),
                new Variant(south, upper, right, f).addModelPointer(new ModelPointer(topRight).y(90)),
                new Variant(south, upper, right, t).addModelPointer(new ModelPointer(topRightOpen)),

                new Variant(west, lower, left, f).addModelPointer(new ModelPointer(botLeft).y(180)),
                new Variant(west, lower, left, t).addModelPointer(new ModelPointer(botLeftOpen).y(270)),
                new Variant(west, lower, right, f).addModelPointer(new ModelPointer(botRight).y(180)),
                new Variant(west, lower, right, t).addModelPointer(new ModelPointer(botRightOpen).y(90)),
                new Variant(west, upper, left, f).addModelPointer(new ModelPointer(topLeft).y(180)),
                new Variant(west, upper, left, t).addModelPointer(new ModelPointer(topLeftOpen).y(270)),
                new Variant(west, upper, right, f).addModelPointer(new ModelPointer(topRight).y(180)),
                new Variant(west, upper, right, t).addModelPointer(new ModelPointer(topRightOpen).y(90))
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

    //TODO test the method
    public BlockState pressurePlate(String pressurePlate, String pressurePlateDown){
        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant("powered=false").addModelPointer(pressurePlate),
                new Variant("powered=true").addModelPointer(pressurePlateDown)
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
