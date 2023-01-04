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
        super("block state", "assets/" + Config.getModID() + "/blockstates", name);
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
    public BlockState defaultBlock(String model, boolean isModded){
        return new BlockState(name, BlockStateType.VARIANTS).addVariant(new Variant("").addModelPointer(model, isModded));
    }

    /**
     * <b>Default Block States</b>
     *  <p>
     * This template creates a default block block state file, with a single unconditional variant pointing to the block model.
     * **/
    public BlockState defaultBlock(String model){
        return this.defaultBlock(model, true);
    }

    //TREE
    /**
     * <b>Log Block States</b>
     * <p>
     * This template creates the block states file for a log block, use this also for stripped logs, wood and stripped wood.
     * **/
    public BlockState log(String model, String modelHorizontal, boolean isModded){
        return new BlockState(name, BlockStateType.VARIANTS).addVariants(
                new Variant("axis=x")
                        .addModelPointer(new ModelPointer(modelHorizontal, isModded).x(90).y(90)),
                new Variant("axis=y")
                        .addModelPointer(new ModelPointer(model, isModded)),
                new Variant("axis=z")
                        .addModelPointer(new ModelPointer(modelHorizontal, isModded).x(90))
        );
    }
    /**
     * <b>Log Block States</b>
     * <p>
     * This template creates the block states file for a log block, use this also for stripped logs, wood and stripped wood.
     * **/
    public BlockState log(String model, String modelHorizontal){
        return this.log(model, modelHorizontal, true);
    }

    //Plank blocks

    //public BlockState slab(String slabModel, String plankModel, String slabTopModel)


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
