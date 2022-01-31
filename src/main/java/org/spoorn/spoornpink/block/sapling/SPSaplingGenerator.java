package org.spoorn.spoornpink.block.sapling;

import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Random;

public class SPSaplingGenerator extends SaplingGenerator {

    private final ConfiguredFeature<SPTreeConfig, ?> configuredFeature;

    public SPSaplingGenerator(ConfiguredFeature<SPTreeConfig, ?> configuredFeature) {
        this.configuredFeature = configuredFeature;
    }

    @Nullable
    @Override
    protected ConfiguredFeature<?, ?> getTreeFeature(Random random, boolean bees) {
        return configuredFeature;
    }
}
