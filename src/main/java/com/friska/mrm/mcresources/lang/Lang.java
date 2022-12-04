package com.friska.mrm.mcresources.lang;

import com.friska.mrm.annotations.ExpectModdersToAccess;
import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.data.TranslationTypes;
import com.friska.mrm.serialiser.builder.JBreak;
import com.friska.mrm.serialiser.builder.JValue;
import com.friska.mrm.config.Config;
import com.friska.mrm.mcresources.MinecraftJSONResource;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

@NeedsRevision("Yet to be added:" +
        "-Item effects" +
        "-Normal/Splash/Lingering potion effects" +
        "-Banners"+
        "-Colors" +
        "-Translation record override.")
@ExpectModdersToAccess
public class Lang extends MinecraftJSONResource {
    private final String languageCode;


    //JValues
    private ArrayList<Translation> blocks;
    private ArrayList<Translation> items;
    private ArrayList<Translation> instruments;
    private ArrayList<Translation> entities;
    private ArrayList<Translation> effects;
    private ArrayList<Translation> events;
    private ArrayList<Translation> enchantments;
    private ArrayList<Translation> statTypes;
    private ArrayList<Translation> stats;
    private ArrayList<Translation> biomes;
    private ArrayList<Translation> misc;


    /**
     * This class builds a language JSON file.
     * @param languageCode The language code for the file, which would also be its name. For example, en_us indicates that the file is American English.
     *                     <b>Use the LanguageCodes class and call the static strings for the sake of convenience</b>
     * **/
    public Lang(@Nonnull String languageCode){
        super();
        setPath("assets/" + Config.getModID() + "/lang");
        this.languageCode = languageCode;
        setName(languageCode);
        initiateBuilder();
        getBuilder().setPath(this.path);

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
     * Adding a biome translation, key formatted as biome.MODID.ID.
     * **/
    public void addBiome(String biomeID, String translation){biomes.add((new Translation(biomeID, translation)).buildSimpleKey(TranslationTypes.BIOME));}
    public void addBiomes(Translation... translations){List.of(translations).forEach((t) -> biomes.add(t.buildSimpleKey(TranslationTypes.BIOME)));}

    /**
     * Adding a stat and stat type translation, key formatted as stat_type.MODID.ID/stat.MODID.ID.
     * **/
    public void addStatType(String statTypeID, String translation){statTypes.add((new Translation(statTypeID, translation)).buildSimpleKey(TranslationTypes.STAT_TYPE));}
    public void addStatTypes(Translation... translations){List.of(translations).forEach((t) -> statTypes.add(t.buildSimpleKey(TranslationTypes.STAT_TYPE)));}
    public void addStat(String statID, String translation){stats.add((new Translation(statID, translation)).buildSimpleKey(TranslationTypes.STAT));}
    public void addStats(Translation... translations){List.of(translations).forEach((t) -> stats.add(t.buildSimpleKey(TranslationTypes.STAT)));}

    /**
     * Adding an enchantment translation, key formatted as enchantment.MODID.ID.
     * **/
    public void addEnchantment(String enchantmentID, String translation){enchantments.add((new Translation(enchantmentID, translation)).buildSimpleKey(TranslationTypes.ENCHANTMENT));}
    public void addEnchantments(Translation... translations){List.of(translations).forEach((t) -> enchantments.add(t.buildSimpleKey(TranslationTypes.ENCHANTMENT)));}

    /**
     * Adding an event translation, key formatted as event.MODID.ID. An example of an event is the Minecraft raid.
     * **/
    public void addEvent(String eventID, String translation){events.add((new Translation(eventID, translation)).buildSimpleKey(TranslationTypes.EVENT));}
    public void addEvents(Translation... translations){List.of(translations).forEach((t) -> events.add(t.buildSimpleKey(TranslationTypes.EVENT)));}

    /**
     * Adding an effect translation, key formatted as effect.MODID.ID.
     * **/
    public void addEffect(String effectID, String translation){effects.add((new Translation(effectID, translation)).buildSimpleKey(TranslationTypes.EFFECT));}
    public void addEffects(Translation... translations){List.of(translations).forEach((t) -> effects.add(t.buildSimpleKey(TranslationTypes.EFFECT)));}

    /**
     * Adding an entity translation, key formatted as entity.MODID.ID(.SUBIDs).
     * **/
    public void addEntity(String entityID, String translation){entities.add((new Translation(entityID, translation)).buildSimpleKey(TranslationTypes.ENTITY));}
    public void addEntities(Translation... translations){List.of(translations).forEach((t) -> entities.add(t.buildSimpleKey(TranslationTypes.ENTITY)));}

    /**
     * Adding a block translation, key formatted as instrument.MODID.ID. Do not get it confused with goat horn items, instrument dictates the sound and other special properties of a goat horn.
     * **/
    public void addInstrument(String instrumentID, String translation){instruments.add((new Translation(instrumentID, translation)).buildSimpleKey(TranslationTypes.INSTRUMENT));}
    public void addInstruments(Translation... translations){List.of(translations).forEach((t) -> instruments.add(t.buildSimpleKey(TranslationTypes.INSTRUMENT)));}

    /**
     * Adding a block translation, key formatted as block.MODID.ID.
     * **/
    public void addBlock(String blockID, String translation){blocks.add((new Translation(blockID, translation)).buildSimpleKey(TranslationTypes.BLOCK));}
    public void addBlocks(Translation... translations){List.of(translations).forEach((t) -> blocks.add(t.buildSimpleKey(TranslationTypes.BLOCK)));}

    /**
     * Adding an item translation, key formatted as item.MODID.ID.
     * **/
    public void addItem(String itemID, String translation){items.add((new Translation(itemID, translation)).buildSimpleKey(TranslationTypes.ITEM));}
    public void addItems(Translation... translations){
        List.of(translations).forEach((t) -> items.add(t.buildSimpleKey(TranslationTypes.ITEM)));
    }

    /**
     * Manually adding custom translations, lower level access that is not abstracted by methods such as addBlock.
     * **/
    public void addMisc(String key, String translation){misc.add(new Translation(key, translation));}
    public void addMiscs(Translation... translations){misc.addAll(List.of(translations));}

    /**Builds the language JSON file.**/
    public void build() {
        all().forEach(this::iterate);
        getBuilder().build();
    }

    private void iterate(ArrayList<Translation> list){
        list.forEach((t) -> getBuilder().nest(new JValue<>(t.id(), t.translation())));
        getBuilder().nest(new JBreak());
    }

    /**Constructs an ArrayList of all ArrayLists used in the object, i.e. items, blocks, etc. This is used to merge multiple instances of Lang instantiations in LangRegistry.**/
   @NeedsRevision("May be messy")
    public ArrayList<ArrayList<Translation>> all(){
        ArrayList<ArrayList<Translation>> meta = new ArrayList<>();
        meta.add(blocks);
        meta.add(items);
        meta.add(instruments);
        meta.add(entities);
        meta.add(effects);
        meta.add(events);
        meta.add(enchantments);
        meta.add(biomes);
        meta.add(misc);
        return meta;
    }


    /**
     * Absorbing another Lang object to combine it into one. This should only be done when the two objects have the same language code.
     * @param lang The Lang object.
     * **/
    @NeedsRevision("Should be done in a more abstract way, and should be done in registries, not the Lang class itself.")
    public void inject(Lang lang){
        ArrayList<ArrayList<Translation>> all = this.all();
        ArrayList<ArrayList<Translation>> needle = lang.all();
        for(int i = 0; i <= all.size() - 1; i++){
            all.get(i).addAll(needle.get(i));
        }
    }

    /**Gets the language code.**/
    public String getLanguageCode() {
        return languageCode;
    }
}
