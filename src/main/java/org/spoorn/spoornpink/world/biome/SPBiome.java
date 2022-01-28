package org.spoorn.spoornpink.world.biome;

import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import terrablender.worldgen.TBClimate;

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
     * Below are parameters used for TerraBlender's {@link terrablender.api.BiomeProvider#addBiome(Consumer, TBClimate.ParameterPoint, RegistryKey).}
     *
     * These have to be unique per biome, else they will override each other.  You can think of the parameters as like
     * a unique key for the biome to be generated.
     *
     * See {@link net.minecraft.world.biome.source.util.VanillaBiomeParameters}.
     */
    SpoornPinkUtil.ParameterPointData getParameterPoints();
}
