package org.spoorn.spoornpink.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkDefaultBiomeFeatures;

public class KikoForestBiome extends AbstractSPBiome {

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

    @Override
    public BiomeEffects getBiomeEffects() {
        return KIKO_FOREST_BIOME_EFFECTS;
    }

    @Override
    public void genSettings(GenerationSettings.Builder genSettings) {
        super.genSettings(genSettings);
        DefaultBiomeFeatures.addMossyRocks(genSettings);
        SpoornPinkDefaultBiomeFeatures.addCherryBlossomTrees(genSettings);
    }

    @Override
    public float getTemperature() {
        return 0.7f;
    }

    @Override
    public SpoornPinkUtil.ParameterPointData getParameterPoints() {
        return SpoornPinkUtil.constructParameterPoint(getTemperature(), getTemperature(), 0.7f, 0.7f, -0.9f, -0.9f, -1.0f, 1.0f, -1.0f, -1.0f, 0.0f, 0.0f);
    }
}
