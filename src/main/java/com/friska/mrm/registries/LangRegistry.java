package com.friska.mrm.registries;

import com.friska.mrm.mcresources.lang.Lang;

import javax.annotation.Nonnull;
import java.util.ArrayList;

public class LangRegistry{

    private static ArrayList<Lang> LANGS = new ArrayList<>();

    private static ArrayList<String> uniqueLanguageCodes = new ArrayList<>();

    /**
     * Use the static strings in the LanguageCode class to find the correct language code. Language codes are basically the name for your language JSON file, e.g en_us.json
     * **/
    public static void register(@Nonnull Lang lang){
        String languageCode = lang.getLanguageCode();
        if(uniqueLanguageCodes.contains(languageCode)){
            langWithLanguageCode(languageCode).inject(lang);
        }else{
            uniqueLanguageCodes.add(languageCode);
            LANGS.add(lang);
        }
    }

    private static Lang langWithLanguageCode(String languageCode){
        for(Lang lang : LANGS){
            if(lang.getLanguageCode().equals(languageCode)){
                return lang;
            }
        }
        return null;
    }

    public static void build(){
        LANGS.forEach(Lang::build);
    }
}
