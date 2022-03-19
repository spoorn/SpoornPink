package org.spoorn.spoornpink.world.biome.core;

import com.mojang.datafixers.util.Pair;
import lombok.extern.log4j.Log4j2;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.source.util.MultiNoiseUtil;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

@Log4j2
public class SpoornPinkBiomeProvider extends Region {
    
    public SpoornPinkBiomeProvider(Identifier name, int overworldWeight) {
        super(name, RegionType.OVERWORLD, overworldWeight);
    }

    /**
     * protected final void addBiome(Consumer<Pair<ParameterPoint, RegistryKey<Biome>>> mapper, Temperature temperature,
     * Humidity humidity, Continentalness continentalness, Erosion erosion, Weirdness weirdness, Depth depth, float offset, RegistryKey<Biome> biome) {
     */
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<MultiNoiseUtil.NoiseHypercube, RegistryKey<Biome>>> mapper) {
        log.info("Adding overworld Biomes");
        Set<RegistryKey<Biome>> replaced = new HashSet<>();
        this.addModifiedVanillaOverworldBiomes(mapper, builder -> {
            builder.replaceBiome(BiomeKeys.FOREST, SpoornPinkBiomeRegistry.KIKO_FOREST);
        });
        /*for (Entry<RegistryKey<Biome>, SPBiome> entry : SpoornPinkBiomeRegistry.BIOMES.entrySet()) {
            SPBiome spBiome = entry.getValue();
            *//*
            TODO: Use parameter point data if available.  Remove from KikoForest and PinkForest
            SpoornPinkUtil.ParameterPointData parameterPoints = spBiome.getParameterPoints();
            this.addBiome(mapper, MultiNoiseUtil.ParameterRange.of(parameterPoints.temperatureMin, parameterPoints.temperatureMax),
                MultiNoiseUtil.ParameterRange.of(parameterPoints.humidityMin, parameterPoints.humidityMax),
                MultiNoiseUtil.ParameterRange.of(parameterPoints.continentalnessMin, parameterPoints.continentalnessMax),
                MultiNoiseUtil.ParameterRange.of(parameterPoints.erosionMin, parameterPoints.erosionMax),
                MultiNoiseUtil.ParameterRange.of(parameterPoints.weirdnessMin, parameterPoints.weirdnessMax),
                MultiNoiseUtil.ParameterRange.of(parameterPoints.depth),
                parameterPoints.offset, entry.getKey());*//*
            RegistryKey<Biome> replacementBiome = spBiome.replacementBiome();
            if (replaced.contains(replacementBiome)) {
                throw new RuntimeException("Already replaced biome [" + replacementBiome + "]!  Could not add [" + spBiome + "]");
            } else {
                this.addBiomeSimilar(mapper, spBiome.replacementBiome(), entry.getKey());
                replaced.add(spBiome.replacementBiome());
            }
        }*/
    }

    // Example on how to change the biome's surface
    /*@Override
    public Optional<MaterialRules.MaterialRule> getOverworldSurfaceRules() {
        MaterialRules.MaterialCondition isAtOrAboveWaterLevel = MaterialRules.water(-1, 0);
        MaterialRules.MaterialRule leaves = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.block(SpoornPinkBlocks.PINK_BLOSSOM_LEAVES.getDefaultState())),
                MaterialRules.block(Blocks.DIRT.getDefaultState()));
        MaterialRules.MaterialRule leavesSurface = MaterialRules.condition(MaterialRules.surface(), leaves);

        MaterialRules.MaterialRule defaultMaterialRule = MaterialRules.sequence(MaterialRules.condition(isAtOrAboveWaterLevel, MaterialRules.block(Blocks.GRASS_BLOCK.getDefaultState())),
                MaterialRules.block(Blocks.DIRT.getDefaultState()));
        return Optional.of(MaterialRules.sequence(MaterialRules.condition(MaterialRules.biome(SpoornPinkBiomeRegistry.PINK_FOREST), leavesSurface), defaultMaterialRule));
    }*/
}
