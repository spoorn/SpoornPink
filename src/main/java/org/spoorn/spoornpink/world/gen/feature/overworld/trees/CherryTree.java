package org.spoorn.spoornpink.world.gen.feature.overworld.trees;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import org.spoorn.spoornpink.world.gen.feature.config.SPTreeConfig;

import java.util.Random;
import java.util.Set;

public class CherryTree extends AbstractSPTree<SPTreeConfig> {

    public CherryTree(Codec<SPTreeConfig> configCodec) {
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

        int leavesHeight = config.leavesHeight;
        boolean isEven = leavesHeight % 2 == 0;
        int halfHeight = leavesHeight / 2;
        int start_dy = isEven ? halfHeight - 1 : halfHeight;
        mutableBlockPos.move(Direction.DOWN, start_dy + 1);  // down 1 more since the loop above ends with moving up
        for (int i = 0; i < leavesHeight; i++) {
            placeLeavesSquare(changedLeafBlocks, world, random, config, mutableBlockPos);
            mutableBlockPos.move(Direction.UP);
        }

        return true;
    }
}
