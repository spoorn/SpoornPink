package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.feature.VegetationConfiguredFeatures;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SPBlocks;

public class SPPlacedFeatures {

    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    public static final PlacedFeature PINK_BLOSSOM_TREES = register("pink_blossom_trees",
            SPConfiguredFeatures.PINK_BLOSSOM_TREES.withPlacement(
                    PlacedFeatures.createCountExtraModifier(3, 0.05F, 2),
                    SquarePlacementModifier.of(),
                    NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(SPBlocks.PINK_BLOSSOM_SAPLING.getDefaultState(), BlockPos.ORIGIN)),
                    BiomePlacementModifier.of()));

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
    public static final PlacedFeature PATCH_GRASS = PlacedFeatures.register("sp_patch_grass", VegetationConfiguredFeatures.PATCH_GRASS.withPlacement(SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
    public static final PlacedFeature PATCH_TALL_GRASS = PlacedFeatures.register("sp_patch_tall_grass", VegetationConfiguredFeatures.PATCH_TALL_GRASS.withPlacement(RarityFilterPlacementModifier.of(5), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));


    public static void init() {

    }

    public static <PF extends PlacedFeature> PF register(String id, PF placedFeature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier, placedFeature);
    }
}
