package org.spoorn.spoornpink.world.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import terrablender.api.BiomeProvider;
import terrablender.api.ParameterUtils;
import terrablender.worldgen.TBClimate;

import java.util.function.Consumer;

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
        this.addBiome(mapper, ParameterUtils.Temperature.COOL, ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Continentalness.FAR_INLAND,
                ParameterUtils.Erosion.FULL_RANGE, ParameterUtils.Weirdness.FULL_RANGE, ParameterUtils.Depth.SURFACE, 0.0f, SpoornPinkBiomeRegistry.KIKO_FOREST);
        this.addBiome(mapper, ParameterUtils.Temperature.NEUTRAL, ParameterUtils.Humidity.NEUTRAL, ParameterUtils.Continentalness.FAR_INLAND,
                ParameterUtils.Erosion.FULL_RANGE, ParameterUtils.Weirdness.FULL_RANGE, ParameterUtils.Depth.SURFACE, 0.0f, SpoornPinkBiomeRegistry.PINK_FOREST);
    }
}
