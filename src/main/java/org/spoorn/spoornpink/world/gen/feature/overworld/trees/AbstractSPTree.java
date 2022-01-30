package org.spoorn.spoornpink.world.gen.feature.overworld.trees;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Random;
import java.util.Set;

/**
 * Shared functionality for all trees.
 */
public abstract class AbstractSPTree<FC extends SPTreeConfig> extends Feature<FC> {

    public AbstractSPTree(Codec<FC> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<FC> context) {
        Set<BlockPos> changedBlocks = Sets.newHashSet();
        return generate(changedBlocks, context.getWorld(), context.getRandom(), context.getOrigin(), context.getConfig());
    }

    protected abstract boolean generate(Set<BlockPos> changedBlocks, StructureWorldAccess world, Random random, BlockPos origin, SPTreeConfig config);

    protected boolean canGenerateDirt(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, state -> Feature.isSoil(state) && !state.isOf(Blocks.GRASS_BLOCK) && !state.isOf(Blocks.MYCELIUM));
    }

    protected boolean placeDirt(Set<BlockPos> changedBlocks, StructureWorldAccess world, Random random, BlockPos pos, SPTreeConfig config) {
        if (canGenerateDirt(world, pos)) {
            changedBlocks.add(pos.toImmutable());
            world.setBlockState(pos, config.dirtProvider.getBlockState(random, pos), Block.NOTIFY_ALL | Block.FORCE_STATE);
            return true;
        }
        return false;
    }

    protected boolean placeTrunk(Set<BlockPos> changedBlocks, StructureWorldAccess world, Random random, BlockPos pos, SPTreeConfig config) {
        if (TreeFeature.canReplace(world, pos)) {
            changedBlocks.add(pos.toImmutable());
            world.setBlockState(pos, config.trunkProvider.getBlockState(random, pos), Block.NOTIFY_ALL | Block.FORCE_STATE);
            return true;
        }
        return false;
    }
}
