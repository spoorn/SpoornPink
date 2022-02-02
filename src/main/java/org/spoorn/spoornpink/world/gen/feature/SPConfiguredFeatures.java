package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SPBlocks;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.List;

public class SPConfiguredFeatures {

    public static final ConfiguredFeature<SPTreeConfig, ?> PINK_BLOSSOM_TREE = register("pink_blossom_tree",
            SPFeatures.PINK_BLOSSOM_TREE.configure(SPTreeConfig.builder().trunkProvider(SimpleBlockStateProvider.of(SPBlocks.PINK_BLOSSOM_LOG))
                    .leavesProvider(SimpleBlockStateProvider.of(SPBlocks.PINK_BLOSSOM_LEAVES))
                    .build())
    );

    public static final ConfiguredFeature<SPTreeConfig, ?> DARK_PINK_BLOSSOM_TREE = register("dark_pink_blossom_tree",
            SPFeatures.PINK_BLOSSOM_TREE.configure(SPTreeConfig.builder().trunkProvider(SimpleBlockStateProvider.of(SPBlocks.PINK_BLOSSOM_LOG))
                    .leavesProvider(SimpleBlockStateProvider.of(SPBlocks.DARK_PINK_BLOSSOM_LEAVES))
                    .build())
    );

    public static final ConfiguredFeature<?, ?> PINK_BLOSSOM_TREES = register("pink_blossom_trees",
            Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(List.of(
                    new RandomFeatureEntry(PINK_BLOSSOM_TREE.withPlacement(), 0.5f),
                    new RandomFeatureEntry(DARK_PINK_BLOSSOM_TREE.withPlacement(), 0.5f)
            ), PINK_BLOSSOM_TREE.withPlacement()))
    );

    public static void init() {

    }

    private static <FC extends FeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF register(String id, CF configuredFeature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        if (BuiltinRegistries.CONFIGURED_FEATURE.containsId(identifier)) {
            throw new IllegalStateException("ConfiguredFeature: \"" + identifier + "\" already exists in the ConfiguredFeatures registry!");
        }

        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configuredFeature);
    }
}
