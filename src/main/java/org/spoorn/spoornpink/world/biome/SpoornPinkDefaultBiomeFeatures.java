package org.spoorn.spoornpink.world.biome;

import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.GenerationStep;

/**
 * Similar to vanilla's DefaultBiomeFeatures.  Generation/Spawn settings that can be used by any biome.
 */
public final class SpoornPinkDefaultBiomeFeatures {

    public static void addKikoForestTrees(GenerationSettings.Builder genSettings) {
        genSettings.feature(GenerationStep.Feature.VEGETAL_DECORATION, SpoornPinkBiomeRegistry.TEST);
    }
}
