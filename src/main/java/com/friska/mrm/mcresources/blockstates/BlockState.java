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

//TODO javadoc

@ExpectAccess
public class BlockState extends MinecraftJSONResource {

    private final ArrayList<Variant> variants = new ArrayList<>();

    private final ArrayList<Case> cases = new ArrayList<>();
    public BlockStateType blockStateType;
    public BlockState(@Nonnull String name, @Nullable BlockStateType blockStateType) {
        super("block state", "assets/" + Config.getModID() + "/blockstates", name);
        this.blockStateType = blockStateType;
    }

    public BlockState(@Nonnull String name) {
        this(name, null);
    }

    //----------------------------------------------VARIANTS------------------------------------------------
    @BlockStateOnly.Variants
    public BlockState addVariant(Variant variant){
        onlyAllow(BlockStateType.VARIANTS);
        this.variants.add(variant);
        return this;
    }

    @BlockStateOnly.Variants
    public BlockState addVariants(Variant... variants){
        onlyAllow(BlockStateType.VARIANTS);
        this.variants.addAll(List.of(variants));
        return this;
    }

    //----------------------------------------------VARIANTS TEMPLATES------------------------------------------------

    public BlockState defaultBlock(String texture, boolean isModded){
        return new BlockState(name, BlockStateType.VARIANTS).addVariant(new Variant((String) null).addModelPointer(texture, isModded));
    }

    public BlockState defaultBlock(String texture){
        return this.defaultBlock(texture, true);
    }

    //----------------------------------------------MULTIPARTS------------------------------------------------

    @BlockStateOnly.Multipart
    public BlockState addCase(@Nonnull Case case_){
        onlyAllow(BlockStateType.MULTIPART);
        this.cases.add(case_);
        return this;
    }

    @BlockStateOnly.Multipart
    public BlockState addCase(@Nonnull ModelPointer modelPointer){
        return this.addCase(new Case(modelPointer));
    }

    @BlockStateOnly.Multipart
    public BlockState addCases(Case... cases){
        onlyAllow(BlockStateType.MULTIPART);
        this.cases.addAll(List.of(cases));
        return this;
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
        JArray jArray = new JArray("multipart");
        ArrayList<JObject> casesJObject = new ArrayList<>();
        this.cases.forEach((c) -> casesJObject.add(c.build()));
        jArray.setArray(casesJObject);
        getBuilder().nest(jArray);
    }
}
