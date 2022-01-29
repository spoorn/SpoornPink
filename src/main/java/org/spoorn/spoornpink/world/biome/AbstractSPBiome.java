package org.spoorn.spoornpink.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkDefaultBiomeFeatures;

/**
 * Base Abstract Biome.
 *
 * Most of these defaults are for vanilla Plains from {@link net.minecraft.world.biome.OverworldBiomeCreator}.
 */
public abstract class AbstractSPBiome implements SPBiome {

    @Override
    public void spawnSettings(SpawnSettings.Builder spawnSettings) {
        DefaultBiomeFeatures.addPlainsMobs(spawnSettings);
        SpoornPinkDefaultBiomeFeatures.addVanillaBasicMobs(spawnSettings);
    }

    @Override
    public void genSettings(GenerationSettings.Builder genSettings) {
        SpoornPinkDefaultBiomeFeatures.addVanillaBasicFeatures(genSettings);
        DefaultBiomeFeatures.addForestFlowers(genSettings);
        DefaultBiomeFeatures.addMeadowFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addExtraDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(genSettings);
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);
    }

    @Override
    public Biome.Precipitation getPrecipitation() {
        return Biome.Precipitation.RAIN;
    }

    @Override
    public Biome.Category getBiomeCategory() {
        return Biome.Category.PLAINS;
    }

    @Override
    public float getDownfall() {
        return 0.4f;
    }
}
