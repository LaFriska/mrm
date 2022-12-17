package com.friska.mrm.mcresources.blockstates;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.MinecraftJSONResource;

import javax.annotation.Nonnull;

//TODO javadoc
@ExpectAccess
public class BlockState extends MinecraftJSONResource {

    public BlockState(@Nonnull String name) {
        super("block state", "assets/" + Config.getModID() + "/blockstates", name);
    }

    @Override
    public void build() {

    }
}
