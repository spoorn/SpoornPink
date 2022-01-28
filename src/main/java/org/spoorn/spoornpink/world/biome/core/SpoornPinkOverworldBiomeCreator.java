package org.spoorn.spoornpink.world.biome.core;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import org.spoorn.spoornpink.world.biome.SPBiome;

public class SpoornPinkOverworldBiomeCreator {

    public static Biome constructBiome(SPBiome spBiome) {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        spBiome.spawnSettings(spawnSettings);
        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        spBiome.genSettings(genSettings);
        return createBiome(spBiome.getPrecipitation(), spBiome.getBiomeCategory(), spBiome.getTemperature(), spBiome.getDownfall(),
                spBiome.getBiomeEffects(), spawnSettings, genSettings);
    }

    private static Biome createBiome(Biome.Precipitation precipitation, Biome.Category category, float temperature, float downfall,
                                       BiomeEffects biomeEffects, SpawnSettings.Builder spawnSettings, GenerationSettings.Builder generationSettings) {
        return new Biome.Builder()
                .precipitation(precipitation)
                .category(category)
                .temperature(temperature)
                .downfall(downfall)
                .effects(biomeEffects)
                .spawnSettings(spawnSettings.build())
                .generationSettings(generationSettings.build())
                .build();
    }
}
