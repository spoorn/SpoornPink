package org.spoorn.spoornpink.world.biome;

import net.minecraft.sound.BiomeMoodSound;
import net.minecraft.world.biome.BiomeEffects;
import org.spoorn.spoornpink.util.SpoornPinkUtil;

public class PinkForestBiome extends AbstractSPBiome {

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

    @Override
    public BiomeEffects getBiomeEffects() {
        return PINK_FOREST_BIOME_EFFECTS;
    }

    @Override
    public SpoornPinkUtil.ParameterPointData getParameterPoints() {
        return SpoornPinkUtil.constructParameterPoint(0.3f, 0.1f, -0.2f, 1.0f, -1.0f, 1.0f, -0.9f, 0.9f, 0.0f, 0.0f);
    }
}
