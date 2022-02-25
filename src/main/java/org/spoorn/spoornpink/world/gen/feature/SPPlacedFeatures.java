package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ClampedIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import org.spoorn.spoornpink.SpoornPink;

import java.util.HashMap;
import java.util.Map;

public class SPPlacedFeatures {

    public static final Map<String, PlacedFeature> PLACED_FEATURES = new HashMap<>();
    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    /**
     * There is a Feature Order Cycle issue in vanilla code in {@link net.minecraft.world.biome.source.BiomeSource}
     * that forces all biomes in the game to add features to biomes in the same order.  If this ordering conflicts with
     * some other biome, even that of another mod's, the game will crash with a non-descriptive error message.
     *
     * Adding my own placed features, even if they duplicate vanillas, prevents this from happening.
     *
     * See https://github.com/Glitchfiend/TerraBlender/issues/21
     *
     * So far,
     *      this was tested with every Biome mod available for 1.18.1 on 2/2/22
     *
     * TODO: Add bamboo patches
     * TODO: Add my own PlacedFeatures for everything to prevent feature order cycle with any other mod's biomes
     */
    public static final PlacedFeature PATCH_GRASS = PlacedFeatures.register("sp_patch_grass",
            VegetationConfiguredFeatures.PATCH_GRASS.withPlacement(
                    SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature PATCH_TALL_GRASS = PlacedFeatures.register("sp_patch_tall_grass",
            VegetationConfiguredFeatures.PATCH_TALL_GRASS.withPlacement(
                    RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature BAMBOO_PATCH = PlacedFeatures.register("sp_bamboo_patch",
            VegetationConfiguredFeatures.BAMBOO_SOME_PODZOL.withPlacement(
                    CountPlacementModifier.of(10),
                    RarityFilterPlacementModifier.of(20), SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
    
    public static PlacedFeature PINK_LILAC_PATCH;
    public static PlacedFeature PINK_ORCHIDS;
    public static PlacedFeature KIKO_FLOWERS;

    public static void bootstrap() {
        PINK_LILAC_PATCH = PlacedFeatures.register("sp_tall_pink_lilac_patch", SPConfiguredFeatures.PINK_LILACS.withPlacement(
                PlacedFeatures.createCountExtraModifier(4, 0.2f, 1), RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        PINK_ORCHIDS = PlacedFeatures.register("sp_pink_orchids", SPConfiguredFeatures.PINK_ORCHIDS.withPlacement(
                PlacedFeatures.createCountExtraModifier(5, 0.1f, 1), RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        KIKO_FLOWERS = PlacedFeatures.register("sp_kiko_flowers", SPConfiguredFeatures.PINK_ORCHIDS.withPlacement(
                PlacedFeatures.createCountExtraModifier(5, 0.1f, 1), RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    }
    
    public static PlacedFeature registerTree(String id, ConfiguredFeature<?, ?> spTreeCF, Block saplingBlock) {
        PlacedFeature pf = register(id,
                spTreeCF.withPlacement(
                        PlacedFeatures.createCountExtraModifier(3, 0.05F, 2),
                        SquarePlacementModifier.of(),
                        NOT_IN_SURFACE_WATER_MODIFIER,
                        PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                        BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(saplingBlock.getDefaultState(), BlockPos.ORIGIN)),
                        BiomePlacementModifier.of()));
        PLACED_FEATURES.put(id, pf);
        return pf;
    }

    public static <PF extends PlacedFeature> PF register(String id, PF placedFeature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier, placedFeature);
    }
}
