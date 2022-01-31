package org.spoorn.spoornpink.world.gen.feature.overworld.trees;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Random;
import java.util.Set;

public class PinkBlossomTree extends AbstractSPTree<SPTreeConfig> {

    public PinkBlossomTree(Codec<SPTreeConfig> configCodec) {
        super(configCodec);
    }

    @Override
    protected boolean generate(Set<BlockPos> changedTrunkBlocks, Set<BlockPos> changedLeafBlocks, StructureWorldAccess world, Random random, BlockPos origin, SPTreeConfig config) {
        if (world.toServerWorld().getRegistryKey() != World.OVERWORLD) {
            return false;
        }

        // Force dirt under tree if possible
        placeDirt(changedTrunkBlocks, world, random, origin.down(), config);

        BlockPos.Mutable mutableBlockPos = origin.mutableCopy();
        int height = random.nextInt(config.minHeight, config.maxHeight + 1);
        for (int i = 0; i < height; ++i) {
            placeTrunk(changedTrunkBlocks, world, random, mutableBlockPos, config);
            mutableBlockPos.move(Direction.UP);
        }

        int dHeightDiv2 = Math.abs(config.maxHeight - height)/2;
        int leavesHeight = config.leavesHeight + 1 - dHeightDiv2;  // This is incremented because the bottom block is usually the trunk
        boolean isEven = leavesHeight % 2 == 0;
        int halfHeight = leavesHeight / 2;
        int start_dy = isEven ? halfHeight - 1 : halfHeight;
        // Go down more similar to how vanilla trees have the leaves center around the 2nd or 3rd trunk down.
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

        return true;
    }
}
