package org.spoorn.spoornpink.world.biome.core;

import lombok.extern.log4j.Log4j2;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;
import org.spoorn.spoornpink.world.biome.KikoForestBiome;
import org.spoorn.spoornpink.world.biome.PinkForestBiome;
import org.spoorn.spoornpink.world.biome.SPBiome;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

@Log4j2
public class SpoornPinkBiomeRegistry {

    public static final Map<RegistryKey<Biome>, SPBiome> BIOMES = new HashMap<>();

    public static final RegistryKey<Biome> KIKO_FOREST = registerBiomeKey("kiko_forest");
    public static final RegistryKey<Biome> PINK_FOREST = registerBiomeKey("pink_forest");

    public static final ConfiguredFeature<?, ?> TREE_RICH = Feature.TREE
            // Configure the feature using the builder
            .configure(new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(SpoornPinkBlocks.CHERRY_LOG.getDefaultState()), // Trunk block provider
                    new StraightTrunkPlacer(8, 3, 0), // places a straight trunk
                    SimpleBlockStateProvider.of(SpoornPinkBlocks.PINK_CHERRY_LEAVES.getDefaultState()), // Foliage block provider
                    new BlobFoliagePlacer(ConstantIntProvider.create(4), ConstantIntProvider.create(0), 3), // places leaves as a blob (radius, offset from trunk, height)
                    new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
            ).build());

    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);
    public static final PlacedFeature TEST = register("test_tree", TREE_RICH.withPlacement(
            PlacedFeatures.createCountExtraModifier(3, 0.05F, 2), SquarePlacementModifier.of(), NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()
    ));

    public static void init() {
        log.info("Initializing Biome Registry");
        BIOMES.put(KIKO_FOREST, new KikoForestBiome());
        BIOMES.put(PINK_FOREST, new PinkForestBiome());

        registerBiomes();
    }

    private static void registerBiomes() {
        log.info("Registering Biomes");
        StringBuilder sb = new StringBuilder();
        for (Entry<RegistryKey<Biome>, SPBiome> entry : BIOMES.entrySet()) {
            RegistryKey<Biome> key = entry.getKey();
            if (!sb.isEmpty()) {
                sb.append(",");
            }
            sb.append(" ");
            sb.append(key.getValue().toString());
            register(key, SpoornPinkOverworldBiomeCreator.constructBiome(entry.getValue()));
        }

        log.info("Registered biomes: [{}]", sb);
    }

    private static Biome register(RegistryKey<Biome> key, Biome biome) {
        return BuiltinRegistries.set(BuiltinRegistries.BIOME, key, biome);
    }

    private static RegistryKey<Biome> registerBiomeKey(String name) {
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(SpoornPink.MODID, name));
    }

    private static PlacedFeature register(String name, PlacedFeature feature) {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(SpoornPink.MODID, name), feature);
    }
}
