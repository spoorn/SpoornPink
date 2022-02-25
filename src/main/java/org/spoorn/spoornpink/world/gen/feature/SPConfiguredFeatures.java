package org.spoorn.spoornpink.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import org.spoorn.spoornpacks.api.Resource;
import org.spoorn.spoornpacks.type.BlockType;
import org.spoorn.spoornpink.SpoornPink;
import org.spoorn.spoornpink.util.SpoornPinkUtil;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Arrays;
import java.util.stream.Collectors;

public class SPConfiguredFeatures {

    public static ConfiguredFeature<RandomPatchFeatureConfig, ?> PINK_LILACS;
    public static ConfiguredFeature<RandomPatchFeatureConfig, ?> PINK_ORCHIDS;
    public static ConfiguredFeature<RandomPatchFeatureConfig, ?> KIKO_FLOWERS;

    public static void bootstrap(Resource resource) {
        PINK_LILACS = ConfiguredFeatures.register("sp_pink_lilacs", Feature.FLOWER.configure(
                createRandomPatchFeatureConfig(BlockStateProvider.of(SpoornPinkUtil.getBlockFromResource(resource, BlockType.TALL_FLOWER, "pink_lilac")), 32)));
        PINK_ORCHIDS = ConfiguredFeatures.register("sp_pink_orchids", Feature.FLOWER.configure(
                createRandomPatchFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(SpoornPinkUtil.getBlockFromResource(resource, BlockType.SMALL_FLOWER, "pink_orchid").getDefaultState(), 1)
                        .add(SpoornPinkUtil.getBlockFromResource(resource, BlockType.SMALL_FLOWER, "pink_orchid2").getDefaultState(), 1)
                        .add(SpoornPinkUtil.getBlockFromResource(resource, BlockType.SMALL_FLOWER, "pink_orchid3").getDefaultState(), 1)
                        .add(SpoornPinkUtil.getBlockFromResource(resource, BlockType.SMALL_FLOWER, "pink_orchid4").getDefaultState(), 1)
                        .add(SpoornPinkUtil.getBlockFromResource(resource, BlockType.SMALL_FLOWER, "pink_orchid5").getDefaultState(), 1)
                ), 64)));
        KIKO_FLOWERS = ConfiguredFeatures.register("sp_kiko_flowers", Feature.FLOWER.configure(
                createRandomPatchFeatureConfig(new WeightedBlockStateProvider(DataPool.<BlockState>builder()
                        .add(Blocks.PEONY.getDefaultState(), 1)
                        .add(Blocks.PINK_TULIP.getDefaultState(), 1)
                        .add(Blocks.WHITE_TULIP.getDefaultState(), 1)
                        .add(Blocks.AZURE_BLUET.getDefaultState(), 1)
                        .add(Blocks.OXEYE_DAISY.getDefaultState(), 1)
                ), 64)));
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

    /**
     * Copied from {@link VegetationConfiguredFeatures}.
     */
    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, Feature.SIMPLE_BLOCK.configure(new SimpleBlockFeatureConfig(block)).withInAirFilter());
    }
}
