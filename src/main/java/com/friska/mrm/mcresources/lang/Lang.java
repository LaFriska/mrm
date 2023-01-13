package com.friska.mrm.mcresources.lang;

import com.friska.mrm.mcresources.Combinable;
import com.friska.mrm.mcresources.Registrable;
import com.friska.mrm.registries.Registry;
import com.friska.mrm.registries.ResourceManager;
import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.annotations.NeedsRevision;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.mcresources.data.TranslationTypes;
import com.friska.mrm.system.serialiser.builder.JBreak;
import com.friska.mrm.system.util.KeyValue;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@NeedsRevision("Yet to be added:" +
        "-Item effects" +
        "-Normal/Splash/Lingering potion effects" +
        "-Banners"+
        "-Colors" +
        "-Translation record override.")
@ExpectAccess
public class Lang extends MinecraftResource implements Combinable<Lang>, Registrable<Lang> {
    private final String languageCode;


    //KeyValues
    private final ArrayList<KeyValue<String>> blocks;
    private final ArrayList<KeyValue<String>> items;
    private final ArrayList<KeyValue<String>> instruments;
    private final ArrayList<KeyValue<String>> entities;
    private final ArrayList<KeyValue<String>> effects;
    private final ArrayList<KeyValue<String>> events;
    private final ArrayList<KeyValue<String>> enchantments;
    private final ArrayList<KeyValue<String>> statTypes;
    private final ArrayList<KeyValue<String>> stats;
    private final ArrayList<KeyValue<String>> biomes;
    private final ArrayList<KeyValue<String>> misc;


    /**
     * This class builds a language JSON file.
     * @param languageCode The language code for the file, which would also be its name. For example, en_us indicates that the file is American English.
     *                     <b>Use the LanguageCodes class and call the static strings for the sake of convenience</b>
     * **/
    public Lang(@Nonnull String languageCode){
        super("language", "assets/" + Config.getDefaultNamespace() + "/lang", languageCode);

        this.languageCode = languageCode;

        blocks = new ArrayList<>();
        items = new ArrayList<>();
        instruments = new ArrayList<>();
        entities = new ArrayList<>();
        effects = new ArrayList<>();
        events = new ArrayList<>();
        enchantments = new ArrayList<>();
        statTypes = new ArrayList<>();
        stats = new ArrayList<>();
        biomes = new ArrayList<>();
        misc = new ArrayList<>();
    }

    /**
     * Adding a biome value, key formatted as biome.MODID.ID.
     * **/
    public Lang addBiome(String biomeID, String translation){
        biomes.add((new KeyValue<>(biomeID, translation)).buildSimpleTranslationKey(TranslationTypes.BIOME));
        return this;
    }
    @SafeVarargs
    public final Lang addBiomes(KeyValue<String>... translations){List.of(translations).forEach((t) -> biomes.add(t.buildSimpleTranslationKey(TranslationTypes.BIOME)));
    return this;}

    /**
     * Adding a stat and stat type value, key formatted as stat_type.MODID.ID/stat.MODID.ID.
     * **/
    public Lang addStatType(String statTypeID, String translation){statTypes.add((new KeyValue<>(statTypeID, translation)).buildSimpleTranslationKey(TranslationTypes.STAT_TYPE));return this;}
    @SafeVarargs
    public final Lang addStatTypes(KeyValue<String>... translations){List.of(translations).forEach((t) -> statTypes.add(t.buildSimpleTranslationKey(TranslationTypes.STAT_TYPE)));return this;}
    public Lang addStat(String statID, String translation){stats.add((new KeyValue<>(statID, translation)).buildSimpleTranslationKey(TranslationTypes.STAT));return this;}
    @SafeVarargs
    public final Lang addStats(KeyValue<String>... translations){List.of(translations).forEach((t) -> stats.add(t.buildSimpleTranslationKey(TranslationTypes.STAT)));return this;}

    /**
     * Adding an enchantment value, key formatted as enchantment.MODID.ID.
     * **/
    public Lang addEnchantment(String enchantmentID, String translation){enchantments.add((new KeyValue<>(enchantmentID, translation)).buildSimpleTranslationKey(TranslationTypes.ENCHANTMENT));return this;}
    @SafeVarargs
    public final Lang addEnchantments(KeyValue<String>... translations){List.of(translations).forEach((t) -> enchantments.add(t.buildSimpleTranslationKey(TranslationTypes.ENCHANTMENT)));return this;}

    /**
     * Adding an event value, key formatted as event.MODID.ID. An example of an event is the Minecraft raid.
     * **/
    public Lang addEvent(String eventID, String translation){events.add((new KeyValue<>(eventID, translation)).buildSimpleTranslationKey(TranslationTypes.EVENT));return this;}
    @SafeVarargs
    public final Lang addEvents(KeyValue<String>... translations){List.of(translations).forEach((t) -> events.add(t.buildSimpleTranslationKey(TranslationTypes.EVENT)));return this;}

    /**
     * Adding an effect value, key formatted as effect.MODID.ID.
     * **/
    public Lang addEffect(String effectID, String translation){effects.add((new KeyValue<>(effectID, translation)).buildSimpleTranslationKey(TranslationTypes.EFFECT));return this;}
    @SafeVarargs
    public final Lang addEffects(KeyValue<String>... translations){List.of(translations).forEach((t) -> effects.add(t.buildSimpleTranslationKey(TranslationTypes.EFFECT)));return this;}

    /**
     * Adding an entity value, key formatted as entity.MODID.ID(.SUBIDs).
     * **/
    public Lang addEntity(String entityID, String translation){entities.add((new KeyValue<>(entityID, translation)).buildSimpleTranslationKey(TranslationTypes.ENTITY));return this;}
    @SafeVarargs
    public final Lang addEntities(KeyValue<String>... translations){List.of(translations).forEach((t) -> entities.add(t.buildSimpleTranslationKey(TranslationTypes.ENTITY)));return this;}

    /**
     * Adding a block value, key formatted as instrument.MODID.ID. Do not get it confused with goat horn items, instrument dictates the sound and other special properties of a goat horn.
     * **/
    public Lang addInstrument(String instrumentID, String translation){instruments.add((new KeyValue<>(instrumentID, translation)).buildSimpleTranslationKey(TranslationTypes.INSTRUMENT));return this;}
    @SafeVarargs
    public final Lang addInstruments(KeyValue<String>... translations){List.of(translations).forEach((t) -> instruments.add(t.buildSimpleTranslationKey(TranslationTypes.INSTRUMENT)));return this;}

    /**
     * Adding a block value, key formatted as block.MODID.ID.
     * **/
    public Lang addBlock(String blockID, String translation){blocks.add((new KeyValue<>(blockID, translation)).buildSimpleTranslationKey(TranslationTypes.BLOCK));return this;}
    @SafeVarargs
    public final Lang addBlocks(KeyValue<String>... translations){List.of(translations).forEach((t) -> blocks.add(t.buildSimpleTranslationKey(TranslationTypes.BLOCK)));return this;}

    /**
     * Adding an item value, key formatted as item.MODID.ID.
     * **/
    public Lang addItem(String itemID, String translation){items.add((new KeyValue<>(itemID, translation)).buildSimpleTranslationKey(TranslationTypes.ITEM));return this;}
    @SafeVarargs
    public final Lang addItems(KeyValue<String>... translations){
        List.of(translations).forEach((t) -> items.add(t.buildSimpleTranslationKey(TranslationTypes.ITEM)));
        return this;
    }

    /**
     * Manually adding custom translations, lower level access that is not abstracted by methods such as addBlock.
     * **/
    public Lang addMisc(String key, String translation){misc.add(new KeyValue<>(key, translation));return this;}
    @SafeVarargs
    public final Lang addMiscs(KeyValue<String>... translations){misc.addAll(List.of(translations));return this;}

    /**Builds the language JSON file. <b>Calling this method directly from resources is very risky and unsafe, please instantiate a ResourceManager object and register resource there.</b> **/
    @Override
    public void build() {
        createBuilder();
        all().forEach(this::iterate);
        getBuilder().build();
    }

    private void iterate(ArrayList<KeyValue<String>> list){
        list.forEach((t) -> getBuilder().nest(t.toJValue()));
        getBuilder().nest(new JBreak());
    }

    @NeedsRevision("May be messy")
    public ArrayList<ArrayList<KeyValue<String>>> all(){
        ArrayList<ArrayList<KeyValue<String>>> meta = new ArrayList<>();
        meta.add(blocks);
        meta.add(items);
        meta.add(instruments);
        meta.add(entities);
        meta.add(effects);
        meta.add(events);
        meta.add(enchantments);
        meta.add(biomes);
        meta.add(misc);
        meta.add(statTypes);
        meta.add(stats);
        return meta;
    }

    /**Gets the language code.**/
    public String getLanguageCode() {
        return languageCode;
    }

    @Override
    public Lang combine(Lang object) {
        if(this == object) return this;
        ArrayList<ArrayList<KeyValue<String>>> all = this.all();
        ArrayList<ArrayList<KeyValue<String>>> needle = object.all();
        for(int i = 0; i <= all.size() - 1; i++){
            all.get(i).addAll(needle.get(i));
        }
        return this;
    }
    @Override
    public Registry<Lang> getRegistry(ResourceManager resourceManager) {
        return resourceManager.regLang;
    }
}
