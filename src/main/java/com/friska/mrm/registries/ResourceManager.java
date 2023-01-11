package com.friska.mrm.registries;

import com.friska.mrm.mcresources.blockstates.BlockState;
import com.friska.mrm.system.annotations.ExpectAccess;
import com.friska.mrm.system.annotations.NeedsRevision;
import com.friska.mrm.mcresources.MinecraftResource;
import com.friska.mrm.mcresources.lang.Lang;
import com.friska.mrm.mcresources.models.BlockModel;
import com.friska.mrm.mcresources.models.Model;
import com.friska.mrm.mcresources.recipes.Recipe;
import com.friska.mrm.system.config.Config;
import com.friska.mrm.system.exceptions.RegistryException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

@ExpectAccess
@SuppressWarnings("unused")
public class ResourceManager {

    private static final HashSet<String> NAMESPACE_POOL = new HashSet<>();

    private final String namespace;
    public Registry<Recipe> regRecipe;
    public Registry<Model<?>> regModel;
    public Registry<Lang> regLang;

    public Registry<BlockState> regBlockState;

    public ResourceManager(){
        this(null);
    }

    public ResourceManager(@Nullable /*If null, should be defaulted to default namespace in Config*/ String namespace){
        this.namespace = namespace == null ? Config.getDefaultNamespace() : namespace;

        if(NAMESPACE_POOL.contains(this.namespace)) throw new RegistryException("Identical namespace found in multiple instantiations of ResourceManager.");
        NAMESPACE_POOL.add(this.namespace);

        regRecipe = new Registry<>(namespace);
        regModel = new Registry<>(namespace);
        regLang = new Registry<>(namespace);
        regBlockState = new Registry<>(namespace);
    }

    /**
     * Registration of one resource.
     * @param resource The resource you wish to register (e.g. SmeltingRecipe object).
     * **/
    @NeedsRevision("Generics bullshit")
    public <T extends MinecraftResource> ResourceManager register(@Nonnull T resource){

        if(resource instanceof Lang){
            regLang.register((Lang) resource);
        } else if (resource instanceof Recipe){
            regRecipe.register((Recipe) resource);
        } else if (resource instanceof Model<?>){
            regModel.register((BlockModel) resource);
        } else if (resource instanceof BlockState){
            regBlockState.register((BlockState) resource);
        }

        return this;
    }

    /**
     * Registration of multiple resources.
     * @param resources The resources you wish to register (e.g. SmeltingRecipe objects, or Lang objects). Separate them via commas.
     * **/
    @SafeVarargs
    public final <T extends MinecraftResource> ResourceManager register(@Nonnull T... resources){
        List.of(resources).forEach(this::register);
        return this;
    }
    /**
     * This method builds all registered managers into actual JSON files. This should be called when all resources are registered.
     * **/
    public ResourceManager build(){
        System.out.println("-------------------------Building resources for namespace \"" + namespace + "\"-------------------------------");
        System.out.println("Started building all Minecraft resources...");
        regLang.build();
        regRecipe.build();
        regModel.build();
        regBlockState.build();
        //System.out.println("-------------------------Minecraft Resource Manager-------------------------------");
        return this;
    }

}
