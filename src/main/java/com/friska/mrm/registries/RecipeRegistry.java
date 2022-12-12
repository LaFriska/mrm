package com.friska.mrm.registries;

import com.friska.mrm.annotations.NeedsRevision;
import com.friska.mrm.mcresources.recipes.Recipe;
import com.friska.mrm.mcresources.recipes.SmithingRecipe;
import com.friska.mrm.mcresources.recipes.StoneCuttingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.BlastingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.CampfireRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmeltingRecipe;
import com.friska.mrm.mcresources.recipes.cooking.SmokingRecipe;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShaped;
import com.friska.mrm.mcresources.recipes.crafting.CraftingShapeless;

import javax.annotation.Nonnull;
import java.util.ArrayList;

@NeedsRevision("Consider changing everything to be in 1 generified arraylist")
public class RecipeRegistry {

    private static final ArrayList<BlastingRecipe> BLASTING = new ArrayList<>();
    private static final ArrayList<SmokingRecipe> SMOKING = new ArrayList<>();
    private static final ArrayList<SmeltingRecipe> SMELTING = new ArrayList<>();
    private static final ArrayList<CampfireRecipe> CAMPFIRING = new ArrayList<>();
    private static final ArrayList<StoneCuttingRecipe> STONECUTTING = new ArrayList<>();
    private static final ArrayList<SmithingRecipe> SMITHING = new ArrayList<>();
    private static final ArrayList<CraftingShaped> CRAFTING_SHAPED = new ArrayList<>();
    private static ArrayList<CraftingShapeless> CRAFTING_SHAPELESS = new ArrayList<>();

    /**
     * Registers any recipe objects.
     * **/
    public static <T extends Recipe> void register (T recipe){
        addToAppropriateArray(recipe);
    }

    private static <T extends Recipe> void addToAppropriateArray(T recipe){
        if(recipe instanceof SmeltingRecipe){
            SMELTING.add((SmeltingRecipe) recipe);
        }else if(recipe instanceof BlastingRecipe){
            BLASTING.add((BlastingRecipe) recipe);
        }else if(recipe instanceof SmokingRecipe){
            SMOKING.add((SmokingRecipe) recipe);
        }else if(recipe instanceof CampfireRecipe){
            CAMPFIRING.add((CampfireRecipe) recipe);
        }else if(recipe instanceof StoneCuttingRecipe){
            STONECUTTING.add((StoneCuttingRecipe) recipe);
        }else if(recipe instanceof SmithingRecipe){
            SMITHING.add((SmithingRecipe) recipe);
        }else if(recipe instanceof CraftingShaped){
            CRAFTING_SHAPED.add((CraftingShaped) recipe);
        }else if(recipe instanceof CraftingShapeless){
            CRAFTING_SHAPELESS.add((CraftingShapeless) recipe);
        }
    }

    private static @Nonnull ArrayList<Recipe> all(){
        ArrayList<Recipe> arrayList = new ArrayList<>();
        arrayList.addAll(BLASTING);
        arrayList.addAll(SMOKING);
        arrayList.addAll(SMELTING);
        arrayList.addAll(CAMPFIRING);
        arrayList.addAll(STONECUTTING);
        arrayList.addAll(SMITHING);
        arrayList.addAll(CRAFTING_SHAPED);
        arrayList.addAll(CRAFTING_SHAPELESS);
        return arrayList;
    }

    /**
     * Builds all cooking recipes.
     * **/
    public static void build(){
        RegistryUtil.updateNames(all());
        SMELTING.forEach(SmeltingRecipe::build);
        BLASTING.forEach(BlastingRecipe::build);
        SMOKING.forEach(SmokingRecipe::build);
        CAMPFIRING.forEach(CampfireRecipe::build);
        STONECUTTING.forEach(StoneCuttingRecipe::build);
        SMITHING.forEach(SmithingRecipe::build);
        CRAFTING_SHAPED.forEach(CraftingShaped::build);
        CRAFTING_SHAPELESS.forEach(CraftingShapeless::build);
    }
}
