package com.friska.mrm.serialiser.builder;

import com.friska.mrm.config.Config;

public class JSONBuilderUtils {

    private static String indentation = null;

    public static String wrapInString(String str){
        return "\"" + str + "\"";
    }

    public static String wrapInCurlyBraces(String str){
        return "{\n" + addIndentation(str) + "}";
    }
    public static String wrapInSquareBraces(String str){
        return "[\n" + addIndentation(str) + "]";
    }

    public static String addIndentation(String str) {
        String[] split = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i <= split.length - 1; i++){
            split[i] = getIndentation() + split[i];
        }
        for(int i = 0; i <= split.length - 1; i++){
            sb.append(split[i]).append("\n");
        }

        return sb.toString();
    }

    private static String getIndentation(){
        if(indentation == null) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i <= Config.getJSONIndentSize() - 1; i++) {
                sb.append(" ");
            }
            indentation = sb.toString();
        }
        return indentation;
    }
    public static String checkJSONString(String key){
        String result = "pass";

        if(key.contains("\"")){
            result = "Key and value should not contain quotation marks.";
        }else if (key.contains("\\")){
            result = "Key and value should not contain escape characters.";
        }
        return result;

    }
}
