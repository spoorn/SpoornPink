package org.spoorn.spoornpink.world.biome.core;

import com.mojang.datafixers.util.Pair;
import lombok.extern.log4j.Log4j2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import org.spoorn.spoornpink.world.biome.SPBiome;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;

import java.util.Map.Entry;
import java.util.function.Consumer;

@Log4j2
public class SpoornPinkBiomeProvider extends BiomeProvider {
    
    public SpoornPinkBiomeProvider(Identifier name, int overworldWeight) {
        super(name, overworldWeight);
    }

    /**
     * protected final void addBiome(Consumer<Pair<ParameterPoint, RegistryKey<Biome>>> mapper, Temperature temperature,
     * Humidity humidity, Continentalness continentalness, Erosion erosion, Weirdness weirdness, Depth depth, float offset, RegistryKey<Biome> biome) {
     */
    @Override
    public void addOverworldBiomes(Registry<Biome> registry, Consumer<Pair<TBClimate.ParameterPoint, RegistryKey<Biome>>> mapper)
    {
        log.info("Adding overworld Biomes");
        for (Entry<RegistryKey<Biome>, SPBiome> entry : SpoornPinkBiomeRegistry.BIOMES.entrySet()) {
            SPBiome spBiome = entry.getValue();
            SpoornPinkUtil.ParameterPointData parameterPoints = spBiome.getParameterPoints();
            this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(parameterPoints.temperatureMin, parameterPoints.temperatureMax),
                    MultiNoiseUtil.ParameterRange.of(parameterPoints.humidityMin, parameterPoints.humidityMax),
                    MultiNoiseUtil.ParameterRange.of(parameterPoints.continentalnessMin, parameterPoints.continentalnessMax),
                    MultiNoiseUtil.ParameterRange.of(parameterPoints.erosionMin, parameterPoints.erosionMax),
                    MultiNoiseUtil.ParameterRange.of(parameterPoints.weirdnessMin, parameterPoints.weirdnessMax),
                    MultiNoiseUtil.ParameterRange.of(parameterPoints.depth),
                    parameterPoints.offset, entry.getKey());
        }
    }
}
