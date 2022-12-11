package com.friska.mrm.mcresources.models;

import com.friska.mrm.annotations.ExpectModdersToAccess;

import javax.annotation.Nonnull;

/**
 * Record holding key-value pairs for textures used in models.
 * @param key The key of the texture, for example, "layer0".
 * @param texture The path and name of the texture file, for example, "minecraft:block/acacia_planks".
 * **/
@ExpectModdersToAccess
public record ModelTexture(@Nonnull String key, @Nonnull String texture) {}
