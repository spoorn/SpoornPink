package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.block.SpoornPinkBlocks;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

public class SPConfiguredFeatures {

    public static final ConfiguredFeature<SPTreeConfig, ?> CHERRY_TREE = register("cherry_tree",
            SPFeatures.CHERRY_TREE.configure(SPTreeConfig.builder().trunkProvider(SimpleBlockStateProvider.of(SpoornPinkBlocks.CHERRY_LOG))
                    .leavesProvider(SimpleBlockStateProvider.of(SpoornPinkBlocks.PINK_CHERRY_LEAVES))
                    .build())
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
