package com.friska.mrm.templates;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.models.BlockModel;

@ExpectAccess
@NeedsRevision("Javadoc required, encapsulation of block model and blockstate etc required")

//TODO encapsulation of block model and blockstate etc required

public class TreeBlocks implements Template{

    private final String treeName;

    private BlockModel woodBM;
    private BlockModel strippedWoodBM;
    private BlockModel logBM;
    private BlockModel strippedLogBM;
    private BlockModel leavesBM;
    private BlockModel saplingBM;
    public TreeBlocks(String treeName){
        this.treeName = treeName;

        this.woodBM = new BlockModel(treeName + "_wood").wood(treeName + "_" + "wood");
        this.strippedWoodBM = new BlockModel("stripped_" + treeName + "_wood").wood("stripped_" + treeName + "_wood");
        this.logBM = new BlockModel(treeName + "_log").log(treeName + "_log", treeName + "_log_top");
        this.strippedLogBM = new BlockModel("stripped_" + treeName + "_log").log("stripped_" + treeName + "_log", "stripped_" + treeName + "_log_top");
        this.leavesBM = new BlockModel(treeName + "_leaves").leaves(treeName + "_leaves");

    }

    //--------------------BLOCK MODEL ACCESSORS--------------------------//

    /**
     * Getter method for the block model of the wood block.
     * **/
    public BlockModel getWoodBlockModel() {
        return woodBM;
    }

    /**
     * Getter method for the block model of the stripped wood block.
     * **/
    public BlockModel getStrippedWoodBlockModel() {
        return strippedWoodBM;
    }

    @Override
    public void build() {
        this.woodBM.build();
        this.strippedWoodBM.build();
    }
}
