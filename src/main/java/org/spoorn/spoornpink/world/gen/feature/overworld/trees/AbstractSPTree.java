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
        Set<BlockPos> changedTrunkBlocks = Sets.newHashSet();
        Set<BlockPos> changedLeafBlocks = Sets.newHashSet();
        StructureWorldAccess world = context.getWorld();
        boolean generated = generate(changedTrunkBlocks, changedLeafBlocks, world, context.getRandom(), context.getOrigin(), context.getConfig());
        if (!generated || changedTrunkBlocks.isEmpty() && changedLeafBlocks.isEmpty()) {
            return false;
        }

        /**
         * Copy vanilla's from {@link TreeFeature}.
         */
        return BlockBox.encompassPositions(Iterables.concat(changedTrunkBlocks, changedLeafBlocks)).map(box -> {
            VoxelSet voxelSet = placeLogsAndLeaves(world, box, changedTrunkBlocks);
            Structure.updateCorner(world, Block.NOTIFY_ALL, voxelSet, box.getMinX(), box.getMinY(), box.getMinZ());
            return true;
        }).orElse(false);
    }

    protected abstract boolean generate(Set<BlockPos> changedTrunkBlocks, Set<BlockPos> changedLeafBlocks, StructureWorldAccess world, Random random, BlockPos origin, SPTreeConfig config);

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

    protected void placeBulbLeaves(Set<BlockPos> changedLeafBlocks, StructureWorldAccess world, Random random, int height,
                                   BlockPos.Mutable mutableBlockPos, SPTreeConfig config) {
        int dHeightDiv2 = Math.abs(config.maxHeight - height)/2;
        // This is incremented because the bottom block is usually the trunk
        int leavesHeight = config.leavesHeight + 1 - dHeightDiv2;
        boolean isEven = leavesHeight % 2 == 0;
        int halfHeight = leavesHeight / 2;
        // Go down more similar to how vanilla trees have the leaves center around the 2nd or 3rd trunk down.
        int start_dy = isEven ? halfHeight - 1 : halfHeight;
        mutableBlockPos.move(Direction.DOWN, start_dy + 2);
        int startRadius = config.radius;
        // Loop until original leavesHeight so the top isn't cut off even when leaves are shorter for the bottom
        for (int i = 0; i < leavesHeight + dHeightDiv2; i++) {
            if (i > 1) {  // Skip bottom most disk
                int radius = startRadius;
                if (Math.abs(i - halfHeight) > 1) {
                    radius -= Math.abs(i - halfHeight) - 1;  // pyramid shape
                }
                placeLeavesSquare(changedLeafBlocks, world, random, config, mutableBlockPos, radius, i == halfHeight);
            }
            mutableBlockPos.move(Direction.UP);
        }
    }

    protected void placeLeavesSquare(Set<BlockPos> changedBlocks, StructureWorldAccess world, Random random, SPTreeConfig config, BlockPos centerPos, int levelRadius, boolean widestDisk) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int j = -levelRadius; j <= levelRadius; ++j) {
            for (int k = -levelRadius; k <= levelRadius; ++k) {
                boolean jEnd = Math.abs(j) == levelRadius;
                boolean kEnd = Math.abs(k) == levelRadius;
                boolean onlyOneEnd = (jEnd || kEnd) && !(jEnd && kEnd);
                if (levelRadius == config.radius) {
                    if (jEnd && kEnd) {
                        continue;
                    }

                    if (!widestDisk && ((jEnd && Math.abs(k) == levelRadius - 1) || kEnd && Math.abs(j) == levelRadius - 1)) {
                        continue;
                    }
                }
                if ((jEnd && kEnd && random.nextInt(2) == 0) || (onlyOneEnd && random.nextInt(6) == 0)) {
                    continue;
                }
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
