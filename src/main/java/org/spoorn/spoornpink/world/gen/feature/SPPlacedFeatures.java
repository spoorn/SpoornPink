package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
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

    public static void init() {

    }

    public static <PF extends PlacedFeature> PF register(String id, PF placedFeature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier, placedFeature);
    }
}
