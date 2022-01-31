package org.spoorn.spoornpink.world.gen.feature.overworld.trees;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.property.Properties;
import net.minecraft.structure.Structure;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.BitSetVoxelSet;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.ModifiableWorld;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.ArrayList;
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
        boolean generated = generate(changedBlocks, context.getWorld(), context.getRandom(), context.getOrigin(), context.getConfig());
        if (!generated || changedBlocks.isEmpty()) {
            return false;
        }

        /**
         * Copy vanilla's from {@link TreeFeature}.
         */
        return BlockBox.encompassPositions(Iterables.concat(changedBlocks)).map(box -> {
            VoxelSet voxelSet = placeLogsAndLeaves(context.getWorld(), box, changedBlocks);
            Structure.updateCorner(context.getWorld(), Block.NOTIFY_ALL, voxelSet, box.getMinX(), box.getMinY(), box.getMinZ());
            return true;
        }).orElse(false);
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
            setBlockStateWithoutUpdatingNeighbors(world, pos, config.trunkProvider.getBlockState(random, pos));
            return true;
        }
        return false;
    }

    protected void placeLeavesSquare(Set<BlockPos> changedBlocks, StructureWorldAccess world, Random random, SPTreeConfig config, BlockPos centerPos) {
        int radius = config.radius;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int j = -radius; j <= radius; ++j) {
            for (int k = -radius; k <= radius; ++k) {
                mutable.set(centerPos, j, 0, k);
                placeLeaves(changedBlocks, world, random, mutable, config);
            }
        }
    }

    protected boolean placeLeaves(Set<BlockPos> changedBlocks, StructureWorldAccess world, Random random, BlockPos pos, SPTreeConfig config) {
        if (TreeFeature.canReplace(world, pos)) {
            changedBlocks.add(pos.toImmutable());
            setBlockStateWithoutUpdatingNeighbors(world, pos, config.leavesProvider.getBlockState(random, pos));
            return true;
        }
        return false;
    }

    private static void setBlockStateWithoutUpdatingNeighbors(ModifiableWorld world, BlockPos pos, BlockState state) {
        world.setBlockState(pos, state, Block.NOTIFY_ALL | Block.FORCE_STATE);
    }

    /**
     * Copy vanilla's from {@link TreeFeature}.  This updates neighbor block states for the changed blocks and prevents
     * leaves from decaying if they are near logs.
     */
    private static VoxelSet placeLogsAndLeaves(WorldAccess world, BlockBox box, Set<BlockPos> trunkPositions) {
        ArrayList<Set<BlockPos>> list = Lists.newArrayList();
        BitSetVoxelSet voxelSet = new BitSetVoxelSet(box.getBlockCountX(), box.getBlockCountY(), box.getBlockCountZ());
        int i = 6;
        for (int j = 0; j < 6; ++j) {
            list.add(Sets.newHashSet());
        }
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (BlockPos blockPos : Lists.newArrayList(trunkPositions)) {
            if (box.contains(blockPos)) {
                ((VoxelSet)voxelSet).set(blockPos.getX() - box.getMinX(), blockPos.getY() - box.getMinY(), blockPos.getZ() - box.getMinZ());
            }
            for (Direction direction : Direction.values()) {
                BlockState blockState;
                mutable.set((Vec3i)blockPos, direction);
                if (trunkPositions.contains(mutable) || !(blockState = world.getBlockState(mutable)).contains(Properties.DISTANCE_1_7)) continue;
                ((Set)list.get(0)).add(mutable.toImmutable());
                setBlockStateWithoutUpdatingNeighbors(world, mutable, (BlockState)blockState.with(Properties.DISTANCE_1_7, 1));
                if (!box.contains(mutable)) continue;
                ((VoxelSet)voxelSet).set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
            }
        }
        for (int k = 1; k < 6; ++k) {
            Set<BlockPos> set = list.get(k - 1);
            Set<BlockPos> set2 = list.get(k);
            for (BlockPos blockPos2 : set) {
                if (box.contains(blockPos2)) {
                    ((VoxelSet)voxelSet).set(blockPos2.getX() - box.getMinX(), blockPos2.getY() - box.getMinY(), blockPos2.getZ() - box.getMinZ());
                }
                for (Direction direction2 : Direction.values()) {
                    int l;
                    BlockState blockState2;
                    mutable.set((Vec3i)blockPos2, direction2);
                    if (set.contains(mutable) || set2.contains(mutable) || !(blockState2 = world.getBlockState(mutable)).contains(Properties.DISTANCE_1_7) || (l = blockState2.get(Properties.DISTANCE_1_7).intValue()) <= k + 1) continue;
                    BlockState blockState3 = (BlockState)blockState2.with(Properties.DISTANCE_1_7, k + 1);
                    setBlockStateWithoutUpdatingNeighbors(world, mutable, blockState3);
                    if (box.contains(mutable)) {
                        ((VoxelSet)voxelSet).set(mutable.getX() - box.getMinX(), mutable.getY() - box.getMinY(), mutable.getZ() - box.getMinZ());
                    }
                    set2.add(mutable.toImmutable());
                }
            }
        }
        return voxelSet;
    }
}
