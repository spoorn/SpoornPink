package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import org.spoorn.spoornpink.SpoornPink;

public class SPPlacedFeatures {

    public static final PlacementModifier NOT_IN_SURFACE_WATER_MODIFIER = SurfaceWaterDepthFilterPlacementModifier.of(0);

    public static final PlacedFeature CHERRY_TREE = register("cherry_tree",
            SPConfiguredFeatures.CHERRY_TREE.withPlacement(
                    PlacedFeatures.createCountExtraModifier(3, 0.05F, 2),
                    SquarePlacementModifier.of(),
                    NOT_IN_SURFACE_WATER_MODIFIER,
                    PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                    // TODO: create sapling and update biome allowed
                    BlockFilterPlacementModifier.of(BlockPredicate.wouldSurvive(Blocks.OAK_SAPLING.getDefaultState(), BlockPos.ORIGIN)),
                    BiomePlacementModifier.of()));

    public static void init() {

    }

    public static <PF extends PlacedFeature> PF register(String id, PF placedFeature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, identifier, placedFeature);
    }
}
