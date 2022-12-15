package com.friska.mrm.registries;

import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.lang.Lang;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;

public class LangRegistry{

    private static final ArrayList<Lang> LANGS = new ArrayList<>();
    private static final ArrayList<String> UNIQUE_LANGUAGE_CODES = new ArrayList<>();

    @NeedsRevision("Consider using Sets to check duplicates")
    protected static void register(@Nonnull Lang lang){
        String languageCode = lang.getLanguageCode();
        if(UNIQUE_LANGUAGE_CODES.contains(languageCode)){
            RegistryUtil.combineLang(langWithLanguageCode(languageCode), lang);
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
    protected static void build(){
        if(LANGS.isEmpty()){return;}
        System.out.println("Building language files...");
        LANGS.forEach(Lang::build);
    }
}
