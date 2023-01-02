package com.friska.mrm.util;

import com.friska.mrm.serialiser.builder.JObject;

import java.util.ArrayList;

public class Util {

    public static JObject[] getJObjectArray(ArrayList<JObject> jObjectArrayList){

        JObject[] result = new JObject[jObjectArrayList.size()];

        for(int i = 0; i <= jObjectArrayList.size() - 1; i++){
            result[i] = jObjectArrayList.get(i);
        }

        return result;
    }

}
