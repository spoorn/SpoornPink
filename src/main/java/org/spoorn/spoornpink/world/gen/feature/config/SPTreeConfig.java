package org.spoorn.spoornpink.world.gen.feature.config;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

/**
 * Custom Tree Config.
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
public class SPTreeConfig implements FeatureConfig {

    public static final Codec<SPTreeConfig> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    (BlockStateProvider.TYPE_CODEC.fieldOf("trunk_provider")).forGetter((spTreeConfig) -> spTreeConfig.trunkProvider),
                    (BlockStateProvider.TYPE_CODEC.fieldOf("leaves_provider")).forGetter(spTreeConfig -> spTreeConfig.leavesProvider),
                    (BlockStateProvider.TYPE_CODEC.fieldOf("dirt_provider")).forGetter(spTreeConfig -> spTreeConfig.dirtProvider),
                    (Codec.INT.fieldOf("min_height")).forGetter(spTreeConfig -> spTreeConfig.minHeight),
                    (Codec.INT.fieldOf("max_height")).forGetter(spTreeConfig -> spTreeConfig.maxHeight),
                    (Codec.INT.fieldOf("radius")).forGetter(spTreeConfig -> spTreeConfig.radius),
                    (Codec.INT.fieldOf("leaves_offset")).forGetter(spTreeConfig -> spTreeConfig.leavesOffset),
                    (Codec.INT.fieldOf("leaves_height")).forGetter(spTreeConfig -> spTreeConfig.leavesHeight))
                    .apply(instance, SPTreeConfig::new));

    public final BlockStateProvider trunkProvider;
    public final BlockStateProvider leavesProvider;

    public final BlockStateProvider dirtProvider;

    public final int minHeight;
    public final int maxHeight;

    public final int radius;  // radius of leaves width
    public final int leavesOffset;
    public final int leavesHeight;

    public static SPTreeConfigBuilder builder() {
        return new SPTreeConfigBuilder();
    }

    @ToString
    public static class SPTreeConfigBuilder {
        private BlockStateProvider trunkProvider = SimpleBlockStateProvider.of(Blocks.OAK_LOG.getDefaultState());
        private BlockStateProvider leavesProvider = SimpleBlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState());
        private BlockStateProvider dirtProvider = SimpleBlockStateProvider.of(Blocks.DIRT.getDefaultState());
        private int minHeight = 5;
        private int maxHeight = 8;
        private int radius = 2;
        private int leavesOffset = 0;
        private int leavesHeight = 3;

        SPTreeConfigBuilder() {
        }

        public SPTreeConfigBuilder trunkProvider(BlockStateProvider trunkProvider) {
            this.trunkProvider = trunkProvider;
            return this;
        }

        public SPTreeConfigBuilder leavesProvider(BlockStateProvider leavesProvider) {
            this.leavesProvider = leavesProvider;
            return this;
        }

        public SPTreeConfigBuilder dirtProvider(BlockStateProvider dirtProvider) {
            this.dirtProvider = dirtProvider;
            return this;
        }

        public SPTreeConfigBuilder minHeight(int minHeight) {
            this.minHeight = minHeight;
            return this;
        }

        public SPTreeConfigBuilder maxHeight(int maxHeight) {
            this.maxHeight = maxHeight;
            return this;
        }

        public SPTreeConfigBuilder radius(int radius) {
            this.radius = radius;
            return this;
        }

        public SPTreeConfigBuilder leavesOffset(int leavesOffset) {
            this.leavesOffset = leavesOffset;
            return this;
        }

        public SPTreeConfigBuilder leavesHeight(int leavesHeight) {
            this.leavesHeight = leavesHeight;
            return this;
        }

        public SPTreeConfig build() {
            return new SPTreeConfig(trunkProvider, leavesProvider, dirtProvider, minHeight, maxHeight, radius, leavesOffset, leavesHeight);
        }
    }
}
