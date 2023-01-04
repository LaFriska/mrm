package com.friska.mrm.system.util;

import com.friska.mrm.system.interfaces.JObjectBuildable;
import com.friska.mrm.system.serialiser.builder.JObject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class Util {

    public static JObject[] getJObjectArray(@Nonnull ArrayList<? extends JObjectBuildable> jObjectBuildables){
        JObject[] result = new JObject[jObjectBuildables.size()];
        for(int i = 0; i <= jObjectBuildables.size() - 1; i++){
            result[i] = jObjectBuildables.get(i).toJObject(null);
        }
        return result;
    }

}
