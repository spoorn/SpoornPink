package org.spoorn.spoornpink.world.biome;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spoorn.spoornpink.util.SpoornPinkUtil;

import javax.annotation.Nullable;
import java.util.function.Consumer;

/**
 * Base Biome interface.
 * 
 * ParameterRange temperature, ParameterRange humidity, ParameterRange continentalness, ParameterRange erosion, ParameterRange weirdness, ParameterRange depth, float offset
 */
public interface SPBiome {

    BiomeEffects getBiomeEffects();

    void spawnSettings(SpawnSettings.Builder spawnSettings);

    void genSettings(GenerationSettings.Builder generationSettings);

    Biome.Precipitation getPrecipitation();

    Biome.Category getBiomeCategory();

    float getTemperature();

    float getDownfall();

    /**
     * Gets the vanilla Biome Key to replace in our region, for use with TerraBlender's APIs.
     */
    RegistryKey<Biome> replacementBiome();

    /**
     * Below are parameters used for TerraBlender's {@link terrablender.api.Region#addBiome(Consumer, MultiNoiseUtil.NoiseHypercube, RegistryKey)} .}
     *
     * These have to be unique per biome, else they will override each other.  You can think of the parameters as like
     * a unique key for the biome to be generated.
     *
     * See {@link net.minecraft.world.biome.source.util.VanillaBiomeParameters}.
     *
     * Either use these precise parameters, or a replacement biome with {@link #replacementBiome()}.
     */
    @Nullable
    default SpoornPinkUtil.ParameterPointData getParameterPoints() {
        return null;
    }
}
