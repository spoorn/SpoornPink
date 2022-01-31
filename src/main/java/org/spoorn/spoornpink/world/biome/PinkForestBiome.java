package org.spoorn.spoornpink.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.GenerationSettings;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkDefaultBiomeFeatures;

public class PinkForestBiome extends AbstractSPBiome {

    private static final BiomeEffects PINK_FOREST_BIOME_EFFECTS = new BiomeEffects.Builder()
            .waterColor(10741503)
            .waterFogColor(10741503)
            .fogColor(16235757)
            .skyColor(16747226)
            .moodSound(BiomeMoodSound.CAVE)
            .music(null)
            .grassColor(16754355)
            .foliageColor(16754355)
            .build();

    @Override
    public BiomeEffects getBiomeEffects() {
        return PINK_FOREST_BIOME_EFFECTS;
    }

    @Override
    public void genSettings(GenerationSettings.Builder genSettings) {
        super.genSettings(genSettings);
        SpoornPinkDefaultBiomeFeatures.addCherryBlossomTrees(genSettings);
    }

    @Override
    public float getTemperature() {
        return 0.8f;
    }

    @Override
    public RegistryKey<Biome> replacementBiome() {
        return BiomeKeys.BIRCH_FOREST;
    }

    @Override
    public SpoornPinkUtil.ParameterPointData getParameterPoints() {
        return SpoornPinkUtil.constructParameterPoint(getTemperature(), getTemperature(), 0.0f, 0.0f, 1.0f, 1.0f, 0f, 0f, -1.0f, -1.0f, 0.0f, 0.0f);
    }
}
