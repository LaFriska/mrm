package com.friska.mrm.templates;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.models.BlockModel;

@ExpectAccess
@NeedsRevision("Javadoc required")
public class TreeBlocks {

    private final String treeName;

    private BlockModel woodBlockModel;

    public TreeBlocks(String treeName){
        this.treeName = treeName;
        this.woodBlockModel = new BlockModel(treeName + "_" + "wood").wood(treeName + "_" + "wood");
    }

    //--------------------ID AND TEXTURE NAME CHANGERS-------------------//

    public TreeBlocks setWoodBlockModelIDs(){
        return this;
    }

    //--------------------ACCESSORS--------------------------//

    /**
     * Getter method for the block model of the wood tree type.
     * **/
    public BlockModel getWoodBlockModel() {
        return woodBlockModel;
    }
}
