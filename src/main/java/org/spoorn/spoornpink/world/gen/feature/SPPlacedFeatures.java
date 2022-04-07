package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.spoorn.spoornpacks.api.Resource;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.world.gen.placementmodifier.LeafPilePlacementModifier;

import java.util.HashMap;
import java.util.Map;

public class SPPlacedFeatures {

    public static final Map<String, RegistryEntry<PlacedFeature>> PLACED_FEATURES = new HashMap<>();
    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    /**
     * There is a Feature Order Cycle issue in vanilla code in {@link BiomeSource}
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
    public static final RegistryEntry<PlacedFeature> PATCH_GRASS = PlacedFeatures.register("sp_patch_grass",
            VegetationConfiguredFeatures.PATCH_GRASS,
                    SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> LESS_PATCH_GRASS = PlacedFeatures.register("sp_less_patch_grass",
            VegetationConfiguredFeatures.PATCH_GRASS,
            RarityFilterPlacementModifier.of(3), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> PATCH_TALL_GRASS = PlacedFeatures.register("sp_patch_tall_grass",
            VegetationConfiguredFeatures.PATCH_TALL_GRASS,
                    RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
    public static final RegistryEntry<PlacedFeature> BAMBOO_PATCH = PlacedFeatures.register("sp_bamboo_patch",
            VegetationConfiguredFeatures.BAMBOO_SOME_PODZOL,
                    CountPlacementModifier.of(10),
                    RarityFilterPlacementModifier.of(20), SquarePlacementModifier.of(),
                    PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of());
    
    public static RegistryEntry<PlacedFeature> PINK_LILAC_PATCH;
    public static RegistryEntry<PlacedFeature> PINK_ORCHIDS;
    public static RegistryEntry<PlacedFeature> KIKO_FLOWERS;
    public static RegistryEntry<PlacedFeature> PINK_BLOSSOM_LEAF_PILES;

    public static void bootstrap() {
        PINK_LILAC_PATCH = PlacedFeatures.register("sp_tall_pink_lilac_patch", SPConfiguredFeatures.PINK_LILACS,
                PlacedFeatures.createCountExtraModifier(2, 0.2f, 1), RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        PINK_ORCHIDS = PlacedFeatures.register("sp_pink_orchids", SPConfiguredFeatures.PINK_ORCHIDS,
                PlacedFeatures.createCountExtraModifier(3, 0.1f, 1), RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        KIKO_FLOWERS = PlacedFeatures.register("sp_kiko_flowers", SPConfiguredFeatures.KIKO_FLOWERS,
                PlacedFeatures.createCountExtraModifier(2, 0.2f, 1), RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
        // Placement Modifiers are triggered in order, so put LeafPilePlacementModifier at the end since it's expensive
        PINK_BLOSSOM_LEAF_PILES = PlacedFeatures.register("sp_pink_blossom_leaf_piles", SPConfiguredFeatures.PINK_BLOSSOM_LEAF_PILES,
               // TODO: refactor to use maxHeight from TreeConfig
               BiomePlacementModifier.of(), LeafPilePlacementModifier.of(6, 0.5f, 0.01f));
    }
    
    public static RegistryEntry<PlacedFeature> registerTree(String id, RegistryEntry<ConfiguredFeature<RandomFeatureConfig, Feature<RandomFeatureConfig>>> spTreeCF, Block saplingBlock) {
        RegistryEntry<PlacedFeature> pf = register(id,
                spTreeCF,
                        PlacedFeatures.createCountExtraModifier(3, 0.05F, 2),
                        SquarePlacementModifier.of(),
                        NOT_IN_SURFACE_WATER_MODIFIER,
                        PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                        BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(saplingBlock.getDefaultState(), BlockPos.ORIGIN)),
                        BiomePlacementModifier.of());
        PLACED_FEATURES.put(id, pf);
        return pf;
    }

    public static <CF extends RegistryEntry<? extends ConfiguredFeature<?, ?>>> RegistryEntry<PlacedFeature> register(String id, CF configuredFeature, PlacementModifier... placementModifiers) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return PlacedFeatures.register(identifier.toString(), configuredFeature, placementModifiers);
    }
}
