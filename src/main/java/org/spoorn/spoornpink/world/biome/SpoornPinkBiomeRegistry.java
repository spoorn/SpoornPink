package org.spoorn.spoornpink.world.biome;

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

public class SpoornPinkBiomeRegistry {

    public static final RegistryKey<Biome> KIKO_FOREST = registerBiomeKey("kiko_forest");
    public static final RegistryKey<Biome> PINK_FOREST = registerBiomeKey("pink_forest");

    public static final ConfiguredFeature<?, ?> TREE_RICH = Feature.TREE
            // Configure the feature using the builder
            .configure(new TreeFeatureConfig.Builder(
                    SimpleBlockStateProvider.of(SpoornPinkBlocks.CHERRY_LOG.getDefaultState()), // Trunk block provider
                    new StraightTrunkPlacer(20, 3, 0), // places a straight trunk
                    SimpleBlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()), // Foliage block provider
                    new BlobFoliagePlacer(ConstantIntProvider.create(5), ConstantIntProvider.create(0), 5), // places leaves as a blob (radius, offset from trunk, height)
                    new TwoLayersFeatureSize(1, 0, 1) // The width of the tree at different layers; used to see how tall the tree can be without clipping into blocks
            ).build());

    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);
    public static final PlacedFeature TEST = register("test_tree", TREE_RICH.withPlacement(
            PlacedFeatures.createCountExtraModifier(3, 0.1F, 2), SquarePlacementModifier.of(), NOT_IN_SURFACE_WATER_MODIFIER, PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)), BiomePlacementModifier.of()
    ));

    public static void init() {
        register(KIKO_FOREST, SpoornPinkOverworldBiomeCreator.kikoForest());
        register(PINK_FOREST, SpoornPinkOverworldBiomeCreator.pinkForest());
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
