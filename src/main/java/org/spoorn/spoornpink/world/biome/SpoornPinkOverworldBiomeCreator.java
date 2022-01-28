package org.spoorn.spoornpink.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;

public class SpoornPinkOverworldBiomeCreator {

    private static final BiomeEffects KIKO_FOREST_BIOME_EFFECTS = new BiomeEffects.Builder()
            .waterColor(16743104)
            .waterFogColor(12638463)
            .fogColor(16235757)
            .skyColor(16747226)
            .moodSound(BiomeMoodSound.CAVE)
            .music(null)
            .grassColor(11075534)
            .foliageColor(11075534)
            .build();

    public static Biome kikoForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addMushroomMobs(spawnSettings);
        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        addBasicFeatures(genSettings);
        DefaultBiomeFeatures.addDefaultOres(genSettings);
        DefaultBiomeFeatures.addDefaultDisks(genSettings);
        DefaultBiomeFeatures.addMushroomFieldsFeatures(genSettings);
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);
        return createBiome(Biome.Precipitation.RAIN, Biome.Category.PLAINS, 0.7f, 0.4f, KIKO_FOREST_BIOME_EFFECTS, spawnSettings, genSettings);
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

    private static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
    }

    protected static int defaultSkyColor(float temperature) {
        float f = temperature;
        f /= 3.0f;
        f = MathHelper.clamp(f, -1.0f, 1.0f);
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }
}
