package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SPConfiguredFeatures {

    public static void init() {

    }
    
    public static ConfiguredFeature<SPTreeConfig, ?> registerSPTreeCF(String id, Feature<SPTreeConfig> feature, SPTreeConfig spTreeConfig) {
        return register(id, feature.configure(spTreeConfig));
    }
    
    public static ConfiguredFeature<?, ?> registerMixOfTrees(String id, ConfiguredFeature<?, ?> defaultCF, ConfiguredFeature<?, ?>... configuredFeatures) {
        return register(id,
                Feature.RANDOM_SELECTOR.configure(new RandomFeatureConfig(Arrays.stream(configuredFeatures)
                            .map(cf -> new RandomFeatureEntry(cf.withPlacement(), 1.0f/configuredFeatures.length))
                            .collect(Collectors.toList()), 
                        defaultCF.withPlacement())));
    }

    private static <FC extends FeatureConfig, F extends Feature<FC>, CF extends ConfiguredFeature<FC, F>> CF register(String id, CF configuredFeature) {
        Identifier identifier = new Identifier(SpoornPink.MODID, id);
        if (BuiltinRegistries.CONFIGURED_FEATURE.containsId(identifier)) {
            throw new IllegalStateException("ConfiguredFeature: \"" + identifier + "\" already exists in the ConfiguredFeatures registry!");
        }

        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, identifier, configuredFeature);
    }
}
