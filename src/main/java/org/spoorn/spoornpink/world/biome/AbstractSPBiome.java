package org.spoorn.spoornpink.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;
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
        genSettings(genSettings, true, true, true, true, true);
    }

    /**
     * There is a Feature Order Cycle issue in vanilla code in {@link net.minecraft.world.biome.source.BiomeSource}
     * that forces all biomes in the game to add features to biomes in the same order.  If this ordering conflicts with
     * some other biome, even that of another mod's, the game will crash with a non-descriptive error message.
     *
     * See https://github.com/Glitchfiend/TerraBlender/issues/21
     *
     * So far,
     *      this was tested with every Biome mod available for 1.18.1 on 2/2/22
     *
     * TODO: Add bamboo patches
     * TODO: Add my own PlacedFeatures for everything to prevent feature order cycle with any other mod's biomes
     */
    protected void genSettings(GenerationSettings.Builder genSettings, boolean addForestFlowers, boolean addMeadowFlowers,
                               boolean addMossyRocks, boolean addSweetBerryBushes, boolean addDefaultGrass) {
        SpoornPinkDefaultBiomeFeatures.addVanillaBasicFeatures(genSettings);
        if (addForestFlowers) DefaultBiomeFeatures.addForestFlowers(genSettings);
        if (addMossyRocks) DefaultBiomeFeatures.addMossyRocks(genSettings);
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addDefaultDisks(genSettings);
        DefaultBiomeFeatures.addDefaultFlowers(genSettings);
        if (addMeadowFlowers) DefaultBiomeFeatures.addMeadowFlowers(genSettings);
        DefaultBiomeFeatures.addExtraDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(genSettings);
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);
        if (addSweetBerryBushes) DefaultBiomeFeatures.addSweetBerryBushes(genSettings);
        if (addDefaultGrass) SpoornPinkDefaultBiomeFeatures.addDefaultGrass(genSettings);
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
