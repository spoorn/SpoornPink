package org.spoorn.spoornpink.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.util.math.BlockPos;

public class SPSignBlockEntity extends SignBlockEntity {

    public SPSignBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public BlockEntityType<?> getType() {
        return SPEntities.PINK_BLOSSOM_SIGNS;
    }
}
