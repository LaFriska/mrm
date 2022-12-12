package com.friska.mrm.registries;

import com.friska.mrm.mcresources.lang.Lang;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class LangRegistry{

    private static final ArrayList<Lang> LANGS = new ArrayList<>();
    private static final ArrayList<String> UNIQUE_LANGUAGE_CODES = new ArrayList<>();

    /**
     * Use the static strings in the LanguageCode class to find the correct language code. Language codes are basically the name for your language JSON file, e.g en_us.json
     * **/
    public static void register(@Nonnull Lang lang){
        String languageCode = lang.getLanguageCode();
        if(UNIQUE_LANGUAGE_CODES.contains(languageCode)){
            langWithLanguageCode(languageCode).inject(lang);
        }else{
            UNIQUE_LANGUAGE_CODES.add(languageCode);
            LANGS.add(lang);
        }
    }

    private static @Nullable Lang langWithLanguageCode(String languageCode){
        for(Lang lang : LANGS){
            if(lang.getLanguageCode().equals(languageCode)){
                return lang;
            }
        }
        return null;
    }

    /**
     * Builds all registered Lang objects.
     * **/
    public static void build(){
        LANGS.forEach(Lang::build);
    }
}
