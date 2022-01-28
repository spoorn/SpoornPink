package org.spoorn.spoornpink.world.biome;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

public class SpoornPinkOverworldBiomeCreator {

    private static final BiomeEffects PINK_FOREST_BIOME_EFFECTS = new BiomeEffects.Builder()
            .waterColor(16764925)
            .waterFogColor(16764925)
            .fogColor(16235757)
            .skyColor(16747226)
            .moodSound(BiomeMoodSound.CAVE)
            .music(null)
            .grassColor(11075534)
            .foliageColor(11075534)
            .build();
    private static final BiomeEffects KIKO_FOREST_BIOME_EFFECTS = new BiomeEffects.Builder()
            .waterColor(14204415)
            .waterFogColor(14204415)
            .fogColor(16235757)
            .skyColor(16747226)
            .moodSound(BiomeMoodSound.CAVE)
            .music(null)
            .grassColor(11075534)
            .foliageColor(11075534)
            .build();

    public static Biome pinkForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addPlainsMobs(spawnSettings);
        addBasicMobs(spawnSettings);
        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        addBasicFeatures(genSettings);
        DefaultBiomeFeatures.addForestFlowers(genSettings);
        DefaultBiomeFeatures.addMeadowFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addExtraDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(genSettings);
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);
        DefaultBiomeFeatures.addMossyRocks(genSettings);

        SpoornPinkDefaultBiomeFeatures.addKikoForestTrees(genSettings);
        return createBiome(Biome.Precipitation.RAIN, Biome.Category.PLAINS, 0.7f, 0.4f, PINK_FOREST_BIOME_EFFECTS, spawnSettings, genSettings);
    }

    public static Biome kikoForest() {
        SpawnSettings.Builder spawnSettings = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addPlainsMobs(spawnSettings);
        addBasicMobs(spawnSettings);
        GenerationSettings.Builder genSettings = new GenerationSettings.Builder();
        addBasicFeatures(genSettings);
        DefaultBiomeFeatures.addForestFlowers(genSettings);
        DefaultBiomeFeatures.addMeadowFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addExtraDefaultFlowers(genSettings);
        DefaultBiomeFeatures.addDefaultMushrooms(genSettings);
        DefaultBiomeFeatures.addDefaultVegetation(genSettings);
        DefaultBiomeFeatures.addMossyRocks(genSettings);

        SpoornPinkDefaultBiomeFeatures.addKikoForestTrees(genSettings);
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

    private static void addBasicMobs(SpawnSettings.Builder spawnSettings) {
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 5, 2, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.BEE, 5, 4, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CAT, 5, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.LLAMA, 5, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PANDA, 1, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 5, 4, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 5, 2, 4));
    }

    private static void addBasicFeatures(GenerationSettings.Builder generationSettings) {
        DefaultBiomeFeatures.addLandCarvers(generationSettings);
        DefaultBiomeFeatures.addAmethystGeodes(generationSettings);
        DefaultBiomeFeatures.addDungeons(generationSettings);
        DefaultBiomeFeatures.addMineables(generationSettings);
        DefaultBiomeFeatures.addSprings(generationSettings);
        DefaultBiomeFeatures.addFrozenTopLayer(generationSettings);
        DefaultBiomeFeatures.addDefaultOres(generationSettings);
        DefaultBiomeFeatures.addDefaultDisks(generationSettings);
    }

    protected static int defaultSkyColor(float temperature) {
        float f = temperature;
        f /= 3.0f;
        f = MathHelper.clamp(f, -1.0f, 1.0f);
        return MathHelper.hsvToRgb(0.62222224f - f * 0.05f, 0.5f + f * 0.1f, 1.0f);
    }
}
