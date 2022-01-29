package org.spoorn.spoornpink.world.biome.core;

import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.Codec;
import lombok.extern.log4j.Log4j2;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import org.spoorn.spoornpink.world.biome.KikoForestBiome;
import org.spoorn.spoornpink.world.biome.SPBiome;
import terrablender.api.BiomeProvider;
import terrablender.worldgen.TBClimate;
import terrablender.worldgen.surface.NamespacedSurfaceRuleSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
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

    // Example on how to change the biome's surface
    /*@Override
    public Optional<MaterialRules.MaterialRule> getOverworldSurfaceRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialRule leaves = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.block(SpoornPinkBlocks.PINK_CHERRY_LEAVES.getDefaultState())),
                MaterialRules.block(Blocks.DIRT.getDefaultState()));
        MaterialRules.MaterialRule leavesSurface = MaterialRules.condition(MaterialRules.surface(), leaves);

        MaterialRules.MaterialRule defaultMaterialRule = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.block(Blocks.GRASS_BLOCK.getDefaultState())),
                MaterialRules.block(Blocks.DIRT.getDefaultState()));
        return Optional.of(MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(SpoornPinkBiomeRegistry.PINK_FOREST), leavesSurface), defaultMaterialRule));
    }*/
}
