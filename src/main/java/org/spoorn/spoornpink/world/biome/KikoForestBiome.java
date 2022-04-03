package org.spoorn.spoornpink.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.*;
import org.spoorn.spoornpink.particle.SpoornPinkParticles;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import org.spoorn.spoornpink.world.biome.core.SpoornPinkDefaultBiomeFeatures;

public class KikoForestBiome extends AbstractSPBiome {

    private static final BiomeEffects KIKO_FOREST_BIOME_EFFECTS = new BiomeEffects.Builder()
            .waterColor(14204415)
            .waterFogColor(14204415)
            .fogColor(16770039)
            .skyColor(16763375)
            .moodSound(BiomeMoodSound.CAVE)
            .music(null)
            .grassColor(11075534)
            .foliageColor(11075534)
            .particleConfig(new BiomeParticleConfig(SpoornPinkParticles.PINK_BLOSSOM_PETALS, 0.0017f))
            .build();

    @Override
    public BiomeEffects getBiomeEffects() {
        return KIKO_FOREST_BIOME_EFFECTS;
    }

    @Override
    public void genSettings(GenerationSettings.Builder genSettings) {
        super.genSettings(genSettings, false, false, false, true);
        SpoornPinkDefaultBiomeFeatures.addPinkBlossomTree(genSettings);
        SpoornPinkDefaultBiomeFeatures.addPinkLilacAndOrchids(genSettings);
        SpoornPinkDefaultBiomeFeatures.addKikoFlowers(genSettings);
    }

    @Override
    public float getTemperature() {
        return 0.7f;
    }

    @Override
    public float getDownfall() {
        return 0.5f;
    }

    @Override
    public RegistryKey<Biome> replacementBiome() {
        return BiomeKeys.FOREST;
    }

    @Override
    public SpoornPinkUtil.ParameterPointData getParameterPoints() {
        return SpoornPinkUtil.constructParameterPoint(getTemperature(), getTemperature(), 0.3f, 0.3f, 0.9f, 0.9f, -1.0f, 1.0f, -1.0f, -1.0f, 0.0f, 0.0f);
    }
}
