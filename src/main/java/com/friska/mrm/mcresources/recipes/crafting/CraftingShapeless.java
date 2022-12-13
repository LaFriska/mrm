package com.friska.mrm.mcresources.recipes.crafting;

import com.friska.mrm.annotations.ExpectAccess;
import com.friska.mrm.exceptions.CraftingRecipeException;
import com.friska.mrm.serialiser.builder.JArray;
import com.friska.mrm.serialiser.builder.JObject;
import com.friska.mrm.serialiser.builder.JValue;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@ExpectAccess
public class CraftingShapeless extends Crafting<CraftingShapeless>{

    ArrayList<String> ingredients = new ArrayList<>();

    /**
     * This class constructs JSON recipes for shapeless crafting. Shapeless crafting means recipes that do not need a particular pattern of items in a crafting table,
     * as long as the right number of ingredient is present. An example of this would be the fermented spider eye, or eye of ender.
     * **/
    public CraftingShapeless(String result) {
        super(result);
        checkResultForTags(result);
        setAndTruncateName(result);
    }

    /**
     * This method adds multiple instances of a particular item or tag to the recipe. Omit the amount parameter to default the amount to 1.
     * **/
    public CraftingShapeless addIngredient(String id, int amount){
        if(amount == 0) return this;
        if (ingredients.size() + amount > 9) throw new CraftingRecipeException("The number of ingredients in a shapeless crafting recipe cannot exceed 9.");
        for(int i = 0; i <= amount - 1; i++){
            ingredients.add(id);
        }
        return this;
    }

    /**
     * This method adds a singular item or tag to the recipe. Use the method overload to specify an amount of such ingredient.
     * **/
    public CraftingShapeless addIngredient(String id){
        addIngredient(id, 1);
        return this;
    }

    /**
     * Builds the JSON recipe.
     * **/
    @Override
    public void build() {
        super.build();
        getBuilder().nest(new JValue<>("type", "minecraft:crafting_shapeless"));
        JArray ingredients = new JArray("ingredients");
        String idType;
        List<JObject> ingredientJObjectList = new ArrayList<>();
        for (String i : this.ingredients) {
            idType = getIDType(i);
            ingredientJObjectList.add((new JObject(null)).nest(new JValue<>(idType, idType.equals("tag") ? i.substring(1) : i)));
        }
        ingredients.setArray(ingredientJObjectList.toArray());
        getBuilder().nest(ingredients);
        if(this.group != null) getBuilder().nest(new JValue<>("group", group));
        JObject result = new JObject("result");
        result.nest(new JValue<>("item", this.result));
        if(count != null) result.nest(new JValue<>("count", count));
        getBuilder().nest(result);

        getBuilder().build();
    }
}
