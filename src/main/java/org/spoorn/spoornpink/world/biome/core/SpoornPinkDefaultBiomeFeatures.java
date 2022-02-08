package org.spoorn.spoornpink.world.biome.core;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spoorn.spoornpink.world.gen.feature.SPPlacedFeatures;

/**
 * Similar to vanilla's {@link DefaultBiomeFeatures}.  Generation/Spawn settings that can be used by any biome.
 */
public final class SpoornPinkDefaultBiomeFeatures {

    public static void addPinkBlossomTree(GenerationSettings.Builder genSettings) {
        genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, SPPlacedFeatures.PLACED_FEATURES.get("pink_blossom_trees"));
    }

    public static void addDefaultGrass(GenerationSettings.Builder genSettings) {
        genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, SPPlacedFeatures.PATCH_GRASS);
        genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, SPPlacedFeatures.PATCH_TALL_GRASS);
    }

    public static void addBambooPatches(GenerationSettings.Builder genSettings) {
        genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, SPPlacedFeatures.BAMBOO_PATCH);
    }

    public static void addVanillaBasicMobs(SpawnSettings.Builder spawnSettings) {
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.WOLF, 5, 4, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.FOX, 5, 2, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.BEE, 5, 4, 6));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.CAT, 5, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.LLAMA, 5, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.PANDA, 1, 2, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.RABBIT, 5, 4, 4));
        spawnSettings.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityType.OCELOT, 5, 2, 4));
    }

    public static void addVanillaBasicFeatures(GenerationSettings.Builder generationSettings) {
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
