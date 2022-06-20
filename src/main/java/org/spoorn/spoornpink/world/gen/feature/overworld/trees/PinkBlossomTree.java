package org.spoorn.spoornpink.world.gen.feature.overworld.trees;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Set;

public class PinkBlossomTree extends AbstractSPTree<SPTreeConfig> {

    public PinkBlossomTree(Codec<SPTreeConfig> configCodec) {
        super(configCodec);
    }

    @Override
    protected boolean generate(Set<BlockPos> changedTrunkBlocks, Set<BlockPos> changedLeafBlocks, StructureWorldAccess world, Random random, BlockPos origin, SPTreeConfig config) {
        // Force dirt under tree if possible
        placeDirt(changedTrunkBlocks, world, random, origin.down(), config);

        // Trunk
        BlockPos.Mutable mutableBlockPos = origin.mutableCopy();
        int height = random.nextBetween(config.minHeight, config.maxHeight + 1);
        for (int i = 0; i < height; ++i) {
            placeTrunk(changedTrunkBlocks, world, random, mutableBlockPos, config);
            mutableBlockPos.move(Direction.UP);
        }

        // Leaves
        placeBulbLeaves(changedLeafBlocks, world, random, height, mutableBlockPos, config);

        return true;
    }
}
